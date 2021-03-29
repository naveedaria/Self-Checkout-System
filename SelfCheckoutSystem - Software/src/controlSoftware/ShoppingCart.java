package controlSoftware;

import java.math.BigDecimal;
import java.util.Scanner;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;
import org.lsmr.selfcheckout.external.ProductDatabases;
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
	int totalNumOfItems;
	BigDecimal itemPrice;
	String[][] SHOPPING_CART_ARRAY;
	int i;
	
	ElectronicScaleListener baggingAreaScale;
	
	
	public ShoppingCart() {
		SHOPPING_CART_ARRAY = new String[30][2];
		this.totalPayment = new BigDecimal("0.00");
		this.totalNumOfItems = 0;
		i = 0;
			
	}
	
	public void addBarcodedItemToShoppingCart(BarcodedItem item, int quantity) {
		
		BarcodedProduct prod = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(item.getBarcode());
				
		SHOPPING_CART_ARRAY[i][0] = prod.getDescription();
		SHOPPING_CART_ARRAY[i][1] = Integer.toString(quantity);
		
		i++;
		
		if (i >= 31) {
			throw new SimulationException("Too many items added to the shopping cart");
		}

	}
		
	
	public int addQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("null barcode");
		}
		totalNumOfItems += quantity;
		return totalNumOfItems;
	}
	
	//Quantity fix
	public void updateTotalPayment(BarcodedItem barcde, int quantity) {

		BigDecimal price = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcde.getBarcode()).getPrice().multiply(new BigDecimal(quantity));
				
		totalPayment = totalPayment.add(price);
	
	}
	
	public BigDecimal getTotalPayment() {
		return totalPayment;
	}
	
	
	public int getTotalQuantity() {
		return totalNumOfItems;
	}
	

}
