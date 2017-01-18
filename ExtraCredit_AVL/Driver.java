package ExtraCredit_AVL;
/*Name of program - Driver.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A driver class to test the implementation of a AVL tree.
 */
//package hw4;



public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 AVL_tree<Integer> avlTree = new AVL_tree<Integer>();
		  //duplicates not allowed 
		    avlTree.insert (new Integer(2));
		    avlTree.insert (new Integer(5));
		    avlTree.insert (new Integer(1));
		    avlTree.insert (new Integer(6));
		    avlTree.insert (new Integer(4));
		    avlTree.insert (new Integer(8));
		    avlTree.insert (new Integer(3));
		    avlTree.insert (new Integer(7));
		    avlTree.insert (new Integer(9));
		    avlTree.insert (new Integer(11));
		  
		   avlTree.writeIndentedTree(System.out);
		 
		   avlTree.remove(5);
		   System.out.println();
		   System.out.println("After removal of 5");
		   avlTree.writeIndentedTree(System.out);
		   
		   avlTree.remove(2);
		   System.out.println();
		   System.out.println("After removal of 2");
		   avlTree.writeIndentedTree(System.out);
		   avlTree.remove(1);
		   System.out.println();
		   System.out.println("After removal of 1");
		   avlTree.writeIndentedTree(System.out);
		   avlTree.remove(9);
		   System.out.println();
		   System.out.println("After removal of 9");
		   avlTree.writeIndentedTree(System.out);
		  
	}

	

}
/*
 * OUTPUT
 *                 4. 11
           3. 9
                4. 8
      2. 7
           3. 6
 1. 5
           3. 4
                4. 3
      2. 2
           3. 1

After removal of 7
           3. 11
      2. 9
                4. 8
           3. 6
 1. 5
           3. 4
                4. 3
      2. 2
           3. 1

After removal of 2
           3. 11
      2. 9
                4. 8
           3. 6
 1. 5
           3. 4
      2. 3
           3. 1

After removal of 1
           3. 11
      2. 9
                4. 8
           3. 6
 1. 5
           3. 4
      2. 3

After removal of 9
           3. 11
      2. 8
           3. 6
 1. 5
           3. 4
      2. 3
      
      
 * OUTPUT 1

                 4. 11
           3. 9
                4. 8
      2. 7
           3. 6
 1. 5
           3. 4
                4. 3
      2. 2
           3. 1

After removal of 7
           3. 11
      2. 9
                4. 8
           3. 6
 1. 5
           3. 4
                4. 3
      2. 2
           3. 1

After removal of 2
           3. 11
      2. 9
                4. 8
           3. 6
 1. 5
           3. 4
      2. 3
           3. 1

After removal of 1
           3. 11
      2. 9
                4. 8
           3. 6
 1. 5
           3. 4
      2. 3

After removal of 9
           3. 11
      2. 8
           3. 6
 1. 5
           3. 4
      2. 3

OUTPUT 1
                4. 11
           3. 9
                4. 8
      2. 7
           3. 6
 1. 5
           3. 4
                4. 3
      2. 2
           3. 1

After removal of 5
                4. 11
           3. 9
                4. 8
      2. 7
           3. 6
 1. 4
           3. 3
      2. 2
           3. 1

After removal of 2
                4. 11
           3. 9
                4. 8
      2. 7
           3. 6
 1. 4
           3. 3
      2. 1

After removal of 7
           3. 11
      2. 9
                4. 8
           3. 6
 1. 4
           3. 3
      2. 1

After removal of 1
           3. 11
      2. 9
           3. 8
 1. 6
      2. 4
           3. 3
 */
