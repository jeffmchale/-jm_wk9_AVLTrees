//HEADER 
//Program Name: jm_wk9_AVLTrees
//Author: Jeff McHale
//Class: CS260 Fall 2019
//Date: 11/21/2019
//Description: The problem was to correctly fix the balance factor and insertion of new nodes in the AVL Tree.

package edu.cgcc.cs260;

public class AVLNode {
	//members
	public int key, height, balance;
	public AVLNode parent, left, right;
	private Person data;
	
	/**
	 * Constructor for AVL Tree Node
	 * @param k key value for data
	 * @param p parent node for AVL node
	 */
	AVLNode(int k, AVLNode p){
		key = k;
		parent = p;
		data = Person.generatePerson();
	}
	
	//public member functions
	public Person getData() {
		return data;
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
*/
