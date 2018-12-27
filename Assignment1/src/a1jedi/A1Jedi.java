package a1jedi;

import java.util.Scanner;

public class A1Jedi {

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
				double[][] information = new double[students][4];
				int a = 0, aa = 0, bbb = 0, b = 0, bb = 0, ccc = 0, c = 0, cc = 0, ddd = 0, d = 0, f = 0;
				//loop through the data for each student
				for (int h = 0; h < students; h++) {
					String first = s.next();
					String last = s.next();
					int actPart = s.nextInt();
					int n = 0;
					double [] actAssign = new double [totalAssignments];
					for (int m = totalAssignments; m > 0; m--) {
						actAssign[n] = s.nextDouble();
						n++;
					}
					double part = 100 * (actPart/(possiblePart *.8));
					if (part > 100) {
						part = 100;
					}
					
					information[h][0] = part;
					double assignment = 0;
					double possibleAssignPoints = 0;
					double actAssignPoints = 0;
					int u = 0;
					for (int dh = totalAssignments; dh > 0; dh--) {
						actAssignPoints += actAssign[u];
						possibleAssignPoints += possiblePoints[u];
						u++;
					}
					assignment = 100 * (actAssignPoints/possibleAssignPoints);
					information[h][1] = assignment;
					double midExam = s.nextDouble();
					information[h][2] = midExam;
					double finalExam = s.nextDouble();
					information[h][3] = finalExam;
				}
				
				double standardDevMid = 0;
				double standardDevFin = 0;
				double sumMid = 0, sumFin = 0;
				for (int l = 0; l < students; l++) {
						sumMid +=information[l][2];
						sumFin +=information[l][3];
				}
					double averageMid = sumMid/students;
					double averageFin = sumFin/students;
					for (int e = 0; e < students; e++) {
						standardDevMid += (information[e][2]-averageMid) * (information[e][2]-averageMid);
						standardDevFin += (information[e][3]-averageFin) * (information[e][3]-averageFin);
					}
					double varianceMid = standardDevMid;
					standardDevMid = Math.sqrt(varianceMid/students);
					double varianceFin = standardDevFin;
					standardDevFin = Math.sqrt(varianceFin/students);
					double normalizedMid = 0;
					double normalizedFin = 0;
					for (int e = 0; e < students; e++) {
						normalizedMid = (information[e][2] - averageMid) / standardDevMid;
						normalizedFin = (information[e][3] - averageFin) / standardDevFin;
					double curved_scoreMid = 100;
					double curved_scoreFin = 100;
					
					if (normalizedMid < 2 && normalizedMid >=1) {
						curved_scoreMid = (((normalizedMid - 1) / (2-1)) * (100-94)) + 94;
					} else if (normalizedMid < 1 && normalizedMid >= 0) {
						curved_scoreMid = (((normalizedMid - 0) / (1-0)) * (94-85)) + 85;
					} else if (normalizedMid < 0 && normalizedMid >= -1) {
						curved_scoreMid = (((normalizedMid + 1) / (0+1)) * (85-75)) + 75;
					} else if (normalizedMid < -1 && normalizedMid >= -1.5) {
						curved_scoreMid = (((normalizedMid + 1.5) / (-1 + 1.5)) * (75 - 65)) + 65;
					} else if (normalizedMid < -1.5 && normalizedMid >= -2) {
						curved_scoreMid = (((normalizedMid + 2) / (-1.5 + 2)) * (65 - 55)) + 55;
					} else if (normalizedMid < -2 && normalizedMid >= -3) {
						curved_scoreMid = (((normalizedMid + 3) / (-2 + 3)) * (55 - 30)) + 30;
					} else if (normalizedMid < -3 && normalizedMid > -4) {
						curved_scoreMid = (((normalizedMid + 4) / (-3 +4 )) * (30-0));
					} else if (normalizedMid <= -4) {
						curved_scoreMid = 0;
					} 
					information[e][2] = curved_scoreMid;
					if (normalizedFin < 2 && normalizedFin >=1) {
						curved_scoreFin = (((normalizedMid - 1) / (2-1)) * (100-94)) + 94;
					} else if (normalizedFin < 1 && normalizedFin >= 0) {
						curved_scoreFin = (((normalizedFin - 0) / (1-0)) * (94-85)) + 85;
					} else if (normalizedFin < 0 && normalizedFin >= -1) {
						curved_scoreFin = (((normalizedFin + 1) / (0+1)) * (85-75)) + 75;
					} else if (normalizedFin < -1 && normalizedFin >= -1.5) {
						curved_scoreFin = (((normalizedFin + 1.5) / (-1+1.5)) * (75-65)) + 65;
					} else if (normalizedFin < -1.5 && normalizedFin >= -2) {
						curved_scoreFin = (((normalizedFin + 2) / (-1.5 + 2)) * (65-55)) + 55;
					} else if (normalizedFin < -2 && normalizedFin >= -3) {
						curved_scoreFin = (((normalizedFin + 3) / (-2+3)) * (55-30)) + 30;
					} else if (normalizedFin < -3 && normalizedFin > -4) {
						curved_scoreFin = (((normalizedFin + 4) / (-3+4)) * (30-0));
					} else if (normalizedFin <= -4) {
						curved_scoreFin = 0;
					}
					information[e][3] = curved_scoreFin;
					}
					double finalGrade = 0;
					for (int z = 0; z < students; z++) {
					finalGrade = information[z][0] * .15 + information[z][1] *.4 + information[z][2] *.2 + information[z][3] * .25;
					
					if (finalGrade >= 94) {
						a += 1;
					} else if (finalGrade >= 90) {
						aa += 1;
					} else if (finalGrade >= 86) {
						bbb += 1;
					} else if (finalGrade >= 83) {
						b += 1;
					} else if (finalGrade >= 80) {
						bb += 1;
					} else if (finalGrade >= 76) {
						ccc += 1;
					} else if (finalGrade >= 73) {
						c += 1;
					} else if (finalGrade >= 70) {
						cc += 1;
					} else if (finalGrade >= 65) {
						ddd += 1;
					} else if (finalGrade >= 60) {
						d += 1;
					} else if (finalGrade < 60) {
					 f +=1;
					}
					}
				
					System.out.println("A: " + a);
					System.out.println("A-: " + aa);
					System.out.println("B+: "  + bbb);
					System.out.println("B: " + b);
					System.out.println("B-: " + bb);
					System.out.println("C+: " + ccc);
					System.out.println("C: " + c);
					System.out.println("C-: " + cc);
					System.out.println("D+: " + ddd);
					System.out.println("D: " + d);
					System.out.println("F: " + f);
				}
					
	}


