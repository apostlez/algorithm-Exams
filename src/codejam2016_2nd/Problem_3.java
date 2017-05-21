package codejam2016_2nd;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Problem_3 {

    static BufferedWriter out;
    public static void main(String args[]) throws FileNotFoundException {
		try {
            out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".out"));
			start(args[0]);
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static class Edge {
    	public Edge (int a, int b, int dist) {
    		NodeA = a;
    		NodeB = b;
    		this.dist = dist;
    	}
    	private int NodeA = 0;
    	private int NodeB = 0;
    	private int dist = 0;
    	
    	public void set(int a, int b ,int dist) {
    		NodeA = a;
    		NodeB = b;
    		this.dist = dist;
    	}
    	public boolean isContain(int a) {
    		return (NodeA == a | NodeB == a);
    	}
    	public int getDist() {
    		return dist;
    	}
    	
    };

	static void start(String filename) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int N = sc.nextInt()-1;
            int M = sc.nextInt();
            int K = sc.nextInt();
            for (int i = 0; i < M; i++) {
            	int a = sc.nextInt();
            	int b = sc.nextInt();
                int dist = sc.nextInt();
            	ArrayList<Edge> edgeA = map.get(a);
            	ArrayList<Edge> edgeB = map.get(b);
            	if(edgeA == null) {
            		edgeA = new ArrayList<Edge>();
            	}
            	if(edgeB == null) {
            		edgeB = new ArrayList<Edge>();
            	}
        		edgeA.add(new Edge(a,b,dist));
        		edgeB.add(new Edge(b,a,dist));
            	map.put(a, edgeA);
            	map.put(b, edgeB);
            }
            for (int i = 0; i < K; i++) {
                //System.out.println(calculate(N, sc.nextInt(), sc.nextInt()));
                calculate(N, sc.nextInt(), sc.nextInt());
                output(min_path);
            }
            map.clear();
        }
    }
    
    
    public static HashMap<Integer, ArrayList<Edge>> map = new HashMap<Integer, ArrayList<Edge>>();
    
    public static ArrayList<Integer> _path = new ArrayList<Integer>();
    public static ArrayList<Integer> _dist = new ArrayList<Integer>();
    public static ArrayList<Point> min_path = new ArrayList<Point>();
    public static int min_dist = 0;
    public static ArrayList<Edge> currentEdges = null;

    
    public static void findPath(int n, ArrayList<Integer> path, int current, int l) {
    	currentEdges = map.get(current);
    	if(currentEdges ==null)
    		return;
		path.add(current);
    	for(int i = 0; i<currentEdges.size(); i++) {
    		Edge e = currentEdges.get(i);
    		if (!path.contains(e.NodeB)) {
        		if(e.isContain(l)) {
        			_dist.add(e.dist);
    				path.add(l);
    				// for debugging
    				//print(path);
    				int tmpdist = getDist(_dist);
    				if(min_dist == 0 || min_dist > tmpdist) {
    					min_dist = tmpdist; 
    					min_path.clear();
    					min_path.add(findCenter(path));
    					//print(path);
    				} else if (min_dist == tmpdist) {
    					min_path.add(findCenter(path));
    					//print(path);
    				}
    				//return path; // found
    				_dist.remove(_dist.size()-1);
    				path.remove(path.size()-1);
        		}
        		_dist.add(e.dist);
        		if(min_dist == 0 || min_dist >= getDist(_dist)) {
        			findPath(n, path, e.NodeB, l);
        			path.remove(path.size()-1);
        		}
        		_dist.remove(_dist.size()-1);
    		}
    	}
    	return;
    }
    
    public static int calculate(int n, int a, int b) {
        _path.clear();
        _dist.clear();
        min_path.clear();
        min_dist = 0;
        findPath(n, _path, a, b);
    	return 0;
    }
    
    // path should be like this: node (dist) node (dist) node (dist) node
    public static void print(ArrayList<Integer> path) {
    	int dist = 0;
    	for(int i=0;i<path.size()-1;i++) {
    		System.out.print(path.get(i) + " ");
    		System.out.print("(" + _dist.get(i) + ") ");
    		dist += _dist.get(i);
    	}
    	if(path.size()%2 > 0) {
    		System.out.println(path.get(path.size()-1) + ":" + dist);
    	} else {
    		System.out.println(":" + dist);
    	}
    	
    }
    
    public static int getDist(ArrayList<Integer> path) {
    	int dist = 0;
    	for(int i=0;i<_dist.size();i++) {
    		dist += _dist.get(i);
    	}
    	return dist;
    }
    
    public static Point findCenter(ArrayList<Integer> path) {
    	int dist = 0;
    	double center = 0;
    	Point ret = null;
    	center = (double)getDist(_dist)/(double)2;
    	for(int i=0;i<path.size();i++) {
    		dist += _dist.get(i);
    		if(center == dist) {
    			//System.out.print(path.get(i) + " " + path.get(i));
    			ret = new Point(path.get(i), path.get(i));
    			break;
    		} else if(center < dist) {
    			ret = new Point(path.get(i) < path.get(i+1) ? path.get(i) : path.get(i+1),
    					path.get(i) > path.get(i+1) ? path.get(i) : path.get(i+1));
    			//System.out.print(ret.x + " " + ret.y);
    			break;
    		}
    	}
    	//System.out.println();
    	return ret;
    }
    
    public static void output(ArrayList<Point> p) throws IOException {
    	Point a = p.get(0);
    	for(int i=0;i<p.size()-1;i++) {
    		if(p.get(i).x > p.get(i+1).x) {
    			a = p.get(i+1);    			
    		} else if(p.get(i).x == p.get(i+1).x && p.get(i).y > p.get(i+1).y) {
    			a = p.get(i+1);
    		}
    	}
    	System.out.println(a.x + " " + a.y);
		out.write(String.valueOf(a.x) + " " + String.valueOf(a.y));
		out.newLine();
    }
}
