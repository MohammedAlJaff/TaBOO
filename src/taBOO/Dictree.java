package taBOO;
import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import taBOO.Encoder.EncoderException;
import taBOO.FastaChunk.FastaChunkException;
// BLA

/**
 * WARNING: NOT DOC'ED!
 * @author RockefellerSuperstar
 *
 */

public class Dictree {

	// Instance Variable; 
	DTnode root;
	Encoder encoder;
	// Constructor;
	
	public Dictree(Encoder e) {
		root = new DTnode(0, e.getNcodeN());
		this.encoder = e;
	}
	
	// Incomplete;
	class DTnode{
		// Instance Variables
		int id;
		ArrayList<DTnode> children;
		ArrayList<int[]> words;
		int level; 
		
		// Constructor ::::::::::::::::::::::::::::::::::::
		public DTnode(int level, int NcodeN) {
			this.level = level; 
			if(this.level<2) {
				children = new ArrayList<DTnode>((int) Math.pow(5, NcodeN));
				for(int i=0; i<Math.pow(5, NcodeN); i++) {
					children.add(new DTnode(this.level+1, NcodeN));
				}
			} else {
				this.words = new ArrayList<int[]>(25);
			}
			
		}		
		// Method
		
		public DTnode getChild(int i) {
			return this.children.get(i);
		}	
	}
	
	public void populate(int[] word) {
		this.root.getChild(word[0]).getChild(word[1]).words.add(word);
	}
	
	public void populate(ArrayList<int[]> table) {
		for(int i=0; i<table.size(); i++) {
			this.populate(table.get(i));
		}
	}
	
	public void populateDeep(ArrayList<int[]> table) {
		for(int i=0; i<table.size(); i++) {
			this.populate(table.get(i));
		}
	}
	
	public boolean containsEaxaktly(int[] query) throws EncoderException {;
	
		for(int[] w: this.getWords(query[0], query[1])) {
			if(w!= null){
				if(encoder.withinKMismatches(query, w, 0)) {
				return true;
				}
			};
		}
		return false;
	}
	
	
	
	
	
	
	
	
	public ArrayList<int[]> getWords(int adress1, int adress2){
		return this.root.getChild(adress1).getChild(adress2).words;
	}

	public void printWordsIn(int adress1, int adress2) {
		for(int[] w : this.getWords(adress1, adress2)) {
			for(int j=0; j<w.length; j++) {
				System.out.print(w[j]+", ");
			}
			System.out.println();
		}
	}

	/** WARNING: NOT DONE. DO!!
	 * 
	 */
	public void emptyDictree() {
		
	}
	
	/** Currently only works for one strand.
	 * Creates and returns a Dictree made from the content of a single PartialOrganism Class.
	 * @param p
	 * @param seqWordlength
	 * @param e
	 * @return
	 * @throws EncoderException
	 */
	public static Dictree createPartialDictree(PartialOrganism p, int seqWordLength, Encoder e ) throws EncoderException {
		
		// Check to see if WordLength is shorter than partialSeq length.
		if(p.getSeqLength()<seqWordLength) {
			throw new RuntimeException("seqWordLength is larger than the size of the partial"); 
		}
		
		String[] stringTable = new String[p.getSeqLength()-seqWordLength+1];
		
		for(int pos=0; pos<p.getSeqLength()-seqWordLength+1; pos++) {
			stringTable[pos] = p.getSeq().substring(pos, pos+seqWordLength);
		}
		
		System.out.print("Sorting stringTable...");
		long t0 = System.currentTimeMillis();
		Arrays.sort(stringTable);
		long t1 = System.currentTimeMillis();
		System.out.println("Done: Elapsed time: " + (t1-t0) + "msek.");
		
		ArrayList<int[]> encodedWords = new ArrayList<int[]>(stringTable.length);
		
		System.out.print("Encoding words...");
		t0 = System.currentTimeMillis();
		for(int i=0; i<stringTable.length; i++) {
			encodedWords.add(e.encode(stringTable[i]));
		}
		t1 = System.currentTimeMillis();
		System.out.println("Done: Elapsed time: " + (t1-t0) + "msek.");
		
		Dictree partialDictree = new Dictree(e);
		
		partialDictree.populate(encodedWords);
	
		// Get seq. from partials 
		// Make a string table to store the seqs in.
		// Sort the string table 
		// Convert strings ad put into array of arrays
		// Populate tree with seqs.
		
		return partialDictree;
	}
	
