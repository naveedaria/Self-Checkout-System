package controlSoftware;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({SetGetTotalBalanceTest.class, CalculateCoinPaymentTest.class, BaggingAreaTest.class, BanknotePaymentTest.class,
	CoinMethodTest.class, ScanProductTest.class})
public class AllTests {
	
	public static String BASEDIR = "/Users/naveed/git/SENG300Project";


}
