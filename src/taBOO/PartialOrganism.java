package taBOO;

public class PartialOrganism {
	
	/**
	 * identifier
	 */
	private String gi;
	
	/**
	 * Sequence
	 */
	private String seq;
	
	/**
	 * The reverse sequence
	 */
	private String revSeq;
	
	/**
	 * 
	 * @param fastaChunk
	 */
	public PartialOrganism(String fastaChunk) {
		fastaSplitter(fastaChunk);
	}

	/**
	 * 
	 * @param fastaChunk
	 */
	private void fastaSplitter(String fastaChunk) {
		String[] headAndShoulders= fastaChunk.split("\n");
		gi = headAndShoulders[0];
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < headAndShoulders.length; i++) {
			sb.append(headAndShoulders[i]);
		}
		seq = sb.toString();
	}
	
	public static void main() {
		
	}
	
}
