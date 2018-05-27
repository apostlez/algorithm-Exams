package codejam2018_1st;
import java.util.Scanner;

public class Problem_1 {
	static int x[] = {  0,  1,  1,  1,  0, -1, -1, -1 };
	static int y[] = {  1,  1,  0, -1, -1, -1,  0,  1 };
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		char[] s = sc.nextLine().toCharArray();
		int n = sc.nextInt();
		int m = sc.nextInt();
		char[][] table = new char[n][m];
		for (int i=0;i<n;i++) {
			String tmp = sc.nextLine();
			if(tmp.length() == 0) {
				i--;continue;
			}
			table[i] = tmp.toCharArray();
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(s[0] != table[i][j]) {
					continue;
				}
				for(int k=0;k<8;k++) {
					int len = s.length - 1;
					if((i+x[k]*len) < 0 || (i+x[k]*len) >= n || (j+y[k]*len) < 0 || (j+y[k]*len) >=m ) {
						continue;
					}
					boolean found = true;
					for(int l=1;l<s.length;l++) {
						if(s[l] != table[i+x[k]*l][j+y[k]*l]) {
							found = false;
							break;
						}
					}
					if(found) {
						System.out.println("1");
						return;
					}
				}
			}
		}
		System.out.println("0");
	}
}
