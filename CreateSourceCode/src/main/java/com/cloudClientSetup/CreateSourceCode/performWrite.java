package com.cloudClientSetup.CreateSourceCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


public class performWrite {
	
	static void modifyFile(String rootFile, String requiredFile, Map<String, String> dataMap) throws IOException {

		File fileToBeModified = new File(rootFile);
		File fileToWrite = new File(requiredFile);
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));

			// Reading all the lines of input text file into oldContent
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}

			// Replacing oldString with packageName in the oldContent
			String newContent = oldContent.replaceAll("<<packageName>>", dataMap.get("packageName")).
					replaceAll("<<serviceName>>", dataMap.get("serviceName")).
					replaceAll("<<classFileName>>", dataMap.get("classFileName"));
			
			System.out.println(newContent);
			// Rewriting the input text file with newContent
			writer = new FileWriter(fileToWrite);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the resources
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}