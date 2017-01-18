/*Name of program - YearComparator.java
Student name - Nianthrini Vivekanandan
Current Date - 05/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class which compares the dates based on year ordering. This
implements comparator interface.
 */
//package hw4;

import java.util.Comparator;

public class YearComparator implements Comparator<Date> {

	@Override
	public int compare(Date o1, Date o2) {
		// TODO Auto-generated method stub
		// returns the difference between the dates
		return o1.compareTo(o2);
	}

}
