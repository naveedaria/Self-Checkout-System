package controlSoftware;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import org.lsmr.selfcheckout.BlockedCardException;
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
	
	// NOTE: Register card reader in driver *****
	public void PaymentByCard(SelfCheckoutStation selfCheckout, String cardCompany) {
		//register the CardReaderListenerStub 
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
		this.cardIssuer = new CardIssuer(cardCompany);
	}
	
	// Aris comment: For things like cardLimt, this is where external API calls would be made to financial db
	// Detecting a card I think its enough to just do type, number, cardholder, cvv, and expiry
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
	
	public boolean tapToPay(BigDecimal amount) throws ChipFailureException, IOException {
		boolean paymentSuccessfullyProcessed = true; 
		try {
			CardData data= this.cardReader.tap(this.inputCard);
			if (data==null) {
				paymentSuccessfullyProcessed = false; 
				System.out.println("Tap is not enabled.\n");
				return paymentSuccessfullyProcessed;
			}
			validateCard();
			boolean verifyPayment = authorizeCardPayment(data, amount);
			if(verifyPayment == false) {
				return false;
			}
			else return paymentSuccessfullyProcessed;
		}catch(IOException e) {
			throw new ChipFailureException();
		}

		//set change to 0 in ControlSoftware if true is returned
		//return paymentSuccessfullyProcessed;
	}
	
	public boolean swipeToPay(BufferedImage signature, BigDecimal amount) throws MagneticStripeFailureException, IOException{
		boolean paymentSuccessfullyProcessed = true; 
		try {
			CardData data= this.cardReader.swipe(this.inputCard, signature);
			
			validateCard();
			boolean verifyPayment = authorizeCardPayment(data, amount);
			if(verifyPayment == false) {
				return false;
			}
			else return paymentSuccessfullyProcessed;
		}catch(IOException e) {
			throw new MagneticStripeFailureException();
		}

		//set change to 0 in ControlSoftware if true is returned

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
	
	
	// amount here is just used below to test that it works for now
	//card hold number
	BigDecimal amt = new BigDecimal(25.00);
	//payment total
	//BigDecimal pmt = new BigDecimal(15.00);
	//--------------------------------------------------------
	
	//authorize the hold
	public boolean authorizeCardPayment(CardData data, BigDecimal actualAmount) throws IOException {
		try {
			//CardData data = this.cardReader.insert(this.inputCard, this.pin);
			int holdNumber = cardIssuer.authorizeHold(data.getNumber(), amt);
			if (holdNumber == -1) {	
				System.out.println("Payment failed - card has insufficient funds, is blocked, or does not exist.\n");
				throw new BlockedCardException();
			}
			else {
				// post transaction
				boolean successOrFailPayment = cardIssuer.postTransaction(data.getNumber(), holdNumber, actualAmount);
				
				// successful payment
				return successOrFailPayment;
			}
		}
		catch(SimulationException e) {
			throw e;
		}
//		catch(BlockedCardException e) {
//			throw e;
//		}
	}
	
	// FOR THIRD ITERATION
//	public boolean cancelReleasePayment(int holdNumber, CardData data) throws IOException {
//		try {
//			//CardData data = this.cardReader.insert(this.inputCard, this.pin);
//			boolean checkReleaseHold = cardIssuer.releaseHold(data.getNumber(), holdNumber);
//			if (checkReleaseHold == false) {
//				System.out.println("Releasing hold on amount on card failed.\n");
//				// failed release of holds
//				return false;
//			}
//			// successful release of holds
//			else return true;
//		}
//		catch(SimulationException e) {
//			throw e;
//		}
//		catch(BlockedCardException e) {
//			throw e;
//		}
//	}
	
}
