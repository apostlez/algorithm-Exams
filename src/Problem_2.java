import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem_2 {

	public static void main(String[] args) {
		try {
			start(args[0]);
			//start("problem_2_Set2.in");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static int a[], b[];
	
	static void start(String filename) throws FileNotFoundException {
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
			System.out.println(compareF(a, b, n) | compareB(a, b, n));
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
