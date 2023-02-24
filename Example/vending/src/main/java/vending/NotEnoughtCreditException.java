package vending;

/**
 * Classe de Exceção para o Dispenser indicar quando o crédito existente não é
 * suficiente para a compra do item.
 */
public class NotEnoughtCreditException extends Exception {

	/**
    * 
    */
   private static final long serialVersionUID = 1L;

   public NotEnoughtCreditException() {
		super();
	}

	public NotEnoughtCreditException(String message) {
		super(message);
	}
}
