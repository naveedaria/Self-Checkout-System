package controlSoftware;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;
import org.lsmr.selfcheckout.external.ProductDatabases;

public class BarcodeScannerListenerStub implements BarcodeScannerListener{

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		// TODO Auto-generated method stub
//		System.out.print("test test \n");
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
		
		// Handler for price lookup via barcode scanner if listener is being used. At the moment, the shopping cart handles the logic
		//System.out.println("Price lookup: " + ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode).getDescription() + " : $" + ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode).getPrice());
	}
	
	//private boolean getProductType() {
		
	//}

}
