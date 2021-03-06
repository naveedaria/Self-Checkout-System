package controlSoftware;

import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CardReaderListener;

public class CardReaderListenerStub implements CardReaderListener{

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		// TODO Auto-generated method stub
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cardInserted(CardReader reader) {
		System.out.println("Card was inserted.\n");
		
	}

	@Override
	public void cardRemoved(CardReader reader) {
		System.out.println("Card was removed.\n");
		
	}

	@Override
	public void cardTapped(CardReader reader) {
		System.out.println("Card was tapped.\n");
		
	}

	@Override
	public void cardSwiped(CardReader reader) {
		System.out.println("Card was swiped.\n");
		
	}

	@Override
	public void cardDataRead(CardReader reader, CardData data) {
		System.out.println("Card data was read.\n");
		
	}

}
