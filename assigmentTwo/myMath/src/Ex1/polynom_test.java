package Ex1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class polynom_test {

	@Test
	public void polynomTest1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		assertEquals("1.5x^2+1.0x+1.0", p1.toString());
	}
	
	@Test
	public void polynomTest2()
	{
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		assertEquals(2.0, p1.area(0, 1, 0.0001), 0.01);
	}
	
	@Test
	public void polynomTest3()
	{
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		Polynom p = new Polynom();
		Monom m = new Monom("0");
		p.add(m);
		p1.substract(p1);
		assertTrue(p1.equals(p));
	}
	
	@Test
	public void polynomTest4()
	{
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		Polynom p = new Polynom(p1.toString());
		assertEquals(p1.toString(), p.toString());
	}
	
	@Test
	public void polynomTest5()
	{
		Polynom p1 = new Polynom();
		Polynom p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		
		  for(int i=0;i<monoms2.length;i++) { 
			  Monom m = new Monom(monoms2[i]);
			  p2.add(m); 
		  } 
		  p1.add(p2);
		  assertEquals("-3.0x^2+0.7x+8.0", p1.toString()); 
	}
	
	@Test
	public void polynomTest6()
	{
		Polynom p1 = new Polynom();
		Polynom p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		
		  for(int i=0;i<monoms2.length;i++) { 
			  Monom m = new Monom(monoms2[i]);
			  p2.add(m); 
		  } 
		  p1.add(p2);
		  p1.multiply(p2);
		  assertEquals("-10.200000000000001x^4-5.1x^3+0.7x+8.0", p1.toString()); 
	}
	
	@Test
	public void polynomTest7()
	{
		Polynom p1 = new Polynom("-10.2x^4+15x^3");
		assertEquals("-10.2x^4+15.0x^3", p1.toString());
	}
	
	@Test
	public void polynomTest8()
	{
		Polynom p = new Polynom("0.0");
		assertEquals("0", p.toString());
	}
}
