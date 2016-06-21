package taBOO;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import taBOO.Encoder.EncoderException;

public class SortingTest {

	

	public static int[] generateVector(int vectorLength, int maxElementValue ){
		Random r = new Random()	;
		
		int[] vector = new int[vectorLength];
		for(int i=0; i<vectorLength; i++)	{
			vector[i] = r.nextInt(1024);
		}
		return vector;
	}
	
	public static void main(String[] args) throws EncoderException, InterruptedException {
		
//		randomFastaFile f = new randomFastaFile(
//				"sortTest1.txt", 10000000, 0, 1, false, 80);
//		f.toFile();
		
		// Creation of an organism.
		Organism o = new Organism("hiv.fna");
		System.out.println("Created org");
		

		int wordLengthBases = 20;
		int L = o.getTotalGenomeLength();
		
		String[] stringTable = new String[L-wordLengthBases];
		
		System.out.println("----------------------");
		System.out.println("Creating table...");
		
		for(PartialOrganism p : o.getPartials()) {
			
			for(int pos = 0; pos<L-wordLengthBases; pos++) {
		
				stringTable[pos] = p.getSeq().substring(pos, pos+wordLengthBases);
			}			
		}	
		System.out.println("StringTable finished");
		System.out.println("-------------");
//		for (String s : stringTable) {
//			System.out.println(s);
//		}
	
		long t0 = System.currentTimeMillis();
		Arrays.sort(stringTable);
		long t1 = System.currentTimeMillis();
		System.out.println("Sorted in " + (t1-t0) + "ms");		
		
		// Creation and encoding.
		int NcodeN = 5;
		int sl = stringTable.length;
		int depth = wordLengthBases/NcodeN;
		Encoder e = new Encoder(NcodeN);
	
		//int[][]	codedWords = new int[sl][depth];
		ArrayList<int[]> cw = new ArrayList<int[]>(sl);
		
		System.out.println("-------------");
		long q0 = System.currentTimeMillis();
		for(int i=0; i<sl; i++) {
			//codedWords[i] = e.encode(stringTable[i]);
			cw.add(e.encode(stringTable[i]));
			stringTable[i] = null;
		}
		long q1 = System.currentTimeMillis();
	
		System.out.println("Converted in " + (q1-q0) + "ms");
		System.out.println(o.getGi() + " - "+ o.getTotalGenomeLength());

		stringTable = null;

		System.gc();
		Runtime.getRuntime().gc();
		Thread.sleep(3000);
		
//		//int[][] sm = TablePartition.getSwitchMatrix(NcodeN, codedWords, 0);
//		int[][] sm = TablePartition.getSwitchMatrix(NcodeN, cw, 0);
//		
//		for(int i = 0; i<sm.length; i++) {
//			for(int j = 0; j<2; j++) {
//				System.out.print(sm[i][j] + ", ");
//			}
//			System.out.println();
//		}
		
		System.out.println("------------------------------");
		
//		for(int i=sm[1][0]; i<sm[1][1]+9; i++) {
//			for (int j=0; j<depth; j++) {
//				System.out.print(cw.get(i)[j] + ", ");
//			}
//			System.out.println();
//		}
		
		System.out.println("Creating  and Populting a DicTree");
		Dictree dt = new Dictree(e);
		dt.populate(cw);
		
		System.out.print("Removing reference...");
		for(int[] a : cw) {
			a = null;
		}
		
		for(int i=0; i<cw.size(); i++) {
			cw.set(i, null);
		}
		System.out.println("Done");
		
		System.out.println("DicTree done");
		
		cw = null;
		
		dt.printWordsIn(1, 120);
		
		int[] query2 = {1, 120, 39, 9};
		int[] query3 = {1, 120, 354, 3046};
		
		System.out.println("-----------");
		System.out.println(dt.containsEaxaktly(query2));
		System.out.println(dt.containsEaxaktly(query3));
		
		System.gc();
		Runtime.getRuntime().gc();
		Thread.sleep(3000);
		
		
		
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		
		
		
//		for(int i=0; i<sl; i++) {
//			for(int j=0; j<depth; j++) {
//				System.out.print(codedWords[i][j]+"\t");
//			}
//			System.out.println("");
//		}
	}//End Main;
}// End Class
