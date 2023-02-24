package vending;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

/**
 * The Class DispenserTest is a Junit Test Case for Dispenser.class
 * @author Adheli
 */
public class DispenserTest {
	
	/** The dispenser. */
	private Dispenser dispenser;
	
	/**
	 * Inits the dispenser before the test begins.
	 */
	@Before
	public void init() {
		dispenser = new Dispenser();
	}

	/**
	 * Test for a valid dispense.
	 * 
	 * @param 50 = coins
	 * @param  2 = item from StockPrice.class
	 *
	 * @throws NoCoinsException the no coins exception
	 * @throws InvalidItemException the invalid item exception
	 * @throws UnavailableItemException the unavailable item exception
	 * @throws NotEnoughtCreditException the not enough credit exception
	 */
	@Test
	public final void testDispenseValid() throws NoCoinsException, InvalidItemException, 
	UnavailableItemException, NotEnoughtCreditException {
		assertTrue(dispenser.dispense(50, 2) == 50);
	}

	/**
	 * Test for dispense with not enough credit, throwing a NotEnoughtCreditException.
	 * 
	 * @param  5 = coins
	 * @param 14 = item from StockPrice.class
	 * 
	 * @throws NoCoinsException the no coins exception
	 * @throws InvalidItemException the invalid item exception
	 * @throws UnavailableItemException the unavailable item exception
	 * @throws NotEnoughtCreditException the not enough credit exception
	 */
	@Test(expected=NotEnoughtCreditException.class)
	public final void testDispenseNotEnoughtCreditException() throws NoCoinsException, InvalidItemException, 
	UnavailableItemException, NotEnoughtCreditException {
		dispenser.dispense(5, 14);
	}
	
	/**
	 * Test for dispense without coins, throwing a NoCoinsException.
	 * @param 0 = coins
	 * @param 5 = item from StockPrice.class
	 *
	 * @throws NoCoinsException the no coins exception
	 * @throws InvalidItemException the invalid item exception
	 * @throws UnavailableItemException the unavailable item exception
	 * @throws NotEnoughtCreditException the not enough credit exception
	 */
	@Test(expected=NoCoinsException.class)
	public final void testDispenseNoCoinsException() throws NoCoinsException, InvalidItemException, 
	UnavailableItemException, NotEnoughtCreditException {
		dispenser.dispense(0, 5);
	}
	
	/**
	 * Test for dispense with an invalid item, throwing an InvalidItemException.
	 * @param 50 = coins
	 * @param 29 = item that doesn't exist in StockPrice.class
	 * 
	 * @throws NoCoinsException the no coins exception
	 * @throws InvalidItemException the invalid item exception
	 * @throws UnavailableItemException the unavailable item exception
	 * @throws NotEnoughtCreditException the not enough credit exception
	 */
	@Test(expected=InvalidItemException.class)
	public final void testDispenseInvalidItemException() throws NoCoinsException, InvalidItemException, 
	UnavailableItemException, NotEnoughtCreditException {
		dispenser.dispense(50, 29);
	}
	
	/**
	 * Test for dispense with a item with zero stock, throwing an UnavailableItemException.
	 * @param 50 = coins
	 * @param  5 = item from StockPrice.class
	 * @throws NoCoinsException the no coins exception
	 * @throws InvalidItemException the invalid item exception
	 * @throws UnavailableItemException the unavailable item exception
	 * @throws NotEnoughtCreditException the not enough credit exception
	 */
	@Test(expected=UnavailableItemException.class)
	public final void testDispenseUnavailableItemException() throws NoCoinsException, InvalidItemException, 
	UnavailableItemException, NotEnoughtCreditException {
		dispenser.dispense(50, 5);
	}
}
