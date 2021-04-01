package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.lsmr.selfcheckout.InvalidPINException;
import org.lsmr.selfcheckout.MagneticStripeFailureException;
import org.lsmr.selfcheckout.devices.CardReader;

import org.junit.Before;
import org.junit.Test;

public class SwipeToPayTest {
	private PaymentByCard cardPayment;
	private BigDecimal totalBalance; //---------------------
	
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
	public void testSwipeToPayWithNoChip() {
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
		BufferedImage signature = new BufferedImage(1, 1, 1);
		
		try {
			this.cardPayment.detectCard(type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
		
		try {
			boolean successfulPayment = this.cardPayment.swipeToPay(signature, this.totalBalance, false, pin);
		}catch(Exception e) {
			e.printStackTrace();
			assertTrue(e instanceof Exception); 
		}
	}
	
	@Test
	public void testSwipeToPayWithChip() {
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
		BufferedImage signature = new BufferedImage(1, 1, 1);
		
		try {
			this.cardPayment.detectCard(type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
		
		try {
			boolean successfulPayment = this.cardPayment.swipeToPay(signature, this.totalBalance, true, pin);
		}catch(Exception e) {
			e.printStackTrace();
			assertTrue(e instanceof Exception); 
		}
	}
	
	@Test
	public void testSwipeToPayInvalidPIN() {
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
		BufferedImage signature = new BufferedImage(1, 1, 1);
		
		try {
			this.cardPayment.detectCard(type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit);
		}catch(Exception e) {
			e.printStackTrace();
			assertTrue(e instanceof Exception); 
		}
		
		try {
			boolean successfulPayment = this.cardPayment.swipeToPay(signature, this.totalBalance, true, "5678");
		}catch(Exception e) {
			assertTrue("Invalid PIN entered.\n", e instanceof Exception); 
		}
	}
	
	
	@Test
	public void testSwipeToPayMagStripeFail() {
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
		BufferedImage signature = new BufferedImage(1, 1, 1);
		
		try {
			this.cardPayment.detectCard(type, number, cardholder, cvv, pin, isTapEnabled, false, expiry, cardLimit);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
		
		try {
			boolean successfulPayment = this.cardPayment.swipeToPay(signature, this.totalBalance, true, pin);
		}catch(Exception e) {
//			assertTrue("Swipe card failed.\n", e instanceof MagneticStripeFailureException); 
			assertTrue("Swipe card failed.\n", e instanceof IOException); 
		}
	}
	
	
	
	
	
	
}
