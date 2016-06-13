package taBOO;

import taBOO.FastaChunk.FastaChunkException;

public class PartialOrganism {
	
	/**
	 * Identifier for the partial organism, e.g ">E.coli, chromosome 2".
	 */
	private String gi;
	
	/**
	 * Sequence for the partial organism, e.g "ATGCGATG".
	 */
	private String seq;
	
	/**
	 * The reverse sequence of the {@link PartialOrganism#seq} field.
	 */
	private String revSeq;
	
	/**
	 * Generates a complete PartialOrganism object. This means generating an identifier, a sequence
	 * and the reverse sequence. This constructor also handles any non-canonical bases by....
	 * @param fastaChunk a String consisting of a fasta header and a sequence body
	 * @throws FastaChunkException 
	 */
	public PartialOrganism(FastaChunk chunk) throws FastaChunkException {
		gi = chunk.getHead();
		seq = SequenceTools.baseFixer(chunk.getBody());
		revSeq = SequenceTools.reverseStrandCreator(seq);
		//TODO baseFixer, revSeq
	}
	
	private PartialOrganism(PartialOrganism p) {
		
		this.gi = p.getGi();
		this.seq = p.getSeq();
		this.revSeq = p.getRevSeq();
	}
	

	/**
	 * Generates the identifier and sequence of a PartialOrganism object from a fasta chunk.
	 * <br><br>The String:<br> ">header<br>ATGAGTAG"<br> would set the gi to ">header" and seq to "ATGAGTAG".
	 * @param fastaChunk a String consisting of a fasta header 
	 * and a sequence body, separated by a line break
	 */
//	private void Guilliotine(String fastaChunk) {
//		try {
//		String[] headAndShoulders= fastaChunk.split("\n");
//		gi = headAndShoulders[0];
//		seq = headAndShoulders[1];
//		}
//		catch (Exception e) {
//			System.out.println("fml");
//		}
//	}
	
	/**
	 * Gets the gi number of the partial organism
	 * @return the gi number of the partial organism, {@link PartialOrganism#gi}
	 */
	public String getGi() {
		return gi;
	}
	
	/**
	 * Gets the sequence of the partial organism
	 * @return the sequence of the partial organism, {@link PartialOrganism#seq}
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * Gets the reverse sequence of the partial organism
	 * @return the reverse sequence of the partial organism, {@link PartialOrganism#revSeq}
	 */
	public String getRevSeq() {
		return revSeq;
	}
	
	/**
	 * Creates a and returns a clone of the input PartialOrganism
	 */
	public static PartialOrganism PartialOrganismFactory(PartialOrganism p) {
		return new PartialOrganism(p);
	}
	
	
	/**
	 * Returns the identifier, sequence and reverse sequence of the partial organism<br><br>
	 * Example:<br>
	 * >E.coli, chromosome 2<br>
	 * Seq: &emsp; ATGAT<br>
	 * revSeq: TACTA
	 */
	public String toString() {
		return gi + "\n" + "Seq:\t" + seq + "\nRevSeq:\t" + revSeq;
	}
	
}
