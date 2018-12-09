package util;

import java.util.HashMap;

public class Polynomial {
	public PolynomialNode start;
	public Polynomial add(double coeff, double exp){
		return add(new PolynomialNode(coeff, exp,null));
	}
	
	public Polynomial add(PolynomialNode newNode){
		PolynomialNode current = start;
		if (start==null){
			start = newNode;
			return this;
		}
		
		while(current.next!=null && current.next.exp>newNode.exp){
			current=current.next;
		}
		
		if (current.next==null){
			current.next = newNode;
			return this;
		}
		
		if (current.next.exp==newNode.exp){
			current.next.coeff+=newNode.coeff;
			if (current.next.coeff==0.0){
				current.next =current.next.next;
			}
			return this;
		}
		
		if (current.next.exp<newNode.exp){
			newNode.next = current.next;
			current.next = newNode;
		}
		
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
	
	public boolean insertAfter(int index, double coeff, double exp){
		PolynomialNode current = start;
		for(int i=0;i<index;i++){
			current = current.next;
			if (current==null){
				return false;
			}
		}
		
		PolynomialNode newNode = new PolynomialNode(coeff,exp, current.next);
		current.next = newNode;
		return true;
		
	}
	
	public int indexOf(double coeff){
		int index= 0;
		PolynomialNode current = start;
		while(current!=null){
			if (current.coeff==coeff){
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}
	
	@Override
	public String toString(){
		StringBuilder output = new StringBuilder();
		
		PolynomialNode current = start;
		do{
			String str = current.toString();
			output.append((str.charAt(0)=='-'?"":"+")+ str);
			current = current.next;
		}while(current!=null);
		
		return output.charAt(0)=='+' ? output.substring(1): output.toString();
	}
	
	public Polynomial add(Polynomial other){
		PolynomialNode nodeOther = other.start;
		PolynomialNode node1current = start;
		PolynomialNode node1previous = null;
		
		while (true){
			if (node1current.exp==nodeOther.exp){
				node1current.coeff += nodeOther.coeff;
				nodeOther = nodeOther.next;
				node1previous=node1current;
				node1current = node1current.next;
			}else if (node1current.exp < nodeOther.exp){
//				//hijack other polynomial's node
//				PolynomialNode next = nodeOther.next;
//				nodeOther.next = node1current;
//				if (node1previous==null){
//					//the first item
//					start = nodeOther;
//				}else{
//					node1previous.next = nodeOther;
//				}
//				nodeOther = next;
//				node1previous = nodeOther;
		
			//	// or create a new one and link it in
				PolynomialNode newNode = new PolynomialNode(nodeOther.coeff, nodeOther.exp, node1current);
				if (node1previous==null){
					//the first item
					start = newNode;
				}else{
					node1previous.next = newNode;
				}
				nodeOther = nodeOther.next;
				node1previous = newNode;
			}else{
				node1previous = node1current;
				node1current=node1current.next;
			}
			
			if (nodeOther==null){
				break;
			}
			
			if (node1current==null){
				//hijack other polynomial's nodes
//				node1previous.next = nodeOther;
				
				
				// or create new nodes and link it in
				PolynomialNode current = node1previous;
				while(nodeOther!=null){
					PolynomialNode newNode = new PolynomialNode(nodeOther.coeff, nodeOther.exp, null);
					current.next = newNode;
					current = newNode;
					nodeOther = nodeOther.next;
				}
				
				//common code
				break;
			}

		}
		return this;
	
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
	
	@Override
	public String toString(){
		return (coeff<0?"-":"")+String.format("(%.2f)*x^(%.2f)", coeff, exp);
	}

}