package controlSoftware;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.SimulationException;

import attendent.StationControl;


public class PaperInkTest
{
	StationControl station;
	Currency currency;
	
	@Before
	public void setUp() throws Exception {
		station = new StationControl();
		
	}
	
	@Test
	public void testNoInk() {
		try{
			station.addInkToStation(0);
			fail("Should throw exception for illegal argument");
		}
		catch(Exception e){
			
		}
	}
	
	
	@Test
	public void testOverloadingInk() {
		try {
			station.addInkToStation(21);
			fail("Exception should be thrown for overfilling");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test
	public void testNoPaper() {
		try{
			station.addPaperToStation(0);
			fail("Should throw exception for illegal argument");
		}
		catch(Exception e){
			
		}
	}
	
	
	@Test
	public void testOverloadingPaper() {
		try {
			station.addPaperToStation(21);
			fail("Exception should be thrown for overfilling");
		}
		catch(Exception e) {
			
		}
	}
	
	
	
}
