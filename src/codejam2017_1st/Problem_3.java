package codejam2017_1st;


import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem_3 {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
			String filename = "Set3.in";
			//String filename = "test.in";
			if(args.length > 0) {
				filename = args[0];
			}
            out = new BufferedWriter(new FileWriter(filename.substring(0, filename.lastIndexOf('.')) + ".out"));
			start(filename);
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	static void start(String filename) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			boolean result = calculate(n, k);
			System.out.println(result ? "O" : "X");
			out.write(String.valueOf(result ? "O" : "X"));
			out.newLine();
		}
	}
	
	static boolean calculate(int n, int k) {
		boolean ret = false;
		for(int x=n/10; x>=0; x--) {
			int n_x = (n-10*x);
			for(int a=n_x/9; a>=0; a--) {
				int n_x_a = (n_x-9*a);
				for(int y=n_x_a/5; y>=0; y--) {
					int n_x_a_y = (n_x_a-5*y);
					for(int b=n_x_a_y/4; b>=0; b--) {
						int left_n = (n_x_a_y-(4*b));
						int left_k = k-(2*x)-(2*y)-(3*a)-(3*b);
						if(left_k == left_n) {
							//System.out.println(x+"*X(2)+"+a+"*IX(3)+"+y+"*V(2)+"+b+"*IV(3)+"+left_k+"*I");
							// match
							return true;
						} else if(left_n > left_k) {
							// lack of stick (k)
							break;
						} else {
							// overflow of stick (k)
						}
					}
				}
			}
			
		}
		return ret;
	}
	
}
