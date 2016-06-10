package taBOO;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test //Reflexive equality 
	public void equalsTes1Reflexive() {
			Node n1 = new Node(3);
			assertEquals(n1,n1);	
	}
	
	@Test
	public void equalsTestSymetric(){
		Node n1 = new Node(3);
		Node n2 = new Node(3);
		boolean[] actual = new boolean[2];
		
		actual[0] = n1.equals(n2);
		actual[1] = n2.equals(n1);
		
		boolean[] expected = {true, true};
		assertArrayEquals(expected, actual);
	}
	

	@Test
	public void equalsTestInEquality(){
		Node n1 = new Node(3);
		Node n2 = new Node(33);
		boolean[] actual = {n1.equals(n2), n2.equals(n1)};
		boolean[] expected = {false, false};
		
		assertArrayEquals( expected, actual);
	}
	
	
	@Test 
	public void getIDtest() {
		Node n  = new Node(334);
		assertEquals(334, n.getID());
	}
	
	
	@Test 
	public void hasChildTest1() {
		Node n1 = new Node(12);
		assertTrue(n1.hasChildren());	
	}
	
	@Test 
	public void addChildTest1() {
		Node n1 = new Node(13);
		Node n2 = new Node(214);
		Node n3 = new Node(214);
		
		boolean b1 = n1.addChild(n2);
		boolean b2 = n1.addChild(n2);
		boolean b3 = n1.addChild(n3);
		boolean[] actuals = {b1, b2, b3};
		
		boolean[] expected = {Boolean.TRUE, Boolean.FALSE, Boolean.FALSE};
		assertArrayEquals(expected, actuals);
	}
	
	
	
	
	
}
