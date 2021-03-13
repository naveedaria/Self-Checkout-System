package org.lsmr.selfcheckout.controlsoftware;

import java.math.BigDecimal;
import java.util.Map;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ControlSoftware {
	
	public static void main(String[] args) {
		
		// Questions for TA: 1. Where does control software folder go
		
		// TODO: Welcome Message
		System.out.println("Scan item: ");
		
		// TODO: Hard-code all of the items (initialization)
		// Banknote demoninations, coin denominations, kind of currency, max weight, scale-sensitivity
		
	
		
		
		
		BarcodeScanner scannerObject = new BarcodeScanner();
		Barcode someBarcode = new Barcode("1234");
		BarcodedItem someItem = new BarcodedItem(someBarcode, 2.0);
		// BarcodeScannerListenerStub stub = new BarcodeScannerListenerStub();
		//scannerObject.register(stub);
		
		
		
		BigDecimal bananaPrice = new BigDecimal(2.5);
		
		BarcodedProduct prod = new BarcodedProduct(someBarcode, "Banana", bananaPrice);
		
		
		
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
		
		db.put(someBarcode, prod);
		
		
		System.out.println("Product Description is: " + db.get(someBarcode).getPrice());
		
		
		// System.out.println("Barcode is: " + someBarcode.toString());
		
		
		// TODO: Set up self-checkout station object
		
		

		// TODO: Scan item, barcode, weigh-item, checkout (pay and stuff(())
		
		// TODO: Shopping cart (keeps track of items that are scanned)
		
		
		// TODO: print receipt hardware
		
		
		
	
	}

}
