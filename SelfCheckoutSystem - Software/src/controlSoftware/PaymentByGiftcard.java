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
	BigDecimal balance; 
	boolean activeStatus = true;
	private CardReader cardReader;
	private Card inputCard;
	//private CardIssuer cardIssuer;
	//status field - null or active 
	//suppose no expiry 
	
	
	//don't need database for now - just use amount on card 
	//plan to tap giftcard 
	
	//either we overload the payment by card methods
	//extend the payment by card class 
	//create separate methods based on payment by card methods (parallel) - PICKED 
	
	
	public PaymentByGiftcard(SelfCheckoutStation selfCheckout) {
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
		//this.cardIssuer = new CardIssuer("Calgary Co-op");
		//card company is COOP 
		
	}
	
	//other parameters will be null - pass in cardnumber, amount 
	public void detectCard(String number) {
		try {
			this.inputCard = new Card("Gift Card", number, "N/A", null, null, true, false);
			//this.cardIssuer.addCardData(number, "N/A", expiry, cvv, cardLimit);
		}catch(SimulationException e) {
			throw e;
		}
	}
	 
	//value is gift card balance 
	public boolean tapToRedeem(BigDecimal value, BigDecimal paymentBalance) throws IOException {
		boolean cardSuccessfullyUsed=true; 
		try {
			CardData data= this.cardReader.tap(this.inputCard);
			if (data==null) {
				cardSuccessfullyUsed = false; 
				System.out.println("Tap is not enabled.\n");
				return cardSuccessfullyUsed;
			}
			
			
			return cardSuccessfullyUsed;
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
	
	

}