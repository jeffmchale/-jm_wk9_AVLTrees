//HEADER 
//Program Name: jm_wk9_AVLTrees
//Author: Jeff McHale
//Class: CS260 Fall 2019
//Date: 11/21/2019
//Description: The problem was to correctly fix the balance factor and insertion of new nodes in the AVL Tree.


package edu.cgcc.cs260;
import java.util.NoSuchElementException;

/**
 * A Self-Balancing Binary Search Tree
 * The heights of the two child subtrees of any node differ by at most one
 * Insertions and deletions may require the tree to be re-balanced by one or more rotations
 */
public class AVLTree {
	//members
	private AVLNode root;
	
	//
	//private member functions
	//
	/**
	 * private insert node into AVL tree
	 * @param key integer representing key value for data in AVL Node
	 * @return true on insertion success, false on insertion failure
	 * @throws Exception
	 */
	private void insertNode(AVLNode rt, int key) throws Exception {
		//if root is null, add node to root
		if(rt == null) {
			root = new AVLNode(key, null);
			return;
		}
		
		//don't insert duplicate key
		//demonstration of exception handling
		if(rt.key == key)
			throw new Exception("Person " + key + " already exists.");
		
		//otherwise, insert node in appropriate position
		//infinite loop to keep going until 
		//correct position is found and returned
		while(true) {
			//set the parent to current rt
			AVLNode parent = rt;
			
			//check the key. left if it's less, right if it's not
			//short form if statement
			boolean goLeft = rt.key > key;
			rt = goLeft ? rt.left : rt.right;
			
			//insert at null leaf
			if(rt == null) {
				//if the key is left, insert on left
				//otherwise, insert on right
				if(goLeft)
					parent.left = new AVLNode(key, parent);
				else
					parent.right = new AVLNode(key, parent);
				
				//re-balance the tree
				rebalance(parent);
				
				//quit the loop
				break;
			}
		}

		return;
	}
	
	/**
	 * 
	 * @param rt
	 * @throws NoSuchElementException
	 */
	private void deleteNode(AVLNode rt) {
		//if it's a leaf node
		if(rt.left == null && rt.right == null) {
			
			//if parent is null, root should be deleted
			if(rt.parent == null)
				root = null; // set root to null
			else {
				AVLNode parent = rt.parent;
				if(parent.left == rt)
					parent.left = null;
				else
					parent.right = null;
				
				rebalance(parent);
			}
			
			return;
		}
		
		//delete left
		if(rt.left != null) {
			AVLNode child = rt.left;
			while(child.right != null)
				child = child.right;
			rt.key = child.key;
			deleteNode(child);
		}
		
		//delete right
		else {
			AVLNode child = rt.right;
			while(child.left != null)
				child = child.left;
			rt.key = child.key;
			deleteNode(child);
		}
	}
	
	/**
	 * re-balance the AVL tree after insertion and deletion
	 * @param rt
	 */
	private void rebalance(AVLNode rt) {
		//set the balance of the node
		setBalance(rt, null);

		if(rt.balance < -1) {
			if(height(rt.left.left) >= height(rt.left.right))
				rt = rotateRight(rt);
			else
				rt = rotateLeftThenRight(rt);
		}
		
		if(rt.balance > 1) {
			if (height(rt.right.right) >= height(rt.right.left))
                rt = rotateLeft(rt);
            else
                rt = rotateRightThenLeft(rt);
		}
		
		//re-balance on parent if it exists
		if(rt.parent != null)
			rebalance(rt.parent);
		//otherwise, use current node
		else
			root = rt;
	}
	
	/**
	 * rotate tree to the left for balance
	 * @param oldRT
	 * @return
	 */
	private AVLNode rotateLeft(AVLNode oldRT) {
		
		//get right child of rotation node
        AVLNode newRT = oldRT.right;
        
        //rotate right child left by changing right child's parent
        //to the rotation node's parent
        newRT.parent = oldRT.parent;
 
        //rotate the right child's, left child to the left
        //by rotating to the rotation node's right child
        oldRT.right = newRT.left;
 
        //if the rotation node's right child isn't null,
        //the new right child's parent gets set to rotation node
        if (oldRT.right != null)
            oldRT.right.parent = oldRT;
        
        //move the rotation node to right child's left
        newRT.left = oldRT;
        
        //set the rotation node's new parent to its right child
        oldRT.parent = newRT;
 
        //if right child's parent isn't null do housekeeping
        if (newRT.parent != null) {
        	
            if (newRT.parent.right == oldRT) {
                newRT.parent.right = newRT;
            } else {
                newRT.parent.left = newRT;
            }
        }
 
        setBalance(oldRT, newRT);
 
        return newRT;
    }
 
	/**
	 * rotate tree to the right for balance
	 * @param oldRT
	 * @return
	 */
    private AVLNode rotateRight(AVLNode oldRT) {
 
        AVLNode newRT = oldRT.left;
        newRT.parent = oldRT.parent;
 
        oldRT.left = newRT.right;
 
        if (oldRT.left != null)
            oldRT.left.parent = oldRT;
 
        newRT.right = oldRT;
        oldRT.parent = newRT;
 
        if (newRT.parent != null) {
            if (newRT.parent.right == oldRT) {
                newRT.parent.right = newRT;
            } else {
                newRT.parent.left = newRT;
            }
        }
 
        setBalance(oldRT, newRT);
 
        return newRT;
    }
 
