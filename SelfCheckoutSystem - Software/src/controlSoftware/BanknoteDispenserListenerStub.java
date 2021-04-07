package controlSoftware;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteDispenserListener;

public class BanknoteDispenserListenerStub implements BanknoteDispenserListener{

	boolean isEnabled;
	boolean isDisabled;
	
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
	public void banknotesFull(BanknoteDispenser dispenser) {
		System.out.println("Banknote dispenser is full.\n");
		
	}

	@Override
	public void banknotesEmpty(BanknoteDispenser dispenser) {
		System.out.println("Banknote dispenser is empty.\n");
		
	}

	@Override
	public void banknoteAdded(BanknoteDispenser dispenser, Banknote banknote) {
		System.out.println("Banknote added to dispenser.\n");
		
	}

	@Override
	public void banknoteRemoved(BanknoteDispenser dispenser, Banknote banknote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void banknotesLoaded(BanknoteDispenser dispenser, Banknote... banknotes) {
		System.out.println("Banknote released from dispenser.\n");
		
	}

	@Override
	public void banknotesUnloaded(BanknoteDispenser dispenser, Banknote... banknotes) {
		System.out.println("Banknotes unloaded from dispenser.\n");
		
	}

	public boolean returnEnabled() {
		return isEnabled;
	}

	public boolean returnDisabled() {
		return isDisabled;
	}
}
