package taBOO;

import java.io.*;
import java.util.*;

import taBOO.FastaChunk.FastaChunkException;

public class Organism {
	
	/**
	 * Organism identifier. Taken from the FASTA file name.
	 * @param args
	 */
	private String gi;
	/**
	 * 
	 */
	List<PartialOrganism> partialGenomes;
	
	//Constructors ::::::::::::::::::::::::::::::::::::::::::::::::::::
/**
 * 
 * @param url
 */
	public Organism(String url) {
		
		try {
			
			File f = new File(url);
			this.gi = url;
			partialOrganismListCreator(fastaSpliter(f));
		
		} catch(Exception exp){
			
			exp.printStackTrace();
		}	
	}
	
	// Methods:::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	/**
	 * This method takes in a fasta file url and  segments it into it's constituent header+body parts
	 * By this we mean that the reutrn is a list of Strings where each string is a single header + sequence body.
	 * @param f
	 * @return
	 * @throws IOException 
	 */
	private static List<FastaChunk> fastaSpliter(File fileURL) throws IOException{
		
		List<String> temp = new ArrayList<String>();
		List<FastaChunk> tempFC = new ArrayList<FastaChunk>();
		List<FastaChunk> returnTempFC = new ArrayList<FastaChunk>();
		
		FileReader fr = new FileReader(fileURL);
		BufferedReader br=  new BufferedReader(fr);
		
		StringBuilder tc = new StringBuilder(10000000);
		
		String s = null;
//		String tempChunk = ">REMOVE\nREMOVE"; 
		tc.append(">REMOVE\nREMOVE");
		int counter =0;
		while ((s = br.readLine()) != null) {
//			
//			counter++;
//			System.out.println("Organism loop: " + counter);
			if(s.charAt(0) == '>') {
				
//				System.out.print(tempChunk);
//				tempFC.add(new FastaChunk(tempChunk));
				tempFC.add(new FastaChunk(tc.toString()));
						
//				tempChunk = s+"\n";
				tc.setLength(0);
				tc.append(s+"\n");
				
			} else {
				
//				tempChunk = tempChunk+s+"\n";
				tc.append(s+"\n");
			}
		}
		
//			System.out.print(tempChunk);
//			tempFC.add(new FastaChunk(tempChunk));
			tempFC.add(new FastaChunk(tc.toString()));
//			tempChunk = s;

		// Removal of first FastaChunk. 
		// Note we begin at 1 because the first one gets removed	
		for (int i=1; i<tempFC.size();i++) {
			returnTempFC.add(tempFC.get(i));
		}
	
		return returnTempFC;		
	}
	
	/**
	 *This method takes in a list of
	 * @param fastaChunks
	 * @throws FastaChunkException 
	 */
	private void partialOrganismListCreator(List<FastaChunk> fastaChunks) throws FastaChunkException{
			
		List<PartialOrganism> temp = new ArrayList<PartialOrganism>(fastaChunks.size());
		
		for (int i =0; i<fastaChunks.size(); i++) {
			temp.add(new PartialOrganism(fastaChunks.get(i)));
		}
		
		this.partialGenomes = temp;
		
	}

	/**
	 * Returns the total number of bases in the whole
	 * genome of the calling organism. This is equal to
	 * the sum of all lengths of all its partialGenomes
	 * @return
	 */
	public int getTotalGenomeLength() {
		int sum=0;	
		
		for (PartialOrganism p: this.partialGenomes) {
			sum = sum + p.getSeqLength();
		}
		return sum;
	}
	
	public List<PartialOrganism> getPartials(){
		
		List<PartialOrganism> temp = new ArrayList<PartialOrganism>(this.partialGenomes.size());
		
		for(PartialOrganism p: this.partialGenomes) {
			temp.add(PartialOrganism.PartialOrganismFactory(p));
		}
		return temp;
	}
	
	public String getGi() {
		return gi;
	}

	// MAIN :::::::::::::::::::::::::::::::::::::::::::::::::::::
	public static void main(String [] args) throws IOException{
		// TODO Auto-generated method stub
		
		// FastaSpliter test
		
		// Test Organism creator.
		
		Organism x = new Organism("text1.txt");
		
		System.out.println("Printing all partials");
		System.out.println("-------------------------------");
		List<PartialOrganism> p = x.getPartials();
		
		for( PartialOrganism a: p){
			System.out.println(a+"\n");
			
		}
		System.out.println(x.getGi());
		
		
		
		
	}// end main

}//end class
