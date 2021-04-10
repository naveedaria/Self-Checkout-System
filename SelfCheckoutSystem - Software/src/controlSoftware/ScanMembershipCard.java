package controlSoftware;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
	
<<<<<<< HEAD
	//creating membership card method here **
=======
	
	/**
	 * Method for creating a new membership card
	 * @param newMember 
	 * 		  The name of the member that is getting a new membership card.
	 * @return 
	 * 		  String - new member card number. 
	 */
	public String createNewMembershipCard(String newMember) throws IOException{
		try {
			// if new member string passed is null:
			if(newMember == null) {
				throw new IOException();
			}
			
			// create new, unique card number for new member (going to assume 6 digits per card number)
			boolean getNewNum = false;
			Random random = new Random();
			int maxDigNum = 999999;
			int uniqueNum;
			Integer newNum;
			String newUniqueNum = "";
			
			while(getNewNum == false) {
				uniqueNum = random.nextInt(maxDigNum);
				newNum = Integer.valueOf(uniqueNum);
				if(this.membershipDatabase.containsKey(newNum.toString()) == false) {
					newUniqueNum = newNum.toString();
					getNewNum = true;
				}
			}
			
			// create new card record with new member name, new and unique card number, and activate it
			CardRecord newCardRecord = new CardRecord();
			newCardRecord.cardholder = newMember;
			newCardRecord.number = newUniqueNum;
			newCardRecord.activated = true;
			
			// put card record into the membership card database
			this.membershipDatabase.put(newUniqueNum, newCardRecord);
			
			// return new card number to new member
			return newUniqueNum;
		}
		catch(IOException e) {
			System.out.println("Error creating new card.\n");
			throw e;
		}
	}
	
	
	// wouldn't the tap method do the same thing as below method? so is it necessary?
	// method for a user with a membership card that forgot their card but remembers number
	// also can check if a member has a card or not --> (check membership)
	public String enterMembershipCard(String cardNumber) throws IOException{
		try {
			String memberName;
			if(this.membershipDatabase.containsKey(cardNumber) == true) {
				memberName = membershipDatabase.get(cardNumber).cardholder;
				// review --> acceptable to detect card and tap for this to initialize bonus points
				detectCard(cardNumber, memberName);
				CardData data= this.cardReader.tap(this.inputCard);
				System.out.println("Membership detected.\n");
				return data.getNumber();
			}
			else {
				// review --> what should be returned if error for interface?
				System.out.println("Membership not found.\n");
				throw new TapFailureException();
			}
		}
		catch(IOException e) {
			throw e;
		}
	}
	
	
>>>>>>> refs/heads/Samir's
	

}
