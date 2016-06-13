package taBOO;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class EncoderTest {

	@Test
	public void kmmStringMistachTest1()	{
		String s1 = "AATT";
		String s2 = "AATA";

		boolean b1 = Encoder.kmm(s1, s2, 0);
		boolean b2 = Encoder.kmm(s1,s2,1);
		boolean b3 = Encoder.kmm(s1,s2,2);
		
		boolean[] actual =  {b1, b2, b3};
		boolean[] expected = {false, true, true};
		
		assertArrayEquals(expected, actual);
	}

	@Test
	public void numbMismatchStringNumberOfMistachesTest1()	{
		String s1 = "AATT";
		String s2 = "AATA";
		String s3 = "ATTT";
		String s4 = "TTTT";
 
		int b0 = Encoder.numbMismatch(s1, s1);
		int b1 = Encoder.numbMismatch(s1, s2);
		int b2 = Encoder.numbMismatch(s1, s3);
		int b3 = Encoder.numbMismatch(s2, s3);
		int b4 = Encoder.numbMismatch(s1, s4);
		int b5 = Encoder.numbMismatch(s3, s4);
		
		int[] actual =  {b0, b1, b2, b3, b4, b5};
		int[] expected = {0, 1, 1, 2, 2, 1};
		
		assertArrayEquals(expected, actual);
	}

	@Test 
	public void encodeStringToArrayTest1() {
		Encoder e = new Encoder();
		
		String s1 = "AAAAATTTTT";
		String s2 = "AAAACAAAAG";
		String s3 = "TTTTTTTTTG";
		
		int[][] expRes = {
				{0, 1023}, 
				{1, 2},
				{1023, 1022}
		};
		
		boolean b1 = java.util.Arrays.equals(expRes[0], e.encode5(s1) ) ;
		boolean b2 = java.util.Arrays.equals(expRes[1], e.encode5(s2) ) ;
		boolean b3 = java.util.Arrays.equals(expRes[2], e.encode5(s3) ) ;
		
		boolean[] actual = {b1,b2,b3};
		boolean[] expected = {true, true, true};
		
		assertArrayEquals(expected, actual);
	}

}
