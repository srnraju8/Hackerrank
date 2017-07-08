package week2;

/*
 * 
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid
 * 
 Sample Input 
 4
 4
 1 1 0 0
 0 1 1 0
 0 0 1 0
 1 0 0 0
 Output
 5
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Connectedcells {

	static int a[][];
	static int tempcount = 0;
	static int height;
	static int length;
	static ArrayList<String> s = new ArrayList<String>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int mastercount = 0;
		height = sc.nextInt();
		length = sc.nextInt();
		a = new int[height][length];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		// print(m,n);
		for (int curr_Height = 0; curr_Height < height; curr_Height++) {
			for (int curr_Length = 0; curr_Length < length; curr_Length++) {
				if (a[curr_Height][curr_Length] != 0) {
					tempcount = 1;
					// System.out.println("Calling with" + i + " " + j);
					tempcount = getPosiibletraversels(curr_Height, curr_Length);
					// System.out.println("tempcount "+tempcount);
					if (tempcount > mastercount)
						mastercount = tempcount;
				}
			}
		}
		System.out.println(mastercount);

	}

	static int getPosiibletraversels(int heightToStartWith, int lengthToStartWith) {
		s.add(heightToStartWith + "" + lengthToStartWith);
		for (int pointingHeight = heightToStartWith - 1; pointingHeight <= heightToStartWith + 1; pointingHeight++) {
			for (int pointingLength = lengthToStartWith - 1; pointingLength <= lengthToStartWith
					+ 1; pointingLength++) {
				// print();
				if (pointingHeight < height && pointingHeight >= 0 && pointingLength < length && pointingLength >= 0
						&& !s.contains(pointingHeight + "" + pointingLength)) {
					s.add(pointingHeight + "" + pointingLength);
					if (a[heightToStartWith][lengthToStartWith] == a[pointingHeight][pointingLength]) {
						tempcount++;
						// call to start again from the currently traversed
						// Element
						getPosiibletraversels(pointingHeight, pointingLength);
					}

				}

			}
		}
		return tempcount;
	}

	// a method to print the array
	static void print() {
		for (int ii = 0; ii < height; ii++) {
			for (int jj = 0; jj < length; jj++) {
				System.out.print("(" + ii + "" + jj + ")=" + a[ii][jj] + "   ");
			}
			System.out.println();
		}
	}

}
