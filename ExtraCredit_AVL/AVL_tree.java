package ExtraCredit_AVL;
/*Name of program - AVL_tree.java
Student name - Nianthrini Vivekanandan
Current Date - 06/24/2016
Computer operating system and compiler you are using - MAC OS X Version 10.9.5; JAVA SE-1.7; 
ECLIPSE Version: Mars.2 Release (4.5.2)
PROGRAM DESCRIPTION - A class to implement AVL tree.
 */

public class AVL_tree<E extends Comparable<E>> extends BST<E> {

	public AVL_tree() {
	}

	public AVL_tree(E[] objects) {
		super(objects);
	}
	//method to insert new node calls _insert by passing root
	public boolean insert(E e) {
		root = _insert((AVL_node<E>) root, e);
		super.size++;//increment size
		return true;
	}

	public AVL_node<E> _insert(AVL_node<E> node, E e) {
		//if node is null return new node
		if (node == null) {
			return new AVL_node<E>(e);
		} //if entry is less than node- go left
		else if (e.compareTo(node.getData()) < 0) {
			//insert on left side
			node.setLeftChild(_insert((AVL_node<E>) node.getLeftChild(), e));// recursive
			//balance tree, addition to left, left is heavier
			if ((getHeight((AVL_node<E>) node.getLeftChild()) - (getHeight((AVL_node<E>) node.getRightChild()))) > 1) {
				//if left child of data is less, rotate with left child
				//if left child is heavier, double rotate with left child
				if (e.compareTo(node.getLeftChild().getData()) < 0)
					node = rotateWithLeftChild(node);
				else
					node = doubleWithLeftChild(node);
			}
		}
		//if entry is greater than node- go right
		else if (e.compareTo(node.getData()) > 0) {
			// else go right to insert
			node.setRightChild(_insert((AVL_node<E>) node.getRightChild(), e));// recursive
			//balance tree - addition to right, right is heavier
			if (((getHeight((AVL_node<E>) node.getRightChild()))
					- ((getHeight((AVL_node<E>) node.getLeftChild())))) > 1) {
				//if right child of data is less, rotate with right child
				//if right child is heavier, double rotate with right child
				if (e.compareTo(node.getRightChild().getData()) > 0)
					node = rotateWithRightChild(node);
				else
					node = doubleWithRightChild(node);
			}
			
		}//node found - cannot insert duplicates 
		else
			System.out.println("Cannot insert duplicates, " + e + " is already present");
		//change height
		node.setHeight(
				Math.max(getHeight((AVL_node<E>) node.getLeftChild()), getHeight((AVL_node<E>) node.getRightChild()))
						+ 1);

		return node;

	}
//for inserting purpose - heights, -1 if no child
	public int getHeight(AVL_node<E> node) {
		if (node == null)
			return -1;
		else
			return node.getHeight();
	}
//get the full height - goes to the last deepest node without leaf and gets the height
	//0 if no child
	public int getFullHeight(AVL_node<E> node) {
		int height = 1;
		if (node == null)
			return 0;
		//if both child are present - get the tallest of the either sub-trees
		else if (node.getRightChild() != null && node.getLeftChild() != null) {
			height = getFullHeight((AVL_node<E>) node.getRightChild()) > getFullHeight(
					(AVL_node<E>) node.getLeftChild()) ? getFullHeight((AVL_node<E>) node.getRightChild()) + height
							: height + getFullHeight((AVL_node<E>) node.getLeftChild());
		}//if no left child-get height of right subtree 
		else if (node.getRightChild() != null)
			height = height + getFullHeight((AVL_node<E>) node.getRightChild());
		//if no right child - get height of left subtree
		else if (node.getLeftChild() != null)
			height = height + getFullHeight((AVL_node<E>) node.getLeftChild());
		return height;
	}

	public boolean remove(E e) {

		root = _remove((AVL_node<E>) root, e);
		if (root != null) {
			super.size--;//decrease count
			return true;
		} else
			return false;
	}

