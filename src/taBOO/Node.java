package taBOO;

import java.util.HashMap;


public class Node {
	// Inner Class
	
		// Instance variables
		private int ID;
		private Node parent = null;
		private HashMap<Integer, Node> children;
		
		// Constructors;
		public Node() {
			this(-1);
		}
		
		public Node(int i) {
			this.ID = i;
			this.children = new HashMap<Integer, Node>();
		}

		// Methods
		/**
		 * Returns the ID for this node, i.e. the genome base word.
		 * @return
		 */
		public int getID() {
			return this.ID;
		}
		
		/**
		 * return true if Node has no children Nodes in its HashMap:
		 * @return
		 */
		public boolean hasChildren() {
			return !this.children.isEmpty();
		}
		
		/**
		 * Method returns true if the calling node has a child node with ID as the parameter.
		 * @param integer
		 * @return
		 */
		public boolean hasChild(int integer) {
			return this.children.containsKey(integer);
		}
			
		
		/**
		 * This method returns true if the calling node has a child like the input node, 
		 * otherwise it returns false. 
		 * @param n
		 * @return
		 */
		public boolean hasChild(Node n) {
			
			return this.hasChild(n.getID());
		}
			
			
			
			
		/**
		 * WARNING - MIGHT CHNAGE IN THE FUTURE SO AS TO THROW AN EXCEPTION INSTEAD.
		 * Adds a child node with id the same as the input parameter if such a child node does 
		 * not already exist. The method also returns true if addition was successful, otherwise
		 * it returns false.
		 * @param i
		 * @return
		 * @throws NodeException
		 */
		public boolean addChild(int i) throws NodeException{
			if (this.hasChild(i)) {
				return false;
//				throw new NodeException();
			} else {
				Node n1 = new Node(i);
				n1.parent = this;
				this.children.put(i, n1);
				return true;
			}
		}
		
		

		
		
		
		/**
		 * Adds the input node as a child to the calling node. If successful, 
		 * the method returns true while if there already is a child with the
		 * same ID as the input node, then the methods returns false. 
		 * @param n
		 * @return
		 * @throws NodeException
		 */
		
		public boolean addChild(Node n) {
			if (this.hasChild(n.getID())) {
				return false;
//				throw new NodeException();
			} else {;
				n.parent = this;
				this.children.put(n.getID(), n);
				return true;
			}
		}
		
		
		/**
		 * Returns the parent of the 
		 * @return
		 */
		public Node getParent() {
			return this.parent;
		}
		
		/**
		 * Returns the number of children the calling node has. 
		 * @return
		 */
		public int numberOfChildren() {
			return this.children.size();
		}
		
		
		/**
		 * Method returns the child of the calling node with ID equals to
		 * the input parameter if the calling node has such a child. 
		 * If not, the method throws a null. 
		 * @param i
		 * @return
		 */
		public Node getChild(int i) {
			if(this.hasChild(i)) {
				return this.children.get(i);
			} else {
				return null;
			}
		}
		
		/**
		 * Returns the ID of node 
		 * as well the ID of its children  		
		 * the string as well
		*/
		public String toString() {	
			return String.valueOf(this.getID());
		}
				
		// Class exceptions.
		public static class NodeException extends Exception{
			public NodeException() {
				super();
			}
		}
						
	public static void main(String[] args) {

		
		
		
		
		
	}// End Main;

}// End Class
