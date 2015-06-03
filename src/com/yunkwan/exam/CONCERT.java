package com.yunkwan.exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CONCERT {
//public class Main {

	public static void main(String[] args) throws FileNotFoundException {
        //Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new BufferedReader(new FileReader("CONCERT.txt")));
        int cases = sc.nextInt();
        while(cases-- > 0) {
            int N = sc.nextInt();
            int VS = sc.nextInt();
            int VM = sc.nextInt();
            ArrayList<Integer> vn = new ArrayList<Integer>();
            for(int i = 0;i < N; i++) {
            	vn.add(sc.nextInt());
            }
            System.out.println(getMaxVolume(N, VS, VM, vn));
            
        }
    }

    private static int getMaxVolume(int n, int vs, int vm, ArrayList<Integer> vn) {
    	int plus = 0, minus = 0;
    	if(n == 0 || vs < 0) {
    		return vs;
    	}
    	plus = vs + vn.get(vn.size()-n);
    	if(plus > vm) {
    		plus = -1;
    	}
    	minus = vs - vn.get(vn.size()-n);
   		plus = getMaxVolume(--n, plus, vm, vn);
   		minus = getMaxVolume(n, minus, vm, vn);
    	return plus > minus ? plus : minus;
    }
}
