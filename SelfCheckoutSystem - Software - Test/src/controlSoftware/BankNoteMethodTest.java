package controlSoftware;


import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
//import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
//import org.lsmr.selfcheckout.devices.DisabledException;

import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.SimulationException;

public class BankNoteMethodTest {
	private BigDecimal bankValue;
	
	@Test
	public void testBankMethodValidInput() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;  
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			int expectedValue = 5;
			Banknote someNote = new Banknote(expectedValue,currency);
			int resultVal = controlSoft.banknoteMethod(someNote);
			assertTrue(expectedValue == resultVal);
		}catch (Exception e) {
			fail("Coin value was valid - exception not expected");
		}
	}
	
	@Test
	public void testBankMethodInvalidInput() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1; 
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			int value = 3;
			Banknote someCoin = new Banknote(value,currency);
			controlSoft.banknoteMethod(someCoin);
		}catch (Exception e) {
			assertTrue("Invalid input - IllegalArgumentException expected.\n", e instanceof IllegalArgumentException);
		}
	}
	
	
	
	@Test
	public void testBankMethodCoinNull() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1; 
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			Banknote someCoin = null; 
			controlSoft.banknoteMethod(someCoin);
		}catch (Exception e) {
			assertTrue("Coin is null - SimulationException expected.\n", e instanceof SimulationException);
		}
	}

}