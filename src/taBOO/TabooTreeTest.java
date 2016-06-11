package taBOO;

import static org.junit.Assert.*;

import org.junit.Test;

public class TabooTreeTest {

	@Test
	public void basicGetterTest() {
		
		TabooTree t1 = new TabooTree(5, 5, 30);
		
		int i1 = t1.getNcodeN();
		int i3 = t1.getDepth();
		int i2 = t1.getAlphabetSize();
		
		int[] actual = {i1, i2, i3};
		int[] exp = {5, 5, 30};
		assertArrayEquals(exp, actual);	
	}
	
	
	
	
}//End of JUint test
