package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QUADTREE {

	public static void main(String args[]) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("QUADTREE.txt")));
		int cases = sc.nextInt();
		while (cases-- > 0) {
			String line = sc.next();
			System.out.println(start(line));
			index = 0;
		}
	}
	
	public static int index = 0;
	public static String start(String line) {
		String lt, rt, lb, rb; 
		// 1 2 3 4 -> 3 4 1 2
		char c = line.charAt(index++); 
		if(c != 'x') {
			return String.valueOf(c);
		} else {
			lt = start(line);
			rt = start(line);
			lb = start(line);
			rb = start(line);
			return "x" + lb + rb + lt + rt;
		}
	}
}
