package util;

public class Polynomial {
	public PolynomialNode start;
	public Polynomial add(double coeff, double exp){
		PolynomialNode newNode = new PolynomialNode(coeff, exp,null);
		PolynomialNode current = start;
		if (start==null){
			start = newNode;
			return this;
		}
		while(current.next!=null){
			current = current.next;
		}
		current.next = newNode;
		return this;
	}
	
	public double sum(double x){
		PolynomialNode current = start;
		double result=0.0;
		do{
			result+= current.coeff * Math.pow(x, current.exp);
			current = current.next;
		}while(current!=null);
		return result;
	}

	
}

class PolynomialNode {
	public double coeff;
	public double exp;
	public PolynomialNode next;
	
	public PolynomialNode(double coeff, double exp, PolynomialNode next){
		this.coeff = coeff;
		this.exp=exp;
		this.next = next;
	}
}