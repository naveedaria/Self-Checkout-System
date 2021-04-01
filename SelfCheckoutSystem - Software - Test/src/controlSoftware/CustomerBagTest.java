package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.OverloadException;

public class CustomerBagTest {
	
	Currency currency = Currency.getInstance("CAD");
	int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
	BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
	int scaleMaximumWeight = 500; 
	int scaleSensitivity = 1;
	
	ControlSoftware controlSoft;

	@Test
	public void testCustomerBag() throws OverloadException {
		
		try {
			
			Barcode barcode = new Barcode("1234");
			BarcodedItem barcodedItem = new BarcodedItem(barcode, 20);
			BarcodedItem barcodedItem2 = new BarcodedItem(barcode, 20);
			BarcodedItem barcodedItem3 = new BarcodedItem(barcode, 20);
			double expectedWeight = 65;
			
			controlSoft = new ControlSoftware(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleMaximumWeight);
			
			controlSoft.addToBaggingArea(barcodedItem);
			controlSoft.addToBaggingArea(barcodedItem2);
			controlSoft.addToBaggingArea(barcodedItem3);
			
			BarcodedItem customerBag = controlSoft.customerBag();
			controlSoft.addToBaggingArea(customerBag);
			
			double testWeight = controlSoft.selfCheckout.baggingArea.getCurrentWeight();
			
			assertTrue(testWeight == expectedWeight);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Expected weight did not equal test");
		}
				
	}

}