	public AVL_node<E> _remove(AVL_node<E> node, E e) {
		//if node is null, return null
		if (node == null) {
			return node;
		}
		//value is smaller than node, go left and remove from left child 
		if (e.compareTo(node.getData()) < 0) {

			node.setLeftChild(_remove((AVL_node<E>) node.getLeftChild(), e));
			//rebalance tree
			if ((getFullHeight((AVL_node<E>) node.getRightChild())
					- getFullHeight((AVL_node<E>) node.getLeftChild())) > 1) {
				//right could be heavier, since removal is from left
				int rightHeight;
				if (node.getRightChild() != null && node.getRightChild().getRightChild() != null)
					rightHeight = getFullHeight((AVL_node<E>) node.getRightChild().getRightChild());
				else
					rightHeight = getFullHeight((AVL_node<E>) node.getRightChild());
				int leftHeight;
				if (node.getLeftChild() != null && node.getLeftChild().getLeftChild() != null)
					leftHeight = getFullHeight((AVL_node<E>) node.getLeftChild().getLeftChild());
				else
					leftHeight = getFullHeight((AVL_node<E>) node.getLeftChild());
				//if right sub-tree is taller, double with right child, else double with left
				if (rightHeight >= leftHeight) {
				
					node = doubleWithRightChild(node);
				} else
				{
				
					node = doubleWithLeftChild(node);
				}
			}
		}//value is larger than node, go right and remove from right child 
		else if (e.compareTo(node.getData()) > 0) {

			node.setRightChild(_remove((AVL_node<E>) node.getRightChild(), e));
			//check for imbalance and rebalance
			 //since removal from right, left could be heavier
			if ((getFullHeight((AVL_node<E>) node.getLeftChild())
					- getFullHeight((AVL_node<E>) node.getRightChild())) > 1) {
				//since the left side is imbalance, get the height of the sub-trees
				int rightHeight;
				if (node.getRightChild() != null && node.getRightChild().getRightChild() != null)
					rightHeight = getFullHeight((AVL_node<E>) node.getRightChild().getRightChild());
				else
					rightHeight = getFullHeight((AVL_node<E>) node.getRightChild());
				int leftHeight;
				if (node.getLeftChild() != null && node.getLeftChild().getLeftChild() != null)
					leftHeight = getFullHeight((AVL_node<E>) node.getLeftChild().getLeftChild());
				else
					leftHeight = getFullHeight((AVL_node<E>) node.getLeftChild());
				//if left sub-tree is taller, double with left child, else double with right
				if (leftHeight >= rightHeight) {
					node = doubleWithLeftChild(node);
				} else
				{
					node = doubleWithRightChild(node);
				}
			}
		}

		else {
			//if no child return null
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				return null;
			}
			//if left child is null, return right
			if (node.getLeftChild() == null) {
				return (AVL_node<E>) node.getRightChild();
			}
			//if right child is null return left
			if (node.getRightChild() == null) {
				return (AVL_node<E>) node.getLeftChild();
			}
			//if both child are present, go left once and all the way right
			AVL_node<E> rightMostLeft = (AVL_node<E>) node.getLeftChild();

			rightMostLeft = findMax((AVL_node<E>) rightMostLeft);
			E tempData = rightMostLeft.getData();
			node.setData(tempData);//change data value
			//remove the node and set the rest to the left child
			node.setLeftChild(_remove((AVL_node<E>) node.getLeftChild(), node.getData()));
			//rebalance tree - since the replacement for removal is done from the left
			//there would be imbalance on the right if there is one
			if ((getFullHeight((AVL_node<E>) node.getRightChild())
					- getFullHeight((AVL_node<E>) node.getLeftChild())) > 1) {
				
				int rightHeight = getFullHeight((AVL_node<E>) node.getRightChild().getRightChild());
				int leftHeight = getFullHeight((AVL_node<E>) node.getRightChild().getLeftChild());
				//if right side of right sub-tree is heavier - rotate with right child
				//if left side of right subtree is heavier - rotate double with right
				if (rightHeight >= leftHeight) {
					node = rotateWithRightChild(node);
				} else
				{
					node = doubleWithRightChild(node);
				}
			}

		}

		return node;
	}
//find the maximum of the child
	private AVL_node<E> findMax(AVL_node<E> child) {
		// TODO Auto-generated method stub
		if (child == null)
			return child;

		while (child.getRightChild() != null)
			child = (AVL_node<E>) child.getRightChild();
		return child;
	}
//find the minimum of the child
	private AVL_node<E> findMin(AVL_node<E> child) {
		// TODO Auto-generated method stub
		if (child == null)
			return child;

		while (child.getLeftChild() != null)
			child = (AVL_node<E>) child.getLeftChild();
		return child;
	}
//rotate with left child 
	protected AVL_node<E> rotateWithLeftChild(AVL_node<E> k2) {
		AVL_node<E> k1 = (AVL_node<E>) k2.getLeftChild();

		if (k1 != null) {
			k2.setLeftChild(k1.getRightChild());
			k1.setRightChild(k2);
			k2.setHeight(Math.max((getHeight((AVL_node<E>) k2.getLeftChild())),
					(getHeight((AVL_node<E>) k2.getRightChild()))) + 1);
			k1.setHeight(Math.max((getHeight((AVL_node<E>) k1.getLeftChild())), (getHeight((AVL_node<E>) k2))) + 1);
		}
		return k1;
	}

	// What I called Left Rotation at k2
	protected AVL_node<E> rotateWithRightChild(AVL_node<E> k2) {
		AVL_node<E> k1 = (AVL_node<E>) k2.getRightChild();
		if (k1 != null) {
			k2.setRightChild(k1.getLeftChild());
			k1.setLeftChild(k2);
			k2.setHeight(Math.max((getHeight((AVL_node<E>) k2.getLeftChild())),
					(getHeight((AVL_node<E>) k2.getRightChild()))) + 1);
			k1.setHeight(Math.max((getHeight((AVL_node<E>) k1.getRightChild())), (getHeight((AVL_node<E>) k2))) + 1);
		}
		return k1;
	}

	// What I called Left-Right double rotation:
	protected AVL_node<E> doubleWithLeftChild(AVL_node<E> k3) {
		k3.setLeftChild(rotateWithRightChild((AVL_node<E>) k3.getLeftChild()));
		return rotateWithLeftChild(k3);
	}

	// What I called Right-Left double rotation:
	protected AVL_node<E> doubleWithRightChild(AVL_node<E> k3) {
		k3.setRightChild(rotateWithLeftChild((AVL_node<E>) k3.getRightChild()));
		return rotateWithRightChild(k3);
	}

}
