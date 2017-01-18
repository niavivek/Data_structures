/*Name of program - IpAddressStringHasher.java
Student name - Nianthrini Vivekanandan
Current Date - 06/05/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class that implements hasher interface to override the hashing mathod.
 */
package hw5;

public class IpAddressStringHasher implements Hasher<IpAddress> {
	
	
//returns a custom hash value for the string value of the ipaddress based on the character
	@Override
	public int hash(IpAddress elem) {
		// TODO Auto-generated method stub
		int k, retVal = 0;
		String value = elem.getDottedDecimal();
    for(k = 0; k < value.length(); k++ )
        retVal = 37 * retVal + value.charAt(k);
    return retVal;
	}
}
