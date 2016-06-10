package taBOO;

import java.util.HashSet;


public class Node {
	// Inner Class
	
			// Instance variables
			private int ID;
			private Node parent;
			private HashSet<Node> children;
			
			// Constructors;
			public Node() {
				this(-1);
			}
			
			public Node(int i) {
				this.ID = i;
				this.children = new HashSet<Node>();
			}

			// Methods
			public int getID() {
				return this.ID;
			}

			/**
			 * Adds an input Node as a child to the calling node.
			 * @param n
			 */
			public boolean addChild(Node n) {
				
				if( this.children.contains(n)){
					return false;
				} else {
					this.children.add(n);
					return true;
				}
			}
			
			
			/**
			 * equals method for Node class.
			 * DRAFT.1
			 * Two node are "equal" if they have the same ID;
			 */
			@Override public boolean equals(Object o) {
				
				if (!(o instanceof Node)) {
					return false;
				} else { 
				
					Node t = (Node) o;
					return this.getID()==t.getID();
				}
			}
			
			
			@Override public int hashCode()
			{
				return this.getID();	
			}
			
			
			/**
			 * Returns false if the node has no children.
			 * @return
			 */
			public boolean hasChildren(){
				return this.children.isEmpty();
			}
			
			/**
			 * Returns false if the node has no children.
			 * @return
			 */
			public boolean hasChild(Node n){
				return this.children.contains(n);
			}
			
			
			
			
			
			
			
			/**
			 * Returns the ID of node 
			 * as well the ID of its children 
			 * the string as well
			 */
			public String toString() {
		
				return String.valueOf(this.getID());
			}
				
				

	public static void main(String[] args) {

		
		
		
		
		
	}// End Main;

}// End Class
