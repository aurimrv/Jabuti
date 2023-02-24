package vending;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Esta classe é responsável em testar o método isAvailable da classe StockPrice
 * 
 * Ela irá testa se um item, entre 1 e 20, está disponível para compra. Como
 * também irá utilizar a classe Parameterized para realizar os testes com base
 * no Critério Particionamento de Equivalência.
 * 
 * @author Amaral,Rafael, Estevão e Adheli
 * 
 */
@RunWith(Parameterized.class)
public class IsAvailableTestCase {

   private int param;
   private boolean result;

   /**
    * Este método possui os parametros de critério de pasrtição de equivalência
    * o qual a classe Parameterized irá usar.
    * 
    * @return uma lista de caso de teste
    */
   @Parameters
   public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] { 
            { 0, false }, 
            { 1, true },
            { 2, true }, 
            { 19, false }, 
            { 20, true }, 
            { 21, false } });
   }

   public IsAvailableTestCase(int param, boolean resul) {
      this.param = param;
      this.result = resul;
   }

   @Test(timeout = 30)
   public void teste() {
      assertEquals(result, StockPrice.isAvailable(param));
   }

}
