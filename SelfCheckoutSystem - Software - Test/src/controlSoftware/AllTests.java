package controlSoftware;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ addToScalePromptTest.class, AttendantLogIn_OutTest.class, BaggingAreaTest.class,
		BankNoteMethodTest.class, CoinMethodTest.class, ControlSoftTest.class, CustomerBagTest.class,
		DispenseChangeTest.class, FinishedAddingItemsTest.class, PaymentByGiftcardTest.class, receiptTest.class,
		RemoveItemFromScaleTest.class, ScanMemberShipCardTest.class, ScanProductTest.class, ShoppingCartTest.class,
		StationControlTest.class, SwipeToPayTest.class, TapToPayTest.class, WeighItemTest.class })

public class AllTests {

	public static String BASEDIR = "/Users/naveed/git/SENG300Project";

}
