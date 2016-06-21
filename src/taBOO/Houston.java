package taBOO;

import java.util.List;

import taBOO.Encoder.EncoderException;
import taBOO.Setup.SetupException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Houston {

	public static void main(String[] args) throws IOException, SetupException, EncoderException {
		int wordLength = Setup.checkWordLength(args[2]);
		int mismatchThresh = Setup.checkMismatchThresh(args[3]);
		String outputDir = args[4];
		
		// SystemSetup
		List<File> aorgFolders = Setup.getAllFolders(args[0]); 
		List<File> borgFolders = Setup.getAllFolders(args[1]); 
		
		List<File> aorgFiles = Setup.getFilesFromFolder(aorgFolders); 
		List<File> borgFiles = Setup.getFilesFromFolder(borgFolders); 
		
		ArrayList<Organism> aorgs = new ArrayList<Organism>(aorgFiles.size()); 
		ArrayList<Organism> borgs = new ArrayList<Organism>(borgFiles.size());
		
		
		for(File f : aorgFiles) {
			aorgs.add(new Organism(f.toString()));
		}
		
		for(File f : borgFiles) {
			borgs.add(new Organism(f.toString()));
		}
		
		
		int NcodeN = Setup.getNcodeN(wordLength);
		
		Encoder e = new Encoder(NcodeN);
		
		for(Organism a: aorgs) {
			ArrayList<ArrayList<Integer>> survivorIndexList = FilterEngine.getInitialList(a, wordLength);
			for(Organism b: borgs) {

				survivorIndexList = FilterEngine.filter(a, b, wordLength, mismatchThresh, e, NcodeN, survivorIndexList);
			}
			FilterEngine.resultsToFile(a, survivorIndexList, wordLength, outputDir);
		}
		
		
		
		
		
		
		// 
		

	}// end Main
}// end Class
