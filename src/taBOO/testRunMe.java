package taBOO;

import java.io.IOException;

import taBOO.Encoder.EncoderException;
import taBOO.FastaChunk.FastaChunkException;
import taBOO.Node.NodeException;
import taBOO.TabooTree.TabooTreeException;

public class testRunMe {
	
	public static void main(String[] args) throws FastaChunkException, TabooTreeException, NodeException, EncoderException, IOException {
		

		System.out.println("BeginReading");
		long t0 = System.currentTimeMillis();
		Organism hiv = new Organism("hiv.fna");
		Organism siv = new Organism("ecoli.fna");
		
		long t1 = System.currentTimeMillis();
		long elapsed = t1-t0;
		System.out.println("Organism creation: Elapsed time: " + elapsed +" msek");
		System.out.println(hiv.getGi());
		System.out.println(siv.getGi());
		
		
		// Creation and growth of a tree.
		Organism[] Borgs = new Organism[1];
		Borgs[0] = siv;
		Organism[] Aorgs = new Organism[1];
		Aorgs[0] = hiv;
		
		int NcodeN = 5;
		int wordLength = 20;
		int depth = wordLength/NcodeN;
		
		TabooTree t = new TabooTree(NcodeN, 5, depth);
		Encoder e = new Encoder(NcodeN);
		System.out.println("-----------------------");
		
		System.out.println("Building TabooTree");
		long x0 = System.currentTimeMillis();
		TabooTree.turnTaboo(t, Borgs, wordLength, NcodeN, e);
		long x1 = System.currentTimeMillis();
		long elapsedTimeTree = x1-x0;
		System.out.println("TabooTree Finished - Growth time: " + elapsedTimeTree+ "msek");
		System.out.println("----------------------");
		
		System.out.println("Filtering..." );
		FilterEngine.filterEngine(Aorgs, t, NcodeN);

	}

}
