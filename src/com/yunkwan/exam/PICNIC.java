package com.yunkwan.exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PICNIC {

	public static void main(String[] args) throws FileNotFoundException {
		PICNIC p = new PICNIC();
		p.start();
	}

	/*
	 * 1 3 4
	 */
	public void start() throws FileNotFoundException {
		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new BufferedReader(
				new FileReader("PICNIC.txt")));
		int cases = sc.nextInt();
		while (cases-- > 0) {
			int peoples = sc.nextInt();
			int pairsNumber = sc.nextInt();
			ArrayList<Integer> friends = new ArrayList<Integer>();
			for (int i = 0; i < pairsNumber * 2; i++) {
				friends.add(sc.nextInt());
			}
			System.out.println(getFriendsPairs(peoples, pairsNumber, friends));
		}
		sc.close();
	}

	public int getFriendsPairs(int peoples, int pairs, ArrayList<Integer> friends) {
		ArrayList<Integer> group = new ArrayList<Integer>();
		return getSubPairs(peoples, group, 0, pairs, friends);
	}
	
	public int getSubPairs(int peoples, ArrayList<Integer> groups, int person, int pairs, ArrayList<Integer> friends) {
		if(groups.size() == peoples) { 
			return 1;
		}
		int max = 0;
		for (int i = 0; i < pairs; i++) {
			if (friends.get(i * 2) == person || friends.get(i * 2 + 1) == person) {
				if(!groups.contains(friends.get(i * 2)) && !groups.contains(friends.get(i * 2 + 1))) {
					groups.add(0, friends.get(i * 2));
					groups.add(0, friends.get(i * 2 + 1));
					int p = person;
					do {
						p++;
					} while (groups.contains(p));
					max += getSubPairs(peoples, groups, p , pairs, friends);
					groups.remove(0);
					groups.remove(0);
				}
			}
		}
		return max;
	}
}
