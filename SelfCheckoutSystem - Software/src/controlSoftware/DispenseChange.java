package controlSoftware;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;


//within change class, need to call connect, load, emit sequentially for both CoinDispenser and BanknoteDispenser 
public class DispenseChange {
	BigDecimal change;
	Coin[] nickels;
	Coin[] dimes;
	Coin[] quarters;
	Coin[] loonies;
	Coin[] toonies;
	
	Banknote[] fives;
	Banknote[] tens;
	Banknote[] twenty;
	Banknote[] fifty;
	Banknote[] hundreds;
	
	public DispenseChange(SelfCheckoutStation selfCheckout, BigDecimal change) {
		this.change = change; 
		
		//connect the coin tray to the coin dispenser
		//load the coin dispenser
		
		//connect an inverted banknote slot to the banknote dispenser 
		//load the banknote dispenser 
	}
	
	public void calculateChangeDenominations() {
		
	}
	
	public void dispenseDenominations() {
		//major issue - the dispensers don't track the values of the coins/banknotes !!!
		
	}
	

}
