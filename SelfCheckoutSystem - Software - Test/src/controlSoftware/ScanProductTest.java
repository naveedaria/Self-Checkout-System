package controlSoftware;


import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ScanProductTest {
	
	ControlSoftware controlSoft;
	
	@Before
	public void setUp() throws Exception {
		Currency currency = Currency.getInstance("CAD");
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
		int scaleMaximumWeight = 500; 
		int scaleSensitivity = 1;  
		controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity);
	}

	
	@Test
	public void testPriceForscanningProductWithQuantityOne(){
		
		Barcode barcode = new Barcode("1234567");
		BarcodedProduct bp1 = new BarcodedProduct(barcode, "Banana2", new BigDecimal(1.75));
		BarcodedItem barcodedItem = new BarcodedItem(barcode, 500);
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
		Map<Barcode, BarcodedItem> barcodedDB = BarcodedItemDatabase.BARCODED_ITEM_DATABASE;
	    db.put(barcode, bp1);
		barcodedDB.put(barcode, barcodedItem);

		
		controlSoft.scanProduct(barcode, 1);
		
		//Assert that 1 item was added to the shopping cart
		assertEquals(new BigDecimal(1.75), controlSoft.shoppingCart.getTotalPayment());
		
	}
	
	@Test
	public void testPriceForscanningProductWithQuantityTwo(){
		
		Barcode barcode = new Barcode("1234567");
		BarcodedProduct bp1 = new BarcodedProduct(barcode, "Banana2", new BigDecimal(1.75));
		BarcodedItem barcodedItem = new BarcodedItem(barcode, 500);
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
		Map<Barcode, BarcodedItem> barcodedDB = BarcodedItemDatabase.BARCODED_ITEM_DATABASE;
	    db.put(barcode, bp1);
		barcodedDB.put(barcode, barcodedItem);
		
		controlSoft.scanProduct(barcode, 2);
		
		//Assert that 1 item was added to the shopping cart
		assertEquals(new BigDecimal(3.50).setScale(2, RoundingMode.HALF_UP), controlSoft.shoppingCart.getTotalPayment());
		
	}
	
	@Test
	public void testPriceForscanningExpensiveItem(){
		
		Barcode barcode = new Barcode("1234567");
		BarcodedProduct bp1 = new BarcodedProduct(barcode, "Banana2", new BigDecimal(215.78));
		BarcodedItem barcodedItem = new BarcodedItem(barcode, 500);
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
		Map<Barcode, BarcodedItem> barcodedDB = BarcodedItemDatabase.BARCODED_ITEM_DATABASE;
	    db.put(barcode, bp1);
		barcodedDB.put(barcode, barcodedItem);
		
		controlSoft.scanProduct(barcode, 1);
		
		//Assert that 1 item was added to the shopping cart
		assertEquals(new BigDecimal(215.78).setScale(2, RoundingMode.HALF_UP), controlSoft.shoppingCart.getTotalPayment());
		
	}
	
	
	@Test
	public void testQuantityForscanningProductWithQuantityOne(){
		
		Barcode barcode = new Barcode("1234567");
		BarcodedProduct bp1 = new BarcodedProduct(barcode, "Banana2", new BigDecimal(1.75));
		BarcodedItem barcodedItem = new BarcodedItem(barcode, 500);
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
		Map<Barcode, BarcodedItem> barcodedDB = BarcodedItemDatabase.BARCODED_ITEM_DATABASE;
	    db.put(barcode, bp1);
		barcodedDB.put(barcode, barcodedItem);
		
		controlSoft.scanProduct(barcode, 1);
		
		//Assert that 1 item was added to the shopping cart
		assertEquals(1, controlSoft.shoppingCart.getTotalQuantity());
		
	}
	
	
	@Test
	public void testQuantityForscanningProductWithQuantityThree(){
		
		Barcode barcode = new Barcode("1234567");
		BarcodedProduct bp1 = new BarcodedProduct(barcode, "Banana2", new BigDecimal(1.75));
		BarcodedItem barcodedItem = new BarcodedItem(barcode, 500);
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
		Map<Barcode, BarcodedItem> barcodedDB = BarcodedItemDatabase.BARCODED_ITEM_DATABASE;
	    db.put(barcode, bp1);
		barcodedDB.put(barcode, barcodedItem);
		
		controlSoft.scanProduct(barcode, 3);
		
		//Assert that 1 item was added to the shopping cart
		assertEquals(3, controlSoft.shoppingCart.getTotalQuantity());
		
	}
	
	
	
	

}
