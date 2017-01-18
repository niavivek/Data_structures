/*Name of program - Driver.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A driver to test random hashing by inserting ipaddress and test and display the hash
tables and statistics.
 */
package extraCredit_RandomProbe;

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
	
	public static IpAddress putEntry(HashTable<IpAddress> var1)
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
					System.out.println("Inserted into HashRandom: "+a1.toString());
				
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
		HashTable<IpAddress> var2 = new HashRandom<>(new IpAddressStringHasher(),new IpAddressStringComparator());
		//insert the values in the hashtable
		IpAddress retVal = putEntry(var2);
		if(retVal != null)
		{
			//if the file was not empty do some tests
			//display table values and the statistics
			
			
			System.out.println("\nContents of HashRandom with the String key:");
			var2.displayTable();
			var2.displayStatistics();
			
			
			System.out.println("\nTesting HashRandom with String key:");
			if(var2.contains(retVal))
				testHashTable(var2,retVal);
			else
				System.out.println("HashRandom does not contain: "+retVal);
			//display contents of the hashtable after removing a value
			
			System.out.println("\nNow the contents of HashRandom with the IpValue key has:");
			var2.displayTable();
			
		}
		else
			System.out.println("Unable to read the file - End program");
	}
}

/*
 * OUTPUT
 
Enter the input filename: HW5Input.txt
Inserted into HashRandom: 205.226.201, 13492937
Inserted into HashRandom: 205.226.203.186, 3454192570
Inserted into HashRandom: 205.226.203.35, 3454192419
Inserted into HashRandom: 205.226.203.56, 3454192440
Inserted into HashRandom: 205.226.203.62, 3454192446
Inserted into HashRandom: 205.266.204.238, 3456814318
Inserted into HashRandom: 202.33.250.146, 3391224466
Inserted into HashRandom: 202.33.250.147, 3391224467
Inserted into HashRandom: 202.33.250.148, 3391224468
Inserted into HashRandom: 202.33.250.149, 3391224469
Inserted into HashRandom: 202.33.250.150, 3391224470
Inserted into HashRandom: 202.33.250.151, 3391224471
Inserted into HashRandom: 202.33.250.152, 3391224472
Inserted into HashRandom: 202.33.250.153, 3391224473
Inserted into HashRandom: 202.33.250.154, 3391224474
Inserted into HashRandom: 204.162.96, 13410912
Inserted into HashRandom: 204.162.97.1, 3433193729
Inserted into HashRandom: 204.162.97.152, 3433193880
Inserted into HashRandom: 204.162.97.17, 3433193745
Inserted into HashRandom: 204.162.97.2, 3433193730
Inserted into HashRandom: 206.3.30.196, 3456310980
Inserted into HashRandom: 206.3.0.250, 3456303354
Inserted into HashRandom: 206.3.30.251, 3456311035
Inserted into HashRandom: 195.145.119.24, 3281090328
Inserted into HashRandom: 195.145.119.25, 3281090329
Inserted into HashRandom: 198.5.208, 12977616
Inserted into HashRandom: 198.5.210, 12977618
Inserted into HashRandom: 204.162.97.205, 3433193933
Inserted into HashRandom: 204.162.97.228, 3433193956
Inserted into HashRandom: 204.162.97.231, 3433193959
Inserted into HashRandom: 204.162.97.3, 3433193731
Inserted into HashRandom: 204.162.97.32, 3433193760
Inserted into HashRandom: 204.162.98.11, 3433193995
Inserted into HashRandom: 204.162.98.12, 3433193996
Inserted into HashRandom: 204.162.98.124, 3433194108
Inserted into HashRandom: 204.162.98.126, 3433194110
Inserted into HashRandom: 204.162.98.151, 3433194135
Inserted into HashRandom: 204.162.98.161, 3433194145
Inserted into HashRandom: 204.162.98.168, 3433194152
Inserted into HashRandom: 204.162.98.18, 3433194002
Inserted into HashRandom: 204.162.98.192, 3433194176
Inserted into HashRandom: 204.162.98.2, 3433193986
Inserted into HashRandom: 204.162.98.237, 3433194221
Inserted into HashRandom: 204.162.98.27, 3433194011
Inserted into HashRandom: 204.162.98.3, 3433193987
Inserted into HashRandom: 204.162.98.36, 3433194020
Inserted into HashRandom: 204.162.98.38, 3433194022
Inserted into HashRandom: 204.162.98.4, 3433193988
Inserted into HashRandom: 204.162.98.45, 3433194029
Inserted into HashRandom: 204.162.98.48, 3433194032
Inserted into HashRandom: 204.162.98.49, 3433194033
Inserted into HashRandom: 204.162.98.5, 3433193989
Inserted into HashRandom: 204.162.98.6, 3433193990
Inserted into HashRandom: 204.162.98.7, 3433193991
Inserted into HashRandom: 204.162.98.8, 3433193992
Inserted into HashRandom: 204.162.98.80, 3433194064
Inserted into HashRandom: 204.162.98.88, 3433194072
Inserted into HashRandom: 204.162.98.9, 3433193993
Inserted into HashRandom: 204.162.98.91, 3433194075
Inserted into HashRandom: 204.162.98.98, 3433194082
Inserted into HashRandom: 204.202.132.19, 3435824147

Contents of HashRandom with the String key:
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
54: ----
55: ----
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
77: 205.226.203.35, 3454192419
78: ----
79: ----
80: ----
81: 202.33.250.150, 3391224470
82: 202.33.250.151, 3391224471
83: 202.33.250.152, 3391224472
84: 205.226.203.186, 3454192570
85: 202.33.250.154, 3391224474
86: ----
87: ----
88: ----
89: ----
90: 204.162.98.49, 3433194033
91: 205.226.203.62, 3454192446
92: ----
93: ----
94: ----
95: ----
96: ----
97: ----
98: 204.162.98.48, 3433194032
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
132: 205.226.201, 13492937
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
144: 204.162.98.6, 3433193990
145: 204.162.98.7, 3433193991
146: 204.162.98.8, 3433193992
147: 204.162.98.9, 3433193993
148: 206.3.0.250, 3456303354
149: 204.162.97.1, 3433193729
150: 204.162.97.2, 3433193730
151: 204.162.97.3, 3433193731
152: ----
153: 195.145.119.24, 3281090328
154: 195.145.119.25, 3281090329
155: ----
156: ----
157: ----
158: 205.266.204.238, 3456814318
159: ----
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
181: ----
182: ----
183: ----
184: ----
185: 204.162.98.5, 3433193989
186: ----
187: ----
188: ----
189: ----
190: 204.162.98.80, 3433194064
191: ----
192: 202.33.250.153, 3391224473
193: ----
194: ----
195: ----
196: ----

In the HashQP object:

Table Size = 197
Number of entries = 61
Load factor = 0.3096446700507614
Number of collisions = 8
Longest Collision Run = 2

Testing HashRandom with String key:
Retrieved in HashTable, IpAddress: 204.202.132.19, 3435824147, now trying to delete it
Removed 204.202.132.19, 3435824147 successfully!

Now the contents of HashRandom with the IpValue key has:
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
54: ----
55: ----
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
77: 205.226.203.35, 3454192419
78: ----
79: ----
80: ----
81: 202.33.250.150, 3391224470
82: 202.33.250.151, 3391224471
83: 202.33.250.152, 3391224472
84: 205.226.203.186, 3454192570
85: 202.33.250.154, 3391224474
86: ----
87: ----
88: ----
89: ----
90: 204.162.98.49, 3433194033
91: 205.226.203.62, 3454192446
92: ----
93: ----
94: ----
95: ----
96: ----
97: ----
98: 204.162.98.48, 3433194032
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
132: 205.226.201, 13492937
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
144: 204.162.98.6, 3433193990
145: 204.162.98.7, 3433193991
146: 204.162.98.8, 3433193992
147: 204.162.98.9, 3433193993
148: 206.3.0.250, 3456303354
149: 204.162.97.1, 3433193729
150: 204.162.97.2, 3433193730
151: 204.162.97.3, 3433193731
152: ----
153: 195.145.119.24, 3281090328
154: 195.145.119.25, 3281090329
155: ----
156: ----
157: ----
158: 205.266.204.238, 3456814318
159: ----
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
181: ----
182: ----
183: ----
184: ----
185: 204.162.98.5, 3433193989
186: ----
187: ----
188: ----
189: ----
190: 204.162.98.80, 3433194064
191: ----
192: 202.33.250.153, 3391224473
193: ----
194: ----
195: ----
196: ----

Enter the input filename: HW5TestInput1.txt
Inserted into HashRandom: 203.255.234.105, 3422546537
Inserted into HashRandom: 203.255.234.102, 3422546534
Inserted into HashRandom: 203.255.234.103, 3422546535
Inserted into HashRandom: 203.255.234.106, 3422546538
Inserted into HashRandom: 203.141.52.41, 3415028777
Inserted into HashRandom: 203.141.52.42, 3415028778
Inserted into HashRandom: 203.141.52.43, 3415028779
Inserted into HashRandom: 203.141.52.44, 3415028780
Inserted into HashRandom: 207.126.239.224, 3481202656
Inserted into HashRandom: 203.141.52.45, 3415028781
Inserted into HashRandom: 203.141.52.46, 3415028782
Inserted into HashRandom: 203.141.52.47, 3415028783
Inserted into HashRandom: 206.190.43.125, 3468569469
Inserted into HashRandom: 206.190.43.81, 3468569425

Contents of HashRandom with the String key:
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

Testing HashRandom with String key:
Retrieved in HashTable, IpAddress: 206.190.43.81, 3468569425, now trying to delete it
Removed 206.190.43.81, 3468569425 successfully!

Now the contents of HashRandom with the IpValue key has:
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
Inserted into HashRandom: 72.30.214, 4726486
Inserted into HashRandom: 72.30.215, 4726487
Inserted into HashRandom: 72.30.216, 4726488
Inserted into HashRandom: 72.30.221, 4726493
Inserted into HashRandom: 72.30.226, 4726498
Inserted into HashRandom: 72.30.252, 4726524
Inserted into HashRandom: 72.30.54, 4726326
Inserted into HashRandom: 72.30.56, 4726328
Inserted into HashRandom: 72.30.60, 4726332
Inserted into HashRandom: 72.30.61, 4726333
Inserted into HashRandom: 72.30.8.1, 1209927681
Inserted into HashRandom: 72.30.87, 4726359
Inserted into HashRandom: 72.30.9, 4726281
Inserted into HashRandom: 72.30.97, 4726369
Inserted into HashRandom: 72.30.98, 4726370
Inserted into HashRandom: 72.30.99, 4726371
Inserted into HashRandom: 74.6.131, 4851331
Inserted into HashRandom: 74.6.17, 4851217
Inserted into HashRandom: 74.6.18, 4851218
Inserted into HashRandom: 7.4.6.19, 117704211
Inserted into HashRandom: 74.6.20, 4851220
Inserted into HashRandom: 74.6.21, 4851221
Inserted into HashRandom: 74.6.22, 4851222
Inserted into HashRandom: 74.6.23, 4851223
Inserted into HashRandom: 74.6.24, 4851224
Inserted into HashRandom: 74.6.240, 4851440
Inserted into HashRandom: 74.6.25, 4851225
Inserted into HashRandom: 74.6.26, 4851226
Inserted into HashRandom: 74.6.27, 4851227
Inserted into HashRandom: 74.6.28, 4851228
Inserted into HashRandom: 74.6.29, 4851229
Inserted into HashRandom: 74.6.31, 4851231
Inserted into HashRandom: 74.6.65, 4851265
Inserted into HashRandom: 74.6.66, 4851266
Inserted into HashRandom: 74.6.67, 4851267
Inserted into HashRandom: 74.6.68, 4851268
Inserted into HashRandom: 74.6.69, 4851269
Inserted into HashRandom: 74.6.7, 4851207
Inserted into HashRandom: 74.6.70, 4851270
Inserted into HashRandom: 74.6.71, 4851271
Inserted into HashRandom: 74.6.72, 4851272
Inserted into HashRandom: 74.6.73, 4851273
Inserted into HashRandom: 74.6.74, 4851274
Inserted into HashRandom: 74.6.75, 4851275
Inserted into HashRandom: 74.6.76, 4851276
Inserted into HashRandom: 74.6.79, 4851279
Inserted into HashRandom: 74.6.8, 4851208
Inserted into HashRandom: 74.6.85, 4851285
Inserted into HashRandom: 74.6.86, 4851286
Inserted into HashRandom: 74.6.87, 4851287

Contents of HashRandom with the String key:
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
49: 74.6.20, 4851220
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
99: 74.6.240, 4851440
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
115: 72.30.215, 4726487
116: 7.4.6.19, 117704211
117: ----
118: ----
119: ----
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
136: 72.30.216, 4726488
137: 74.6.31, 4851231
138: ----
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
164: 72.30.8.1, 1209927681
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
Number of collisions = 3
Longest Collision Run = 1

Testing HashRandom with String key:
Retrieved in HashTable, IpAddress: 74.6.87, 4851287, now trying to delete it
Removed 74.6.87, 4851287 successfully!

Now the contents of HashRandom with the IpValue key has:
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
49: 74.6.20, 4851220
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
99: 74.6.240, 4851440
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
115: 72.30.215, 4726487
116: 7.4.6.19, 117704211
117: ----
118: ----
119: ----
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
136: 72.30.216, 4726488
137: 74.6.31, 4851231
138: ----
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
164: 72.30.8.1, 1209927681
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
 * */

