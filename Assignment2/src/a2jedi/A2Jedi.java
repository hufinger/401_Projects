package a2jedi;

import java.util.Scanner;

public class A2Jedi {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
	}
	public static void process(Scanner s) {
		//Gather and store the data
		int increment = s.nextInt();
		int width = s.nextInt();
		int length = s.nextInt();
		int[]input = new int[length*width];
		for (int i = 0; i < input.length; i++) {
			input[i] = s.nextInt();
		}
		//Calculate bins
		int bins;
		if (100 % increment == 0) {
			bins = 100/increment;
		} else {
			bins = (100/increment) + 1;
		}
		//fill in the array
		int[]totalBins = new int [bins];
		 for (int g = 0; g < bins; g++) {
			 for (int h = 0; h < input.length; h++) {
				 if (input[h]  >= (g * increment)) {
					if (input[h] < ((g+1) * increment)) {
						totalBins[g] += 1;
					}
				}
			 }
		 }
		for (int f = 0; f < bins; f++) {
			double stars = (double)totalBins[f] / (length*width);
			stars *= 100;
			stars += .5;
			totalBins[f] = (int)stars;
		}
		int height = 0;
		for (int q = 0; q < bins; q++) {
			if (totalBins[q] > height) {
				height = totalBins[q];
			}
		}
		char[][] printed = new char[height + 3][bins];
		for (int j = 0; j < bins; j++) {
			for (int w = 0; w < height + 3; w++) {
				printed[w][j] = ' ';
				printed[height][j] = '-';
			}
		}
		char[][]inc = new char[2][bins];
		for (int r = 0; r < bins; r++) {
			for (int e = 0; e < inc.length; e++) {
				String change = Integer.toString(increment*r);
				if (increment*r > 9) {
					if (e == 0) {
						inc[e][r] = change.charAt(0);
					} else {
						inc[e][r] = change.charAt(1);
					}
				} else {
					inc[0][r] = ' ';
					inc[1][r] = change.charAt(0);
				} 
			}
		} 
		for (int b = 1; b < 3; b++) {
			for (int l = 0; l < bins; l++) {
				printed[height+b][l] = inc[b-1][l];
			}
		}
		//Resume here with inputting the stars
		for (int t = 0 ; t < height ; t++) {
			for (int m = 0; m < bins; m++ ) {
				if (totalBins[m] > t && totalBins[m] != 0) {
					printed[height - t - 1][m] = '*';
				}
			}
		}
		for (int n = 0; n < height + 3; n++) {
			for (int x = 0; x < bins; x++) {
				System.out.print(printed[n][x]);
			}
			System.out.println("");
		}
	} 
}
