package vending;

import static org.junit.Assert.*;

import org.junit.Test;

public class UpdateStockTesteCase {
 
 @Test
 public void updateStock() {
	 int quantValor = StockPrice.howMuch(2);
	 StockPrice.updateStock(2);
	 assertEquals(quantValor - 1, StockPrice.howMuch(2));
 }

 @Test
 public void updateStock2() {
    StockPrice.updateStock(5);
    assertEquals(0, StockPrice.howMuch(5));
 }

 @Test
 public void updateStock3() {
    StockPrice.updateStock(-1);
    assertEquals(-1, StockPrice.howMuch(-1));
 }

 @Test
 public void updateStock4() {
    int qtde = StockPrice.howMuch(20);
    for (int i = 0; i <= qtde; i++) {
       StockPrice.updateStock(20);
    }
    assertTrue(StockPrice.howMuch(20) == 0);
 }
 
 @Test
 public void updateStock5() {
    int qtde = StockPrice.howMuch(5);
    for (int i = 0; i < qtde; i++) {
       StockPrice.updateStock(5);
    }
    assertTrue(StockPrice.howMuch(5) == 0);
 }
 
 @Test
 public void updateStock6() {
    int qtde = StockPrice.howMuch(3);
    for (int i = 0; i < qtde-1; i++) {
       StockPrice.updateStock(3);
    }
    assertTrue(StockPrice.howMuch(3) == 1);
 } 
}