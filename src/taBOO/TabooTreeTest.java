package taBOO;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import taBOO.Encoder.EncoderException;
import taBOO.Node.NodeException;
import taBOO.TabooTree.TabooTreeException;

public class TabooTreeTest {
	
	public final static ExpectedException exxx = ExpectedException.none(); 

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
	}
	
		
	@Test
	public void expandNonRecTest1() throws TabooTreeException	{
		
		TabooTree t1 = new TabooTree(5, 5, 5);
		int[] w1 = {1, 2, 3, 4, 5};
		int[] w2 = {1, 2, 3, 4, 5};
		int[] w3 = {1, 2, 2, 2, 2};
		int[] w4 = {1, 2, 2, 2, 3};
		int[] w5 = {2, 2, 2, 2, 3};
		int[] w6 = {3, 2, 2, 2, 3};	
		
		t1.expandNonRec(w1);
		
		t1.printAllWords();
		
		
	}
	
	/**
	 * Uncertain about the need for the method.
	 * @throws TabooTreeException
	 * @throws NodeException
	 */
	@Test
	public void getAllWordTest() throws TabooTreeException, NodeException {
		TabooTree t1 = new TabooTree(5,5,5);

		int[][]	input1 = {
				{1, 2, 3, 4, 5},
				{1, 2, 3, 4, 5},
				{1, 2, 2, 2, 2},
				{1, 2, 2, 2, 3},
				{2, 2, 2, 2, 3},
				{3, 2, 2, 2, 3}
				};	
		
		for(int i=0; i<input1.length; i++) {
			t1.expand(input1[i]);
		}
		
		int[][] returnedWords = t1.getAllWords();
		
		
	
	}

	@Test 
	public void containsExactOnlyTest1() throws TabooTreeException, NodeException {
		TabooTree t1 = new TabooTree(5,5,5);
		
//		int[] input1 = {1, 2, 3, 4, 5};
//		int[] notInQuery = {1, 2, 3, 4, 4};
//		t1.expand(input1);
		
		
//		boolean b1 = t1.contains(input1);
//		boolean b2 = t1.contains(notInQuery);
//		
//		boolean[] actual = {b1, b2};
//		boolean[] expected = {true, false};
//
		int[][]	input1 = {
							{1, 2, 3, 4, 5},
							{1, 2, 3, 4, 5},
							{1, 2, 2, 2, 2},
							{1, 2, 2, 2, 3},
							{2, 2, 2, 2, 3},
							{3, 2, 2, 2, 3} };	
		
		for(int i=0; i<input1.length; i++) {
			t1.expand(input1[i]);
		}
		
		int[] q1 = {1,2,3,4,5};
		int[] q2 = {1,2,3,4,4};
		int[] q3 = {5,5,5,5,5};
		int[] q4 = {23,4,3,5,5};
		int[] q5 = {2,2,2,2,3};
		
		boolean b1 = t1.contains(q1);
		boolean b2 = t1.contains(q2);
		boolean b3 = t1.contains(q3);
		boolean b4 = t1.contains(q4);
		boolean b5 = t1.contains(q5);
		
		boolean[] actual = {b1,b2,b3,b4, b5};
		boolean[] expected = {true, false, false, false, true};
		
		assertArrayEquals(expected, actual);	
	}
	

	@Test(expected = TabooTreeException.class)
	public void containsExactOnlyTestException() throws TabooTreeException, NodeException {
		TabooTree t1 = new TabooTree(5,5,5);
		int[][]	input1 = {
				{1, 2, 3, 4, 5},
				{1, 2, 3, 4, 4},
				};	
		
		for(int i=0; i<input1.length; i++) {
			t1.expand(input1[i]);
		}
		
		int[] q1 = {1,2,3,4,5,6};
		t1.contains(q1);// Should generate the TabooTreeException.
	}
	
	/**
	 * // EXTREMLY FRAGILE METHOD! SHOULD HARDEN AND TEST AGAIN.
	 * @throws TabooTreeException
	 * @throws NodeException
	 * @throws EncoderException 
	 */
	@Test 
	public void growTreeTest1() throws TabooTreeException, NodeException, EncoderException{
		// Create empty tree
		TabooTree t = new TabooTree(5, 4, 2);
		Encoder encoder  = new Encoder();
		
		// Create sequence string that gets encoded
		String s = "ACGTACGTACGTACGTACGT"+
				"AAAAATTTTTTTTTTTTTTTTAAAAAAAAATTTTTTTTTTTAAAAAAAAATTTTTTTTTTTAAAAAAAAA"
				+ "TTTTTTTTTTTAAAAAAAAATTTTTTTTTTTAAAAAAAAATTTTTTTTTTTAAAAAAAAA"
				+ "TTTTTTTTTTTAAAAAAAAAATAAATACACGAGAGCAGAGACAGCAGAGCAAAATAAATACACGAGAGCAGAGACAGCAGAG"
				+ "CAAAATAAATACACGAGAGCAGAGACAGCAGAGCAAAATAAATACACGAGAGCAGAGACA";
		
		String[] seqsIn = new String[4];
		seqsIn[0] = "ACGTACGTAC";
		seqsIn[1] = "CGTACGTACG";
		seqsIn[2] = "GTACGTACGG";
		seqsIn[3] = "TACGTACGGG";
		
		// Feed string into growTree to grow the t1 tree.
		TabooTree.growTree(t, s, 10, 5, encoder);
		// use contains to see if sequences can be found.
		boolean b1 = t.contains(encoder.encode(seqsIn[0]));
		boolean b2 = t.contains(encoder.encode(seqsIn[1]));
		boolean b3 = t.contains(encoder.encode(seqsIn[2]));
		boolean b4 = t.contains(encoder.encode(seqsIn[3]));
		
		boolean[] actual = {b1, b2, b3, b4};
		boolean[] expected = {true, true, false, false};
		
		assertArrayEquals(expected, actual);
		
		// Assert.
		
	}
	
	
	
	
	
	
}//End of JUint test
