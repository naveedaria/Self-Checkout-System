package controlSoftware;

import java.util.Currency;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.Acceptor;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.BidirectionalChannel;
import org.lsmr.selfcheckout.devices.UnidirectionalChannel;

public class billPayment {

	//===============================================================================================
	// Initialization of 
	//===============================================================================================
	// Create Acceptor
//	Acceptor billAccept = new Acceptor();
	// Create BanknoteSlot
	BanknoteSlot billslot = new BanknoteSlot(false);
	// Create BanknoteStorageUnit
	int maxCapacity = 1000;
	BanknoteStorageUnit billStorage = new BanknoteStorageUnit(maxCapacity);
	// Create BidirectionalChannel
//	BidirectionalChannel billSource = new BidirectionalChannel(billStorage, billAccept);
	// Create UnidirectionalChannel
	UnidirectionalChannel billSink = new UnidirectionalChannel(billStorage);
	// Create BanknoteValidator
	Currency billCurr;
	int denom[] = {5, 10, 20, 50, 100};
	
	BanknoteValidator validateBill = new BanknoteValidator(billCurr, denom);
	
	
	
}
