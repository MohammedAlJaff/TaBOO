package taBOO;

import java.util.*;

import taBOO.Node.NodeException;

public class TabooTree {

	
	// Instance variables ::::::::::::::::::::::::::::::
	
	private Node root;
	private Node leafLinker; // Remains to be used.
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
		if(currentIndex==word.length) { // DO NOTHING.
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
	
	/** NOT DONE! Maybe Not Needed(?).
	 * The method returns all words in the tree. More specifically, because 
	 * all "words" in a taboo tree is given by all the paths from the root to
	 * a leaf, this method essentially does a depth-first-traversal of the calling
	 * tree and returns each path in an array format. The sum of all such paths are 
	 * returned then as an 2D-array where each row
	 * @return
	 */
	public int[][] getAllWords() {
		int[][] x = new int[1][1];
		x[0][0] = -3;
		return x;
	}
	
	/**
	 * Given a query word in array format this method will return true
	 * is there exists a path in the calling tree specified by the query
	 * word. In more abstract terms a "true" return means that the query
	 * "sequence" is also in the taboo organisms.
	 * @param queryWord
	 * @return
	 */
	public boolean contains(int[] queryWord) throws TabooTreeException{
		
		if(queryWord.length != this.depth) {
			throw new TabooTreeException("Argument is of wrong length.");
			//throw new TabooTreeException("Query word length does not equal depth of tree");
		} else {
			return containsExactHelper(this.root, queryWord, 0);
			
		}
	}
	public boolean containsExactHelper(Node n, int[] queryWord ,int index) {
		if(n.hasChild(queryWord[index])) {
			if(index == queryWord.length-1) {
				return true;
			} else {
				return containsExactHelper(n.getChild(queryWord[index]), queryWord, index+1);
			}
		} else {
				return false;
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
		
		TabooTree t1 = new TabooTree(5,5,5);
		
		int[][]	input1 = {
				{1, 2, 3, 4, 53},
				{1, 2, 3, 4, 52},
				{1, 2, 2, 2, 2},
				{1, 2, 2, 2, 3},
				{2, 2, 2, 2, 3},
				{3, 2, 2, 2, 3}
				};	
		
		for(int i=0; i<input1.length; i++ ) {
			t1.expand(input1[i]);
		}
		
		t1.printAllWords();


		
		
	}// End Main

}// End Class
