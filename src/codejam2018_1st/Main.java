package codejam2018_1st;
import java.util.*;

public class Main {
	
	static int[] h = new int[100001];
	static int[] ho = new int[100001];
	static int[] hc = new int[100001];
	static int[] d = new int[100001];
	static ArrayList<Integer> h_order = new ArrayList<Integer>();
	static ArrayList<Integer> h_origin = new ArrayList<Integer>();
	static ArrayList<Integer> h_index = new ArrayList<Integer>();
	
	static void orderedInsert(int start, int end, int e, int index) {
		if(start == end) {
			h_order.add(start, e);
			h_index.add(start,index);
			return;
		}
		int mid = start + end >> 1;
		if(h_order.get(mid) == e) {
			orderedInsert(mid+1, end, e, index);
		} else if(h_order.get(mid) > e) {
			orderedInsert(start, mid, e, index);
		} else {
			orderedInsert(mid+1, end, e, index);
		}
	}
	
	static int getDistFromArrayList() {
		int sum = 0;
		for (int i = 1; i < n; i++) {
			//if(h_order.get(i) - h_order.get(i-1) <= 1) continue;
			int start = Math.min(h_index.get(i), h_index.get(i-1));
			int end = Math.max(h_index.get(i), h_index.get(i-1));
			d[i] = end - start - 1;
			for (int j = start+1; j < end; j++) {
				if(h_origin.get(j) <= h_order.get(i)) {
					d[i]--;
				}
			}
			sum += d[i];
		}
		return sum;
	}

	
	static int n = 0;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		for (int i=0;i<n;i++) {
			int t = sc.nextInt();
			h[i] = t;
			ho[i] = t;
			hc[i] = i;
			orderedInsert(0, i, t, i);
			h_origin.add(t);
		}
		int res = getDistFromArrayList();
		System.out.println(res + n + h_index.get(0));
/*
		h_order.forEach((a) -> { System.out.print(a + " ");});
		System.out.println();
		h_index.forEach((a) -> { System.out.print(a + " ");});
		System.out.println();
*/	

		// bubble sort
		//sort();

		// quick sort
		//sort(0, n-1);
/*		
		for(int i=0;i<n;i++) {
			System.out.print(h[i] + " ");
		}
		System.out.println();
		for(int i=0;i<n;i++) {
			System.out.print(hc[i] + " ");
		}
		System.out.println();
*/
//		int res = getD();
//		System.out.println(res + n + hc[0]);

/*		for(int i=0;i<n;i++) {
			System.out.print(d[i] + " ");
		}
		System.out.println();
*/
	}
	
	static int partition(int low, int high)
    {
        int pivot = h[high]; 
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (h[j] <= pivot)
            {
                i++;
                swap(i, j);
            }
        }
        swap(i+1, high);
        return i+1;
    }
 
    static void sort(int low, int high)
    {
        if (low < high)
        {
            int pi = partition(low, high);
            sort(low, pi-1);
            sort(pi+1, high);
        }
    }

    static void swap(int i, int j) {
		int temp = h[i];
		h[i] = h[j];
		h[j] = temp;
		temp = hc[i];
		hc[i] = hc[j];
		hc[j] = temp;
    }
	
	static int sort() {
        int temp = 0;
		for (int i = 0; i < n; i++) {
	        boolean isSwaped = false;
			for (int j = 1; j < (n - i); j++) {
				if ((h[j - 1] > h[j])) {
					isSwaped = true;
					swap(j-1, j);
				} else if (h[j - 1] == h[j] && j >= 2) {
					if (Math.abs(hc[j - 2] - hc[j - 1]) > Math.abs(hc[j - 2] - hc[j])) {
						swap(j-1, j);
						isSwaped = true;
					}
				}
			}
			if(!isSwaped) break;
		}
		return 0;
	}
	
	static int getD() {
		int sum = 0;
		for (int i = 1; i < n; i++) {
			int start = Math.min(hc[i], hc[i-1]);
			int end = Math.max(hc[i], hc[i-1]);
			//d[i] = Math.abs(hc[i] - hc[i-1]) - 1;
			d[i] = end - start - 1;
			for (int j = start+1; j < end; j++) {
				//System.out.println(ho[j] + " " + h[i]);
				if(h[i] - h[i-1] == 1) break;
				//System.out.println();
				if(ho[j] <= h[i]) {
					d[i]--;
				}
			}
			sum += d[i];
		}
		return sum;
	}
}

