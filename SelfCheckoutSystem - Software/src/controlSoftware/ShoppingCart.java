package controlSoftware;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

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
	BarcodedItem[] BARCODEDITEM_ARRAY;
	PLUCodedItem[] PLUCODEDITEM_ARRAY;
	int i;
	Barcode[] BARCODE_ARRAY;
	ElectronicScaleListener baggingAreaScale;
	
	/**
	 * Initializes Shopping Cart
	 * 
	 */
	public ShoppingCart() {
		
		SHOPPING_CART_ARRAY = new String[30][2];
		this.totalPayment = new BigDecimal("0.00");
		this.totalNumOfItems = 0;
		BARCODEDITEM_ARRAY = new BarcodedItem[30];
		PLUCODEDITEM_ARRAY = new PLUCodedItem[30];
		BARCODE_ARRAY = new Barcode[30];
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
			BARCODEDITEM_ARRAY[i] = item;
			BARCODE_ARRAY[i] = prod.getBarcode();
			updateTotalPayment(item, quantity);
			
			totalNumOfItems += quantity;
			
		} catch (NullPointerException e) {
			throw new SimulationException(e);
		}
		
		i++;

	}
	

	
	/**
	 * Adds item to Shopping cart array
	 * @param item
	 * 		PLUCodedItem input, can access database using PLUCode
	 * @param quantity
	 * 		The number of times item was scanned
	 */
	public void addToShoppingCart(PLUCodedItem pluCodedItem, int quantity) {
		PLUCodedProduct pluProd = ProductDatabases.PLU_PRODUCT_DATABASE.get(pluCodedItem.getPLUCode());
		
		try {
			
			SHOPPING_CART_ARRAY[i][0] = pluProd.getDescription();
			SHOPPING_CART_ARRAY[i][1] = Integer.toString(quantity);
			PLUCODEDITEM_ARRAY[i] = pluCodedItem;
			updateTotalPayment(pluCodedItem, quantity);
			
			totalNumOfItems += quantity;
			
		} catch (Exception e) {
			throw new SimulationException(e);
		}
		
		i++;
	}
	
	
	
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
					BARCODE_ARRAY[j] = null;
					BARCODEDITEM_ARRAY[j] = null;
					break;
				}
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
		
		System.out.println("Total Payment = " + totalPayment.toString());
	
	}
	
	
	/**
	 * Updates the total payment. Calculates the correct price with respect to the quantity
	 * of item and adds it to the totalpayment.
	 * @param pluCodedItem
	 * 		PLUCodedcodedItem to find the price
	 * @param quantity
	 * 		quantity of item
	 */
	private void updateTotalPayment(PLUCodedItem pluCodedItem, int quantity) {

		double weight = pluCodedItem.getWeight() / 1000;
		
		BigDecimal price = ProductDatabases.PLU_PRODUCT_DATABASE.get(pluCodedItem.getPLUCode())
				.getPrice().multiply(new BigDecimal(quantity)).multiply(new BigDecimal(weight));
		

	
		
		price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
						
		totalPayment = totalPayment.add(price);
		
		String description = ProductDatabases.PLU_PRODUCT_DATABASE.get(pluCodedItem.getPLUCode()).getDescription();
		
		System.out.println("Subtotal of " + description + " is: " + totalPayment.toString());
	
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
	
	
	
	public BigDecimal getPriceOfBarcodedProduct(BarcodedItem barcodedItem) {
		
		return ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcodedItem.getBarcode()).getPrice();
	}
	
	
	public String getDescriptionOfBarcodedProduct(BarcodedItem barcodedItem) {
		
		return ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcodedItem.getBarcode()).getDescription();
	}
	
	
	public BigDecimal getPriceOfPLUProduct(PLUCodedItem pluCodedItem) {
		
		return ProductDatabases.PLU_PRODUCT_DATABASE.get(pluCodedItem.getPLUCode()).getPrice();
	}
	
	public String getDescriptionOfPLUProduct(PLUCodedItem pluCodedItem) {
		
		return ProductDatabases.PLU_PRODUCT_DATABASE.get(pluCodedItem.getPLUCode()).getDescription();
	}
	
	public void updatePayment(BigDecimal price) {
        this.totalPayment = this.totalPayment.add(price);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
