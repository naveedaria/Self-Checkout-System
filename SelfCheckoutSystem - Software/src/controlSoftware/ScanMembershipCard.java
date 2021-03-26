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
	
	public void ScanMembershipCard(SelfCheckoutStation selfCheckout) {
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
	}
	
	
	//set the attributes of the user's card
	public void detectCard(String type, String number, String cardholder) {
		try {
			this.inputCard = new Card(type, number, cardholder, null, null, true, false);
		}catch(SimulationException e) {
			throw e;
		}
	}
	
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
