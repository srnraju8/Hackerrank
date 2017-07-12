package week3;
/*

https://www.hackerrank.com/challenges/kruskalmstrsub/problem

*/
import java.util.*;

public class MST {
	static int traversedCost = 0;
	static int noOfNodes;
	static int noOfEdges;
	static Map<Integer, String> map = new TreeMap<>();
	static List<String> traversedNodes = new LinkedList<String>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		noOfNodes = sc.nextInt();
		noOfEdges = sc.nextInt();
		int tempNode1, tempNode2, tempCost;
		String node = "";
		for (int i = 0; i < noOfEdges; i++) {
			tempNode1 = sc.nextInt();
			tempNode2 = sc.nextInt();
			tempCost = sc.nextInt();
			node = map.get(tempCost);
			if (node == null) {
				map.put(tempCost, "");
				node = "";
			}
			if (tempNode1 < tempNode2)
				map.put(tempCost, node + tempNode1 + "," + tempNode2 + "|");
			else
				map.put(tempCost, node + tempNode2 + "," + tempNode1 + "|");
		}
//		printMap();
		startTraversel();
		System.out.println(traversedCost);
		sc.close();
	}

	private static void startTraversel() {
		Set<Integer> costKeys = map.keySet();
		for (int i : costKeys) {
			String nodesCombination[] = getnodePairsSorted(i);
			for (String combination : nodesCombination) {
				String individualNodes[] = combination.split(",");
				if (!isCyclesformed(individualNodes)) {
					traversedCost += i;
				}
				if (traversedNodes.get(0).split(",").length == noOfNodes)
					return;
			}
		}
	}

	private static boolean isCyclesformed(String[] individualNodes) {

		//System.out.println("Checking cycle for " + individualNodes[0] + "," + individualNodes[1]);
		int nodesize = traversedNodes.size();
		boolean nodaAfound = false, nodeBfound = false;
		int foundAindex = 0, foundBindex = 0;
		for (int i = 0; i < nodesize; i++) {
			String[] s = traversedNodes.get(i).split(",");
			for (int j = 0; j < s.length; j++) {
				if (s[j].equals(individualNodes[0])) {
					nodaAfound = true;foundAindex = i;
				}
				if (s[j].equals(individualNodes[1])) {
					nodeBfound = true;foundBindex = i;
				}
			}
		}
		if (nodaAfound&&nodeBfound&&foundAindex == foundBindex)
			return true;
		if (nodaAfound && nodeBfound) {
			//System.out.println("found both at " + foundAindex + " " + foundBindex);
			String str = traversedNodes.get(foundAindex).concat("," + traversedNodes.get(foundBindex));
			traversedNodes.remove(foundAindex);
			traversedNodes.add(foundAindex, str);
			traversedNodes.remove(foundBindex);
		} else if (nodaAfound) {
			//System.out.println("found A at " + foundAindex);
			String str = traversedNodes.get(foundAindex).concat("," + individualNodes[1]);
			traversedNodes.remove(foundAindex);
			traversedNodes.add(foundAindex, str);
		} else if (nodeBfound) {
			//System.out.println("found B at " + foundBindex);
			String str = traversedNodes.get(foundBindex).concat("," + individualNodes[0]);
			traversedNodes.remove(foundBindex);
			traversedNodes.add(foundBindex, str);
		} else if (!(nodaAfound || nodeBfound)) {
			//System.out.println("found neither " + foundAindex + " " + foundBindex);
			traversedNodes.add(individualNodes[0] + "," + individualNodes[1]);
		}
		return false;
	}

	private static String[] getnodePairsSorted(int i) {
		String str[] = map.get(i).split("\\|");
		if (str.length == 1)
			return str;
		int[] sumArray = new int[str.length];
		for (int i1 = 0; i1 < str.length; i1++) {
			String indivNode[] = str[i1].split(",");
			sumArray[i1] = Integer.parseInt(indivNode[0]) + Integer.parseInt(indivNode[1]);
		}
		int temp_int;
		String temp_str;
		for (int i1 = 0; i1 < str.length; i1++) {
			for (int j1 = i1 + 1; j1 < str.length; j1++) {
				if (sumArray[i1] > sumArray[j1]) {
					// Swapping the sumArray
					temp_int = sumArray[i1];
					sumArray[i1] = sumArray[j1];
					sumArray[j1] = temp_int;
					// Swapping the NodeArray
					temp_str = str[i1];
					str[i1] = str[j1];
					str[j1] = temp_str;
				}
				if (sumArray[i1] == sumArray[j1]) {
					if (Integer.parseInt(str[i1].split(",")[0]) > Integer.parseInt(str[j1].split(",")[0])) {
						temp_str = str[i1];
						str[i1] = str[j1];
						str[j1] = temp_str;
					}
				}
			}
		}
		return str;
	}

	static void printMap() {
		for (int i : map.keySet())
			System.out.println(i + "  " + map.get(i));
	}
}
