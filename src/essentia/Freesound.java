package essentia;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class Freesound {
public static void main(String[] args) {
		getExeResponse(args);
		
////		String folderPath = "C:/Users/Julius/Desktop/SampleDatenbanken/15Samples";
//		String folderPath = "C:/Users/Julius/Desktop/SampleDatenbanken/33DrumSamples";
//		
//		
//		String exePath = "C:/Users/Julius/Desktop/essentia-extractors-v2.1_beta2/streaming_extractor_freesound.exe";
//
//		
////		create directory for the yaml files
//		String outputFolderPath = folderPath +"/YAMLessentiaFreesound/";
//		File folder = new File(outputFolderPath);
//		folder.mkdirs();
//		
//		Collection<File> wavFiles = getWAVCollection(folderPath);
//		
//		for(File file : wavFiles){
//			String filePath = null;
//			String fileName = null;
//			try {
////				System.out.println(file.getCanonicalPath());
////				System.out.println(file.getName());
//				filePath = file.getCanonicalPath();
//				fileName = file.getName();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			String yamlFileName = fileName.replace(".wav", ".yaml");
//			
//			String outputFilePath = outputFolderPath+ yamlFileName;
//			System.out.println("Starting to create: "+outputFilePath);
//			
//			
//			
//			Process process =  null;		
//			try {
//				 process= new ProcessBuilder(exePath,filePath, outputFilePath ).start();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			InputStream is = process.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader br = new BufferedReader(isr);
//			String line;
//
//			System.out.printf("Output of running %s is:", Arrays.toString(args));
//
//			try {
//				while ((line = br.readLine()) != null) {
//				  System.out.println(line);
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
		

		

	}

	protected static void getExeResponse(String [] args){
		String exePath = "C:/Users/Julius/Desktop/essentia-extractors-v2.1_beta2/streaming_extractor_freesound.exe";
		Process process =  null;		
		try {
			 process= new ProcessBuilder(exePath).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;

		System.out.printf("Output of running %s is:", Arrays.toString(args));

		try {
			while ((line = br.readLine()) != null) {
			  System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected static Collection<File> getWAVCollection(String dirName) {
		File dir = new File(dirName);
		String[] extensions = new String[] {"wav" , "WAV" };
		Collection<File> files =   FileUtils.listFiles(dir, extensions, true);
		System.out.println("There are "+files.size()+" samples in this folder");
		return files;
	}
	

}
