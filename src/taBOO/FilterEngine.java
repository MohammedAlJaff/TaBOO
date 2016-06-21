package taBOO;

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
		//System.out.println("Done.");
		
		ArrayList<HashMap<Integer, ArrayList<int[]>>> h = new ArrayList<HashMap<Integer, ArrayList<int[]>>>(thresh+1);
		
		for(int i=0; i<thresh+1; i++) {
			h.add(Encoder.getListIndicator(e, i));
		}
		
		
		int hitsFound = 0;
		for(int i=0; i<initialSurvivorIndexList.size(); i++) {
			
			for(int j=0; j<initialSurvivorIndexList.get(i).size(); j++) {
				
				int tempPos = initialSurvivorIndexList.get(i).get(j);
				//System.out.println(tempPos);
				String tempSeq = aorg.getPartials().get(i).getSeq().substring(tempPos, tempPos+seqWordLength);
				String tempSeqRev = SequenceTools.reverseStrandCreator(tempSeq);
				long t0 = System.currentTimeMillis();
				if(!bdt.containsWithin(e.encode(tempSeq), thresh, e, h) && !bdt.containsWithin(e.encode(tempSeqRev), thresh, e, h))  {
					survivorIndexList.get(i).add(tempPos);
					//System.out.println(tempSeq);
					hitsFound++;
				}
				long t1 = System.currentTimeMillis();
				System.out.println("Elapsed time: " + (t1-t0) + "msek.");
			}
		}
		
		//System.out.println(hitsFound);
		return survivorIndexList;
	}
	

	
	
	
	public static void main(String[] args) throws EncoderException {
		
		
		Organism a = new Organism("orgA.txt");
		
		Organism b1 = new Organism("orgB.txt");
		Organism b2 = new Organism("orgB2.txt");
		
		Encoder e = new Encoder(5);
		Organism[] borgs = {b1, b2}; 
		
		
		ArrayList<ArrayList<Integer>> x = getInitialList(a, 10);
		System.out.println(x.get(0).size());
		
		for(Organism b : borgs) {	
			x = filter(a, b, 10, 3, e, 5, x);
			System.out.println(x.get(0).size());
		}
		

		
		
		
	}
	
	

}
