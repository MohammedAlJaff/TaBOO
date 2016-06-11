package taBOO;

import java.util.HashSet;

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
	
	
	
	public void expand(int[] word) throws TabooTreeException{
		
		
	}
	
	
	
	// Class Exception ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	public static class TabooTreeException extends Exception{
		public TabooTreeException(String msg) {
			super(msg);
		}
	}
	
	
	
	// Main ::::::::::::::::::::::::::::::
	
	
	
	
	public static void main(String[] args) {
		

	}

}
