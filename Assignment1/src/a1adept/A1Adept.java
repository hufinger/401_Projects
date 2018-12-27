package a1adept;

import java.util.Scanner;

public class A1Adept {
	
	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
		
	}
	
	public static void process(Scanner s) {
		//Call the number of assignments
		int totalAssignments = s.nextInt();
		//Create an array to store the values of possible assignment points
		int[]possiblePoints = new int[totalAssignments];
		int k = 0;
		//Call the possible points for each assignment
		for (int i = totalAssignments; i > 0; i--) {
			possiblePoints[k]= s.nextInt();
			k++;
		}
		//Call the possible participation points and the amount of students
		int possiblePart = s.nextInt();
		int students = s.nextInt(); 
		//loop through the data for each student
		for (int h = students; h > 0; h--) {
			String first = s.next();
			String last = s.next();
			int actPart = s.nextInt();
			int n = 0;
			double [] actAssign = new double [totalAssignments];
			for (int m = totalAssignments; m > 0; m--) {
				actAssign[n] = s.nextDouble();
				n++;
			}
			double midExam = s.nextDouble();
			double finalExam = s.nextDouble();
			double part = 100 * (actPart/(possiblePart *.8));
			if (part > 100) {
				part = 100;
			}
			double assignment = 0;
			double possibleAssignPoints = 0;
			double actAssignPoints = 0;
			int u = 0;
			for (int d = totalAssignments; d > 0; d--) {
				actAssignPoints += actAssign[u];
				possibleAssignPoints += possiblePoints[u];
				u++;
			}
			assignment = 100 * (actAssignPoints/possibleAssignPoints);
			double finalPart = part *.15;
			double finalAssign = assignment * .4;
			double finalmidExam = midExam * .2;
			double finalfinalExam = finalExam *.25;
			double finalGrade = finalPart + finalAssign + finalmidExam + finalfinalExam;
			String letterGrade = "F";
			if (finalGrade >= 94) {
				letterGrade = "A";
			} else if (finalGrade >= 90) {
				letterGrade = "A-";
			} else if (finalGrade >= 86) {
				letterGrade = "B+";
			} else if (finalGrade >= 83) {
				letterGrade = "B";
			} else if (finalGrade >= 80) {
				letterGrade = "B-";
			} else if (finalGrade >= 76) {
				letterGrade = "C+";
			} else if (finalGrade >= 73) {
				letterGrade = "C";
			} else if (finalGrade >= 70) {
				letterGrade = "C-";
			} else if (finalGrade >= 65) {
				letterGrade = "D+";
			} else if (finalGrade >= 60) {
				letterGrade = "D";
			}
			System.out.println(first.charAt(0) + ". " + last + " " + letterGrade );
		}
	}

}
