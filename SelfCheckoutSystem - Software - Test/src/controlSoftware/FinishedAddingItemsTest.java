package controlSoftware;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;

public class FinishedAddingItemsTest {
	ControlSoftware control;
	Currency currency;
	
	@Before
	public void setUp() throws Exception {
		Currency currency = Currency.getInstance("CAD");
		this.currency = currency;
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
		int scaleMaximumWeight = 500; 
		int scaleSensitivity = 1;  
		
		ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
		this.control = controlSoft;
		this.control.shoppingCart.totalPayment = new BigDecimal(13.00);
	}

	@Test
	public void testFinishedAddingItemsCardTap() {
		boolean useMembershipCard=true; 
		String numberMember = "123456";
		String cardholderMember = "Bob"; 
		boolean tap = true;
		String cardCompany = "RBC VISA"; 
		String type = "Credit"; 
		String number = "2468"; 
		String cardholder = "Bob";;
		String cvv = "345"; 
		String pin = "2345"; 
		boolean isTapEnabled = true;
		boolean hasChip = false; 
		Calendar expiry = Calendar.getInstance();
		expiry.set(Calendar.YEAR, 2023);
		BigDecimal cardLimit = new BigDecimal(1000); 
		BufferedImage signature = null; 
		boolean insertCard = false; 
		String pinInput = "2345";
		
		try {
			control.finishedAddingItems(useMembershipCard, numberMember, cardholderMember, tap, insertCard, cardCompany, cardLimit, type, number, cardholder, cvv, pin, pinInput, isTapEnabled, hasChip, expiry, cardLimit, signature, insertCard, pinInput);
			if (control.change.compareTo(new BigDecimal(0))!=0) {
				fail("Change was supposed to be 0.\n");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			assertTrue(e instanceof Exception);
		}
	}


	@Test
	public void testFinishedAddingItemsCardSwipe() {
		boolean useMembershipCard=true; 
		String numberMember = "123456";
		String cardholderMember = "Bob"; 
		boolean tap = false;
		String cardCompany = "RBC VISA"; 
		String type = "Credit"; 
		String number = "2468"; 
		String cardholder = "Bob"; 
		String cvv = "345"; 
		String pin = "2345"; 
		boolean isTapEnabled = false;
		boolean hasChip = false; 
		Calendar expiry = Calendar.getInstance();
		expiry.set(Calendar.YEAR, 2023);
		BigDecimal cardLimit = new BigDecimal(1000); 
		//random width, height, and colour numeric value used for signature
		BufferedImage signature = new BufferedImage(2,2,2); 
		boolean insertCard = false; 
		String pinInput = "2345";
		
		try {
			control.finishedAddingItems(useMembershipCard, numberMember, cardholderMember, tap, 
					insertCard, cardCompany, cardLimit, type, number, cardholder, cvv, pin, pinInput, isTapEnabled, hasChip, expiry, 
					cardLimit, signature, insertCard, pinInput);
			if (control.change.compareTo(new BigDecimal(0))!=0) {
				fail("Change was supposed to be 0.\n");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			assertTrue(e instanceof Exception);
		}
	}

	
	@Test
	public void testFinishedAddingItemsCash() {
		boolean useMembershipCard=true; 
		String numberMember = "123456";
		String cardholderMember = "Bob"; 
		Coin[] coins = {new Coin(new BigDecimal(1), this.currency), new Coin(new BigDecimal(2), this.currency)};
		Banknote[] banknotes = {new Banknote(5, this.currency), new Banknote(5, this.currency)};
		
		try {
			control.finishedAddingItems(useMembershipCard, numberMember, cardholderMember, useMembershipCard, cardholderMember, null, useMembershipCard, coins, banknotes);
		}catch(Exception e) {
			e.printStackTrace();
			assertTrue(e instanceof Exception);
		}
	}

}
