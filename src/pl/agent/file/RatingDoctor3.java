package pl.agent.file;

import java.io.IOException;

public class RatingDoctor3 {

	static ReadFile readFile = new ReadFile();
	static Rating oceny = new Rating();
	static RatingDoctor1 doctor1 = new RatingDoctor1();
	static RatingDoctor2 doctor2 = new RatingDoctor2();
	static int rat;
	static double rat2;
	
	public static double average(double rat2){
		
		int doctorOne = doctor1.rating(rat);
		System.out.println(doctorOne);
		
		int doctorTwo = doctor2.rating(rat);
		System.out.println(doctorTwo);
		
		rat2 = (((doctorOne + oceny.doktor1) + (doctorTwo + oceny.doktor2)) / 2);
		
		return rat2;
		
	}
	
	public static void main(String[] args) throws IOException {

		readFile.readFile("c:/plik.txt");
		
		System.out.println(average(rat2));
	}
}
