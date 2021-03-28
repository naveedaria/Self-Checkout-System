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
	
	// amount here is just used below to test that it works for now
	//card hold number
	BigDecimal amt = new BigDecimal(25.00);
	//payment total
	//BigDecimal pmt = new BigDecimal(15.00);
	
	// NOTE: Register card reader in driver *****
	public void PaymentByCard(SelfCheckoutStation selfCheckout, String cardCompany) {
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
		this.cardIssuer = new CardIssuer(cardCompany);
	}
	
	/**
	 * Detecting the card by creating a Card object for use in payment process
	 * @param type 
	 * 		  The type of card (credit or debit).
	 * @param number 
	 * 		  The card identification number.
	 * 	@param cardholder 
	 * 		  The name of the cardholder.
	 * 	@param cvv 
	 * 		  The card verification value (CVV), a 3- or 4-digit value often on
	 *            the back of the card. This can be null.
	 * 	@param pin 
	 * 		  The personal identification number (PIN) for access to the card.
	 *            This can be null if the card has no chip.
	 *  @param isTapEnabled
	 *            Whether this card is capable of being tapped.
	 *  @param hasChip
	 *            Whether this card has a chip.
	 * 	@param expiry
	 * 		  The expiry date of card.
	 * 	@param cardLimit 
	 * 		  For a credit card, this represents the credit limit. For a debit
	 *            card, this is how much money is available.
	 */
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
	
	/**
	 * Method to calculate payment by coin
	 * @param amount
	 * 		  The total balance amount for payment.
	 * @return 
	 * 		  True if payment successfully processed, false otherwise. 
	 */
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
	}
	
	/**
	 * Method to calculate payment by coin
	 * @param signature
	 * 		  Image of signature required for swiping.
	 * @param amount
	 * 		  The total balance amount for payment.
	 * @return 
	 * 		  True if payment successfully processed, false otherwise. 
	 */
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
	
	/**
	 * Method to calculate payment by coin
	 * @param coinValue
	 * 		  The value of coin used to pay 
	 * @return 
	 * 		  Returns the change if any
	 */
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
	
	/**
	 * Method to calculate payment by coin
	 * @param coinValue
	 * 		  The value of coin used to pay 
	 * @return 
	 * 		  Returns the change if any
	 */
	public boolean authorizeCardPayment(CardData data, BigDecimal actualAmount) throws IOException {
		try {
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
	}
	
	// FOR THIRD ITERATION
//	public boolean cancelReleasePayment(int holdNumber, CardData data) throws IOException {
//		try {
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
//	}
	
}
