package codejam2018_1st;
import java.util.*;

public class Problem_4_timeout2 {
	static class Road {
		int start, end, C, T;
		int lastCost = 0;
		int lastP = 0;
		
		Road(int a, int b, int c, int t) {
			start = a;
			end = b;
			C = c;
			T = t;
		}
		int getOther(int a) {
			return a == start ? end : start;
		}

		int cost(int p) {
			if(lastP == p) return lastCost;
			int c = 0;
			if (p > T) {
				int tmp = (p - T);
				c = C * tmp * tmp;
			}
			lastP = p;
			lastCost = c;
			return c;
		}
		
		void print() {
			System.out.print(start + " " + end + "");
		}
	};
	
	static int max_p = 0;
	static int n = 0, m = 0, k = 0;
	static ArrayList<Road>[] map = new ArrayList[100001];
	static int[] visited = new int[100001];
	static ArrayList<Road> course = new ArrayList<Road>(); 

	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		for (int i=0;i<m;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int t = sc.nextInt();
			if(map[a] == null) {
				map[a] = new ArrayList<Road>();
			}
			Road r = new Road(a, b, c, t);
			map[a].add(r);
			if(map[b] == null) {
				map[b] = new ArrayList<Road>();
			}
			map[b].add(r);
		}
		//System.out.println(System.currentTimeMillis());
		search(1, course);
		System.out.println(max_p);
		//System.out.println(System.currentTimeMillis());
	}
	
	public static int sumCosts(ArrayList<Road> course, int p) {
		int sum = 0;
		for(int i=0;i<course.size();i++) {
			sum += course.get(i).cost(p);
			if(sum > k) return 0;
		}
		return sum;
	}
	
	public static int search(int node, ArrayList<Road> course) {
/*		if(visited[node] != 0) {
			//System.out.println("visited:" + node);
			return 0;
		}
*/		if(node == n) {
			//course.forEach((e) -> e.print());System.out.println();
			for(int p = max_p + 1;/* sum <= k */;p++) {
				int sum = sumCosts(course, p);
				if(sum == 0) return 0;
				max_p = p;
			}
		} else {
			// for performance
			if(max_p > 0) {
				if(sumCosts(course, max_p + 1) == 0) return 0;
			}

			for(int i=0;i<map[node].size();i++) {
				Road tmp = map[node].get(i);
				int end = tmp.getOther(node);
				if(visited[end] != 1) {
					course.add(tmp);
					visited[node] = 1;
					search(end, course);
					course.remove(course.size()-1);
					visited[node] = 0;
				}
			}
		}
		return 0;
	}
}


//dices.forEach(System.out::println);
