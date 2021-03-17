package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class BanknotePaymentTest {

	@Test
	public void testCalculateBanknotePaymentOneBanknote() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;  
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			String barcode = "1234";
			float weight = (float) 2.0;
			float price = (float) 5;
			String name = "apple";
			controlSoft.scanProduct(barcode, weight, price, name);
			controlSoft.setTotalBalance();
			
			
			Banknote someBanknote = new Banknote(10, currency);
			BigDecimal banknoteValue = new BigDecimal(5);
			int resultValue = controlSoft.banknoteMethod(controlSoft.selfCheckout, currency, banknoteDenominations, someBanknote);
			BigDecimal result = controlSoft.calculateBillPayment(someBanknote.getValue(), false);
			assertEquals(new BigDecimal(0), result);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	
	@Test
	public void testBillPayment() {
		Currency currency = Currency.getInstance("CAD");
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
		int scaleMaximumWeight = 500; 
		int scaleSensitivity = 1;  
		
		ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
		String barcode = "1234";
		float weight = (float) 2.0;
		float price = (float) 5;
		String name = "apple";
		controlSoft.scanProduct(barcode, weight, price, name);
		controlSoft.setTotalBalance();
	}
	
	

}
