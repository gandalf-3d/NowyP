package pl.agent.read.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.border.EmptyBorder;


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
