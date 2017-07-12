package week3;
/*
*
* https://www.hackerrank.com/challenges/game-of-thrones
* this is java8 dependent
*/
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Palincheck {
	static Map<Character, Integer> map = new TreeMap<>();
	static Set<Character> keys = new HashSet<Character>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] str = sc.next().split("");
		sc.close();
		for (String s : str) {
			Character temp = s.charAt(0);
			if (map.get(temp) == null)
				map.put(temp, 1);
			else
				map.put(temp, map.get(temp) + 1);
		}
		int oddCounter = 0;
		boolean isPalidrome = true;
		keys = map.keySet();
		if (str.length % 2 == 1) {
			for (Character c : keys) {
				if (map.get(c) % 2 == 1)
					oddCounter++;
				if (oddCounter > 1) {
					isPalidrome = false;
					break;
				}
			}
		} else {
			for (Character c : keys) {
				if (map.get(c) % 2 == 1) {
					isPalidrome = false;
					break;
				}
			}
		}
		if(isPalidrome)
			System.out.println("YES");
		else
			System.out.println("NO");
		
	}
}
