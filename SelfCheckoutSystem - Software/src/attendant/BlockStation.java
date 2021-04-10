package attendant;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class BlockStation {
	boolean isBlocked;
	
	public BlockStation(SelfCheckoutStation station) {
		if (isBlocked) {
		station.scale.disable();
		station.baggingArea.disable();
		station.mainScanner.disable();
		station.handheldScanner.disable();
		station.banknoteInput.disable();
		station.coinSlot.disable();
		station.screen.disable();
		station.cardReader.disable();

		} else {
			station.scale.enable();
			station.baggingArea.enable();
			station.mainScanner.enable();
			station.handheldScanner.enable();
			station.banknoteInput.enable();
			station.coinSlot.enable();
			station.screen.enable();
			station.cardReader.enable();
		}
	}
	
	public boolean isBlocked() {
		return this.isBlocked = true;
	}
	

}
