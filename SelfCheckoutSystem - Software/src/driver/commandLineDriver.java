package driver;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class commandLineDriver {
	public static void main(String[] args) {
        System.out.println("Self-Checkout Station turning on...");
        System.out.println("Initializing Control Software v.1......");
        
        /*===============================================================
         *                INITIALIZE PRODUCT DATABASE
         *===============================================================*/
        Barcode b1 = new Barcode("1234");
        BarcodedProduct bp1 = new BarcodedProduct(b1, "Banana", new BigDecimal(1.5));
        
        ProductDatabases.BARCODED_PRODUCT_DATABASE.put(b1, bp1);
        
        System.out.println("The product is: " + ProductDatabases.BARCODED_PRODUCT_DATABASE.get(b1).getPrice());
        
        /*===============================================================
         *                INITIALIZE CONTROL SOFTWARE
         *===============================================================*/
        
        // Initialize Control Software
        //controlSoftware cs = new cs
        
        System.out.println("Self-checkout is ready! Scan your item...");
        
        /*===============================================================
         *                START OF CONTROL FLOW
         *===============================================================*/
        
        // 
        //controlSoftware cs = new cs
        
	}
}
