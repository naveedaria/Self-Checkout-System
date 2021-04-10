package attendant;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

public class AttendantLookup {

	
	/**
	 * 
	 * @param plucode
	 * 		pluCode to search products
	 * @return
	 * 		returns the Product 
	 */		
	public PLUCodedProduct lookupUsingPLU(PriceLookupCode plucode) {
		if (plucode == null) {
			throw new NullPointerException();
		}
		
		return ProductDatabases.PLU_PRODUCT_DATABASE.get(plucode);
		
	}
	
	/**
	 * 
	 * @param barcode
	 * 		barcode of product to lookup
	 * @return
	 * 		return (if exists) product
	 */
	public BarcodedProduct lookupUsingPLU(Barcode barcode) {
		if (barcode == null) {
			throw new NullPointerException();
		}
		
		return ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
		
	}
}
