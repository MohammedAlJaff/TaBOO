package taBOO;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrganismTest {

	@Test
	public void numberOfPartialsTest1() {
		randomFastaFile rff = new randomFastaFile("organismTest.txt", 100, 0.2, 6, true, 50);
		rff.toFile();
		Organism o = new Organism("organismTest.txt");
		
		assertEquals(6, o.getPartials().size());
	}
	
	@Test
	public void getGiTest1() {
		randomFastaFile rff = new randomFastaFile("organismTest.txt", 100, 0.2, 6, true, 50);
		rff.toFile();
		Organism o = new Organism("organismTest.txt");
		
		assertEquals("organismTest.txt", o.getGi());
	}

}
