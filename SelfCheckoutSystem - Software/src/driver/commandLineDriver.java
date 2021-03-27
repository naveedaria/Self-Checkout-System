package driver;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import controlSoftware.ControlSoftware;

public class commandLineDriver {
	public static void main(String[] args) {
        System.out.println("Self-Checkout Station turning on...");
        System.out.println("Initializing Control Software v.1......");
        
        /*===============================================================
         *                INITIALIZE PRODUCT DATABASE
         *===============================================================*/
        Barcode b1 = new Barcode("1234");
        BarcodedProduct bp1 = new BarcodedProduct(b1, "Banana", new BigDecimal(1.5));
        
        //ProductDatabases.BARCODED_PRODUCT_DATABASE.put(b1, bp1);
        Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;
        db.put(b1, bp1);
        
        System.out.println("The product is: " + db.get(b1).getPrice());
        
        /*===============================================================
         *                INITIALIZE CONTROL SOFTWARE
         *===============================================================*/
        
        // Initialize Control Software
        final Currency c1 = Currency.getInstance("CAD");
    	final int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
    	final BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
    	final int scaleMaximumWeight = 500; // Don't know the units of the scale, will figure out later
    	final int scaleSensitivity = 1; // Don't know the units also
        ControlSoftware controlSoftware = new ControlSoftware(c1, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
        
        System.out.println("Self-checkout is ready! Scan your item...");
        
        /*===============================================================
         *                START OF CONTROL FLOW
         *===============================================================*/
        
         // Hard-coding Test Case #1: User wants to buy 2 bananas
        
        BarcodedItem customerItem1 = new BarcodedItem(b1, 500);
        int customerItem1quantity = 2;
        
        controlSoftware.scanProduct(customerItem1, customerItem1quantity);
        
        
        
        
	}
}
