package a2adept;

import java.util.Scanner;

public class A2Adept {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
	}
	public static void process(Scanner s) {
		int increment = s.nextInt();
		int width = s.nextInt();
		int length = s.nextInt();
		int[] input = new int[length*width];
		for (int g = 0; g < length*width; g++) {
				input[g] = s.nextInt();
				}
		
		int bins;
		if (100 % increment == 0) {
		bins = 100/increment; 
		}
		else {
			bins = 100/increment + 1;
		}
		
		int[]totalBins = new int [bins];
		for (int i = 0; i < bins; i++) {
			for (int h = 0; h < input.length; h++) {
				if (input[h] >= (i * increment)) {
					if (input[h] < ((i+1) * increment)) {
					totalBins[i] = totalBins[i] + 1;
					}
				}
			} 
		}
		
		for (int g = 0; g < bins; g++) {
			double stars =  ((double) totalBins[g]) / (length*width);
			stars *= 100;
			stars += .5;
			totalBins[g] = (int)stars;
		}
		
			for (int f = 0; f < bins; f++) {
				if (f*increment > 9) {
				System.out.print(f*increment + ":");
				} else {
					System.out.print(" " + f*increment + ":");
				}
				for (int j = 0; j < totalBins[f]; j++) {
				System.out.print("*");
				}
			System.out.println("");
			}	
		}
}
