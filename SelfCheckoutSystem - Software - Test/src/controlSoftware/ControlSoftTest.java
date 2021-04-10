package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
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
		
		BigDecimal expectedChange = new BigDecimal("5");
				
		control.calculateBillPayment(5);
		
		assertEquals(expectedChange, control.change);
		
	}
	
	@Test
	public void calculateCoinPaymentTest() {
		
		BigDecimal expectedChange = new BigDecimal("8");

		BigDecimal payment = new BigDecimal("2");

		control.calculateCoinPayment(payment);
		
		assertEquals(expectedChange, control.change);
		
	}
	

}
