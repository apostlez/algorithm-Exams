package codejam2018_1st;
import java.util.*;

public class Problem_3{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Integer> dices = new ArrayList<Integer>();
		for (int i=0;i<n;i++) {
			dices.add(sc.nextInt());
		}
		dices.sort(Comparator.naturalOrder());
		int[] towers = new int[1000];
		int num = 1;
		towers[0] = 1;
		for ( int i = 1; i < n; i++) {
			if(dices.get(i) == 0) {
				towers[num++] = 1;
				continue;
			}
			boolean found = false;
			for(int j = 0; j < num; j++) {
				if(towers[j] <= dices.get(i) ) {
					towers[j]++;
					found = true;
					break;
				}
			}
			if(!found)
				towers[num++] = 1;
		}
		System.out.println(num);
	}
}


//dices.forEach(System.out::println);
