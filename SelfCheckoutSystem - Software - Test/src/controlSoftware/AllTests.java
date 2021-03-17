package controlSoftware;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({BaggingAreaTest.class, BanknotePaymentTest.class, CalculateCoinPaymentTest.class,
	CoinMethodTest.class, ScanProductTest.class, SetGetTotalBalanceTest.class})
public class AllTests {
	
	public static String BASEDIR = "/Users/naveed/git/SENG300Project";


}
