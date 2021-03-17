package controlSoftware;


import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.BidirectionalChannel;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

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
			assertFalse(new BigDecimal(0) == result);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testCalculateBanknotePaymentBillProcessedFalse() {
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
			
			controlSoft.setChange();
			Banknote someBanknote = new Banknote(10, currency);
			BigDecimal banknoteValue = new BigDecimal(5);
			int resultValue = controlSoft.banknoteMethod(controlSoft.selfCheckout, currency, banknoteDenominations, someBanknote);
			BigDecimal result = controlSoft.calculateBillPayment(someBanknote.getValue(), true);
			assertFalse(new BigDecimal(0) == result);
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testCheckBanknoteVal() {
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
			
			controlSoft.setChange();
			Banknote someBanknote = new Banknote(200, currency);
			BigDecimal banknoteValue = new BigDecimal(5);
			int resultValue = controlSoft.banknoteMethod(controlSoft.selfCheckout, currency, banknoteDenominations, someBanknote);
			BigDecimal result = controlSoft.calculateBillPayment(someBanknote.getValue(), true);
			assertFalse(controlSoft.checkBanknoteVal(someBanknote, banknoteDenominations));
		}catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	
	@Test
	public void testBanknoteMethodDisabled() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;  
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			Banknote someBanknote = new Banknote(10, currency);
			BigDecimal banknoteValue = new BigDecimal(5);
			controlSoft.selfCheckout.banknoteInput.disable();
			controlSoft.banknoteMethod(controlSoft.selfCheckout, currency, banknoteDenominations, someBanknote);
			
		}catch(Exception e) {
			assertTrue("BanknoteInput is disabled.", e instanceof DisabledException);
		}
	}
	
	@Test
	public void testBanknoteMethodNullBanknoteOverload() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;  
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			Banknote someBanknote = new Banknote(10, currency);
			
			controlSoft.selfCheckout.banknoteInput.emit(someBanknote);
			controlSoft.banknoteMethod(controlSoft.selfCheckout, currency, banknoteDenominations, someBanknote);
			
		}catch(Exception e) {
			assertTrue("Banknote emitted and dangling from banknote slot", e instanceof OverloadException);
		}
	}
	
	@Test
	public void testBanknoteMethodNullBanknoteSimulation() {
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; 
			int scaleSensitivity = 1;  
			
			ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
			Banknote someBanknote = null;
			controlSoft.banknoteMethod(controlSoft.selfCheckout, currency, banknoteDenominations, someBanknote);
		}catch(Exception e) {
			assertTrue("Banknote is null", e instanceof SimulationException);
		}
	}

	

}
