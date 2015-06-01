package com.yunkwan.exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class LongestIncreasingSequence {

	public static void main(String[] args) {
		LongestIncreasingSequence lis = new LongestIncreasingSequence();
		lis.start("LongestIncreasingSequence.txt");

	}

	public void start(String filename) {
		try {
	      BufferedReader in = new BufferedReader(new FileReader(filename));
	      String str = in.readLine();
	      int nTestCases = Integer.parseInt(str);
	      int nNumber = 0;
	      for(int i = 0; i < nTestCases; i++) {
	    	  nNumber = Integer.parseInt(in.readLine());
	    	  str = in.readLine();
	    	  System.out.println(longestPath(nNumber, str));
	      }
	      in.close();
	    } catch (IOException e) {
	        System.err.println(e);
	    }

}
	
	private int[] parseArrayToInt(String[] str, int length) {
		int [] sequence = new int[length];
		for(int i = 0; i < length; i++) {
			sequence[i] = Integer.parseInt(str[i]);
		}
		return sequence;
	}
	
	private void print(int[] arr, int length) {
		for(int k = 0;k < length; k++) {
			System.out.print(arr[k] + " ");
		}
		System.out.println("= " + length);
	}
	
	private void print(ArrayList<Integer> sortedArray) {
		for (Integer integer : sortedArray) {
			System.out.print(integer + " ");
		}
		System.out.println("= " + sortedArray.size());
	}
	
	/*
	 * 3rd try
	 * 20 62 19 92 18 32 17 42 16 52 15 6 14 7 13 8 12 9 1 10
	 */
	private int longestPath(int number, String str) {
		Stack<Integer> next = new Stack<Integer>();
		int [] sequence = parseArrayToInt(str.split(" "), number);
		int index = 0;
		int max = 0;
		int startLoc = 0;
		
		while (startLoc < number) {
			if ( next.size() > 1 ) {
				index = next.pop()+1;
			} else {
				next.clear();
				next.push(startLoc++);
				index = startLoc;
			}
			for(int i = index; i < number; i++) {
				if( sequence[next.get(next.size()-1)] < sequence[i] ) {
					next.push(i);
				}
			}
			if( max < next.size() ) {
				max = next.size();
			}
		}
		return max;
	}
}