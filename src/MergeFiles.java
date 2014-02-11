// Oblig2 Oppgave 19_12 v/ Steffen H. Olaisen 10.02.2014
//
//
//
import java.io.*;
import java.util.*;

public class MergeFiles {
	
	/**
	 * Main method
	 * @param args[0], args[1}, args[2]...args[n - 1] for SourceFiles
	 * @param args[2] for TargetFile
	 */

	public static void main(String[] args) throws IOException{

		
		// Check for parameters
		if(args.length <= 2){
			System.out.println("Usage: Java sourceFiles(s) targetFile");
			System.exit(1);
		}
		
		// check if the target file alread exist
		File targetFile = new File(args[args.length - 1]);
		if (targetFile.exists()) {
			System.out.println("Source file " + args[args.length - 1] + " already exist");
			System.exit(2);
		}

		// Initiating Arrays to store the needed files and their I/O writers
		ArrayList<File> sourceFiles = new ArrayList<File>();
		ArrayList<RandomAccessFile> sourceFilesInOutput = new ArrayList<RandomAccessFile>();
		RandomAccessFile targetFileOut = new RandomAccessFile(targetFile, "rw");

		// Adding and creating files from alle the parameters except the last one, wich is the target file.
		for(int i = 0; i < args.length - 1; i++){
			sourceFiles.add(i, new File(args[i]));
		}
		
		// Adding I/O to all the sourcefiles
		for(int i = 0; i < args.length - 1; i++){
			sourceFilesInOutput.add(i, new RandomAccessFile(sourceFiles.get(i), "rw"));
		}
		
		// variables for useful information
		long currentLength = 0;
		long totalLength = 0;
		for(int i = 0; i < args.length - 1; i++){
			totalLength += sourceFilesInOutput.get(i).length();
		}

		// This loop read from all the sourcefiles, and writes to the targetfile.
		int r, bytesCopied = 0;
		for(int i = 0; i < args.length - 1; i++){
			while((r = sourceFilesInOutput.get(i).read()) != -1){
				targetFileOut.write((byte)r);
				bytesCopied++;
			}
			// Closing streams as they are not needed anymore. And displaying usefull information along the way.
			currentLength += sourceFilesInOutput.get(i).length();
			System.out.printf("Progress: " + ((double)currentLength/totalLength) * 100 + "%");
			sourceFilesInOutput.get(i).close();

		}
		System.out.println("Total size of the new file " + targetFile + " is " + targetFileOut.length() + " Byte");

		targetFileOut.close();

		
		
		
		

	}

}
