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
	int nickels = 0, dimes = 0, quarters = 0, loonies = 0, toonies= 0;

	//Use to keep track of bank notes to dispense for hardware 
	int fives = 0, tens = 0, twenty = 0, fifty = 0, hundreds = 0;

	public DispenseChange(SelfCheckoutStation selfCheckout, BigDecimal change) throws SimulationException, OverloadException {
		this.change = change; 
		this.coinDispensers = selfCheckout.coinDispensers;
		this.banknoteDispensers = selfCheckout.banknoteDispensers;
		
		//register listeners for each dispenser 
		for (BigDecimal coinType: selfCheckout.coinDenominations) {
			CoinDispenserListenerStub coinDispenserListener = new CoinDispenserListenerStub();
			selfCheckout.coinDispensers.get(coinType).register(coinDispenserListener);
		}
		
		for (int banknoteType: selfCheckout.banknoteDenominations) {
			BanknoteDispenserListenerStub banknoteDispenserListener = new BanknoteDispenserListenerStub();
			selfCheckout.banknoteDispensers.get(banknoteType).register(banknoteDispenserListener);
		}	
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
	
	// Returns value of change at the end - which should be big decimal 0
	// Method counts the relevant denomination whenever a specific coin/ bank-note is required for change
	public BigDecimal calculateChangeDenominations() {
		// Changed the change into double
		double doubleValueOfChange = change.doubleValue();
		// If greater or equal to 5 dollars 
		if(doubleValueOfChange >= 5.0) {
		// Check how many hundreds fit in change
		// Update hundreds counter
			hundreds = (int) doubleValueOfChange / 100;
		// Modulo/ take remainder
			doubleValueOfChange %= 100;
		// Check how many fifties fit in change	
		// Update fifties counter
			fifty = (int) doubleValueOfChange / 50;
		// Modulo/ take remainder
			doubleValueOfChange %= 50;
		// Rest of the code in this method follows same logic 
		// as the previous two cases
			twenty = (int) doubleValueOfChange / 20;
			doubleValueOfChange %= 20;
			
			tens = (int) doubleValueOfChange / 10;
			doubleValueOfChange %= 10;
			
			fives = (int) doubleValueOfChange / 5;
			doubleValueOfChange %= 5;
					
		}else if(doubleValueOfChange < 5.0){
			toonies = (int) doubleValueOfChange / 2;
			doubleValueOfChange %= 2;
			
			loonies = (int) doubleValueOfChange / 1;
			doubleValueOfChange %= 1;
			
			quarters = (int) (doubleValueOfChange / 0.25);
			doubleValueOfChange %= 0.25;
			
			dimes = (int) (doubleValueOfChange / 0.10);
			doubleValueOfChange %= 0.10;
			
			nickels = (int) (doubleValueOfChange / 0.05);
			doubleValueOfChange %= 0.05;
		}
		// Return 0 as change
		return new BigDecimal(0);
	}
	
	public void dispenseDenominations() throws OverloadException, EmptyException, DisabledException {
		for (int i=0;i<this.nickels; i++) {
			this.coinDispensers.get(new BigDecimal(0.05)).emit();
		}
		for (int i=0;i<this.dimes; i++) {
			this.coinDispensers.get(new BigDecimal(0.10)).emit();
		}
		for (int i=0;i<this.quarters; i++) {
			this.coinDispensers.get(new BigDecimal(0.25)).emit();
		}
		for (int i=0;i<this.loonies; i++) {
			this.coinDispensers.get(new BigDecimal(1.00)).emit();
		}
		for (int i=0;i<this.toonies; i++) {
			this.coinDispensers.get(new BigDecimal(2.00)).emit();
		}
		
		
		for (int i=0;i<this.fives; i++) {
			this.banknoteDispensers.get(5).emit();
		}
		for (int i=0;i<this.tens; i++) {
			this.banknoteDispensers.get(10).emit();
		}		
		for (int i=0;i<this.twenty; i++) {
			this.banknoteDispensers.get(20).emit();
		}
		for (int i=0;i<this.fifty; i++) {
			this.banknoteDispensers.get(50).emit();
		}
		for (int i=0;i<this.hundreds; i++) {
			this.banknoteDispensers.get(100).emit();
		} 		
	}
	

}
