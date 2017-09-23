package util;

import java.util.Stack;

public class ExpressionCalculator {

	public static void main(String[] args){
		ExpressionCalculator c = new ExpressionCalculator();
		System.out.println(c.calculateAtOnce("3*(1+7+6*2)"));
	}
	
	public double calculate(String infixExpression){
		return evaluate(infixToPostfix(infixExpression));
	}
	
	
	public double calculateAtOnce(String infixExpression){
		Stack<Character> operators = new Stack<>();
		Stack<Double> numbers = new Stack<>();
		
		for(int i=0;i<infixExpression.length();i++){
			char c = infixExpression.charAt(i);
			if (Character.isDigit(c)){
				numbers.push(Double.parseDouble(c+""));
				continue;
			}
			if (c=='('){
				operators.push(c);
				continue;
			}
			if (c==')'){
				char o;
				while((o=operators.pop())!='('){
					calculate(numbers, o);
				}
				continue;
			}
			if (operators.empty() || priority(c)>priority(operators.peek())){
				operators.push(c);
				continue;
			}else{
				calculate(numbers, c);
			}
		}
		while(!operators.empty()){
			calculate(numbers, operators.pop());
		}
		return numbers.pop();
	}
	
	
	public String infixToPostfix(String infixExpression){
		StringBuilder result = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		
		for(int i=0;i<infixExpression.length();i++){
			char c = infixExpression.charAt(i);
			if (Character.isDigit(c)){
				result.append(c);
				continue;
			}
			if (c=='('){
				stack.push(c);
				continue;
			}
			if (c==')'){
				char o;
				while((o=stack.pop())!='('){
					result.append(o);
				}
				continue;
			}
			if (stack.empty() || priority(c)>priority(stack.peek())){
				stack.push(c);
				continue;
			}else{
				result.append(c);
			}
		}
		while(!stack.isEmpty()){
			result.append(stack.pop());
		}
		return result.toString();
		
	}
	
	public int priority(char operator){
		switch(operator){
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 3;
		default:
			return -1;
		}
	}
	
	
	public double evaluate(String postfixExpression){
		Stack<Double> stack = new Stack<>();

		for(int i=0;i<postfixExpression.length();i++){
			char c = postfixExpression.charAt(i);
			switch(c){
			case '+':
			case '-':
			case '*':
			case '/':
				calculate(stack, c);
				break;
			default:
				stack.push(Double.parseDouble(c+""));
			}
		}
		return stack.pop();
	}
	
	public void calculate(Stack<Double> stack, char operator){
		double operator1 = stack.pop();
		double operator2 = stack.pop();
		double result;
		switch(operator){
		case '+':
			result = operator1+operator2;
			break;
		case '-':
			result=  operator1-operator2;
			break;
		case '*':
			result= operator1*operator2;
			break;
		case '/':
			result= operator1/operator2;
			 break;
			default:
				result = operator1;
				break;
		}
		stack.push(result);
	}
}
