package vending;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HowMuchTestCase.class, DispenserTest.class, IsAvailableTestCase.class, StockPriceTestCase.class,
		UpdateStockTesteCase.class })
public class AllTests {

}
