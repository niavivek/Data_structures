package ExtraCredit_AVL;
/*Name of program - BST.java
Student name - Nianthrini Vivekanandan
Current Date - 05/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class to denote the binary search tree, it has atmost 2 child nodes per root.
 */
//package hw4;



// Adapted from Code By Y. Daniel Liang
// Modified by C. Lee-Klawender

public class BST<E extends Comparable<E>> extends BinaryTree<E> {

	private boolean foundNode; // helper variable
	//private Comparator<E> c1;

	/** Create a default binary tree */
	public BST() {
		
	}

	/** Create a binary tree from an array of objects */
	public BST(E[] objects) {
		
		for (int i = 0; i < objects.length; i++)
			insert(objects[i]);
	}

	@Override /** Returns true if the element is in the tree */
	public boolean contains(E e) {
		BinaryNode<E> current = root; // Start from the root

		while (current != null) {
			if (e.compareTo(current.getData()) < 0) {
				current = current.getLeftChild();
			} else if (e.compareTo(current.getData()) > 0) {
				current = current.getRightChild();
			} else // element matches current.getData()
				return true; // Element is found
		} // end while

		return false;
	}

	@Override
	/**
	 * Returns the data of the Node that equals the parameter, null if not
	 * found.
	 */
	public E getEntry(E e) {
		// find node if present
		BinaryNode<E> foundNode = _findNode(root, e); // YOU WRITE FOR LAB EX.
		// 7.3
		// if not null the return the data
		if (foundNode != null)
			return foundNode.getData();
		// else return null
		return null;
	}

	private BinaryNode<E> _findNode(BinaryNode<E> node, E e) {
		// TODO Auto-generated method stub
		// if node is null return null
		if (node == null)

			return null;
		// if target data < node data, go to left and call find node again to
		// find the node
		else if (e.compareTo(node.getData()) < 0) {
			return _findNode(node.getLeftChild(), e);
		}
		// if target data > node data, go to right and call find node again to
		// find the node
		else if (e.compareTo(node.getData()) > 0) {
			return _findNode(node.getRightChild(), e);
		}
		// else they are equal, so return the node
		else
			return node;
	}

	@Override
	/**
	 * Insert element o into the binary tree Return true if the element is
	 * inserted successfully
	 */
	public boolean insert(E e) {
		
			// insert new node
			// FILL IN HERE FOR HW#4***********************
			root = _insert(root, e);
			size++;
	
		return true; // Element inserted successfully

	}

	// Private recursive method that returns an updated "root" node from where
	// current node is
	private BinaryNode<E> _insert(BinaryNode<E> node, E e) {

		// IF no more nodes THEN
		// return a new Node(e)
		if (node == null) {
			return new BinaryNode<E>(e);
		} else {
			// IF e < node's data THEN
			// keep going left to insert at the correct position
			if (e.compareTo(node.getData()) < 0)
				node.setLeftChild(_insert(node.getLeftChild(), e));// recursive
			// call
			else
				// else go right to insert
				node.setRightChild(_insert(node.getRightChild(), e));// recursive
			// call
		}
		return node;

	}

	@Override
	/**
	 * Delete an element from the binary tree. Return true if the element is
	 * deleted successfully Return false if the element is not in the tree
	 */
	public boolean delete(E e) {
		foundNode = false; // initialize boolean instance variable
		root = _delete(root, e); // call private method to do actual deletion

		if (foundNode) {
			size--;// Element deleted successfully
		}
		return foundNode;
	}

	// Private recursive method that returns an updated "root" node from where
	// current node is
	private BinaryNode<E> _delete(BinaryNode<E> node, E e) {
		if (node == null) {
			return null;
		}
		if (e.compareTo(node.getData()) < 0)
			node.setLeftChild(_delete(node.getLeftChild(), e));// recursive call
		else if (e.compareTo(node.getData()) > 0)
			node.setRightChild(_delete(node.getRightChild(), e));// recursive
		// call
		else {
			foundNode = true;
			node = _deleteNode(node);
		}
		return node;
	} // end _delete

	// Private method that either "moves up" the left or right child, OR
	// replaces the data in the nodeToDelete with the data in the rightmost
	// child of
	// the nodeToDelete's left child, then "removes" that node
	private BinaryNode<E> _deleteNode(BinaryNode<E> nodeToDelete) {
		if (nodeToDelete.isLeaf()) // node to delete has no children
		{
			return null;
		}
		if (!nodeToDelete.hasLeftChild()) // node to delete has no LEFT child
		{
			return nodeToDelete.getRightChild();
		}
		if (!nodeToDelete.hasRightChild()) // node to delete has no RIGHT child
		{
			return nodeToDelete.getLeftChild();
		}
		// must have left and right children
		// Locate the rightmost node in the left subtree of
		// the node to delete and also its parent
		BinaryNode<E> parentOfRightMost = nodeToDelete;
		BinaryNode<E> rightMost = nodeToDelete.getLeftChild();

		while (rightMost.getRightChild() != null) {
			parentOfRightMost = rightMost;
			rightMost = rightMost.getRightChild(); // Keep going to the right
		}

		// Replace the element in nodeToDelete by the element in rightMost
		nodeToDelete.setData(rightMost.getData()); // don't really delete the
		// node, just change the
		// data

		// Eliminate rightmost node
		if (parentOfRightMost.getRightChild() == rightMost)
			parentOfRightMost.setRightChild(rightMost.getLeftChild());
		else
			// Special case: nodeToDelete's leftChild has no rightChild
			parentOfRightMost.setLeftChild(rightMost.getLeftChild());

		return nodeToDelete;
	} // end private _deleteNode
		// method to get the first node

	public E getFirst()// you finish (part of HW#4)
	{
		// NON-recursive algorithm:
		// If the tree is empty, return null
		if (root == null)
			return null;
		BinaryNode<E> leftMost = root.getLeftChild();
		BinaryNode<E> parentOfLeftMost = root;
		// FIND THE LEFT-MOST LEFT CHILD
		while (leftMost != null) {
			parentOfLeftMost = leftMost;
			leftMost = leftMost.getLeftChild();
		}
		// WHEN you can't go left anymore, return the node's data to first Item
		return parentOfLeftMost.getData();
	}

	// method to get the last node
	public E getLast()// you finish (part of HW#4)
	{
		// If the tree is empty, return null

		if (root == null)
			return null;
		BinaryNode<E> rightMost = root.getRightChild();
		BinaryNode<E> parentOfRightMost = root;
		// FIND THE RIGHT-MOST RIGHT CHILD
		while (rightMost != null) {
			parentOfRightMost = rightMost;
			rightMost = rightMost.getRightChild();
		}
		// WHEN you can't go RIGHT anymore, return the node's data to last Item
		return parentOfRightMost.getData();

	}
} // end class BST
