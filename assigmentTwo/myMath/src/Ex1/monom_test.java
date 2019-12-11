package Ex1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class monom_test {

	@Test
	public void testMonomOne() {
		String [] sTest = {"0) 2.0    	isZero: false	 f(0) = 2.0", "1) -1.0x    	isZero: false	 f(1) = -1.0", "2) -3.2x^2    	isZero: false	 f(2) = -12.8", "3) 0    	isZero: true	 f(3) = 0.0" };
		String[] monoms = {"2", "-x","-3.2x^2","0"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			String str = i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx;
			assertEquals(sTest[i], str);
		}
	}
	
	@Test
	public void testMonomtwo()
	{
		String [] sTest = {"0) 0    	isZero: true  	eq: true", "1) -1.0    	isZero: false  	eq: true", "2) -1.3x    	isZero: false  	eq: true", "3) -2.2x^2    	isZero: false  	eq: true" };
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			String str = i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e;
			assertEquals(sTest[i], str);
		}
	}
	
	@Test
	public void testMonomThree()
	{
		Monom m = new Monom("0.7x");
		assertEquals("0.7x" , m.toString());
		
	}

}
