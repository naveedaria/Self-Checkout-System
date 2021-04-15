package controlSoftware;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.OverloadException;

public class addToScalePromptTest {
	
	Currency currency = Currency.getInstance("CAD");
	int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
	BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
	int scaleMaximumWeight = 500; 
	int scaleSensitivity = 1;
	
	ControlSoftware controlSoft;
	
	Barcode barcode = new Barcode("1234");
	BarcodedItem barcodedItem = new BarcodedItem(barcode, 10);

	@Test
	public void testFailToAddAccept() throws OverloadException {
		
		controlSoft = new ControlSoftware(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleMaximumWeight);
		
		try {
			controlSoft.addToBaggingArea(barcode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputPass = "0";
		
		ByteArrayInputStream expectedInput = new ByteArrayInputStream(inputPass.getBytes());
		
		System.setIn(expectedInput);
		
		boolean test = controlSoft.addToScalePrompt(barcodedItem);
		
		assertTrue(test == true);
		
		
		
	}
	

}
