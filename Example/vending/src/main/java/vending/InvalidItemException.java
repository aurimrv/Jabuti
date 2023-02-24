package vending;

/**
 * Classe de Exceção para o Dispenser indicar quando um item inválido foi
 * selecionado.
 */
public class InvalidItemException extends Exception {

	/**
    * 
    */
   private static final long serialVersionUID = 1L;

   public InvalidItemException() {
		super();
	}

	public InvalidItemException(String message) {
		super(message);
	}
}
