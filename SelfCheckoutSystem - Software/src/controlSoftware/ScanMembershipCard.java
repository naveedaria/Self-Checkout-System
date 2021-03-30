package controlSoftware;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.TapFailureException;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class ScanMembershipCard {
	private CardReader cardReader;
	private Card inputCard;
	
	private class CardRecord {
		String number;
		String cardholder;
		boolean activated = true; 
	}

	private HashMap<String, CardRecord> membershipDatabase = new HashMap<>();
	
	
	/**
	 * Constructor.
	 * @param selfCheckout 
	 * 		  The specific checkout station instance.
	 */
	public ScanMembershipCard(SelfCheckoutStation selfCheckout) {
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		selfCheckout.cardReader.register(cardReaderListener);
		this.cardReader = selfCheckout.cardReader;
		
		//populating database for the simulation 
		String testerCardNumber = "123456";
		String testerCardHolder = "Bob";
		CardRecord testerRecord = new CardRecord();
		testerRecord.number = testerCardNumber;
		testerRecord.cardholder = testerCardHolder;
		this.membershipDatabase.put(testerCardNumber, testerRecord);
	}
	
	/**
	 * Method to create the Card object with certain membership card attributes.
	 * @param number 
	 * 		  The card identification number.
	 * @param cardHolder 
	 * 		  The name of the card holder. 
	 */
	private void detectCard(String number, String cardHolder) {
		try {
			this.inputCard = new Card("Membership", number, cardHolder, null, null, true, false);
		}catch(SimulationException e) {
			throw e;
		}
	}
	
	/**
	 * Method to tap membership card and note the number before making a payment - if applicable.
	 * @param number 
	 * 		  The card identification number.
	 * @param cardHolder 
	 * 		  The name of the card holder. 
	 * @return 
	 * 		  String - card number. 
	 */
	public String tapMembershipCard(String number, String cardHolder) throws IOException {
		try {
			detectCard(number, cardHolder);
			CardData data= this.cardReader.tap(this.inputCard);
			//use the key that was added to populate database in constructor for testing 
			if(this.membershipDatabase.containsKey(number)==true) {
				System.out.println("Membership detected and tapped.\n");
				return data.getNumber();
			}else {
				System.out.println("Membership card not in database.\n");
				throw new TapFailureException();
			}
		}catch(SimulationException e) {
			throw e;
		}catch(IOException e) {
			throw e;
		}
	}
	

}
