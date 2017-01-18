/*Name of program - IpAddressValueHasher.java
Student name - Nianthrini Vivekanandan
Current Date - 06/05/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class that implements hasher to override hashing method.
 */
package hw5;

public class IpAddressValueHasher implements Hasher<IpAddress> {

	@Override
	//return the long value of the ipaddress cast to int
	public int hash(IpAddress elem) {
		// TODO Auto-generated method stub
		return (int)elem.getIpValue();
	}

}
