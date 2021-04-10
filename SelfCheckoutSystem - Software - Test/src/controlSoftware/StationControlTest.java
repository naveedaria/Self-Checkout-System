package controlSoftware;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import attendant.AttendantLogIn_Out;
import attendant.AttendantProfile;
import attendant.AttendantProfileDatabase;
import attendant.StationControl;

public class StationControlTest {
	
	Currency currency = Currency.getInstance("CAD");
	int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
	BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
	int scaleMaxWeight = 500; 
	int scaleSensitivity = 1;  
	public SelfCheckoutStation selfCheckout;
	
	StationControl control = new StationControl();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void turnOnStationTest() {
		
		control.turnOnStation(currency, banknoteDenominations, coinDenominations, scaleMaxWeight, scaleSensitivity);
		
		assertTrue(scaleMaxWeight == control.selfCheckout.scale.getWeightLimit());
		assertTrue(scaleSensitivity == control.selfCheckout.scale.getSensitivity());
	
	}
	
	@Test
	public void turnOffStationTest() {
		control.turnOffStation(selfCheckout);
		
		assertEquals(null, control.selfCheckout);
		
	}
	
	
	@Test
	public void attendantRemoveTest() {
		int i = 0;
		ControlSoftware cs = new ControlSoftware(currency, banknoteDenominations, coinDenominations, scaleMaxWeight, scaleMaxWeight);
		Barcode barcode = new Barcode("1234");
		BarcodedItem bItem = new BarcodedItem(barcode, 20);
		int quantity = 1;
		
		BigDecimal price = new BigDecimal("7.23");
		String description = "Milk";
		BarcodedProduct barcodeProd = new BarcodedProduct(barcode, description, price);
		
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
		db.put(barcode, barcodeProd);
		
		cs.shoppingCart.addToShoppingCart(bItem, quantity);
		control.attendantRemoveItem(cs, bItem, quantity);
		
		assertEquals(null, cs.shoppingCart.SHOPPING_CART_ARRAY[i][0]);
		
		assertEquals(null, cs.shoppingCart.SHOPPING_CART_ARRAY[i][1]);
		
	}
	
	
	
	@Test
	public void attendantApproveDesc() throws OverloadException {
		String username = "Bob";
		String password = "hunter2";
		AttendantProfile attendant = new AttendantProfile(username, password);
		AttendantProfileDatabase attendantDB = new AttendantProfileDatabase();
		
		attendantDB.addProfile(attendant);
		try {
			assertEquals(true, control.attendantApproveWeight());
		} catch (Exception e){
			assertTrue(e instanceof Exception);
		}
		
			
		
		
		
		
	}
	
	

}
