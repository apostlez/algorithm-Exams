package com.yunkwan.exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	private int[][] blocksH = new int[][]{{0, 0, 1},{0, 1, 1},{0, 0, 1},{0, 1, 1}};
	private int[][] blocksW = new int[][]{{0, 1, 0},{0, -1, 0},{0, 1, 1},{0, 0, 1}};
	
	public void start() throws FileNotFoundException {
		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new BufferedReader(new FileReader(
				"BOARDCOVER.txt")));
		int cases = sc.nextInt();
		
		while (cases-- > 0) {
			h = sc.nextInt();
			w = sc.nextInt();
		
			char[][] map = new char[h][];
			for (int i = 0; i < h; i++) {
				map[i] = sc.next().toCharArray();
			}
			if(checkValid(map)) {
				System.out.println(getSuccessfulTryCount(map));
			} else {
				System.out.println(0);
			}
		}
	}
	private boolean checkValid(char[][] map2) {
		int validBlock = 0;
		for (int i = h-1; i >= 0 ; i--) {
			for (int j = w-1; j >= 0 ; j--) {
				if(map2[i][j] == '.') {
					validBlock++;
				}
			}
		}
		return (validBlock%3 == 0)? true:false;
	}
	public int getSuccessfulTryCount(char[][] map) {
		int max = 0;
		int startLocH = -1, startLocW = -1;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w ; j++) {
				if(map[i][j] == '.') {
					startLocH = i;
					startLocW = j;
					i=h;
					break;
				}
			}
		}
		if(startLocH < 0 ) {
			return 1;
		}
		for(int i=0;i<4;i++) {
			char [][] ret = check(i, startLocH, startLocW, map);
			if( ret != null) {
				//print(ret);
				max += getSuccessfulTryCount(ret);
			}
		}
		return max;
	}
	
	public char[][] check(int block, int indexH, int indexW, char[][] map) {
		// performance improvement points
		char[][] tmp = new char[h][];
		for (int j = 0; j < h; j++) {
			tmp[j] = map[j].clone();
		}
		
		for (int j = 0; j < 3; j++) {
			int locH = indexH + blocksH[block][j];
			int locW = indexW + blocksW[block][j];
			if( locH >= h || locH < 0 || locW >= w || locW < 0) {
				return null;
			}
			if(tmp[locH][locW] == '#') {
				return null;
			}
			tmp[locH][locW] = '#';
		}
		return tmp;
	}
	
	public void print(char[][] map) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}