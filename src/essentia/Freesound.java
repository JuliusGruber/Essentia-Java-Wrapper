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
		
		
//		String folderPath = "C:/Users/Julius/Desktop/SampleDatenbanken/15Samples";
//		String folderPath = "C:/Users/Julius/Desktop/SampleDatenbanken/33DrumSamples";
//		String folderPath = "C:/Users/Julius/Desktop/SampleDatenbanken/TSD Acidized Wav Files/TSD - Reggae Snares - 100";
//		String folderPath = "C:/Users/Julius/Desktop/SampleDatenbanken/snareGroups";
		String folderPath = "C:/Users/Julius/Desktop/SampleDatenbanken/synth-groups";
		
		
//		getExeResponse(args);
		
		extractFeatures(folderPath,args);

		

		

	}



	protected static void extractFeatures(String folderPath, String []args ){
		String exePath = "C:/Users/Julius/Desktop/essentia-extractors-v2.1_beta2/streaming_extractor_freesound.exe";
		String profilePath = "C:/Users/Julius/Dropbox/eclipseWorkspace/essentia/yamlProfiles/freeSound.yaml";
		
		

		
//		create directory for the yaml files
		String outputFolderPath = folderPath +"/YAMLessentiaFreesound/";
		File folder = new File(outputFolderPath);
		folder.mkdirs();
		
		Collection<File> wavFiles = getWAVCollection(folderPath);
		
		for(File file : wavFiles){
			String filePath = null;
			String fileName = null;
			try {
//				System.out.println(file.getCanonicalPath());
//				System.out.println(file.getName());
				filePath = file.getCanonicalPath();
				fileName = file.getName();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String yamlFileName = fileName.replace(".wav", ".yaml");
			
			String outputFilePath = outputFolderPath+ yamlFileName;
			System.out.println("Starting to create: "+outputFilePath);
			
			
			
			Process process =  null;		
			try {
				 process= new ProcessBuilder(exePath,filePath, outputFilePath).start();
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
		
		deleteJSONfiles(outputFolderPath);
	}
	
	
	protected static void deleteJSONfiles(String outputFolderPath){
		File dir = new File(outputFolderPath);
		String[] extensions = new String[] {"json" , "JSON" };
		Collection<File> files =   FileUtils.listFiles(dir, extensions, true);
		for(File file : files){
			file.delete();
		}
	}

	protected static void getExeResponse(String [] args){
		String exePath = "C:/Users/Julius/Desktop/essentia-extractors-v2.1_beta2/streaming_extractor_freesound.exe";
		
		
		String inputAudioFilePath = "C:/Users/Julius/Desktop/SampleDatenbanken/15Samples/LudFlamA-01.wav";
		String outputTxtFilePath = "C:/Users/Julius/Desktop/SampleDatenbanken/15Samples/LudFlamA-01.yaml";
		String profilePath = "C:/Users/Julius/Dropbox/eclipseWorkspace/essentia/yamlProfiles/freeSound.yaml";
		
		
		Process process =  null;		
		try {
			 process= new ProcessBuilder(exePath, inputAudioFilePath, outputTxtFilePath).start();
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
