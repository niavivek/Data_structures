package ExtraCredit_AVL;
/*Name of program - AVL_node.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A node for AVL tree.
 */
public class AVL_node<E> extends BinaryNode<E> {

	private int height;
	//constructor which sets the height to 0
	public AVL_node()
	{
		super();
		height = 0;
	}
	//sets the data and height
	public AVL_node(E data)
	{
		super(data);
		height = 0;
	}
	//get the height
	public int getHeight() {
		return height;
	}
	//set the height
	public void setHeight(int height) {
		this.height = height;
	}
}
