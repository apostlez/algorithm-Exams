package codejam2018_1st;
import java.util.*;

public class Problem_2{
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int m = sc.nextInt();
		int a[] = new int[n];
		int op[] = new int[m];
		int index = k;
		for (int i=0;i<n;i++) {
			a[i] = sc.nextInt();
		}
		for (int i=0;i<m;i++) {
			op[i] = sc.nextInt();
			int o = op[i];
			if(o > 0 && index <= o) {
				int loc = index;
				int changes = o - 2 * loc + 1;
				index += changes;
			} else if(o < 0 && index >= (n+o+1)) {
				int loc = index - (n+o);
				int changes = (o * -1) - 2 * loc + 1;
				index += changes;
			}
		}
		System.out.println(index);
	}
}
