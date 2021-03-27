package controlSoftware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ControlSoftware {
	private static BigDecimal paymentTotal = new BigDecimal(0);
	private BigDecimal change;
	private boolean coinProcessed = false;
	private int numProducts = 0;
	private ArrayList<Barcode> productBarcodes = new ArrayList<Barcode>();
	
	private Currency currency;
	private int[] banknoteDenominations;
	private BigDecimal[] coinDenominations;
	private int scaleMaxWeight;
	private int scaleSensitivity;
	public SelfCheckoutStation selfCheckout;
	
	//Aris Comment: This shouldn't be here. We will construct the database at initialization
	private Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
	
	
	private BarcodeScanner scannerObject;
	
	
	/*
	private final Currency c1 = Currency.getInstance("CAD");
	private final int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
	private final BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
	private final int scaleMaximumWeight = 500; // Don't know the units of the scale, will figure out later
	private final int scaleSensitivity = 1; // Don't know the units also
	private static SelfCheckoutStation selfCheckout = new SelfCheckoutStation(c1, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
	*/ 
	//Test Commit Ali 
	// Second Test
	
	/**
	 * Constructor that initializes the SelfCheckout Station hardware
	 * 
	 * @param currency
	 * 		  The currency type accepted
	 * @param banknoteDenominations
	 * 		  The denominations of bank notes accepted
	 * @param coinDenominations
	 * 		  The denominations of coins accepted
	 * @param scaleMaxWeight
	 * 		  The maximum weight permitted in the bagging area
	 * @param scaleSensitivity
	 * 		  The sensitivity of the scale. 
	 */
	public ControlSoftware(Currency currency, int[] banknoteDenominations, BigDecimal[] coinDenominations, int scaleMaxWeight, int scaleSensitivity) {
		this.currency = currency;
		this.banknoteDenominations = banknoteDenominations;
		this.coinDenominations = coinDenominations;
		this.scaleMaxWeight = scaleMaxWeight;
		this.scaleSensitivity = scaleSensitivity; 
		
		this.selfCheckout = new SelfCheckoutStation(this.currency, this.banknoteDenominations, this.coinDenominations, this.scaleMaxWeight, this.scaleSensitivity);
		
		// Aris comment: I think we should register all devices right here. This is simulating the software turning on, and connecting to all devices
		// Will do for the scanner, then show to the Team on Saturday's meeting
		
		// We already have a mainScanner as part of self-checkout
		//scannerObject = new BarcodeScanner();
		BarcodeScannerListenerStub stub = new BarcodeScannerListenerStub();
		//scannerObject.register(stub);	
		//scannerObject.enable();
		
		selfCheckout.mainScanner.register(stub);
		selfCheckout.mainScanner.enable();
	}
	
	/**
	 * 
	 * @param barcode
	 * 		  String input of the Item's barcode
	 * @param weight
	 * 		  Weight of the item in grams 
	 * @param price
	 * 		  Price of the item
	 * @param name
	 * 		  Name of the Item
	 */
	public void scanProduct(BarcodedItem barcodedItem, int quantity) {
		//float weight, float price, String name
		
		// Branch 2 test commit
		
		// Aris comment: Step 1: Before any of this, we need to populate the database. It would work for now (iteration 2), but not at runtime
		// DONE.
		
		// Aris Comment: Step 2. This method should take only BarcodedItem barcodeItem, and some int quantity
		// DONE.
		
		// Aris Comment Step 3: Now we would perform a scan. In here, use the scan of the mainsScanner inside of selfCheckout
		// scannerObject.scan(barcodedItem); 
	
		selfCheckout.mainScanner.scan(barcodedItem);
		
		// Aris Comment Step 4: After scanning, we can either check if the item is of type pricePerUnit() or if its just a regular item
		// in the Listener (ie. override the method), or we can do it here. For now, let's do it here
		
		// BarcodedProduct bp = new BarcodedProduct(barcodedItem.getBarcode(), )
		
		
		
		//BarcodeScanner scannerObject = new BarcodeScanner();
		//Barcode someBarcode = new Barcode(barcode);
		//BarcodedItem someItem = new BarcodedItem(someBarcode, weight);
		
		
		//Aris Comment: Step 4. Then instead of this code below, we would make a call to the database using barcode as input
		//BigDecimal productPrice = new BigDecimal(price);
		// BarcodedProduct prod = new BarcodedProduct(someBarcode, name, productPrice);
		// this.db.put(someBarcode, prod);
		
		// This should be in the constructor
//		BarcodeScannerListenerStub stub = new BarcodeScannerListenerStub();
//		scannerObject.register(stub);	
//		scannerObject.enable();
//		scannerObject.scan(someItem);
		
		// No need for this
		//this.productBarcodes.add(someBarcode);
		//this.numProducts+=1;
		
		// Aris Comment: Step 5. If the product is produce (ie. isPerUnit() == false), then it would have to be weighed here, and price would be calculated taking into account weight
		// Otherwise, just calculate price, and quantity
		
		// Aris Comment: Step 6: If it needed to be weighted, the control is transferred back to user. They will need to put item on scale. This would happen on GUI.
		// For now, we can either use command line to input it, or hard-code it
		
		// Aris Comment: Step 7: The result of scanProduct is to update shopping cart. So we would call the method: addBarcodedItemToShoppingCart here
		// At this point, we know: price per item, the quantity entered by user (default is 1), and the weight.
		
		// We can either calculate the total price here for this one scan, and pass that to method, ie. addBarcodedItemToShoppingCart(totalPriceScanned)
		// This is 1 design. It depends what you guys want to appear on the receipt/GUI menu
		
		// Aris comment: Alternative step 7: Or another design would be: shopingcart.addBarcodedItemToShoppingCart(barcode, pricePerItem, quantity, weight)
		// This would allow the information in the parameter set to be displayed through shopping cart, receipt, and screen, etc.
	}
	
	public int getNumOfProducts() {
		return this.numProducts;
	}
	

	
	/**
	 * Method to add items from the bagging area
	 * 
	 * @param selfCheckout
	 * 	      SelfCheckout station input
	 * @param someItem
	 * 		  The item to add to the baggingArea 
	 */
	public void addItemToBaggingArea(SelfCheckoutStation selfCheckout, BarcodedItem someItem) {
		baggingAreaStub bagAreaStub = new baggingAreaStub();
		selfCheckout.baggingArea.register(bagAreaStub);
		//selfCheckout.baggingArea.enable();
		selfCheckout.baggingArea.add(someItem);
	}
	
	/**
	 * Method to remove items from the bagging area
	 * 
	 * @param selfCheckout
	 * 	      SelfCheckout station input
	 * @param someItem
	 * 		  The item to add to the baggingArea 
	 */
	public void removeItemFromBaggingArea(SelfCheckoutStation selfCheckout, BarcodedItem someItem) {
		baggingAreaStub bagAreaStub = new baggingAreaStub();
		selfCheckout.baggingArea.register(bagAreaStub);
		//selfCheckout.baggingArea.enable();
		selfCheckout.baggingArea.remove(someItem);
	}
	

	/**
	 * Method to check whether a coin is accepted (correct denomination and currency)
	 * 
	 * @param selfCheckout
	 *  	  SelfCheckout station input
	 * @param currency
	 * 		  The type of currency accepted
	 * @param coinDenominations
	 * 		  The denomination of coins accepted 
	 * @param someCoin
	 * 		  The coin checked and accepted for payment
	 * @return
	 * 		  Return true if the coin is correct
	 * @throws DisabledException
	 * 		   If the coin machine is filled
	 */
	public static BigDecimal coinMethod(SelfCheckoutStation selfCheckout, Currency currency, BigDecimal[] coinDenominations, Coin someCoin) throws DisabledException {
		try {
			if (someCoin==null) {
				throw new SimulationException("Null coin entered.");
			}
			boolean validCoinVal = checkCoinVal(someCoin, coinDenominations);
			if (validCoinVal) {
				CoinPaymentStub coinStub = new CoinPaymentStub();
				selfCheckout.coinSlot.register(coinStub);
				selfCheckout.coinSlot.accept(someCoin); 
				return someCoin.getValue();
			}else {
				throw new IllegalArgumentException("Invalid coin value");
			}
		}catch(SimulationException e) {
			throw new SimulationException("Invalid coin entered.");
		}catch (DisabledException e) {
			throw new DisabledException();
		}catch(IllegalArgumentException e) {
			System.out.println("Value of coin was invalid.");
			throw new IllegalArgumentException("Invalid coin value"); 
		}
	}
	
	/**
	 * Method returns the value of the coin
	 * 
	 * @param someCoin
	 * 		  A type of coin
	 * @param coinDenominations
	 * 		  The denomination of coins accepted
	 * @return
	 */
	public static boolean checkCoinVal(Coin someCoin, BigDecimal[] coinDenominations) {
		//checking if coin value is in list of coin denominations 
		for (int i = 0; i<coinDenominations.length; i++) {
			if (coinDenominations[i].compareTo(someCoin.getValue())==0) {
				return true; 
			}
		}
		return false; 
	}
	
	/**
	 * Method to verify the bank note is accepted and value
	 * 
	 * @param selfCheckout
	 *  	  SelfCheckout station input
	 * @param currency
	 * 		  The type of currency accepted
	 * @param banknoteDenominations
	 * 		  The denomination of bank notes accepted 
	 * @param someBanknote
	 * 		  The bank note checked and accepted for payment
	 * @return
	 * @throws OverloadException
	 * 		   If the coin machine is filled
	 * @throws DisabledException
	 * 		   If the machien is disabled
	 */
	public static int banknoteMethod(SelfCheckoutStation selfCheckout, Currency currency, int[] banknoteDenominations, Banknote someBanknote) throws OverloadException, DisabledException {
		try {
			if (someBanknote==null) {
				throw new SimulationException("Null banknote entered.");
			}
			boolean validCoinVal = checkBanknoteVal(someBanknote, banknoteDenominations);
			if (validCoinVal) {
				banknotePaymentStub billStub = new banknotePaymentStub();
				selfCheckout.banknoteInput.register(billStub);
				//selfCheckout.banknoteInput.enable();
				selfCheckout.banknoteInput.accept(someBanknote);
				return someBanknote.getValue();
			}else {
				throw new IllegalArgumentException("Invalid banknote value");
			}
		}catch(OverloadException e) {
			throw new OverloadException("A banknote is dangling from the slot. Remove that before adding another.");
		}catch(SimulationException e) {
			throw new SimulationException("Invalid banknote entered.");
		}catch (DisabledException e) {
			throw new DisabledException();
		}catch(IllegalArgumentException e) {
			System.out.println("Value of banknote was invalid.");
			throw new IllegalArgumentException("Invalid banknote value");
		}
	}
	
	/**
	 * Method returns the value of the bank note
	 * 
	 * @param someBankote
	 * 		  A type of bank note
	 * @param banknoteDenominations
	 * 		  The denomination of bank notes accepted
	 * @return
	 */
	public static boolean checkBanknoteVal(Banknote someBanknote, int[] banknoteDenominations) {
		//checking if banknote value is in list of banknote denominations 
		for (int i = 0; i<banknoteDenominations.length; i++) {
			if (banknoteDenominations[i] == someBanknote.getValue()) {
				return true; 
			}
		}
		return false; 
	}
	
	/**
	 * Method that sets the total balance owed by the customer
	 * 
	 */
	public void setTotalBalance() {
		for (int i = 0; i<this.productBarcodes.size();i++) {
			BigDecimal price = this.db.get(productBarcodes.get(i)).getPrice();
			this.paymentTotal = this.paymentTotal.add(price);
		}
	} 
	
	/**
	 * Getter for total balance
	 * @return
	 */
	public BigDecimal getTotalBalance() {
		return this.paymentTotal;
	}
	
	/**
	 * Setter for the Customers Change
	 */
	public void setChange() {
		this.change = new BigDecimal(0);
	}
	
	/**
	 * Method to calculate payment by coin
	 * @param coinValue
	 * 		  The value of coin used to pay 
	 * @return 
	 * 		  Returns the change if any
	 */
	public BigDecimal calculateCoinPayment(BigDecimal coinValue) {
		if (coinProcessed==false) {
			BigDecimal balance = getTotalBalance();
			this.change = balance.subtract(coinValue);
			this.coinProcessed = true; 
		}else if (coinProcessed==true) {
			this.change = this.change.subtract(coinValue);
		}
		return this.change;
	}
	
	/**
	 * Method for calculating payment by bill
	 * @param banknoteValue
	 * 		  The value of bank note used
	 * @param billProcessed
	 * 		  Checks whether the bill is acceptable
	 * @return
	 * 		  Returns the change if any
	 */
	public BigDecimal calculateBillPayment(int banknoteValue, boolean billProcessed) {
		BigDecimal bankNoteVal = new BigDecimal(banknoteValue);
		if (billProcessed==false) {
			BigDecimal balance = getTotalBalance();
			this.change = balance.subtract(bankNoteVal);
			billProcessed = true; 
		}else {
			this.change = this.change.subtract(bankNoteVal);
		}
		return this.change;
	}
	
}