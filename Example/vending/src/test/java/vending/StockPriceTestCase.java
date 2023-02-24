package vending;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class StockPriceTestCase {

	@Ignore
	@Test(timeout = 20)
	public void verificarComportamentoSeNumItemForVazio() {
		assertEquals(0, StockPrice.price((Integer) null));
	}

	@Test(timeout = 20)
	public void verificarComportamentoSeNumItemForUmAcimaLimiteMaximo() {
		assertEquals(0, StockPrice.price(21));
	}

	@Test(timeout = 20)
	public void VerificarComportamentoSeNumItemForUmAbaixoLimiteMinimo() {
		assertEquals(0, StockPrice.price(0));
	}

	@Test(timeout = 20)
	public void VerificarComportamentoSeNumItemForUmAbaixoLimiteMaximo() {
		assertEquals(200, StockPrice.price(19));
	}

	@Test(timeout = 20)
	public void VerificarComportamentoSeNumItemForUmAcimaLimiteMinimo() {
		assertEquals(50, StockPrice.price(2));
	}

	@Test(timeout = 20)
	public void VerificarComportamentoSeNumItemForEntreValoresValidos() {
		assertEquals(50, StockPrice.price(5));
	}

}