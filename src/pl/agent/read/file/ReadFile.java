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
		
		 try {
		
			 do {
			 System.out.println(text);
			 text= text.append(bufferedReader.readLine());
			 
			 //po wszytkich zakach wyslietla nullnullnull...i znow znaki od nowej lini i znów null..

			 } while (text != null);
			 
		 } finally { 
			 bufferedReader.close();
			 }
		 
		 return text;
			  }
		
	
	
	public static void main(String[] args) throws IOException {
		
		//zapetla sie trzeba poprawiæ
		
		readFile("c:/plik.txt");

	}

}
