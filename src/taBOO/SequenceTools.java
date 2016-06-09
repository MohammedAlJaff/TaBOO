package taBOO;

import java.util.HashMap;

public class SequenceTools {

	/**
	 * Reverses a sequence (i.e forward strand). 
	 * The sequence may contain the canonical bases and unknown bases "N" where "N" is it's own complementary base.
	 * The forward strand is expected to be read 5'-->3' while the reverse strand is expected to be read 3'-->5'.
	 * <br><br>Example:<br>"ATGGN" would return "NCCAT"
	 * @param seq the sequence to be reversed
	 * @return the reverse sequence of input sequence
	 */
	public static String reverseStrandCreator(String seq) {
		StringBuilder revSeq = new StringBuilder(seq.length());
		HashMap<String, String> h = new HashMap<String, String>();
		h.put("A", "T");
		h.put("T", "A");
		h.put("C", "G");
		h.put("G", "C");
		h.put("N", "N");
		
		for(int i = seq.length()-1; i >= 0; i--) {
			revSeq.append(h.get(String.valueOf(seq.charAt(i))));
		}
		
		return revSeq.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(SequenceTools.reverseStrandCreator("ATGGN"));
	}
	
}
