/*Name of program - lisp.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class than implements methods to solve a lisp expression.
 */
package ExtraCredit_palin_lisp;

import java.util.ArrayList;

public class lisp {

	private String wholeExpr = "";// private variable to store the whole
	// expression - initialized to empty to prevent null value
	private ArrayList<String> tokens = new ArrayList<>();// arraylist to store
	// the separated tokens from the expression
	private double result = 0.0;// double value to store the result

	public lisp(String wholeExpr) {// constructor with string
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
			//System.out.println(tokenArray[i]);
		}
	} // end tokenize

	// method to execute the operand on 2 values based on the precedence
	private void execute(StackInterface<Object> valStack) {
		// variables to store the double values
		ArrayStack<Double> operandStack = new ArrayStack<>();
		Double tempVal = 0.0;//temp variable to store the expression value
		String operator = "";
		
		// return if the value stack is empty
		if (valStack.isEmpty())
			return;
		// pop the opStack
		
		Object op = valStack.pop();//pop the doubele value

        while ( op instanceof Double ) 
        {
          double value = (double) op;
          operandStack.push(value);//if it is a double value push into stack
          op = valStack.pop();//the last one popped will be the operator
        }

        operator = op.toString() ;
		// based on the operator - do the operations
		switch (operator) {
		case "+":
			// temporary variable to store result
			tempVal = 0.0;
			while (!operandStack.isEmpty())//pop the double values and perform operations
				tempVal = tempVal + operandStack.pop();
			break;
		case "-":
			// temporary variable to store result
			tempVal = 0.0;
			tempVal = operandStack.pop();
			//if there is just one value then answer is -a
            if (operandStack.isEmpty()) {
              tempVal = -tempVal;
            }
            else {
              //case 2: there are many numbers
              while(!operandStack.isEmpty()) {//pop the double values and perform operations
                tempVal = tempVal - operandStack.pop();
              }
                
            }
			
		
			break;
		case "*":
			// temporary variable to store result
			tempVal = 1.0;
			while (!operandStack.isEmpty())//pop the double values and perform operations
				tempVal = tempVal * operandStack.pop();
			
			break;
		case "/":
			// temporary variable to store result
			tempVal = 1.0;
			tempVal = operandStack.pop();
			//if there is just one value answer is 1/a
			if(operandStack.isEmpty())
			{
				tempVal = 1/tempVal;
			}
			else
			{//more than 1 value
			while (!operandStack.isEmpty())//pop the double values and perform operations
				tempVal = tempVal / operandStack.pop();
			}
			
			break;
		}
		// store the result on top of valstack
		
		valStack.push(tempVal);

	}

	// method to check if the passed string is an operator
	private boolean operatorCheck(String operator) {
		if (operator.matches("[-+*/]")) {
			// if (operator.equals("+") || operator.equals("-") ||
			// operator.equals("*") || operator.equals("/"))
			return true;
		} else
			return false;

	}

	// method to evaluate the expression
	private void evaluate() {
		// arraystack and linkedstack to store the operators and values
		// respectively

		ArrayStack<Object> valStack = new ArrayStack<>();
		// for each token do the following
		for (String temp : tokens) {
			// check if token is an operator
			if (operatorCheck(temp)) 
				valStack.push(temp);
			 // if the token is an open bracket continue
			else if (temp.equals("("))
				continue;
			else if (temp.equals(")")) {// if the token is closed bracket
				// then pop the stack and evaluate
					execute(valStack);
				
			} else // if the token is a value - convert it into double and push
					// into valstack
			{
				Double val = Double.parseDouble(temp);
				valStack.push(val);

			}

		}
		// while the opstack is not empty- execute the operations on the values
		while (!valStack.isEmpty()) {
			if (valStack.size() == 1)// if the valstack size is 1- pop the result
			{
				result = (double) valStack.pop();
				break;
			}
			//else continue operations
			execute(valStack);
		
		
		}
	}

	public static void main(String[] args) {
		
		//some valid lisp expressions
		lisp expr1 = new lisp("( + ( - 6 ) ( * 8 3 4 ) ( / ( + 5 ) ( * ) ( - 2 2 1 ) ) )");
		lisp expr2 = new lisp("( + ( - 62 ) ( * 3 13 4 ) ( / ( + 72 ) ( * 1 ) ( - 2 13 1 ) ) ( + ) )");
		lisp expr3 = new lisp("( + ( / 5 ) ( * 2 ) ( / ( + 2 ) ( + 3 ) ( - 9 1 ) ) ( * ) )");
		lisp expr4 = new lisp("( + ( / 6 ) ( + ) )");
		System.out.print("( + ( - 6 ) ( * 8 3 4 ) ( / ( + 5 ) ( * ) ( - 2 2 1 ) ) ) = ");
		System.out.println(expr1.getResult());
		System.out.print("( + ( - 62 ) ( * 3 13 4 ) ( / ( + 72 ) ( * 1 ) ( - 2 13 1 ) ) ( + ) ) = ");
		System.out.println(expr2.getResult());
		System.out.print("( + ( / 5 ) ( * 2 ) ( / ( + 2 ) ( + 3 ) ( - 9 1 ) ) ( * ) ) = ");
		System.out.println(expr3.getResult());
		System.out.print("( + ( / 6 ) ( + ) ) = ");
		System.out.println(expr4.getResult());
	}
}
/*OUTPUT
 * 
 * ( + ( - 6 ) ( * 8 3 4 ) ( / ( + 5 ) ( * ) ( - 2 2 1 ) ) ) = 85.0
( + ( - 62 ) ( * 3 13 4 ) ( / ( + 72 ) ( * 1 ) ( - 2 13 1 ) ) ( + ) ) = 88.0
( + ( / 5 ) ( * 2 ) ( / ( + 2 ) ( + 3 ) ( - 9 1 ) ) ( * ) ) = 3.2833333333333337
( + ( / 6 ) ( + ) ) = 0.16666666666666666

*/

