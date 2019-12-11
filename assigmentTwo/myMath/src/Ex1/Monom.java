
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public Monom(String s) 
	{
		boolean minus = false;
		try {
			if(s.charAt(0) == 45) //check if the string starts with "-"
			{
				minus = true;
				s = s.substring(1);
			}
			
			if(s.contains("x^")) // if there is x^ split it for before x^ and after
			{
				String[] strArr = s.split("[x^]", 2);
				strArr[1] = strArr[1].substring(1);
				if(strArr[0].equals(""))
				{
					this.set_coefficient(1);
					this.set_power(Integer.parseInt(strArr[1]));
				}
				else {
					this.set_coefficient(Double.valueOf(strArr[0]));
					this.set_power(Integer.parseInt(strArr[1]));
				}
			}
			
			if(!s.contains("x")) // if there is no x-s then its only the  coefficient 
			{
					this.set_coefficient(Double.parseDouble(s));
					this.set_power(0);
			}
			
			if(s.contains("x") && !s.contains("^"))
			{
				if(s.charAt(0) == 'x')
				{
					this.set_coefficient(1.0);
					this.set_power(1);
				}
				else
				{
					this.set_coefficient(Double.parseDouble(s.substring(0, s.indexOf('x'))));
					this.set_power(1);	
				}
			}
			if(minus)
			{
				set_coefficient(this._coefficient*(-1));
			}
		} 
		catch (Exception e) {
			System.out.println("incorrect input");
		}
		
	}
	
	public void add(Monom m) {
		if(this.get_power()!=m.get_power())
		{
			System.out.println("can't make add function");
			return;
		}
		this.set_coefficient(m.get_coefficient()+this.get_coefficient());
		
	}
	
	public void multipy(Monom d) {
		this._coefficient *= d.get_coefficient();
		this._power += d.get_power();
	}
	
	public boolean equals(Monom d) {
		if( this.get_coefficient()==d.get_coefficient() && this.get_power()==d.get_power())
			return true;
		if(this.get_coefficient() == 0 && 0 == d.get_coefficient())
			return true;
		return false;
	}
	
	public String toString() {
		String ans = "";
		if(this.get_coefficient() != 0 && this.get_power() != 0)
			ans = "" + this.get_coefficient() + "x^" + this.get_power();
		if(this.get_coefficient() != 0 && this.get_power() == 0)
			ans = "" + this.get_coefficient();
		if(this.get_coefficient() == 0)
			ans = "0";
		if(this.get_coefficient() != 0 && this.get_power() == 1)
			ans = "" + this.get_coefficient() + "x";
		return ans;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	
	// get string and check if it is a number

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	
	@Override
	public function initFromString(String s) {
		Monom m;
		try {
			m = new Monom(s);	
		}
		catch (Exception e) {
			System.out.println("incorect input, as defuelt the function will return 0 monom");
			m = new Monom("0.0");
		}
		return m;
		
	}
	@Override
	public function copy() {
		Monom m = new Monom(this.get_coefficient(), this.get_power());
		return m;
	}
	
	
}
