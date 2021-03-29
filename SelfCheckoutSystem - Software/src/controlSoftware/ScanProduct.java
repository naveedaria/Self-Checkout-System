package controlSoftware;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ScanProduct extends ShoppingCart{
	
	public SelfCheckoutStation station;
	
	BarcodeScannerListenerStub scannerStub;

	public void scanProduct(String barcode, float weight, float price, String name) {
		BarcodeScanner scannerObject = new BarcodeScanner();
		Barcode someBarcode = new Barcode(barcode);
		BarcodedItem someItem = new BarcodedItem(someBarcode, weight);
		
		BigDecimal productPrice = new BigDecimal(price);
		BarcodedProduct prod = new BarcodedProduct(someBarcode, name, productPrice);
		
		BarcodeScannerListenerStub stub = new BarcodeScannerListenerStub();
		scannerObject.register(stub);	
		scannerObject.enable();
		scannerObject.scan(someItem);
		
		addBarcodedItemToShoppingCart(someBarcode, someItem, prod);
		
	}
	
}

