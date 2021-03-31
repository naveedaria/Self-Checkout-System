package controlSoftware;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

// Payment calculation for weighted items
// Test case for weighed Items


/**
 * Creates a Shopping Cart with most of the functionality
 * Tracks total payment the Customer owes
 * Tracks the items in the shopping cart
 * 
 *
 */
public class ShoppingCart {
	

	BigDecimal totalPayment;
	int totalNumOfItems;
	String[][] SHOPPING_CART_ARRAY;
	int i;
	
	ElectronicScaleListener baggingAreaScale;
	
	/**
	 * Initializes Shopping Cart
	 * 
	 */
	public ShoppingCart() {
		SHOPPING_CART_ARRAY = new String[30][2];
		this.totalPayment = new BigDecimal("0.00");
		this.totalNumOfItems = 0;
		i = 0;
			
	}
	
	/**
	 * Adds item to Shopping cart array
	 * @param item
	 * 		BarcodedItem input, can access database from barcode
	 * @param quantity
	 * 		The number of times item was scanned
	 */
	public void addToShoppingCart(BarcodedItem item, int quantity) {
		
		BarcodedProduct prod = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(item.getBarcode());
		try {
			
			SHOPPING_CART_ARRAY[i][0] = prod.getDescription();
			SHOPPING_CART_ARRAY[i][1] = Integer.toString(quantity);
			
			updateTotalPayment(item, quantity);
			
		} catch (NullPointerException e) {
			throw new SimulationException(e);
		}
		
		i++;

	}
	
	
	
	
//	public void addToShoppingCart(BarcodedItem item) {
//		item.getWeight();
//	}
	
	
	// Aris added: This method makes a call to the DB to check if isPerUnit is True or False
//	public boolean doesItemNeedToBeWeighed(BarcodedItem item) {
//		return ProductDatabases.BARCODED_PRODUCT_DATABASE.get(item.getBarcode()).isPerUnit();
//	}
	
	
	/**
	 * Removes item from Shopping cart. Loops through the array and if the inputs match
	 * elements in the array then remove it from the array.
	 * @param item
	 * 		BarcodedItem input
	 * @param quantity
	 * 		Quantity of the item you want to remove
	 */
	@SuppressWarnings("unused")
	public void removeFromShoppingCart(BarcodedItem item, int quantity) {
		
		BarcodedProduct prod = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(item.getBarcode());
		
		try {
			
			for (int j = 0; j < SHOPPING_CART_ARRAY.length; j++) {
				if (prod.getDescription().equals(SHOPPING_CART_ARRAY[j][0]) && 
						Integer.toString(quantity).equals(SHOPPING_CART_ARRAY[j][1])) {
					SHOPPING_CART_ARRAY[j][0] = null;
					SHOPPING_CART_ARRAY[j][1] = null;
					totalNumOfItems = totalNumOfItems - quantity;
					decreaseTotalPayment(item, quantity);
					
					break;
				} else 
					throw new NullPointerException("Item is not on shopping cart");
			} 
		} catch (NullPointerException e) {
			throw new SimulationException(e);
		}


	}
		
	/**
	 * Adds quantity to total quantity
	 * @param quantity
	 * 		number to add to quantity
	 * @return
	 * 		return the total number of items in cart
	 */
	public void addQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("null barcode");
		}
		totalNumOfItems += quantity;
	}
	
	/**
	 * Removes quantity from total quantity
	 * @param quantity
	 * 		Number to remove from total quantity
	 * @return
	 * 		return total quantity 
	 */
	public void decreaseQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("null barcode");
		}
		totalNumOfItems -= quantity;
	}
	
	/**
	 * Updates the total payment. Calculates the correct price with respect to the quantity
	 * of item and adds it to the totalpayment.
	 * @param barcodedItem
	 * 		BarcodedItem to find the price
	 * @param quantity
	 * 		quantity of item
	 */
	private void updateTotalPayment(BarcodedItem barcodedItem, int quantity) {

		BigDecimal price = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcodedItem.getBarcode())
				.getPrice().multiply(new BigDecimal(quantity));
		
		price = price.setScale(2, BigDecimal.ROUND_HALF_UP); 
						
		totalPayment = totalPayment.add(price);
	
	}
	
	/**
	 * Updates the total payment. Calculates the correct price with respect to the quantity
	 * of item and subtracts it from the total payment.
	 * @param barcodedItem
	 * 		BarcodedItem to find the price
	 * @param quantity
	 * 		quantity of item
	 */
	@SuppressWarnings("unused")
	private void decreaseTotalPayment(BarcodedItem barcodedItem, int quantity) {

		BigDecimal price = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcodedItem.getBarcode())
				.getPrice().multiply(new BigDecimal(quantity));
		
		price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
						
		totalPayment = totalPayment.subtract(price);
	
	}
	
	/**
	 * 
	 * @return
	 * 		Return the total payment
	 */
	public BigDecimal getTotalPayment() {
		return totalPayment;
	}
	
	/**
	 * 
	 * @return
	 * 		return the total Quantity
	 */
	public int getTotalQuantity() {
		return totalNumOfItems;
	}
	

}
