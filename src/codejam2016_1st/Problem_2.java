package codejam2016_1st;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Problem_2 {

    static BufferedWriter out;
    
	public static void main(String[] args) {
		try {
		    out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".out"));
		    start(args[0]);
		    out.close();
			//start("problem_2_Set2.in");
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static int a[], b[];
	
	static void start(String filename) throws IOException {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			a = new int[100000];
			b = new int[100000];
			int n = sc.nextInt();
			for(int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
			}
			for(int i = 0; i < n; i++) {
				b[i] = sc.nextInt();
			}
			int result = compareF(a, b, n) | compareB(a, b, n);
			System.out.println(result);
			out.write(String.valueOf(result));
			out.newLine();
		}
	}
	
	static int compareF(int a[], int b[], int n) {
		int ret = 1;
		for(int k=0;k<n;k++) {
			ret = 1;
			for(int i=0,j=k;i<n;i++,j++) {
				if(a[i] != b[j>=n?j-n:j]) {
					ret = 0;
					break;
				}
			}
			if(ret == 1) {
				return 1;
			}
		}
		return 0;
	}
	
	static int compareB(int a[], int b[], int n) {
		int ret = 1;
		int r = 0, rr = 0;
		for(int k=0;k<n;k++) {
			ret = 1;
			for(int i=0,j=n-1-k;i<n;i++,j--) {
				r = b[j>-1?j:j+n];
				rr = b[j-1>-1?j-1:j+n-1];
				if(rr < 0) {
					r = Math.abs(r);
				} else {
					r = Math.abs(r) * -1;
				}
				if(a[i] != r) {
					ret = 0;
					break;
				}
			}
			if(ret == 1) {
				return 1;
			}
		}
		return 0;
	}
}
