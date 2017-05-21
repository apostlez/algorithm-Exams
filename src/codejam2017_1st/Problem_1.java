package codejam2017_1st;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;

public class Problem_1 {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
            out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".out"));
			//out = new BufferedWriter(new FileWriter("input.out"));
            //start("Set3.in");
			start(args[0]);
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	static void start(String filename) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			int D = sc.nextInt();
			String strA = sc.next();
			String strB = sc.next();
			boolean result = false;
			if(strA.length() == strB.length()) {
				result = calculate(D, strA.toCharArray(), strB.toCharArray());
			}
			//System.out.println(result ? "O" : "X");
			out.write(String.valueOf(result ? "O" : "X"));
			out.newLine();
		}
	}
	
	static boolean calculate(int D, char[] a, char[] b) {
		boolean ret = false;
		boolean found = false;
		for (int i = 0; i < a.length ; i++) {
			found = false;
			int min = Math.min(i+D+1, a.length);
			int max = Math.max(0, i-D);
			for(int j = max; j <= i; j++) {
				if(a[i] == b[j]) {
					b[j] = ' ';
					found = true;
					break;
				}
			}
			if(!found) {
				for(int j = i+1; j < min; j++) {
					if(a[i] == b[j]) {
						b[j] = ' ';
						found = true;
						break;
					}
				}
				if(!found) {
					return false;
				}
			}
			//System.out.println(a);
			//System.out.println(b);
		}
		return true;
	}
}
