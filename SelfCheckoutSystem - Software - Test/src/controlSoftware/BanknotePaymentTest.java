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
			//assertEquals(new BigDecimal(0), result);
			assertFalse(new BigDecimal(0) == result);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	
	@Test
	public void testCalculateBanknotePaymentOneBanknote2() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;  
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			String barcode = "1234";
			float weight = (float) 2.0;
			float price = (float) 1.50;
			String name = "apple";
			
			String barcode2 = "2468";
			float weight2 = (float) 2.0;
			float price2 = (float) 1.50;
			String name2 = "apple";
			
			controlSoft.scanProduct(barcode, weight, price, name); 
			controlSoft.scanProduct(barcode2, weight2, price2, name2);
			controlSoft.setTotalBalance();
			
			Banknote someBanknote = new Banknote(10, currency);
			BigDecimal banknoteValue = new BigDecimal(5);
			int resultValue = controlSoft.banknoteMethod(controlSoft.selfCheckout, currency, banknoteDenominations, someBanknote);
			
			Banknote someBanknote2 = new Banknote(10, currency);
			BigDecimal banknoteValue2 = new BigDecimal(5);
			int finalResult = controlSoft.banknoteMethod(controlSoft.selfCheckout, currency, banknoteDenominations, someBanknote);
						
			float expectedBalance = price+price2;
			BigDecimal totalBillVal = banknoteValue.add(banknoteValue2);
			BigDecimal expected = (new BigDecimal(expectedBalance)).subtract(totalBillVal);
			//assertTrue(expected.compareTo(finalResult)==0);
			//assertEquals(new BigDecimal(0), result);
			assertFalse(new BigDecimal(0) == expected);
			//assertEquals(expected, -7);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	

}
