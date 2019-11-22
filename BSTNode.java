//HEADER 
//Program Name: jm_wk9_AVLTrees
//Author: Jeff McHale
//Class: CS260 Fall 2019
//Date: 11/21/2019
//Description: The problem was to correctly fix the balance factor and insertion of new nodes in the AVL Tree.

package edu.cgcc.cs260;

public class BSTNode {
	//members
	private int key;
	private Person data;
	public BSTNode left;
	public BSTNode right;

	//constructor
	public BSTNode(int k) {
		key = k;
		data = Person.generatePerson();
	}
	
	//member functions
	public int getKey() {
		return key;
	}
	
	public void setKey(int k) {
		key = k;
	}
	
	public Person getData() {
		return data;
	}
	
	public void setData(Person d) {
		data = d;
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
