package driver;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import javax.swing.JFrame;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

import controlSoftware.ControlSoftware;

import panels.*;

public class CommandLineDriver {
	
	public static JFrame mainFrame;
	
	public static MainScreen m;
	public static PaymentSelectorScreen pss;
	
	public static CardPaymentScreen card;
	public static CashPaymentScreen cash;
	public static  GiftCardPaymentScreen giftcard;
	public static  LookupItemScreen lookup;
	public static  ThankYouForShoppingScreen thank;
	public static  AttendantMenuScreen attendant;
	
	public static ControlSoftware controlSoftware;
	
	public static void main(String[] args) {
        System.out.println("Self-Checkout Station turning on...");
        System.out.println("Initializing Control Software v.1......");
        
        /*===============================================================
         *                INITIALIZE PRODUCT DATABASE
         *===============================================================*/
        
        Map<Barcode, BarcodedProduct> db = ProductDatabases.BARCODED_PRODUCT_DATABASE;

        
        Barcode b1 = new Barcode("1111");
        BarcodedItem bItem = new BarcodedItem(b1, 5);
        BarcodedProduct bp1 = new BarcodedProduct(b1, "Banana", new BigDecimal(1.00));
        
        Barcode b2 = new Barcode("2222");
        BarcodedItem bItem2 = new BarcodedItem(b2, 5);
        BarcodedProduct bp2 = new BarcodedProduct(b2, "Milk", new BigDecimal(2.50));
        
        Barcode b3 = new Barcode("3333");
        BarcodedItem bItem3 = new BarcodedItem(b3, 5);
        BarcodedProduct bp3 = new BarcodedProduct(b3, "Cereal", new BigDecimal(5.00));
        
        Barcode b4 = new Barcode("4444");
        BarcodedItem bItem4 = new BarcodedItem(b4, 5);
        BarcodedProduct bp4 = new BarcodedProduct(b4, "Wagyu Beef", new BigDecimal(50.00));
        
        Barcode b5 = new Barcode("5555");
        BarcodedItem bItem5 = new BarcodedItem(b5, 5);
        BarcodedProduct bp5 = new BarcodedProduct(b5, "500 Year Old Wine", new BigDecimal(52.00));
        
        
        db.put(b1, bp1);
        db.put(b2, bp2);
        db.put(b3, bp3);
        db.put(b4, bp4);
        db.put(b5, bp5);
        
        
        // System.out.println("The product is: " + db.get(b1).getPrice());
        
        /*===============================================================
         *                INITIALIZE CONTROL SOFTWARE
         *===============================================================*/
        
        // Initialize Control Software
        final Currency c1 = Currency.getInstance("CAD");
    	final int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
    	final BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal(0.05), new BigDecimal(0.10), new BigDecimal(0.25), new BigDecimal(1.00), new BigDecimal(2.00)};
    	final int scaleMaximumWeight = 500; // Don't know the units of the scale, will figure out later
    	final int scaleSensitivity = 1; // Don't know the units also
        controlSoftware = new ControlSoftware(c1, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
        
        System.out.println("Self-checkout is ready! Scan your item...");
        
        /*===============================================================
         *                START OF CONTROL FLOW: Scanning
         *===============================================================*/
        
         // Run of Use-Case #1: User wants to buy 2 bananas
        
        //BarcodedItem customerItem1 = new BarcodedItem(b1, 500);
        //int customerItem1quantity = 1;
        
        //Card card = new Card("Visa", "1234 5678 9102 2212", "Aris", String pin, boolean isTapEnabled, boolean hasChip)
        
        //controlSoftware.scanProduct(customerItem1, customerItem1quantity);
        
        // if scanProduct returns True, it will be added to shopping cart
        // else if scanProduct returns False, it is an item which needs to be weighed.
        
        //double customerItem1weight = controlSoftware.weighItem(customerItem1);
        
        // Remove item from scale. This event should trigger add to shopping cart
        
        
        // If checkout button not pressed, loop and prompt the scan for next item
        // else, invoke logic for payment
        
        
        // Run of Use-case #2: User wants to buy Reese's Pieces (iteration 3)
        
         //PLUCodedItem pluCodedItem1 = new PLUCodedItem(plu1, 100);
         //int customerItem2quantity = 1;
//        
//        controlSoftware.scanProductUsingPLUCode(pluCodedItem1, customerItem2quantity);
        
        // Run of Use-case #3: User wants to look up the price via barcode
        
        //controlSoftware.lookupProductUsingBarcode(customerItem1);
        
        // Run of Use-case #3: User wants to look up the price via barcode
        
        //controlSoftware.lookup.lookupUsingPLU(plu1);
        
        
        /*===============================================================
         *                START OF CONTROL FLOW: Checkout/Payment (TODO)
         *===============================================================*/
        
         
        
        
        /*===============================================================
         *                END OF CONTROL FLOW: Print Receipt (TODO)
         *===============================================================*/
        
        
        m = new MainScreen();
        pss = new PaymentSelectorScreen();
        card = new CardPaymentScreen();;
        cash = new CashPaymentScreen();
        giftcard = new GiftCardPaymentScreen();   
        lookup = new LookupItemScreen();
        thank = new ThankYouForShoppingScreen();
        attendant = new AttendantMenuScreen();
        
        mainFrame = controlSoftware.selfCheckout.screen.getFrame();
        mainFrame.setVisible(true);
        mainFrame.setSize(600,500);
        mainFrame.setContentPane(m);
        mainFrame.pack();
       
        controlSoftware.selfCheckout.screen.setVisible(true);
        
       
        
        
	}
	
	public static void goToScreen(String idx) {
		if(idx == "main") { //Main Screen
			
			mainFrame.setContentPane(m);
			mainFrame.pack();
		} 
		if(idx == "pay") { //PaymentSelectorScreen
			//mainFrame.removeAll();
			mainFrame.setContentPane(pss);
			mainFrame.pack();
		}
		if (idx == "card") { 
			mainFrame.setContentPane(card);
			mainFrame.pack();
		}
		if (idx == "cash") { 
			mainFrame.setContentPane(cash);
			mainFrame.pack();
		}
		if (idx == "giftcard") { 
			mainFrame.setContentPane(giftcard);
			mainFrame.pack();
		}
		if (idx == "lookup") { 
			mainFrame.setContentPane(lookup);
			mainFrame.pack();
		}
		if (idx == "thank") { 
			mainFrame.setContentPane(thank);
			mainFrame.pack();
		}
		if (idx == "attendant") { 
			mainFrame.setContentPane(attendant);
			mainFrame.pack();
		}
	}
}