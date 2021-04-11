package attendant;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Scanner;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

import controlSoftware.ControlSoftware;

public class StationControl {
	
	private Currency currency;
	private int[] banknoteDenominations;
	private BigDecimal[] coinDenominations;
	private int scaleMaxWeight;
	private int scaleSensitivity;
	public SelfCheckoutStation selfCheckout;
	double expectedWeight;

	
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
			throw new IllegalArgumentException("Wrong units added");
		}
		selfCheckout.printer.addPaper(units);
	}
	
	public void addInkToStation(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Wrong units added");
		}
		
		selfCheckout.printer.addInk(quantity);
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
