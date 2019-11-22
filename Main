//HEADER 
//Program Name: jm_wk9_AVLTrees
//Author: Jeff McHale
//Class: CS260 Fall 2019
//Date: 11/21/2019
//Description: The problem was to correctly fix the balance factor and insertion of new nodes in the AVL Tree.


package edu.cgcc.cs260;

public class Main {

	public static void main(String[] args) {
		int[] iArr = new int[] {10,8,7,9,15,12,17,16,18}; //add (Left-Left): 6,4; b < a < c
		int[] iArr1 = new int[] {10,8,7,9,15,14,17};//add (Right-Right): 18,19; c < a < b
		int[] iArr2 = new int[] {10,7,4,9,15,14,16,2,3}; //add (Left-Right): 2, 3; a < b < c
		AVLTree avl = new AVLTree();
		//create tree
		for(int i = 0; i < iArr2.length; i++) {
			avl.insert(iArr2[i]);
		}
		//avl.preOrder();
		System.out.println("Pre Order w/ Balance Factor:");
		avl.preOrder();
		System.out.println();
		avl.printBalancePreOrder();
		


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
