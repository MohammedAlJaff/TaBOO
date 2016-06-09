package taBOO;

import java.io.*;
import java.util.*;

public class Organism {
	
	
	
	/**
	 * Organism identifier. Taken from the FASATA file name.
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
	 */
	
	static List<String> fastaSpliter(File fileUrl){
		
		
		List<String> temp = new ArrayList<String>();
	
		return temp;
			
	}
	
	
	
	/**
	 *  This method takes in a list of
	 * @param fastaChunks
	 */
	static void partialOrganismListCreator(List<String> fastaChunks){
			
		List<PartialOrganism> temp = new ArrayList<PartialOrganism>();

	}

	
	
	

	
	
	
	
	
	
	

	public static void main() {
		// TODO Auto-generated method stub
//
	}

}
