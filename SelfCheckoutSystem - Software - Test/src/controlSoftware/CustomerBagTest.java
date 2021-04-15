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
import org.lsmr.selfcheckout.devices.OverloadException;

public class CustomerBagTest {
	
	Currency currency = Currency.getInstance("CAD");
	int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
	BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
	int scaleMaximumWeight = 500; 
	int scaleSensitivity = 1;
	
	ControlSoftware controlSoft;

	@Test
	public void testCustomerBag() throws Exception {
					
			Barcode barcode = new Barcode("1234");
			Barcode barcode2 = new Barcode("12334");
			Barcode barcode3 = new Barcode("12344");

			BarcodedItem barcodedItem = new BarcodedItem(barcode, 20);
			BarcodedItem barcodedItem2 = new BarcodedItem(barcode2, 20);
			BarcodedItem barcodedItem3 = new BarcodedItem(barcode3, 20);
			
			controlSoft = new ControlSoftware(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleMaximumWeight);
			Map<Barcode, BarcodedItem> barcodedDB = BarcodedItemDatabase.BARCODED_ITEM_DATABASE;
			barcodedDB.put(barcode, barcodedItem);
			barcodedDB.put(barcode2, barcodedItem2);
			barcodedDB.put(barcode3, barcodedItem3);
			
						
			BarcodedItem customerBag = controlSoft.customerBag();
			double expectedWeight = customerBag.getWeight();
			

			double testWeight = controlSoft.selfCheckout.baggingArea.getCurrentWeight();
			
			assertTrue(0.0 == testWeight);

	}

}
