package taBOO;

import static org.junit.Assert.*;

import org.junit.Test;

import taBOO.FastaChunk.FastaChunkException;

public class PartialOrganismTest {


	@Test
	public void testGetSeqAndGetRevSeq() throws FastaChunkException {
		
		String s1 = ">Bla\nATTTATTT";
		FastaChunk f1 = new FastaChunk(s1);
		PartialOrganism p = new PartialOrganism(f1);
		
		boolean b1 = p.getGi().equals(">Bla");
		boolean b2 = p.getSeq().equals("ATTTATTT");
		boolean b3 = p.getRevSeq().equals("AAATAAAT");
		
		boolean[] actual = {b1, b2, b3};
		boolean[] expected = {true, true, true};
		
		assertArrayEquals(expected, actual);
	}

	
	@Test
	public void testPartialOrganismFactory() throws FastaChunkException {
		
		String s1 = ">Bla\nATTTATTT";
		FastaChunk f1 = new FastaChunk(s1);
		PartialOrganism p = new PartialOrganism(f1);
		
		PartialOrganism q = PartialOrganism.PartialOrganismFactory(p);
		
		boolean b1 = q.getGi().equals(p.getGi());
		boolean b2 = q.getSeq().equals(p.getSeq());
		boolean b3 = q.getRevSeq().equals(p.getRevSeq()); 
		
		boolean[] actual = {b1, b2, b3};
		boolean[] expected = {true, true, true}; 
		
	}
}
