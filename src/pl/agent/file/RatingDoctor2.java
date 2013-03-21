package pl.agent.file;

import java.io.IOException;

//zlicza ile slow jest w pliku i ocenia

public class RatingDoctor2 {
	
	static int rat;
	static Rating oceny = new Rating();
	static ReadFile readFile = new ReadFile();
	static StringBuffer s;
	
	
	public static int countWords(StringBuffer s){

		s = readFile.text;
	    int counter = 0;

	    boolean word = false;
	    int endOfLine = s.length() - 1;

	    for (int i = 0; i < s.length(); i++) {
	        if (Character.isLetter(s.charAt(i)) == true && i != endOfLine) {
	            word = true;
	
	        } else if (Character.isLetter(s.charAt(i)) == false && word == true) {
	            counter++;
	            word = false;

	        } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
	            counter++;
	        }
	    }
	    
	    return counter;
	}

	public static int rating(int rat){
		
		int numberOfWords = countWords(s);
		
		//trzeba bêdzie zliczaæ slowa i jakas sredia wyznaczyc
		
		if(numberOfWords <= 5){
			rat = oceny.cztery;
		}
		
		else if (numberOfWords <= 4){
			rat = oceny.trzy;
		}
		
		else if (numberOfWords <= 2){
			rat = oceny.dwa;
		}
		
		
		else if (numberOfWords==0){
			rat = oceny.jeden;
		}
		
		else{
			rat = oceny.dziesiec;
		}
		
		return rat;
	}


	public static void main(String[] args) throws IOException {
		readFile.readFile("c:/plik.txt");
		
		System.out.println(countWords(s));
		
		System.out.println(rating(rat));
		

	}

}
