package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// YES
// NO

/*
 * 1. calurate max size wall
 * 2. left_min and right_max are fixed
 * 3. only top_max and bot_min have exceptions K
 * 4. number N, exception K, depth W
 */

public class Problem2_Babylonia_wall {
    
    public static void main(String args[]) throws FileNotFoundException {
        //Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("Problem2.txt")));
    	Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("problem2.in")));
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int W = sc.nextInt();
            ArrayList<Integer> wall = new ArrayList<Integer>(); 
            for (int i = 0; i < N*2; i++) {
            	wall.add(sc.nextInt());
            }
            horizontal = getHorizontalOutline(wall); // for fixed left and right
            System.out.println(calculate(N, K, W, wall) ? "YES":"NO");
        }
    }
    
    public static int[] getHorizontalOutline(ArrayList<Integer> wall) {
    	int size = wall.size();
    	int[] ret = {2000000000, -1, -2000000000, -1};
    	for(int i = 0; i < size-1; i = i + 2) {
        	int x = wall.get(i);
    		if(ret[MAX] < x) { ret[MAX] = x; ret[MAX_INDEX] = i; }
    		if(ret[MIN] > x) { ret[MIN] = x; ret[MIN_INDEX] = i; }
    	}
    	return ret;
    }
    
    public static final int MAX = 2;
    public static final int MAX_INDEX = 3;
    public static final int MIN = 0;
    public static final int MIN_INDEX = 1;
    static int[] horizontal = {};
    
    public static int[] getVerticalOutline(ArrayList<Integer> wall) {
    	int size = wall.size();
    	int[] ret = {2000000000, -1, -2000000000, -1}; 
    	for(int i = 0; i < size-1; i = i + 2) {
        	int y = wall.get(i+1);
    		if(ret[MAX] < y) { ret[MAX] = y; ret[MAX_INDEX] = i; }
    		if(ret[MIN] > y) { ret[MIN] = y; ret[MIN_INDEX] = i; }
    	}
    	return ret;
    }
    
    public static boolean remove(int index, int N, int K, int W, ArrayList<Integer> wall) {
		int a = wall.remove(index);
		int b = wall.remove(index);
		boolean ret = false;
		ret = calculate(N, K, W, wall);
		wall.add(index, b);
		wall.add(index, a);
		return ret;
    }

    public static boolean calculate(int N, int K, int W, ArrayList<Integer> wall) {
    	// if k=0, check wall is correct ? true
    	int[] vertical = getVerticalOutline(wall);
       	for(int i = 0; i < N*2-1; i = i + 2) {
           	int x = wall.get(i);
           	int y = wall.get(i+1);
           	if((horizontal[MIN]+W < x && x < horizontal[MAX]-W) && (vertical[MIN]+W < y && y < vertical[MAX]-W)) {
           		// MAX or MIN is outbound
           		if(K>0) {
               		if(vertical[MAX]-y < y-vertical[MIN] ) {
               			return remove(vertical[MAX_INDEX], N-1, K-1, W, wall);
               		} else {
               			return remove(vertical[MIN_INDEX], N-1, K-1, W, wall);
               		}
           		}
           		return false;
           	}
       	}
/*       	for(int i = 0; i < N*2-1; i = i + 2) {
       		System.out.println(wall.get(i) + "\t" + wall.get(i+1));
       	}
*/       	return true;
    }
}
