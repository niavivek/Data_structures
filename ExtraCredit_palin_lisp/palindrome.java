package ExtraCredit_palin_lisp;
/*Name of program - palindrome.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class than implements methods check is an expression is a palindrome.
 */
import java.util.Scanner;

public class palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Input a String to check if it is a palindrome");
		String input = in.nextLine();
		if (palindromeCheck(input))
			System.out.println(input + " is a palindrome.");
		else
			System.out.println(input + " is not a palindrome.");
	}

	private static boolean palindromeCheck(String input) {
		boolean result = false;
		StackInterface<String> palinStack = new ArrayStack<>(100);
		StackInterface<String> palinStackCopy = new ArrayStack<>(100);
		StackInterface<String> palinStackRev = new ArrayStack<>(100);
		String value;
		for (int i = 0; i < input.length(); i++) {
			value = Character.toString(input.charAt(i));
			if (value.matches("[a-zA-Z]+")) {
				palinStack.push(value);
				palinStackCopy.push(value);
			}
		}
		while (palinStackCopy.size() != 0) {
			palinStackRev.push(palinStackCopy.pop());
		}
		while (!palinStackRev.isEmpty()) {
			if ((palinStackRev.pop()).equalsIgnoreCase(palinStack.pop())) {
				result = true;
			} 
			else {
				result = false;
				return result;
			}
		}
		return result;

	}

}
/*OUTPUT
 * 
 * Input a String to check if it is a palindrome
Race car.
Race car. is a palindrome.

Input a String to check if it is a palindrome
Banana
Banana is not a palindrome.

Input a String to check if it is a palindrome
A man, a plan, a canal – Panama!
A man, a plan, a canal – Panama! is a palindrome.

 */
 
