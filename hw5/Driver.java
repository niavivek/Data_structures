/*Name of program - Driver.java
Student name - Nianthrini Vivekanandan
Current Date - 06/05/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A driver to insert ipaddress to HashSC and HashQP and test and display the hash
tables and statistics.
 */
package hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	public static Scanner userScanner = new Scanner(System.in);

	// opens a text file for input, returns a Scanner:
	public static Scanner openInputFile()
	{
		String filename;
        	Scanner scanner=null;
        
		System.out.print("Enter the input filename: ");
		filename = userScanner.nextLine();
        	File file= new File(filename);

        	try{
        		scanner = new Scanner(file);
        	}// end try
        	catch(FileNotFoundException fe){
        	   System.out.println("Can't open input file\n");
       	    return null; // array of 0 elements
        	} // end catch
        	return scanner;
	}


// Call the following in main for EACH of the HashTables:

	public static void testHashTable(HashTable<IpAddress> table,
							IpAddress targetIpAddress)
	{
		IpAddress tempIpAddress;
		// found a IpAddress in table1 in table2
		tempIpAddress = table.getEntry(targetIpAddress);
		if( tempIpAddress != null )
		{
			System.out.println("Retrieved in HashTable, IpAddress: " + tempIpAddress
					+ ", now trying to delete it");
			// now delete it 
			testRemove( table, targetIpAddress ); // YOU WRITE THIS (SEE SPECS BELOW)
		}
		else
			System.out.println("Error in HashTable: can't retrieve "+ targetIpAddress);
		
	} // testHashTable


	public static void testRemove(HashTable<IpAddress> table,
							IpAddress targetIpAddress)
	{
		// YOU FINISH SO THIS METHOD DOES THE FOLLOWING
		//     Call the HashTable's remove method, passing the targetIpAdddress
		//     If it returns true, display a message that it was removed (and display targetIpAddress)
		//     If it returns false, display a message that the remove failed 
		//			(and display targetIpAddress
		// Make sure you don't call the remove() method more than once in this method!
		if(table.remove(targetIpAddress))
			System.out.println("Removed "+targetIpAddress+" successfully!");
		else
			System.out.println("Could not remove "+targetIpAddress);

	}
	
	public static IpAddress putEntry(HashTable<IpAddress> var1,HashTable<IpAddress> var2)
	{
		//open file
		Scanner fileInput = openInputFile();
		//create a new ipaddress object
		IpAddress a1 = new IpAddress();
		String address;
		if (fileInput != null) {
			//if file is not empty
			while(fileInput.hasNext())
			{//input the value to a new ipadress object
				address = fileInput.next().trim();
				a1 = new IpAddress(address);
				//insert the values in the hashtables
				if(var1.insert(a1))
					System.out.println("Inserted into HashSC: "+a1.toString());
				if(var2.insert(a1))
					System.out.println("Inserted into HashQP: "+a1.toString());
			}
			fileInput.close();
			//return the last entered ipaddress
			return a1;
		}
		return null;	
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create 2 hashtable variables
		HashTable<IpAddress> var1 = new HashSC<>(new IpAddressValueHasher(), new IpAddressValueComparator());
		HashTable<IpAddress> var2 = new HashQP<>(new IpAddressStringHasher(),new IpAddressStringComparator());
		//insert the values in the hashtable
		IpAddress retVal = putEntry(var1,var2);
		if(retVal != null)
		{
			//if the file was not empty do some tests
			//display table values and the statistics
			System.out.println("\nContents of HashSC with the IpValue key:");
			var1.displayTable();
			var1.displayStatistics();
			
			System.out.println("\nContents of HashQP with the String key:");
			var2.displayTable();
			var2.displayStatistics();
			//call the test methods
			System.out.println("\nTesting HashSC with IpValue key:");
			if(var1.contains(retVal))
				testHashTable(var1,retVal);
			else
				System.out.println("HashSC does not contain: "+retVal);
			
			System.out.println("\nTesting HashQP with String key:");
			if(var2.contains(retVal))
				testHashTable(var2,retVal);
			else
				System.out.println("HashQP does not contain: "+retVal);
			//display contents of the hashtable after removing a value
			System.out.println("\nNow the contents of HashSC with the IpValue key has:");
			var1.displayTable();
			System.out.println("\nNow the contents of HashQP with the IpValue key has:");
			var2.displayTable();
		}
		else
			System.out.println("Unable to read the file - End program");
	}
}

