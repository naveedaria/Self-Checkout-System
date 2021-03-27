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
	private Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
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
	public void scanProduct(String barcode, float weight, float price, String name) {
		BarcodeScanner scannerObject = new BarcodeScanner();
		Barcode someBarcode = new Barcode(barcode);
		BarcodedItem someItem = new BarcodedItem(someBarcode, weight);
		
		BigDecimal productPrice = new BigDecimal(price);
		BarcodedProduct prod = new BarcodedProduct(someBarcode, name, productPrice);
		this.db.put(someBarcode, prod);
		
		BarcodeScannerListenerStub stub = new BarcodeScannerListenerStub();
		scannerObject.register(stub);	
		scannerObject.enable();
		scannerObject.scan(someItem);
		this.productBarcodes.add(someBarcode);
		this.numProducts+=1;
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