package pl.agent.file;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class ReadFile {
	
	public static StringBuffer text;
	
	public static StringBuffer readFile(String filePath) throws IOException {
		
		text = new StringBuffer();
		
		File file = new File(filePath);
		Scanner scanner = new Scanner(file);
		
		
		 try {
			while (scanner.hasNextLine()) {
			        text = text.append(scanner.nextLine());
			        
			 }
			 
			 scanner.close();
		} catch (Exception e) {
			System.err.println("B£¹d w odczytaniu pliku");
		}
		 
		 return text;
			  }
		
	
	
	public static void main(String[] args) throws IOException {
		
		
		readFile("c:/plik.txt");
		
		System.out.println(text);

	}

}
