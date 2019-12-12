package Ex1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	
	private LinkedList <Monom> poly = new LinkedList<Monom>();

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		poly.add(new Monom("0"));
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		s = s.replaceAll("\\s+","");
		s = s.replace('+', ':');
		s = s.replaceAll("-", ":-");
		if(s.charAt(0) == ':')
		{
			s = s.substring(1);
		}
		String[] arr = s.split(":");
		Monom[] mArr = new Monom[arr.length];
		for(int i=0; i<arr.length; i++)
		{
			mArr[i] = new Monom(arr[i]);
		}
		this.poly.add(mArr[0]);
		for(int i=1; i<mArr.length; i++)
		{
			int j=0;
			for(Monom m: this.getPoly())
			{
				if(mArr[i].get_power() == m.get_power())
				{
					m.add(mArr[i]);
					break;
				}
				else {
					if(mArr[i].get_power()> m.get_power())
					{
						this.poly.add(j, mArr[i]);
						break;
					}
				}
				j++;
			}
			if(j<this.poly.size()+1)
				this.poly.addLast(mArr[i]);
		}
		
		
	}
	@Override
	public double f(double x) {
		double sum = 0.0;
		for(Monom m : this.getPoly())
		{
			sum += m.f(x);
		}
		return sum;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor(); 
		while(it.hasNext())
		{
			this.add(it.next());
		}
		this.add(it.next());
	}

	@Override
	public void add(Monom m1) {
		int i=0;
		for(Monom m: this.getPoly())
		{
			if(m1.get_power() == m.get_power())
			{
				m.add(m1);
				return;
			}
			else {
				if(m1.get_power()> m.get_power())
				{
					this.poly.add(i, m1);
					return;
				}
			}
			i++;
				
		}
		
	}

	private LinkedList<Monom> getPoly() {
		return this.poly;
	}
	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> itMon = p1.iteretor();
		Monom m;
		Monom minusOne = new Monom("-1");
		Monom temp;
		while(itMon.hasNext())
		{
			m = itMon.next();
			temp = new Monom(m);
			temp.multipy(minusOne);
			this.add(temp);
		}
		m = itMon.next();
		temp = new Monom(m);
		temp.multipy(minusOne);
		this.add(temp);
		
	}

	@Override
	public void multiply(Polynom_able p1) {
		Iterator<Monom> itp1=p1.iteretor();
		Iterator<Monom> itThis=this.iteretor();
		Monom m;
		Monom temp;
		while(itThis.hasNext())
		{
			m = itThis.next();
			temp = new Monom(m);
			while(itp1.hasNext())
			{
				m.multipy(itp1.next());
				this.add(m);
				m=new Monom(temp);
			}
		}		
	}

	public boolean equals(Polynom_able p1) {
		if(this.isZero() && p1.isZero())
			return true;
		Iterator<Monom> it = p1.iteretor();
		Iterator<Monom> itPoly = this.iteretor();
		while(it.hasNext() && itPoly.hasNext())
		{
			if(it.next().equals(itPoly.next()))
			{
				return false;
			}
		}
		if(it.hasNext() && !itPoly.hasNext())
			return false;
		if(!it.hasNext() && itPoly.hasNext())
			return false;
		return true;
	}

	@Override
	public boolean isZero() {
		if(this.getPoly().isEmpty() || this.getPoly().getFirst().isZero())
		{
			return true;
		}
		return false;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		double x = 0;
		if(x0>x1)
		{
			double temp = x0;
			x0 = x1;
			x1 = temp;
		}
		x = findRoot(x0, x1, eps);
		return x;
	}

	private double findRoot(double x0, double x1, double eps) {
		double x = (f(x0)+f(x1))/2.0;
		if(x >= 0-eps || x <= x+eps)
			return x;
		if(x < 0-eps)
		{
			x0 = x;
			return findRoot(x0, x1, eps);
		}
		if(x > 0+eps)
		{
			x1 = x;
			return findRoot(x0, x1, eps);
		}
		if(f(x0) > 0 && f(x1) > 0)
		{
			return Double.MAX_VALUE;
		}
		if(f(x0) < 0 && f(x1) < 0)
		{
			return Double.MIN_VALUE;
		}
		return 0.0;
	}
	@Override
	public Polynom_able copy() {
		Iterator <Monom> it = this.iteretor();
		Polynom p = new Polynom();
		while(it.hasNext())
		{
			p.add(it.next());
		}
		return p;
	}

	@Override
	public Polynom_able derivative() {
		Iterator <Monom> it = this.iteretor();
		while(it.hasNext())
		{
			it.next().derivative();
		}
		return this;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		double area = 0.0;
		if(x1 < x0)
		{
			double temp = x1;
			x1 = x0;
			x0 = temp;
			
		}
		while(x0 < x1)
		{
			area += calArea(x0, x0+eps);
			x0 +=eps;
		}
		return area;
	}

	private double calArea(double x0, double d) {
		double area = 0.0;
		if(f(x0) >= 0 && f(d)>=0)
		{
			area = ((f(x0)+(f(d)))*(d-x0))/(2.0);
		}
		if(f(x0) <= 0 && f(d) <= 0)
		{
			area = (Math.abs((f(x0)+(f(d))))*(d-x0))/(2.0);
		}
		if(f(x0) < 0 && f(d) > 0)
		{
			area = calArea(x0, ((x0+d)/2)) + calArea(((x0+d)/2), d);
		}
		if(f(x0) > 0 && f(d) < 0)
		{
			area = calArea(x0, ((x0+d)/2)) + calArea(((x0+d)/2), d);
		}
		return area;
	}
	@Override
	public Iterator<Monom> iteretor() {
		Iterator <Monom> itMon = new Iterator<Monom>() {
			
			private int indx = 0;
			
			@Override
			public boolean hasNext() {
				if(getPoly().size() > indx+1)
					return true;
				return false;
			}

			@Override
			public Monom next() {
				Monom m = getPoly().get(indx);
				indx++;
				return m;
			}
		};
		return itMon;
	}
	@Override
	public void multiply(Monom m1) {
		for(Monom m : this.getPoly())
		{
			m.multipy(m1);
		}
		
	}
	
	@Override
	public String toString()
	{
		int i=0;
		String s = "";
		for(Monom m: this.getPoly())
		{
			if(i>0){
				if(m.get_coefficient() < 0)
				{
					s += m.toString();
				}
				if(m.get_coefficient()>0)
				{
					s += "+";
					s += m.toString();
				}
			}
			else {
				if(m.get_coefficient() != 0)
					s += m.toString();
			}	
			i++;
			//System.out.println("s = " + s.toString());
		}
		if(s.equals(""))
			s += "0";
		return s;
	}
	public static Polynom_able parse(String s1) {
		Polynom p = new Polynom(s1);
		return p;
	}
	@Override
	public function initFromString(String s) {
		Polynom p;
		try {
			p = new Polynom(s);	
		}
		catch (Exception e) {
			System.out.println("incorect input, as defuelt the function will return 0 Polynom");
			p = new Polynom("0.0");
		}
		return p;
	}
	
}
