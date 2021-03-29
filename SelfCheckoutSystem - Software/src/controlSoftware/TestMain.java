package controlSoftware;

import java.math.BigDecimal;
import java.util.Map;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class TestMain {
	
	private static Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;

	static ShoppingCart cart;
	static BigDecimal totalAmount;

	int i = 0;

	public static void main(String[] args) {
		
		cart = new ShoppingCart();
		
		totalAmount = new BigDecimal("0.00");
		
		
		
		Barcode testBarcode = new Barcode("1234");
		BarcodedItem item = new BarcodedItem(testBarcode, 50);
		int quantity = 5;
		String description = "Milk";
		BigDecimal price = new BigDecimal("9.00");
		BarcodedProduct prod = new BarcodedProduct(testBarcode, description, price);
		
		Barcode testBarcode2 = new Barcode("3333");
		BarcodedItem item2 = new BarcodedItem(testBarcode2, 33);
		int quantity2 = 100;
		String description2 = "Bread";
		BigDecimal price2 = new BigDecimal("3.00");
		BarcodedProduct prod2 = new BarcodedProduct(testBarcode2, description2, price2);
		
		Barcode testBarcode3 = new Barcode("2222");
		BarcodedItem item3 = new BarcodedItem(testBarcode3, 12);
		int quantity3 = 3;
		String description3 = "Sugar";
		BigDecimal price3 = new BigDecimal("9.00");
		BarcodedProduct prod3 = new BarcodedProduct(testBarcode3, description3, price3);
		
		db.put(testBarcode, prod);

		db.put(testBarcode2, prod2);

		db.put(testBarcode3, prod3);
		

		cart.addBarcodedItemToShoppingCart(item, quantity);
		
		cart.updateTotalPayment(item, quantity);
		
		cart.addQuantity(quantity);
		

		cart.addBarcodedItemToShoppingCart(item2, quantity2);
		
		cart.updateTotalPayment(item2, quantity2);
		
		cart.addQuantity(quantity2);


		cart.addBarcodedItemToShoppingCart(item, quantity3);
		
		cart.updateTotalPayment(item3, quantity3);
		
		cart.addQuantity(quantity3);

				
		System.out.println(cart.SHOPPING_CART_ARRAY[0][0] + "\n");

		System.out.println(cart.SHOPPING_CART_ARRAY[0][1] + "\n");
		
		System.out.println(cart.SHOPPING_CART_ARRAY[1][0] + "\n");

		System.out.println(cart.SHOPPING_CART_ARRAY[1][1] + "\n");
		
		System.out.println(cart.SHOPPING_CART_ARRAY[2][0] + "\n");

		System.out.println(cart.SHOPPING_CART_ARRAY[2][1] + "\n");
		
								
			
		
		System.out.println(cart.getTotalPayment().toString() + "\n");
		
		System.out.println(cart.getTotalQuantity() + "\n");



	}
	

}
