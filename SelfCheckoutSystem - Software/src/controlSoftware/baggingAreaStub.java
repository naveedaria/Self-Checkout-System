package controlSoftware;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class baggingAreaStub implements ElectronicScaleListener{
	
	double currentWeight;
	int weightLimit;
	int sensitivity;
	private boolean isEnabled;
	private boolean isDisabled;

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		isEnabled = true;
		isDisabled = false;
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		isDisabled = true;
		isEnabled = false;
	}

	@Override
	public void weightChanged(ElectronicScale scale, double weightInGrams) {
		
	}

	@Override
	public void overload(ElectronicScale scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outOfOverload(ElectronicScale scale) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean returnEnabled() {
		return isEnabled;
	}

	public boolean returnDisabled() {
		return isDisabled;
	}
}
