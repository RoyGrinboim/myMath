package Ex1;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class ComplexFunction implements complex_function{
	private function left;
	private function right;
	private Operation opertion;

	public  ComplexFunction()
	{
		this.opertion = Operation.Plus;
		this.right = null;
		Monom m = new Monom("0.0");
		this.left = m;
	}
	public ComplexFunction(Monom m)
	{
		this.opertion = Operation.Plus;
		this.right = null;
		this.left = m;
	}
	public ComplexFunction(Polynom p)
	{
		this.opertion = Operation.Plus;
		this.right = null;
		this.left = p;
	}
	public ComplexFunction(Monom m, Polynom p)
	{
		this.opertion = Operation.Plus;
		this.right = m;
		this.left = p;
	}
	public ComplexFunction(ComplexFunction cf)
	{
		this.opertion = Operation.Plus;
		this.right = null;
		this.left = cf;
	}
	public ComplexFunction(ComplexFunction cf1, ComplexFunction cf2)
	{
		this.opertion = Operation.Plus;
		this.right = cf1;
		this.left = cf2;
	}
	public ComplexFunction(Monom m1, Monom m2)
	{
		this.opertion = Operation.Plus;
		this.right = m1;
		this.left = m2;
	}
	public ComplexFunction(Polynom p1, Polynom p2)
	{
		this.opertion = Operation.Plus;
		this.right = p1;
		this.left = p2;
	}
	public ComplexFunction(ComplexFunction cf, Monom m)
	{
		this.opertion = Operation.Plus;
		this.right = m;
		this.left = cf;
	}
	public ComplexFunction(ComplexFunction cf, Polynom p)
	{
		this.opertion = Operation.Plus;
		this.right = p;
		this.left = cf;
	}
	public ComplexFunction(Monom m, Operation op)
	{
		this.opertion = op;
		this.right = null;
		this.left = m;
	}
	public ComplexFunction(Polynom p, Operation op)
	{
		this.opertion = op;
		this.right = null;
		this.left = p;
	}
	public ComplexFunction(Monom m, Polynom p, Operation op)
	{
		this.opertion = op;
		this.right = m;
		this.left = p;
	}
	public ComplexFunction(ComplexFunction cf, Operation op)
	{
		this.opertion = op;
		this.right = null;
		this.left = cf;
	}
	public ComplexFunction(ComplexFunction cf1, ComplexFunction cf2, Operation op)
	{
		this.opertion = op;
		this.right = cf1;
		this.left = cf2;
	}
	public ComplexFunction(Monom m1, Monom m2, Operation op)
	{
		this.opertion = op;
		this.right = m1;
		this.left = m2;
	}
	public ComplexFunction(Polynom p1, Polynom p2, Operation op)
	{
		this.opertion = op;
		this.right = p1;
		this.left = p2;
	}
	public ComplexFunction(ComplexFunction cf, Monom m, Operation op)
	{
		this.opertion = op;
		this.right = m;
		this.left = cf;
	}
	public ComplexFunction(ComplexFunction cf, Polynom p, Operation op)
	{
		this.opertion = op;
		this.right = p;
		this.left = cf;
	}
	public ComplexFunction(String s)
	{
		
	}
	public ComplexFunction(ComplexFunction complexFunction, function f1, Operation op) {
		this.left = complexFunction;
		this.right = f1;
		this.opertion = op;
	}
	public ComplexFunction(function f1, function f2, Operation op) {
		this.left = f1;
		this.right = f2;
		this.opertion = op;
	}
	@Override
	public double f(double x) {
		Operation o = this.getOp();
		if(this.right == null)
		{
			Monom m = new Monom("0.0");
			this.right = m;
		}
		switch(o) {
			case Plus:
				return (this.left.f(x)+this.right.f(x));
			case Times:
				return (this.left.f(x)*this.right.f(x));
			case Divid:
				if(this.right.f(x) != 0)
				{
					return (this.left.f(x) / this.right.f(x));
				}
				else
				{
					if(this.left.f(x) < 0)
					{
						return Double.MIN_VALUE;
					}
					if(this.left.f(x) > 0)
					{
						return Double.MAX_VALUE;
					}
					return (this.left.f(x+0.01) / this.right.f(x+0.01));
				}
			case Comp:
				return (this.left.f(this.right.f(x)));
			case Max:
				double fml = findMax(this.left, x);
				double fmr = findMax(this.right, x);
				if(fml > fmr)
					return fml;
				else
					return fmr;
			case Min:
				double fmnl = findMin(this.left, x);
				double fmnr = findMin(this.right, x);
				if(fmnl < fmnr)
					return fmnl;
				else
					return fmnr;
			default:
				System.out.println("wrong input");			
		}
	
		return 0.0;
	}

	private double findMin(function f, double x) {
		if(f.f(x)-f.f(x+1) == f.f(x+1)-f.f(x+2))
		{
			return f.f(x);
		}
		if(f.f(x) > f.f(x+1))
		{
			while(f.f(x) > f.f(x+1))
			{
				x++;
			}
			return f.f(x);
		}
		else
		{
			if(f.f(x) != f.f(x-1))
			{
				while(f.f(x-1) < f.f(x))
				{
					x--;
				}
				return f.f(x);
			}
			else
				return f.f(x);
		}
	}
	private double findMax(function f, double x) {
		if(f.f(x)-f.f(x+1) == f.f(x+1)-f.f(x+2))
		{
			return f.f(x);
		}
		if(f.f(x) < f.f(x+1))
		{
			while(f.f(x) < f.f(x+1))
			{
				x++;
			}
			return f.f(x);
		}
		else
		{
			if(f.f(x) != f.f(x-1))
			{
				while(f.f(x-1) > f.f(x))
				{
					x--;
				}
				return f.f(x);
			}
			else
				return f.f(x);
		}
	}
	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		ComplexFunction cf = new ComplexFunction(this.left, this.right, this.opertion);
		return cf;
	}

	@Override
	public void plus(function f1) {
		this.left = this;
		this.right = f1;
		this.opertion = Operation.Plus;
		
	}

	@Override
	public void mul(function f1) {
		this.left = this;
		this.right = f1;
		this.opertion = Operation.Times;
		
		
	}

	@Override
	public void div(function f1) {
		this.left = this;
		this.right = f1;
		this.opertion = Operation.Divid;
		
		
	}

	@Override
	public void max(function f1) {
		this.left = this;
		this.right = f1;
		this.opertion = Operation.Max;
		
	}

	@Override
	public void min(function f1) {
		this.left = this;
		this.right = f1;
		this.opertion = Operation.Min;
		
		
	}

	@Override
	public void comp(function f1) {
		this.left = this;
		this.right = f1;
		this.opertion = Operation.Comp;
		
		
	}

	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.opertion;
	}

}
