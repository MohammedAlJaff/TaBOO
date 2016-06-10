package taBOO;

import java.io.*;
import java.util.*;

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
	
	
	//Constructors ////////////////////////////////////////////////////////
	/*
	 * Not Done
	 */
	private Organism(String url) {
		
		try {
			
			File f = new File(url);
			
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
	static public List<FastaChunk> fastaSpliter(File fileURL) throws IOException{
		
		List<String> temp = new ArrayList<String>();
		List<FastaChunk> tempFC = new ArrayList<FastaChunk>();
		List<FastaChunk> returnTempFC = new ArrayList<FastaChunk>();
		
		
		FileReader fr = new FileReader(fileURL);
		BufferedReader br=  new BufferedReader(fr);
		
		String s = null;
		String tempChunk = ">REMOVE\nREMOVE"; 
		while ((s = br.readLine()) != null) {
			
			if(s.charAt(0) == '>') {
				
//				System.out.print(tempChunk);
				tempFC.add(new FastaChunk(tempChunk));
				
				tempChunk = s+"\n";
			} else {
				
				tempChunk = tempChunk+s+"\n";
			}
		}
		
//			System.out.print(tempChunk);
			tempFC.add(new FastaChunk(tempChunk));
			tempChunk = s;

	
		
		
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
	 */
	private void partialOrganismListCreator(List<FastaChunk> fastaChunks){
			
		List<PartialOrganism> temp = new ArrayList<PartialOrganism>();
		
		this.partialGenomes = temp;
		
	}


	
	
	

	public static void main(String [] args) throws IOException{
		// TODO Auto-generated method stub
		
		// FastaSpliter test
		
		
		File file = new File("text1.txt");
		FileReader fw = new FileReader(file);
		BufferedReader bw = new BufferedReader(fw);
		
		
		List<FastaChunk> x = new ArrayList<FastaChunk>();
		
		
		x = fastaSpliter(file);
		
		for(FastaChunk e: x) {
			
			System.out.println(e);
			//System.out.println(e.getHead());
			//System.out.println(e.getBody());
						
		}
		
		
		
		
	}

}
