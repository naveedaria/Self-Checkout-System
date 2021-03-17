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
import org.lsmr.selfcheckout.devices.SimulationException;

public class BaggingAreaTest {

	@Test
	public void testAddItemToBaggingArea() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;
			
			ControlSoftware controlStation = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 

			Barcode itemBarcode = new Barcode("1234");
			Barcode itemBarcode2 = new Barcode("4321");
			Barcode itemBarcode3 = new Barcode("1111");
			BarcodedItem item250weight = new BarcodedItem(itemBarcode, 250);
			BarcodedItem item50weight = new BarcodedItem(itemBarcode3, 50);
			
			controlStation.addItemToBaggingArea(controlStation.selfCheckout, item250weight);
			assertTrue(controlStation.selfCheckout.baggingArea.getCurrentWeight() == 250);

		}catch(SimulationException | OverloadException e) {
			e.printStackTrace();
			fail("weight not tracked appropriately");
			
		}
	}

	@Test
	public void testRemoveItemFromBaggingArea() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;
			
			ControlSoftware controlStation = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 

			Barcode itemBarcode = new Barcode("1234");
			Barcode itemBarcode2 = new Barcode("4321");
			Barcode itemBarcode3 = new Barcode("1111");
			BarcodedItem item250weight = new BarcodedItem(itemBarcode, 250);
			BarcodedItem item500weight = new BarcodedItem(itemBarcode2, 500);
			BarcodedItem item50weight = new BarcodedItem(itemBarcode3, 50);
			
			controlStation.addItemToBaggingArea(controlStation.selfCheckout, item500weight);
			controlStation.removeItemFromBaggingArea(controlStation.selfCheckout, item500weight);
			assertTrue(controlStation.selfCheckout.baggingArea.getCurrentWeight() == 0);

		}catch(Exception e) {
			e.printStackTrace();
			fail("weight not tracked appropriately");
		}
	}
	
	@Test(expected = SimulationException.class)
	public void testAddNullItem() {
		Currency currency = Currency.getInstance("CAD");
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
		int scaleMaximumWeight = 500; 
		int scaleSensitivity = 1;
		
		ControlSoftware controlStation = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity);
		
		Barcode nullB = new Barcode("");
		BarcodedItem nullBarcode = new BarcodedItem(nullB, 20);
		
		controlStation.addItemToBaggingArea(controlStation.selfCheckout, nullBarcode);
		
		
	}
	
	@Test(expected = SimulationException.class)
	public void testRemoveNullItem() {
		Currency currency = Currency.getInstance("CAD");
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
		int scaleMaximumWeight = 500; 
		int scaleSensitivity = 1;
		
		ControlSoftware controlStation = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity);
		
		Barcode nullB = new Barcode("");
		BarcodedItem nullBarcode = new BarcodedItem(nullB, 20);
		
		controlStation.removeItemFromBaggingArea(controlStation.selfCheckout, nullBarcode);
		
		
	}


}
