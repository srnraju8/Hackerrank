package week2;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * https://www.hackerrank.com/challenges/pairs?h_r=internal-search
 * 
 * Sample Input
 * 5 2  
 * 1 5 3 4 2  
 * Output
 * 3
 * */
public class Pairs {

	public static void main(String[] args) {

		int a[];
		Scanner scan = new Scanner(System.in);
		int arryLength = scan.nextInt();
		int difference = scan.nextInt();
		int temp = 0;
		ArrayList<Integer> ar = new ArrayList<Integer>();
		for (int curr_pointer = 0; curr_pointer < arryLength; curr_pointer++) {
			temp = scan.nextInt();
			ar.add(temp);
		}
		temp = 0;
		for (int curr_pointer = 0; curr_pointer < arryLength; curr_pointer++)
			if (ar.contains((ar.get(curr_pointer) + difference)))
				temp++;
		System.out.println(temp);
	}
}
