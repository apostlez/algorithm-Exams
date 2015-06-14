package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CLOCKSYNC {
	
	/*
switches connected clocks:
0	0, 1, 2
1	3, 7, 9, 11
2	4, 10, 14, 15
3	0, 4, 5, 6, 7
4	6, 7, 8, 10, 12
5	0, 2, 14, 15
6	3, 14, 15
7	4, 5, 7, 14, 15
8	1, 2, 3, 4, 5
9	3, 4, 5, 9, 13
	 */
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
			System.out.println(calculate(clocks));
		}
	}

	private static int calculate(int[] clocks) {
		int eval = 0, eval_priv = 0;
		int ret = 0;
		eval_priv = checksum(clocks);
		if(eval_priv == 192) {
			System.out.println("find!");
			return 0;
		}
		for(int i = 0; i < 10; i++) {
			eval = checksumNext(i, clocks);
			if(eval >= eval_priv) {
				print(i, clocks);
				eval_priv = eval;
				ret = calculate(pushSwitch(i, clocks));
				if( ret >= 0) {
					return ++ret;
				}
			}
		}
		return -1;
	}
	
	private static int checksum(int[] clocks) {
		int sum = 0; 
		for(int i = 0; i < 16; i++) {
			sum += clocks[i];
		}
		return sum;
	}

	private static int checksumNext(int switchNum, int[] clocks) {
		int sum = 0; 
		for(int i = 0; i < 16; i++) {
			sum += clocks[i];
		}
		for(int i = 0; i < switches[switchNum].length; i++) {
			if((clocks[switches[switchNum][i]] + 3) > 12) {
				sum -= 9;
			} else {
				sum += 3;
			}
		}
		return sum;
	}

	private static int[] pushSwitch(int switchNum, int[] clocks) {
		int[] tmp = clocks.clone();
		for(int i = 0; i < switches[switchNum].length; i++) {
			tmp[switches[switchNum][i]] = tmp[switches[switchNum][i]] + 3;
			if(tmp[switches[switchNum][i]] > 12) {
				tmp[switches[switchNum][i]] = 3;
			}
		}
		return tmp;
	}
	
	private static void print(int sw, int [] clocks) {
		for(int i = 0; i < 16; i++) {
			System.out.print(clocks[i] + "\t");
		}
		System.out.println(" " + sw);
	}

}
