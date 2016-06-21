package taBOO;

import java.util.ArrayList;

public class TablePartition {
	
	
	
	/**
	 * Returns the switchIndex for all values in a SORTED array of arrays.
	 * @param NcodeN
	 * @param t
	 * @return
	 */
	public static int[][] getSwitchMatrix(int NcodeN, int[][] t, int el){
	
		int[][] switchMatrix = new int[(int) Math.pow(5, NcodeN)][2];
		for(int i=0; i<switchMatrix.length; i++ ) {
			for(int j=0; j<2; j++) {
				switchMatrix[i][j] = -2;
			}
		}
		
		int currentInt; 
		int startindx; 
		int endindx; 
		int tindx=0;
		
		while (tindx<t.length) {
			currentInt = t[tindx][el];
			startindx = tindx;
			while(t[tindx][el]==currentInt) {
				tindx=tindx+1;
				if(tindx==t.length) { break;}
			}
			
			endindx=tindx;
			switchMatrix[currentInt][0] = startindx;
			switchMatrix[currentInt][1] = endindx;
		}
		
		return switchMatrix;
	}
	
	
	
	
	
	/**
	 * Returns the switchIndex for all values in a SORTED array of arrays.
	 * @param NcodeN
	 * @param t
	 * @return
	 */
	public static int[][] getSwitchMatrix(int NcodeN, ArrayList<int[]> t, int el){
	
		int[][] switchMatrix = new int[(int) Math.pow(5, NcodeN)][2];
		for(int i=0; i<switchMatrix.length; i++ ) {
			for(int j=0; j<2; j++) {
				switchMatrix[i][j] = -2;
			}
		}
		
		int currentInt; 
		int startindx; 
		int endindx; 
		int tindx=0;
		
		while (tindx<t.size()) {
			currentInt = t.get(tindx)[el];
			startindx = tindx;
			while(t.get(tindx)[el]==currentInt) {
				tindx=tindx+1;
				if(tindx==t.size()) { break;}
			}
			
			endindx=tindx;
			switchMatrix[currentInt][0] = startindx;
			switchMatrix[currentInt][1] = endindx;
		}
		
		return switchMatrix;
	}
	
	public static void main(String[] args) {
		
		int[][] t = {{0,1,1},{0,4,1}, {1,1,1}, 
					 {2,1,4},{2,1,7}, {4,1,1}, 
					 {4,4,1}, {5,6,1},{9,1,4}};
		
		int[][] sw = getSwitchMatrix(5, t, 0);
		
		for(int i=0; i<sw.length; i++) {
			for(int j=0; j<2; j++) {
				System.out.print(sw[i][j]+", ");
			}
			System.out.println();
		}
		
		
	}
	
	
}
