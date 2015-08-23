package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem4_astrorescue {
	
    public static void main(String args[]) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("problem4.in")));
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int N = sc.nextInt()-1;
            int K = sc.nextInt();
            for (int i = 0; i < N; i++) {
            	edges.add(sc.nextInt());
            	edges.add(sc.nextInt());
            }
            for (int i = 0; i < K; i++) {
                System.out.println(calculate(N, sc.nextInt(), sc.nextInt()));
            }
            edges.clear();
        }
    }
    
    public static ArrayList<Integer> edges = new ArrayList<Integer>(); 
    
    public static ArrayList<Integer> findPath(int n, ArrayList<Integer> path, int current, int l) {
    	ArrayList<Integer> ret = null;
		path.add(0, current);
    	for (int i = 0; i < n; i++) {
    		int a = edges.get(i*2);
    		int b = edges.get(i*2+1);
    		if(a == current && !path.contains(b)) {
    			if(l == b) {
    				path.add(0, l);
    				return path; // found
    			}
    			ret = findPath(n, path, b, l);
    		}
    		else if(b == current && !path.contains(a)) {
    			if(l == a) {
    				path.add(0, l);
    				return path; // found
    			}
    			ret = findPath(n, path, a, l);
    		}
    		if(ret != null) return ret;
    	}
    	path.remove(0);
    	return ret;
    }
    
    public static int calculate(int n, int a, int b) {
    	ArrayList<Integer> path = new ArrayList<Integer>();
    	path = findPath(n, path, a, b);
    	//print(path);
    	int ret = (path.size()+1)/2-1;
    	return path.get(ret);
    }
    
    public static void print(ArrayList<Integer> path) {
    	for(int i=0;i<path.size();i++) {
    		System.out.print(path.get(i) + " ");
    	}
    	System.out.println(":" + path.size());
    }

}
