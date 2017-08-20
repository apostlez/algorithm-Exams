import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Problem_1_1_done {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
            //out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".out"));
			out = new BufferedWriter(new FileWriter("input.out"));
            start("Set1.in");
			//start("t.in");
			//start(args[0]);
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	static void start(String filename) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			int N = sc.nextInt();
			TreeMap<Integer,TreeMap<Integer, Integer>> map = new TreeMap<Integer, TreeMap<Integer, Integer>>();
			for(int i=0;i<N;i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				TreeMap<Integer, Integer> x1 = map.get(x);
				if(x1 == null) {
					x1 = new TreeMap<Integer, Integer>();
					x1.put(y, 0);
				} else {
					x1.put(y, 0);
				}
				map.put(x, x1);
			}
			int result = calculate(map);
			System.out.println(result);
			out.write(String.valueOf(result));
			out.newLine();
		}
	}
	
	/*
	 * 1. marking first
	 * 2. update map
	 * 3. do next sec 
	 */
	static int calculate(TreeMap<Integer,TreeMap<Integer, Integer>> map) {
		boolean found = true;
		int sec = 0;
		while(found) {
			ArrayList<int[]> changes = new ArrayList<int[]>();

			for(Map.Entry<Integer, TreeMap<Integer, Integer>> xentry : map.entrySet()) {
				int x = xentry.getKey();
				TreeMap<Integer, Integer> x1 = map.get(x);
				for(Map.Entry<Integer, Integer> yentry : x1.entrySet()) {
					int y = yentry.getKey();
					//  C 1
					// 4 2
					//  3
					// compare C 1
					if (x1.get(y + 2) != null) {
						if (x1.get(y + 1) == null) {
							changes.add(new int[] { x, y + 1 });
						}
					}
					TreeMap<Integer, Integer> x2 = map.get(x + 1);
					if (x2 != null) {
						// compare C 2
						if (x2.get(y + 1) != null) {
							if (x1.get(y + 1) == null) {
								changes.add(new int[] { x, y + 1 });
							}
							if (x2.get(y) == null) {
								changes.add(new int[] { x + 1, y });
							}
						}
						// compare C 4
						if (x2.get(y - 1) != null) {
							if (x1.get(y - 1) == null) {
								changes.add(new int[] { x, y - 1 });
							}
							if (x2.get(y) == null) {
								changes.add(new int[] { x + 1, y });
							}
						}
					}
					// compare C 3
					TreeMap<Integer, Integer> x3 = map.get(x + 2);
					if (x3 != null && x3.get(y) != null) {
						if (x2 == null || x2.get(y) == null) {
							changes.add(new int[] { x + 1, y });
						}
					}
				}
			}
			//print(map);
			if (changes.size() == 0) {
				found = false;
				break;
			}
			for(int i=0;i<changes.size();i++) {
				int[] point = changes.get(i);
				TreeMap<Integer, Integer> tmp = map.get(point[0]);
				if(tmp == null) {
					tmp = new TreeMap<Integer, Integer>();
					map.put(point[0], tmp);
				}
				tmp.put(point[1], sec+1);
			}
			//System.out.println(map);
			sec++;
		}
		//print(map);
		return sec;
	}
	
	static void print(TreeMap<Integer,TreeMap<Integer, Integer>> map) {
		int MAX = 7;
		for(int i=0;i<MAX;i++) {
			TreeMap<Integer, Integer> line = map.get(i);
			if(line == null) {
				fillNum(MAX);
				System.out.println();
				continue;
			}
			for(int j=0;j<MAX;j++) {
				Object y = line.get(j);
				if(y == null) {
					System.out.print("O");
				} else {
					System.out.print("X");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	static void fillNum(int num) {
		for(int i=0;i<num;i++) {
			System.out.print('O');
		}
	}
}
