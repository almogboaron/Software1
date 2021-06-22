package il.ac.tau.cs.sw1.hw6;

public class Polynomial {
	
	private double[] polyCo;
	private int degree;
	private static double[] polyZero = new double[1];
	
	/**
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 * @post Polynimial = 0 ;
	 */
	public Polynomial()
	{
		this(polyZero);
	} 
	/**
	 * Creates a new polynomial with the given coefficients.
//	 * @post polynoms
	 */
	public Polynomial(double[] coefficients) 
	{
		this.degree = calcDegree(coefficients);
		this.polyCo = new double[degree+1];
		for(int i=0;i<=this.degree;i++) {
			this.polyCo[i]=coefficients[i];
		}
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial)
	{
		int maxDeg = Math.max(this.degree, polynomial.getDegree());

		double[] newPoly = new double[maxDeg+1];
		
		for(int i=0;i<=maxDeg;i++) {     newPoly[i] = this.getCoefficient(i) + polynomial.getCoefficient(i);}
		
		Polynomial addPoly = new Polynomial(newPoly);
		return addPoly;
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		double[] newpoly = this.polyCo.clone();
		for(int i=0 ; i<this.degree ; i++) {
			newpoly[i] = newpoly[i]*a;
		}
		return new Polynomial(newpoly);
		
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{
		return this.degree;
	}
	/** 
	 * 
	 * @param args = Array of Coefficients
	 * @post $ret = i+1 iff (args(i+1)!=0) AND (to all j>i+1 args[j]=0;)
	 */
	private int calcDegree(double[] args) {
		for(int i=args.length-1;i>=0;i-- ) {
			if(args[i]!=0) {
				return i ;
			}
		}
		return 0;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{
		if (n>this.getDegree()) {
			return 0;}
		else {
			return this.polyCo[n];}
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)
	{
		if(this.degree>=n) {this.polyCo[n]=c;}
		else {
			double[] coe = new double[n+1];
			
			for(int i = 0;i<=n;i++) {
				coe[i]=this.polyCo[i]+coe[i];
			}
			
			this.polyCo = coe.clone();
		}
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		double[] Deriv = new double[this.degree];
		for(int i = 1;i<=this.degree;i++) {
			Deriv[i-1] = i*this.polyCo[i];
		}
		return new Polynomial(Deriv);
	}
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{
		int sumPoly = 0;
		for(int i=0 ; i<=this.degree;i++) {
			sumPoly += this.polyCo[i]*Math.pow(x, i);
		}
		return sumPoly;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x)
	{
		Polynomial firstDeriv = (this.getFirstDerivation());
		double DerivX = firstDeriv.computePolynomial(x);
		if(DerivX==0) {
			double Deriv2X = firstDeriv.getFirstDerivation().computePolynomial(x);
			if(Deriv2X!=0) {
				return true;
			}
		}
		return false;
	}
	
	
	
	

    
    

}
