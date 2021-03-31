package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class WeighItemTest {

	
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
	public void testWeighItemWhoseWeightis50grams() {
		Barcode barcode = new Barcode("1234567");
		BarcodedProduct bp1 = new BarcodedProduct(barcode, "Banana2", new BigDecimal(1.75));
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
	    db.put(barcode, bp1);
		
		BarcodedItem barcodedItem = new BarcodedItem(barcode, 50);
		
		assertEquals(50, controlSoft.weighItem(barcodedItem), .000000001d);
	}
	
	@Test
	public void testWeighItemWhoseWeightis500grams() {
		Barcode barcode = new Barcode("1234567");
		BarcodedProduct bp1 = new BarcodedProduct(barcode, "Banana2", new BigDecimal(1.75));
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
	    db.put(barcode, bp1);
		
		BarcodedItem barcodedItem = new BarcodedItem(barcode, 500);
		
		assertEquals(500, controlSoft.weighItem(barcodedItem), .000000001d);
	}
	
	
	@Test
	public void testWeighItemWhoseWeightis501grams() {
		Barcode barcode = new Barcode("1234567");
		BarcodedProduct bp1 = new BarcodedProduct(barcode, "Banana2", new BigDecimal(1.75));
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
	    db.put(barcode, bp1);
		
		BarcodedItem barcodedItem = new BarcodedItem(barcode, 501);
	
		assertEquals(0, controlSoft.weighItem(barcodedItem), .000000001d);
	}
	


}