    /**
     * perform two rotations, left then right
     * @param n
     * @return
     */
    private AVLNode rotateLeftThenRight(AVLNode n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }
 
    /**
     * perform two rotations, right then left
     * @param n
     * @return
     */
    private AVLNode rotateRightThenLeft(AVLNode n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }
    
    /**
     * set the balance for both nodes passed
     * @param a
     * @param b
     */
    private void setBalance(AVLNode a, AVLNode b) {    
    	
    	for (AVLNode n : new AVLNode[] {a, b}) {
    		if(n != null) {
	            reHeight(n);
	            n.balance = height(n.right) - height(n.left);
    		}
        }
    	
    }
    
    /**
     * set the height for a node
     * @param n
     * @return
     */
    private int height(AVLNode n) {
        if (n == null)
            return -1;
        
        return n.height;
    }
    
    /**
     * re-height the tree to reflect correct heights for nodes
     * @param n
     */
    private void reHeight(AVLNode n) {
        if (n != null) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
        }
    }
	
	/**
	 * print node key and person name to console
	 * @param rt node containing data
	 */
	private void printData(AVLNode rt) {
		System.out.print("(" + rt.key + ")" + rt.getData().getName() + " ");
	}
	private void printKey(AVLNode rt) {
		System.out.print("(" + rt.key + ")"+rt.balance+"\t");
	}
	
	/**
	 * recursively print nodes to console in pre-order traversal
	 * @param rt
	 */
	private void printPreOrder(AVLNode rt, boolean printKeyOnly) {
		
		//base case: if node is null, return
		if(rt == null) {
			return;
		}
		
		//print pre order
		if (printKeyOnly) 
			printKey(rt); 
		else
			printData(rt);
		printPreOrder(rt.left, printKeyOnly);
		printPreOrder(rt.right, printKeyOnly);
	}
	
	/**
	 * recursively print nodes to console in in-order traversal
	 * @param rt
	 */
	private void printInOrder(AVLNode rt) {
		
		//base case: if node is null, return
		if(rt == null) {
			return;
		}
		
		//print in order
		printInOrder(rt.left);
		printData(rt);
		printInOrder(rt.right);
	}
	
	/**
	 * recursively print nodes to console in post-order traversal
	 * @param rt
	 */
	private void printPostOrder(AVLNode rt) {
		
		//base case: if node is null, return
		if(rt == null) {
			return;
		}
		
		//print post order
		printPostOrder(rt.left);
		printPostOrder(rt.right);
		printData(rt);
	}
	
	private void printNodeBalancePreOrder(AVLNode n) {
        if(n == null)
        	return;
		
    	System.out.printf("%s\t", n.balance);
    	printNodeBalancePreOrder(n.left);
    	printNodeBalancePreOrder(n.right);
    }
	
	//
	//public member functions
	//
	
	/**
	 * abstraction for insertNode method
	 * @param key
	 */
	public void insert(int key) {
		//attempt insertion
		try {
			insertNode(root, key);
			
			//on success, print message
			System.out.println("Person " + key + " inserted succesfully.");
		}
		//catch failures
		catch(Exception e) {
			System.err.println((e.getMessage() != null) ? e.getMessage() : "");
		}
	}
	
	/**
	 * abstraction for deleteNode method
	 * @param key
	 */
	public void delete(int key) {
		try {
			
			//make sure root isn't null
			if(root == null)
				throw new NullPointerException("The tree root is unassigned.");
			
			//set the child node to root
			AVLNode child = root;
			
			//while children exist, find node to delete
			while(child != null) {
				//set the child to current node to check
				AVLNode rt = child;
				
				//determine whether to traverse right or left from rt
				child = key >= rt.key ? rt.right : rt.left;
				
				//if the node is found, delete it
				if(key == rt.key) {
					deleteNode(rt);
					System.out.println("Person " + key + " has been deleted.");
					return;
				}
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * print the tree in pre-order traversal
	 */
	public void preOrder() {
		printPreOrder(root, true);
	}
	
	/**
	 * print the tree in in-order traversal
	 */
	public void inOrder() {
		printInOrder(root);
	}
	
	/**
	 * print the tree in post-order traversal
	 */
	public void postOrder() {
		printPostOrder(root);
	}
	
	public void printBalancePreOrder() {
		printNodeBalancePreOrder(root);
	}
}

/*
Expected output:
Person 10 inserted succesfully.
Person 7 inserted succesfully.
Person 4 inserted succesfully.
Person 9 inserted succesfully.
Person 15 inserted succesfully.
Person 14 inserted succesfully.
Person 16 inserted succesfully.
Person 2 inserted succesfully.
Person 3 inserted succesfully.
Pre Order w/ Balance Factor:
(10)-1	(7)-1	(3)0	(2)0	(4)0	(9)0	(15)0	(14)0	(16)0	
-1	-1	0	0	0	0	0	0	0	
