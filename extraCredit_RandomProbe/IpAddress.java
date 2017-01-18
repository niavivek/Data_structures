/*Name of program - IpAddress.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class to store the ipadress as a value and string pair.
 */
package extraCredit_RandomProbe;

public class IpAddress {
	private long ipValue = 0;
	private String dottedDecimal = "0.0.0.0";

	public IpAddress() {
	}

	public IpAddress(String dec) {
		setDottedDecimal(dec);
	}

	public boolean setDottedDecimal(String s) {
		if (s == null || s.length() == 0)
			return false;
		dottedDecimal = s;
		ipValue = 0;
		String[] tokens = s.split("[.]");
		for (String tok : tokens) {
			int subVal = Integer.parseInt(tok);
			ipValue = ipValue * 256 + subVal;
		} // end for
		return true;
	}

	public long getIpValue() {
		return ipValue;
	}

	public String getDottedDecimal() {
		return dottedDecimal;
	}

	public String toString() {
		return dottedDecimal + ", " + ipValue;
	}
} // end class IpAddress