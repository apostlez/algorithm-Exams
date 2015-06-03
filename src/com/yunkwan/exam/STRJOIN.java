package com.yunkwan.exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class STRJOIN {
//public class Main {
		ArrayList<Integer> lengthes = new ArrayList<Integer>();
		int size = 0;

		public static void main(String[] args) throws FileNotFoundException {
			STRJOIN m = new STRJOIN();
			m.start();
		}

		public void start() throws FileNotFoundException {
			// Scanner sc = new Scanner(System.in);
			Scanner sc = new Scanner(new BufferedReader(new FileReader(
					"STRJOIN.txt")));
			int cases = sc.nextInt();
			while (cases-- > 0) {
				size = sc.nextInt();
				for (int i = 0; i < size; i++) {
					lengthes.add(sc.nextInt());
				}
				System.out.println(getMinCost());
				lengthes.clear();
			}
		}

		public int getMinCost() {
			int sumCost = 0;
			do {
				int singleCost = 0;
				lengthes.sort(new AscComparator());
				//Collections.sort(lengthes);
				singleCost += lengthes.remove(0);
				singleCost += lengthes.remove(0);
				lengthes.add(singleCost);
				sumCost += singleCost;
			}while(lengthes.size() > 1);
			return sumCost;
		}
		
		public class AscComparator implements Comparator<Integer> {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 > o2 ? 1 : o1 < o2 ? -1 : 0;
			}
		}
	}
