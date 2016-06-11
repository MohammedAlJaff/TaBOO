package taBOO;

import java.util.*;

import taBOO.Node.NodeException;

public class TabooTree {

	
	// Instance variables ::::::::::::::::::::::::::::::
	
	private Node root;
	private Node LeafLinker;
	private int depth;
	private int NcodeN; 
	private int alphabetSize;
	
	// Constructors :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	public TabooTree(int NcodeN, int alphabetSize, int depth) {
		
		this.NcodeN = NcodeN;
		this.alphabetSize = alphabetSize;
		this.depth = depth;	
		this.root = new Node(-1);
	}
	
	// Methods ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	
	/**
	 * Returns the NcodeN value for the tree.
	 * @return
	 */
	public int getNcodeN() {
		return this.NcodeN;
	}
	
	/**
	 * Returns the depth value for the tree.
	 * @return
	 */
	public int getDepth() {
		return this.depth;
	}
	
	/**
	 * Returns the NcodeN value for the tree.
	 * @return
	 */
	public int getAlphabetSize() {
		return this.alphabetSize;
	}
	
	
	/**
	 * Method expands the tree from the root onwards with Nodes with IDs the same 
	 * as those contained in the word in sequential order.
	 * @param word
	 * @throws TabooTreeException
	 * @throws NodeException 
	 */
	public void expand(int[] word) throws TabooTreeException, NodeException{	
		if(word.length != depth) {
			throw new TabooTreeException("input sequence of wrong length."
					+ "Word length should be " + this.getDepth());
		} else {
			
			expandHelper(this.root, word, 0);
		}
	}
	
	public static void expandHelper(Node n, int[] word, int currentIndex) throws NodeException {	
		if(currentIndex==word.length) {
			
		} else {
			int temp = word[currentIndex];
			n.addChild(temp);
		//	System.out.println(currentIndex);
			expandHelper(n.getChild(temp), word, currentIndex+1);
		}
	}
	
	
	
	/**
	 * Prints all words in the tree to the console.
	 */
	public void printAllWords() {
		
		printAllWordsHelper(this.root, "", this.depth);
	}
	
	public static void printAllWordsHelper(Node n, String s, int depth) {
		
		if(!n.hasChildren()) {
			String temp = s+n.getID();
			System.out.println(temp);
		} else if(n.getID()==(-1) ){
			
			String temp = "";
			
			Set<Integer> children =   n.children.keySet();
			Integer[] c = children.toArray(new Integer[children.size()]);
			
			for(Integer i: c) {
			
				printAllWordsHelper(n.getChild(i), temp , depth);
			}	 
		}else {
			
			String temp = s+n.getID()+ ",";
			
			Set<Integer> children =   n.children.keySet();
			Integer[] c = children.toArray(new Integer[children.size()]);
			
			for(Integer i: c) {
			
				printAllWordsHelper(n.getChild(i), temp , depth);
			}
		}
	}
	
	
	
	
	
	
	
	// Class Exception ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	public static class TabooTreeException extends Exception{
		public TabooTreeException(String msg) {
			super(msg);
		}
	}
	
	
	
	// Main ::::::::::::::::::::::::::::::
	
	
	
	
	public static void main(String[] args) throws TabooTreeException, NodeException {
		
		TabooTree t1 = new TabooTree(5, 5, 5);
		
		int[] w1 = {1, 2, 3, 4, 5};
		int[] w2 = {1, 2, 3, 4, 5};
		int[] w3 = {1, 2, 1, 4, 5};
		int[] w4 = {3, 2, 3, 4, 5};
		int[] w5 = {5, 2, 3, 4, 5};
	
		t1.expand(w3);	
		t1.expand(w4);
		t1.printAllWords();
//		
//		t1.expand(w5);
//		t1.printAllWords();
//		
		
		

		
		
	}// End Main

}// End Class
