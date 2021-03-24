package controlSoftware;

import org.lsmr.selfcheckout.devices.CardReader;

public class PaymentByCard {

	public void PaymentByCard() {
		//register the CardReaderListenerStub 
		CardReaderListenerStub cardReaderListener = new CardReaderListenerStub();
		CardReader cardReader = new CardReader();
		cardReader.register(cardReaderListener);
	}
}
