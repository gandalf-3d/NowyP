package pl.agent.file;

import java.io.IOException;
import java.util.List;

//porownuje ile slow znalazl z tablica slow do wyszukania.

public class RatingDoctor1 {

	static ReadFile readFile = new ReadFile();
	static SearchWordInFileFromArray word = new SearchWordInFileFromArray();
	static int searchedWords = word.resulds;
	static List<String> bankWord = word.words();
	static Rating oceny = new Rating();
	static String rat;
	
	public static String rating(String rat){
		
		int bankWordSize = bankWord.size();
		int wordSearchedSize = word.searchWord(searchedWords);
		
		
		//trzeba bêdzie zliczaæ slowa i jakas sredia wyznaczyc
		
		if (bankWordSize <= wordSearchedSize){
			rat = oceny.bardzoDobra;
		}
		
		int bankWordSize2 = bankWordSize-1; 
		
		if ((wordSearchedSize < bankWordSize)&&(wordSearchedSize == bankWordSize2)){
			rat = oceny.dobra;
		}
		
		int bankWordSize3 = bankWordSize-3;
		
		if ((wordSearchedSize < bankWordSize)&&(wordSearchedSize == bankWordSize3)){
			rat = oceny.przeciêtna;
		}
		
		int bankWordSize4 = bankWordSize-4;
		
		if ((wordSearchedSize < bankWordSize)&&(wordSearchedSize == bankWordSize4)){
			rat = oceny.slaba;
		}
		
		
		if (wordSearchedSize==0){
			rat = oceny.beznadziejna;
		}
		
		
		return rat;
	}
	
	public static void main(String[] args) throws IOException {

		readFile.readFile("c:/plik.txt");
		
		System.out.println(rating(rat));
		
	}

}
