
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

public class Problem_5 {

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

    public static ArrayList<Point> men = new ArrayList<Point>();
    public static Integer entered[] = new Integer[500];
    public static ArrayList<Point> shelters = new ArrayList<Point>();
    public static Integer accom[] = new Integer[500];
    public static double max_d = 0;
    public static int N, M;

    
	static void start(String filename) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
        int tc = sc.nextInt();
        while (tc-- > 0) {
    		for(int i=0;i<500;i++) {
        		entered[i] = -1;
        		accom[i] = 0;
    		}
            N = sc.nextInt(); // man
            M = sc.nextInt(); // shelter
            for (int i = 0; i < N; i++) {
            	int a = sc.nextInt();
            	int b = sc.nextInt();
            	men.add(new Point(a, b));
            }
            for (int i = 0; i < M; i++) {
            	int a = sc.nextInt();
            	int b = sc.nextInt();
            	shelters.add(new Point(a, b));
            }
            for (int i = 0; i < M; i++) {
            	accom[i] = sc.nextInt();
            }
            max_d = sc.nextInt();
            //System.out.println("max_d=" + max_d*max_d);

            int ret = calculate();
            System.out.println(ret);
            out.write(String.valueOf(ret));
    		out.newLine();
    		
    		men.clear();
    		shelters.clear();
        }
    }
    
    public static int calculate() {
    	if(move(N)) return 1;
    	return 0;
    }

    public static boolean move(int remained) {
    	boolean ret = false;
    	if(remained == 0) return true;

    	int start = 0;
    	// �ѱ����� ���� �ִ� �༮���� �� ä������ ��������.
    	for(int i=0;i<N;i++) {
        	int num = 0;
        	int s = 0;
    		if(entered[i] == -1) {
        		for(int j=0;j<M;j++) {
            		if(accom[j] > 0 && getDist(men.get(i), shelters.get(j))) {
            			num++;
            			s = j;
            		}
        		}
        		if(num == 0) {
        			return false;
        		}
        		if(num == 1) {
        			entered[i] = s;
        			accom[s]--;
        			remained--;
        		}
    			if(start == 0) start = i;
    		}
    	}
    	//print();

    	for(int i=start;i<N;i++) {
    		int num = 0;
    		if(entered[i] == -1) {
        		for(int j=0;j<M;j++) {
            		if(accom[j] > 0 && getDist(men.get(i), shelters.get(j))) {
            			entered[i] = j;
            			accom[j]--;
            			//print();
            			ret = move(--remained);
            			if(ret == true) {
            				//print();
            				return true;
            			}
            			entered[i] = -1;
            			accom[j]++;
            			remained++;
            			num++;
            		}
        		}
       			return false;
    		}
    	}
    	return false;
    }
    
    public static boolean getDist(Point a, Point b) {
    	double x = Math.abs(b.x - a.x);
    	double y = Math.abs(b.y - a.y);
    	double dist = x + y;
    	if(max_d >= dist) 
    		return true; 
    	return false;
    }
    
    public static void print() {
    	for(int i=0;i<N;i++) {
    		System.out.print(entered[i] + " ");
    	}
    	System.out.println();
    }
}
