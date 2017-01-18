/*Name of program - MonthVisitor.java
Student name - Nianthrini Vivekanandan
Current Date - 05/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class which visits the dates based on month ordering. This
implements Visitor interface.
 */
//package hw4;


public class MonthVisitor implements Visitor<Date> {

	@Override
	public void visit(Date obj) {
		// TODO Auto-generated method stub
		// print the visited date
		System.out.println(obj.toString());
	}

}
