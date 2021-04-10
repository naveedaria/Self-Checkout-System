package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

public class receiptTest {
	
	ShoppingCart cart = new ShoppingCart();
	Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
	Map<PriceLookupCode, PLUCodedProduct> pluDb = ProductDatabases.PLU_PRODUCT_DATABASE;
	
	Barcode barcode = new Barcode("1234");
	BarcodedItem bItem = new BarcodedItem(barcode, 20);
	int quantity = 2;
	
	BigDecimal price = new BigDecimal("7.23");
	String description = "Milk";
	BarcodedProduct barcodeProd = new BarcodedProduct(barcode, description, price);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void receiptPrintTest() {
		
	}

}
