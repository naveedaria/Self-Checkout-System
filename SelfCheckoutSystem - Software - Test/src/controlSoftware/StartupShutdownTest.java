package controlSoftware;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class StartupShutdownTest 
{
	public baggingAreaStub baggingArea;
	public BanknoteDispenserListenerStub banknoteDispenser;
	public banknotePaymentStub banknotePayment;
	public BarcodeScannerListenerStub barcodeScanner;
	public CardReaderListenerStub cardReader;
	public CoinDispenserListenerStub coinDispenser;
	public CoinPaymentStub coinPayment;
	public ElectronicScaleListenerStub electronicScale;
	public SelfCheckoutStation selfcheckoutstation;
	StartUpShutDown startup;
	
	@Before
	public void setUp() throws Exception 
	{
		baggingArea = new baggingAreaStub();
		banknoteDispenser = new BanknoteDispenserListenerStub();
		banknotePayment = new banknotePaymentStub();
		barcodeScanner = new BarcodeScannerListenerStub();
		cardReader = new CardReaderListenerStub();
		coinDispenser = new CoinDispenserListenerStub();
		coinPayment = new CoinPaymentStub();
		electronicScale = new ElectronicScaleListenerStub();
		
	}
	
	
	@Test
	public void testStartUp() 
	{
		StartUpShutDown startup = new StartUpShutDown();
		startup.startingUp();				
		
		assertFalse(startup.baggingArea.returnDisabled());
		assertFalse(startup.banknoteDispenser.returnDisabled());
		assertFalse(startup.banknotePayment.returnDisabled());
		assertFalse(startup.barcodeScanner.returnDisabled());
		assertFalse(startup.cardReader.returnDisabled());
		assertFalse(startup.coinDispenser.returnDisabled());
		assertFalse(startup.coinPayment.returnDisabled());
		assertFalse(startup.electronicScale.returnDisabled());

		assertTrue(startup.baggingArea.returnEnabled());
		assertTrue(startup.banknoteDispenser.returnEnabled());
		assertTrue(startup.banknotePayment.returnEnabled());
		assertTrue(startup.barcodeScanner.returnEnabled());
		assertTrue(startup.cardReader.returnEnabled());
		assertTrue(startup.coinDispenser.returnEnabled());
		assertTrue(startup.coinPayment.returnEnabled());
		assertTrue(startup.electronicScale.returnEnabled());
	}
	
	@Test
	public void testShutDown()
	{
		StartUpShutDown startup = new StartUpShutDown();
		startup.shuttingDown();
		
		assertFalse(startup.baggingArea.returnEnabled());
		assertFalse(startup.banknoteDispenser.returnEnabled());
		assertFalse(startup.banknotePayment.returnEnabled());
		assertFalse(startup.barcodeScanner.returnEnabled());
		assertFalse(startup.cardReader.returnEnabled());
		assertFalse(startup.coinDispenser.returnEnabled());
		assertFalse(startup.coinPayment.returnEnabled());
		assertFalse(startup.electronicScale.returnEnabled());
		
		assertTrue(startup.baggingArea.returnDisabled());
		assertTrue(startup.banknoteDispenser.returnDisabled());
		assertTrue(startup.banknotePayment.returnDisabled());
		assertTrue(startup.barcodeScanner.returnDisabled());
		assertTrue(startup.cardReader.returnDisabled());
		assertTrue(startup.coinDispenser.returnDisabled());
		assertTrue(startup.coinPayment.returnDisabled());
		assertTrue(startup.electronicScale.returnDisabled());
	}
}
