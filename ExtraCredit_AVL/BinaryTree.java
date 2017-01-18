package ExtraCredit_AVL;
/*Name of program - BinaryTree.java
Student name - Nianthrini Vivekanandan
Current Date - 05/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class which represents the binary tree.
 */
//package hw4;

import java.io.PrintStream;

// Adapted from code by Y. Daniel Liang
// Modified by C. Lee-Klawender

public abstract class BinaryTree<E> implements TreeInterface<E> {
	protected BinaryNode<E> root = null; // reference to the root
	protected int size = 0; // number of nodes in the tree

	public BinaryTree() {
	}

	/*
	 * FINISH THIS METHOD so it will call writeTree which will display the tree
	 * LIKE below, in this example, 50 is in the root 70 is in the RIGHT child
	 * of the root, 20 is in the LEFT child of the root, 80 is in the RIGHT
	 * child of 70, 60 is in the LEFT child of 70, 40 is in the RIGHT child of
	 * 20, 10 is in the LEFT child of 20 1. 50 2. 70 3. 80 3. 60 2. 20 3. 40 3.
	 * 10
	 */
	//method to write an indented tree to the screen
	public void writeIndentedTree(PrintStream writer) {
		// Call the writeTree, passing this parameter, this' root, 1 and ""
		writeTree(writer, root, 1, " ");
	}

	// FINISH THE FOLLOWING METHOD so it returns if the root is null,
	// calls print or println for the PrintWriter parameter to write the root's
	// data
	// then (like preorder, but "reversed" and with level and indent) make
	// recursive calls to writeTree
	//method to write an indented tree to the screen
	protected void writeTree(PrintStream writer, BinaryNode<E> root, int level, String indent) {
		if (root == null)
			return;
		//use tabs to represent the level if level is > 1 so the tree is indented
		if(level != 1)
		indent = indent+"     ";
		//print the values
		writeTree(writer, root.getRightChild(), level + 1, indent);
		writer.println(indent + level + ". " + root.getData());
		writeTree(writer, root.getLeftChild(), level + 1, indent);
	}

	/** Clears the whole tree */
	public void clear() {
		root = null;
		size = 0;
	}

	@Override /** Preorder traversal from the root */
	public void preorder(Visitor<E> visitor) {
		preorder(root, visitor);
	}

	@Override /** Inorder traversal from the root */
	public void inorder(Visitor<E> visitor) {
		// you finish (part of HW#4)
		inorder(root, visitor);
	}

	@Override /** Postorder traversal from the root */
	public void postorder(Visitor<E> visitor) {
		// you finish (part of HW#4)
		postorder(root, visitor);
	}

	@Override /** Get the number of nodes in the tree */
	public int getSize() {
		return size;
	}

	@Override /** Return true if the tree is empty */
	public boolean isEmpty() {
		return getSize() == 0;
	}

	/** Preorder traversal from a subtree */
	protected void preorder(BinaryNode<E> root, Visitor<E> visitor) {
		if (root == null)
			return;
		visitor.visit(root.getData());
		preorder(root.getLeftChild(), visitor);
		preorder(root.getRightChild(), visitor);
	}

	/** Inorder traversal from a subtree */
	protected void inorder(BinaryNode<E> root, Visitor<E> visitor) {
		// you finish (part of HW#4)
		if (root == null)
			return;
		inorder(root.getLeftChild(), visitor);
		visitor.visit(root.getData());
		inorder(root.getRightChild(), visitor);

	}

	/** Postorder traversal from a subtree */
	protected void postorder(BinaryNode<E> root, Visitor<E> visitor) {
		// you finish (part of HW#4)
		if (root == null)
			return;
		postorder(root.getLeftChild(), visitor);
		postorder(root.getRightChild(), visitor);
		visitor.visit(root.getData());

	}
} // end abstract BinaryTree class
