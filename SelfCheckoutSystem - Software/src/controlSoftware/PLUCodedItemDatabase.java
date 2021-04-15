package controlSoftware;

import java.util.HashMap;
import java.util.Map;

import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;

/**
 * Database for PLU coded items
 * Used for universal retreival of PLU items
 * @author naveed
 *
 */
public class PLUCodedItemDatabase {
	private PLUCodedItemDatabase() {}
	
	public static final Map<PriceLookupCode, PLUCodedItem> PLUCoded_ITEM_DATABASE = new HashMap<>();

}
