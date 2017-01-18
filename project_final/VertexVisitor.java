/*Name of program - VertexVisitor.java
 Project members - Nianthrini Vivekanandan, Hien Vo and Igor Ponomarev
 Team number - 5 - Minimum Spanning Tree
 Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
 ECLIPSE Version: Mars.2 Release (4.5.2)
 PROGRAM DESCRIPTION - A class which visits the vertex in the graph and prints it.
 */

public class VertexVisitor<E> implements Visitor<E>
	{
	int countVisited = 1;
	@Override
	public void visit(E x)
		{
		// TODO Auto-generated method stub
		System.out.println(countVisited +":  " + x);
		countVisited++;
		}

	}
