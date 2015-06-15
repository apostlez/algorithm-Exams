package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
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
	
	static ArrayList<Integer> history = new ArrayList<Integer>(); 

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
			System.out.println(new Date());
			System.out.println(calculate(clocks) + " " + cases);
			System.out.println(new Date());
			printHistory();
			cases = 0;
			history.clear();
			level = 0;
		}
	}

	static int cases = 0;
	/*
Tue Jun 16 01:36:06 KST 2015
find!
2 31019795
Tue Jun 16 01:36:23 KST 2015
8  8  
Tue Jun 16 01:36:23 KST 2015
find!
9 43383132
Tue Jun 16 01:36:48 KST 2015
6  6  6  1  7  5  5  3  0  
	 */
	private static int calculate(int[] clocks) {
		cases++;
		int eval = 0;
		int ret = 0;
		eval = checksum(clocks);
		if(eval == 192) {
			//System.out.println("find!");
			return 0;
		}
		int i = 0;
		for(i = 0; clocks[i] == 12; i++);
		for(int j = 0; j < 10; j++) {
			for(int k = 0; k < switches[j].length; k++) {
				if(switches[j][k] == i) {
					int count = 0;
					for(int l=0;l<history.size();l++) {
						if(history.get(l) == j) {
							count++;
						}
					}
					if(count < 3) {
						history.add(0, j);
						ret = calculate(pushSwitch(j, clocks));
						if( ret >= 0) {
							return ++ret;
						}
					}
				}
			}
		}
		history.remove(0);
		return -1;
	}

	/*
Tue Jun 16 01:26:39 KST 2015
find!
2
8  8  
Tue Jun 16 01:26:56 KST 2015
Tue Jun 16 01:26:56 KST 2015
find!
9
6  6  6  1  7  5  5  3  0  
Tue Jun 16 01:27:20 KST 2015
	 */
	private static int calculate_brforeOpt(int[] clocks) {
		ArrayList<Integer> localHistory = new ArrayList<Integer>();
		int eval = 0;
		int ret = 0;
		eval = checksum(clocks);
		if(eval == 192) {
			System.out.println("find!");
			return 0;
		}
		// 1. find not "12"
		int i = 0;
		for(i = 0; clocks[i] == 12; i++);
		for(int j = 0; j < 10; j++) {
			for(int k = 0; k < switches[j].length; k++) {
				if(switches[j][k] == i) {
					localHistory.add(j);
				}
			}
		}
		for(int j = 0; j < localHistory.size(); j++) {
			int count = 0;
			for(int k=0;k<history.size();k++) {
				if(history.get(k) == localHistory.get(j)) {
					count++;
				}
			}
			if(count < 3) {
				history.add(0, localHistory.get(j));
				ret = calculate(pushSwitch(localHistory.get(j), clocks));
				if( ret >= 0) {
					return ++ret;
				}
			}
		}
		history.remove(0);
		return -1;
	}
	
	static int level = 0;
	private static int calculate_old(int[] clocks) {
		int eval = 0, eval_priv = 0;
		int ret = 0;
		eval_priv = checksum(clocks);
		if(eval_priv == 192) {
			System.out.println("find!");
			level--;
			return 0;
		}
		for(int i = 0; i < 10; i++) {
			//eval = checksumNext(i, clocks);
			int count = 0;
			for(int j=0;j<(history.size() > 4 ? 4 : history.size());j++) {
				if(history.get(j) == i) {
					count++;
				}
			}
			if(count > 3) {
				level--;
				return -1;
			}
			history.add(0,i);
			//if(eval >= eval_priv)
			{
				print(i, clocks);
				level++;
				eval_priv = eval;
				ret = calculate(pushSwitch(i, clocks));
				if( ret >= 0) {
					level--;
					return ++ret;
				}
			}
			history.remove(0);
		}
		level--;
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
/*		for(int i = level; i >= 0; i--) {
			System.out.print("\t");
		}
*/		for(int i = 0; i < 16; i++) {
			System.out.print(clocks[i] + "\t");
		}
		System.out.println("=" + checksum(clocks) + ","+ sw);
	}
	
	private static void printHistory() {
		for(int i = 0; i < history.size(); i++) {
			System.out.print(history.get(i) + "  ");
		}
		System.out.println();
	}

}
