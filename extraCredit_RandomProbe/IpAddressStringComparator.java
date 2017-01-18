/*Name of program - IpAddressStringComparator.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class that implements comparator interface and overrides the hashing method
to compare the string values of the ipaddress.
 */
package extraCredit_RandomProbe;

import java.util.Comparator;

public class IpAddressStringComparator implements Comparator<IpAddress> {

	@Override
	//compare the string value of the ipaddress and return the value
	public int compare(IpAddress o1, IpAddress o2) {
		// TODO Auto-generated method stub
		return o1.getDottedDecimal().compareTo(o2.getDottedDecimal());
	}

}
