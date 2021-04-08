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
	private CardReader cardReader;
	private Card inputCard;
	
	
	private class GiftCardRecord {
		String number;
		BigDecimal amount;
	}

	private HashMap<String, GiftCardRecord> giftcardDatabase = new HashMap<>();
	

	//QUESTION: new card reader for each instance of paymentbycard/membership class?? 
	// NOTE: Register card reader in driver *****
	
	/**
	 * Constructor 
	 * @param selfCheckout 
	 * 		 Instance of selfCheckout station from control software 
	 * 		     
	 */
	public PaymentByGiftcard(SelfCheckoutStation selfCheckout) {
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
		
		//populating database for the simulation 
		
		//giftcard with balance $25
		String testerCardNumber = "123456";
		BigDecimal amount = new BigDecimal(25);
		GiftCardRecord testerRecord = new GiftCardRecord();
		testerRecord.number = testerCardNumber;
		testerRecord.amount = amount;
		this.giftcardDatabase.put(testerCardNumber, testerRecord);
		
		//giftcard with balance $0
		String testerCardNumber2 = "123567";
		BigDecimal amount2 = new BigDecimal(0);
		GiftCardRecord testerRecord2 = new GiftCardRecord();
		testerRecord2.number = testerCardNumber2;
		testerRecord2.amount = amount2;
		this.giftcardDatabase.put(testerCardNumber2, testerRecord2);
		
		//giftcard with balance $150
		String testerCardNumber3 = "123789";
		BigDecimal amount3 = new BigDecimal(150);
		GiftCardRecord testerRecord3 = new GiftCardRecord();
		testerRecord3.number = testerCardNumber3;
		testerRecord3.amount = amount3;
		this.giftcardDatabase.put(testerCardNumber3, testerRecord3);
		
	}
	
	/**
	 * Detecting the card by creating a Card object for use in giftcard payment process
	 * @param number 
	 * 		 	 The card identification number.
	 *  @param isTapEnabled
	 *            Whether this card is capable of being tapped.
	 */
	public void detectCard(String number, boolean tapEnabled) {
		try {
			this.inputCard = new Card("Gift Card", number, "N/A", null, null, tapEnabled, false);
		}catch(SimulationException e) {
			throw e;
		}
	}
	 
	/**
	 * If the card number passed is a valid key for a giftcard in the hashmap, use any remaining value on that giftcard towards paymentBalance.
	 * @param number 
	 * 		  	The card identification number.
	 *  @param paymentBalance
	 *            The balance owed for the items being purchased in the shopping cart.
	 *  @param isTapEnabled
	 *            Whether this card is capable of being tapped.
	 *  @return BigDecimal
	 *  		The updated paymentBalance is returned; if (-1) is returned the giftcard was either not in the database or tap was disabled.
	 */
	public BigDecimal tapToRedeem(String number, BigDecimal paymentBalance, boolean tapEnabled) throws IOException {
		try {
			detectCard(number, tapEnabled);
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
			
		}catch(SimulationException e) {
			throw e;
		}
		System.out.println("Gift card not in database.\n");
		return new BigDecimal(-1); 
		
	}
	
	/**
	 * Get the remaining balance on a giftcard
	 * @param number 
	 * 		  	The card identification number.
	 *  @return BigDecimal
	 *  		The current balance on a giftcard; (-1) is returned if the card is not in the database. 
	 */
	public BigDecimal getAmount(String number) {
		if(this.giftcardDatabase.containsKey(number)==true) {
			return giftcardDatabase.get(number).amount;
		}else {
			System.out.println("Gift card not in database.\n");
			return new BigDecimal(-1);
		}
	}
	
	
}