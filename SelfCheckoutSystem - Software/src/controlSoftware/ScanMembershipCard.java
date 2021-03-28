package controlSoftware;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.TapFailureException;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class ScanMembershipCard {
	private CardReader cardReader;
	private Card inputCard;
	
	/**
	 * Constructor.
	 * @param selfCheckout 
	 * 		  The specific checkout station instance.
	 */
	public void ScanMembershipCard(SelfCheckoutStation selfCheckout) {
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
	}
	
	/**
	 * Method to create the Card object with certain membership card attributes.
	 * @param number 
	 * 		  The card identification number.
	 * @param cardHolder 
	 * 		  The name of the card holder. 
	 */
	public void detectCard(String number, String cardHolder) {
		try {
			this.inputCard = new Card("Membership", number, cardHolder, null, null, true, false);
		}catch(SimulationException e) {
			throw e;
		}
	}
	
	/**
	 * Method to tap membership card and note the number before making a payment - if applicable.
	 * @return 
	 * 		  String - card number. 
	 */
	public String tapMembershipCard() throws IOException {
		try {
			CardData data= this.cardReader.tap(this.inputCard);
			System.out.println("Membership detected and tapped.\n");
			return data.getNumber();
		}
		catch(IOException e) {
			throw new TapFailureException();
		}
	}
}
