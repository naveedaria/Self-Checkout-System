package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
//import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
//import org.lsmr.selfcheckout.devices.DisabledException;

import org.junit.Test;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.SimulationException;

public class CoinMethodTest {
	private BigDecimal coinValue;
	
	@Test
	public void testCoinMethodValidInput() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;  
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			BigDecimal expectedVal = new BigDecimal(2);
			Coin someCoin = new Coin(expectedVal,currency);
			BigDecimal resultVal = controlSoft.coinMethod(controlSoft.selfCheckout, currency, coinDenominations, someCoin);
			assertTrue(expectedVal == resultVal);
		}catch (Exception e) {
			fail("Coin value was valid - exception not expected");
		}
	}
	
	@Test
	public void testCoinMethodInvalidInput() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1; 
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			BigDecimal value = new BigDecimal(3);
			Coin someCoin = new Coin(value,currency);
			controlSoft.coinMethod(controlSoft.selfCheckout, currency, coinDenominations, someCoin);
		}catch (Exception e) {
			assertTrue("Invalid input - IllegalArgumentException expected.\n", e instanceof IllegalArgumentException);
		}
	}
	
	
	@Test
	public void testCoinMethodCoinSlotDisabled() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1; 
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			BigDecimal value = new BigDecimal(2);
			Coin someCoin = new Coin(value,currency);
			controlSoft.selfCheckout.coinSlot.disable();
			controlSoft.coinMethod(controlSoft.selfCheckout, currency, coinDenominations, someCoin);
		}catch (Exception e) {
			assertTrue("Coin slot is disabled - DisabledException expected.\n", e instanceof DisabledException);
		}
	}
	
	@Test
	public void testCoinMethodCoinNull() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1; 
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			Coin someCoin = null; 
			controlSoft.coinMethod(controlSoft.selfCheckout, currency, coinDenominations, someCoin);
		}catch (Exception e) {
			assertTrue("Coin is null - SimulationException expected.\n", e instanceof SimulationException);
		}
	}

}
