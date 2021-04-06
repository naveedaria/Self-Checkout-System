package controlSoftware;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import org.lsmr.selfcheckout.BlockedCardException;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.ChipFailureException;
import org.lsmr.selfcheckout.InvalidPINException;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.CardIssuer;

public class PaymentByGiftcard {
	BigDecimal value; 
	private CardReader cardReader;
	private Card inputCard;
	
	//do we want to include this? we might need a database to check the status of Co-op issued cards?
	//boolean activeStatus = true;
	
	//private CardIssuer cardIssuer;
	//status field - null or active 
	//suppose no expiry 
	
	
	//don't need database for now - just use amount on card 
	//use database - similar to scan membership class (hash map) 
	
	//either we overload the payment by card methods
	//extend the payment by card class 
	//create separate methods based on payment by card methods (parallel) - PICKED 
	
	
	public PaymentByGiftcard(SelfCheckoutStation selfCheckout, BigDecimal value) {
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
		this.value = value; 
	}
	
	//other parameters will be null - pass in cardnumber, amount 
	//handled in listeners 
	public void detectCard(String number) {
		try {
			this.inputCard = new Card("Gift Card", number, "N/A", null, null, true, false);
		}catch(SimulationException e) {
			throw e;
		}
	}
	 
	//value is gift card balance 
	//return value is updated paymentBalance (-1 means gift card wasn't used or useless) 
	public BigDecimal tapToRedeem(BigDecimal paymentBalance) throws IOException {
		//boolean cardSuccessfullyUsed=true; 
		try {
			CardData data= this.cardReader.tap(this.inputCard);
			if (data==null) { 
				System.out.println("Tap is not enabled.\n");
				return new BigDecimal(-1);
			}
			
			//Gift Card does not have any money left 
			if (value.compareTo(new BigDecimal(0))==0) {
				System.out.println("0 balance remaining on Gift Card.\n");
				return new BigDecimal(-1);
			} //Gift Card value is valid 
			else if(value.compareTo(new BigDecimal(0))==1) {
				//Just in case we want to update giftcard value for database 
				//this.value = paymentBalance.subtract(value);
				return paymentBalance.subtract(value);
			}//Gift Card value is negative 
			else {
				System.out.println("Invalid balance for Gift Card entered.\n");
				return new BigDecimal(-1);
			}
		}catch(BlockedCardException e) {
			throw e; 
		}catch(SimulationException e) {
			throw e;
		}
		
		//Use Cases: card balance is greater than amount (left over amount on card)
		//Use Case: card balance is less than amount (set status to null once amount used) 
		//Use Case: is card is null - return a false boolean to say card wasn't used 
		
	}
	
	//QUESTION: new card reader for each instance of paymentbycard/membership class?? 
	//QUESTION: do we need databases/hashmaps for giftcard or attendant classes? Requirement of databases?? 
			// ask TA ****
	

}