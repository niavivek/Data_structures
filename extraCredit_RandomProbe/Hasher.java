/*Name of program - Hasher.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - An interface to hash the values .
 */
package extraCredit_RandomProbe;

public interface Hasher<E> {
	public int hash(E elem);
}

