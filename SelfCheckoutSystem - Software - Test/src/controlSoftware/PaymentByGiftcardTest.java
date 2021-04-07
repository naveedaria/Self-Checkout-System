package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PaymentByGiftcardTest {
	PaymentByGiftcard giftcardPayment;

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
	}


	@Test
	public void testTapToRedeemTapDisabled() {
		
	}
	
	
	@Test
	public void testTapToRedeemInvalidCard() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTapToRedeemZeroBalance() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTapToRedeemAmountGreaterThanPayment() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTapToRedeemAmountLessThanPayment() {
		fail("Not yet implemented");
	}
	

	@Test
	public void testGetAmountValid() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAmountInvalid() {
		fail("Not yet implemented");
	}

}
