package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("Problem2.txt")));
    	//Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("problem2.in")));
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int W = sc.nextInt();
            ArrayList<Integer> wall = new ArrayList<Integer>(); 
            for (int i = 0; i < N*2; i++) {
            	wall.add(sc.nextInt());
            }
            maxRight = -2000000000;minLeft = 2000000000;
            getOutline(wall); // for fixed left and right
            System.out.println(calculate(N, K, W, wall) ? "YES":"NO");
        }
    }
    
    
    public static int minBot = 0;
    public static int minLeft = 0;
    public static int maxTop = 0;
    public static int maxRight = 0;
    
    public static void getOutline(ArrayList<Integer> wall) {
    	int size = wall.size();
    	maxTop = -2000000000;minBot = 2000000000;
    	for(int i = 0; i < size-1; i = i + 2) {
        	int x = wall.get(i);
        	int y = wall.get(i+1);
    		if(maxRight < x) maxRight = x;
    		if(minLeft > x) minLeft = x;
    		if(maxTop < y) maxTop = y;
    		if(minBot > y) minBot = y;
    	}
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
   		getOutline(wall);
       	for(int i = 0; i < N*2-1; i = i + 2) {
           	int x = wall.get(i);
           	int y = wall.get(i+1);
           	// check inner box
           	if((maxRight-W > x && minLeft+W < x ) && (maxTop-W > y && minBot + W < y )) {
           		if(K>0) {
           			return remove(i, N-1, K-1, W, wall);
           		}
           		//print(x,y,W,false);
           		return false;
           	}
           	// check outer box
           	if( (maxRight < x || maxTop < y  || minLeft > x || minBot > y)) {
            	if(K>0) {
            		return remove(i, N-1, K-1, W, wall);
            	}
            	//print(x,y,W,false);
            	return false;
       		}
       	}
       	return true;
    }

    public static void print (int x, int y, int W, boolean t) {
    	System.out.println(minLeft + "<" + x + "<" + maxRight + " and " + minBot + "<" + y + "<"  + maxTop + " margin:" + W + " " + t);
    	
    }

}
