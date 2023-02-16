/*
 * Created on 12/09/2011
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package cal;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalTest {
private Cal cl;
static private ByteArrayOutputStream baOut, baErr;
static private PrintStream psOut, psErr;
	
   @BeforeClass
   static public void beforeClassInit() {
		baOut = new ByteArrayOutputStream();
		psOut = new PrintStream(baOut);
		System.setOut(psOut); 
		baErr = new ByteArrayOutputStream();
		psErr = new PrintStream(baErr);
	    System.setErr(psErr);
   }

   @AfterClass
   static public void afterClassFinalize() {
	   psErr.close();
	   psOut.close();
   }
   

	@Before
	public void setUp()
	{
		cl = new Cal();
		baOut.reset();
		baErr.reset();
	}
	
	@Test
	public void testMain01() {
		Cal.main(new String[] {"0", "1963"});
		String s = baErr.toString();
		assertEquals("Cal: 0: mes invalido.\n", s); 
	}

	@Test
	public void testMain02() {
		Cal.main(new String[] {"8", "10000"});
		String s = baErr.toString();
		assertEquals("Cal: 10000: ano invalido.\n", s); 
	}

	@Test
	public void testMain03() {
		Cal.main(new String[] {"8", "1963"});
		String s = baOut.toString();
		assertEquals("   Agosto 1963\nDo Se Te Qa Qi Se Sa\n             1  2  3\n 4  5  6  7  8  9 10\n"+
				     "11 12 13 14 15 16 17\n18 19 20 21 22 23 24\n25 26 27 28 29 30 31\n\n", s); 
	}
	
	@Test
	public void testMain04() {
		Cal.main(new String[] {"1963"});
		String s = baOut.toString();
		assertTrue(s.indexOf("   Agosto 1963\nDo Se Te Qa Qi Se Sa\n             1  2  3\n 4  5  6  7  8  9 10\n"+
				     "11 12 13 14 15 16 17\n18 19 20 21 22 23 24\n25 26 27 28 29 30 31\n\n") >= 0); 
	}
	
	@Test
	public void testMain05() {
		Cal.main(new String[] {"8", "1963", "abc"});
		String s = baOut.toString();
		assertEquals("   Agosto 1963\nDo Se Te Qa Qi Se Sa\n             1  2  3\n 4  5  6  7  8  9 10\n"+
				     "11 12 13 14 15 16 17\n18 19 20 21 22 23 24\n25 26 27 28 29 30 31\n\n", s); 
	}

	// Teste do método firstOfMonth
	// os casos de teste devem retornar cada um dos resultados 0,1,2...6
	// para os meses de janeiro e de dezembro. os anos vão variando para
	// se obter esses resultados
	@Test
	public void testFirstOfMonth01() {
		assertEquals(6,cl.firstOfMonth(1, 1));
	}
	@Test
	public void testFirstOfMonth02() {
		assertEquals(4,cl.firstOfMonth(12, 1));
	}
	@Test
	public void testFirstOfMonth03() {
		assertEquals(0,cl.firstOfMonth(1, 2));
	}
	@Test
	public void testFirstOfMonth04() {
		assertEquals(5,cl.firstOfMonth(12, 2));
	}
	@Test
	public void testFirstOfMonth05() {
		assertEquals(1,cl.firstOfMonth(1, 3));
	}
	@Test
	public void testFirstOfMonth06() {
		assertEquals(6,cl.firstOfMonth(12, 3));
	}
	@Test
	public void testFirstOfMonth07() {
		assertEquals(2,cl.firstOfMonth(1, 4));
	}
	@Test
	public void testFirstOfMonth08() {
		assertEquals(1,cl.firstOfMonth(12, 4));
	}
	@Test
	public void testFirstOfMonth09() {
		assertEquals(4,cl.firstOfMonth(1, 5));
	}
	@Test
	public void testFirstOfMonth10() {
		assertEquals(2,cl.firstOfMonth(12, 5));
	}
	@Test
	public void testFirstOfMonth11() {
		assertEquals(5,cl.firstOfMonth(1, 6));
	}
	@Test
	public void testFirstOfMonth12() {
		assertEquals(3,cl.firstOfMonth(12, 6));
	}
	@Test
	public void testFirstOfMonth13() {
		assertEquals(3,cl.firstOfMonth(1, 10));
	}
	@Test
	public void testFirstOfMonth14() {
		assertEquals(0,cl.firstOfMonth(12, 9));
	}

	// teste do método numberOfDays
	// usa cada um dos meses do ano, alem de um mes bissexto e 
	// o mes de mudanca do calendário
	@Test
	public void testNumberOfDays01() {
		assertEquals(31, cl.numberOfDays(1, 1963));
	}
	@Test
	public void testNumberOfDays02() {
		assertEquals(28, cl.numberOfDays(2, 1963));
	}
	@Test
	public void testNumberOfDays03() {
		assertEquals(31, cl.numberOfDays(3, 1963));
	}
	@Test
	public void testNumberOfDays04() {
		assertEquals(30, cl.numberOfDays(4, 1963));
	}
	@Test
	public void testNumberOfDays05() {
		assertEquals(31, cl.numberOfDays(5, 1963));
	}
	@Test
	public void testNumberOfDays06() {
		assertEquals(30, cl.numberOfDays(6, 1963));
	}
	@Test
	public void testNumberOfDays07() {
		assertEquals(31, cl.numberOfDays(7, 1963));
	}
	@Test
	public void testNumberOfDays08() {
		assertEquals(31, cl.numberOfDays(8, 1963));
	}
	@Test
	public void testNumberOfDays09() {
		assertEquals(30, cl.numberOfDays(9, 1963));
	}
	@Test
	public void testNumberOfDays10() {
		assertEquals(31, cl.numberOfDays(10, 1963));
	}
	@Test
	public void testNumberOfDays11() {
		assertEquals(30, cl.numberOfDays(11, 1963));
	}
	@Test
	public void testNumberOfDays12() {
		assertEquals(31, cl.numberOfDays(12, 1963));
	}
	@Test
	public void testNumberOfDays13() {
		assertEquals(29, cl.numberOfDays(2, 1972));
	}
	@Test
	public void testNumberOfDays14() {
		assertEquals(19, cl.numberOfDays(9, 1752));
	}

	// Casos de teste para metodo is Leap
	@Test
	public void testIsLeap01() {
		assertTrue(cl.isLeap(4));
	}
	@Test
	public void testIsLeap02() {
		assertTrue(cl.isLeap(1752));
	}
	@Test
	public void testIsLeap03() {
		assertTrue(cl.isLeap(9996));
	}
	@Test
	public void testIsLeap04() {
		assertTrue(cl.isLeap(1756));
	}
	@Test
	public void testIsLeap05() {
		assertFalse(cl.isLeap(1800));
	}
	@Test
	public void testIsLeap06() {
		assertTrue(cl.isLeap(2000));
	}
	@Test
	public void testIsLeap07() {
		assertFalse(cl.isLeap(9900));
	}
	@Test
	public void testIsLeap08() {
		assertTrue(cl.isLeap(9600));
	}

	@Test
	public void testIsLeap09() {
		assertFalse(cl.isLeap(1));
	}
	@Test
	public void testIsLeap10() {
		assertFalse(cl.isLeap(1));
	}
	@Test
	public void testIsLeap11() {
		assertFalse(cl.isLeap(1751));
	}
	@Test
	public void testIsLeap12() {
		assertFalse(cl.isLeap(1753));
	}
	@Test
	public void testIsLeap13() {
		assertFalse(cl.isLeap(9999));
	}

	// teste do metodo jan1
	// 
	@Test
	public void testJan101() {
		assertEquals(6,cl.jan1(1));
	}
	@Test
	public void testJan102() {
		assertEquals(0,cl.jan1(2));
	}
	@Test
	public void testJan103() {
		assertEquals(1,cl.jan1(3));
	}
	@Test
	public void testJan104() {
		assertEquals(2,cl.jan1(4));
	}
	@Test
	public void testJan105() {
		assertEquals(4,cl.jan1(5));
	}
	@Test
	public void testJan106() {
		assertEquals(5,cl.jan1(6));
	}
	@Test
	public void testJan107() {
		assertEquals(6,cl.jan1(7));
	}
	@Test
	public void testJan108() {
		assertEquals(0,cl.jan1(8));
	}
	@Test
	public void testJan109() {
		assertEquals(2,cl.jan1(9));
	}
	@Test
	public void testJan111() {
		assertEquals(3,cl.jan1(1752));
	}
	@Test
	public void testJan112() {
		assertEquals(1,cl.jan1(1753));
	}
	@Test
	public void testJan113() {
		assertEquals(3,cl.jan1(1800));
	}
	@Test
	public void testJan114() {
		assertEquals(4,cl.jan1(1801));
	}
	
	
	// teste do metodo Cal
	// recebe como parametro o primeiro dia da semana
	// do mes e o numero de dias do mes
	@Test
	public void testCal01() {
		assertEquals("       1  2 14 15 16\n17 18 19 20 21 22 23\n24 25 26 27 28 29 30",
				     cl.cal(0, 19));
	}
	
	@Test
	public void testCal02() {
		assertEquals(" 1  2  3  4  5  6  7\n 8  9 10 11 12 13 14\n15 16 17 18 19 20 21\n22 23 24 25 26 27 28\n",
				     cl.cal(0, 28));
	}
	
	@Test
	public void testCal03() {
		assertEquals("                   1\n 2  3  4  5  6  7  8\n 9 10 11 12 13 14 15\n16 17 18 19 20 21 22\n23 24 25 26 27 28 ",
				     cl.cal(6, 28));
	}
	
	@Test
	public void testCal04() {
		assertEquals(" 1  2  3  4  5  6  7\n 8  9 10 11 12 13 14\n15 16 17 18 19 20 21\n22 23 24 25 26 27 28\n29 30 ",
				     cl.cal(0, 30));
	}

	@Test
	public void testCal05() {
		assertEquals("                   1\n 2  3  4  5  6  7  8\n 9 10 11 12 13 14 15\n16 17 18 19 20 21 22\n23 24 25 26 27 28 29\n30 ",
				     cl.cal(6, 30));
	}

	@Test
	public void testCal06() {
		assertEquals(" 1  2  3  4  5  6  7\n 8  9 10 11 12 13 14\n15 16 17 18 19 20 21\n22 23 24 25 26 27 28\n29 30 31 ",
				     cl.cal(0, 31));
	}

	@Test
	public void testCal07() {
		assertEquals("                   1\n 2  3  4  5  6  7  8\n 9 10 11 12 13 14 15\n16 17 18 19 20 21 22\n23 24 25 26 27 28 29\n30 31 ",
				     cl.cal(6, 31));
	}
	
}
