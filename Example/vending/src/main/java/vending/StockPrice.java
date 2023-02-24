package vending;

/**
 * Esta classe armazenta o estoque de cada item disponível na máquina de vendas.
 * 
 * Ela mantem e atualiza uma matriz com dados sobre os 20 itens. A primeira linha da
 * matriz de inteiros guarda a quantidade em estoque de cada item e a segunda linha 
 * da matriz de números inteiro o respectivo valor de cada item.
 * 
 * 
 * @author Auri Vincenzi
 * 
 */
public class StockPrice {
   final public static int MINSEL = 1; // Índice do primeiro item
   final public static int MAXSEL = 20; // Índice do último item

   private static int[][] quantValor = {
         { 10, 10, 10, 10, 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
               0, 0, 10 },
         { 50, 50, 50, 100, 50, 100, 50, 200, 100, 150, 100, 50, 50, 50, 50,
               50, 50, 100, 200, 200 } };

   /**
    * Este método verifica se um determinado item está disponível para compra.
    * 
    * @param item
    *           - número do item a ser verificado. Deve variar entre 1 e 20.
    * 
    * @return true se o item estiver disponível e falso caso não esteja.
    */
   public static boolean isAvailable(int item) {
      boolean result = false;

      if (isValid(item)) {
         result = StockPrice.quantValor[0][item - 1] != 0;
      }

      return result;
   }

   /**
    * Este método determina a quantidade em estoque de determinado item.
    * 
    * @param item
    *           - número do item a ser verificado. Deve variar entre 1 e 20.
    * 
    * @return quantidade disponível do item. Se o item for invalido retorna -1.
    */
   public static int howMuch(int item) {
      int qtde = -1;
      if (isValid(item)) {
         qtde = StockPrice.quantValor[0][item - 1];
      }

      return qtde;
   }

   /**
    * Este método indica o valor de determinado item.
    * 
    * @param item
    *           - número do item que se deseja descobrir o valor.
    * @return o valor do item consultado ou 0.0f caso o item não exista.
    */
   public static int price(int item) {
      int price = 0;

      if (isValid(item)) {
         price = StockPrice.quantValor[1][item - 1];
      }

      return price;
   }

   /**
    * Este método atualiza a quantidade de itens no estoque, decrementando o
    * contador do item em uma unidade.
    * 
    * @param item
    *           - código do item a ser atualizado. -
    */
   public static void updateStock(int item) {
      if (isValid(item)) {
         if (howMuch(item) > 0) {
            StockPrice.quantValor[0][item - 1]--;
         }
      }
   }

   public static boolean isValid(int item) {
      return ((item >= 1) && (item <= 20));
   }
}
