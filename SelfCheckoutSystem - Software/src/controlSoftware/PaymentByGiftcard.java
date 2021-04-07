package controlSoftware;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;

import org.lsmr.selfcheckout.BlockedCardException;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.ChipFailureException;
import org.lsmr.selfcheckout.InvalidPINException;
import org.lsmr.selfcheckout.TapFailureException;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.CardIssuer;


public class PaymentByGiftcard {
	//BigDecimal value; 
	private CardReader cardReader;
	private Card inputCard;
	
	
	private class GiftCardRecord {
		String number;
		BigDecimal amount;
		//boolean activated = true; 
	}

	private HashMap<String, GiftCardRecord> giftcardDatabase = new HashMap<>();
	
	
	public PaymentByGiftcard(SelfCheckoutStation selfCheckout) {
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
		//this.value = value; 
		
		//populating database for the simulation 
		String testerCardNumber = "123456";
		//String testerCardHolder = "Bob";
		BigDecimal amount = new BigDecimal(25);
		GiftCardRecord testerRecord = new GiftCardRecord();
		testerRecord.number = testerCardNumber;
		testerRecord.amount = amount;
		this.giftcardDatabase.put(testerCardNumber, testerRecord);
	}
	
	//other parameters will be null - pass in cardnumber
	//handled in listeners 
	public void detectCard(String number) {
		try {
			this.inputCard = new Card("Gift Card", number, "N/A", null, null, true, false);
		}catch(SimulationException e) {
			throw e;
		}
	}
	 
	//number is gift card number 
	//return value is updated paymentBalance (-1 means gift card wasn't used or useless) 
	public BigDecimal tapToRedeem(String number, BigDecimal paymentBalance) throws IOException {
		try {
			detectCard(number);
			CardData data= this.cardReader.tap(this.inputCard);
			if (data==null) { 
				System.out.println("Tap is not enabled.\n");
				return new BigDecimal(-1);
			}
			
			//check if in database - check valid card 
			if(this.giftcardDatabase.containsKey(number)==true) {
				System.out.println("Gift card detected and tapped.\n");
				
				//Gift Card does not have any money left 
				if (giftcardDatabase.get(number).amount.compareTo(new BigDecimal(0))==0) {
					System.out.println("0 balance remaining on Gift Card.\n");
					return paymentBalance;
				} //Gift Card has money on it 
				else if(giftcardDatabase.get(number).amount.compareTo(new BigDecimal(0))==1) {
					//covers case where gift card balance is equal to or greater than amount owed (paymentBalance)
					if (giftcardDatabase.get(number).amount.compareTo(paymentBalance)>=0) {
						//Just in case we want to update giftcard value for database 
						
						GiftCardRecord updatedRecord = new GiftCardRecord();
						updatedRecord.number = number; 
						updatedRecord.amount = giftcardDatabase.get(number).amount.subtract(paymentBalance);
						this.giftcardDatabase.put(number, updatedRecord);
						
						return new BigDecimal(0);
					}//covers the case where the giftcard amount is insufficient for paymentBalance 
					else {
						BigDecimal updatedPaymentBalance = paymentBalance.subtract(giftcardDatabase.get(number).amount);
						
						GiftCardRecord updatedRecord = new GiftCardRecord();
						updatedRecord.number = number; 
						updatedRecord.amount = new BigDecimal(0);
						this.giftcardDatabase.put(number, updatedRecord);
			
						return updatedPaymentBalance;
					}
				}

			}
			
		}catch(BlockedCardException e) {
			throw e; 
		}catch(SimulationException e) {
			throw e;
		}
		System.out.println("Gift card not in database.\n");
		return new BigDecimal(-1);

		
		//Use Cases: card balance is greater than amount (left over amount on card)
		//Use Case: card balance is less than amount (set status to null once amount used) 
		//Use Case: is card is null - return a false boolean to say card wasn't used 
		
	}
	public BigDecimal getAmount(String number) {
		if(this.giftcardDatabase.containsKey(number)==true) {
			return giftcardDatabase.get(number).amount;
		}else {
			System.out.println("Gift card not in database.\n");
			return new BigDecimal(-1);
		}
	}
	
	
	//QUESTION: new card reader for each instance of paymentbycard/membership class?? 

	

}