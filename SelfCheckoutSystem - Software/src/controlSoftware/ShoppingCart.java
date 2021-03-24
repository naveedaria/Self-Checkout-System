package controlSoftware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ShoppingCart {
	
	/**
	 * Tracks total price, all the items scanned, PLUCode
	 * 
	 * Find a suitable data structure
	 * 
	 * Workout logic of scanning -> ShoppingCart -> Scale
	 * 
	 * 
	 */
	
	float totalPayment;
	
	private Map<Barcode, BarcodedItem> SHOPPING_CART = new HashMap<>();
	public SelfCheckoutStation station;
	ElectronicScaleListener baggingAreaScale;
	
	
	public ShoppingCart(SelfCheckoutStation station) {
		
		SHOPPING_CART = null;
		this.totalPayment = 0;
		this.station = station;
			
	}
	
	public void addBarcodedItemToShoppingCart(Barcode barcode, BarcodedItem item, BarcodedProduct prod) {
		
		SHOPPING_CART.put(barcode, item);
		
		// Error handling: Item or Product can be null.
		

		// Add to Receipt
		// Do you want to add your item to the bagging Area? Yes / No
		//	selfCheckout.baggingArea.add(someItem);
		//  if not do nothing? -- don't add to scale
		BigDecimal price = prod.getPrice();
//		totalPayment += price;
	}
	
	public void addToScalePrompt(Item item) {
		System.out.println("Please add item to the bagging area");
		
		boolean addedToScale = false;
		
		
	}
	
	public void addPLUItemToShoppingCart(Barcode barcode, float price, String productName) {
		
		// HASHMAP or MAP idk add Barcode as Key
		// Receipt
		
		
		totalPayment += price;
		
	}
	
	
	public float getTotalPrice() {
		return totalPayment;
	}
	

	public void ifBag() {
		// Changes sensitivity or add weight
		// Prompt at beginning or end
	}
}
