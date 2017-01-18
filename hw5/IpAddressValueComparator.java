/*Name of program - IpAddressvalueComparator.java
Student name - Nianthrini Vivekanandan
Current Date - 06/05/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class that implements comparator and compares the long value of the ipaddress.
 */
package hw5;

import java.util.Comparator;

public class IpAddressValueComparator implements Comparator<IpAddress> {

	@Override
	//compares the long value and returns the result
	public int compare(IpAddress o1, IpAddress o2) {
		// TODO Auto-generated method stub
		return Long.compare(o1.getIpValue(), o2.getIpValue());
	}

}
