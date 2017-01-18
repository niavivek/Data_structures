/*Name of program - MonthComparator.java
Student name - Nianthrini Vivekanandan
Current Date - 05/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class which compares the dates based on month ordering. This
implements comparator interface.
 */
//package hw4;

/**
 * Class to implement the comparator<Date> with month's being compared
 */
import java.util.Comparator;

public class MonthComparator implements Comparator<Date> {

	@Override
	public int compare(Date o1, Date o2) {
		// TODO Auto-generated method stub
		// get the value of the diff of the months
		int value = o1.getMonth() - o2.getMonth();
		// if value is zero, get diff of days
		if (value == 0) {
			value = o1.getDay() - o2.getDay();
			// if value is zero, get diff of years
			if (value == 0)
				value = o1.getYear() - o2.getYear();

		}
		// return value
		return value;
	}
}
