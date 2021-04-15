package controlSoftware;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.ChipFailureException;
import org.lsmr.selfcheckout.MagneticStripeFailureException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ControlSoftTest {
	ControlSoftware control;
	
	@Before
	public void setUp() throws Exception {
		Currency currency = Currency.getInstance("CAD");
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
		int scaleMaximumWeight = 500; 
		int scaleSensitivity = 1;  
		
		ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
		this.control = controlSoft;
		this.control.shoppingCart.totalPayment = new BigDecimal(10.00);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void calculateBillPaymentTest() {
		
		BigDecimal expectedChange = new BigDecimal("-5");
				
		control.calculateBillPayment(5);
		
		assertEquals(expectedChange, control.change);
		
	}
	
	@Test
	public void calculateCoinPaymentTest() {
		
		BigDecimal expectedChange = new BigDecimal("-2");

		BigDecimal payment = new BigDecimal("2");

		control.calculateCoinPayment(payment);
		
		assertEquals(expectedChange, control.change);
		
	}
	
	@Test
	public void testSwipeToPay() throws MagneticStripeFailureException, IOException {
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
			control.swipeToPay(pin, type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit, null, hasChip);

		} catch(Exception e) {
			assertTrue(e instanceof NullPointerException); 

		}
		
		assertFalse(new BigDecimal("0") == control.change);
	
	}
	
	@Test
	public void testTapToPay() throws ChipFailureException, IOException {
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
			control.tapToPay(pin, type, number, cardholder, cvv, pin, isTapEnabled, hasChip, expiry, cardLimit, hasChip);
		} catch(Exception e) {
			assertTrue(e instanceof NullPointerException); 

		}
		assertFalse(new BigDecimal("0") == control.change);
	}
	
	@Test
	public void testGetAmountGift() {
		
		String cardNumber = "123456";
		BigDecimal expected = control.getAmountOnGiftCard(cardNumber);
		
		assertEquals(new BigDecimal(25), expected);
		
		
	}
	
	@Test
	public void testUseGift() throws IOException {
		String cardNumber = "123456";
		
		BigDecimal expected = control.useGiftCard(cardNumber);
		
		assertEquals(new BigDecimal(0), expected);
		
	}
	
	@Test
	public void testUseMemCard() throws IOException {
		String cardNumber = "123456";
		String cardHolder = "Bob";
		try {
			assertEquals(cardNumber, control.useMembershipCard(cardNumber, cardHolder));
		} catch (Exception e) {
			assertTrue("Chip failure exception", e instanceof ChipFailureException);
		}
		
	}
	
	@Test
	public void testGetMemName() {
		String cardNumber = "123456";
		String cardHolder = "Bob";
		
		assertEquals(cardHolder, control.getMemberName(cardNumber, cardHolder));
	}
	
	@Test
	public void testPlasticBags() {
		control.plasticBagsUsed(2);
		
		assertEquals(new BigDecimal("10.1"), control.shoppingCart.getTotalPayment());
	}
	

}
