package controlSoftware;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.BlockedCardException;
import org.lsmr.selfcheckout.ChipFailureException;
import org.lsmr.selfcheckout.devices.SimulationException;

public class TapToPayTest {
	private PaymentByCard cardPayment;
	private BigDecimal totalBalance;
	
	@Before
	public void setUp() throws Exception {
		Currency currency = Currency.getInstance("CAD");
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
		int scaleMaximumWeight = 500; 
		int scaleSensitivity = 1;  
		
		ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
		PaymentByCard cardPayment = new PaymentByCard(controlSoft.selfCheckout, "RBC Visa");
		this.cardPayment = cardPayment;
		
		//Suppose the shopping balance is $100.00
		this.totalBalance = new BigDecimal(100);
	}

	@Test
	public void testInvalidCard() {
		String type = null;
		String number = "24689";
		String cardholder = "Bob";
		String cvv = "321";
		String pin = "1234";
		boolean isTapEnabled = true;
		boolean hasChip = true; 
		Calendar expiry = Calendar.getInstance();
		expiry.set(Calendar.YEAR, 2023);
		BigDecimal cardLimit = new BigDecimal(1000);
		
		try {
			this.cardPayment.detectCard(type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit);
		}catch(Exception e) {
			assertTrue("Invalid input.\n", e instanceof SimulationException);
		}
	}
	
	
	@Test
	public void testTapDisabled() throws ChipFailureException, IOException {
		String type = "Credit Card";
		String number = "24689";
		String cardholder = "Bob";
		String cvv = "321";
		String pin = "1234";
		boolean isTapEnabled = false;
		boolean hasChip = true; 
		Calendar expiry = Calendar.getInstance();
		expiry.set(Calendar.YEAR, 2023);
		BigDecimal cardLimit = new BigDecimal(1000);
		
		try {
			this.cardPayment.detectCard(type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
		boolean expected = false;
		boolean successfulPayment = this.cardPayment.tapToPay(this.totalBalance, true);
		assertTrue(expected == successfulPayment);
	}
	

	@Test
	public void testNoChipInsertAttempt() {
		String type = "Credit Card";
		String number = "24689";
		String cardholder = "Bob";
		String cvv = "321";
		String pin = "1234";
		boolean isTapEnabled = true;
		boolean hasChip = false; 
		Calendar expiry = Calendar.getInstance();
		expiry.set(Calendar.YEAR, 2023);
		BigDecimal cardLimit = new BigDecimal(1000);
		
		try {
			this.cardPayment.detectCard(type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
		
		try {
			boolean successfulPayment = this.cardPayment.tapToPay(this.totalBalance, true);
		}catch(Exception e) {
			assertTrue("Invalid card insertion.\n", e instanceof ChipFailureException);
		}
	}
	
	@Test
	public void testExceedCardLimit() {
		String type = "Credit Card";
		String number = "24689";
		String cardholder = "Bob";
		String cvv = "321";
		String pin = "1234";
		boolean isTapEnabled = true;
		boolean hasChip = true; 
		Calendar expiry = Calendar.getInstance();
		expiry.set(Calendar.YEAR, 2023);
		//Suppose limit is less than total balance for payment 
		BigDecimal cardLimit = new BigDecimal(50);
		
		try {
			this.cardPayment.detectCard(type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
		
		try {
			boolean successfulPayment = this.cardPayment.tapToPay(this.totalBalance, true);
		}catch(Exception e) {
			assertTrue("Card limit is less than total balance payment.\n", e instanceof BlockedCardException);
		}
	}
	
	@Test
	public void testValidPaymentWithChip() {
		String type = "Credit Card";
		String number = "24689";
		String cardholder = "Bob";
		String cvv = "321";
		String pin = "1234";
		boolean isTapEnabled = true;
		boolean hasChip = true; 
		Calendar expiry = Calendar.getInstance();
		expiry.set(Calendar.YEAR, 2023);
		BigDecimal cardLimit = new BigDecimal(1000);
		
		try {
			this.cardPayment.detectCard(type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
		
		try {
			boolean successfulPayment = this.cardPayment.tapToPay(this.totalBalance, true);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testValidPaymentWithoutChip() {
		String type = "Credit Card";
		String number = "24689";
		String cardholder = "Bob";
		String cvv = "321";
		String pin = "1234";
		boolean isTapEnabled = true;
		boolean hasChip = false; 
		Calendar expiry = Calendar.getInstance();
		expiry.set(Calendar.YEAR, 2023);
		BigDecimal cardLimit = new BigDecimal(1000);
		
		try {
			this.cardPayment.detectCard(type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
		
		try {
			//don't insert card because hasChip==false
			boolean successfulPayment = this.cardPayment.tapToPay(this.totalBalance, false);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected");  
		}
	}

}
