package controlSoftware;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({addToScalePromptTest.class, CustomerBagTest.class, BaggingAreaTest.class, DispenseChangeTest.class,
	FinishedAddingItemsTest.class, ScanProductTest.class, RemoveItemFromScaleTest.class, ScanMemberShipCardTest.class,
	ShoppingCartTest.class, SwipeToPayTest.class, TapToPayTest.class, WeighItemTest.class, CoinMethodTest.class, BankNoteMethodTest.class})
public class AllTests {
	
	public static String BASEDIR = "/Users/naveed/git/SENG300Project":


}
