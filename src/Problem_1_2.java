import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Problem_1_2 {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
            //out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".out"));
			out = new BufferedWriter(new FileWriter("input.out"));
            start("Set2.in");
			//start("t.in");
			//start(args[0]);
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	static double MAX = 5000;
	//static double MAX = 1000000000;
	static void start(String filename) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			int N = sc.nextInt();
			TreeMap<Double,Integer> map = new TreeMap<Double, Integer>();
			for(int i=0;i<N;i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				map.put((double)((x - 1) * MAX + y), 0);
			}
			System.out.println(calculate(map));
		}
	}
	
	/*
	 * 1. marking first
	 * 2. update map
	 * 3. do next sec 
	 */
	static int calculate(TreeMap<Double, Integer> map) {
		boolean found = true;
		int sec = 0;
		while(found) {
			ArrayList<Double> changes = new ArrayList<Double>();

			for(Map.Entry<Double, Integer> xentry : map.entrySet()) {
				double x = xentry.getKey();
				//  C 1
				// 4 2
				//  3
				// compare C 1
				if (map.get(x + 2) != null && map.get(x + 1) == null) {
					changes.add(x + 1);
				}
				// compare C 2
				if (map.get(x + MAX + 1) != null) {
					if(map.get(x + 1) == null) {
						changes.add(x + 1);
					}
					if(map.get(x + MAX) == null) {
						changes.add(x + MAX);
					}
					
				}
				// compare C 4
				if (map.get(x + MAX - 1) != null) {
					if(map.get(x - 1) == null) {
						changes.add(x - 1);
					}
					if(map.get(x + MAX) == null) {
						changes.add(x + MAX);
					}
				}
				// compare C 3
				if (map.get(x + MAX + MAX) != null && map.get(x + MAX) == null) {
					changes.add(x + MAX);
				}
			}
			//print(map);
			if (changes.size() == 0) {
				found = false;
				break;
			}
			for(int i=0;i<changes.size();i++) {
				double point = changes.get(i);
				map.put(point, sec+1);
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
