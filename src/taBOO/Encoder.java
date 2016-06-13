package taBOO;

import java.util.HashMap;

public class Encoder {
	
	
	public  HashMap<String, Integer> conversionMap;// = new HashMap<String, Integer>();
	public  int[][] mmlu;
	
	
	public Encoder() {
		// Setup for one-fifth coding.
		String[] base = {"A","C","G","T"}; 
		String[] pos5words = new String[(int) Math.pow(4, 5)];
		
		int indx =0;
		for (int x=0;x<4;x++) {
			for (int y=0;y<4;y++) {
				for (int z=0;z<4;z++) {
					for (int w=0;w<4;w++) {
						for (int u=0;u<4;u++) {
							String tempWord = base[x]+base[y]+base[z]+base[w]+base[u];
							pos5words[indx]=tempWord;
							indx++;
						}	
					}	
				}	
			}	
		}
		//System.out.println("___Done: Creation of all 5 base words.____");
		// Create lookup table.
		this.mmlu= new int[(int) Math.pow(4, 5)][(int) Math.pow(4, 5)];
		
		for (int i=0;i<1024;i++) {
			for (int j=0;j<1024;j++) {
				this.mmlu[i][j] = numbMismatch(pos5words[i], pos5words[j]); 
			}	
		}
		
		//System.out.println("___Done: MM look-up table for all 5 base words.____");
		// Create conversion hashmap;		
		this.conversionMap = new HashMap<String,Integer>();
		
		for (int i=0; i<pos5words.length;i++) {
			this.conversionMap.put(pos5words[i], i);
		}
	}

	/**
	 * Method converts the input string into N-code where N=5.
	 * The return sequences is given as an array.
	 * @param s
	 * @return
	 */
	public int[] encode5(String s) {
		int n = (s.length())/5;
		int[] out = new int[n];
		
		for(int i=0; i<n;i++) {
			String temp = s.substring(i*5, (i+1)*5);	
			out[i] = this.conversionMap.get(temp);
		}
		return out;	
	}
	 
	/**
	 * This method compares two sequences and returns
	 * back the number of mismatches between them.
	 * CASE SENSITIVE!
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int numbMismatch (String s1, String s2){		
			// Initialize number of mistaches and set to zero.
			int n = 0;	
			// compare input strings and allow only stings of equal lenngths
			if (s1.length() == s2.length()) {
				/*  Go through each position of both strings and check for equality.
				 *  If characters are not equal, increment n, else move to next position. 
				 */
				for (int i=0; i<s1.length(); i++){	
					if (s1.charAt(i) != s2.charAt(i)){
						n++;
					}
				}
				return n;
			} else {
				throw new RuntimeException();
			}
	}
		
	/**
	 * Returns false if the number of mismatches between the two input 
	 * strings is greater than the input parameter k.
	 * @param s1
	 * @param s2
	 * @param k
	 * @return
	 */
	public static boolean kmm (String s1, String s2, int k){	
			// Initialize number of mistaches and set to zero.
			int n = 0;	
			boolean answer = true;
			// compare input strings and allow only stings of equal lenngths
				/*  Go through each position of both strings and check for equality.
				 *  If characters are not equal, increment n, else move to next position. 
				 */
				for (int i=0; i<s1.length(); i++){	
					
					if (s1.charAt(i) != s2.charAt(i)){
						n++;
						
						if(n>k) {
							answer = false;
							break;
						}
					}
				}
				return answer;
		}
		
	/**	
	 * NOT TESTED OR DOCED
	 * @param s1
	 * @param s2
	 * @param k
	 * @param mmluN
	 * @return
	 */
	public static boolean kmm (int[] s1, int[] s2, int k, int[][] mmluN){	
			// Initialize number of mismatches and set to zero.
			int n = 0;	
			//boolean answer = true;
			// compare input strings and allow only stings of equal lengths
				/*  Go through each position of both strings and check for equality.
				 *  If characters are not equal, increment n, else move to next position. 
				 */
				for (int i=0; i<s1.length; i++){		
						n= n + mmluN[ s1[i] ][s2[i] ];
						if(n>k) {
							return false;
						}
				}		
				return true;
	}
		
	/**
	 * NOT TESTED OR DOCED
	 * @param s1
	 * @param s2
	 * @param mmluN
	 * @return
	 */
	public static int numbMisMatch (int[] s1, int[] s2, int[][] mmluN){	
			// Initialize number of mismatches and set to zero.
			int n = 0;	
			// compare input strings and allow only stings of equal lengths
			
				/*  Go through each position of both strings and check for equality.
				 *  If characters are not equal, increment n, else move to next position. 
				 */
				for (int i=0; i<s1.length; i++){		
						n= n + mmluN[ s1[i] ][s2[i] ];
				}		
				return n;
	}
	
	public static void main(String [] args) {
	}// end Main

	
}// end class
