package controlSoftware;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class ElectronicScaleListenerStub implements ElectronicScaleListener {

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
	public void weightChanged(ElectronicScale scale, double weightInGrams) {
		// TODO Auto-generated method stub
		System.out.println("Detected Weight Changed on electronic scale");
		
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
