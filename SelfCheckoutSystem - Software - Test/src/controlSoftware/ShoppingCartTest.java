package controlSoftware;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ShoppingCartTest {
	
	ShoppingCart cart = new ShoppingCart();
	Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
	
	Barcode barcode = new Barcode("1234");
	BarcodedItem bItem = new BarcodedItem(barcode, 20);
	int quantity = 2;
	
	BigDecimal price = new BigDecimal("7.23");
	String description = "Milk";
	BarcodedProduct barcodeProd = new BarcodedProduct(barcode, description, price);
	
	@SuppressWarnings("unused")
	@Before
	public void setUp() {
		ShoppingCart cart = new ShoppingCart();
	}
	@Test
	public void testSuccessfulAddition() {
		int i = 0;
		
		db.put(barcode, barcodeProd);
		
		cart.addToShoppingCart(bItem, quantity);
		
		BarcodedProduct prod = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(bItem.getBarcode());
		
		db.put(barcode, prod);
		
		assertEquals(barcode, prod.getBarcode());

		assertEquals(prod.getDescription(), cart.SHOPPING_CART_ARRAY[i][0]);

		assertEquals(Integer.toString(quantity), cart.SHOPPING_CART_ARRAY[i][1]);

	}
	
	@Test
	public void testSuccessfulRemoval() {
		
		int i = 0;
		
		db.put(barcode, barcodeProd);
		
		cart.addToShoppingCart(bItem, quantity);
		
		BarcodedProduct prod = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(bItem.getBarcode());
		
		db.put(barcode, prod);
		
		cart.removeFromShoppingCart(bItem, quantity);
				
		assertEquals(null, cart.SHOPPING_CART_ARRAY[i][0]);
		
		assertEquals(null, cart.SHOPPING_CART_ARRAY[i][1]);
		
	}
	
	@Test(expected = SimulationException.class)
	public void testArrayRange() {
		for(int i = 0; i < 100; i++) {
			cart.addToShoppingCart(bItem, i);
			continue;
		}
	}
	
	@Test(expected = SimulationException.class)
	public void testRemoveNullItem() {
		cart.removeFromShoppingCart(bItem, quantity);
		
	}
	
	@Test
	public void testQuantity() {
		int add = 2;
		cart.addQuantity(add);
		
		assertEquals(add, cart.getTotalQuantity());
		
		cart.decreaseQuantity(add);
		
		assertEquals(0, cart.getTotalQuantity());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testQuantityArgs() {
		int add = -1;
		cart.addQuantity(add);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testrQuantityArgs() {
		int add = -1;
		cart.decreaseQuantity(add);
	}
	
	@Test
	public void testUpdatePayment() {
		cart.updateTotalPayment(bItem, quantity);
		
		BigDecimal price = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(bItem.getBarcode())
				.getPrice().multiply(new BigDecimal(quantity));
		price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		assertEquals(price, cart.getTotalPayment());
		
		cart.decreaseTotalPayment(bItem, quantity);
		
		assertEquals(new BigDecimal("0.00"), cart.getTotalPayment());
		
	}

	

}
