package taBOO;

import static org.junit.Assert.*;

import org.junit.Test;

import taBOO.Node.NodeException;

public class NodeTest {
	
	@Test 
	public void getIDtest() {
		Node n  = new Node(334);
		assertEquals(334, n.getID());
	}
	
	@Test
	public void hasChildrenTestt1() {
		Node n1 = new Node(1);
		
		boolean b1 = n1.hasChildren();
		boolean exp = false;
		
		assertEquals(exp, b1);
	}
	
	@Test 
	public void hasChildTest1Integer() {
		
		Node n1 = new Node();
		assertFalse(n1.hasChild(4));
	}
	
	
	@Test 
	public void addChildTest1Integer() throws NodeException {
		
		Node n1 = new Node(1);
		
		boolean b1 = n1.hasChildren();
		boolean b2 = n1.hasChild(3);

		boolean b3 = n1.addChild(3);		
		boolean b4 = n1.hasChild(3);
		boolean b5 = n1.addChild(3);
		
		boolean[] actual = {b1, b2, b3, b4, b5};
		
		boolean[] exp = {false, false, true, true, false};
		
		assertArrayEquals(exp, actual);
	}
	
	
	
	@Test
	public void hasChildTest2Node() throws NodeException {
		
		Node n1 = new Node(1);
		n1.addChild(4);
		
		Node n2 = new Node(414);
		Node n3 = new Node(4);
		
		boolean b1 = n1.hasChild(n2);
		boolean b2 = n1.hasChild(n3);
		
		boolean[] actuals = {b1, b2};
		
		boolean[] exp = {false, true};
		assertArrayEquals(exp, actuals);		
	}
	
	
	@Test 
	public void addChildNodeANDgetParentTest() throws NodeException {
		
		Node n1 = new Node(1);
		Node n2 = new Node(23);
	
		Node x1 = n2.getParent();
		boolean b1 = (x1==null);
		
		boolean b2 = n1.addChild(n2);
		
		
		Node x2 =  n2.getParent();
		
		boolean b3 = (x2!=null)&&(x2 instanceof Node);
		boolean b4 = (x2.getID()==n1.getID());
		
		
		boolean b5 = n1.addChild(n2);
		boolean b6 = n1.addChild(n2.getID());
		
		boolean[] actual = {b1, b2, b3, b4, b5, b6};
		boolean[] exp = {true, true, true, true, false, false};
		
		assertArrayEquals(exp, actual);
	}

		
	@Test
	public void numberOfChildrenTest() throws NodeException {
		Node n1 = new Node(13);
		Node n2 = new Node(14);
		Node n3 = new Node(14);
		
		int i1 = n1.numberOfChildren();
		
		n1.addChild(n2);
		int i2 = n1.numberOfChildren();
		
		n1.addChild(n3);
		int i3 = n1.numberOfChildren();
		
		n1.addChild(14);
		
		int i4 = n1.numberOfChildren();
		
		int[] actual = {i1, i2, i3, i4};
		int[] expected = {0, 1, 1, 1};
		
		assertArrayEquals(expected, actual);	
	}
	
	
	@Test 
	public void getChildTestInteger() throws NodeException{
		
		Node n1 = new Node();
		Node n3 = new Node(3);
		
		n1.addChild(2);
		n1.addChild(n3);
		
		Node x1 = n1.getChild(2);
		Node x2 = n1.getChild(3);
		Node x3 = n1.getChild(5);
		
		boolean b1 = (x1!=null)&&(x1 instanceof Node)&&(x1.getID()==2);
		boolean b2 = (x2!=null)&&(x2 instanceof Node)&&(x2.getID()==3);
		boolean b3 = (x3==null);
		
		boolean[] actual = {b1, b2, b3};
		boolean[] EXPECTED = {true, true, true};

		assertArrayEquals(EXPECTED, actual);
		
	}
	
	
	
	
	
}
