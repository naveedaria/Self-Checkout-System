package controlSoftware;

import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinDispenserListener;

public class CoinDispenserListenerStub implements CoinDispenserListener{

	private boolean isEnabled;
	private boolean isDisabled;
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		isEnabled = true;
		isDisabled = false;
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		isEnabled = false;
		isDisabled = true;
	}

	@Override
	public void coinsFull(CoinDispenser dispenser) {
		System.out.println("Coin dispenser is full.\n");
		
	}

	@Override
	public void coinsEmpty(CoinDispenser dispenser) {
		System.out.println("Coin dispenser is empty.\n");
		
	}

	@Override
	public void coinAdded(CoinDispenser dispenser, Coin coin) {
		System.out.println("Coin added to dispenser.\n");
		
	}

	@Override
	public void coinRemoved(CoinDispenser dispenser, Coin coin) {
		System.out.println("Coin released from dispenser.\n");
		
	}

	@Override
	public void coinsLoaded(CoinDispenser dispenser, Coin... coins) {
		System.out.println("Coins loaded into dispenser.\n");
		
	}

	@Override
	public void coinsUnloaded(CoinDispenser dispenser, Coin... coins) {
		System.out.println("Coins unloaded from dispenser.\n");
		
	}

	public boolean returnEnabled() {
		return isEnabled;
	}

	public boolean returnDisabled() {
		return isDisabled;
	}
}
