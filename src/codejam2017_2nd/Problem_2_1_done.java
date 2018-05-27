package codejam2017_2nd;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Problem_2_1_done {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
            //out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".out"));
			out = new BufferedWriter(new FileWriter("input.out"));
            start("Set1.in");
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
			ArrayList<int[]> cord = new ArrayList<int[]>();
			for(int i=0;i<N;i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				cord.add(new int[] {x, y});
			}
			double result = calculate(cord);
			System.out.printf("%.0f\n", result);
			out.write(String.format("%.0f", result));
			out.newLine();
		}
	}
	
	static double calculate(ArrayList<int[]> cord) {
		double min = 1000000000;
		double thin = 1;
		for(int i=0;i<cord.size()-1;i++) {
			for(int j=i+1;j<cord.size();j++) {
				int [] first = cord.get(i);
				int [] second = cord.get(j);
				double t = getThin(first, second);
				if(thin >= t) {
					double a = Math.abs(first[0] - second[0]);
					double b= Math.abs(first[1] - second[1]);
					double x = (a + b);
					x *= 2;
					if(x < 0) {
						System.out.println(Arrays.toString(first) + Arrays.toString(second));
					}
					if(thin == t) {
						min = Math.min(min, x);
					} else {
						thin = t;
						min = x;
					}
				}
				
			}
		}
		return min;
	}
	
	static double getThin(int[] a, int[] b) {
		double h = Math.abs(a[0]-b[0]);
		double v = Math.abs(a[1]-b[1]);
		return Math.min(h, v) / Math.max(h, v);
	}
	
}
