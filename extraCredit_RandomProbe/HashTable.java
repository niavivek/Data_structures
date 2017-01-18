/*Name of program - HashTable.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - An abstract class for hashtable containing abstract methods that has
to be implemented by the non-abstract sub-class.
 */
package extraCredit_RandomProbe;

import java.util.Comparator;

public abstract class HashTable<E> {
	   protected int numCollisions; // how many collisions since instantiating or rehashing
	   protected int longestCollisionRun;// longest run for ONE entry or longest linked list
	   protected Hasher<E> hasher;
	   protected Comparator<E> comparator;
	   
	   public HashTable(Hasher<E> h, Comparator<E> c)
	   {
		   hasher = h;
		   comparator = c;
	   }
	   
	   public abstract E getEntry(E target);
	   public abstract boolean contains( E x);
	   public abstract void makeEmpty();
	   public abstract boolean insert( E x);
	   public abstract boolean remove( E x);
	   public abstract int size();
	   public abstract boolean setMaxLambda( double lam );
	   public abstract void displayTable(); 
	   public abstract void displayStatistics();
}
