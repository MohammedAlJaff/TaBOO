package taBOO;

public class FastaChunk {

	
	
	
	
	private String head;
	private String body;
	
	public FastaChunk(String s) {
	
		String[] temp;
		try {
			temp = guillotine(s);

			this.head = temp[0]; 
			this.body = temp[1];
		} catch (FastaChunkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private static String[] guillotine(String s) throws FastaChunkException {
			
		String[] temp = s.split("\n"); 

		if (temp.length==2) {
			return temp;
		} else {
			Exception e = new FastaChunkException();
			throw (new FastaChunkException());
		}
	
	
	}
	
	
	
	static FastaChunk fastaChunckCreator(String string) {
		
		return new FastaChunk(string);
	}
	
	// 
	public String getHead() throws FastaChunkException {
		if (head != null) {
			return head;	
		} else {
			throw new RuntimeException();
		}
		
	}
	
	
	// maybe change body to sequence
	public String getBody() {
		return body;
	}
	
	
	
	
	
	public static class FastaChunkException extends Exception {
		
		public FastaChunkException() {
			super();
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	
		String s = ">blablabl\nAAABBBAAA";
		
		System.out.println("------------------------------");
		// Test. FastaChunck;
		
		FastaChunk x = new FastaChunk(s);
		
		
		System.out.println("Head: " + x.getHead());
		System.out.println("Body of squence: " + x.getBody());
		
		System.out.println("------------------------------");
		
		// Test. fastaChunkCreator;
		String r = ">rrr\nTGTGTGTGTGT\n";
		FastaChunk y = new FastaChunk(r);
				
		System.out.println("Head: " + y.getHead());
		System.out.println("Body of squence: " + y.getBody());
				
		System.out.println("------------------------------");
				
				
		// Test FastaChuNKeXCEPTION;
		
		String w = ">rrr\nTGTGTGTGTGT\nhbjbh";
		FastaChunk q = new FastaChunk(w);
				
		System.out.println("Head: " + q.getHead());
		System.out.println("Body of squence: " + q.getBody());
				
		System.out.println("------------------------------");
		
		
		
		
		
		
		
		
		
		
		
		
	}	

}
