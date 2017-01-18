/*Name of program - Pair.java
Project members - Nianthrini Vivekanandan, Hien Vo and Igor Ponomarev
Team number - 5 - Minimum Spanning Tree
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class to represent a simple pair data structure, 
 (first, second), that uses first as a key.
 */

//--- a simple pair data structure, (first, second), that uses first as a key
public class Pair<E, F>
	{
	public E first;
	public F second;

	public Pair(E x, F y)
		{
		first = x;
		second = y;
		}

	public boolean equals(Object rhs)
		{
		Pair<E, F> other;
		other = (Pair<E, F>) rhs;
		return first.equals(other.first);
		}

	public int hashCode()
		{
		return first.hashCode();
		}
	}