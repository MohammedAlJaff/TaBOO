package taBOO;

public class FastaChunk {

	private String head;
	private String body;
	
	public FastaChunk(String s) {
	
		String[] temp = guillotine(s);
		
		this.head = temp[0]; 
		this.body = temp[1];
	}
	
	
	private String[] guillotine(String s) {
			
		return s.split("\n");	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	
		String s = ">blablabl\nAAABBBAAA";
		
		// Test guillotine
		
	}	

}
