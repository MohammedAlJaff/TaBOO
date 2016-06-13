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
			
			n.addChild(word[currentIndex]);
		//	System.out.println(currentIndex);
			expandHelper(n.getChild(word[currentIndex]), word, currentIndex+1);
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
			Set<Integer> children =  (n.children.keySet());
			Integer[]c = children.toArray(new Integer[children.size()]);
			Arrays.sort(c);
			for(Integer i: c) {
				printAllWordsHelper(n.getChild(i), temp , depth);
			}	 
		}else {	
			String temp = s+n.getID()+ ",";
			Set<Integer> children =   n.children.keySet();
			
			Integer[] c = children.toArray(new Integer[children.size()]);
			Arrays.sort(c);
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
	
	/**EXTREMLY FRAGILE! - ONLY HANDLES WORDLENGTH DIVISIBLE BY 5!!!!! 
	 * This method returns a full TabooTree constructed from the n-code converted sequences
	 * (of length w) found in the string s.
	 * @param seed
	 * @param s
	 * @param wordLength
	 * @param NcodeN
	 * @param e
	 * @throws TabooTreeException 
	 * @throws NodeException 
	 */
	public static void growTree(TabooTree t, String s, int wordLength, int NcodeN , Encoder e) throws TabooTreeException, NodeException {
		if (t.getNcodeN() != NcodeN || t.getDepth() != (wordLength/NcodeN)) {
			throw new TabooTreeException("Fuck off");
		}else {
			
			for(int i=0; i<(s.length()-wordLength+1); i++) {
				String temp = s.substring(i, i+wordLength);
				t.expand(e.encode5(temp));
			}	
		}
	}
	
	/** NEEDS TESING!
	 * Method essentially construct a input TabooTree from an array of input Organisms.
	 * @param t
	 * @param os
	 * @param wordLength
	 * @param NcodeN
	 * @param e
	 * @throws TabooTreeException
	 * @throws NodeException
	 */
	public static void turnTaboo(TabooTree t, Organism[] os, int wordLength, int NcodeN , Encoder e) throws TabooTreeException, NodeException {
		
		for (Organism o: os) {		
			for(PartialOrganism p : o.getPartials()) {
				t.growTree(t, p.getSeq(), wordLength, NcodeN, e);
				t.growTree(t, p.getRevSeq(), wordLength, NcodeN, e);	
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
		
		// Create empty tree
		TabooTree t = new TabooTree(5, 4, 2);
		Encoder encoder  = new Encoder();
		
		// Create sequence string that gets encoded
		
		String s1 = "AAAAATTTTTTTTTTTTTTTTAAAAAAAAATTTTTTTTTTTAAAAAAAAATTTTTTTTTTTAAAAAAAAA"
				+ "TTTTTTTTTTTAAAAAAAAATTTTTTTTTTTAAAAAAAAATTTTTTTTTTTAAAAAAAAA"
				+ "TTTTTTTTTTTAAAAAAAAAATAAATACACGAGAGCAGAGACAGCAGAGCAAAATAAATACACGAGAGCAGAGACAGCAGAG"
				+ "CAAAATAAATACACGAGAGCAGAGACAGCAGAGCAAAATAAATACACGAGAGCAGAGACAGCAGAGCAAAAAAA";
		
		TabooTree.growTree(t, s1, 10, 5, encoder);

		
		//TabooTree.growTree(t, s1, 10, 5, encoder);
		t.printAllWords();
	
		System.out.println("-------------------");
		
	
		HashSet<Integer> x = new HashSet<Integer>();
		x.add(4);
		x.add(26);
		x.add(1);
		x.add(5);
		for(Integer i: x) {
			System.out.print(i + " ");
		}
		
		Integer[] xa = x.toArray(new Integer[x.size()]);
		
		Arrays.sort(xa);
		
		
	}// End Main

}// End Class
