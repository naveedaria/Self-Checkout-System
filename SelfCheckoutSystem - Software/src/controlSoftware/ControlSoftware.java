package controlSoftware;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ControlSoftware {
	private static BigDecimal paymentTotal = new BigDecimal(0);
	public BigDecimal change;
	private boolean coinProcessed = false;
	private boolean billProcessed = false;
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
	
	// Aris comment: Delete this
	private BarcodeScanner scannerObject;
	
	public ShoppingCart shoppingCart;
	
	
	/*
	private final Currency c1 = Currency.getInstance("CAD");
	private final int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
	private final BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
	private final int scaleMaximumWeight = 500; // Don't know the units of the scale, will figure out later
	private final int scaleSensitivity = 1; // Don't know the units also
	private static SelfCheckoutStation selfCheckout = new SelfCheckoutStation(c1, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
	*/ 
	
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
		
		
		
		// Create shopping cart object
		shoppingCart = new ShoppingCart();
		
		
		// We already have a mainScanner as part of self-checkout
		//scannerObject = new BarcodeScanner();
		BarcodeScannerListenerStub stub = new BarcodeScannerListenerStub();
		//scannerObject.register(stub);	
		//scannerObject.enable();
		
		selfCheckout.mainScanner.register(stub);
		selfCheckout.mainScanner.enable();
		
		ElectronicScaleListenerStub electronicScaleStub = new ElectronicScaleListenerStub();
		
		selfCheckout.scale.register(electronicScaleStub);
		selfCheckout.scale.enable();
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
	// Changing this to (BarcodedItem barcodedItem, int quantity)
	// Aris comment: This should (probably) be changed to boolean when using with main() or GUI
	public void scanProduct(BarcodedItem barcodedItem, int quantity) {
		
		// 1. All this does is notify the listener. You can either implement the event handler there or here.
		selfCheckout.mainScanner.scan(barcodedItem);
		
		// Aris Comment: If the product is produce (ie. isPerUnit() == false), then it would have to call weigh item here, and price would be calculated taking into account weight
		// Otherwise, just calculate price, and quantity
		
		//if(!shoppingCart.doesItemNeedToBeWeighed(barcodedItem)) {
			// Pass execution flow back to user, and prompt to put item on scale (in main/driver/GUI)
			// This can be done by setting a return to True (changing return type to Boolean)
		//}else {
			shoppingCart.addToShoppingCart(barcodedItem, quantity);
		//}
		
	
	}


	// This can eventually be removed
	public int getNumOfProducts() {
		return this.numProducts;
	}
	
	
	
		public double weighItem(BarcodedItem barcodedItem) {
			//selfCheckout.scale.add(barcodedItem);
			try {
				
				// This will throw an exception if item weight exceeds MAX scale weight
				selfCheckout.scale.add(barcodedItem);
				return selfCheckout.scale.getCurrentWeight();
				
				// Will return 0 if nothing is on scale. Note that this isn't currently used, because we don't have GUI set up
				//weight = selfCheckout.scale.getCurrentWeight();
				
				//System.out.println("The weight rigtht now is: " + weight);
			
				
				// Design preference: Call GUI and/or pass the weight and barcodedItem to the view (iteration 3)
				// something like: updateGUIweight(barcodedItem, weight)
				
				// Then return flow of execution back to main/driver, and prompt for user to remove from scale, and place into bagging area
				//return true;
			}catch(OverloadException e) {
				// Handle exceptions here
				//System.out.println("The weight rigtht now is: " + weight);
				return 0;
			}
		}
		
		
		public boolean removeItemFromScale(BarcodedItem barcodedItem){
			
			// You can either call shopping cart here, or wait until customer puts it into bagging area.
			
			try {
				// Remove item
				selfCheckout.scale.remove(barcodedItem);
				
				// If removal succeeds, add it to the shopping cart (use overloaded method)
				shoppingCart.addToShoppingCart(barcodedItem, 1);
				
				// Return flow of execution back to driver. As an aside, in the driver, the customer will now
				// be asked to place item into bagging area
				return true;
				
			}catch (SimulationException e) {
				
				return false;
			}
		
		}
	
	
	
	
	
	
	
	
	//Use Case: Customer Doesn't put item on the bagging area scale
	//Still need 
	
	public boolean addToScalePrompt(Item item) {
		System.out.println("Please add item to the bagging area \n");
		
		boolean addedToScale = false;
		
		while(addedToScale == false) {
			
			selfCheckout.mainScanner.disable();
			
			@SuppressWarnings("resource")
			Scanner scaleUserInput = new Scanner(System.in);
			
			System.out.println("Press 1 if you will not bag, 0 if you will \n");
			
			int userInput = scaleUserInput.nextInt();
			
			if (userInput == 0) {
				
				addedToScale = true;
				selfCheckout.mainScanner.enable();

								
			} else continue;
			
			// Disable scanner until input (GUI this would a button)
			// If weightChanged() returns something then we close out of the loop
			
		} return true;
		
	}
	
	// Use Case: Customer Has their Own Bag
	
	public BarcodedItem customerBag() {
		
		Barcode bag = new Barcode("customerBag");
		BarcodedItem customerBag = new BarcodedItem(bag, 5.00);
		
		return customerBag;
	}
	
	public boolean doneAddingItems(boolean ooo) {
		return ooo;
	}
	
	public void addToBaggingArea(BarcodedItem item) throws OverloadException {
		
		selfCheckout.baggingArea.add(item);
		
	}
	
	public void removeFromBaggingArea(BarcodedItem item) {
		
		selfCheckout.baggingArea.remove(item);
		
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
	 * 		  Return coin value 
	 * @throws DisabledException
	 * 		   If the coin machine is filled
	 */
	public BigDecimal coinMethod(Coin someCoin) throws DisabledException {
		// Aris comment: we shouldn't need to pass selfCheckout into this. Use the global variable of this class 
		// For currency, and coinDenominations. ie. to access them, we would use the constructed currency and coinDenominations
		try {
			if (someCoin==null) {
				throw new SimulationException("Null coin entered.");
			}
			boolean validCoinVal = checkCoinVal(someCoin);
			if (validCoinVal) {
				// Aris comment: put in constructor
				CoinPaymentStub coinStub = new CoinPaymentStub();
				this.selfCheckout.coinSlot.register(coinStub);
				this.selfCheckout.coinSlot.accept(someCoin); 
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
	private boolean checkCoinVal(Coin someCoin) {
		// Aris comment: Should only need someCoin. coinDenominations can be used from the constructed field of this class
		//checking if coin value is in list of coin denominations 
		for (int i = 0; i<coinDenominations.length; i++) {
			if (this.coinDenominations[i].compareTo(someCoin.getValue())==0) {
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
	public int banknoteMethod(Banknote someBanknote) throws OverloadException, DisabledException {
		// Aris comment: same comments as above. Don't need selfCheckout, currency, banknoteDenominations. Only someBanknote
		try {
			if (someBanknote==null) {
				throw new SimulationException("Null banknote entered.");
			}
			boolean validCoinVal = checkBanknoteVal(someBanknote, this.banknoteDenominations);
			if (validCoinVal) {
				banknotePaymentStub billStub = new banknotePaymentStub();
				this.selfCheckout.banknoteInput.register(billStub);
				//selfCheckout.banknoteInput.enable();
				this.selfCheckout.banknoteInput.accept(someBanknote);
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
	private static boolean checkBanknoteVal(Banknote someBanknote, int[] banknoteDenominations) {
		// Aris comment: Same comments as abobe: only need someBanknote. For banknoteDenominations, use field/instance variable constructed by this class
		//checking if banknote value is in list of banknote denominations 
		for (int i = 0; i<banknoteDenominations.length; i++) {
			if (banknoteDenominations[i] == someBanknote.getValue()) {
				return true; 
			}
		}
		return false; 
	}
	
	
	/**
	 * Setter for the Customers Change
	 */
	public void setChange() {
		// Aris comment: For calculating change, another model-type would need to be made. In here, we would handle the logic for check-out
		// Somewhere, there would be a getter to get the change. Not set it. So you could have a method in software that accesses this via
		// an object of the checkout class
		this.change = new BigDecimal(0);
	}
	
	
	
	
	/**
	 * Method to calculate payment by coin
	 * @param coinValue
	 * 		  The value of coin used to pay 
	 */
	public void calculateCoinPayment(BigDecimal coinValue) {
		// Aris comment: for this, it's okay to have the method calculateCoinPayment, but the outcome should be a call to Checkout checkout
		// which either updates the remaining balance (like in a vending machine) and prints to GUI, or accumulates it until the balance has been met
		if (coinProcessed==false) {
			BigDecimal balance = this.shoppingCart.getTotalPayment();
			this.change = balance.subtract(coinValue);
			this.coinProcessed = true; 
		}else if (coinProcessed==true) {
			this.change = this.change.subtract(coinValue);
		}
	}
	
	/**
	 * Method for calculating payment by bill
	 * @param banknoteValue
	 * 		  The value of bank note used
	 * @param billProcessed
	 * 		  Checks whether the bill is acceptable
	 */
	public void calculateBillPayment(int banknoteValue) {
		BigDecimal bankNoteVal = new BigDecimal(banknoteValue);
		if (billProcessed==false) {
			BigDecimal balance = this.shoppingCart.getTotalPayment();
			this.change = balance.subtract(bankNoteVal);
			billProcessed = true; 
		}else {
			this.change = this.change.subtract(bankNoteVal);
		}
	}
	
	//Pay by card 
	//Create Card before for Iteration 3
	public void finishedAddingItems(boolean useMembershipCard, String numberMember, String cardholderMember, boolean tap, String cardCompany, String type, String number, String cardholder, String cvv, String pin, boolean isTapEnabled,
			boolean hasChip, Calendar expiry, BigDecimal cardLimit, BufferedImage signature, boolean insertCard, String pinInput) throws IOException {
		
		BigDecimal balance = this.shoppingCart.getTotalPayment();
		PaymentByCard cardPaymentHandler = new PaymentByCard(this.selfCheckout, cardCompany);
		cardPaymentHandler.detectCard(type, number, cardholder, cvv, pinInput, isTapEnabled, hasChip, expiry, cardLimit);
		
		if (useMembershipCard) {
			//THIRD ITERATION - no discount or points implemented yet 
			ScanMembershipCard membershipCardReader = new ScanMembershipCard(this.selfCheckout);
			membershipCardReader.tapMembershipCard(numberMember, cardholderMember);
		}
		
		if (tap) {
			cardPaymentHandler.tapToPay(balance, insertCard, pinInput);
			this.change = new BigDecimal(0);
		}else {
			cardPaymentHandler.swipeToPay(signature, balance, insertCard, pinInput);
			this.change = new BigDecimal(0);
		}
		//change is now 0, proceed to receipt 	
	}
	
	
	//Pay by cash 
	//Assume user enters more than necessary cash, handle case of less cash than balance through GUI in Iteration 3
	public void finishedAddingItems(boolean useMembershipCard, String numberMember, String cardholderMember, Coin[] coins, Banknote[] banknotes) throws IOException, DisabledException, OverloadException {
		BigDecimal balance = this.shoppingCart.getTotalPayment();
		
		if (useMembershipCard) {
			//THIRD ITERATION - no discount or points implemented yet 
			
			ScanMembershipCard membershipCardReader = new ScanMembershipCard(this.selfCheckout);
			membershipCardReader.tapMembershipCard(numberMember, cardholderMember);
		}

		for (int i=0; i<coins.length; i++) {
			BigDecimal coinVal = coinMethod(coins[i]);
			calculateCoinPayment(coinVal);
		}
		
		for (int i=0; i<banknotes.length; i++) {
			int billVal = banknoteMethod(banknotes[i]);
			calculateBillPayment(billVal);
		}
		
		//at this point, the this.change value should actually be the final change 
		DispenseChange changeDispenser = new DispenseChange(this.selfCheckout, this.change);
		this.change = changeDispenser.calculateChangeDenominations();
		//change is now 0, proceed to receipt 
	
		
		if (insertCoin) {
			
		}
		
		if (insertBill) {
			
		}
	}
	// Method to print the receipt
	public void printReceipt() {
		// Some strings used to format the receipt 
		String s1 = "------------------------------------";
		// 2-D Array from the shopping cart class
		String[][] cart = shoppingCart.SHOPPING_CART_ARRAY;
		// Barcoded Item array from shopping cart class
		// A single barcoded temporary item
		// A barcoded product
		BarcodedProduct prod;
		// Printing 
		System.out.println(s1);
		String s2 = String.format("%-1s %1s %10s\n","Qty", "Item", "Price");
		System.out.println(s2);
		String line;
		for (int i = 0; i < cart.length; i++) {
			try {
			// Accessing the barcoded item from the array
			BarcodedItem tempItem = shoppingCart.BARCODEDITEM_ARRAY[i];
			// Getting the product from the Database
			prod = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(tempItem.getBarcode());
			// String format for each line printed in the receipt
			// Quantity and Name are accessed from the shopping cart array
			// Price is accessed from the product class
			line = String.format("%-1s %1s %10s\n", cart[i][1], cart[i][0], prod.getPrice());
			// Print line
			System.out.println(line);
			}catch (NullPointerException e) {
			}
		}
		// Print end line
		System.out.println(s1);
	
	}
	
}
