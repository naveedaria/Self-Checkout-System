package controlSoftware;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
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
	private SelfCheckoutStation selfCheckout;
	
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
		this.selfCheckout = selfCheckout;
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
	// Note: commented out print statements below are for debugging in future if need be
	public BigDecimal calculateChangeDenominations() {
		nickels = 0; dimes = 0; quarters = 0; loonies = 0; toonies= 0;
		fives = 0; tens = 0; twenty = 0; fifty = 0; hundreds = 0;
		
		// Set change to double
		BigDecimal doubleValueOfChange = change;
		
		// Check how many hundreds fit in change
		// Update hundreds counter
		hundreds = doubleValueOfChange.divide(new BigDecimal("100")).intValue();
		// Modulo/take remainder
		doubleValueOfChange = doubleValueOfChange.remainder(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("amount of hundreds: " + hundreds);
//		System.out.println("change left: " + doubleValueOfChange);
		
		// Check how many fifties fit in change	
		// Update fifties counter
		fifty = doubleValueOfChange.divide(new BigDecimal("50")).intValue();
		// Modulo/take remainder
		doubleValueOfChange = doubleValueOfChange.remainder(new BigDecimal("50")).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("amount of fifties: " + fifty);
//		System.out.println("change left: " + doubleValueOfChange);
		
		// Rest of the code in this method follows same logic 
		// as the previous two cases
		twenty = doubleValueOfChange.divide(new BigDecimal("20")).intValue();
		doubleValueOfChange = doubleValueOfChange.remainder(new BigDecimal("20")).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("amount of twenties: " + twenty);
//		System.out.println("change left: " + doubleValueOfChange);

		tens = doubleValueOfChange.divide(new BigDecimal("10")).intValue();
		doubleValueOfChange = doubleValueOfChange.remainder(new BigDecimal("10")).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("amount of tens: " + tens);
//		System.out.println("change left: " + doubleValueOfChange);

		fives = doubleValueOfChange.divide(new BigDecimal("5")).intValue();
		doubleValueOfChange = doubleValueOfChange.remainder(new BigDecimal("5")).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("amount of fives: " + fives);
//		System.out.println("change left: " + doubleValueOfChange);
					
		toonies = doubleValueOfChange.divide(new BigDecimal("2")).intValue();
		doubleValueOfChange = doubleValueOfChange.remainder(new BigDecimal("2")).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("amount of toonies: " + toonies);
//		System.out.println("change left: " + doubleValueOfChange);
			
		loonies = doubleValueOfChange.divide(new BigDecimal("1")).intValue();
		doubleValueOfChange = doubleValueOfChange.remainder(new BigDecimal("1")).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("amount of loonies: " + loonies);
//		System.out.println("change left: " + doubleValueOfChange);
			
		quarters = doubleValueOfChange.divide(new BigDecimal("0.25")).intValue();
		doubleValueOfChange = doubleValueOfChange.remainder(new BigDecimal("0.25")).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("amount of quarters: " + quarters);
//		System.out.println("change left: " + doubleValueOfChange);
			
		dimes = doubleValueOfChange.divide(new BigDecimal("0.10")).intValue();
		doubleValueOfChange = doubleValueOfChange.remainder(new BigDecimal("0.10")).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("amount of dimes: " + dimes);
//		System.out.println("change left: " + doubleValueOfChange);
			
		nickels = doubleValueOfChange.divide(new BigDecimal("0.05")).intValue();
		doubleValueOfChange = doubleValueOfChange.remainder(new BigDecimal("0.05")).setScale(2, RoundingMode.HALF_UP);
//		System.out.println("amount of nickels: " + nickels);
//		System.out.println("change left: " + doubleValueOfChange);

		// Return 0 as change
		return new BigDecimal(0);
	}
	
	// Method that dispenses the appropriate change in coins and/or banknotes 
	public void dispenseDenominations() throws OverloadException, EmptyException, DisabledException {
		for (int i=0;i<this.nickels; i++) {
			System.out.println("emitted nickels");
			this.coinDispensers.get(new BigDecimal(0.05)).emit();
		}
		for (int i=0;i<this.dimes; i++) {
			System.out.println("emitted dimes");
			this.coinDispensers.get(new BigDecimal(0.10)).emit();
		}
		for (int i=0;i<this.quarters; i++) {
			System.out.println("emitted quarters");
			this.coinDispensers.get(new BigDecimal(0.25)).emit();
		}
		for (int i=0;i<this.loonies; i++) {
			System.out.println("emitted loonies");
			this.coinDispensers.get(new BigDecimal(1.00)).emit();
		}
		for (int i=0;i<this.toonies; i++) {
			System.out.println("emitted toonies");
			this.coinDispensers.get(new BigDecimal(2.00)).emit();
		}
		
		// remove dangling banknote after emitting one banknote if multiple banknotes need to be emitted 
		//		in one transaction.
		for (int i=0;i<this.fives; i++) {
			System.out.println("emitted fives");
			this.banknoteDispensers.get(5).emit();
			selfCheckout.banknoteOutput.removeDanglingBanknote();
		}
		for (int i=0;i<this.tens; i++) {
			System.out.println("emitted tens");
			this.banknoteDispensers.get(10).emit();
			selfCheckout.banknoteOutput.removeDanglingBanknote();
		}		
		for (int i=0;i<this.twenty; i++) {
			System.out.println("emitted twenty");
			this.banknoteDispensers.get(20).emit();
			selfCheckout.banknoteOutput.removeDanglingBanknote();
		}
		for (int i=0;i<this.fifty; i++) {
			System.out.println("emitted fifty");
			this.banknoteDispensers.get(50).emit();
			selfCheckout.banknoteOutput.removeDanglingBanknote();
		}
		for (int i=0;i<this.hundreds; i++) {
			System.out.println("emitted hundreds");
			this.banknoteDispensers.get(100).emit();
			selfCheckout.banknoteOutput.removeDanglingBanknote();
		} 		
	}
	

}
