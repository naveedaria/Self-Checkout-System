package controlSoftware;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;


public class DispenseChangeTest {

	private SelfCheckoutStation selfCheckout;
//	private Currency currency;
	private Currency currency = Currency.getInstance("CAD");
	
	private Coin nickel1 = new Coin(new BigDecimal(0.05), currency);
	private Coin nickel2 = new Coin(new BigDecimal(0.05), currency);
	private Coin[] nickelsLoaded = new Coin[] { nickel1, nickel2 };
	
	private Coin dime1 = new Coin(new BigDecimal(0.10), currency);
	private Coin dime2 = new Coin(new BigDecimal(0.10), currency);
	private Coin[] dimesLoaded = new Coin[] { dime1, dime2 };
	
	private Coin quarter1 = new Coin(new BigDecimal(0.25), currency);
	private Coin quarter2 = new Coin(new BigDecimal(0.25), currency);
	private Coin[] quartersLoaded = new Coin[] { quarter1, quarter2 };
	
	private Coin loonie1 = new Coin(new BigDecimal(1.00), currency);
	private Coin loonie2 = new Coin(new BigDecimal(1.00), currency);
	private Coin[] looniesLoaded = new Coin[] { loonie1, loonie2 };
	
	private Coin toonie1 = new Coin(new BigDecimal(2.00), currency);
	private Coin toonie2 = new Coin(new BigDecimal(2.00), currency);
	private Coin[] tooniesLoaded = new Coin[] { toonie1, toonie2 };
	
	private Banknote five1 = new Banknote(5, currency);
	private Banknote five2 = new Banknote(5, currency);
	private Banknote[] fivesLoaded = new Banknote[] { five1, five2 };
	
	private Banknote ten1 = new Banknote(10, currency);
	private Banknote ten2 = new Banknote(10, currency);
	private Banknote[] tensLoaded = new Banknote[] { ten1, ten2 };
	
	private Banknote twenty1 = new Banknote(20, currency);
	private Banknote twenty2 = new Banknote(20, currency);
	private Banknote[] twentyLoaded = new Banknote[] { twenty1, twenty2 };
	
	private Banknote fifty1 = new Banknote(50, currency);
	private Banknote fifty2 = new Banknote(50, currency);
	private Banknote[] fiftyLoaded = new Banknote[] { fifty1, fifty2 };
	
	private Banknote hundred1 = new Banknote(100, currency);
	private Banknote hundred2 = new Banknote(100, currency);
	private Banknote[] hundredLoaded = new Banknote[] { hundred1, hundred2 };
	
//	//private Coin[] nickelsLoaded;
//	private Coin[] dimesLoaded;
//	private Coin[] quartersLoaded;
//	private Coin[] looniesLoaded;
//	private Coin[] tooniesLoaded;
//	private Banknote[] fivesLoaded;
//	private Banknote[] tensLoaded;
//	private Banknote[] twentyLoaded;
//	private Banknote[] fiftyLoaded;
//	private Banknote[] hundredLoaded;

	@Before
	public void setUp() throws Exception {
		Currency currency = Currency.getInstance("CAD");
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
		int scaleMaximumWeight = 500; 
		int scaleSensitivity = 1;  
		
		ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity);
		SelfCheckoutStation selfCheckout = new SelfCheckoutStation(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity);
//		DispenseChange dispenseChange = new DispenseChange(controlSoft, );
		this.selfCheckout = selfCheckout;
		
		Coin nickel1 = new Coin(new BigDecimal(0.05), currency);

	}
	
	
	@Test
	public void testLoadDispensersFullLoad() throws SimulationException, OverloadException {
			BigDecimal testChange = new BigDecimal(3.45);
			DispenseChange dispenseChange = new DispenseChange(selfCheckout, testChange);
		try {
//			Coin nickel1 = new Coin(new BigDecimal(0.05), currency);
			dispenseChange.loadDispensers(selfCheckout, nickelsLoaded, dimesLoaded, quartersLoaded, looniesLoaded, tooniesLoaded, fivesLoaded, tensLoaded, twentyLoaded, fiftyLoaded, hundredLoaded);
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}	
	}
	