	/** NOT DONE - NOT EVEN STARTED ON!!!
 	 * Returns all words that begin with the integer that is passed
 	 * 
 	 * SHOULD THROW EXCEPTION IF INTEGER IS LARGER THAN MAX NcodeN number.
	 * @param adress1
	 * @return
	 */
	public ArrayList<ArrayList<int[]>> getWords(int adress1){
		
		ArrayList<ArrayList<int[]>> temp = new ArrayList<ArrayList<int[]>>();
		
		return temp;
	}
	
	/**
	 * A static method returns the a sorted array of Strings containing the sequences of
	 * length equal to the inputed seqWordLength taken from the inputed organism and all its
	 * partials.
	 * @param o
	 * @return
	 */
	public static String[] createStringTable(Organism o, int seqWordLength) {
		
		int s = o.getTotalGenomeLength() + o.getNumbPartials()*(1-seqWordLength);
		ArrayList<String> seqArrayList = new ArrayList<String>(s);
	
		for(PartialOrganism p: o.getPartials()) {
			
			for(int pos=0; pos<p.getSeqLength()-seqWordLength+1; pos++) {
				seqArrayList.add(p.getSeq().substring(pos, pos+seqWordLength));
				// ReversStrandMight Not need be included.
				//seqArrayList.add(p.getRevSeq().substring(pos, pos+seqWordLength));				
			}
		}
		
		String[] stringArray = seqArrayList.toArray(new String[seqArrayList.size()]);
		
		// sortString Table
		Arrays.sort(stringArray);	
		
		return stringArray;
	}
	
	/**
	 * The method encodes the the inputed string table into Ncode with a given N. 
	 * The method returns the words in array of array.
	 * @param s
	 * @param e
	 * @param NcodeN
	 * @return
	 * @throws EncoderException
	 */
	public static ArrayList<int[]> createEncodedWords(String[] s, Encoder e,  int NcodeN) throws EncoderException{
		
		if(NcodeN!=e.getNcodeN()) {
			throw new RuntimeException("Wrongly specifief NcodeN value");
		}
		
		ArrayList<int[]> encodedWords = new ArrayList<int[]>(s.length);
		
		for(int i=0; i<s.length; i++) {
			encodedWords.add(e.encode(s[i]));
		}
	
		return encodedWords;
	}
	
	public static Dictree createDictree(Organism o, Encoder e, int seqWordLength, int NcodeN ) throws EncoderException {
		
		// Initialize Dictree.
		Dictree dt = new Dictree(e);
		
		// Get stringStable and populating dictree with encodedWords arraylist.
		
		dt.populate(
				createEncodedWords(createStringTable(o, seqWordLength), e, NcodeN) 
				);
		// Return populated dictree. 
		return dt;
	}
	
	public void printWordsIn(int adress1) {
		
	}
	
	
	
	/** NOT DONE.
	 * This method returns true only if a sequence exists within the calling dictree that 
	 * is less than or equal than to thresh-mismatches away from the query array.
	 * @param query
	 * @param thresh
	 * @return
	 * @throws EncoderException 
	 */
	public boolean containsWithin(int[] query, int thresh, Encoder e, ArrayList<HashMap<Integer, ArrayList<int[]>>> h) throws EncoderException {

		int firstElement = query[0];
		int secondElement = query[1];
		
		ArrayList<int[]> possibleAdress1 = h.get(thresh).get(firstElement);
		
		for(int[] brick1 : possibleAdress1 ) {
			
			ArrayList<int[]> possibleAdress2 = h.get(thresh-brick1[1]).get(secondElement);
	
			for(int[] brick2: possibleAdress2) {
				
				for(int[] seq: this.getWords(brick1[0], brick2[0])) {
					if(e.withinKMismatches(query, seq, thresh)) return true;
				}
			}	
		}
		
		return false;
	}
	
	
	// Main ::::::::::::::::::::::::::::::::::::::
	public static void main(String[] args) throws InterruptedException, FastaChunkException, IOException, EncoderException {

		Organism o = new Organism("hiv.fna");
		Encoder e = new Encoder(5);
		Dictree dt = createDictree(o, e, 30, 5);
		dt.printWordsIn(3124 ,120);
		
		int thresh = 3;
		int[] query = {3124, 120, 135, 1527, 2775, 4};
		
		
		
		
		
		

		
		
		
		
		

	}
	
	
	
	
	
				
		
	
	
}