/*
 * OUTPUT
 
 * Enter the input filename: HW5TestInput1.txt
Inserted into HashSC: 203.255.234.105, 3422546537
Inserted into HashQP: 203.255.234.105, 3422546537
Inserted into HashSC: 203.255.234.102, 3422546534
Inserted into HashQP: 203.255.234.102, 3422546534
Inserted into HashSC: 203.255.234.103, 3422546535
Inserted into HashQP: 203.255.234.103, 3422546535
Inserted into HashSC: 203.255.234.106, 3422546538
Inserted into HashQP: 203.255.234.106, 3422546538
Inserted into HashSC: 203.141.52.41, 3415028777
Inserted into HashQP: 203.141.52.41, 3415028777
Inserted into HashSC: 203.141.52.42, 3415028778
Inserted into HashQP: 203.141.52.42, 3415028778
Inserted into HashSC: 203.141.52.43, 3415028779
Inserted into HashQP: 203.141.52.43, 3415028779
Inserted into HashSC: 203.141.52.44, 3415028780
Inserted into HashQP: 203.141.52.44, 3415028780
Inserted into HashSC: 207.126.239.224, 3481202656
Inserted into HashQP: 207.126.239.224, 3481202656
Inserted into HashSC: 203.141.52.45, 3415028781
Inserted into HashQP: 203.141.52.45, 3415028781
Inserted into HashSC: 203.141.52.46, 3415028782
Inserted into HashQP: 203.141.52.46, 3415028782
Inserted into HashSC: 203.141.52.47, 3415028783
Inserted into HashQP: 203.141.52.47, 3415028783
Inserted into HashSC: 206.190.43.125, 3468569469
Inserted into HashQP: 206.190.43.125, 3468569469
Inserted into HashSC: 206.190.43.81, 3468569425
Inserted into HashQP: 206.190.43.81, 3468569425

Contents of HashSC with the IpValue key:
0: ----
1: ----
2: ----
3: ----
4: ----
5: ----
6: ----
7: ----
8: ----
9: ----
10: ----
11: ----
12: ----
13: ----
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: ----
21: ----
22: ----
23: ----
24: ----
25: ----
26: ----
27: ----
28: ----
29: ----
30: ----
31: 206.190.43.81, 3468569425
32: ----
33: ----
34: ----
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: ----
42: ----
43: ----
44: ----
45: ----
46: ----
47: ----
48: ----
49: ----
50: ----
51: 203.255.234.102, 3422546534
52: 203.255.234.103, 3422546535
53: ----
54: 203.255.234.105, 3422546537
55: 203.255.234.106, 3422546538
56: ----
57: ----
58: ----
59: ----
60: ----
61: ----
62: ----
63: ----
64: ----
65: ----
66: ----
67: ----
68: ----
69: ----
70: ----
71: ----
72: ----
73: ----
74: ----
75: 206.190.43.125, 3468569469
76: ----
77: ----
78: ----
79: 207.126.239.224, 3481202656
80: ----
81: ----
82: ----
83: ----
84: ----
85: 203.141.52.41, 3415028777
86: 203.141.52.42, 3415028778
87: 203.141.52.43, 3415028779
88: 203.141.52.44, 3415028780
89: 203.141.52.45, 3415028781
90: 203.141.52.46, 3415028782
91: 203.141.52.47, 3415028783
92: ----
93: ----
94: ----
95: ----
96: ----

In the HashSC object:

Table Size = 97
Number of entries = 14
Load factor = 0.14432989690721648
Number of collisions = 0
Longest Linked List = 1

Contents of HashQP with the String key:
0: 203.255.234.103, 3422546535
1: ----
2: 203.255.234.105, 3422546537
3: 203.255.234.106, 3422546538
4: ----
5: ----
6: ----
7: ----
8: ----
9: ----
10: ----
11: ----
12: ----
13: ----
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: 207.126.239.224, 3481202656
21: ----
22: ----
23: ----
24: ----
25: ----
26: 206.190.43.125, 3468569469
27: ----
28: ----
29: ----
30: 206.190.43.81, 3468569425
31: ----
32: ----
33: ----
34: ----
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: ----
42: ----
43: ----
44: ----
45: ----
46: ----
47: ----
48: ----
49: ----
50: ----
51: ----
52: ----
53: ----
54: ----
55: ----
56: ----
57: ----
58: ----
59: ----
60: ----
61: ----
62: ----
63: ----
64: ----
65: ----
66: ----
67: ----
68: ----
69: ----
70: ----
71: ----
72: ----
73: ----
74: ----
75: 203.141.52.41, 3415028777
76: 203.141.52.42, 3415028778
77: 203.141.52.43, 3415028779
78: 203.141.52.44, 3415028780
79: 203.141.52.45, 3415028781
80: 203.141.52.46, 3415028782
81: 203.141.52.47, 3415028783
82: ----
83: ----
84: ----
85: ----
86: ----
87: ----
88: ----
89: ----
90: ----
91: ----
92: ----
93: ----
94: ----
95: ----
96: 203.255.234.102, 3422546534

In the HashQP object:

Table Size = 97
Number of entries = 14
Load factor = 0.14432989690721648
Number of collisions = 0
Longest Collision Run = 0

Testing HashSC with IpValue key:
Retrieved in HashTable, IpAddress: 206.190.43.81, 3468569425, now trying to delete it
Removed 206.190.43.81, 3468569425 successfully!

Testing HashQP with String key:
Retrieved in HashTable, IpAddress: 206.190.43.81, 3468569425, now trying to delete it
Removed 206.190.43.81, 3468569425 successfully!

Now the contents of HashSC with the IpValue key has:
0: ----
1: ----
2: ----
3: ----
4: ----
5: ----
6: ----
7: ----
8: ----
9: ----
10: ----
11: ----
12: ----
13: ----
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: ----
21: ----
22: ----
23: ----
24: ----
25: ----
26: ----
27: ----
28: ----
29: ----
30: ----
31: ----
32: ----
33: ----
34: ----
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: ----
42: ----
43: ----
44: ----
45: ----
46: ----
47: ----
48: ----
49: ----
50: ----
51: 203.255.234.102, 3422546534
52: 203.255.234.103, 3422546535
53: ----
54: 203.255.234.105, 3422546537
55: 203.255.234.106, 3422546538
56: ----
57: ----
58: ----
59: ----
60: ----
61: ----
62: ----
63: ----
64: ----
65: ----
66: ----
67: ----
68: ----
69: ----
70: ----
71: ----
72: ----
73: ----
74: ----
75: 206.190.43.125, 3468569469
76: ----
77: ----
78: ----
79: 207.126.239.224, 3481202656
80: ----
81: ----
82: ----
83: ----
84: ----
85: 203.141.52.41, 3415028777
86: 203.141.52.42, 3415028778
87: 203.141.52.43, 3415028779
88: 203.141.52.44, 3415028780
89: 203.141.52.45, 3415028781
90: 203.141.52.46, 3415028782
91: 203.141.52.47, 3415028783
92: ----
93: ----
94: ----
95: ----
96: ----

Now the contents of HashQP with the IpValue key has:
0: 203.255.234.103, 3422546535
1: ----
2: 203.255.234.105, 3422546537
3: 203.255.234.106, 3422546538
4: ----
5: ----
6: ----
7: ----
8: ----
9: ----
10: ----
11: ----
12: ----
13: ----
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: 207.126.239.224, 3481202656
21: ----
22: ----
23: ----
24: ----
25: ----
26: 206.190.43.125, 3468569469
27: ----
28: ----
29: ----
30: ----
31: ----
32: ----
33: ----
34: ----
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: ----
42: ----
43: ----
44: ----
45: ----
46: ----
47: ----
48: ----
49: ----
50: ----
51: ----
52: ----
53: ----
54: ----
55: ----
56: ----
57: ----
58: ----
59: ----
60: ----
61: ----
62: ----
63: ----
64: ----
65: ----
66: ----
67: ----
68: ----
69: ----
70: ----
71: ----
72: ----
73: ----
74: ----
75: 203.141.52.41, 3415028777
76: 203.141.52.42, 3415028778
77: 203.141.52.43, 3415028779
78: 203.141.52.44, 3415028780
79: 203.141.52.45, 3415028781
80: 203.141.52.46, 3415028782
81: 203.141.52.47, 3415028783
82: ----
83: ----
84: ----
85: ----
86: ----
87: ----
88: ----
89: ----
90: ----
91: ----
92: ----
93: ----
94: ----
95: ----
96: 203.255.234.102, 3422546534

Enter the input filename: HW5TestInput2.txt
Inserted into HashSC: 72.30.214, 4726486
Inserted into HashQP: 72.30.214, 4726486
Inserted into HashSC: 72.30.215, 4726487
Inserted into HashQP: 72.30.215, 4726487
Inserted into HashSC: 72.30.216, 4726488
Inserted into HashQP: 72.30.216, 4726488
Inserted into HashSC: 72.30.221, 4726493
Inserted into HashQP: 72.30.221, 4726493
Inserted into HashSC: 72.30.226, 4726498
Inserted into HashQP: 72.30.226, 4726498
Inserted into HashSC: 72.30.252, 4726524
Inserted into HashQP: 72.30.252, 4726524
Inserted into HashSC: 72.30.54, 4726326
Inserted into HashQP: 72.30.54, 4726326
Inserted into HashSC: 72.30.56, 4726328
Inserted into HashQP: 72.30.56, 4726328
Inserted into HashSC: 72.30.60, 4726332
Inserted into HashQP: 72.30.60, 4726332
Inserted into HashSC: 72.30.61, 4726333
Inserted into HashQP: 72.30.61, 4726333
Inserted into HashSC: 72.30.8.1, 1209927681
Inserted into HashQP: 72.30.8.1, 1209927681
Inserted into HashSC: 72.30.87, 4726359
Inserted into HashQP: 72.30.87, 4726359
Inserted into HashSC: 72.30.9, 4726281
Inserted into HashQP: 72.30.9, 4726281
Inserted into HashSC: 72.30.97, 4726369
Inserted into HashQP: 72.30.97, 4726369
Inserted into HashSC: 72.30.98, 4726370
Inserted into HashQP: 72.30.98, 4726370
Inserted into HashSC: 72.30.99, 4726371
Inserted into HashQP: 72.30.99, 4726371
Inserted into HashSC: 74.6.131, 4851331
Inserted into HashQP: 74.6.131, 4851331
Inserted into HashSC: 74.6.17, 4851217
Inserted into HashQP: 74.6.17, 4851217
Inserted into HashSC: 74.6.18, 4851218
Inserted into HashQP: 74.6.18, 4851218
Inserted into HashSC: 7.4.6.19, 117704211
Inserted into HashQP: 7.4.6.19, 117704211
Inserted into HashSC: 74.6.20, 4851220
Inserted into HashQP: 74.6.20, 4851220
Inserted into HashSC: 74.6.21, 4851221
Inserted into HashQP: 74.6.21, 4851221
Inserted into HashSC: 74.6.22, 4851222
Inserted into HashQP: 74.6.22, 4851222
Inserted into HashSC: 74.6.23, 4851223
Inserted into HashQP: 74.6.23, 4851223
Inserted into HashSC: 74.6.24, 4851224
Inserted into HashQP: 74.6.24, 4851224
Inserted into HashSC: 74.6.240, 4851440
Inserted into HashQP: 74.6.240, 4851440
Inserted into HashSC: 74.6.25, 4851225
Inserted into HashQP: 74.6.25, 4851225
Inserted into HashSC: 74.6.26, 4851226
Inserted into HashQP: 74.6.26, 4851226
Inserted into HashSC: 74.6.27, 4851227
Inserted into HashQP: 74.6.27, 4851227
Inserted into HashSC: 74.6.28, 4851228
Inserted into HashQP: 74.6.28, 4851228
Inserted into HashSC: 74.6.29, 4851229
Inserted into HashQP: 74.6.29, 4851229
Inserted into HashSC: 74.6.31, 4851231
Inserted into HashQP: 74.6.31, 4851231
Inserted into HashSC: 74.6.65, 4851265
Inserted into HashQP: 74.6.65, 4851265
Inserted into HashSC: 74.6.66, 4851266
Inserted into HashQP: 74.6.66, 4851266
Inserted into HashSC: 74.6.67, 4851267
Inserted into HashQP: 74.6.67, 4851267
Inserted into HashSC: 74.6.68, 4851268
Inserted into HashQP: 74.6.68, 4851268
Inserted into HashSC: 74.6.69, 4851269
Inserted into HashQP: 74.6.69, 4851269
Inserted into HashSC: 74.6.7, 4851207
Inserted into HashQP: 74.6.7, 4851207
Inserted into HashSC: 74.6.70, 4851270
Inserted into HashQP: 74.6.70, 4851270
Inserted into HashSC: 74.6.71, 4851271
Inserted into HashQP: 74.6.71, 4851271
Inserted into HashSC: 74.6.72, 4851272
Inserted into HashQP: 74.6.72, 4851272
Inserted into HashSC: 74.6.73, 4851273
Inserted into HashQP: 74.6.73, 4851273
Inserted into HashSC: 74.6.74, 4851274
Inserted into HashQP: 74.6.74, 4851274
Inserted into HashSC: 74.6.75, 4851275
Inserted into HashQP: 74.6.75, 4851275
Inserted into HashSC: 74.6.76, 4851276
Inserted into HashQP: 74.6.76, 4851276
Inserted into HashSC: 74.6.79, 4851279
Inserted into HashQP: 74.6.79, 4851279
Inserted into HashSC: 74.6.8, 4851208
Inserted into HashQP: 74.6.8, 4851208
Inserted into HashSC: 74.6.85, 4851285
Inserted into HashQP: 74.6.85, 4851285
Inserted into HashSC: 74.6.86, 4851286
Inserted into HashQP: 74.6.86, 4851286
Inserted into HashSC: 74.6.87, 4851287
Inserted into HashQP: 74.6.87, 4851287

Contents of HashSC with the IpValue key:
0: ----
1: 72.30.54, 4726326
2: ----
3: 72.30.56, 4726328
4: 74.6.65, 4851265
5: 72.30.252, 4726524 -> 74.6.66, 4851266
6: 74.6.67, 4851267
7: 72.30.60, 4726332 -> 74.6.68, 4851268
8: 72.30.61, 4726333 -> 74.6.69, 4851269
9: 74.6.70, 4851270
10: 74.6.71, 4851271
11: 74.6.72, 4851272
12: 74.6.73, 4851273
13: 74.6.74, 4851274
14: 74.6.75, 4851275
15: 74.6.76, 4851276
16: ----
17: ----
18: 74.6.79, 4851279
19: ----
20: ----
21: ----
22: ----
23: ----
24: 72.30.8.1, 1209927681 -> 74.6.85, 4851285
25: 74.6.86, 4851286
26: 74.6.87, 4851287
27: ----
28: ----
29: ----
30: ----
31: ----
32: ----
33: ----
34: 72.30.87, 4726359
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: ----
42: ----
43: 74.6.7, 4851207
44: 72.30.97, 4726369 -> 74.6.8, 4851208
45: 72.30.98, 4726370
46: 72.30.99, 4726371 -> 7.4.6.19, 117704211
47: ----
48: ----
49: ----
50: ----
51: ----
52: ----
53: 72.30.9, 4726281 -> 74.6.17, 4851217
54: 74.6.18, 4851218
55: ----
56: 74.6.20, 4851220
57: 74.6.21, 4851221
58: 74.6.22, 4851222
59: 74.6.23, 4851223
60: 74.6.24, 4851224
61: 74.6.25, 4851225
62: 74.6.26, 4851226
63: 74.6.27, 4851227
64: 72.30.214, 4726486 -> 74.6.28, 4851228
65: 72.30.215, 4726487 -> 74.6.29, 4851229
66: 72.30.216, 4726488
67: 74.6.31, 4851231
68: ----
69: ----
70: 74.6.131, 4851331
71: 72.30.221, 4726493
72: ----
73: ----
74: ----
75: ----
76: 72.30.226, 4726498
77: ----
78: ----
79: ----
80: ----
81: ----
82: 74.6.240, 4851440
83: ----
84: ----
85: ----
86: ----
87: ----
88: ----
89: ----
90: ----
91: ----
92: ----
93: ----
94: ----
95: ----
96: ----

In the HashSC object:

Table Size = 97
Number of entries = 50
Load factor = 0.5154639175257731
Number of collisions = 9
Longest Linked List = 2

Contents of HashQP with the String key:
0: 72.30.97, 4726369
1: 72.30.98, 4726370
2: 72.30.99, 4726371
3: ----
4: ----
5: ----
6: ----
7: ----
8: ----
9: ----
10: ----
11: ----
12: ----
13: ----
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: ----
21: ----
22: ----
23: ----
24: ----
25: ----
26: ----
27: ----
28: ----
29: ----
30: ----
31: ----
32: ----
33: ----
34: ----
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: ----
42: ----
43: ----
44: ----
45: ----
46: 72.30.54, 4726326
47: ----
48: 72.30.56, 4726328
49: ----
50: ----
51: ----
52: ----
53: ----
54: ----
55: 74.6.65, 4851265
56: 74.6.66, 4851266
57: 74.6.67, 4851267
58: 74.6.68, 4851268
59: 74.6.69, 4851269
60: ----
61: ----
62: ----
63: 72.30.252, 4726524
64: ----
65: ----
66: ----
67: ----
68: ----
69: 74.6.17, 4851217
70: 74.6.18, 4851218
71: ----
72: ----
73: 74.6.131, 4851331
74: ----
75: ----
76: ----
77: ----
78: ----
79: 72.30.60, 4726332
80: 72.30.61, 4726333
81: ----
82: ----
83: ----
84: ----
85: ----
86: ----
87: 74.6.70, 4851270
88: 74.6.71, 4851271
89: 74.6.72, 4851272
90: 74.6.73, 4851273
91: 74.6.74, 4851274
92: 74.6.75, 4851275
93: 74.6.76, 4851276
94: ----
95: ----
96: 74.6.79, 4851279
97: ----
98: ----
99: 74.6.20, 4851220
100: 74.6.21, 4851221
101: 74.6.22, 4851222
102: 74.6.23, 4851223
103: 74.6.24, 4851224
104: 74.6.25, 4851225
105: 74.6.26, 4851226
106: 74.6.27, 4851227
107: 74.6.28, 4851228
108: 74.6.29, 4851229
109: ----
110: ----
111: ----
112: ----
113: ----
114: 72.30.214, 4726486
115: 74.6.240, 4851440
116: 7.4.6.19, 117704211
117: 72.30.216, 4726488
118: ----
119: 72.30.215, 4726487
120: ----
121: ----
122: ----
123: ----
124: ----
125: ----
126: ----
127: ----
128: ----
129: 74.6.85, 4851285
130: 74.6.86, 4851286
131: 74.6.87, 4851287
132: ----
133: ----
134: ----
135: ----
136: ----
137: 72.30.8.1, 1209927681
138: 74.6.31, 4851231
139: ----
140: ----
141: ----
142: ----
143: 74.6.7, 4851207
144: 74.6.8, 4851208
145: ----
146: ----
147: ----
148: 72.30.221, 4726493
149: ----
150: ----
151: 72.30.9, 4726281
152: ----
153: 72.30.226, 4726498
154: ----
155: ----
156: ----
157: ----
158: ----
159: ----
160: 72.30.87, 4726359
161: ----
162: ----
163: ----
164: ----
165: ----
166: ----
167: ----
168: ----
169: ----
170: ----
171: ----
172: ----
173: ----
174: ----
175: ----
176: ----
177: ----
178: ----
179: ----
180: ----
181: ----
182: ----
183: ----
184: ----
185: ----
186: ----
187: ----
188: ----
189: ----
190: ----
191: ----
192: ----
193: ----
194: ----
195: ----
196: ----

In the HashQP object:

Table Size = 197
Number of entries = 50
Load factor = 0.25380710659898476
Number of collisions = 8
Longest Collision Run = 4

Testing HashSC with IpValue key:
Retrieved in HashTable, IpAddress: 74.6.87, 4851287, now trying to delete it
Removed 74.6.87, 4851287 successfully!

Testing HashQP with String key:
Retrieved in HashTable, IpAddress: 74.6.87, 4851287, now trying to delete it
Removed 74.6.87, 4851287 successfully!

Now the contents of HashSC with the IpValue key has:
0: ----
1: 72.30.54, 4726326
2: ----
3: 72.30.56, 4726328
4: 74.6.65, 4851265
5: 72.30.252, 4726524 -> 74.6.66, 4851266
6: 74.6.67, 4851267
7: 72.30.60, 4726332 -> 74.6.68, 4851268
8: 72.30.61, 4726333 -> 74.6.69, 4851269
9: 74.6.70, 4851270
10: 74.6.71, 4851271
11: 74.6.72, 4851272
12: 74.6.73, 4851273
13: 74.6.74, 4851274
14: 74.6.75, 4851275
15: 74.6.76, 4851276
16: ----
17: ----
18: 74.6.79, 4851279
19: ----
20: ----
21: ----
22: ----
23: ----
24: 72.30.8.1, 1209927681 -> 74.6.85, 4851285
25: 74.6.86, 4851286
26: ----
27: ----
28: ----
29: ----
30: ----
31: ----
32: ----
33: ----
34: 72.30.87, 4726359
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: ----
42: ----
43: 74.6.7, 4851207
44: 72.30.97, 4726369 -> 74.6.8, 4851208
45: 72.30.98, 4726370
46: 72.30.99, 4726371 -> 7.4.6.19, 117704211
47: ----
48: ----
49: ----
50: ----
51: ----
52: ----
53: 72.30.9, 4726281 -> 74.6.17, 4851217
54: 74.6.18, 4851218
55: ----
56: 74.6.20, 4851220
57: 74.6.21, 4851221
58: 74.6.22, 4851222
59: 74.6.23, 4851223
60: 74.6.24, 4851224
61: 74.6.25, 4851225
62: 74.6.26, 4851226
63: 74.6.27, 4851227
64: 72.30.214, 4726486 -> 74.6.28, 4851228
65: 72.30.215, 4726487 -> 74.6.29, 4851229
66: 72.30.216, 4726488
67: 74.6.31, 4851231
68: ----
69: ----
70: 74.6.131, 4851331
71: 72.30.221, 4726493
72: ----
73: ----
74: ----
75: ----
76: 72.30.226, 4726498
77: ----
78: ----
79: ----
80: ----
81: ----
82: 74.6.240, 4851440
83: ----
84: ----
85: ----
86: ----
87: ----
88: ----
89: ----
90: ----
91: ----
92: ----
93: ----
94: ----
95: ----
96: ----

Now the contents of HashQP with the IpValue key has:
0: 72.30.97, 4726369
1: 72.30.98, 4726370
2: 72.30.99, 4726371
3: ----
4: ----
5: ----
6: ----
7: ----
8: ----
9: ----
10: ----
11: ----
12: ----
13: ----
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: ----
21: ----
22: ----
23: ----
24: ----
25: ----
26: ----
27: ----
28: ----
29: ----
30: ----
31: ----
32: ----
33: ----
34: ----
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: ----
42: ----
43: ----
44: ----
45: ----
46: 72.30.54, 4726326
47: ----
48: 72.30.56, 4726328
49: ----
50: ----
51: ----
52: ----
53: ----
54: ----
55: 74.6.65, 4851265
56: 74.6.66, 4851266
57: 74.6.67, 4851267
58: 74.6.68, 4851268
59: 74.6.69, 4851269
60: ----
61: ----
62: ----
63: 72.30.252, 4726524
64: ----
65: ----
66: ----
67: ----
68: ----
69: 74.6.17, 4851217
70: 74.6.18, 4851218
71: ----
72: ----
73: 74.6.131, 4851331
74: ----
75: ----
76: ----
77: ----
78: ----
79: 72.30.60, 4726332
80: 72.30.61, 4726333
81: ----
82: ----
83: ----
84: ----
85: ----
86: ----
87: 74.6.70, 4851270
88: 74.6.71, 4851271
89: 74.6.72, 4851272
90: 74.6.73, 4851273
91: 74.6.74, 4851274
92: 74.6.75, 4851275
93: 74.6.76, 4851276
94: ----
95: ----
96: 74.6.79, 4851279
97: ----
98: ----
99: 74.6.20, 4851220
100: 74.6.21, 4851221
101: 74.6.22, 4851222
102: 74.6.23, 4851223
103: 74.6.24, 4851224
104: 74.6.25, 4851225
105: 74.6.26, 4851226
106: 74.6.27, 4851227
107: 74.6.28, 4851228
108: 74.6.29, 4851229
109: ----
110: ----
111: ----
112: ----
113: ----
114: 72.30.214, 4726486
115: 74.6.240, 4851440
116: 7.4.6.19, 117704211
117: 72.30.216, 4726488
118: ----
119: 72.30.215, 4726487
120: ----
121: ----
122: ----
123: ----
124: ----
125: ----
126: ----
127: ----
128: ----
129: 74.6.85, 4851285
130: 74.6.86, 4851286
131: ----
132: ----
133: ----
134: ----
135: ----
136: ----
137: 72.30.8.1, 1209927681
138: 74.6.31, 4851231
139: ----
140: ----
141: ----
142: ----
143: 74.6.7, 4851207
144: 74.6.8, 4851208
145: ----
146: ----
147: ----
148: 72.30.221, 4726493
149: ----
150: ----
151: 72.30.9, 4726281
152: ----
153: 72.30.226, 4726498
154: ----
155: ----
156: ----
157: ----
158: ----
159: ----
160: 72.30.87, 4726359
161: ----
162: ----
163: ----
164: ----
165: ----
166: ----
167: ----
168: ----
169: ----
170: ----
171: ----
172: ----
173: ----
174: ----
175: ----
176: ----
177: ----
178: ----
179: ----
180: ----
181: ----
182: ----
183: ----
184: ----
185: ----
186: ----
187: ----
188: ----
189: ----
190: ----
191: ----
192: ----
193: ----
194: ----
195: ----
196: ----

 * 
 * 
 * 
Enter the input filename: HW5TestInput3.txt
Inserted into HashSC: 68.142.195.80, 1150206800
Inserted into HashQP: 68.142.195.80, 1150206800
Inserted into HashSC: 68.142.195.81, 1150206801
Inserted into HashQP: 68.142.195.81, 1150206801
Inserted into HashSC: 68.142.203.133, 1150208901
Inserted into HashQP: 68.142.203.133, 1150208901
Inserted into HashSC: 68.142.211.69, 1150210885
Inserted into HashQP: 68.142.211.69, 1150210885
Inserted into HashSC: 68.142.212.197, 1150211269
Inserted into HashQP: 68.142.212.197, 1150211269
Inserted into HashSC: 68.142.230.125, 1150215805
Inserted into HashQP: 68.142.230.125, 1150215805
Inserted into HashSC: 68.142.230.126, 1150215806
Inserted into HashQP: 68.142.230.126, 1150215806
Inserted into HashSC: 68.142.230.127, 1150215807
Inserted into HashQP: 68.142.230.127, 1150215807
Inserted into HashSC: 68.142.230.128, 1150215808
Inserted into HashQP: 68.142.230.128, 1150215808
Inserted into HashSC: 68.142.230.129, 1150215809
Inserted into HashQP: 68.142.230.129, 1150215809
Inserted into HashSC: 68.142.230.130, 1150215810
Inserted into HashQP: 68.142.230.130, 1150215810
Inserted into HashSC: 68.142.246, 4493046
Inserted into HashQP: 68.142.246, 4493046
Inserted into HashSC: 68.142.249, 4493049
Inserted into HashQP: 68.142.249, 4493049
Inserted into HashSC: 68.142.250, 4493050
Inserted into HashQP: 68.142.250, 4493050
Inserted into HashSC: 68.142.251, 4493051
Inserted into HashQP: 68.142.251, 4493051
Inserted into HashSC: 68.180.216.111, 1152702575
Inserted into HashQP: 68.180.216.111, 1152702575
Inserted into HashSC: 68.180.250, 4502778
Inserted into HashQP: 68.180.250, 4502778
Inserted into HashSC: 68.180.251, 4502779
Inserted into HashQP: 68.180.251, 4502779
Inserted into HashSC: 69.147.79.131, 1167282051
Inserted into HashQP: 69.147.79.131, 1167282051
Inserted into HashSC: 69.147.79.137, 1167282057
Inserted into HashQP: 69.147.79.137, 1167282057
Inserted into HashSC: 69.147.79.173, 1167282093
Inserted into HashQP: 69.147.79.173, 1167282093
Inserted into HashSC: 68.1.0.131, 1140916355
Inserted into HashQP: 68.1.0.131, 1140916355
Inserted into HashSC: 68.142.230.132, 1150215812
Inserted into HashQP: 68.142.230.132, 1150215812
Inserted into HashSC: 68.142.230.133, 1150215813
Inserted into HashQP: 68.142.230.133, 1150215813
Inserted into HashSC: 68.142.230.1, 1150215681
Inserted into HashQP: 68.142.230.1, 1150215681
Inserted into HashSC: 68.142.230.135, 1150215815
Inserted into HashQP: 68.142.230.135, 1150215815
Inserted into HashSC: 68.142.330.136, 1150241416
Inserted into HashQP: 68.142.330.136, 1150241416
Inserted into HashSC: 68.142.230.137, 1150215817
Inserted into HashQP: 68.142.230.137, 1150215817
Inserted into HashSC: 68.142.230.138, 1150215818
Inserted into HashQP: 68.142.230.138, 1150215818
Inserted into HashSC: 68.442.230.139, 1169876619
Inserted into HashQP: 68.442.230.139, 1169876619
Inserted into HashSC: 68.142.230.140, 1150215820
Inserted into HashQP: 68.142.230.140, 1150215820
Inserted into HashSC: 68.142.230.141, 1150215821
Inserted into HashQP: 68.142.230.141, 1150215821
Inserted into HashSC: 68.142.230.142, 1150215822
Inserted into HashQP: 68.142.230.142, 1150215822
Inserted into HashSC: 68.142.230.143, 1150215823
Inserted into HashQP: 68.142.230.143, 1150215823
Inserted into HashSC: 68.142.230.144, 1150215824
Inserted into HashQP: 68.142.230.144, 1150215824
Inserted into HashSC: 68.142.230.145, 1150215825
Inserted into HashQP: 68.142.230.145, 1150215825
Inserted into HashSC: 68.142.230.146, 1150215826
Inserted into HashQP: 68.142.230.146, 1150215826
Inserted into HashSC: 68.142.230.147, 1150215827
Inserted into HashQP: 68.142.230.147, 1150215827
Inserted into HashSC: 68.142.230.148, 1150215828
Inserted into HashQP: 68.142.230.148, 1150215828
Inserted into HashSC: 67.195.44, 4440876
Inserted into HashQP: 67.195.44, 4440876
Inserted into HashSC: 67.195.45, 4440877
Inserted into HashQP: 67.195.45, 4440877
Inserted into HashSC: 67.195.58, 4440890
Inserted into HashQP: 67.195.58, 4440890
Inserted into HashSC: 68.142.230.150, 1150215830
Inserted into HashQP: 68.142.230.150, 1150215830
Inserted into HashSC: 68.142.230.151, 1150215831
Inserted into HashQP: 68.142.230.151, 1150215831
Inserted into HashSC: 68.142.230.152, 1150215832
Inserted into HashQP: 68.142.230.152, 1150215832
Inserted into HashSC: 68.142.230.153, 1150215833
Inserted into HashQP: 68.142.230.153, 1150215833
Inserted into HashSC: 68.142.230.154, 1150215834
Inserted into HashQP: 68.142.230.154, 1150215834
Inserted into HashSC: 68.142.230.155, 1150215835
Inserted into HashQP: 68.142.230.155, 1150215835
Inserted into HashSC: 68.142.230.156, 1150215836
Inserted into HashQP: 68.142.230.156, 1150215836
Inserted into HashSC: 68.142.230.157, 1150215837
Inserted into HashQP: 68.142.230.157, 1150215837
Inserted into HashSC: 68.142.230.158, 1150215838
Inserted into HashQP: 68.142.230.158, 1150215838
Inserted into HashSC: 68.142.230.159, 1150215839
Inserted into HashQP: 68.142.230.159, 1150215839
Inserted into HashSC: 68.142.230.160, 1150215840
Inserted into HashQP: 68.142.230.160, 1150215840
Inserted into HashSC: 68.142.230.161, 1150215841
Inserted into HashQP: 68.142.230.161, 1150215841
Inserted into HashSC: 68.142.230.162, 1150215842
Inserted into HashQP: 68.142.230.162, 1150215842
Inserted into HashSC: 68.142.230.163, 1150215843
Inserted into HashQP: 68.142.230.163, 1150215843
Inserted into HashSC: 68.142.230.164, 1150215844
Inserted into HashQP: 68.142.230.164, 1150215844
Inserted into HashSC: 68.142.230.165, 1150215845
Inserted into HashQP: 68.142.230.165, 1150215845
Inserted into HashSC: 68.142.230.166, 1150215846
Inserted into HashQP: 68.142.230.166, 1150215846
Inserted into HashSC: 68.142.230.167, 1150215847
Inserted into HashQP: 68.142.230.167, 1150215847
Inserted into HashSC: 68.142.230.168, 1150215848
Inserted into HashQP: 68.142.230.168, 1150215848
Inserted into HashSC: 68.142.230.169, 1150215849
Inserted into HashQP: 68.142.230.169, 1150215849
Inserted into HashSC: 68.142.230.174, 1150215854
Inserted into HashQP: 68.142.230.174, 1150215854
Inserted into HashSC: 68.142.230.175, 1150215855
Inserted into HashQP: 68.142.230.175, 1150215855
Inserted into HashSC: 68.142.230.176, 1150215856
Inserted into HashQP: 68.142.230.176, 1150215856
Inserted into HashSC: 68.142.230.177, 1150215857
Inserted into HashQP: 68.142.230.177, 1150215857
Inserted into HashSC: 68.142.230.178, 1150215858
Inserted into HashQP: 68.142.230.178, 1150215858
Inserted into HashSC: 68.142.230.179, 1150215859
Inserted into HashQP: 68.142.230.179, 1150215859
Inserted into HashSC: 68.142.230.180, 1150215860
Inserted into HashQP: 68.142.230.180, 1150215860
Inserted into HashSC: 68.142.230.181, 1150215861
Inserted into HashQP: 68.142.230.181, 1150215861
Inserted into HashSC: 68.142.230.182, 1150215862
Inserted into HashQP: 68.142.230.182, 1150215862
Inserted into HashSC: 68.142.230.183, 1150215863
Inserted into HashQP: 68.142.230.183, 1150215863
Inserted into HashSC: 68.142.230.184, 1150215864
Inserted into HashQP: 68.142.230.184, 1150215864
Inserted into HashSC: 68.142.230.185, 1150215865
Inserted into HashQP: 68.142.230.185, 1150215865
Inserted into HashSC: 68.142.230.186, 1150215866
Inserted into HashQP: 68.142.230.186, 1150215866
Inserted into HashSC: 68.142.230.187, 1150215867
Inserted into HashQP: 68.142.230.187, 1150215867
Inserted into HashSC: 68.142.230.188, 1150215868
Inserted into HashQP: 68.142.230.188, 1150215868
Inserted into HashSC: 68.142.230.189, 1150215869
Inserted into HashQP: 68.142.230.189, 1150215869
Inserted into HashSC: 68.142.230.190, 1150215870
Inserted into HashQP: 68.142.230.190, 1150215870
Inserted into HashSC: 68.142.230.191, 1150215871
Inserted into HashQP: 68.142.230.191, 1150215871
Inserted into HashSC: 68.142.230.192, 1150215872
Inserted into HashQP: 68.142.230.192, 1150215872
Inserted into HashSC: 68.142.230.193, 1150215873
Inserted into HashQP: 68.142.230.193, 1150215873
Inserted into HashSC: 68.142.230.194, 1150215874
Inserted into HashQP: 68.142.230.194, 1150215874
Inserted into HashSC: 68.142.230.195, 1150215875
Inserted into HashQP: 68.142.230.195, 1150215875
Inserted into HashSC: 68.142.230.196, 1150215876
Inserted into HashQP: 68.142.230.196, 1150215876
Inserted into HashSC: 68.142.230.197, 1150215877
Inserted into HashQP: 68.142.230.197, 1150215877
Inserted into HashSC: 68.142.230.198, 1150215878
Inserted into HashQP: 68.142.230.198, 1150215878
Inserted into HashSC: 68.142.230.199, 1150215879
Inserted into HashQP: 68.142.230.199, 1150215879
Inserted into HashSC: 68.142.230.200, 1150215880
Inserted into HashQP: 68.142.230.200, 1150215880
Inserted into HashSC: 68.142.230.201, 1150215881
Inserted into HashQP: 68.142.230.201, 1150215881
Inserted into HashSC: 68.142.230.202, 1150215882
Inserted into HashQP: 68.142.230.202, 1150215882
Inserted into HashSC: 68.142.230.203, 1150215883
Inserted into HashQP: 68.142.230.203, 1150215883
Inserted into HashSC: 68.142.230.204, 1150215884
Inserted into HashQP: 68.142.230.204, 1150215884
Inserted into HashSC: 68.142.230.205, 1150215885
Inserted into HashQP: 68.142.230.205, 1150215885
Inserted into HashSC: 68.142.230.206, 1150215886
Inserted into HashQP: 68.142.230.206, 1150215886
Inserted into HashSC: 68.142.230.207, 1150215887
Inserted into HashQP: 68.142.230.207, 1150215887
Inserted into HashSC: 68.142.230.208, 1150215888
Inserted into HashQP: 68.142.230.208, 1150215888
Inserted into HashSC: 68.142.230.209, 1150215889
Inserted into HashQP: 68.142.230.209, 1150215889
Inserted into HashSC: 68.142.230.210, 1150215890
Inserted into HashQP: 68.142.230.210, 1150215890
Inserted into HashSC: 68.142.230.211, 1150215891
Inserted into HashQP: 68.142.230.211, 1150215891
Inserted into HashSC: 68.142.230.212, 1150215892
Inserted into HashQP: 68.142.230.212, 1150215892
Inserted into HashSC: 68.142.230.213, 1150215893
Inserted into HashQP: 68.142.230.213, 1150215893
Inserted into HashSC: 68.142.230.214, 1150215894
Inserted into HashQP: 68.142.230.214, 1150215894
Inserted into HashSC: 68.142.230.215, 1150215895
Inserted into HashQP: 68.142.230.215, 1150215895
Inserted into HashSC: 68.142.230.216, 1150215896
Inserted into HashQP: 68.142.230.216, 1150215896
Inserted into HashSC: 68.142.230.217, 1150215897
Inserted into HashQP: 68.142.230.217, 1150215897
Inserted into HashSC: 68.142.230.240, 1150215920
Inserted into HashQP: 68.142.230.240, 1150215920
Inserted into HashSC: 68.142.230.247, 1150215927
Inserted into HashQP: 68.142.230.247, 1150215927
Inserted into HashSC: 68.142.230.248, 1150215928
Inserted into HashQP: 68.142.230.248, 1150215928
Inserted into HashSC: 68.142.230.249, 1150215929
Inserted into HashQP: 68.142.230.249, 1150215929
Inserted into HashSC: 68.142.230.250, 1150215930
Inserted into HashQP: 68.142.230.250, 1150215930
Inserted into HashSC: 68.142.230.251, 1150215931
Inserted into HashQP: 68.142.230.251, 1150215931
Inserted into HashSC: 68.142.230.252, 1150215932
Inserted into HashQP: 68.142.230.252, 1150215932
Inserted into HashSC: 68.142.230.253, 1150215933
Inserted into HashQP: 68.142.230.253, 1150215933
Inserted into HashSC: 68.142.230.254, 1150215934
Inserted into HashQP: 68.142.230.254, 1150215934
Inserted into HashSC: 68.142.230.32, 1150215712
Inserted into HashQP: 68.142.230.32, 1150215712
Inserted into HashSC: 68.142.230.33, 1150215713
Inserted into HashQP: 68.142.230.33, 1150215713
Inserted into HashSC: 68.142.230.34, 1150215714
Inserted into HashQP: 68.142.230.34, 1150215714
Inserted into HashSC: 68.142.230.35, 1150215715
Inserted into HashQP: 68.142.230.35, 1150215715
Inserted into HashSC: 68.142.230.36, 1150215716
Inserted into HashQP: 68.142.230.36, 1150215716
Inserted into HashSC: 68.142.230.37, 1150215717
Inserted into HashQP: 68.142.230.37, 1150215717
Inserted into HashSC: 68.142.230.38, 1150215718
Inserted into HashQP: 68.142.230.38, 1150215718
Inserted into HashSC: 68.142.230.39, 1150215719
Inserted into HashQP: 68.142.230.39, 1150215719
Inserted into HashSC: 68.142.230.40, 1150215720
Inserted into HashQP: 68.142.230.40, 1150215720
Inserted into HashSC: 68.142.230.41, 1150215721
Inserted into HashQP: 68.142.230.41, 1150215721
Inserted into HashSC: 68.142.230.43, 1150215723
Inserted into HashQP: 68.142.230.43, 1150215723
Inserted into HashSC: 68.142.230.44, 1150215724
Inserted into HashQP: 68.142.230.44, 1150215724
Inserted into HashSC: 68.142.230.45, 1150215725
Inserted into HashQP: 68.142.230.45, 1150215725
Inserted into HashSC: 68.142.230.46, 1150215726
Inserted into HashQP: 68.142.230.46, 1150215726
Inserted into HashSC: 68.142.230.47, 1150215727
Inserted into HashQP: 68.142.230.47, 1150215727
Inserted into HashSC: 68.142.230.48, 1150215728
Inserted into HashQP: 68.142.230.48, 1150215728
Inserted into HashSC: 68.142.230.49, 1150215729
Inserted into HashQP: 68.142.230.49, 1150215729
Inserted into HashSC: 68.142.231.49, 1150215985
Inserted into HashQP: 68.142.231.49, 1150215985
Inserted into HashSC: 68.142.240.106, 1150218346
Inserted into HashQP: 68.142.240.106, 1150218346

Contents of HashSC with the IpValue key:
0: 68.142.230.135, 1150215815 -> 68.142.230.38, 1150215718
1: 69.147.79.173, 1167282093 -> 68.142.230.39, 1150215719
2: 68.142.230.137, 1150215817 -> 68.142.230.40, 1150215720
3: 68.142.230.138, 1150215818 -> 68.142.230.41, 1150215721
4: ----
5: 68.142.230.140, 1150215820 -> 68.142.230.43, 1150215723
6: 68.142.195.80, 1150206800 -> 68.142.246, 4493046 -> 68.142.230.141, 1150215821 -> 68.142.230.44, 1150215724
7: 68.142.195.81, 1150206801 -> 68.142.230.142, 1150215822 -> 68.142.230.45, 1150215725
8: 68.142.230.143, 1150215823 -> 68.142.230.240, 1150215920 -> 68.142.230.46, 1150215726
9: 68.142.249, 4493049 -> 68.142.230.144, 1150215824 -> 68.142.230.47, 1150215727 -> 68.142.240.106, 1150218346
10: 68.142.250, 4493050 -> 68.142.230.145, 1150215825 -> 68.142.230.48, 1150215728
11: 68.142.251, 4493051 -> 68.142.230.146, 1150215826 -> 68.142.230.49, 1150215729
12: 68.142.230.147, 1150215827
13: 68.142.212.197, 1150211269 -> 68.142.230.148, 1150215828
14: ----
15: 68.142.230.150, 1150215830 -> 68.142.230.247, 1150215927
16: 68.142.230.151, 1150215831 -> 68.142.230.248, 1150215928
17: 68.142.211.69, 1150210885 -> 68.142.230.152, 1150215832 -> 68.142.230.249, 1150215929
18: 68.142.230.153, 1150215833 -> 68.142.230.250, 1150215930
19: 68.142.230.154, 1150215834 -> 68.142.230.251, 1150215931
20: 68.142.230.155, 1150215835 -> 68.142.230.252, 1150215932
21: 68.142.230.156, 1150215836 -> 68.142.230.253, 1150215933
22: 67.195.44, 4440876 -> 68.142.230.157, 1150215837 -> 68.142.230.254, 1150215934
23: 67.195.45, 4440877 -> 68.142.230.158, 1150215838
24: 68.142.230.159, 1150215839
25: 68.142.230.160, 1150215840
26: 68.142.230.161, 1150215841
27: 68.1.0.131, 1140916355 -> 68.142.230.162, 1150215842
28: 68.142.230.163, 1150215843
29: 68.142.230.164, 1150215844
30: 68.142.230.165, 1150215845
31: 68.142.230.166, 1150215846
32: 68.142.230.167, 1150215847
33: 68.142.230.168, 1150215848
34: 68.142.230.169, 1150215849
35: ----
36: 67.195.58, 4440890
37: ----
38: 68.180.250, 4502778
39: 68.180.251, 4502779 -> 68.142.230.174, 1150215854
40: 68.142.230.175, 1150215855
41: 68.142.230.176, 1150215856
42: 68.142.230.177, 1150215857
43: 68.142.230.178, 1150215858
44: 68.142.230.179, 1150215859
45: 68.142.230.180, 1150215860
46: 68.142.230.181, 1150215861
47: 68.142.230.182, 1150215862
48: 68.142.230.183, 1150215863
49: 68.142.230.184, 1150215864
50: 68.142.230.185, 1150215865
51: 68.142.230.186, 1150215866
52: 68.142.230.187, 1150215867
53: 68.142.230.188, 1150215868
54: 68.142.230.189, 1150215869
55: 68.142.230.190, 1150215870
56: 69.147.79.131, 1167282051 -> 68.142.230.191, 1150215871
57: 68.142.230.192, 1150215872
58: 68.142.230.193, 1150215873
59: 68.142.230.194, 1150215874
60: 68.142.230.1, 1150215681 -> 68.142.230.195, 1150215875
61: 68.142.230.196, 1150215876
62: 69.147.79.137, 1167282057 -> 68.142.230.197, 1150215877
63: 68.142.230.198, 1150215878
64: 68.142.230.199, 1150215879
65: 68.142.230.200, 1150215880
66: 68.142.230.201, 1150215881
67: 68.142.230.202, 1150215882
68: 68.180.216.111, 1152702575 -> 68.442.230.139, 1169876619 -> 68.142.230.203, 1150215883
69: 68.142.230.204, 1150215884
70: 68.142.203.133, 1150208901 -> 68.142.230.205, 1150215885
71: 68.142.230.206, 1150215886
72: 68.142.230.207, 1150215887
73: 68.142.230.208, 1150215888 -> 68.142.231.49, 1150215985
74: 68.142.230.209, 1150215889
75: 68.142.230.210, 1150215890
76: 68.142.230.211, 1150215891
77: 68.142.230.212, 1150215892
78: 68.142.230.213, 1150215893
79: 68.142.230.214, 1150215894
80: 68.142.230.215, 1150215895
81: 68.142.230.216, 1150215896
82: 68.142.230.217, 1150215897
83: ----
84: ----
85: ----
86: ----
87: 68.142.230.125, 1150215805
88: 68.142.230.126, 1150215806
89: 68.142.230.127, 1150215807
90: 68.142.230.128, 1150215808 -> 68.142.330.136, 1150241416
91: 68.142.230.129, 1150215809 -> 68.142.230.32, 1150215712
92: 68.142.230.130, 1150215810 -> 68.142.230.33, 1150215713
93: 68.142.230.34, 1150215714
94: 68.142.230.132, 1150215812 -> 68.142.230.35, 1150215715
95: 68.142.230.133, 1150215813 -> 68.142.230.36, 1150215716
96: 68.142.230.37, 1150215717

In the HashSC object:

Table Size = 97
Number of entries = 134
Load factor = 1.3814432989690721
Number of collisions = 58
Longest Linked List = 4

Contents of HashQP with the String key:
0: 67.195.45, 4440877
1: 68.142.230.174, 1150215854
2: 68.142.230.175, 1150215855
3: 68.142.230.176, 1150215856
4: 68.180.216.111, 1152702575
5: 68.142.230.178, 1150215858
6: 68.142.230.179, 1150215859
7: ----
8: 68.142.230.177, 1150215857
9: ----
10: ----
11: ----
12: ----
13: ----
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: ----
21: ----
22: ----
23: ----
24: ----
25: ----
26: ----
27: ----
28: ----
29: ----
30: 69.147.79.131, 1167282051
31: ----
32: ----
33: ----
34: 68.142.230.180, 1150215860
35: 68.142.230.181, 1150215861
36: 69.147.79.137, 1167282057
37: 68.142.230.182, 1150215862
38: 68.142.230.183, 1150215863
39: 68.142.230.184, 1150215864
40: 67.195.58, 4440890
41: 68.142.230.186, 1150215866
42: 68.142.230.187, 1150215867
43: 68.142.230.185, 1150215865
44: 68.142.230.189, 1150215869
45: 68.142.246, 4493046
46: 68.142.230.188, 1150215868
47: ----
48: 68.142.249, 4493049
49: ----
50: ----
51: ----
52: ----
53: ----
54: ----
55: 68.142.330.136, 1150241416
56: ----
57: ----
58: ----
59: ----
60: ----
61: ----
62: ----
63: ----
64: 68.142.230.240, 1150215920
65: ----
66: ----
67: ----
68: ----
69: ----
70: ----
71: 68.142.230.190, 1150215870
72: 68.142.230.191, 1150215871
73: 68.142.230.192, 1150215872
74: 68.142.230.193, 1150215873
75: 68.142.230.194, 1150215874
76: 68.142.250, 4493050
77: 68.142.251, 4493051
78: 68.142.230.196, 1150215876
79: 68.142.230.197, 1150215877
80: 68.142.230.195, 1150215875
81: 68.142.230.199, 1150215879
82: 68.142.230.249, 1150215929
83: 68.142.230.198, 1150215878
84: ----
85: ----
86: ----
87: 68.142.230.247, 1150215927
88: 68.142.230.248, 1150215928
89: ----
90: ----
91: ----
92: ----
93: ----
94: ----
95: ----
96: ----
97: 68.142.230.32, 1150215712
98: 68.142.230.33, 1150215713
99: 68.142.230.34, 1150215714
100: 68.142.230.35, 1150215715
101: 68.142.230.250, 1150215930
102: 68.142.230.251, 1150215931
103: 68.142.230.252, 1150215932
104: 68.142.230.253, 1150215933
105: 68.142.230.254, 1150215934
106: 68.142.230.37, 1150215717
107: 68.142.230.38, 1150215718
108: 68.142.230.39, 1150215719
109: 68.142.240.106, 1150218346
110: 68.142.230.36, 1150215716
111: ----
112: ----
113: ----
114: ----
115: ----
116: ----
117: ----
118: ----
119: ----
120: 68.142.203.133, 1150208901
121: ----
122: ----
123: ----
124: ----
125: ----
126: ----
127: ----
128: ----
129: ----
130: ----
131: ----
132: 68.142.230.40, 1150215720
133: 68.142.230.41, 1150215721
134: ----
135: 68.142.230.43, 1150215723
136: 68.142.230.44, 1150215724
137: 68.142.230.45, 1150215725
138: 68.142.230.46, 1150215726
139: 68.142.230.47, 1150215727
140: 68.142.230.48, 1150215728
141: 68.142.230.49, 1150215729
142: ----
143: ----
144: ----
145: ----
146: ----
147: ----
148: ----
149: ----
150: ----
151: ----
152: ----
153: ----
154: ----
155: ----
156: ----
157: ----
158: ----
159: ----
160: ----
161: ----
162: ----
163: ----
164: ----
165: ----
166: ----
167: ----
168: ----
169: ----
170: ----
171: ----
172: ----
173: ----
174: ----
175: 68.1.0.131, 1140916355
176: ----
177: ----
178: ----
179: ----
180: 69.147.79.173, 1167282093
181: ----
182: ----
183: ----
184: ----
185: ----
186: ----
187: ----
188: ----
189: ----
190: ----
191: ----
192: ----
193: ----
194: ----
195: ----
196: ----
197: ----
198: ----
199: ----
200: ----
201: ----
202: ----
203: ----
204: 68.142.211.69, 1150210885
205: ----
206: ----
207: ----
208: ----
209: ----
210: ----
211: ----
212: ----
213: ----
214: 68.142.230.125, 1150215805
215: 68.142.230.126, 1150215806
216: 68.142.230.127, 1150215807
217: 68.142.230.128, 1150215808
218: 68.142.230.129, 1150215809
219: ----
220: ----
221: ----
222: ----
223: ----
224: 68.142.230.1, 1150215681
225: ----
226: ----
227: ----
228: ----
229: ----
230: ----
231: ----
232: ----
233: 68.142.195.80, 1150206800
234: 68.142.195.81, 1150206801
235: ----
236: ----
237: ----
238: ----
239: ----
240: ----
241: ----
242: ----
243: ----
244: ----
245: ----
246: 68.142.230.130, 1150215810
247: ----
248: 68.142.230.132, 1150215812
249: 68.142.230.133, 1150215813
250: ----
251: 68.142.230.135, 1150215815
252: ----
253: 68.142.230.137, 1150215817
254: 68.142.230.138, 1150215818
255: ----
256: ----
257: ----
258: ----
259: ----
260: ----
261: ----
262: ----
263: ----
264: ----
265: ----
266: ----
267: ----
268: ----
269: ----
270: ----
271: ----
272: ----
273: ----
274: ----
275: ----
276: ----
277: ----
278: ----
279: ----
280: ----
281: ----
282: ----
283: 68.142.230.140, 1150215820
284: 68.142.230.141, 1150215821
285: 68.142.230.142, 1150215822
286: 68.142.230.143, 1150215823
287: 68.142.230.144, 1150215824
288: 68.142.230.145, 1150215825
289: 68.142.230.146, 1150215826
290: 68.142.230.147, 1150215827
291: 68.142.230.148, 1150215828
292: ----
293: ----
294: ----
295: ----
296: ----
297: ----
298: ----
299: ----
300: ----
301: ----
302: ----
303: ----
304: ----
305: ----
306: ----
307: ----
308: ----
309: ----
310: ----
311: ----
312: ----
313: 68.142.230.200, 1150215880
314: 68.142.230.201, 1150215881
315: 68.142.230.202, 1150215882
316: 68.142.230.203, 1150215883
317: 68.142.230.204, 1150215884
318: 68.142.230.205, 1150215885
319: 68.142.230.206, 1150215886
320: 68.142.230.150, 1150215830
321: 68.142.230.151, 1150215831
322: 68.142.230.152, 1150215832
323: 68.142.230.153, 1150215833
324: 68.142.230.154, 1150215834
325: 68.142.230.155, 1150215835
326: 68.180.250, 4502778
327: 68.180.251, 4502779
328: 68.142.230.157, 1150215837
329: 68.142.230.158, 1150215838
330: 68.442.230.139, 1169876619
331: 68.142.230.209, 1150215889
332: ----
333: 68.142.230.159, 1150215839
334: ----
335: 68.142.230.156, 1150215836
336: 68.142.230.207, 1150215887
337: 68.142.230.208, 1150215888
338: ----
339: ----
340: ----
341: ----
342: ----
343: ----
344: ----
345: ----
346: ----
347: ----
348: ----
349: ----
350: 68.142.230.210, 1150215890
351: 68.142.230.211, 1150215891
352: 68.142.230.212, 1150215892
353: 68.142.230.213, 1150215893
354: 68.142.230.214, 1150215894
355: 68.142.230.215, 1150215895
356: 68.142.230.216, 1150215896
357: 68.142.230.160, 1150215840
358: 68.142.230.161, 1150215841
359: 68.142.230.162, 1150215842
360: 68.142.230.163, 1150215843
361: 68.142.230.164, 1150215844
362: 68.142.230.165, 1150215845
363: 68.142.230.166, 1150215846
364: 68.142.230.167, 1150215847
365: 68.142.230.168, 1150215848
366: 68.142.230.169, 1150215849
367: ----
368: ----
369: ----
370: ----
371: ----
372: ----
373: 68.142.230.217, 1150215897
374: ----
375: 68.142.231.49, 1150215985
376: ----
377: ----
378: ----
379: ----
380: ----
381: ----
382: ----
383: ----
384: ----
385: ----
386: ----
387: ----
388: ----
389: 68.142.212.197, 1150211269
390: ----
391: ----
392: ----
393: ----
394: ----
395: ----
396: 67.195.44, 4440876

In the HashQP object:

Table Size = 397
Number of entries = 134
Load factor = 0.33753148614609574
Number of collisions = 65
Longest Collision Run = 4

Testing HashSC with IpValue key:
Retrieved in HashTable, IpAddress: 68.142.240.106, 1150218346, now trying to delete it
Removed 68.142.240.106, 1150218346 successfully!

Testing HashQP with String key:
Retrieved in HashTable, IpAddress: 68.142.240.106, 1150218346, now trying to delete it
Removed 68.142.240.106, 1150218346 successfully!

Now the contents of HashSC with the IpValue key has:
0: 68.142.230.135, 1150215815 -> 68.142.230.38, 1150215718
1: 69.147.79.173, 1167282093 -> 68.142.230.39, 1150215719
2: 68.142.230.137, 1150215817 -> 68.142.230.40, 1150215720
3: 68.142.230.138, 1150215818 -> 68.142.230.41, 1150215721
4: ----
5: 68.142.230.140, 1150215820 -> 68.142.230.43, 1150215723
6: 68.142.195.80, 1150206800 -> 68.142.246, 4493046 -> 68.142.230.141, 1150215821 -> 68.142.230.44, 1150215724
7: 68.142.195.81, 1150206801 -> 68.142.230.142, 1150215822 -> 68.142.230.45, 1150215725
8: 68.142.230.143, 1150215823 -> 68.142.230.240, 1150215920 -> 68.142.230.46, 1150215726
9: 68.142.249, 4493049 -> 68.142.230.144, 1150215824 -> 68.142.230.47, 1150215727
10: 68.142.250, 4493050 -> 68.142.230.145, 1150215825 -> 68.142.230.48, 1150215728
11: 68.142.251, 4493051 -> 68.142.230.146, 1150215826 -> 68.142.230.49, 1150215729
12: 68.142.230.147, 1150215827
13: 68.142.212.197, 1150211269 -> 68.142.230.148, 1150215828
14: ----
15: 68.142.230.150, 1150215830 -> 68.142.230.247, 1150215927
16: 68.142.230.151, 1150215831 -> 68.142.230.248, 1150215928
17: 68.142.211.69, 1150210885 -> 68.142.230.152, 1150215832 -> 68.142.230.249, 1150215929
18: 68.142.230.153, 1150215833 -> 68.142.230.250, 1150215930
19: 68.142.230.154, 1150215834 -> 68.142.230.251, 1150215931
20: 68.142.230.155, 1150215835 -> 68.142.230.252, 1150215932
21: 68.142.230.156, 1150215836 -> 68.142.230.253, 1150215933
22: 67.195.44, 4440876 -> 68.142.230.157, 1150215837 -> 68.142.230.254, 1150215934
23: 67.195.45, 4440877 -> 68.142.230.158, 1150215838
24: 68.142.230.159, 1150215839
25: 68.142.230.160, 1150215840
26: 68.142.230.161, 1150215841
27: 68.1.0.131, 1140916355 -> 68.142.230.162, 1150215842
28: 68.142.230.163, 1150215843
29: 68.142.230.164, 1150215844
30: 68.142.230.165, 1150215845
31: 68.142.230.166, 1150215846
32: 68.142.230.167, 1150215847
33: 68.142.230.168, 1150215848
34: 68.142.230.169, 1150215849
35: ----
36: 67.195.58, 4440890
37: ----
38: 68.180.250, 4502778
39: 68.180.251, 4502779 -> 68.142.230.174, 1150215854
40: 68.142.230.175, 1150215855
41: 68.142.230.176, 1150215856
42: 68.142.230.177, 1150215857
43: 68.142.230.178, 1150215858
44: 68.142.230.179, 1150215859
45: 68.142.230.180, 1150215860
46: 68.142.230.181, 1150215861
47: 68.142.230.182, 1150215862
48: 68.142.230.183, 1150215863
49: 68.142.230.184, 1150215864
50: 68.142.230.185, 1150215865
51: 68.142.230.186, 1150215866
52: 68.142.230.187, 1150215867
53: 68.142.230.188, 1150215868
54: 68.142.230.189, 1150215869
55: 68.142.230.190, 1150215870
56: 69.147.79.131, 1167282051 -> 68.142.230.191, 1150215871
57: 68.142.230.192, 1150215872
58: 68.142.230.193, 1150215873
59: 68.142.230.194, 1150215874
60: 68.142.230.1, 1150215681 -> 68.142.230.195, 1150215875
61: 68.142.230.196, 1150215876
62: 69.147.79.137, 1167282057 -> 68.142.230.197, 1150215877
63: 68.142.230.198, 1150215878
64: 68.142.230.199, 1150215879
65: 68.142.230.200, 1150215880
66: 68.142.230.201, 1150215881
67: 68.142.230.202, 1150215882
68: 68.180.216.111, 1152702575 -> 68.442.230.139, 1169876619 -> 68.142.230.203, 1150215883
69: 68.142.230.204, 1150215884
70: 68.142.203.133, 1150208901 -> 68.142.230.205, 1150215885
71: 68.142.230.206, 1150215886
72: 68.142.230.207, 1150215887
73: 68.142.230.208, 1150215888 -> 68.142.231.49, 1150215985
74: 68.142.230.209, 1150215889
75: 68.142.230.210, 1150215890
76: 68.142.230.211, 1150215891
77: 68.142.230.212, 1150215892
78: 68.142.230.213, 1150215893
79: 68.142.230.214, 1150215894
80: 68.142.230.215, 1150215895
81: 68.142.230.216, 1150215896
82: 68.142.230.217, 1150215897
83: ----
84: ----
85: ----
86: ----
87: 68.142.230.125, 1150215805
88: 68.142.230.126, 1150215806
89: 68.142.230.127, 1150215807
90: 68.142.230.128, 1150215808 -> 68.142.330.136, 1150241416
91: 68.142.230.129, 1150215809 -> 68.142.230.32, 1150215712
92: 68.142.230.130, 1150215810 -> 68.142.230.33, 1150215713
93: 68.142.230.34, 1150215714
94: 68.142.230.132, 1150215812 -> 68.142.230.35, 1150215715
95: 68.142.230.133, 1150215813 -> 68.142.230.36, 1150215716
96: 68.142.230.37, 1150215717

Now the contents of HashQP with the IpValue key has:
0: 67.195.45, 4440877
1: 68.142.230.174, 1150215854
2: 68.142.230.175, 1150215855
3: 68.142.230.176, 1150215856
4: 68.180.216.111, 1152702575
5: 68.142.230.178, 1150215858
6: 68.142.230.179, 1150215859
7: ----
8: 68.142.230.177, 1150215857
9: ----
10: ----
11: ----
12: ----
13: ----
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: ----
21: ----
22: ----
23: ----
24: ----
25: ----
26: ----
27: ----
28: ----
29: ----
30: 69.147.79.131, 1167282051
31: ----
32: ----
33: ----
34: 68.142.230.180, 1150215860
35: 68.142.230.181, 1150215861
36: 69.147.79.137, 1167282057
37: 68.142.230.182, 1150215862
38: 68.142.230.183, 1150215863
39: 68.142.230.184, 1150215864
40: 67.195.58, 4440890
41: 68.142.230.186, 1150215866
42: 68.142.230.187, 1150215867
43: 68.142.230.185, 1150215865
44: 68.142.230.189, 1150215869
45: 68.142.246, 4493046
46: 68.142.230.188, 1150215868
47: ----
48: 68.142.249, 4493049
49: ----
50: ----
51: ----
52: ----
53: ----
54: ----
55: 68.142.330.136, 1150241416
56: ----
57: ----
58: ----
59: ----
60: ----
61: ----
62: ----
63: ----
64: 68.142.230.240, 1150215920
65: ----
66: ----
67: ----
68: ----
69: ----
70: ----
71: 68.142.230.190, 1150215870
72: 68.142.230.191, 1150215871
73: 68.142.230.192, 1150215872
74: 68.142.230.193, 1150215873
75: 68.142.230.194, 1150215874
76: 68.142.250, 4493050
77: 68.142.251, 4493051
78: 68.142.230.196, 1150215876
79: 68.142.230.197, 1150215877
80: 68.142.230.195, 1150215875
81: 68.142.230.199, 1150215879
82: 68.142.230.249, 1150215929
83: 68.142.230.198, 1150215878
84: ----
85: ----
86: ----
87: 68.142.230.247, 1150215927
88: 68.142.230.248, 1150215928
89: ----
90: ----
91: ----
92: ----
93: ----
94: ----
95: ----
96: ----
97: 68.142.230.32, 1150215712
98: 68.142.230.33, 1150215713
99: 68.142.230.34, 1150215714
100: 68.142.230.35, 1150215715
101: 68.142.230.250, 1150215930
102: 68.142.230.251, 1150215931
103: 68.142.230.252, 1150215932
104: 68.142.230.253, 1150215933
105: 68.142.230.254, 1150215934
106: 68.142.230.37, 1150215717
107: 68.142.230.38, 1150215718
108: 68.142.230.39, 1150215719
109: ----
110: 68.142.230.36, 1150215716
111: ----
112: ----
113: ----
114: ----
115: ----
116: ----
117: ----
118: ----
119: ----
120: 68.142.203.133, 1150208901
121: ----
122: ----
123: ----
124: ----
125: ----
126: ----
127: ----
128: ----
129: ----
130: ----
131: ----
132: 68.142.230.40, 1150215720
133: 68.142.230.41, 1150215721
134: ----
135: 68.142.230.43, 1150215723
136: 68.142.230.44, 1150215724
137: 68.142.230.45, 1150215725
138: 68.142.230.46, 1150215726
139: 68.142.230.47, 1150215727
140: 68.142.230.48, 1150215728
141: 68.142.230.49, 1150215729
142: ----
143: ----
144: ----
145: ----
146: ----
147: ----
148: ----
149: ----
150: ----
151: ----
152: ----
153: ----
154: ----
155: ----
156: ----
157: ----
158: ----
159: ----
160: ----
161: ----
162: ----
163: ----
164: ----
165: ----
166: ----
167: ----
168: ----
169: ----
170: ----
171: ----
172: ----
173: ----
174: ----
175: 68.1.0.131, 1140916355
176: ----
177: ----
178: ----
179: ----
180: 69.147.79.173, 1167282093
181: ----
182: ----
183: ----
184: ----
185: ----
186: ----
187: ----
188: ----
189: ----
190: ----
191: ----
192: ----
193: ----
194: ----
195: ----
196: ----
197: ----
198: ----
199: ----
200: ----
201: ----
202: ----
203: ----
204: 68.142.211.69, 1150210885
205: ----
206: ----
207: ----
208: ----
209: ----
210: ----
211: ----
212: ----
213: ----
214: 68.142.230.125, 1150215805
215: 68.142.230.126, 1150215806
216: 68.142.230.127, 1150215807
217: 68.142.230.128, 1150215808
218: 68.142.230.129, 1150215809
219: ----
220: ----
221: ----
222: ----
223: ----
224: 68.142.230.1, 1150215681
225: ----
226: ----
227: ----
228: ----
229: ----
230: ----
231: ----
232: ----
233: 68.142.195.80, 1150206800
234: 68.142.195.81, 1150206801
235: ----
236: ----
237: ----
238: ----
239: ----
240: ----
241: ----
242: ----
243: ----
244: ----
245: ----
246: 68.142.230.130, 1150215810
247: ----
248: 68.142.230.132, 1150215812
249: 68.142.230.133, 1150215813
250: ----
251: 68.142.230.135, 1150215815
252: ----
253: 68.142.230.137, 1150215817
254: 68.142.230.138, 1150215818
255: ----
256: ----
257: ----
258: ----
259: ----
260: ----
261: ----
262: ----
263: ----
264: ----
265: ----
266: ----
267: ----
268: ----
269: ----
270: ----
271: ----
272: ----
273: ----
274: ----
275: ----
276: ----
277: ----
278: ----
279: ----
280: ----
281: ----
282: ----
283: 68.142.230.140, 1150215820
284: 68.142.230.141, 1150215821
285: 68.142.230.142, 1150215822
286: 68.142.230.143, 1150215823
287: 68.142.230.144, 1150215824
288: 68.142.230.145, 1150215825
289: 68.142.230.146, 1150215826
290: 68.142.230.147, 1150215827
291: 68.142.230.148, 1150215828
292: ----
293: ----
294: ----
295: ----
296: ----
297: ----
298: ----
299: ----
300: ----
301: ----
302: ----
303: ----
304: ----
305: ----
306: ----
307: ----
308: ----
309: ----
310: ----
311: ----
312: ----
313: 68.142.230.200, 1150215880
314: 68.142.230.201, 1150215881
315: 68.142.230.202, 1150215882
316: 68.142.230.203, 1150215883
317: 68.142.230.204, 1150215884
318: 68.142.230.205, 1150215885
319: 68.142.230.206, 1150215886
320: 68.142.230.150, 1150215830
321: 68.142.230.151, 1150215831
322: 68.142.230.152, 1150215832
323: 68.142.230.153, 1150215833
324: 68.142.230.154, 1150215834
325: 68.142.230.155, 1150215835
326: 68.180.250, 4502778
327: 68.180.251, 4502779
328: 68.142.230.157, 1150215837
329: 68.142.230.158, 1150215838
330: 68.442.230.139, 1169876619
331: 68.142.230.209, 1150215889
332: ----
333: 68.142.230.159, 1150215839
334: ----
335: 68.142.230.156, 1150215836
336: 68.142.230.207, 1150215887
337: 68.142.230.208, 1150215888
338: ----
339: ----
340: ----
341: ----
342: ----
343: ----
344: ----
345: ----
346: ----
347: ----
348: ----
349: ----
350: 68.142.230.210, 1150215890
351: 68.142.230.211, 1150215891
352: 68.142.230.212, 1150215892
353: 68.142.230.213, 1150215893
354: 68.142.230.214, 1150215894
355: 68.142.230.215, 1150215895
356: 68.142.230.216, 1150215896
357: 68.142.230.160, 1150215840
358: 68.142.230.161, 1150215841
359: 68.142.230.162, 1150215842
360: 68.142.230.163, 1150215843
361: 68.142.230.164, 1150215844
362: 68.142.230.165, 1150215845
363: 68.142.230.166, 1150215846
364: 68.142.230.167, 1150215847
365: 68.142.230.168, 1150215848
366: 68.142.230.169, 1150215849
367: ----
368: ----
369: ----
370: ----
371: ----
372: ----
373: 68.142.230.217, 1150215897
374: ----
375: 68.142.231.49, 1150215985
376: ----
377: ----
378: ----
379: ----
380: ----
381: ----
382: ----
383: ----
384: ----
385: ----
386: ----
387: ----
388: ----
389: 68.142.212.197, 1150211269
390: ----
391: ----
392: ----
393: ----
394: ----
395: ----
396: 67.195.44, 4440876





 * 
 * 
 * 
 *
 * 
 * 
 * 
 * Enter the input filename: HW5Input.txt
Inserted into HashSC: 205.226.201, 13492937
Inserted into HashQP: 205.226.201, 13492937
Inserted into HashSC: 205.226.203.186, 3454192570
Inserted into HashQP: 205.226.203.186, 3454192570
Inserted into HashSC: 205.226.203.35, 3454192419
Inserted into HashQP: 205.226.203.35, 3454192419
Inserted into HashSC: 205.226.203.56, 3454192440
Inserted into HashQP: 205.226.203.56, 3454192440
Inserted into HashSC: 205.226.203.62, 3454192446
Inserted into HashQP: 205.226.203.62, 3454192446
Inserted into HashSC: 205.266.204.238, 3456814318
Inserted into HashQP: 205.266.204.238, 3456814318
Inserted into HashSC: 202.33.250.146, 3391224466
Inserted into HashQP: 202.33.250.146, 3391224466
Inserted into HashSC: 202.33.250.147, 3391224467
Inserted into HashQP: 202.33.250.147, 3391224467
Inserted into HashSC: 202.33.250.148, 3391224468
Inserted into HashQP: 202.33.250.148, 3391224468
Inserted into HashSC: 202.33.250.149, 3391224469
Inserted into HashQP: 202.33.250.149, 3391224469
Inserted into HashSC: 202.33.250.150, 3391224470
Inserted into HashQP: 202.33.250.150, 3391224470
Inserted into HashSC: 202.33.250.151, 3391224471
Inserted into HashQP: 202.33.250.151, 3391224471
Inserted into HashSC: 202.33.250.152, 3391224472
Inserted into HashQP: 202.33.250.152, 3391224472
Inserted into HashSC: 202.33.250.153, 3391224473
Inserted into HashQP: 202.33.250.153, 3391224473
Inserted into HashSC: 202.33.250.154, 3391224474
Inserted into HashQP: 202.33.250.154, 3391224474
Inserted into HashSC: 204.162.96, 13410912
Inserted into HashQP: 204.162.96, 13410912
Inserted into HashSC: 204.162.97.1, 3433193729
Inserted into HashQP: 204.162.97.1, 3433193729
Inserted into HashSC: 204.162.97.152, 3433193880
Inserted into HashQP: 204.162.97.152, 3433193880
Inserted into HashSC: 204.162.97.17, 3433193745
Inserted into HashQP: 204.162.97.17, 3433193745
Inserted into HashSC: 204.162.97.2, 3433193730
Inserted into HashQP: 204.162.97.2, 3433193730
Inserted into HashSC: 206.3.30.196, 3456310980
Inserted into HashQP: 206.3.30.196, 3456310980
Inserted into HashSC: 206.3.0.250, 3456303354
Inserted into HashQP: 206.3.0.250, 3456303354
Inserted into HashSC: 206.3.30.251, 3456311035
Inserted into HashQP: 206.3.30.251, 3456311035
Inserted into HashSC: 195.145.119.24, 3281090328
Inserted into HashQP: 195.145.119.24, 3281090328
Inserted into HashSC: 195.145.119.25, 3281090329
Inserted into HashQP: 195.145.119.25, 3281090329
Inserted into HashSC: 198.5.208, 12977616
Inserted into HashQP: 198.5.208, 12977616
Inserted into HashSC: 198.5.210, 12977618
Inserted into HashQP: 198.5.210, 12977618
Inserted into HashSC: 204.162.97.205, 3433193933
Inserted into HashQP: 204.162.97.205, 3433193933
Inserted into HashSC: 204.162.97.228, 3433193956
Inserted into HashQP: 204.162.97.228, 3433193956
Inserted into HashSC: 204.162.97.231, 3433193959
Inserted into HashQP: 204.162.97.231, 3433193959
Inserted into HashSC: 204.162.97.3, 3433193731
Inserted into HashQP: 204.162.97.3, 3433193731
Inserted into HashSC: 204.162.97.32, 3433193760
Inserted into HashQP: 204.162.97.32, 3433193760
Inserted into HashSC: 204.162.98.11, 3433193995
Inserted into HashQP: 204.162.98.11, 3433193995
Inserted into HashSC: 204.162.98.12, 3433193996
Inserted into HashQP: 204.162.98.12, 3433193996
Inserted into HashSC: 204.162.98.124, 3433194108
Inserted into HashQP: 204.162.98.124, 3433194108
Inserted into HashSC: 204.162.98.126, 3433194110
Inserted into HashQP: 204.162.98.126, 3433194110
Inserted into HashSC: 204.162.98.151, 3433194135
Inserted into HashQP: 204.162.98.151, 3433194135
Inserted into HashSC: 204.162.98.161, 3433194145
Inserted into HashQP: 204.162.98.161, 3433194145
Inserted into HashSC: 204.162.98.168, 3433194152
Inserted into HashQP: 204.162.98.168, 3433194152
Inserted into HashSC: 204.162.98.18, 3433194002
Inserted into HashQP: 204.162.98.18, 3433194002
Inserted into HashSC: 204.162.98.192, 3433194176
Inserted into HashQP: 204.162.98.192, 3433194176
Inserted into HashSC: 204.162.98.2, 3433193986
Inserted into HashQP: 204.162.98.2, 3433193986
Inserted into HashSC: 204.162.98.237, 3433194221
Inserted into HashQP: 204.162.98.237, 3433194221
Inserted into HashSC: 204.162.98.27, 3433194011
Inserted into HashQP: 204.162.98.27, 3433194011
Inserted into HashSC: 204.162.98.3, 3433193987
Inserted into HashQP: 204.162.98.3, 3433193987
Inserted into HashSC: 204.162.98.36, 3433194020
Inserted into HashQP: 204.162.98.36, 3433194020
Inserted into HashSC: 204.162.98.38, 3433194022
Inserted into HashQP: 204.162.98.38, 3433194022
Inserted into HashSC: 204.162.98.4, 3433193988
Inserted into HashQP: 204.162.98.4, 3433193988
Inserted into HashSC: 204.162.98.45, 3433194029
Inserted into HashQP: 204.162.98.45, 3433194029
Inserted into HashSC: 204.162.98.48, 3433194032
Inserted into HashQP: 204.162.98.48, 3433194032
Inserted into HashSC: 204.162.98.49, 3433194033
Inserted into HashQP: 204.162.98.49, 3433194033
Inserted into HashSC: 204.162.98.5, 3433193989
Inserted into HashQP: 204.162.98.5, 3433193989
Inserted into HashSC: 204.162.98.6, 3433193990
Inserted into HashQP: 204.162.98.6, 3433193990
Inserted into HashSC: 204.162.98.7, 3433193991
Inserted into HashQP: 204.162.98.7, 3433193991
Inserted into HashSC: 204.162.98.8, 3433193992
Inserted into HashQP: 204.162.98.8, 3433193992
Inserted into HashSC: 204.162.98.80, 3433194064
Inserted into HashQP: 204.162.98.80, 3433194064
Inserted into HashSC: 204.162.98.88, 3433194072
Inserted into HashQP: 204.162.98.88, 3433194072
Inserted into HashSC: 204.162.98.9, 3433193993
Inserted into HashQP: 204.162.98.9, 3433193993
Inserted into HashSC: 204.162.98.91, 3433194075
Inserted into HashQP: 204.162.98.91, 3433194075
Inserted into HashSC: 204.162.98.98, 3433194082
Inserted into HashQP: 204.162.98.98, 3433194082
Inserted into HashSC: 204.202.132.19, 3435824147
Inserted into HashQP: 204.202.132.19, 3435824147

Contents of HashSC with the IpValue key:
0: ----
1: ----
2: ----
3: 204.162.98.192, 3433194176
4: 205.226.203.62, 3454192446
5: ----
6: 204.162.98.98, 3433194082
7: 204.162.98.2, 3433193986
8: 204.162.98.3, 3433193987
9: 204.162.98.4, 3433193988
10: 204.162.98.5, 3433193989
11: 204.162.98.6, 3433193990
12: 195.145.119.24, 3281090328 -> 204.162.98.7, 3433193991
13: 195.145.119.25, 3281090329 -> 204.162.98.8, 3433193992 -> 204.202.132.19, 3435824147
14: 204.162.98.9, 3433193993
15: ----
16: 206.3.30.251, 3456311035 -> 204.162.98.11, 3433193995
17: 204.162.98.12, 3433193996
18: ----
19: ----
20: ----
21: ----
22: ----
23: 204.162.98.18, 3433194002
24: ----
25: ----
26: ----
27: ----
28: ----
29: ----
30: ----
31: 205.226.203.186, 3454192570
32: 204.162.98.124, 3433194108 -> 204.162.98.27, 3433194011
33: ----
34: 204.162.98.126, 3433194110
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: 204.162.97.1, 3433193729 -> 204.162.98.36, 3433194020
42: 204.162.97.2, 3433193730
43: 205.226.201, 13492937 -> 204.162.97.3, 3433193731 -> 204.162.98.38, 3433194022
44: ----
45: ----
46: ----
47: ----
48: 204.162.98.237, 3433194221
49: ----
50: 204.162.98.45, 3433194029
51: 204.162.97.205, 3433193933
52: ----
53: 204.162.98.48, 3433194032
54: 204.162.98.49, 3433194033
55: ----
56: ----
57: 204.162.97.17, 3433193745
58: 206.3.30.196, 3456310980
59: 202.33.250.146, 3391224466 -> 204.162.98.151, 3433194135
60: 202.33.250.147, 3391224467
61: 202.33.250.148, 3391224468
62: 202.33.250.149, 3391224469
63: 205.266.204.238, 3456814318 -> 202.33.250.150, 3391224470
64: 202.33.250.151, 3391224471
65: 202.33.250.152, 3391224472
66: 202.33.250.153, 3391224473
67: 202.33.250.154, 3391224474
68: ----
69: 204.162.98.161, 3433194145
70: ----
71: ----
72: 204.162.97.32, 3433193760
73: ----
74: 205.226.203.35, 3454192419 -> 204.162.97.228, 3433193956
75: ----
76: 204.162.98.168, 3433194152
77: 204.162.97.231, 3433193959
78: ----
79: ----
80: 204.162.96, 13410912
81: ----
82: ----
83: 198.5.208, 12977616
84: ----
85: 198.5.210, 12977618 -> 204.162.98.80, 3433194064
86: ----
87: ----
88: ----
89: ----
90: ----
91: ----
92: ----
93: 204.162.98.88, 3433194072
94: ----
95: 205.226.203.56, 3454192440 -> 204.162.97.152, 3433193880 -> 206.3.0.250, 3456303354
96: 204.162.98.91, 3433194075

In the HashSC object:

Table Size = 97
Number of entries = 61
Load factor = 0.6288659793814433
Number of collisions = 17
Longest Linked List = 3

Contents of HashQP with the String key:
0: ----
1: 204.162.98.88, 3433194072
2: ----
3: ----
4: ----
5: ----
6: 198.5.208, 12977616
7: 204.162.97.228, 3433193956
8: ----
9: ----
10: ----
11: 204.162.98.36, 3433194020
12: ----
13: 204.162.98.38, 3433194022
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: ----
21: ----
22: 204.202.132.19, 3435824147
23: ----
24: 204.162.98.151, 3433194135
25: ----
26: ----
27: ----
28: ----
29: ----
30: ----
31: 204.162.98.91, 3433194075
32: ----
33: ----
34: ----
35: 198.5.210, 12977618
36: ----
37: 204.162.97.231, 3433193959
38: 204.162.98.98, 3433194082
39: ----
40: ----
41: ----
42: ----
43: ----
44: ----
45: ----
46: ----
47: 204.162.98.45, 3433194029
48: ----
49: ----
50: 202.33.250.146, 3391224466
51: 202.33.250.147, 3391224467
52: 202.33.250.148, 3391224468
53: 202.33.250.149, 3391224469
54: 204.162.98.48, 3433194032
55: 204.162.98.49, 3433194033
56: ----
57: ----
58: 205.226.203.56, 3454192440
59: ----
60: ----
61: 204.162.98.161, 3433194145
62: 204.162.96, 13410912
63: ----
64: ----
65: ----
66: ----
67: ----
68: 204.162.98.168, 3433194152
69: ----
70: ----
71: ----
72: ----
73: ----
74: ----
75: ----
76: ----
77: ----
78: ----
79: ----
80: ----
81: 202.33.250.150, 3391224470
82: 202.33.250.151, 3391224471
83: 202.33.250.152, 3391224472
84: 205.226.203.186, 3454192570
85: 202.33.250.153, 3391224473
86: 202.33.250.154, 3391224474
87: ----
88: ----
89: ----
90: ----
91: 205.226.203.62, 3454192446
92: ----
93: ----
94: ----
95: ----
96: ----
97: ----
98: ----
99: ----
100: ----
101: ----
102: ----
103: ----
104: ----
105: ----
106: ----
107: ----
108: ----
109: ----
110: ----
111: 204.162.97.17, 3433193745
112: ----
113: 204.162.98.124, 3433194108
114: ----
115: 204.162.98.126, 3433194110
116: ----
117: ----
118: ----
119: ----
120: ----
121: ----
122: 204.162.97.152, 3433193880
123: ----
124: ----
125: ----
126: 206.3.30.196, 3456310980
127: 204.162.97.205, 3433193933
128: ----
129: 204.162.98.11, 3433193995
130: 204.162.98.12, 3433193996
131: ----
132: ----
133: ----
134: ----
135: ----
136: 204.162.98.18, 3433194002
137: ----
138: ----
139: ----
140: 204.162.98.2, 3433193986
141: 204.162.98.3, 3433193987
142: 204.162.98.4, 3433193988
143: 204.162.98.237, 3433194221
144: 204.162.98.5, 3433193989
145: 205.226.201, 13492937
146: 204.162.98.7, 3433193991
147: 204.162.98.8, 3433193992
148: 204.162.98.6, 3433193990
149: 204.162.97.1, 3433193729
150: 204.162.97.2, 3433193730
151: 204.162.97.3, 3433193731
152: ----
153: 195.145.119.24, 3281090328
154: 195.145.119.25, 3281090329
155: ----
156: 204.162.98.9, 3433193993
157: ----
158: 205.266.204.238, 3456814318
159: 206.3.0.250, 3456303354
160: 206.3.30.251, 3456311035
161: ----
162: ----
163: ----
164: ----
165: ----
166: ----
167: ----
168: ----
169: ----
170: ----
171: ----
172: 204.162.98.27, 3433194011
173: 204.162.98.192, 3433194176
174: ----
175: ----
176: ----
177: ----
178: ----
179: ----
180: 204.162.97.32, 3433193760
181: 205.226.203.35, 3454192419
182: ----
183: ----
184: ----
185: ----
186: ----
187: ----
188: ----
189: ----
190: 204.162.98.80, 3433194064
191: ----
192: ----
193: ----
194: ----
195: ----
196: ----

In the HashQP object:

Table Size = 197
Number of entries = 61
Load factor = 0.3096446700507614
Number of collisions = 20
Longest Collision Run = 3

Testing HashSC with IpValue key:
Retrieved in HashTable, IpAddress: 204.202.132.19, 3435824147, now trying to delete it
Removed 204.202.132.19, 3435824147 successfully!

Testing HashQP with String key:
Retrieved in HashTable, IpAddress: 204.202.132.19, 3435824147, now trying to delete it
Removed 204.202.132.19, 3435824147 successfully!

Now the contents of HashSC with the IpValue key has:
0: ----
1: ----
2: ----
3: 204.162.98.192, 3433194176
4: 205.226.203.62, 3454192446
5: ----
6: 204.162.98.98, 3433194082
7: 204.162.98.2, 3433193986
8: 204.162.98.3, 3433193987
9: 204.162.98.4, 3433193988
10: 204.162.98.5, 3433193989
11: 204.162.98.6, 3433193990
12: 195.145.119.24, 3281090328 -> 204.162.98.7, 3433193991
13: 195.145.119.25, 3281090329 -> 204.162.98.8, 3433193992
14: 204.162.98.9, 3433193993
15: ----
16: 206.3.30.251, 3456311035 -> 204.162.98.11, 3433193995
17: 204.162.98.12, 3433193996
18: ----
19: ----
20: ----
21: ----
22: ----
23: 204.162.98.18, 3433194002
24: ----
25: ----
26: ----
27: ----
28: ----
29: ----
30: ----
31: 205.226.203.186, 3454192570
32: 204.162.98.124, 3433194108 -> 204.162.98.27, 3433194011
33: ----
34: 204.162.98.126, 3433194110
35: ----
36: ----
37: ----
38: ----
39: ----
40: ----
41: 204.162.97.1, 3433193729 -> 204.162.98.36, 3433194020
42: 204.162.97.2, 3433193730
43: 205.226.201, 13492937 -> 204.162.97.3, 3433193731 -> 204.162.98.38, 3433194022
44: ----
45: ----
46: ----
47: ----
48: 204.162.98.237, 3433194221
49: ----
50: 204.162.98.45, 3433194029
51: 204.162.97.205, 3433193933
52: ----
53: 204.162.98.48, 3433194032
54: 204.162.98.49, 3433194033
55: ----
56: ----
57: 204.162.97.17, 3433193745
58: 206.3.30.196, 3456310980
59: 202.33.250.146, 3391224466 -> 204.162.98.151, 3433194135
60: 202.33.250.147, 3391224467
61: 202.33.250.148, 3391224468
62: 202.33.250.149, 3391224469
63: 205.266.204.238, 3456814318 -> 202.33.250.150, 3391224470
64: 202.33.250.151, 3391224471
65: 202.33.250.152, 3391224472
66: 202.33.250.153, 3391224473
67: 202.33.250.154, 3391224474
68: ----
69: 204.162.98.161, 3433194145
70: ----
71: ----
72: 204.162.97.32, 3433193760
73: ----
74: 205.226.203.35, 3454192419 -> 204.162.97.228, 3433193956
75: ----
76: 204.162.98.168, 3433194152
77: 204.162.97.231, 3433193959
78: ----
79: ----
80: 204.162.96, 13410912
81: ----
82: ----
83: 198.5.208, 12977616
84: ----
85: 198.5.210, 12977618 -> 204.162.98.80, 3433194064
86: ----
87: ----
88: ----
89: ----
90: ----
91: ----
92: ----
93: 204.162.98.88, 3433194072
94: ----
95: 205.226.203.56, 3454192440 -> 204.162.97.152, 3433193880 -> 206.3.0.250, 3456303354
96: 204.162.98.91, 3433194075

Now the contents of HashQP with the IpValue key has:
0: ----
1: 204.162.98.88, 3433194072
2: ----
3: ----
4: ----
5: ----
6: 198.5.208, 12977616
7: 204.162.97.228, 3433193956
8: ----
9: ----
10: ----
11: 204.162.98.36, 3433194020
12: ----
13: 204.162.98.38, 3433194022
14: ----
15: ----
16: ----
17: ----
18: ----
19: ----
20: ----
21: ----
22: ----
23: ----
24: 204.162.98.151, 3433194135
25: ----
26: ----
27: ----
28: ----
29: ----
30: ----
31: 204.162.98.91, 3433194075
32: ----
33: ----
34: ----
35: 198.5.210, 12977618
36: ----
37: 204.162.97.231, 3433193959
38: 204.162.98.98, 3433194082
39: ----
40: ----
41: ----
42: ----
43: ----
44: ----
45: ----
46: ----
47: 204.162.98.45, 3433194029
48: ----
49: ----
50: 202.33.250.146, 3391224466
51: 202.33.250.147, 3391224467
52: 202.33.250.148, 3391224468
53: 202.33.250.149, 3391224469
54: 204.162.98.48, 3433194032
55: 204.162.98.49, 3433194033
56: ----
57: ----
58: 205.226.203.56, 3454192440
59: ----
60: ----
61: 204.162.98.161, 3433194145
62: 204.162.96, 13410912
63: ----
64: ----
65: ----
66: ----
67: ----
68: 204.162.98.168, 3433194152
69: ----
70: ----
71: ----
72: ----
73: ----
74: ----
75: ----
76: ----
77: ----
78: ----
79: ----
80: ----
81: 202.33.250.150, 3391224470
82: 202.33.250.151, 3391224471
83: 202.33.250.152, 3391224472
84: 205.226.203.186, 3454192570
85: 202.33.250.153, 3391224473
86: 202.33.250.154, 3391224474
87: ----
88: ----
89: ----
90: ----
91: 205.226.203.62, 3454192446
92: ----
93: ----
94: ----
95: ----
96: ----
97: ----
98: ----
99: ----
100: ----
101: ----
102: ----
103: ----
104: ----
105: ----
106: ----
107: ----
108: ----
109: ----
110: ----
111: 204.162.97.17, 3433193745
112: ----
113: 204.162.98.124, 3433194108
114: ----
115: 204.162.98.126, 3433194110
116: ----
117: ----
118: ----
119: ----
120: ----
121: ----
122: 204.162.97.152, 3433193880
123: ----
124: ----
125: ----
126: 206.3.30.196, 3456310980
127: 204.162.97.205, 3433193933
128: ----
129: 204.162.98.11, 3433193995
130: 204.162.98.12, 3433193996
131: ----
132: ----
133: ----
134: ----
135: ----
136: 204.162.98.18, 3433194002
137: ----
138: ----
139: ----
140: 204.162.98.2, 3433193986
141: 204.162.98.3, 3433193987
142: 204.162.98.4, 3433193988
143: 204.162.98.237, 3433194221
144: 204.162.98.5, 3433193989
145: 205.226.201, 13492937
146: 204.162.98.7, 3433193991
147: 204.162.98.8, 3433193992
148: 204.162.98.6, 3433193990
149: 204.162.97.1, 3433193729
150: 204.162.97.2, 3433193730
151: 204.162.97.3, 3433193731
152: ----
153: 195.145.119.24, 3281090328
154: 195.145.119.25, 3281090329
155: ----
156: 204.162.98.9, 3433193993
157: ----
158: 205.266.204.238, 3456814318
159: 206.3.0.250, 3456303354
160: 206.3.30.251, 3456311035
161: ----
162: ----
163: ----
164: ----
165: ----
166: ----
167: ----
168: ----
169: ----
170: ----
171: ----
172: 204.162.98.27, 3433194011
173: 204.162.98.192, 3433194176
174: ----
175: ----
176: ----
177: ----
178: ----
179: ----
180: 204.162.97.32, 3433193760
181: 205.226.203.35, 3454192419
182: ----
183: ----
184: ----
185: ----
186: ----
187: ----
188: ----
189: ----
190: 204.162.98.80, 3433194064
191: ----
192: ----
193: ----
194: ----
195: ----
196: ----
 * 
 * 
 * */

