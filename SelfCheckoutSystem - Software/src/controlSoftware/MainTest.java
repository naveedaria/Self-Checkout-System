package controlSoftware;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class MainTest {
	
	ShoppingCart cart = new ShoppingCart();
	static Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
	
	static Barcode barcode = new Barcode("1234");
	static BarcodedItem bItem = new BarcodedItem(barcode, 20);
	static int quantity = 2;
	
	static BigDecimal price = new BigDecimal("7.23");
	static String description = "Milk";
	static BarcodedProduct barcodeProd = new BarcodedProduct(barcode, description, price);
	
	static Currency currency = Currency.getInstance("CAD");
	static int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
	static BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
	static int scaleMaximumWeight = 500; 
	static int scaleSensitivity = 1;
	
	

	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();
		
		ControlSoftware controlSoft = new ControlSoftware(currency,banknoteDenominations,coinDenominations,scaleMaximumWeight,scaleSensitivity); 

		db.put(barcode, barcodeProd);	
		
		cart.addToShoppingCart(bItem, quantity);
		
		controlSoft.printReceipt();
		
		
		

	}

}
