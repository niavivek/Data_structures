/*Name of program - Driver.java
Student name - Nianthrini Vivekanandan
Current Date - 05/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A driver class to test the implementation of a binary search tree.
This class also visits the dates in the tree based on month and based on year. 
An indented tree is written to the screen, based on month or year ordering.
It also traverses the tree post-order, and also checks the insert, delete and contains
options of the tree.
 */
//package hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

	public static Scanner userScanner = new Scanner(System.in);

	// opens a text file for input, returns a Scanner:
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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BST<Date> value1 = new BST<>(new YearComparator());
		BST<Date> value2 = new BST<>(new MonthComparator());

		Scanner fileInput = openInputFile();
		if (fileInput != null) {
			int inputMo = 1, inputDy = 1, inputYr = 1000;

			while (fileInput.hasNext()) // getting the first Date from the file
			{
				Date date1 = new Date();
				inputMo = fileInput.nextInt();
				inputDy = fileInput.nextInt();
				inputYr = fileInput.nextInt();
				if (date1.setDate(inputMo, inputDy, inputYr)) {
					value1.insert(date1);
					value2.insert(date1);
					// System.out.println(value1.getSize());
				} else
					System.out.println("Invalid date in file: " + inputMo + "-" + inputDy + "-" + inputYr);
			}
			fileInput.close();

			System.out.println("\nYear-ordered tree has:");
			YearVisitor yVisit = new YearVisitor();
			value1.inorder(yVisit);

			System.out.println("\nMonth-ordered tree has:");
			MonthVisitor mVisit = new MonthVisitor();
			value2.inorder(mVisit);

			System.out.println("\nTesting Year-ordered tree:");
			testBST(value1);

			System.out.println("\nTesting Month-ordered tree:");
			testBST(value2);

			System.out.println("\nYear-ordered indented tree:");
			value1.writeIndentedTree(System.out);

			System.out.println("\nMonth-ordered indented tree:");
			value2.writeIndentedTree(System.out);

			System.out.println("\nStudent Testing Year-ordered tree:");
			testFirstLast(value1);

			System.out.println("\nStudent Testing Month-ordered tree:");
			testFirstLast(value2);

			System.out.println("\nPostorder traversal of updated Year-ordered tree:");
			value1.postorder(new YearVisitor());

			System.out.println("\nNow the updated Month-ordered indented tree has");
			value2.writeIndentedTree(System.out);

		} else
			System.out.println("Cannot open file - End program");

	}

	public static void testBST(BST<Date> tree) {
		int inputMo = 1, inputDy = 1, inputYr = 1000;
		Date[] dates = new Date[4];
		Date tempDate;
		Scanner infile;

		System.out.println("\n(Use the SAME file you just used)");
		infile = openInputFile();
		if (infile == null) // open the same file you used for the simulation
		{
			System.out.println("Unable to open input file for testing\n");
			return;
		}
		if (infile.hasNext()) // getting the first Date from the file
		{
			inputMo = infile.nextInt();
			inputDy = infile.nextInt();
			inputYr = infile.nextInt();
			dates[0] = new Date(inputMo, inputDy, inputYr);
		}
		for (int count = 0; count < 4 && infile.hasNext(); ++count) {
			infile.nextLine(); // skip next 3 lines, after last \n
		}
		if (infile.hasNext()) // getting the 4th Date from the file
		{
			inputMo = infile.nextInt();
			inputDy = infile.nextInt();
			inputYr = infile.nextInt();
			dates[1] = new Date(inputMo, inputDy, inputYr);
		}
		while (infile.hasNext())// going to the end of the file
		{
			inputMo = infile.nextInt();
			inputDy = infile.nextInt();
			inputYr = infile.nextInt();
		}
		infile.close(); // done with input file

		dates[2] = new Date(inputMo, inputDy, inputYr);// saving the last in the
														// file
		dates[3] = new Date(); // assigning default Date
		for (int i = 0; i < dates.length; ++i) {
			System.out.println("\nTesting if " + dates[i] + " is in the tree...");
			if (tree.contains(dates[i]))
				System.out.println("The tree's contains method found " + dates[i]);
			else
				System.out.println("The tree's contains method didn't find " + dates[i]);
			tempDate = tree.getEntry(dates[i]);
			if (tempDate != null)
				System.out.println("The tree's getEntry method returned: " + tempDate);
			else
				System.out.println("The tree's getEntry couldn't return: " + dates[i]);
		}
		System.out.println("\nTesting constructor with array...");
		BST<Date> testBST = new BST<>(dates, new YearComparator());
		testBST.writeIndentedTree(System.out);
		testBST.clear();
	} // end testBST

	public static void testFirstLast(BST<Date> tree) {
		Date dateFirst = tree.getFirst();
		Date dateLast = tree.getLast();

		System.out.println("getFirst() returns " + dateFirst);
		System.out.println("getLast() returns " + dateLast);

		if (tree.delete(dateFirst))
			System.out.println("Deletion of " + dateFirst + " successful!");
		else
			System.out.println("Could not delete " + dateFirst + " successfully");

		if (tree.insert(dateLast))
			System.out.println("Insertion of " + dateLast + " returned true");
		else
			System.out.println("Insertion of " + dateLast + " returned false");

		if (tree.insert(dateFirst))
			System.out.println("Insertion of " + dateFirst + " again successful!");
		else
			System.out.println("Could not insert " + dateFirst);

	}

}
/*
 * OUTPUT
 * 
Enter the input filename: HW4 Test Input1.txt
Invalid date in file: 4-31-2009

Year-ordered tree has:
1923-12-10
1933-11-10
1947-4-9
1949-11-15
1951-6-5
1960-2-1
1963-8-4
1972-2-25
1979-6-5

Month-ordered tree has:
2/1/1960
2/25/1972
4/9/1947
6/5/1951
6/5/1979
8/4/1963
11/10/1933
11/15/1949
12/10/1923

Testing Year-ordered tree:

(Use the SAME file you just used)
Enter the input filename: HW4 Test Input1.txt

Testing if 6/5/1951 is in the tree...
The tree's contains method found 6/5/1951
The tree's getEntry method returned: 6/5/1951

Testing if 12/10/1923 is in the tree...
The tree's contains method found 12/10/1923
The tree's getEntry method returned: 12/10/1923

Testing if 2/1/1960 is in the tree...
The tree's contains method found 2/1/1960
The tree's getEntry method returned: 2/1/1960

Testing if 1/1/1000 is in the tree...
The tree's contains method didn't find 1/1/1000
The tree's getEntry couldn't return: 1/1/1000

Testing constructor with array...
 1. 6/5/1951
      2. 2/1/1960
      2. 12/10/1923
           3. 1/1/1000

Testing Month-ordered tree:

(Use the SAME file you just used)
Enter the input filename: HW4 Test Input1.txt

Testing if 6/5/1951 is in the tree...
The tree's contains method found 6/5/1951
The tree's getEntry method returned: 6/5/1951

Testing if 12/10/1923 is in the tree...
The tree's contains method found 12/10/1923
The tree's getEntry method returned: 12/10/1923

Testing if 2/1/1960 is in the tree...
The tree's contains method found 2/1/1960
The tree's getEntry method returned: 2/1/1960

Testing if 1/1/1000 is in the tree...
The tree's contains method didn't find 1/1/1000
The tree's getEntry couldn't return: 1/1/1000

Testing constructor with array...
 1. 6/5/1951
      2. 2/1/1960
      2. 12/10/1923
           3. 1/1/1000

Year-ordered indented tree:
 1. 6/5/1951
      2. 2/25/1972
           3. 6/5/1979
           3. 8/4/1963
                4. 2/1/1960
      2. 11/10/1933
           3. 4/9/1947
                4. 11/15/1949
           3. 12/10/1923

Month-ordered indented tree:
 1. 6/5/1951
      2. 11/10/1933
           3. 12/10/1923
                4. 11/15/1949
           3. 6/5/1979
                4. 8/4/1963
      2. 2/25/1972
           3. 4/9/1947
           3. 2/1/1960

Student Testing Year-ordered tree:
getFirst() returns 12/10/1923
getLast() returns 6/5/1979
Deletion of 12/10/1923 successful!
Insertion of 6/5/1979 returned true
Insertion of 12/10/1923 again successful!

Student Testing Month-ordered tree:
getFirst() returns 2/1/1960
getLast() returns 12/10/1923
Deletion of 2/1/1960 successful!
Insertion of 12/10/1923 returned true
Insertion of 2/1/1960 again successful!

Postorder traversal of updated Year-ordered tree:
1923-12-10
1949-11-15
1947-4-9
1933-11-10
1960-2-1
1963-8-4
1979-6-5
1979-6-5
1972-2-25
1951-6-5

Now the updated Month-ordered indented tree has
 1. 6/5/1951
      2. 11/10/1933
           3. 12/10/1923
                4. 12/10/1923
                4. 11/15/1949
           3. 6/5/1979
                4. 8/4/1963
      2. 2/25/1972
           3. 4/9/1947
           3. 2/1/1960
 * 
 * 
 Enter the input filename: HW4 Test Input2.txt
Invalid date in file: 2-29-1941

Year-ordered tree has:
1932-2-17
1934-5-11
1935-9-2
1936-1-9
1937-12-24
1940-4-16
1942-4-1
1953-5-28
1953-9-13
1955-8-25
1955-9-7
1962-1-17
1972-9-23
1973-3-18
1977-8-10
1977-8-11
1978-10-14
1984-7-30
1984-9-30
1987-4-28
1990-5-11
1994-1-21
1994-5-27
1994-11-8
1995-2-9
2000-8-13
2002-3-31
2004-12-17
2008-8-6
2010-7-4
2014-9-11
2017-8-22

Month-ordered tree has:
1/9/1936
1/17/1962
1/21/1994
2/9/1995
2/17/1932
3/18/1973
3/31/2002
4/1/1942
4/16/1940
4/28/1987
5/11/1934
5/11/1990
5/27/1994
5/28/1953
7/4/2010
7/30/1984
8/6/2008
8/10/1977
8/11/1977
8/13/2000
8/22/2017
8/25/1955
9/2/1935
9/7/1955
9/11/2014
9/13/1953
9/23/1972
9/30/1984
10/14/1978
11/8/1994
12/17/2004
12/24/1937

Testing Year-ordered tree:

(Use the SAME file you just used)
Enter the input filename: HW4 Test Input2.txt

Testing if 7/30/1984 is in the tree...
The tree's contains method found 7/30/1984
The tree's getEntry method returned: 7/30/1984

Testing if 9/23/1972 is in the tree...
The tree's contains method found 9/23/1972
The tree's getEntry method returned: 9/23/1972

Testing if 12/17/2004 is in the tree...
The tree's contains method found 12/17/2004
The tree's getEntry method returned: 12/17/2004

Testing if 1/1/1000 is in the tree...
The tree's contains method didn't find 1/1/1000
The tree's getEntry couldn't return: 1/1/1000

Testing constructor with array...
 1. 7/30/1984
      2. 12/17/2004
      2. 9/23/1972
           3. 1/1/1000

Testing Month-ordered tree:

(Use the SAME file you just used)
Enter the input filename: HW4 Test Input2.txt

Testing if 7/30/1984 is in the tree...
The tree's contains method found 7/30/1984
The tree's getEntry method returned: 7/30/1984

Testing if 9/23/1972 is in the tree...
The tree's contains method found 9/23/1972
The tree's getEntry method returned: 9/23/1972

Testing if 12/17/2004 is in the tree...
The tree's contains method found 12/17/2004
The tree's getEntry method returned: 12/17/2004

Testing if 1/1/1000 is in the tree...
The tree's contains method didn't find 1/1/1000
The tree's getEntry couldn't return: 1/1/1000

Testing constructor with array...
 1. 7/30/1984
      2. 12/17/2004
      2. 9/23/1972
           3. 1/1/1000

Year-ordered indented tree:
 1. 7/30/1984
      2. 8/13/2000
           3. 7/4/2010
                4. 9/11/2014
                     5. 8/22/2017
                4. 8/6/2008
                     5. 3/31/2002
                          6. 12/17/2004
           3. 5/27/1994
                4. 11/8/1994
                     5. 2/9/1995
                4. 4/28/1987
                     5. 5/11/1990
                          6. 1/21/1994
                     5. 9/30/1984
      2. 12/24/1937
           3. 9/23/1972
                4. 8/11/1977
                     5. 10/14/1978
                     5. 3/18/1973
                          6. 8/10/1977
                4. 5/28/1953
                     5. 8/25/1955
                          6. 1/17/1962
                               7. 9/7/1955
                          6. 9/13/1953
                     5. 4/1/1942
                          6. 4/16/1940
           3. 5/11/1934
                4. 9/2/1935
                     5. 1/9/1936
                4. 2/17/1932

Month-ordered indented tree:
 1. 7/30/1984
      2. 8/13/2000
           3. 12/24/1937
                4. 9/23/1972
                     5. 11/8/1994
                          6. 12/17/2004
                          6. 9/30/1984
                               7. 10/14/1978
                     5. 8/25/1955
                          6. 9/13/1953
                               7. 9/7/1955
                                    8. 9/11/2014
                                    8. 9/2/1935
                          6. 8/22/2017
           3. 8/11/1977
                4. 8/6/2008
                     5. 8/10/1977
      2. 5/27/1994
           3. 5/28/1953
                4. 7/4/2010
           3. 4/28/1987
                4. 5/11/1934
                     5. 5/11/1990
                4. 1/17/1962
                     5. 3/31/2002
                          6. 4/1/1942
                               7. 4/16/1940
                          6. 3/18/1973
                               7. 2/17/1932
                                    8. 2/9/1995
                                         9. 1/21/1994
                     5. 1/9/1936

Student Testing Year-ordered tree:
getFirst() returns 2/17/1932
getLast() returns 8/22/2017
Deletion of 2/17/1932 successful!
Insertion of 8/22/2017 returned true
Insertion of 2/17/1932 again successful!

Student Testing Month-ordered tree:
getFirst() returns 1/9/1936
getLast() returns 12/24/1937
Deletion of 1/9/1936 successful!
Insertion of 12/24/1937 returned true
Insertion of 1/9/1936 again successful!

Postorder traversal of updated Year-ordered tree:
1932-2-17
1936-1-9
1935-9-2
1934-5-11
1940-4-16
1942-4-1
1953-9-13
1955-9-7
1962-1-17
1955-8-25
1953-5-28
1977-8-10
1973-3-18
1978-10-14
1977-8-11
1972-9-23
1937-12-24
1984-9-30
1994-1-21
1990-5-11
1987-4-28
1995-2-9
1994-11-8
1994-5-27
2004-12-17
2002-3-31
2008-8-6
2017-8-22
2017-8-22
2014-9-11
2010-7-4
2000-8-13
1984-7-30

Now the updated Month-ordered indented tree has
 1. 7/30/1984
      2. 8/13/2000
           3. 12/24/1937
                4. 12/24/1937
                4. 9/23/1972
                     5. 11/8/1994
                          6. 12/17/2004
                          6. 9/30/1984
                               7. 10/14/1978
                     5. 8/25/1955
                          6. 9/13/1953
                               7. 9/7/1955
                                    8. 9/11/2014
                                    8. 9/2/1935
                          6. 8/22/2017
           3. 8/11/1977
                4. 8/6/2008
                     5. 8/10/1977
      2. 5/27/1994
           3. 5/28/1953
                4. 7/4/2010
           3. 4/28/1987
                4. 5/11/1934
                     5. 5/11/1990
                4. 1/17/1962
                     5. 3/31/2002
                          6. 4/1/1942
                               7. 4/16/1940
                          6. 3/18/1973
                               7. 2/17/1932
                                    8. 2/9/1995
                                         9. 1/21/1994
                     5. 1/9/1936
 * 
 * 
 * 
 * 
Enter the input filename: HW4 Input.txt
Invalid date in file: 9-31-1939

Year-ordered tree has:
1934-11-28
1935-9-23
1937-11-8
1939-7-18
1940-4-29
1947-8-17
1953-2-10
1955-10-20
1962-12-30
1966-12-17
1981-3-25
1981-3-27
1983-7-28
1995-10-5
1999-9-23
2002-8-18
2007-12-24
2010-8-17
2013-11-30
2016-8-28
2024-9-18
2028-9-28

Month-ordered tree has:
2/10/1953
3/25/1981
3/27/1981
4/29/1940
7/18/1939
7/28/1983
8/17/1947
8/17/2010
8/18/2002
8/28/2016
9/18/2024
9/23/1935
9/23/1999
9/28/2028
10/5/1995
10/20/1955
11/8/1937
11/28/1934
11/30/2013
12/17/1966
12/24/2007
12/30/1962

Testing Year-ordered tree:

(Use the SAME file you just used)
Enter the input filename: HW4 Input.txt

Testing if 9/23/1999 is in the tree...
The tree's contains method found 9/23/1999
The tree's getEntry method returned: 9/23/1999

Testing if 3/27/1981 is in the tree...
The tree's contains method found 3/27/1981
The tree's getEntry method returned: 3/27/1981

Testing if 11/28/1934 is in the tree...
The tree's contains method found 11/28/1934
The tree's getEntry method returned: 11/28/1934

Testing if 1/1/1000 is in the tree...
The tree's contains method didn't find 1/1/1000
The tree's getEntry couldn't return: 1/1/1000

Testing constructor with array...
 1. 9/23/1999
      2. 3/27/1981
           3. 11/28/1934
                4. 1/1/1000

Testing Month-ordered tree:

(Use the SAME file you just used)
Enter the input filename: HW4 Input.txt

Testing if 9/23/1999 is in the tree...
The tree's contains method found 9/23/1999
The tree's getEntry method returned: 9/23/1999

Testing if 3/27/1981 is in the tree...
The tree's contains method found 3/27/1981
The tree's getEntry method returned: 3/27/1981

Testing if 11/28/1934 is in the tree...
The tree's contains method found 11/28/1934
The tree's getEntry method returned: 11/28/1934

Testing if 1/1/1000 is in the tree...
The tree's contains method didn't find 1/1/1000
The tree's getEntry couldn't return: 1/1/1000

Testing constructor with array...
 1. 9/23/1999
      2. 3/27/1981
           3. 11/28/1934
                4. 1/1/1000

Year-ordered indented tree:
 1. 9/23/1999
      2. 8/18/2002
           3. 11/30/2013
                4. 9/18/2024
                     5. 9/28/2028
                     5. 8/28/2016
                4. 12/24/2007
                     5. 8/17/2010
      2. 10/20/1955
           3. 3/27/1981
                4. 10/5/1995
                     5. 7/28/1983
                4. 12/30/1962
                     5. 12/17/1966
                          6. 3/25/1981
           3. 8/17/1947
                4. 2/10/1953
                4. 4/29/1940
                     5. 9/23/1935
                          6. 7/18/1939
                               7. 11/8/1937
                          6. 11/28/1934

Month-ordered indented tree:
 1. 9/23/1999
      2. 11/30/2013
           3. 12/24/2007
                4. 12/30/1962
                4. 12/17/1966
           3. 10/20/1955
                4. 11/8/1937
                     5. 11/28/1934
                4. 9/28/2028
                     5. 10/5/1995
      2. 8/18/2002
           3. 9/18/2024
                4. 9/23/1935
                4. 8/28/2016
           3. 3/27/1981
                4. 8/17/1947
                     5. 8/17/2010
                     5. 4/29/1940
                          6. 7/18/1939
                               7. 7/28/1983
                4. 2/10/1953
                     5. 3/25/1981

Student Testing Year-ordered tree:
getFirst() returns 11/28/1934
getLast() returns 9/28/2028
Deletion of 11/28/1934 successful!
Insertion of 9/28/2028 returned true
Insertion of 11/28/1934 again successful!

Student Testing Month-ordered tree:
getFirst() returns 2/10/1953
getLast() returns 12/30/1962
Deletion of 2/10/1953 successful!
Insertion of 12/30/1962 returned true
Insertion of 2/10/1953 again successful!

Postorder traversal of updated Year-ordered tree:
1934-11-28
1937-11-8
1939-7-18
1935-9-23
1940-4-29
1953-2-10
1947-8-17
1981-3-25
1966-12-17
1962-12-30
1983-7-28
1995-10-5
1981-3-27
1955-10-20
2010-8-17
2007-12-24
2016-8-28
2028-9-28
2028-9-28
2024-9-18
2013-11-30
2002-8-18
1999-9-23

Now the updated Month-ordered indented tree has
 1. 9/23/1999
      2. 11/30/2013
           3. 12/24/2007
                4. 12/30/1962
                     5. 12/30/1962
                4. 12/17/1966
           3. 10/20/1955
                4. 11/8/1937
                     5. 11/28/1934
                4. 9/28/2028
                     5. 10/5/1995
      2. 8/18/2002
           3. 9/18/2024
                4. 9/23/1935
                4. 8/28/2016
           3. 3/27/1981
                4. 8/17/1947
                     5. 8/17/2010
                     5. 4/29/1940
                          6. 7/18/1939
                               7. 7/28/1983
                4. 3/25/1981
                     5. 2/10/1953
 * 
 */
