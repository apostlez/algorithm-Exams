import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_4 {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
            //out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".out"));
			out = new BufferedWriter(new FileWriter("input.out"));
            start("Set2.in");
			//start("4.in");
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
			ArrayList<int[]> list = new ArrayList<int[]>();
			for(int i=0;i<N;i++) {
				int x = sc.nextInt();
				int y_b = sc.nextInt();
				int y_t = sc.nextInt();
				list.add(new int[] {x, y_b, y_t});
			}
			Collections.sort(list, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					if(o1[0] > o2[0]) return 1;
					else return -1;
				}
			});
/*			for(int[] t : list) {
				System.out.println(Arrays.toString(t));
			}
*/
			double result = calculate(list) % 1000000007.0;
			//System.out.println(result);
			System.out.printf("%.0f\n", result);
			out.write(String.format("%.0f", result));
			out.newLine();
		}
	}
	
	static int TOP = 2;
	static int BOT = 1;
	static double calculate(ArrayList<int[]> list) {
		if(list.size() == 1) {
			return 2;
		}
		double ret = 0;
		
		double tmp = jumpToNew(list); 
		if(tmp > 0) return tmp;
		System.out.println("go");

		for(int i=1;i<list.size();i++) {
			ret += jumpTo(list, 0, i, TOP, TOP);
			ret += jumpTo(list, 0, i, TOP, BOT);
			ret += jumpTo(list, 0, i, BOT, TOP);
			ret += jumpTo(list, 0, i, BOT, BOT);
		}
		return ret;
	}
	
	static double jumpToNew(ArrayList<int[]> list) {
		if(list.size() < 4) return 0;
		// Xn = 4Xn_1 - 3Xn_2 (n>4, X2=4, X3=12)
		int x0 = list.get(0)[0];
		int t = list.get(0)[TOP];
		int b = list.get(0)[BOT];
		int x1 = list.get(1)[0];
		int t1 = list.get(1)[TOP];
		int b1 = list.get(1)[BOT];
		double a_t0 = (double) (t1 - t) / (x1 - x0);
		double a_b0 = (double) (b1 - b) / (x1 - x0);

		double x = 0;
		double x_1 = 12;
		double x_2 = 4;
		for(int z = 1;z<4;z++) {
			x1 = list.get(z)[0];
			t1 = list.get(z)[TOP];
			b1 = list.get(z)[BOT];
			double a_t = (double) (t1 - t) / (x1 - x0);
			double a_b = (double) (b1 - b) / (x1 - x0);
			if(a_t != a_t0 || a_b != a_b0) {
				return 0;
			}
		}
		for(int z = 3;z<list.size();z++) {
			x1 = list.get(z)[0];
			t1 = list.get(z)[TOP];
			b1 = list.get(z)[BOT];
			double a_t = (double) (t1 - t) / (x1 - x0);
			double a_b = (double) (b1 - b) / (x1 - x0);
			if(a_t == a_t0 && a_b == a_b0) {
				x = 4 * x_1 - 3 * x_2;
				x_2 = x_1;
				x_1 = x;
			} else {
				return 0;
			}
		}
		return x;
	}

	
	
	static double jumpTo(ArrayList<int[]> list, int start, int target, int isTop, int toTop) {
		double ret = 0;
		int x0 = list.get(start)[0];
		int y0 = list.get(start)[isTop];
		int x1 = list.get(target)[0];
		int y1 = list.get(target)[toTop];
		double a0 = (double) (y1 - y0) / (x1 - x0);

		// is able to reach to target
		for(int i=start+1;i<target;i++) {
			if(y0 == y1) {
				if(y0 < list.get(i)[BOT])
					return 0;
				if(y0 > list.get(i)[TOP])
					return 0;
			} else { // 기울기 비교
				int x = list.get(i)[0];
				int y_t = list.get(i)[TOP];
				int y_b = list.get(i)[BOT];
				double a_t = (double) (y_t - y0) / (x - x0);
				if(a_t < a0)
					return 0;
				double a_b = (double) (y_b - y0) / (x - x0);
				if(a0 < a_b )
					return 0;
			}
		}
		//System.out.println(start + " " + ((isTop == TOP) ? "top" : "bottom") + " to " + target + " " + ((toTop == TOP) ? "top" : "bottom"));
		// goal
		if(target >= list.size()-1) {
			return 1;
		}
		// if able to jump from start isTop to target toTop, go next jump
		// try to jump next
		for(int i=target+1;i<list.size();i++) {
			ret += jumpTo(list, target, i, toTop, TOP);
			ret += jumpTo(list, target, i, toTop, BOT);
		}
		return ret;
	}
	
}
