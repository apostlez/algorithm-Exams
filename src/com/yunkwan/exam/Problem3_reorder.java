package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// 8
// 5

/*
 */

public class Problem3_reorder {

    public static void main(String args[]) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("Problem3.txt")));
        int tc = sc.nextInt();
        while (tc-- > 0) {
            n = sc.nextInt();
            m = sc.nextInt();
			for (int i = 0; i < m * 2; i++) {
				pairs.add(sc.nextInt());
			}
            System.out.println(Math.min(calcurate(new int[n], 0, 1, n, true), calcurate(new int[n], 0, 1, n, false)));
            pairs.clear();
        }
    }
    
    public static int n = 0;
    public static int m = 0;
    public static ArrayList<Integer> pairs = new ArrayList<Integer>();
    
    public static int eval(int[] newline) {
    	int sum = 0;
    	for(int i=0;i<m;i++) {
    		int a = pairs.get(i*2), b = pairs.get(i*2+1);
    		int tmpA = -1;
    		for(int j=0;j<newline.length;j++) {
    			// find a
    			if(newline[j] == a) {
        			// get dist
    				if(tmpA != -1) {
    					sum += Math.abs(tmpA - j);
    					break;
    				}
    				tmpA = j;
    			}
    			// find b
    			if(newline[j] == b) {
        			// get dist
    				if(tmpA != -1) {
    					sum += Math.abs(tmpA - j);
    					break;
    				}
    				tmpA = j;
    			}
    		}
    	}
//    	print(newline);
//    	System.out.println(sum);
    	return sum;    	
    }
    
    public static int calcurate(int[] newline, int index, int first, int last, boolean isFirst) {
    	int retFirst = 0, retLast = 0;
    	if(isFirst) {
    		newline[index++] = first++;
    	} else {
    		newline[index++] = last--;
    	}
    	if(index == n-1) {
    		newline[index++] = first;
    		return eval(newline);
    	}
    	retFirst = calcurate(newline, index, first, last, true);
   		retLast = calcurate(newline, index, first, last, false);
    	return Math.min(retFirst, retLast);
    }
    
    public static void print(int[] newline) {
    	for(int i=0;i<newline.length;i++) {
    		System.out.print(newline[i] + " ");
    	}
    	System.out.println();
    }

}
