package controlSoftware;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.Acceptor;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteSlotListener;

public class banknotePaymentStub implements BanknoteSlotListener, Acceptor{

	boolean isEnabled;
	boolean isDisabled;
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		isEnabled = true;
		isDisabled = false;
		System.out.print("banknote payment enabled \n");
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		isDisabled = true;
		isEnabled = false;
	}

	@Override
	public void accept(Object thing) throws OverloadException, DisabledException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void banknoteInserted(BanknoteSlot slot) {
		// TODO Auto-generated method stub
		System.out.print("banknote inserted \n");
	}

	@Override
	public void banknoteEjected(BanknoteSlot slot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void banknoteRemoved(BanknoteSlot slot) {
		// TODO Auto-generated method stub
		
	}

	public boolean returnEnabled() {
		return isEnabled;
	}

	public boolean returnDisabled() {
		return isDisabled;
	}
}
