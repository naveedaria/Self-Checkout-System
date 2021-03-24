package controlSoftware;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.ChipFailureException;
import org.lsmr.selfcheckout.MagneticStripeFailureException;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.CardIssuer;

//should work for both debit and credit, unless they need unique payment software specifications 
public class PaymentByCard {
	private CardReader cardReader;
	private Card inputCard;
	private String pin;
	private CardIssuer cardIssuer;
	
	public void PaymentByCard(SelfCheckoutStation selfCheckout, String cardCompany) {
		//register the CardReaderListenerStub 
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
		this.cardIssuer = new CardIssuer(cardCompany);
	}
	
	//set the attributes of the user's card
	public void detectCard(String type, String number, String cardholder, String cvv, String pin, boolean isTapEnabled,
			boolean hasChip, Calendar expiry, BigDecimal cardLimit) {
		try {
			this.inputCard = new Card(type, number, cardholder, cvv, pin, isTapEnabled, hasChip);
			this.pin = pin;
			this.cardIssuer.addCardData(number, cardholder, expiry, cvv, cardLimit);
		}catch(SimulationException e) {
			throw e;
		}
	}
	
	public boolean tapToPay() throws ChipFailureException, IOException {
		boolean paymentSuccessfullyProcessed = true; 
		try {
			CardData data= this.cardReader.tap(this.inputCard);
			if (data==null) {
				paymentSuccessfullyProcessed = false; 
				System.out.println("Tap is not enabled.\n");
				return paymentSuccessfullyProcessed;
			}
		}catch(IOException e) {
			throw new ChipFailureException();
		}
		
		validateCard();
		authorizeCardPayment();
		//set change to 0 in ControlSoftware if true is returned
		return paymentSuccessfullyProcessed;
	}
	
	public boolean swipeToPay(BufferedImage signature) throws MagneticStripeFailureException, IOException{
		boolean paymentSuccessfullyProcessed = true; 
		try {
			CardData data= this.cardReader.swipe(this.inputCard, signature);
		}catch(IOException e) {
			throw new MagneticStripeFailureException();
		}
		
		validateCard();
		authorizeCardPayment();
		//set change to 0 in ControlSoftware if true is returned
		return paymentSuccessfullyProcessed;
	}
	
	public void validateCard() throws IOException {
		try {
			CardData data= this.cardReader.insert(this.inputCard, this.pin);
			this.cardReader.remove();
			if (data==null) {
				System.out.println("Card does not have a chip, pin entry not required.\n");
			}
		}catch(SimulationException e) {
			throw e;
		}catch(ChipFailureException e) {
			throw e;
		}
	}
	
	public void authorizeCardPayment() {
		//authorize the hold
		//post the transaction
	}
}
