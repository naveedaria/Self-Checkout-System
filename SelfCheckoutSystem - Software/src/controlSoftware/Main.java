package controlSoftware;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class Main {
	static Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
		
	static Currency currency = Currency.getInstance("CAD");
	static int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
	static BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
	static int scaleMaximumWeight = 500; 
	static int scaleSensitivity = 1;
	
	

	public static void main(String[] args) {
		Barcode barcode = new Barcode("1234");
		BarcodedItem bItem = new BarcodedItem(barcode, 20);
		int quantity = 1;
		BigDecimal price = new BigDecimal("7.23");
		String description = "Milk";
		BarcodedProduct barcodeProd = new BarcodedProduct(barcode, description, price);
		
		ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 

		ShoppingCart cart = new ShoppingCart();
		db.put(barcode, barcodeProd);	
		
		cart.addToShoppingCart(bItem, quantity);
		controlSoft.shoppingCart = cart;
		controlSoft.printReceipt();
		
		
		

	}

}
