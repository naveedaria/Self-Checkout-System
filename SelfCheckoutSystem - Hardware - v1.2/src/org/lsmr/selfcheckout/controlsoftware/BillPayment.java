package org.lsmr.selfcheckout.controlsoftware;

import java.util.Currency;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.BidirectionalChannel;
import org.lsmr.selfcheckout.devices.UnidirectionalChannel;
import org.lsmr.selfcheckout.devices.Acceptor;

public class BillPayment {
	
	// Create Acceptor
	Acceptor billAccept = new Acceptor();
	// Create BanknoteSlot
	boolean inv = true;
	BanknoteSlot billslot = new BanknoteSlot(inv);
	// Create BanknoteStorageUnit
	int maxCapacity = 100;
	BanknoteStorageUnit billStorage = new BanknoteStorageUnit(maxCapacity);
	// Create BidirectionalChannel
	BidirectionalChannel billSource = new BidirectionalChannel(billStorage, billAccept);
	// Create UnidirectionalChannel
	UnidirectionalChannel billSink = new UnidirectionalChannel(billStorage);
	// Create BanknoteValidator
	Currency billCurr;
	int denom[] = {5, 10, 20, 50, 100};
	
	BanknoteValidator validateBill = new BanknoteValidator(billCurr, denom);
	
	
	// Constructor - if needed?
	BillPayment(Banknote bill){
		
	}
	
	//
	
	
}