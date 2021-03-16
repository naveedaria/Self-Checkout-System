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
	
	public ControlSoftware(Currency currency, int[] banknoteDenominations, BigDecimal[] coinDenominations, int scaleMaxWeight, int scaleSensitivity) {
		this.currency = currency;
		this.banknoteDenominations = banknoteDenominations;
		this.coinDenominations = coinDenominations;
		this.scaleMaxWeight = scaleMaxWeight;
		this.scaleSensitivity = scaleSensitivity; 
		
		this.selfCheckout = new SelfCheckoutStation(this.currency, this.banknoteDenominations, this.coinDenominations, this.scaleMaxWeight, this.scaleSensitivity);
	}
	
	
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
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public static void bagMethod(SelfCheckoutStation selfCheckout, BarcodedItem someItem) {
		baggingAreaStub bagAreaStub = new baggingAreaStub();
		selfCheckout.baggingArea.register(bagAreaStub);
		//selfCheckout.baggingArea.enable();
		selfCheckout.baggingArea.add(someItem);
	}
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public void removeItem(SelfCheckoutStation selfCheckout, BarcodedItem someItem) {
		baggingAreaStub bagAreaStub = new baggingAreaStub();
		selfCheckout.baggingArea.register(bagAreaStub);
		//selfCheckout.baggingArea.enable();
		selfCheckout.baggingArea.remove(someItem);
	}
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
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
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public static boolean checkCoinVal(Coin someCoin, BigDecimal[] coinDenominations) {
		//checking if coin value is in list of coin denominations 
		for (int i = 0; i<coinDenominations.length; i++) {
			if (coinDenominations[i].compareTo(someCoin.getValue())==0) {
				return true; 
			}
		}
		return false; 
	}
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public static int banknoteMethod(SelfCheckoutStation selfCheckout, Currency currency, int[] banknoteDenominations, Banknote someBanknote) throws OverloadException, DisabledException {
		try {
			boolean validCoinVal = checkBanknoteVal(someBanknote, banknoteDenominations);
			if (validCoinVal) {
				banknotePaymentStub billStub = new banknotePaymentStub();
				selfCheckout.banknoteInput.register(billStub);
				selfCheckout.banknoteInput.enable();
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
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public static boolean checkBanknoteVal(Banknote someBanknote, int[] banknoteDenominations) {
		//checking if banknote value is in list of banknote denominations 
		for (int i = 0; i<banknoteDenominations.length; i++) {
			if (banknoteDenominations[i] == someBanknote.getValue()) {
				return true; 
			}
		}
		return false; 
	}
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public void setTotalBalance() {
		for (int i = 0; i<this.productBarcodes.size();i++) {
			BigDecimal price = this.db.get(productBarcodes.get(i)).getPrice();
			this.paymentTotal = this.paymentTotal.add(price);
		}
	} 
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public BigDecimal getTotalBalance() {
		return this.paymentTotal;
	}
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
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
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public BigDecimal calculateBillPayment(int banknoteValue, boolean billProcessed) {
		BigDecimal bankNoteVal = new BigDecimal(banknoteValue);
		if (billProcessed==false) {
			BigDecimal balance = getTotalBalance();
			this.change = balance.subtract(bankNoteVal);
			billProcessed = true; 
		}else if (billProcessed==true) {
			this.change = this.change.subtract(bankNoteVal);
		}
		return this.change;
	}
	
}