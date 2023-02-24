package vending;

/**
 * Esta classe representa uma possível implementação do componente responsável
 * pela entrega do item comprado. Para que a entrega do item se concretize,
 * algumas restrições precisam ser satisfeitas. Tais condições são descritas
 * abaixo.
 * 
 * @author Auri Marcelo Rizzo Vincenzi
 */
public class Dispenser {
   /**
    * Simula o comportamento do componente que faz a entrega de determinado item
    * em uma máquina de venda. Um item é entregue quando uma quantidade de
    * créditos suficiente para a sua compra estiver disponível, o código do item
    * ser um código válido e o item estiver disponível para compra (não estiver
    * esgotado).
    * 
    * @param credit
    *           - o valor atual em centavos do crédito disponível no
    *           compartimento de moedas. Um valor do item a ser adquirido. O
    *           estoque da máquina (classe {@link StockPrice}) armazena a quantidade
    *           e o valor dos 20 itens que podem ser vendidos pela máquina.
    * 
    * @param sel
    *           - o índice do item selecionado para compra. Um item válido tem
    *           um índice representado por um número inteiro variando de 1 a 20.
    *           Observa-se que embora o índice seja válido, pode ser que o item
    *           em questão esteja esgotado e não possa ser vendido.
    * 
    * @return O valor pago pelo item desejado.
    * 
    *         Em caso de erro, uma exceção é lançada, indicando o erro ocorrido
    *         conforme descrito abaixo:
    * 
    * @throws NoCoinsException
    *            nenhuma moeda foi inserida.
    * @throws InvalidItemException
    *            item selecionado é inválido.
    * @throws UnavailableItemException
    *            item selecionado encontra-se esgotado.
    * @throws NotEnoughtCreditException
    *            crédito insuficiente para a compra do item desejado.
    */
   public int dispense(int credit, int sel) throws NoCoinsException,
         InvalidItemException, UnavailableItemException,
         NotEnoughtCreditException {
      
      int preco=0;
      
      if (credit == 0) {
         throw new NoCoinsException();
      } else if (StockPrice.isValid(sel) == false) {
         throw new InvalidItemException();
      } else if (StockPrice.isAvailable(sel) == false) {
         throw new UnavailableItemException(); 
      } else if (credit < StockPrice.price(sel)) {
         throw new NotEnoughtCreditException();
      } else {
         preco = StockPrice.price(sel);
         StockPrice.updateStock(sel);
      }
      return preco;
   }
} // classe Dispenser
