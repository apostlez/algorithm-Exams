package com.yunkwan.exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MATCHORDER {
//public class Main {
	ArrayList<Integer> russian = new ArrayList<Integer>();
	ArrayList<Integer> korean = new ArrayList<Integer>();
	int size = 0;

	public static void main(String[] args) throws FileNotFoundException {
		MATCHORDER m = new MATCHORDER();
		m.start();
	}

	public void start() throws FileNotFoundException {
		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new BufferedReader(new FileReader(
				"MATCHORDER.txt")));
		int cases = sc.nextInt();
		while (cases-- > 0) {
			size = sc.nextInt();
			for (int i = 0; i < size; i++) {
				russian.add(sc.nextInt());
			}
			for (int i = 0; i < size; i++) {
				korean.add(sc.nextInt());
			}
			System.out.println(getWins());
			russian.clear();
			korean.clear();
		}
	}

	public int getWins() {
		int wins = 0;
		korean.sort(new DescComparator());
		russian.sort(new DescComparator());
		for (int i = 0; i < size; i++) {
			if (russian.get(i) - korean.get(0) > 0) {
				korean.remove(korean.size() - 1);
			} else {
				korean.remove(0);
				wins++;
			}
		}
		return wins;
	}
	
	public class DescComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 > o2 ? -1 : o1 < o2 ? 1 : 0;
		}
	}

}
