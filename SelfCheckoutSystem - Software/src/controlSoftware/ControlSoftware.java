package controlSoftware;

import java.math.BigDecimal;
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
	private static BigDecimal paymentTotal;
	public static BigDecimal coinValue;
	public static SelfCheckoutStation station;
	
	private Currency currency;
	private int[] banknoteDenominations;
	private BigDecimal[] coinDenominations;
	private int scaleMaxWeight;
	private int scaleSensitivity;
	public SelfCheckoutStation selfCheckout;
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
		Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
		db.put(someBarcode, prod);
		
		BarcodeScannerListenerStub stub = new BarcodeScannerListenerStub();
		scannerObject.register(stub);	
		scannerObject.enable();
		scannerObject.scan(someItem);
	}
	
	/*
	public static void main(String[] args) {
		
		// TODO: Refactor all these TODOs into separate classes
		
		// Questions for TA: 1. Where does control software folder go
		// Questions for TA: 2. Do we implement payment calculations
		
		// Aris Test Comment - First Commit
		
		System.out.println("Scan item: ");
		
		// Banknote demoninations, coin denominations, kind of currency, max weight, scale-sensitivity
		try {
			Currency currency = Currency.getInstance("CAD");
			int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
			BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
			int scaleMaximumWeight = 500; // Don't know the units of the scale, will figure out later
			int scaleSensitivity = 1; // Don't know the units also
			
			SelfCheckoutStation selfCheckout = new SelfCheckoutStation(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
			//station = selfCheckout;

	
			BarcodeScanner scannerObject = new BarcodeScanner();
			Barcode someBarcode = new Barcode("1234");
			BarcodedItem someItem = new BarcodedItem(someBarcode, 2.0);
			
			//-----------------------------------------
			BigDecimal bananaPrice = new BigDecimal(2.5);
			BarcodedProduct prod = new BarcodedProduct(someBarcode, "Banana", bananaPrice);
			Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
			db.put(someBarcode, prod);
			//----------------------------------------
			
			BarcodeScannerListenerStub stub = new BarcodeScannerListenerStub();
			scannerObject.register(stub);	
			scannerObject.enable();
			scannerObject.scan(someItem);
			
			//-----------------------------------------------
			
			//BAGGING AREA STUBS AND LISTENERS
			bagMethod(selfCheckout, someItem);
			
			Scanner input = new Scanner(System.in);
			System.out.println("Please insert a coin (type in numeric value): ");
			float coinVal = input.nextFloat();
			
			//Coin payment stubs and listeners
			coinValue = new BigDecimal(coinVal);
			Coin someCoin = new Coin(coinValue, currency);
			coinMethod(selfCheckout, currency, coinDenominations, someCoin);
			
			//Banknote payment stubs and listeners
			//incomplete**
			//banknoteMethod(selfCheckout, currency, banknoteDenominations);
			
		}
		catch (Exception e) {
			if (e instanceof IllegalArgumentException) {
				throw new IllegalArgumentException("Invalid coin value");
			}
		}

		// TODO: Sort payment calculations 
		// TODO: Shopping cart (keeps track of items that are scanned)
		// TODO: print receipt hardware
		
		
	}*/
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public static void setPayment(BigDecimal productPrice) {
		paymentTotal.add(productPrice);
	}
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public BigDecimal getPayment() {
		return this.paymentTotal;
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
	
	public void removeItem(SelfCheckoutStation selfCheckout, BarcodedItem someItem) {
		baggingAreaStub bagAreaStub = new baggingAreaStub();
		selfCheckout.baggingArea.register(bagAreaStub);
		//selfCheckout.baggingArea.enable();
		selfCheckout.baggingArea.remove(someItem);
	}
	
	//Functionality: 
	//@Parameters:
	//@Returns: 
	public static void coinMethod(SelfCheckoutStation selfCheckout, Currency currency, BigDecimal[] coinDenominations, Coin someCoin) throws DisabledException {
		try {
			if (someCoin==null) {
				throw new SimulationException("Null coin entered.");
			}
			boolean validCoinVal = checkCoinVal(someCoin, coinDenominations);
			if (validCoinVal) {
				CoinPaymentStub coinStub = new CoinPaymentStub();
				selfCheckout.coinSlot.register(coinStub);
				selfCheckout.coinSlot.accept(someCoin); 
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
	public static void banknoteMethod(SelfCheckoutStation selfCheckout, Currency currency, int[] banknoteDenominations, Banknote someBanknote) throws OverloadException, DisabledException {
		try {
			boolean validCoinVal = checkBanknoteVal(someBanknote, banknoteDenominations);
			if (validCoinVal) {
				//Banknote someBill = new Banknote(10, currency);
				banknotePaymentStub billStub = new banknotePaymentStub();
				selfCheckout.banknoteInput.register(billStub);
				selfCheckout.banknoteInput.enable();
				selfCheckout.banknoteInput.accept(someBanknote);
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
	
	public static boolean checkBanknoteVal(Banknote someBanknote, int[] banknoteDenominations) {
		//checking if coin value is in list of coin denominations 
		for (int i = 0; i<banknoteDenominations.length; i++) {
			if (banknoteDenominations[i] == someBanknote.getValue()) {
				return true; 
			}
		}
		return false; 
	}

}