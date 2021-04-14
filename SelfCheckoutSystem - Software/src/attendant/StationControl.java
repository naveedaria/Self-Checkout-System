package attendant;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Scanner;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ReceiptPrinterListener;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

import controlSoftware.ControlSoftware;
import panels.MainScreen;

public class StationControl extends AttendantLogIn_Out{
	
	private Currency currency;
	private int[] banknoteDenominations;
	private BigDecimal[] coinDenominations;
	private int scaleMaxWeight;
	private int scaleSensitivity;
	public SelfCheckoutStation selfCheckout;
	double expectedWeight;
	int inkQuantity;
	int paperQuantity;
	

	private ReceiptPrinterListener receiptListener = new ReceiptPrinterListener () {

		private boolean enabled = false;
		private int inkQuantity;
		private int paperQuantity;
		
		public void setinkQuantity(int inkQuantity) {
			this.inkQuantity = inkQuantity;
		}
		
		public void setPaperQuantity(int paperQuantity) {
			this.paperQuantity = paperQuantity;
		}

		@Override
		public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			// TODO Auto-generated method stub
			enabled   = true;
			
		}

		@Override
		public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			// TODO Auto-generated method stub
			enabled   = false;
		}

		@Override
		public void outOfPaper(ReceiptPrinter printer) {
			// TODO Auto-generated method stub
			
			// prompt the attendant panel
			// maybe block station
			
			System.out.println("Station is out of receipt paper.");
			
			addPaperToStation(paperQuantity);
		}

		@Override
		public void outOfInk(ReceiptPrinter printer) {
			// TODO Auto-generated method stub
			
			// prompt the attendant panel
			// maybe block station
			
			System.out.println("Station is out of receipt ink.");
			selfCheckout.printer.addInk(inkQuantity);
		}

		@Override
		public void paperAdded(ReceiptPrinter printer) {
			// TODO Auto-generated method stub
			
			// Reset the paperCounter
			// Enable the station
			
		}

		@Override
		public void inkAdded(ReceiptPrinter printer) {
			// TODO Auto-generated method stub
			
			// Reset the InkVolume
			// Enable the station
		}
		
		
	};

	
	public void turnOnStation(Currency currency, int[] banknoteDenominations, BigDecimal[] coinDenominations, int scaleMaxWeight, int scaleSensitivity) {
		this.currency = currency;
		this.banknoteDenominations = banknoteDenominations;
		this.coinDenominations = coinDenominations;
		this.scaleMaxWeight = scaleMaxWeight;
		this.scaleSensitivity = scaleSensitivity; 
		
		this.selfCheckout = new SelfCheckoutStation(this.currency, this.banknoteDenominations, this.coinDenominations, this.scaleMaxWeight, this.scaleSensitivity);
	}
	
	public void turnOffStation(SelfCheckoutStation selfCheckout) {
		
		this.selfCheckout = null;
		
	}
	
	public void addPaperToStation(int units) {
		if (units < 0) {
			MainScreen.paperLevel += units;
			MainScreen.paperLabel.setText("Paper Level: " + MainScreen.paperLevel);
			this.paperQuantity += units;
			throw new IllegalArgumentException("Wrong units added");
		}
		//selfCheckout.printer.addPaper(units);
	}
	
	public void addInkToStation(int quantity) {
		if (quantity < 0) {
			MainScreen.inkLevel += quantity;
			//MainScreen.inkLabel.setText("Ink Level: " + MainScreen.inkLevel);
			this.inkQuantity += quantity;
			throw new IllegalArgumentException("Wrong units added");
		}
		
		//selfCheckout.printer.addInk(quantity);
	}
	
	public void emptyCoinStorageUnit() {
		selfCheckout.coinStorage.unload();
	}
	
	public void emptyBanknoteStorageUnit() {
		selfCheckout.banknoteStorage.unload();
	}
	
	public void attendantRemoveItem(ControlSoftware cs, BarcodedItem item, int quantity) {
		cs.shoppingCart.removeFromShoppingCart(item, quantity);
	}
	
	public boolean attendantApproveWeight() throws OverloadException {
		//Attendant input
		AttendantLogIn_Out attendant = new AttendantLogIn_Out();


//		Scanner s = new Scanner(System.in); 
		//manually approve heavier bags by entering attendant ID and Password(default "12345678" and "12345678")
   		System.out.println("Enter Attendent ID"); 
   		String ID = "Bob";
   		System.out.println("Enter Attendent Password"); 
   		String passWord = "hunter2";
   		
   		AttendantProfile profile = new AttendantProfile(ID, passWord);
   		
   		
	//use logIn method to check if correct username/password is given
   		if ((attendant.logIn(ID, passWord)) == true){
   			//update expected weight if inputs are correct(manually approve weight discrepancy)
				expectedWeight = selfCheckout.scale.getCurrentWeight();
				return true;
			}	
   		else {
   			//Call this method again for attendant to enter username/password
   			System.out.println("Wrong password, try again");
   			return false;
   			}
		
   		}
	
	
	
}
