package a1novice;

import java.util.Scanner;

public class A1Novice {

	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
	}
	
	public static void process(Scanner s) {
		// Creating a for loop to run through all the inputed data
		for (int k = s.nextInt(); k > 0; k--) {
			//Calling all of the information for a student
			String first = s.next();
			String last = s.next();
			double assignment = s.nextDouble() * .4;
			double participation = s.nextDouble() * .15;
			double midterm = s.nextDouble() * .2;
			double examFinal=  s.nextDouble() * .25;
			//Adding the scores together to make a final grade
			double grade = (assignment + participation + midterm + examFinal);
			String finalgrade = "F";
			if (grade >= 94) {
				finalgrade = "A";
			} else if (grade >= 90) {
				finalgrade = "A-";
			} else if (grade >= 86) {
				finalgrade = "B+";
			} else if (grade >= 83) {
				finalgrade = "B";
			} else if (grade >= 80) {
				finalgrade = "B-";
			} else if (grade >= 76) {
				finalgrade = "C+";
			} else if (grade >= 73) {
				finalgrade = "C";
			} else if (grade >= 70) {
				finalgrade = "C-";
			} else if (grade >= 65) {
				finalgrade = "D+";
			} else if (grade >= 60) {
				finalgrade = "D";
			}
			//Joining the various data points into the correct format
			System.out.println(first.charAt(0) + ". " + last + " " + finalgrade);
		}
	}	
}
