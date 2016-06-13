package taBOO;

public class FastaChunk {

	private String head;
	private String body;
	
	public FastaChunk(String s) {
	
		String[] temp;
		temp = guillotine(s);

		this.head = temp[0]; 
		this.body = temp[1];
		
	}
	
	/**
	 * CHoops of the first Line of a string, the head, and returns it as
	 * the first element of the returned String array. 
	 * The second element is the rest of the input string. 
	 * @param s
	 * @return
	 */
	public static String[] guillotine(String s) {
			
		String[] temp = s.split("\n"); 
		
		String head = temp[0];
		String body = "";
		
		for (int i=1; i<temp.length;i++) {
			body = body+temp[i];
		}
		
		String[] whole = new String[2];
		whole[0] = head;
		whole[1] = body;
		
		return whole;
	
	}
	
	public static FastaChunk fastaChunckCreator(String string) {
		return new FastaChunk(string);
	}
	
	/**
	 * Returns the Head of the chunk as a string IN A SINGLE LINE WITHOUT A LINE BREAK.
	 * @return
	 */
	public String getHead()  {
		if (head != null) {
			return head;	
		} else {
			throw new RuntimeException();
		}
		
	}
	
	/**
	 * Returns the body of the chunk as a complete and single line.
	 * @return
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Returns a string where the first line contains the head of the 
	 * chunk and the next line is the body of the sequence as a single line.
	 */
	public String toString() {
	
			return this.getHead()+"\n"+this.getBody();
	}

	/**
	 * Custom classException
	 * @author RockefellerSuperstar
	 *
	 */
	public static class FastaChunkException extends Exception {
		
		public FastaChunkException(String msg) {
			super(msg);
		}
		
		
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	
		String s1 = ">bla1\n"+"AAA";
		String s2 = ">bla2\n"+"AAA\n";
		String s3 = s1+"\n"+s2;
		
		String[] a1 = FastaChunk.guillotine(s1);	
		String[] a2 = FastaChunk.guillotine(s2);
		String[] a3 = FastaChunk.guillotine(s3);
		
		System.out.println(a3[0]);
		System.out.println(a3[1]);

	}	
}
