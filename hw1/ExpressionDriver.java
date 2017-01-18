package hw1;

/*
 * Name of program - ExpressionDriver.java
   Programmer's name - Nianthrini Vivekanandan
   Author -  C. Lee-Klawender
   Updated by Nianthrini Vivekanandan
   Current Date - 04/17/2016
   Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
   ECLIPSE Version: Mars.2 Release (4.5.2)
   PROGRAM DESCRIPTION - Driver class to read a file and get the expressions. The expressions are read
   one and one and evaluated. The results are then displayed.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExpressionDriver {

	// USE THE FOLLOWING IN YOUR MAIN FILE for opening the input file:
	public static Scanner userScanner = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// WRITE MAIN as described on the assignment and using the following
		// method & variable:
		// Scanner variable to store the expressions
		Scanner values = openInputFile();
		// if expressions is not null - evaluate it
		if (values != null) {
			InfixExpression exp1 = new InfixExpression();
			// while there are more expressions in the scanner variable -
			// evaluate the result
			while (values.hasNext()) {
				String expression = values.nextLine();
				// System.out.println(expression);
				exp1.setWholeExpr(expression);

				System.out.println("Value of expression " + exp1.getWholeExpr() + " is " + exp1.getResult());

			}
		}
		// if expressions is null - end program
		else
			System.out.println("End program");

	}

	// YOU'RE NOT ALLOWED TO CHANGE THIS METHOD, AND YOU MUST USE IT:
	public static Scanner openInputFile() {
		String filename;
		Scanner scanner = null;

		System.out.print("Enter the input filename: ");
		filename = userScanner.nextLine();
		File file = new File(filename);

		try {
			scanner = new Scanner(file);
		} // end try
		catch (FileNotFoundException fe) {
			System.out.println("Can't open input file\n");
			return null; // array of 0 elements
		} // end catch
		return scanner;
	} // end openInputFile

}
/*Name of program - ExpressionDriver.java
   Programmer's name - Nianthrini Vivekanandan
   Author -  C. Lee-Klawender
   Updated by Nianthrini Vivekanandan
   Current Date - 04/17/2016
   Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
   ECLIPSE Version: Mars.2 Release (4.5.2)
   PROGRAM DESCRIPTION - Driver class to read a file and get the expressions. The expressions are read
   one and one and evaluated. The results are then displayed.
 * 
 * OUTPUT
 * 
 * Enter the input filename: HW1 Input.txt
Value of expression 5.0 * 7.0 is 35.0
Value of expression 4.0 + 30. / 2 is 19.0
Value of expression 25. / 5. -  3. * 3. is -4.0
Value of expression 54.32 is 54.32
Value of expression 3.5 / 0. is Infinity
Value of expression 11.1  * 22.2 + -  is 0.0
Value of expression 100. /  -20. is -5.0
Value of expression ( 5.0  + 2.5 ) * 3.0 is 22.5
Value of expression ( 9.8 - 3. ) * ( 2. +  1. ) is 20.400000000000002


Enter the input filename: HW1 Test Input1.txt
Value of expression 123.456 - 7.8 is 115.656
Value of expression 3 * 2 + 5 * 4 is 26.0
Value of expression 9.8 / ( 12.3 + 7.6 ) is 0.492462311557789
Value of expression 1928.3746 is 1928.3746
Value of expression ( 78 - 901 ) * ( 234 + 56 ) is -238670.0


Enter the input filename: HW1 Test Input2.txt
Value of expression 11111 is 11111.0
Value of expression 222. - 22.  0.22 is 0.0
Value of expression 33.  /   444.0 is 0.07432432432432433
Value of expression 9. * 3. + is 0.0
Value of expression 55. *  6. - 77. is 253.0
Value of expression 1010. /  is 0.0
Value of expression 8888.  +  -99.  /  33. is 8885.0

 * 
 * */
