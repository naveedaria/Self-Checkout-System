package controlSoftware;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;


//Main Idea: call load, emit sequentially for both CoinDispensers and BanknoteDispensers 
public class DispenseChange {
	private BigDecimal change;
	private Map<BigDecimal, CoinDispenser> coinDispensers;
	private Map<Integer, BanknoteDispenser> banknoteDispensers;
	
	/*Coin[] nickelsLoaded;
	Coin[] dimesLoaded;
	Coin[] quartersLoaded;
	Coin[] looniesLoaded;
	Coin[] tooniesLoaded;
	
	Banknote[] fivesLoaded;
	Banknote[] tensLoaded;
	Banknote[] twentyLoaded;
	Banknote[] fiftyLoaded;
	Banknote[] hundredsLoaded;*/
	
	//Use to keep track of coins to dispense for hardware 
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
	
	public DispenseChange(SelfCheckoutStation selfCheckout, BigDecimal change) throws SimulationException, OverloadException {
		this.change = change; 
		this.coinDispensers = selfCheckout.coinDispensers;
		this.banknoteDispensers = selfCheckout.banknoteDispensers;
		//connect the coin tray to the coin dispenser - DONE in SelfCheckout
		//load the coin dispensers and banknote dispensers - USE loadDispensers method 
		//connect an inverted banknote slot to the banknote dispenser - DONE in SelfCheckout
	}
	
	public void loadDispensers(SelfCheckoutStation selfCheckout, Coin[] nickelsLoaded, Coin[] dimesLoaded, Coin[] quartersLoaded, Coin[] looniesLoaded, Coin[] tooniesLoaded,
			Banknote[] fivesLoaded, Banknote[] tensLoaded, Banknote[] twentyLoaded, Banknote[] fiftyLoaded, Banknote[] hundredLoaded) throws SimulationException, OverloadException {
		try {
			int coinDenominationsCounter = 1;
			for (BigDecimal coinType: selfCheckout.coinDenominations) {
				CoinDispenser aDispenser = selfCheckout.coinDispensers.get(coinType);
				switch(coinDenominationsCounter) {
				case 1:
					aDispenser.load(nickelsLoaded);
					coinDenominationsCounter++;
				case 2:
					aDispenser.load(dimesLoaded);
					coinDenominationsCounter++;
				case 3:
					aDispenser.load(quartersLoaded);
					coinDenominationsCounter++;
				case 4:
					aDispenser.load(looniesLoaded);
					coinDenominationsCounter++;
				case 5: 	
					aDispenser.load(tooniesLoaded);
				}
			}
			
			int banknoteDenominationsCounter = 1;
			for (int banknoteType: selfCheckout.banknoteDenominations) {
				BanknoteDispenser aDispenser = selfCheckout.banknoteDispensers.get(banknoteType);
				switch(banknoteDenominationsCounter) {
				case 1:
					aDispenser.load(fivesLoaded);
					banknoteDenominationsCounter++;
				case 2:
					aDispenser.load(tensLoaded);
					banknoteDenominationsCounter++;
				case 3:
					aDispenser.load(twentyLoaded);
					banknoteDenominationsCounter++;
				case 4:
					aDispenser.load(fiftyLoaded);
					banknoteDenominationsCounter++;
				case 5: 	
					aDispenser.load(hundredLoaded);
				}
			}
		}catch(OverloadException e) {
			throw e;
		}catch(SimulationException e) {
			throw e;
		}
		
	}
	
	public void calculateChangeDenominations() {
		//fill the arrays with number of coins/banknotes of each type to fill
	}
	
	public void dispenseDenominations() throws OverloadException, EmptyException, DisabledException {
		for (Coin nickel: this.nickels) {
			this.coinDispensers.get(new BigDecimal(0.05)).emit();
		}
		for (Coin dime: this.dimes) {
			this.coinDispensers.get(new BigDecimal(0.10)).emit();
		}
		for (Coin quarter: this.quarters) {
			this.coinDispensers.get(new BigDecimal(0.25)).emit();
		}
		for (Coin loonie: this.loonies) {
			this.coinDispensers.get(new BigDecimal(1.00)).emit();
		}
		for (Coin toonie: this.toonies) {
			this.coinDispensers.get(new BigDecimal(2.00)).emit();
		}
		
		
		for (Banknote five: this.fives) {
			this.banknoteDispensers.get(5).emit();
		}
		for (Banknote ten: this.tens) {
			this.banknoteDispensers.get(10).emit();
		}		
		for (Banknote twenty: this.twenty) {
			this.banknoteDispensers.get(20).emit();
		}
		for (Banknote fifty: this.fifty) {
			this.banknoteDispensers.get(50).emit();
		}
		for (Banknote hundred: this.hundreds) {
			this.banknoteDispensers.get(100).emit();
		} 		
	}
	

}
