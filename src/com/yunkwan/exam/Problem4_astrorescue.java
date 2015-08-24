package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Problem4_astrorescue {
	
    static class Edge {
    	public Edge (int a, int b) {
    		NodeA = a;
    		NodeB = b;
    	}
    	private int NodeA = 0;
    	private int NodeB = 0;
    	
    	public void set(int a, int b) {
    		NodeA = a;
    		NodeB = b;
    	}
    	
    	public boolean isContain(int a) {
    		return (NodeA == a | NodeB == a);
    	}
    	
    };

    public static void main(String args[]) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("problem4.in")));
    	//Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("Problem4.txt")));
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int N = sc.nextInt()-1;
            int K = sc.nextInt();
            for (int i = 0; i < N; i++) {
            	int a = sc.nextInt();
            	int b = sc.nextInt();
            	ArrayList<Edge> edgeA = map.get(a);
            	ArrayList<Edge> edgeB = map.get(b);
            	if(edgeA == null) {
            		edgeA = new ArrayList<Edge>();
            	}
            	if(edgeB == null) {
            		edgeB = new ArrayList<Edge>();
            	}
        		edgeA.add(new Edge(a,b));
        		edgeB.add(new Edge(b,a));
            	map.put(a, edgeA);
            	map.put(b, edgeB);
            }
            for (int i = 0; i < K; i++) {
                System.out.println(calculate(N, sc.nextInt(), sc.nextInt()));
            }
            map.clear();
        }
    }
    
    
    public static HashMap<Integer, ArrayList<Edge>> map = new HashMap<Integer, ArrayList<Edge>>();
    
    public static ArrayList<Integer> _path = new ArrayList<Integer>(500000);

    
    public static ArrayList<Integer> findPath(int n, ArrayList<Integer> path, int current, int l) {
    	ArrayList<Integer> ret = null;
    	ArrayList<Edge> currentEdges = map.get(current);
    	if(currentEdges ==null)
    		return ret;
    	for(int i = 0; i<currentEdges.size(); i++) {
    		Edge e = currentEdges.get(i);
    		if (!path.contains(e.NodeB)) {
        		if(e.isContain(l)) {
    				path.add(current);
    				path.add(l);
    				return path; // found
        		}
    			path.add(current);
    			ret = findPath(n, path, e.NodeB, l);
    			if(ret != null) return ret;
    			path.remove(path.size()-1);
    		}
    	}
    	return ret;
    }
    
    public static int calculate(int n, int a, int b) {
        _path.clear();
    	_path = findPath(n, _path, a, b);
    	//print(_path);
    	int ret = ((_path.size()+1) >> 1)-1;
    	return _path.get(ret);
    }
    
    public static void print(ArrayList<Integer> path) {
    	for(int i=0;i<path.size();i++) {
    		System.out.print(path.get(i) + " ");
    	}
    	System.out.println(":" + path.size());
    }

}
