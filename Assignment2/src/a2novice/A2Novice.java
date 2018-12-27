package a2novice;

import java.util.Scanner;

public class A2Novice {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
	}
	public static void process(Scanner s) {
		// Call the width and length into a int that I can store and use multiple times
		int width = s.nextInt();
		int length = s.nextInt();
		printArray(arrayInput(s, length, width), length, width);
	}
	public static int[][] arrayInput(Scanner s, int length, int width) {
		int[][] pixels = new int[length][width];
		//Create for loops to call in all of the data from the Scanner and assign them to their spot in the pixel array
		for (int i = 0; i < width; i++) {
			for (int z = 0; z < length; z++) {
			pixels[z][i] = s.nextInt();	
		}
	}
		return pixels;
	}
		
	 
	public static void printArray(int[][] pixels, int length, int width) {
		int minValue = pixels[0][0];
		int maxValue = pixels[0][0];
		double sum = 0;
		double average = 0;
		for (int h = 0; h < length; h++) {
			for (int g = 0; g < width; g++) {
				if (pixels [h][g] < minValue) {
					minValue = pixels[h][g];
				}
				if (pixels[h][g] > maxValue) {
					maxValue = pixels[h][g];
				}
				sum += pixels[h][g];
				if (pixels[h][g] <= 9) {
				System.out.print("#");
			} else if (pixels[h][g] <= 19) {
				System.out.print("M");
			} else if (pixels[h][g] <= 29) {
				System.out.print("X");
			} else if (pixels[h][g] <= 39) {
				System.out.print("D");
			} else if (pixels[h][g] <= 49) {
				System.out.print("<");
			} else if (pixels[h][g] <= 59) {
				System.out.print(">");
			} else if (pixels[h][g] <= 69) {
				System.out.print("s");
			} else if (pixels[h][g] <= 79) {
				System.out.print(":");
			} else if (pixels[h][g] <= 89) {
				System.out.print("-");
			} else if (pixels[h][g] <= 99) {
				System.out.print(" ");
			}
				
			}
			System.out.println("");
		}
		average = sum/(length*width);
		System.out.println("Min value = " + minValue);
		System.out.println("Max value = " + maxValue);
		System.out.println("Average value = " + average);
	}

	
}