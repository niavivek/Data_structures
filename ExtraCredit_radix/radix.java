package ExtraCredit_radix;
/*Name of program - radix.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class than implements radix sort.
 It sorts from the units digit to the largest digit of the largest number one by one.
 */
import java.util.Scanner;

public class radix {
	//queue for storing the buckets for sorting
	private static LinkedQueue<Integer>[] valRadix = (LinkedQueue<Integer>[]) (new LinkedQueue[10]);

	public static void sort(int[] vals) {
		for (int p = 0; p < 10; p++) {
			valRadix[p] = new LinkedQueue<>();//initialize values
		}
	
		boolean flag = true;
		int divisor = 1;//divisor for the elements
		// sort the list
		while (flag) {//while the list is not yet sorted
			flag = false;
			//for each element get the digits starting from units
			for (int i = 0; i < vals.length; i++) {
				int index = (vals[i] / divisor) % 10;
				if (index > 0)//if the value is greater than 0, change flag value
					flag = true;
				//put the values into the queue
				valRadix[index].enqueue(new Integer(vals[i]));
			}

			//change to next digit value - tens. hundreds etc.,
			divisor = divisor * 10;
			int i = 0;//index for the array
			// copy values back into the array- dequeue from the queue one by one
			for (int k = 0; k < 10; k++) {
				while (!valRadix[k].isEmpty()) {
					int temp = valRadix[k].dequeue();
					vals[i] = temp;
					i++;
				}
			}
		}//continue until all the digits are sorted, you have a fully sorted array
		System.out.println("Sorted list");
		//print values
		for (int l = 0; l < vals.length; l++)
			System.out.println(vals[l]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] values;
		Scanner in = new Scanner(System.in);
		System.out.println("How many number do you want to sort");
		int num = in.nextInt();
		values = new int[num];
		System.out.print("Enter each integer followed by enter");
		for(int i=0;i<num;i++)
		{
			values[i] = in.nextInt();
		}
		sort(values);

	}

}
/*OUTPUT
 * 
 * How many number do you want to sort
20
Enter each integer followed by enter23
2
345
4564
342
23
1
2
45
67
86
56
67
55
89
90
9345
345
34
2456
Sorted list
1
2
2
23
23
34
45
55
56
67
67
86
89
90
342
345
345
2456
4564
9345
 */

