package pl.agent.file;

import java.io.IOException;
import java.util.List;

//porownuje ile slow znalazl z tablica slow do wyszukania.

public class RatingDoctor1 {

	static ReadFile readFile = new ReadFile();
	static SearchWordInFileFromArray word = new SearchWordInFileFromArray();
	static WordBank wordBank = new WordBank();
	static int searchedWords = word.resulds;
	static List<String> bankWord = wordBank.bank();
	static Rating oceny = new Rating();
	static int rat;
	
	public static int rating(int rat){
		
		int bankWordSize = bankWord.size();
		System.out.println(bankWordSize);
		int wordSearchedSize = word.searchWord(searchedWords);
		System.out.println(wordSearchedSize);
		
		//trzeba bêdzie zliczaæ slowa i jakas sredia wyznaczyc
		
		if (bankWordSize <= wordSearchedSize){
			rat = oceny.dziesiec;
		}
		
		int bankWordSize2 = bankWordSize-1; 
		
		if ((wordSearchedSize < bankWordSize)&&(wordSearchedSize == bankWordSize2)){
			rat = oceny.siedem;
		}
		
		int bankWordSize3 = bankWordSize-3;
		
		if ((wordSearchedSize < bankWordSize)&&(wordSearchedSize == bankWordSize3)){
			rat = oceny.cztery;
		}
		
		int bankWordSize4 = bankWordSize-4;
		
		if ((wordSearchedSize < bankWordSize)&&(wordSearchedSize == bankWordSize4)){
			rat = oceny.trzy;
		}
		
		
		if (wordSearchedSize==0){
			rat = oceny.jeden;
		}
		
		
		return rat;
	}
	
	public static void main(String[] args) throws IOException {

		readFile.readFile("c:/plik.txt");
		
		System.out.println(rating(rat));
		
	}

}
