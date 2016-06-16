package taBOO;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
		
		
		System.out.println(Runtime.getRuntime().freeMemory());	
		//
		int wordLengthBases = 100;
		int L = o.getTotalGenomeLength();
		
		String[] stringTable = new String[L-wordLengthBases];
		
		System.out.println("----------------------");
		System.out.println("Creating table...");
		
		for(PartialOrganism p : o.getPartials()) {
			
			for(int pos = 0; pos<L-wordLengthBases; pos++) {
		
				stringTable[pos] = p.getSeq().substring(pos, pos+wordLengthBases);
			}			
		}
		System.out.println(Runtime.getRuntime().freeMemory());	
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
		
		
		int NcodeN =5;
		int sl = stringTable.length;
		int depth = wordLengthBases/NcodeN;
		Encoder e = new Encoder(NcodeN);
	
		int[][]	codedWords = new int[sl][depth];
		
		System.out.println("-------------");
		long q0 = System.currentTimeMillis();
		for(int i=0; i<sl; i++) {
			codedWords[i] = e.encode(stringTable[i]);
			stringTable[i] = null;
		}
		long q1 = System.currentTimeMillis();
		System.out.println(Runtime.getRuntime().freeMemory());	
		System.out.println("Converted in " + (q1-q0) + "ms");
		System.out.println(o.getGi() + " - "+ o.getTotalGenomeLength());
		
		
		stringTable = null;
		o = null;
		System.out.println(Runtime.getRuntime().freeMemory());	
		System.gc();
		Runtime.getRuntime().gc();
		Thread.sleep(60000);
		System.out.println(Runtime.getRuntime().freeMemory());
		
		
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		o = new Organism("siv.fna");
		System.out.println("Created org");
		
		
		System.out.println(Runtime.getRuntime().freeMemory());	
		//
		wordLengthBases = 100;
		L = o.getTotalGenomeLength();
		
		stringTable = new String[L-wordLengthBases];
		
		System.out.println("----------------------");
		System.out.println("Creating table...");
		
		for(PartialOrganism p : o.getPartials()) {
			
			for(int pos = 0; pos<L-wordLengthBases; pos++) {
		
				stringTable[pos] = p.getSeq().substring(pos, pos+wordLengthBases);
			}			
		}
		System.out.println(Runtime.getRuntime().freeMemory());	
		System.out.println("StringTable finished");
		System.out.println("-------------");
//		for (String s : stringTable) {
//			System.out.println(s);
//		}
	
		
		t0 = System.currentTimeMillis();
		Arrays.sort(stringTable);
		t1 = System.currentTimeMillis();
		System.out.println("Sorted in " + (t1-t0) + "ms");
		
		
		// Creation and encoding.

		int[][]	codedWords1 = new int[sl][depth];
		
		System.out.println("-------------");
		q0 = System.currentTimeMillis();
		for(int i=0; i<sl; i++) {
			codedWords1[i] = e.encode(stringTable[i]);
			stringTable[i] = null;
		}
		q1 = System.currentTimeMillis();
		System.out.println(Runtime.getRuntime().freeMemory());	
		System.out.println("Converted in " + (q1-q0) + "ms");
		System.out.println(o.getGi() + " - "+ o.getTotalGenomeLength());
		
		stringTable = null;
	
		System.out.println(Runtime.getRuntime().freeMemory());	
		System.gc();
		Runtime.getRuntime().gc();
		Thread.sleep(60000);
		System.out.println(Runtime.getRuntime().freeMemory());
		
		
		
//		for(int i=0; i<sl; i++) {
//			for(int j=0; j<depth; j++) {
//				System.out.print(codedWords[i][j]+"\t");
//			}
//			System.out.println("");
//		}
	}//End Main;
}// End Class
