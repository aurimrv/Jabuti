package vending;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HowMuchTestCase {

   private int param;
   private int result;

   public HowMuchTestCase(int param, int result) {
      this.param = param;
      this.result = result;
   }

   @Parameters
   public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] { { 0, -1 }, { 1, 10 }, { 2, 9 },
            { 3, 10 }, { 4, 10 }, { 5, 0 }, { 6, 10 }, { 7, 10 }, { 8, 10 },
            { 9, 10 }, { 10, 10 }, { 11, 10 }, { 12, 10 }, { 13, 10 },
            { 14, 10 }, { 15, 10 }, { 16, 10 }, { 17, 10 }, { 18, 0 },
            { 19, 0 }, { 20, 10 },

      });
   }

   @Test(timeout = 20)
   public void teste() {
      assertEquals(result, StockPrice.howMuch(param));
   }
}
