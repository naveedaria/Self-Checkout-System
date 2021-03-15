package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
//import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
//import org.lsmr.selfcheckout.devices.DisabledException;

import org.junit.Test;

public class CoinPaymentTest {
	private BigDecimal coinValue;
	
	@Test
	public void testCoinMethodValidInput() {
		try {
			ControlSoftware controlSoft = new ControlSoftware(); 
			controlSoft.main(null); 
			coinValue= controlSoft.coinValue;
		}catch (Exception e) {
			BigDecimal testVal1 = new BigDecimal(0.05);
			BigDecimal testVal2 = new BigDecimal(0.10);
			BigDecimal testVal3 = new BigDecimal(0.25);
			BigDecimal testVal4 = new BigDecimal(1.00);
			BigDecimal testVal5 = new BigDecimal(2.00);
			if ((this.coinValue.compareTo(testVal1)==0) || (this.coinValue.compareTo(testVal2)==0) || (this.coinValue.compareTo(testVal3)==0) || (this.coinValue.compareTo(testVal4)==0) || (this.coinValue.compareTo(testVal5)==0)) {
				fail("Coin value was valid - exception not expected");
			}
		}
	}
	
	@Test
	public void testCoinMethodInvalidInput() {
		try {
			ControlSoftware controlSoft = new ControlSoftware();
			controlSoft.main(null);
			coinValue= controlSoft.coinValue;
		}catch (Exception e) {
			BigDecimal testVal1 = new BigDecimal(0.05);
			BigDecimal testVal2 = new BigDecimal(0.10);
			BigDecimal testVal3 = new BigDecimal(0.25);
			BigDecimal testVal4 = new BigDecimal(1.00);
			BigDecimal testVal5 = new BigDecimal(2.00);
			if ((this.coinValue.compareTo(testVal1)!=0) || (this.coinValue.compareTo(testVal2)!=0) || (this.coinValue.compareTo(testVal3)!=0) || (this.coinValue.compareTo(testVal4)!=0) || (this.coinValue.compareTo(testVal5)!=0)) {
				assertTrue("Invalid input - IllegalArgumentException expected.\n", e instanceof IllegalArgumentException);
			}
			
		}
	}
	
	/*
	@Test
	public void testCoinMethodCoinSlotDisabled() {
		try {
			ControlSoftware controlSoft = new ControlSoftware();
			controlSoft.station.coinSlot.disable();
			coinValue = controlSoft.coinValue;
		}catch (Exception e) {
			assertTrue("Coin slot is disabled - DisabledException expected.\n", e instanceof DisabledException);

		}
	}*/

}
