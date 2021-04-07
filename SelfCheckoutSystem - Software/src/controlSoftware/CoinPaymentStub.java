package controlSoftware;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.Acceptor;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinSlotListener;

public class CoinPaymentStub implements CoinSlotListener, Acceptor{

	boolean isEnabled;
	boolean isDisabled;
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		isEnabled = true;
		isDisabled = false;
//		System.out.print("coin payment enabled \n");
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		isEnabled = false;
		isDisabled = true;
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
	public void coinInserted(CoinSlot slot) {
		// TODO Auto-generated method stub
//		System.out.print("coin inserted \n");
	}

	public boolean returnEnabled() {
		return isEnabled;
	}

	public boolean returnDisabled() {
		return isDisabled;
	}
}
