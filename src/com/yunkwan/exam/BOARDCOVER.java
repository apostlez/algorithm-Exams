package com.yunkwan.exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.Scanner;

public class BOARDCOVER {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(Charset.forName("EUC-KR"));
		//BOARDCOVER m = new BOARDCOVER();
		//m.start();
	}

	public void start() throws FileNotFoundException {
		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new BufferedReader(new FileReader(
				"BOARDCOVER.txt")));
		int cases = sc.nextInt();
		while (cases-- > 0) {
			int size = sc.nextInt();
			for (int i = 0; i < size; i++) {
				//lengthes.add(sc.nextInt());
			}
			System.out.println();
		}
	}
	/* 0
	 * 2
	 * 1514
	 */
}
