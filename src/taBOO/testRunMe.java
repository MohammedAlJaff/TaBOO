package taBOO;

import taBOO.FastaChunk.FastaChunkException;

public class testRunMe {
	
	public static void main(String[] args) throws FastaChunkException {
		String head = ">head";
		String body = "AGWTCKGATA";
		String test = head + "\n" + body;
		
		FastaChunk f = new FastaChunk(test);
		PartialOrganism p = new PartialOrganism(f);
		System.out.println(p);

	}

}
