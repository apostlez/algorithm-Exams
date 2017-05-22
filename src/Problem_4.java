

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Problem_4 {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
			String filename = "Set1.in";
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
			int [] n = new int[5];
			for(int i=0;i<5;i++) {
				n[i] = sc.nextInt();
			}
			String result = calculate(n);
			System.out.println(result);
			out.write(String.valueOf(result));
			out.newLine();
		}
	}
	
	static String calculate(int[] n) {
		long unit = 0;
		int length = 0;
		for(int i=0;i<5;i++) {
			unit += n[i];
		}
		String sums[] = new String[9];
		int calc[] = new int[9];
		long index[] = new long[9];
		while(true) {
            length++;
		    for(int i=0;i < 9; i++) {
				// make number
		        sums[i] = String.valueOf(calc[i]);
			    sums[i] += i+1;
				calc[i] = Integer.valueOf(sums[i]);
                if(index[i] > 1000000007) {
                    index[i] %= 1000000007;
                }
                if(index[i] == 0)
                    index[i]--;
				index[i] = ((index[i]+1)*10) + calc[i] / unit * 5 - 1;
                if(calc[i] < unit) continue;
                calc[i] = (int) (calc[i] % unit);
				int left = calc[i];
				for(int k=0;k<5;k++) {
					if(left == 0) {
						long x = index[i] + k;
 						return (x % 1000000007) + " " + (int)(i+1) + "(" + length + ")";
					}					
					if(left >= n[k])
						left -= n[k];
					else
						break;
				}
			}
		}
	}
}
