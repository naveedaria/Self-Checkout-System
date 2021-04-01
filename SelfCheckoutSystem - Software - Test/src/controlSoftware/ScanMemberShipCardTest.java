package controlSoftware;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.ChipFailureException;
import org.lsmr.selfcheckout.devices.SimulationException;


public class ScanMemberShipCardTest {
	private ScanMembershipCard membershipcard;

	@Before
	public void setUp() throws Exception {
		Currency currency = Currency.getInstance("CAD");
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
		int scaleMaximumWeight = 500; 
		int scaleSensitivity = 1;  
		ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 
		ScanMembershipCard membershipcard = new ScanMembershipCard(controlSoft.selfCheckout);
		this.membershipcard = membershipcard;
	}

	@Test
	public void testValidMembershipCard() throws SimulationException, IOException {
		String cardNumber1 = "123456";
		String cardHolder2 = "Bob";
		try {
			this.membershipcard.tapMembershipCard(cardNumber1, cardHolder2);
	
		}catch(ChipFailureException e) {
			assertTrue("Card is Invalid.\n", e instanceof ChipFailureException);
		}
		
	}

	@Test
	public void testInvalidMembershipCardWithNoUser() throws SimulationException, Exception{
		try {
			this.membershipcard.tapMembershipCard(null, null);
		}catch(SimulationException e) {
			assertTrue("Card is Invalid.\n", e instanceof SimulationException);
		}
	
	}
	
	@Test
	public void testInvalidMembershipCardWithInvalidUser() throws IOException {
		String cardn1 = "";
		String cardH1 = "";
		try {
			this.membershipcard.tapMembershipCard(cardn1, cardH1);
		} catch(IOException e) {
			assertTrue("Card is Invalid.\n", e instanceof IOException);
		}
	}
}
