package controlSoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;
import org.lsmr.selfcheckout.external.ProductDatabases;

public class BarcodeScannerListenerStub implements BarcodeScannerListener{

	private boolean isEnabled;
	private boolean isDisabled;
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		isEnabled = true;
		isDisabled = false;
//		System.out.print("test test \n");
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		isEnabled = false;
		isDisabled = true;
	}

	@Override
	public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
		// TODO Auto-generated method stub
		System.out.println("Test: BarcodeScannerListener succcessfully heard a scan event");
		
		// Aris Comment: This is where we would implement the event handler for a scanning event
		
		// Step 1: Read request to DB to first check isPerUnit. If so, 
		System.out.println("Attempting to read from the DB. The product description is: " + ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode).getPrice());
	}
	
	//private boolean getProductType() {
		
	//}

	public boolean returnEnabled() {
		return isEnabled;
	}

	public boolean returnDisabled() {
		return isDisabled;
	}
}
