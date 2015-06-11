package com.yunkwan.exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class BOARDCOVER {
	public static void main(String[] args) throws FileNotFoundException {
		BOARDCOVER m = new BOARDCOVER();
		m.start();
	}

	
	private int h;
	private int w;
	
	/*
	 * block[0] = ##, block[1]  #, block[2] ##, block[3] #
	 *            #            ##            #           ##
	 */
	private int[][] blocks = null;
	
	public void start() throws FileNotFoundException {
		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new BufferedReader(new FileReader(
				"BOARDCOVER.txt")));
		int cases = sc.nextInt();
		
		while (cases-- > 0) {
			h = sc.nextInt();
			w = sc.nextInt();
		
			blocks = new int[][]{{0, 1, w},{0, w-1, w},{0, 1, w+1},{0, w, w+1}};
			
			String map = "";
			for (int i = 0; i < h; i++) {
				map += sc.next();
			}
			if(checkValid(map)) {
				System.out.println(getSuccessfulTryCount(map));
			} else {
				System.out.println(0);
			}
		}
	}
	private boolean checkValid(String map2) {
		int validBlock = 0;
		char[] tmp = map2.toCharArray();
		for (int j = map2.length()-1; j >= 0 ; j--) {
			if(tmp[j] == '.') {
				validBlock++;
			}
		}
		return (validBlock%3 == 0)? true:false;
	}
	public int getSuccessfulTryCount(String map) {
		int max = 0;
		int startLoc = map.indexOf(".");
		if(startLoc == -1 ) {
			return 1;
		}
		for(int i=0;i<4;i++) {
			String ret = check(i, startLoc, map);
			if( ret != null) {
				max += getSuccessfulTryCount(ret);
			}
		}
		return max;
	}
	
	public String check(int block, int index, String map) {
		char[] tmp = map.toCharArray();
		for (int j = 0; j < 3; j++) {
			int loc = index + blocks[block][j];
			if( loc > h*w) {
				return null;
			}
			if(tmp[loc] == '#') {
				return null;
			}
			tmp[loc] = '#';
		}
		return String.valueOf(tmp);
	}
	
	public void print(String map) {
		for (int i = 0; i < h; i++) {
			System.out.println(map.substring(i*w, (i*w)+w));
		}
		System.out.println();
	}
}