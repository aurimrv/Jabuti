package vending;

/**
 * Esta classe representa a máquina de vender produtos. Ela implementa uma
 * possível interface de acesso do usuário com a máquina de vender itens.
 * 
 * Nesse caso em particular, a máquina possui uma interface via linha de
 * comando, definida por um conjunto de instruções básicas, definidas a seguir.
 * 
 * @author Auri Marcelo Rizzo Vincenzi
 */
public class VendingMachine {

   private int totValue; // Valor total no cofre da máquina
   private int currValue; // Valor atual depositado
   private Dispenser d;

   /**
    * Construtor da classe, inicializa os contadores e o dispenser da máquina.
    */
   public VendingMachine() {
      totValue = 0;
      currValue = 0;
      d = new Dispenser();
   }

   /**
    * Este método simula que uma nova moeda de determinado valor em centavos foi
    * depositada na máquina. Ele retorna o valor atual já depositado na máquina.
    * 
    * @param valor
    *           - valor inserido em centavos.
    * 
    * @return - o valor total depositado em centavos.
    */
   public int insertCoin(int valor) {
      currValue += valor;
      return currValue;
   }

   /**
    * Este método simula a devolução das moedas ao usuário ou a devolução do
    * troco.
    * 
    * @return - o valor atual em centavos remanescente no porta moeda ou zero
    *         caso não haja saldo a ser devolvido.
    */
   public int returnCoin() {
      int valor = currValue;
      currValue = 0;

      return valor;
   }

   /**
    * Este método simula a requisição de determinado item a ser comprado.
    * 
    * @param selection
    *           o índice do item que se deseja comprar que corresponde a um
    *           número entre 1 e 20.
    * 
    * @return O crédito remanescente após a compra do item no caso de sucesso.
    *         Em caso de falha, uma das exceções abaixo será lançada. A ordem de
    *         prioridade das exceções são:
    * 
    *         <OL TYPE="1">
    *         <LI>NoCoinsException;
    *         <LI>InvalidItemException;
    *         <LI>UnavailableItemException; e
    *         <LI>NotEnoughtCreditException.
    *         </OL>
    * 
    * @throws NoCoinsException
    *            nenhuma moeda foi inserida.
    * @throws InvalidItemException
    *            item selecionado é inválido.
    * @throws UnavailableItemException
    *            item selecionado encontra-se esgotado.
    * @throws NotEnoughtCreditException
    *            crédito insuficiente para a compra do item desejado.
    * 
    */
   public int vendItem(int selection) throws NoCoinsException,
         InvalidItemException, UnavailableItemException,
         NotEnoughtCreditException {
         
         int paied = d.dispense(currValue, selection);
         currValue -= paied;
         
         return currValue; 
   }
} // classe VendingMachine

