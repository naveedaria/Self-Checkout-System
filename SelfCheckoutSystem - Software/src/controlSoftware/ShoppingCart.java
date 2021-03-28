package controlSoftware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
	
	BigDecimal totalPayment;
	int numOfItems;
	BigDecimal itemPrice;
	
	private Map<Barcode, BarcodedProduct> SHOPPING_CART = new HashMap<>();
	
	ArrayList<BarcodedProduct> SHOPPING_CART_LIST = new ArrayList<>();
	ElectronicScaleListener baggingAreaScale;
	
	
	public ShoppingCart() {
		SHOPPING_CART_LIST = null;
		this.totalPayment = null;
		this.numOfItems = 0;
			
	}
	
	public void addBarcodedItemToShoppingCart(Barcode someBarcode, BarcodedItem item, BarcodedProduct prod) {
		
		if (someBarcode == null) {
			throw new IllegalArgumentException("null barcode)");
		}
		
		if (item == null) {
			throw new IllegalArgumentException("null barcoded item)");
		}
		
		if (prod == null) {
			throw new IllegalArgumentException("null barcoded product)");
		}
		
//		SHOPPING_CART.put(someBarcode, prod);
		SHOPPING_CART_LIST.add(prod);
		
		numOfItems++;
		
		if (addToScalePrompt(item)) {
//			addToBaggingArea(item); //Fix addToBaggingArea -- We can just merge Ali's files
		}

		BigDecimal price = prod.getPrice();
		totalPayment.add(price);
	}
	
	public boolean addToScalePrompt(Item item) {
		System.out.println("Please add item to the bagging area \n");
		
		boolean addedToScale = false;
		
		while(addedToScale == false) {
			
			@SuppressWarnings("resource")
			Scanner scaleUserInput = new Scanner(System.in);
			
			System.out.println("Press 1 if you will not bag, 0 if you will \n");
			
			int userInput = scaleUserInput.nextInt();
			
			if (userInput == 0) {
				
				addedToScale = true;
								
			} else continue;
			
			// Disable scanner until input (GUI this would a button)
			// If weightChanged() returns something then we close out of the loop
			
		} return true;
		
	}
	
	public BarcodedItem customerBag() {
		
		Barcode bag = new Barcode("customerBag");
		BarcodedItem customerBag = new BarcodedItem(bag, 5.00);
		
		return customerBag;
	}
	
	public boolean doneAddingItems(boolean ooo) {
		return ooo;
	}
	
	public String shoppingCartToString() {
		return SHOPPING_CART_LIST.toString();
		
	}
	
	
	public BigDecimal getTotalPrice() {
		return totalPayment;
	}

	public int getNumOfItems() {
		return numOfItems;
	}
}
