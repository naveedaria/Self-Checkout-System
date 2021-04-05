package controlSoftware;

import java.math.BigDecimal;
import java.util.Currency;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class StartUpShutDown {
	
	public SelfCheckoutStation selfcheckoutstation;
	
	public baggingAreaStub baggingArea;
	public BanknoteDispenserListenerStub banknoteDispenser;
	public banknotePaymentStub banknotePayment;
	public BarcodeScannerListenerStub barcodeScanner;
	public CardReaderListenerStub cardReader;
	public CoinDispenserListenerStub coinDispenser;
	public CoinPaymentStub coinPayment;
	public ElectronicScaleListenerStub electronicScale;
	
	public int[] notes = { 5, 10, 20, 50, 100 };
	public BigDecimal[] coinTypes = { new BigDecimal(0.05), new BigDecimal(0.1), new BigDecimal(0.25),
			new BigDecimal(1), new BigDecimal(2) };
	
	
	public StartUpShutDown() {
		
		selfcheckoutstation = new SelfCheckoutStation(Currency.getInstance("CAD"), notes, coinTypes, 50, 1);
		
		// Initializing all of the Listeners
		baggingArea = new baggingAreaStub();
		banknoteDispenser = new BanknoteDispenserListenerStub();
		banknotePayment = new banknotePaymentStub();
		barcodeScanner = new BarcodeScannerListenerStub();
		cardReader = new CardReaderListenerStub();
		coinDispenser = new CoinDispenserListenerStub();
		coinPayment = new CoinPaymentStub();
		electronicScale = new ElectronicScaleListenerStub();
		
	}
	
	public void startingUp(){
		
		// Enabling all of the Hardware
		// Starting up the Self Checkout Station
		
		selfcheckoutstation.baggingArea.enable();
		selfcheckoutstation.banknoteInput.enable();
		selfcheckoutstation.banknoteOutput.enable();
		selfcheckoutstation.banknoteStorage.enable();
		selfcheckoutstation.banknoteValidator.enable();
		selfcheckoutstation.cardReader.enable();
		selfcheckoutstation.coinSlot.enable();
		selfcheckoutstation.coinStorage.enable();
		selfcheckoutstation.coinTray.enable();
		selfcheckoutstation.coinValidator.enable();
		selfcheckoutstation.handheldScanner.enable();
		selfcheckoutstation.mainScanner.enable();
		selfcheckoutstation.printer.enable();
		selfcheckoutstation.scale.enable();
		selfcheckoutstation.screen.enable();
		
	}
	
	public void ShuttingDown() {
		
		// Disabling all of the Hardware
		// Shutting down the Self Checkout Station
		
		selfcheckoutstation.baggingArea.disable();
		selfcheckoutstation.banknoteInput.disable();
		selfcheckoutstation.banknoteOutput.disable();
		selfcheckoutstation.banknoteStorage.disable();
		selfcheckoutstation.banknoteValidator.disable();
		selfcheckoutstation.cardReader.disable();
		selfcheckoutstation.coinSlot.disable();
		selfcheckoutstation.coinStorage.disable();
		selfcheckoutstation.coinTray.disable();
		selfcheckoutstation.coinValidator.disable();
		selfcheckoutstation.handheldScanner.disable();
		selfcheckoutstation.mainScanner.disable();
		selfcheckoutstation.printer.disable();
		selfcheckoutstation.scale.disable();
		selfcheckoutstation.screen.disable();
		
	}
}
