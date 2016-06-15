package taBOO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import taBOO.Setup.SetupException;

public class Setup {

	/**
	 * might be useful
	 */
	private static String OS = null;

	/**
	 * might be useful
	 */
	private static String FileSeparator = null;

	/**
	 * String array with all accepted file extensions
	 */
	private static String[] acceptedFileTypes = {"txt", "fna", "fasta"};

	/**
	 * might be useful
	 * @return
	 */
	public static String getOsName() {
		if (OS == null) {
			OS = System.getProperty("os.name");
		}
		return OS;
	}

	/**
	 * might be useful
	 * @return
	 */
	public static String getFileSeparator() {
		if(FileSeparator == null) {
			FileSeparator = File.separator;
		}
		return FileSeparator;
	}

	/**
	 * might be useful
	 * @param url
	 * @return
	 */
	public static boolean fileExists(String url) {
		File f = new File(url);
		if(f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the file extension is recognized
	 * @param url file to be checked
	 * @return true if file extension is recognized, SetupException otherwise
	 * @throws IOException
	 * @throws SetupException
	 */
	private static boolean checkFiletype(String url) throws IOException, SetupException {
		String[] parsedLast = url.split("\\.");
		String fileExtension = parsedLast[parsedLast.length-1];

		for(String ex : acceptedFileTypes) {
			if (ex.equals(fileExtension)) {				
				return true;
			}
		}

		throw new SetupException(url + " file type was not recognized");
	}

	/**
	 * Checks whether a certain folder with filepath "url" exists.
	 * @param url file path to folder
	 * @return true if folder exists, SetupException otherwise
	 * @throws SetupException
	 */
	public static boolean checkDirectory(String url) throws SetupException {
		File folder = new File(url);
		if(!folder.isDirectory()) {
			throw new SetupException(url + " is not a valid folder!");
		}
		return true;
	}

	/**
	 * Returns the absolute filepath to the folder corresponding to the input url as well as all subfolders
	 * of that folder.
	 * @param url filepath to folder
	 * @return a list with absoulte paths to input folder and all it's subfolders.
	 */
	public static List<File> getAllFolders(String url) {
		List<File> allFolders = new ArrayList<File>();
		allFolders.add(new File(url).getAbsoluteFile());
		allFolders.addAll(getAllFoldersHelp(url));

		return allFolders;
	}

	/**
	 * Help class to {@link Setup#getAllFolders(String)}
	 * @param url filepath to folder
	 * @return
	 */
	private static List<File> getAllFoldersHelp(String url) {
		List<File> allFolders = new ArrayList<File>();
		File[] listOfFolders = getOnlyFolders(url);

		allFolders.addAll(Arrays.asList(listOfFolders));
		for(File folders : listOfFolders) {
			if(folders.isDirectory()) {
				allFolders.addAll(getAllFoldersHelp(folders.getAbsolutePath()));
			}
		}

		return allFolders;
	}

	/**
	 * Help class to {@link Setup#getAllFoldersHelp(String)}. Removes all files from a File[] array
	 * so that only folders remains in the array.
	 * @param url filepath to folder
	 * @return File[] array containing only filepaths to folders
	 */
	private static File[] getOnlyFolders(String url) {
		File[] all = new File(url).listFiles();
		String[] onlyFolders = new String[all.length];
		int i = 0;

		for(File folders : all) {
			if(folders.isDirectory()) {
				onlyFolders[i] = folders.toString();
				i++;
			}
		}

		File[] returnOnlyFolders = new File[i];
		for(int j = 0; j < i; j++) {
			returnOnlyFolders[j] = new File(onlyFolders[j]);
		}

		all = null;
		onlyFolders = null;

		return returnOnlyFolders;
	}

	/**
	 * Gets all absolute filepaths to files from a List of folders.
	 * @param folders List with folders to retrieve filepaths from
	 * @return List with filepaths
	 * @throws IOException 
	 * @throws SetupException 
	 */
	public static List<File> getFilesFromFolder(List<File> folders) throws IOException, SetupException {
		List<File> files = new ArrayList<File>();

		for(File folder : folders) {
			File[] allFiles = new File(folder.toString()).listFiles();

			for(File file : allFiles) {
				if(file.isFile()) {
					Setup.checkFiletype(file.getAbsolutePath());
					files.add(file.getAbsoluteFile());
				}
			}
		}
		return files;
	}

	/**
	 * Gets all absoulte filepaths to files from a folder
	 * @param url filepath to folder
	 * @return List with filepaths
	 * @throws SetupException 
	 * @throws IOException 
	 */
	public static List<File> getFilesFromFolder(String url) throws IOException, SetupException {
		File[] listOfFiles = new File(url).listFiles();
		List<File> filePaths = new ArrayList<File>();

		for(File file : listOfFiles) {
			if (file.isFile()) {
				Setup.checkFiletype(file.getAbsolutePath());
				filePaths.add(file.getAbsoluteFile());
			}
		}
		return filePaths;
	}

	/**
	 * Checks if the first character of a file is a ">"
	 * @param url Filepath to file
	 * @return True if first character of file is ">", false otherwise
	 * @throws IOException
	 */
	public static boolean checkFastaHeader(String url) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(url)));	
		String s = br.readLine().substring(0, 1);	
		return s.equals(">");
	}
	
	/**
	 * Only for testing purposes
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String url) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(url)));
		StringBuilder sb = new StringBuilder();
		String s;

		while((s = br.readLine()) != null) {
			sb.append(s);
		}

		br.close();
		return sb.toString();
	}

	/**
	 * Custom exception
	 * @author erique
	 *
	 */
	public static class SetupException extends Exception {
		public SetupException(String msg) {
			super(msg);
		}
	}

	
	
	
	
	public static void main(String[] args) throws SetupException, InterruptedException, IOException {
		String orgAfolder = "C:/Users/erique/Desktop/org/orgAfolder";
		String orgBfolder = "C:/Users/erique/Desktop/org/orgBfolder";
		
		System.out.println("Getting and displaying folders:\n");
		List<File> foldersB = Setup.getAllFolders(orgBfolder);
		for(int i = 0; i < foldersB.size(); i++) {
			System.out.println(foldersB.get(i));
		}
		
		System.out.println("===================================");
		System.out.println("Getting and displaying files from folders (method 1):\n");
		for(int i = 0; i < foldersB.size(); i++) {
			List<File> filesB = Setup.getFilesFromFolder(foldersB.get(i).toString());
			for(int j = 0; j < filesB.size(); j++) {
				System.out.println(filesB.get(j));
			}
		}
		
		System.out.println("===================================");
		System.out.println("Getting and displaying files from folders (method 2):\n");
		List<File> filesB2 = Setup.getFilesFromFolder(foldersB);
		for(int i = 0; i < filesB2.size(); i++) {
			System.out.println(filesB2.get(i));
		}
		
		System.out.println("===================================");
		System.out.println("Checking fasta headers:\n");
		for(File f : filesB2) {
			System.out.println(f.toString() + " HAS FASTA HEADER: " + Setup.checkFastaHeader(f.toString()));
		}
		
	}
}
