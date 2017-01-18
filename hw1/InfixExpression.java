package hw1;

/*
 * Name of program - InfixExpression.java
   Programmer's name - Nianthrini Vivekanandan
   Author -  C. Lee-Klawender
   Updated by Nianthrini Vivekanandan
   Current Date - 04/17/2016
   Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
   ECLIPSE Version: Mars.2 Release (4.5.2)
   PROGRAM DESCRIPTION - This is a program to evaluate an infix expression using stack implementation in java.
   The expression is pushed in 2 different stacks - one for operators and another for values (after converting it into double).
   Based on the precedence of the operators, the values are operated upon until a single result remains.
 */
import java.util.ArrayList;
import java.util.Iterator;

public class InfixExpression {

	private String wholeExpr = "";// private variable to store the whole
	// expression - initialized to empty to prevent null value
	private ArrayList<String> tokens = new ArrayList<>();// arraylist to store
	// the separated tokens from the expression
	private double result = 0.0;// double value to store the result

	public InfixExpression()// no argument default constructor
	{

	}

	public InfixExpression(String wholeExpr) {// constructor with string
		// argument
		// call the mutator
		setWholeExpr(wholeExpr);
	}

	// getters and setters for the rivate variables
	public String getWholeExpr() {
		return wholeExpr;
	}

	public void setWholeExpr(String wholeExpr) {
		if (wholeExpr != null) {
			this.wholeExpr = wholeExpr;
			tokenize();// calls the method to tokenize the expression
			evaluate();// evaluates the expression and stores the result
		}
	}

	public double getResult() {
		return result;
	}

	// method to tokenize the expression into individual operands or values
	// using split method
	private void tokenize() {
		// split the expression into tokens based on space and tabs
		String[] tokenArray = wholeExpr.split("[ \t]+");
		tokens.clear(); // clear the ArrayList
		for (int i = 0; i < tokenArray.length; ++i) {
			tokens.add(tokenArray[i]); // add the next token to the ArrayList
		}
	} // end tokenize

	// method to execute the operand on 2 values based on the precedence
	private void execute(StackInterface<String> opStack, StackInterface<Double> valStack) {
		// variables to store the double values
		Double rightOperand, leftOperand, tempVal;
		// pop the opStack
		String op = opStack.pop();
		// return if the value stack is empty
		if (valStack.isEmpty())
			return;
		// pop the value stack
		rightOperand = valStack.pop();
		// return if the value stack is empty
		if (valStack.isEmpty())
			return;
		// pop the value stack
		leftOperand = valStack.pop();
		// temporary variable to store result
		tempVal = 0.0;
		// based on the operator - do the operations
		switch (op) {
		case "+":
			tempVal = leftOperand + rightOperand;
			break;
		case "-":
			tempVal = leftOperand - rightOperand;
			break;
		case "*":
			tempVal = leftOperand * rightOperand;
			break;
		case "/":
			tempVal = leftOperand / rightOperand;
			break;
		}
		// store the result on top of valstack
		valStack.push(tempVal);

	}

	// method to check if the passed string is an operator
	private boolean operatorCheck(String operator) {
		if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/"))
			return true;
		else
			return false;

	}

	// method to find the precedence of the passed operator as an int value
	private int checkPrecedence(String operator) {
		int preValue = 0;
		switch (operator) {
		case ")":
		case "(":
			preValue = 1;
			break;
		case "+":
		case "-":
			preValue = 2;
			break;
		case "*":
		case "/":
			preValue = 3;
			break;
		}
		return preValue;

	}

	// method to evaluate the expression
	private void evaluate() {
		// arraystack and linkedstack to store the operators and values
		// respectively
		ArrayStack<String> opStack = new ArrayStack<>();
		LinkedStack<Double> valStack = new LinkedStack<>();
		// for each token do the following
		for (String temp : tokens) {
			// check if token is an operator
			if (operatorCheck(temp)) {
				// check if opstack is empty and push into opstack is empty
				if (opStack.isEmpty())
					opStack.push(temp);
				else {// if not empty check for precedence with the next
						// operator in stack
						// if precedence is more push it into opstack
					if (checkPrecedence(temp) > checkPrecedence(opStack.peek()))
						opStack.push(temp);
					else {// if precedence is low - check for empty opstack and
							// equal or lower precedence
							// while the condition is true - execute the
							// operations on the values
						while (!opStack.isEmpty() && (checkPrecedence(temp) <= checkPrecedence(opStack.peek())))
							execute(opStack, valStack);
						// push the token into opstack
						opStack.push(temp);
					}
				}

			} // if the token is an open bracket push into opstack
			else if (temp.equals("("))
				opStack.push(temp);
			else if (temp.equals(")")) {// if the token is closed bracket
				// while the next opstack value is not open bracket - execute
				// the operations on the values
				while (!opStack.peek().equals("("))
					execute(opStack, valStack);
				opStack.pop();// pop the opstack
			} else // if the token is a value - convert it into double and push
					// into valstack
			{
				Double val = Double.parseDouble(temp);
				valStack.push(val);

			}

		}
		// while the opstack is not empty- execute the operations on the values
		while (!opStack.isEmpty()) {
			execute(opStack, valStack);
		}
		// if the valstack size is 1- pop the result
		if (valStack.size() == 1)
			result = valStack.pop();
		else
			result = 0;

	}

}
