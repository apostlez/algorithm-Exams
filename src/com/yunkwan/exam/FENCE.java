package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FENCE {
    public static int fences[] = null;

    public static void main(String args[]) throws FileNotFoundException {
	Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("FENCE.txt")));
	int tc = sc.nextInt();
	while (tc-- > 0) {
	    int fence_length = sc.nextInt();
	    int fences[] = new int[fence_length];
	    for (int i = 0; i < fence_length; i++) {
		fences[i] = sc.nextInt();
	    }
	    System.out.println(calculate(fences, 0, fence_length-1));
	}
    }
    public static int calculate(int fences[], int start, int end) {
	int width = end - start + 1;
	if(width < 1) {
	    return 0;
	}
	int maxArea = 0;
	int shortest = findShortest(fences, start, end);
	maxArea = fences[shortest] * width;
	int left = calculate(fences, start, shortest-1);
	int right = calculate(fences, shortest+1, end);
	System.out.println(shortest + ":" + left + "," + maxArea + "," + right);
	return Math.max(maxArea, Math.max(left, right));	
    }
    
    public static int findShortest(int fences[], int start, int end) {
	int shortest = start;
	for (int i = start; i < end; i++) {
	    if ( fences[shortest] > fences[i+1]) {
		shortest = i+1;
	    }
	}
	System.out.println("shortest fence:" + shortest);
	return shortest;
    }

}
