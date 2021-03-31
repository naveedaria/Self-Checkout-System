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
	int nickels=0;
	int dimes=0;
	int quarters=0;
	int loonies=0;
	int toonies=0;
	
	int fives=0;
	int tens=0;
	int twenty=0;
	int fifty=0;
	int hundreds=0;
	
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
	
	//return value of change at the end - which should be 0
	public BigDecimal calculateChangeDenominations() {
		//increment the relevant counter whenever a specific coin/banknote is required for change
	
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
