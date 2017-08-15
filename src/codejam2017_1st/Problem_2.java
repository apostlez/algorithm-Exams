package codejam2017_1st;


import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem_2 {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
			String filename = "Set3.in";
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
			int n = sc.nextInt();
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0; i<n;i++) {
				int p = sc.nextInt();
				int loc = binaryInsert(list, 0, list.size()-1, p);
				list.add(loc,p);
			}
			//printArray(list);
			
			int result = calculate(list, n);
			System.out.println(result);
			out.write(String.valueOf(result));
			out.newLine();
		}
	}
	
	static int calculate(ArrayList<Integer> list, int n) {
		int ret = 0;
		if(n == 1) return ret;
		if(n == 2) {
			int dist = (list.get(1) - list.get(0));
			return (dist >>> 1) + (dist & 1);
		}
		for (int i = 0; i < n-2 ; i++) {
			int dist = (list.get(i+2) - list.get(i));
			int bit = (dist & 1);
			int r = ((list.get(i+2) - list.get(i)) >>> 1) + bit;
			if(ret < r)
				ret = r;
		}
		return ret;
	}
	
	public static int binaryInsert(ArrayList<Integer> array, int fromIndex, int toIndex, int key) {
	    if (toIndex < fromIndex)
	        //return -1; // key not found.
	    	return fromIndex;
	    int mid = (toIndex+fromIndex) >>> 1;
	    if (array.get(mid) > key)
	        return binaryInsert(array, fromIndex, mid-1, key);
	    else if (array.get(mid) < key)
	        return binaryInsert(array, mid+1, toIndex, key);
	    else
	        return mid; // duplicated value
	}
	
	static void printArray(ArrayList<Integer> list) {
		for(int i=0;i < list.size() - 1;i++) {
			System.out.print(list.get(i) + ", ");
		}
		System.out.println(list.get(list.size()-1));
	}

}