//	@Test
//	public void testLoadDispensersNotFull() throws SimulationException, OverloadException {
//			BigDecimal testChange = new BigDecimal(3.45);
//			DispenseChange dispenseChange = new DispenseChange(selfCheckout, testChange);
//		try {
////			Coin nickel3 = new Coin(new BigDecimal(0.05), currency);
////			Coin[] nickelsLoaded2 = new Coin[] { nickel3 };
//			
//			dispenseChange.loadDispensers(selfCheckout, nickelsLoaded, dimesLoaded, quartersLoaded, looniesLoaded, tooniesLoaded, fivesLoaded, tensLoaded, twentyLoaded, fiftyLoaded, hundredLoaded);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			fail("Exception not expected"); 
//		}	
//	}
	
	
	@Test
	public void testCalcChangeDemonChangeGreaterThan() throws SimulationException, OverloadException {
		BigDecimal testChange = new BigDecimal(3.45);
		DispenseChange dispenseChange = new DispenseChange(selfCheckout, testChange);

		try {
			dispenseChange.calculateChangeDenominations();
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testCalcChangeDemonLessThan() throws SimulationException, OverloadException {
		BigDecimal testChange = new BigDecimal(6.25);
		DispenseChange dispenseChange = new DispenseChange(selfCheckout, testChange);

		try {
			dispenseChange.calculateChangeDenominations();
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testDispenseDemonForConditionFails() throws SimulationException, OverloadException {
		BigDecimal testChange = new BigDecimal(3.45);
		DispenseChange dispenseChange = new DispenseChange(selfCheckout, testChange);

		try {
			dispenseChange.dispenseDenominations();
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	
	@Test
	public void testDispenseDemon10() throws SimulationException, OverloadException {
		BigDecimal testChange = new BigDecimal(10.75);
		DispenseChange dispenseChange = new DispenseChange(selfCheckout, testChange);

		try {
			dispenseChange.loadDispensers(selfCheckout, nickelsLoaded, dimesLoaded, quartersLoaded, looniesLoaded, tooniesLoaded, fivesLoaded, tensLoaded, twentyLoaded, fiftyLoaded, hundredLoaded);
			dispenseChange.calculateChangeDenominations();
			dispenseChange.dispenseDenominations();
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testDispenseDemon5() throws SimulationException, OverloadException {
		BigDecimal testChange = new BigDecimal(5.50);
		DispenseChange dispenseChange = new DispenseChange(selfCheckout, testChange);

		try {
			dispenseChange.loadDispensers(selfCheckout, nickelsLoaded, dimesLoaded, quartersLoaded, looniesLoaded, tooniesLoaded, fivesLoaded, tensLoaded, twentyLoaded, fiftyLoaded, hundredLoaded);
			dispenseChange.calculateChangeDenominations();
			dispenseChange.dispenseDenominations();
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	@Test
	public void testDispenseDemonNickel() throws SimulationException, OverloadException {
		BigDecimal testChange = new BigDecimal(0.30);
		DispenseChange dispenseChange = new DispenseChange(selfCheckout, testChange);

		try {
			dispenseChange.loadDispensers(selfCheckout, nickelsLoaded, dimesLoaded, quartersLoaded, looniesLoaded, tooniesLoaded, fivesLoaded, tensLoaded, twentyLoaded, fiftyLoaded, hundredLoaded);
			dispenseChange.calculateChangeDenominations();
			dispenseChange.dispenseDenominations();
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception not expected"); 
		}
	}
	
	
}	


// coins and bills
//Coin nickel1 = new Coin(new BigDecimal(0.05), currency);
//Coin nickel2 = new Coin(new BigDecimal(0.05), currency);
//Coin[] nickelsLoaded = new Coin[] { nickel1, nickel2 };
//
//Coin dime1 = new Coin(new BigDecimal(0.10), currency);
//Coin dime2 = new Coin(new BigDecimal(0.10), currency);
//Coin[] dimesLoaded = new Coin[] { dime1, dime2 };
//
//Coin quarter1 = new Coin(new BigDecimal(0.25), currency);
//Coin quarter2 = new Coin(new BigDecimal(0.25), currency);
//Coin[] quartersLoaded = new Coin[] { quarter1, quarter2 };
//
//Coin loonie1 = new Coin(new BigDecimal(1.00), currency);
//Coin loonie2 = new Coin(new BigDecimal(1.00), currency);
//Coin[] looniesLoaded = new Coin[] { loonie1, loonie2 };
//
//Coin toonie1 = new Coin(new BigDecimal(2.00), currency);
//Coin toonie2 = new Coin(new BigDecimal(2.00), currency);
//Coin[] tooniesLoaded = new Coin[] { toonie1, toonie2 };
//
//Banknote five1 = new Banknote(5, currency);
//Banknote five2 = new Banknote(5, currency);
//Banknote[] fivesLoaded = new Banknote[] { five1, five2 };
//
//Banknote ten1 = new Banknote(10, currency);
//Banknote ten2 = new Banknote(10, currency);
//Banknote[] tensLoaded = new Banknote[] { ten1, ten2 };
//
//Banknote twenty1 = new Banknote(20, currency);
//Banknote twenty2 = new Banknote(20, currency);
//Banknote[] twentyLoaded = new Banknote[] { twenty1, twenty2 };
//
//Banknote fifty1 = new Banknote(50, currency);
//Banknote fifty2 = new Banknote(50, currency);
//Banknote[] fiftyLoaded = new Banknote[] { fifty1, fifty2 };
//
//Banknote hundred1 = new Banknote(100, currency);
//Banknote hundred2 = new Banknote(100, currency);
//Banknote[] hundredLoaded = new Banknote[] { hundred1, hundred2 };
