package ExtraCredit_shellsort;
/*Name of program - Driver_sort.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A driver class to test the implementation of different gaps for
shell sort. The gaps used are Hibbard's and Frank & Lazarus.
 */
public class Driver_sort{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arraySize = 20000;
		Integer[] arrayInt = new Integer[arraySize]; 
		Integer[] arrayInt1 = new Integer[arraySize]; 
		Integer[] arrayInt2 = new Integer[arraySize]; 
		
		for(int i=0;i<arraySize;i++)
		{
			arrayInt[i] = (int) (Math.random()*1000);
			arrayInt1[i] = (int) (Math.random()*1000);
			arrayInt2[i] = (int) (Math.random()*1000);
		}	
		testMethod(arrayInt,arraySize);
		testMethodFL(arrayInt1,arraySize);
		testMethodHibbard(arrayInt2,arraySize);
		
	}
	public static void testMethod(Integer[] arr, int n)
	{
		long startTime, stopTime; // for timing
	     double elapsedTime = 0; // for timing
	     
	     
	     startTime = System.nanoTime();        // start timing ____ sort----------
	     
	     // call sort method here
	     SortMethods.shellSort(arr,0,n-1,1);

	     stopTime = System.nanoTime();        // stop timing ---------------------
	     elapsedTime =(double)(stopTime - startTime)/1000000.0;

	     System.out.println("\nUsing Shell sort ---N: " + n + " _____ Sort Time: "
	             + elapsedTime + " milliseconds.");
	}
	public static void testMethodFL(Integer[] arr, int n)
	{
		long startTime, stopTime; // for timing
	     double elapsedTime = 0; // for timing
	     
	     
	     startTime = System.nanoTime();        // start timing ____ sort----------
	     
	     // call sort method here
	     SortMethods.shellSort(arr,0,n-1,2);

	     stopTime = System.nanoTime();        // stop timing ---------------------
	     elapsedTime =(double)(stopTime - startTime)/1000000.0;

	     System.out.println("\nUsing Shell sort type Frank & Lazarus---N: " + n + " _____ Sort Time: "
	             + elapsedTime + " milliseconds.");
	}
	public static void testMethodHibbard(Integer[] arr, int n)
	{
		long startTime, stopTime; // for timing
	     double elapsedTime = 0; // for timing
	     
	     
	     startTime = System.nanoTime();        // start timing ____ sort----------
	     
	     // call sort method here
	     SortMethods.shellSort(arr,0,n-1,3);

	     stopTime = System.nanoTime();        // stop timing ---------------------
	     elapsedTime =(double)(stopTime - startTime)/1000000.0;

	     System.out.println("\nUsing Shell sort type Hibbard ---N: " + n + " _____ Sort Time: "
	             + elapsedTime + " milliseconds.");
	}
	
}
/*OUTPUT
 *
 *


Using Shell sort ---N: 5000 _____ Sort Time: 19.373 milliseconds.

Using Shell sort type Frank & Lazarus---N: 5000 _____ Sort Time: 13.571 milliseconds.

Using Shell sort type Hibbard ---N: 5000 _____ Sort Time: 1.398 milliseconds.


Using Shell sort ---N: 10000 _____ Sort Time: 29.786 milliseconds.

Using Shell sort type Frank & Lazarus---N: 10000 _____ Sort Time: 3.763 milliseconds.

Using Shell sort type Hibbard ---N: 10000 _____ Sort Time: 4.281 milliseconds.


Using Shell sort ---N: 20000 _____ Sort Time: 43.572 milliseconds.

Using Shell sort type Frank & Lazarus---N: 20000 _____ Sort Time: 8.385 milliseconds.

Using Shell sort type Hibbard ---N: 20000 _____ Sort Time: 8.216 milliseconds.
 * */
