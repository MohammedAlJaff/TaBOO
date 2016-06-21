package taBOO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import taBOO.Encoder.EncoderException;
import taBOO.TabooTree.TabooTreeException;

public class FilterEngine {
	
	public static ArrayList<ArrayList<Integer>> getInitialList(Organism aorg, int seqWordLength){

		ArrayList<ArrayList<Integer>> initialSurvivorIndexList = 
				new ArrayList<ArrayList<Integer>>(aorg.getNumbPartials());
		
	
		for(int z=0; z<aorg.getNumbPartials(); z++) {
		 
			ArrayList<Integer> temp = new ArrayList<Integer>(
					aorg.getPartials().get(z).getSeqLength()+(1-seqWordLength) 
					);
			
			for(int i=0; i<aorg.getPartials().get(z).getSeqLength()+(1-seqWordLength) ; i++) {
				temp.add(i);
				//System.out.println(temp.get(i));
			}
			
			initialSurvivorIndexList.add(temp);
		}
		
		return initialSurvivorIndexList;
		
	}
	
	
	public static ArrayList<ArrayList<Integer>> filter(Organism aorg, Organism borg, int seqWordLength, int thresh, Encoder e, int NcodeN,
			ArrayList<ArrayList<Integer>> initialSurvivorIndexList) throws EncoderException {
		
		//System.out.print("Initializing strings....");
//		ArrayList<ArrayList<Integer>> initialSurvivorIndexList = 
//				new ArrayList<ArrayList<Integer>>(aorg.getNumbPartials());
		
		ArrayList<ArrayList<Integer>> survivorIndexList = 
				new ArrayList<ArrayList<Integer>>(aorg.getNumbPartials());
	//	System.out.println("Done.");
		
		
		// Initializing arrayList sizes. 
		//System.out.println("Creating initial survivor index....");
		
		for(int z=0; z<aorg.getNumbPartials(); z++) {
		 
			survivorIndexList.add(new ArrayList<Integer>(aorg.getPartials().get(z).getSeqLength()+(1-seqWordLength)));
		}
		
		//System.out.println("Creating initial survivor index....Done.");

		// Creating a Dictree for borg
		//System.out.print("Creating and populating tree with seq from: "+borg.getGi()+"...");
		Dictree bdt = Dictree.createDictree(borg, e, seqWordLength, NcodeN);
		System.out.println("Dictree complete.");
		
		ArrayList<HashMap<Integer, ArrayList<int[]>>> h = new ArrayList<HashMap<Integer, ArrayList<int[]>>>(thresh+1);
		
		for(int i=0; i<thresh+1; i++) {
			h.add(Encoder.getListIndicator(e, i));
		}
		
		
		int hitsFound = 0;
		for(int i=0; i<initialSurvivorIndexList.size(); i++) {
			
			System.out.println("\tPartial nr:" + i);
			
			for(int j=0; j<initialSurvivorIndexList.get(i).size(); j++) {
				
				int tempPos = initialSurvivorIndexList.get(i).get(j);
				if(j%100000==0) {
					System.out.println("\t\tSeq nr. " + j + " out of " + initialSurvivorIndexList.get(i).size() );
				}
				String tempSeq = aorg.getPartials().get(i).getSeq().substring(tempPos, tempPos+seqWordLength);
				String tempSeqRev = SequenceTools.reverseStrandCreator(tempSeq);
				//long t0 = System.currentTimeMillis();
				if(!bdt.containsWithin(e.encode(tempSeq), thresh, e, h) && !bdt.containsWithin(e.encode(tempSeqRev), thresh, e, h))  {
					survivorIndexList.get(i).add(tempPos);
					//System.out.println(tempSeq);
					hitsFound++;
				}
				//long t1 = System.currentTimeMillis();
				//System.out.println("Elapsed time: " + (t1-t0) + "msek.");
			}
		}
		
		//System.out.println(hitsFound);
		return survivorIndexList;
	}
	

	public static void resultsToFile(Organism o, ArrayList<ArrayList<Integer>> survivorIndexList, int seqWordLength, String outputDir) throws IOException {
		
		File f = new File(outputDir + "/PostFilter.txt");
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		
		if( (survivorIndexList.size() != o.getNumbPartials()) ) {
			throw new RuntimeException("Survvivor does not match");
		};
		
		for(int i = 0; i<survivorIndexList.size(); i++) {
			
			for(int j=0; j<survivorIndexList.get(i).size(); j++) {
				
				int pos = survivorIndexList.get(i).get(j);
				String outString = o.getPartials().get(i).getSeq().substring(pos, pos+seqWordLength);
				//bw.write(pos + ", " + outString+"\n");
				bw.write(outString+"\n");
				
			}
		}
		
		bw.close();
		
	
		
	}
	
	
	public static void main(String[] args) throws EncoderException, IOException {
		
		
		Organism a = new Organism("Mt.fna");
		
		Organism b1 = new Organism("Ml2.fna");
		Organism b2 = new Organism("Mb.fna");
		Organism b3 = new Organism("Ms.fna");
		Organism b4 = new Organism("Ma.fna");
		
		Encoder e = new Encoder(5);
		Organism[] borgs = {b1, b2, b3, b4}; 
		
		
		ArrayList<ArrayList<Integer>> x = getInitialList(a, 15);
		System.out.println(x.get(0).size());
		
		for(Organism b : borgs) {	
			System.out.println("Filtering against: " + b.getGi());
			x = filter(a, b, 15, 0, e, 5, x);
			System.out.println(x.get(0).size());
		}
		
		
		
		
		
	}
	
	

}
