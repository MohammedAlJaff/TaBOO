package taBOO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import taBOO.Encoder.EncoderException;
import taBOO.TabooTree.TabooTreeException;

public class FilterEngine {

	
	public static void filterEngine(Organism[] Aorgs, TabooTree t, int NcodeN) throws IOException, TabooTreeException, EncoderException {
		Encoder e = new Encoder(NcodeN);
		
		int i = 1;
		for (Organism o: Aorgs) {
			
			String filename = "PostFilter_OrgNr_"+i;
			i++;
			File f = new File(filename);
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println(o.getGi());
			String tempsi;
			for(PartialOrganism p: o.partialGenomes) {
				System.out.println(p.getGi() + ": + Strand");	
				int wordlength = (t.getDepth()*t.getNcodeN());
			
		        for(int pos=0; pos<p.getSeq().length()-wordlength+1; pos++) {
		        	tempsi = p.getSeq().substring(pos, pos+wordlength);
		        	if(t.contains( e.encode(tempsi) )) {
		        		System.out.println( "pos: "+pos+ " - " + tempsi);
		        	}
		        }
		        
		        System.out.println(p.getGi() + ": - Strand");	
		        for(int pos=0; pos<p.getRevSeq().length()-wordlength+1; pos++) {
		        	tempsi = p.getRevSeq().substring(pos, pos+wordlength);
		        	if(t.contains( e.encode(tempsi) )) {
		        		System.out.println( "pos: "+pos+ " - " + tempsi);
		        	}
		        }  
			}
		}
	}
}
