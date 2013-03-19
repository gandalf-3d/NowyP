package pl.agent.read.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.border.EmptyBorder;


public class ReadFile {
	
	public static StringBuffer text;
	
	public static StringBuffer readFile(String filePath) throws IOException {
		text = new StringBuffer();
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		text = text.append(bufferedReader.readLine());
		
		 while ( text != null){
			 System.out.println(text);
		 }
				  bufferedReader.close();
				return text;
			  }
		
	
	
	public static void main(String[] args) throws IOException {
		
		//zapêtla siê pierwsza linia trzeba poprawiæ
		
		readFile("c:/plik.txt");

	}

}
