package controlSoftware;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PaymentByGiftcardTest {
	private PaymentByGiftcard giftcardPayment;
	private BigDecimal totalBalance;

	@Before
	public void setUp() throws Exception {
		Currency currency = Currency.getInstance("CAD");
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
		int scaleMaximumWeight = 500; 
		int scaleSensitivity = 1;  
		
		ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 

		PaymentByGiftcard giftcardPayment = new PaymentByGiftcard(controlSoft.selfCheckout);
		this.giftcardPayment = giftcardPayment;
		
		//Suppose the shopping balance is $100.00
		this.totalBalance = new BigDecimal(100); 
	}


	@Test
	public void testTapToRedeemTapDisabled() throws IOException {
		String testerCardNum = "123456";
		boolean tapEnabled = false; 
		
		try {
			BigDecimal updatedBalance = this.giftcardPayment.tapToRedeem(testerCardNum, this.totalBalance, tapEnabled);
			BigDecimal expected = new BigDecimal(-1);
			assertTrue(expected.compareTo(updatedBalance)==0);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
		
	}
	
	
	@Test
	public void testTapToRedeemInvalidCard() {
		String testerCardNum = "4689";
		boolean tapEnabled = true; 
		
		try {
			BigDecimal updatedBalance = this.giftcardPayment.tapToRedeem(testerCardNum, this.totalBalance, tapEnabled);
			BigDecimal expected = new BigDecimal(-1);
			assertTrue(expected.compareTo(updatedBalance)==0);
		}catch(Exception e) {
			e.printStackTrace();
			assertTrue("Exception not expected", e instanceof Exception); 
		}
	}
	
	@Test
	public void testTapToRedeemZeroBalance() {
		String testerCardNum = "123567";
		boolean tapEnabled = true; 
		
		try {
			BigDecimal updatedBalance = this.giftcardPayment.tapToRedeem(testerCardNum, this.totalBalance, tapEnabled);
			BigDecimal expected = new BigDecimal(100);
			assertTrue(expected.compareTo(updatedBalance)==0);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testTapToRedeemAmountGreaterThanPayment() {
		String testerCardNum = "123789";
		boolean tapEnabled = true; 
		
		try {
			BigDecimal updatedBalance = this.giftcardPayment.tapToRedeem(testerCardNum, this.totalBalance, tapEnabled);
			BigDecimal expected = new BigDecimal(0);
			assertTrue(expected.compareTo(updatedBalance)==0);
			
			BigDecimal giftcardRemainingBalance = this.giftcardPayment.getAmount(testerCardNum);
			BigDecimal giftcardExpected = new BigDecimal(50);
			assertTrue(giftcardExpected.compareTo(giftcardRemainingBalance)==0);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testTapToRedeemAmountLessThanPayment() {
		String testerCardNum = "123456";
		boolean tapEnabled = true; 
		
		try {
			BigDecimal updatedBalance = this.giftcardPayment.tapToRedeem(testerCardNum, this.totalBalance, tapEnabled);
			BigDecimal expected = new BigDecimal(75);
			assertTrue(expected.compareTo(updatedBalance)==0);
			
			BigDecimal giftcardRemainingBalance = this.giftcardPayment.getAmount(testerCardNum);
			BigDecimal giftcardExpected = new BigDecimal(0);
			assertTrue(giftcardExpected.compareTo(giftcardRemainingBalance)==0);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	

	@Test
	public void testGetAmountValid() {
		String testerCardNum = "123456";
		
		try {
			BigDecimal amount = this.giftcardPayment.getAmount(testerCardNum);
			BigDecimal expected = new BigDecimal(25);
			assertTrue(expected.compareTo(amount)==0);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testGetAmountInvalid() {
		String testerCardNum = "4689";
		
		try {
			BigDecimal amount = this.giftcardPayment.getAmount(testerCardNum);
			BigDecimal expected = new BigDecimal(-1);
			assertTrue(expected.compareTo(amount)==0);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}

}
