package codejam2016_1st;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

class Pair {
	int _node;
	int _dist;
	Pair(int node, int dist) {
		_node = node;
		_dist = dist;			
	}
	public int getDist() {
		return _dist;
	}
	public int getNode() {
		return _node;
	}
	public void setDist(int dist) {
		_dist = dist;
	}
}

class Comp implements Comparator<Pair>{
	@Override
	public int compare(Pair o1, Pair o2) {
		if(o1.getNode() < o2.getNode()) {
			return -1;
		} else if(o1.getNode() > o2.getNode()) {
			return 1;
		} else if(o1.getDist() < o2.getDist()) {
			return -1;
		}
		return 1;
	}
}

public class Problem_4 {

	public static void main(String[] args) {
		try {
			//start(args[0]);
			start("problem_4_Set1.in");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static ArrayList<Pair> pairList[];
	static ArrayList<Integer> addedCity;
	static int addedDist[];
	static int N;
	static int Q, M, sum1, sum2;
	static int dist[];
	
	static void start(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			addedCity = new ArrayList<Integer>();
			pairList = new ArrayList[1000000];
			addedDist = new int[1000000];
			sum1 = 0;
			sum2 = 0;
			
			N = sc.nextInt();
			for(int i=0;i<N;i++) {
				pairList[i] = new ArrayList<Pair>();
			}
			Q = sc.nextInt();
			for(int i=0; i<N-1; i++) {
				int t = sc.nextInt();
				int d = sc.nextInt();
				pairList[i+1].add(new Pair(t-1, d));
				pairList[t-1].add(new Pair(i+1, d));
			}
			// get M
			for(int i=1;i<N; i++) {
				if(pairList[i].size() == 1) {
					addedCity.add(i);
				}
			}
			M = addedCity.size();
			for(int i=0; i<M; i++) {
				int m = sc.nextInt();
				addedDist[addedCity.get(i)] = m;
			}
			ArrayList<Integer> from = new ArrayList<Integer>(); 
			ArrayList<Integer> to = new ArrayList<Integer>();
			for(int i=0; i<Q; i++) {
				from.add(sc.nextInt()-1);
				to.add(sc.nextInt()-1);
			}
			for(int i=0; i<Q; i++) {
				// need to ordering
				if(from.get(i) != to.get(i)) {
					getShortestDist(from.get(i), to.get(i));
				}
			}
			// update new road
			for(int i=0; i<M; i++) {
				boolean found = false;
				int m = addedCity.get(i);
				for(int j=0; j<pairList[0].size(); j++) {
					if(pairList[0].get(j).getNode() == m) {
						found = true;
						pairList[0].get(j).setDist(addedDist[addedCity.get(i)]);
					}
				}
				if(found == false) pairList[0].add(new Pair(m, addedDist[addedCity.get(i)]));
				found = false;
				for(int j=0; j<pairList[m].size(); j++) {
					if(pairList[m].get(j).getNode() == m) {
						found = true;
						pairList[m].get(j).setDist(addedDist[addedCity.get(i)]);
					}
				}
				if(found == false) pairList[m].add(new Pair(0, addedDist[addedCity.get(i)]));
			}
			for(int i=0; i<Q; i++) {
				// need to ordering
				if(from.get(i) != to.get(i)) {
					getShortestDistAdded(from.get(i), to.get(i));
				}
			}
			System.out.println(sum1 + " " + sum2);
		}
	}
	
	static void getShortestDist(int from, int to) {
		int visited[] = new int[1000000];
		dist = new int[1000000];
		int n = 0, d;
		Stack<Integer> path = new Stack<Integer>();
		
		int currentIndex = 0;
		path.push(from);
		visited[from] = 1;
		while(!path.isEmpty()) {
			// 1. add next ordering by dist
			currentIndex = path.pop();
			ArrayList<Pair> current = pairList[currentIndex];
			for(int j=0; j<current.size(); j++) {
				n = current.get(j).getNode();
				d = current.get(j).getDist();
				if (visited[n] == 0 || (dist[n] == 0 || dist[currentIndex] + d < dist[n])) {
					dist[n] = dist[currentIndex] + d;
					path.push(n);
					visited[n] = 1;
					//System.out.println("from:" + currentIndex + " to:" + n);
				}
			}
		}
		//System.out.println("sum1:" + dist[to]);
		sum1 += dist[to];
	}
	
	static void getShortestDistAdded(int from, int to) {
		int visited[] = new int[1000000];
		dist = new int[1000000];
		int n = 0, d;
		Stack<Integer> path = new Stack<Integer>();
		
		int currentIndex = 0;
		path.push(from);
		visited[from] = 1;
		while(!path.isEmpty()) {
			// 1. add next ordering by dist
			currentIndex = path.pop();
			ArrayList<Pair> current = pairList[currentIndex];
			for(int j=0; j<current.size(); j++) {
				n = current.get(j).getNode();
				d = current.get(j).getDist();
				if (visited[n] == 0 || (dist[n] == 0 || dist[currentIndex] + d < dist[n])) {
					dist[n] = dist[currentIndex] + d;
					path.push(n);
					visited[n] = 1;
					//System.out.println("visit2:" + n);
				}
			}
		}
		//System.out.println("sum2:" + dist[to]);
		sum2 += dist[to];
	}
}
