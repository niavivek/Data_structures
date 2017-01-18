/*Name of program - Date.java
Student name - Nianthrini Vivekanandan
Current Date - 05/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class that implements Comparable and checks if a date is valid,
it also compares 2 dates based on year, month and then day to determine the smaller of
the dates.
 */
//package hw4;

public class Date implements Comparable<Date> {

	static final int MIN_MONTH = 1;
	static final int MAX_MONTH = 12;
	static final int MIN_YEAR = 1000;
	static final int MAX_YEAR = 9999;
	static final int[] DAYS_IN_MONTH = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private int month = 1;
	private int day = 1;
	private int year = 1000;

	public Date() {
	}

	public Date(int m, int d, int y) {
		setDate(m, d, y); // else leave default values
	}

	// YOU WRITE a static isLeapYear(int) FUNCTION DEFINITION!!!
	// USE THE ALGORITHM: year is a leap year if it's:
	// divisible by 4 and not divisible by 100 OR
	// divisible by 400

	public static boolean isLeapYear(int y) {
		return (y % 4 == 0 && y % 100 != 0 || y % 400 == 0);
	}

	public boolean setDate(int m, int d, int y) {
		int isLeap = 0;

		if (y >= MIN_YEAR && y <= MAX_YEAR && m >= MIN_MONTH && m <= MAX_MONTH) {
			if (m == 2 && isLeapYear(y))
				isLeap = 1;
			if (d >= 1 && d <= (DAYS_IN_MONTH[m] + isLeap)) {
				month = m;
				day = d;
				year = y;
				return true;
			}
		}
		return false; // leaves instance vars. as they were before
	} // end setDate

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getYear() {
		return year;
	}

	public String toString() {
		return month + "/" + day + "/" + year;
	}

	// USE THE SAME METHOD YOU WROTE IN HW#3, BUT MAKE SURE IT'S FIXED
	// FEEDBACK ABOUT THIS METHOD WILL BE GIVEN WITHIN A WEEK
	// AFTER THE HW#3 DUE DATE
	@Override
	public int compareTo(Date param) {
		int value = 0;
		// compare year, if same compare month, if same compare day else return
		// -1 if value is greater and 1 if value is smaller.
		if (param.getYear() == year) {
			if (param.getMonth() == month) {
				if (param.getDay() == day)
					return value;
				else if (param.getDay() < day)
					return 1;
				else
					return -1;
			} else if (param.getMonth() < month)
				return 1;
			else
				return -1;
		} else if (param.getYear() < year)
			return 1;
		else
			return -1;
	}
}
