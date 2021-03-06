package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

public class ScanProductUsingPLUCodeTest {

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
	public void testPLUforOneSingleItemEntered() {
		
        PriceLookupCode plu1 = new PriceLookupCode("01864");
        PLUCodedProduct pluProd1 = new PLUCodedProduct(plu1, "Reese's Pieces", new BigDecimal(0.25));
        
        Map<PriceLookupCode, PLUCodedProduct> db = ProductDatabases.PLU_PRODUCT_DATABASE;
        db.put(plu1, pluProd1);
        
        // Buying 1000 g of Reese's Pieces @ $0.25 / kg
        PLUCodedItem pluCodedItem1 = new PLUCodedItem(plu1, 1000);
        int customerItem2quantity = 1;
        
        controlSoft.scanProductUsingPLUCode(pluCodedItem1, customerItem2quantity);
        
        assertEquals(new BigDecimal(0.25), controlSoft.shoppingCart.getTotalPayment());
		
	}
	
	@Test
	public void testPLUforSimulationException() {
		
		try {
        PriceLookupCode plu1 = new PriceLookupCode(null);
        PLUCodedProduct pluProd1 = new PLUCodedProduct(plu1, "Reese's Pieces", new BigDecimal(0.25));
        
        Map<PriceLookupCode, PLUCodedProduct> db = ProductDatabases.PLU_PRODUCT_DATABASE;
        db.put(plu1, pluProd1);
        
        PLUCodedItem pluCodedItem1 = new PLUCodedItem(plu1, 1000);
        int customerItem2quantity = 1;
        
        controlSoft.scanProductUsingPLUCode(pluCodedItem1, customerItem2quantity);
        
        assertEquals(new BigDecimal(0.25), controlSoft.shoppingCart.getTotalPayment());
		}catch (Exception e) {
			assertTrue(e instanceof SimulationException);
		}
		
	}
	
	
	@Test
	public void testPLUforSingleItemSmallWeight() {
		
        PriceLookupCode plu1 = new PriceLookupCode("01864");
        PLUCodedProduct pluProd1 = new PLUCodedProduct(plu1, "Reese's Pieces", new BigDecimal(0.25));
        
        Map<PriceLookupCode, PLUCodedProduct> db = ProductDatabases.PLU_PRODUCT_DATABASE;
        db.put(plu1, pluProd1);
        
        // Buying 100 g of Reese's Pieces @ $0.25 / kg
        // The total cost of this should round to 3 cents (ie. $0.03)
        PLUCodedItem pluCodedItem1 = new PLUCodedItem(plu1, 100);
        int customerItem2quantity = 1;
        
        controlSoft.scanProductUsingPLUCode(pluCodedItem1, customerItem2quantity);
        
        assertEquals( new BigDecimal(0.03).setScale(2, RoundingMode.HALF_UP),controlSoft.shoppingCart.getTotalPayment());
		
	}
	
	
	
	@Test
	public void testPLUforMultipleItems() {
		
        PriceLookupCode plu1 = new PriceLookupCode("01864");
        PLUCodedProduct pluProd1 = new PLUCodedProduct(plu1, "Reese's Pieces", new BigDecimal(0.25));
        
        Map<PriceLookupCode, PLUCodedProduct> db = ProductDatabases.PLU_PRODUCT_DATABASE;
        db.put(plu1, pluProd1);
        
        // Buying 100 g of Reese's Pieces @ $0.25 / kg
        PLUCodedItem pluCodedItem1 = new PLUCodedItem(plu1, 2000);
        int customerItem2quantity = 3;
        
        controlSoft.scanProductUsingPLUCode(pluCodedItem1, customerItem2quantity);
        
        assertEquals( new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP),controlSoft.shoppingCart.getTotalPayment());
		
	}

}
