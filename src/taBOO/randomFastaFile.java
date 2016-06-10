package taBOO;

import java.util.Random;

public class randomFastaFile {

	private static int numberOfFiles;
	
	private final int defaultLength = 10000;
	private final double defaultRange = 0.2;
	private final int defaultChunks = 10;
	private final boolean defaultOnlyATCG = true;
	private final int defaultLineLength = 80;
	
	private double range;
	private int length;
	private String filename;
	private int chunks;
	private boolean onlyATCG;
	private String fastaFile;
	private int lineLength;
	
	public randomFastaFile() {
		numberOfFiles++;
		this.range = defaultRange;
		this.length = defaultLength;
		this.chunks = defaultChunks;
		this.onlyATCG = defaultOnlyATCG;
		this.lineLength = defaultLineLength;
		this.filename = "fastaFile" + numberOfFiles;
		System.out.println("Creating fasta sequence with default parameters");
	}
	
	/**
	 * Hello
	 * @param filename name of file
	 * @param length length of each chunk body
	 * @param range range of each chunk body (0.0 - 1.0)
	 * @param chunks number of chunks
	 * @param onlyATCG true if only A,T,C,G should be used, false otherwise
	 * @param lineLength length of line in chunk body
	 */
	public randomFastaFile(String filename, int length, double range, int chunks, boolean onlyATCG, int lineLength) {
		this.filename = filename;
		this.length = length;
		this.range = range;
		this.chunks = chunks;
		this.onlyATCG = onlyATCG;
		this.lineLength = lineLength;
		numberOfFiles++;
		
		System.out.println("Creating fasta sequence with specified parameters");
		checkParameters();
		this.fastaFile = fastaGenerator();
	}
	
	private String fastaGenerator() {
		StringBuilder fasta = new StringBuilder();
		
		for(int i = 0; i < this.chunks; i++) {
			String header = headerGenerator(i);
			String seq = chopSeq(sequenceGenerator());
			fasta.append(header + seq + "\n");
		}
		return fasta.toString();
	}
	
	private String chopSeq(String seq) {
		StringBuilder newSeq = new StringBuilder(seq.length());
		
		for(int i = 0; i < seq.length(); i++) {
			if(i%this.lineLength == 0 && i > 0) {
				newSeq.append("\n");
			}
			newSeq.append(seq.charAt(i));
		}
		
		return newSeq.toString();
	}
	
	private String headerGenerator(int i) {
		return ">Sequence " + i + "\n";
	}
	
	private String sequenceGenerator() {
		StringBuilder seq = new StringBuilder(this.length);
		String[] bases;
		if(this.onlyATCG) {
			String[] tempBases = {"A", "C", "G", "T"};
			bases = tempBases;
		}
		else {
			String[] tempBases = {"A","C","G","T","N","K","M","B","V","S","W","D","Y","R","H"};
			bases = tempBases;
		}
		
		for(int i = 0; i < this.length; i++) {
			String base = bases[(int)(Math.random()*bases.length)];
			seq.append(base);
		}
		
		return seq.toString();
	}
	
	private void checkParameters() {
		System.out.println("Checking parameters...");
		if(this.length < 0) {
			System.out.println("Problem with length, setting to default value: " + defaultLength);
			this.length = 10000;
		}
		if(this.range < 0 || this.range > 1.0) {
			System.out.println("Problem with range, setting to default value: " + defaultRange);
		}
		if(this.chunks < 0) {
			System.out.println("Problem with chunks, setting to default value: " + defaultChunks);
		}
		if(this.lineLength < 0) {
			System.out.println("Problem with lineLength, setting to default value: " + defaultLineLength);
		}
		System.out.println("Done checking parameters");
	}
	
	public String printParameters() {
		return "filename:	" + this.filename + "\n" +
			   "length:		" + this.length + "\n" +
			   "range:		" + this.range + "\n" +
			   "chunks:		" + this.chunks + "\n" +
			   "lineLength:	" + this.lineLength + "\n" +;
	}
	
	public String toString() {
		return fastaFile;
	}
	
	public static void main(String[] args) {
		randomFastaFile f = new randomFastaFile("test", 100, 0.1, 4, true, 40);
		//System.out.println(f);
	}
	
}
