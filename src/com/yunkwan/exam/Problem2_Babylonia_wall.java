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
    
    public static int getMaxTopIndex(ArrayList<Integer> wall) {
    	int size = wall.size();
    	int max_val = -2000000000, max_index = -1;
    	for(int i = 1; i < size; i = i + 2) {
    		if(max_val < wall.get(i)) {
    			max_index = i-1;
    			max_val = wall.get(i);
    		}
    	}
    	return max_index;
    }
    
    public static int getMinBotIndex(ArrayList<Integer> wall) {
    	int size = wall.size();
    	int min_val = 2000000000, min_index = -1;
    	for(int i = 1; i < size; i = i + 2) {
    		if(min_val > wall.get(i)) {
    			min_index = i-1;
    			min_val = wall.get(i);
    		}
    	}
    	return min_index;
    }

    
    public static boolean calculate(int N, int K, int W, ArrayList<Integer> wall) {
    	boolean ret = false;
    	// if k=0, check wall is correct ? true
    	if(K == 0) {
    		getOutline(wall);
        	for(int i = 0; i < N*2-1; i = i + 2) {
            	int x = wall.get(i);
            	int y = wall.get(i+1);
            	// check inner box
            	if((maxRight-W > x && minLeft+W < x ) && (maxTop-W > y && minBot + W < y )) {
            		return false;
            	}else // check outer box
            		if( (maxRight < x || maxTop < y  || minLeft > x || minBot > y)) {
        			return false;
        		}
        	}
        	return true;
    	} else {
        	// remove maxTop from wall, calculate(N, K-1, W, wall') is correct ? true
    		ArrayList<Integer> wall_ = (ArrayList<Integer>) wall.clone();
    		int index = getMaxTopIndex(wall_);
    		wall_.remove(index);wall_.remove(index);
    		ret = calculate(N-1, K-1, W, wall_);
    		if(ret) return true;
        	// else, remove minBot from wall, calculate(N, K-1, W, wall'') is correct ? true
    		ArrayList<Integer> wall__ = (ArrayList<Integer>) wall.clone();
    		index = getMinBotIndex(wall__);
    		wall__.remove(index);wall__.remove(index);
    		ret = calculate(N-1, K-1, W, wall__);
    		if(ret) return true;
    	}
        return false;
    }
}
