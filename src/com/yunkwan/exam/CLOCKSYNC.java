package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CLOCKSYNC {
	
	final static int switches[][] = {
			{0, 1, 2},
			{3, 7, 9, 11},
			{4, 10, 14, 15},
			{0, 4, 5, 6, 7},
			{6, 7, 8, 10, 12},
			{0, 2, 14, 15},
			{3, 14, 15},
			{4, 5, 7, 14, 15},
			{1, 2, 3, 4, 5},
			{3, 4, 5, 9, 13}
	};
	
	public static void main (String args[]) throws FileNotFoundException {
		start();
	}
	
	static void start() throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("CLOCKSYNC.txt")));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			int clocks[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			for(int i = 0; i < 16; i++) {
				clocks[i] = sc.nextInt();
			}
			//System.out.println(new Date());
			System.out.println(calculate(0, clocks));
			//System.out.println(new Date());
			cases = 0;
		}
	}

	static int cases = 0;
	
	private static int calculate(int lastSwitch, int[] clocks) {
		cases++;
		int ret = -1;
		int min = 9999999;
		if(192 == checksum(clocks)) {
			return 0;
		}
		for(int j = lastSwitch; j < 9; j++) {
			for(int i = 0; i < 4; i++) {
				int tmp = -1;
				tmp = calculate(j+1, clocks);
				pushSwitch(j, clocks);
				if( tmp >= 0) {
					ret = Math.min(i + tmp, min);
				}
			}
		}
		return ret;
	}
	
	private static int checksum(int[] clocks) {
		int sum = 0; 
		for(int i = 0; i < 16; i++) {
			sum += clocks[i];
		}
		return sum;
	}

	private static void pushSwitch(int switchNum, int[] clocks) {
		for(int i = 0; i < switches[switchNum].length; i++) {
			int x = switches[switchNum][i];
			clocks[x] += 3;
			if(clocks[x] > 12) {
				clocks[x] = 3;
			}
		}
	}

	private static void print(int sw, int [] clocks) {
		for(int i = 0; i < 16; i++) {
			System.out.print(clocks[i] + "\t");
		}
		System.out.println("=" + checksum(clocks) + ","+ sw);
	}
}