package pl.agent.read.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {
	
	public static void readFile(String filePath) throws IOException {
		
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		 try {
			    String textLine = bufferedReader.readLine();
			    do {
			      System.out.println(textLine);
			  
			      textLine = bufferedReader.readLine();
			    } while (textLine != null);
			  } finally {
			    bufferedReader.close();
			  }
	}

	
	public static void main(String[] args) throws IOException {
		
		readFile("C:/plik.txt");

	}

}
