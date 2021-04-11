package controlSoftware;

import java.util.HashMap;
import java.util.Map;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;


public class BarcodedItemDatabase {
	
	private BarcodedItemDatabase() {}
	
	public static final Map<Barcode, BarcodedItem> BARCODED_ITEM_DATABASE = new HashMap<>();


}
