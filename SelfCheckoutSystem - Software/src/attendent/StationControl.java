package attendent;

import java.math.BigDecimal;
import java.util.Currency;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class StationControl {
	
	private Currency currency;
	private int[] banknoteDenominations;
	private BigDecimal[] coinDenominations;
	private int scaleMaxWeight;
	private int scaleSensitivity;
	public SelfCheckoutStation selfCheckout;

	
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
	
	public void emptyBanknoteStorageUit() {
		selfCheckout.banknoteStorage.unload();
	}
	
}
