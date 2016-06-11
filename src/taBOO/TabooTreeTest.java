package taBOO;

import static org.junit.Assert.*;

import org.junit.Test;

import taBOO.Node.NodeException;
import taBOO.TabooTree.TabooTreeException;

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
	
	@Test 
	public void expandAndGetAllTabooWordsTest() throws TabooTreeException, NodeException {
		TabooTree t1 = new TabooTree(5, 5, 5);
		
		int[] w1 = {1, 2, 3, 4, 5};
		int[] w2 = {1, 2, 3, 4, 5};
		int[] w3 = {1, 2, 2, 2, 2};
		int[] w4 = {1, 2, 2, 2, 3};
		int[] w5 = {2, 2, 2, 2, 3};
		int[] w6 = {3, 2, 2, 2, 3};
		
		t1.expand(w1);
		
		t1.expand(w2);
		
		t1.expand(w3);
		
		t1.expand(w4);
		
		t1.expand(w5);
		
		
	}
	
	
	
}//End of JUint test
