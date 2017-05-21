package codejam2016_2nd;

import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem_2 {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
            out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".out"));
			start(args[0]);
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	static int map[][];
	static int price[][];
	
	static void start(String filename) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			map = new int[5001][5001];
			price = new int[5001][5001];
			int width = sc.nextInt();
			for(int i=0;i<width-1;i++) {
				price[i][0] = sc.nextInt();
				map[i][0] = sc.nextInt();
			}
			int index = sc.nextInt();
			int column = sc.nextInt();
			for(int i=1;i<column+1;i++) {
				map[index-1][i] = sc.nextInt();
				price[index-1][i] = sc.nextInt();
			}
/*
			for(int j=0;j<column+1;j++) {
				for(int i=0;i<width;i++) {
					//System.out.print(price[i][j] + "\t");
					System.out.print(price[i][j] + ":" + map[i][j] + "\t");
				}
				System.out.println();
			}
*/			
			int result = calculate();
			System.out.println(result);
			out.write(String.valueOf(result));
			out.newLine();
		}
	}
	
	static int calculate() {
		int x = 0, y = 0;
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point(price[x][y], 0));
		do {
			// go below
			if(map[x][y+1] > 0) {
				int cp = list.get(0).x;
				int min = cp > price[x][y] ? price[x][y] : cp;
				list.addAll(goDown(x, y+1, min, list.get(0).y));
			}
			// go next
			for(int i=0;i<list.size();i++) {
				int cp = list.get(i).x;
				int min = cp > price[x][y] ? price[x][y] : cp;
				list.get(i).x = min;
				list.get(i).y = list.get(i).y + min * map[x][y];
				//System.out.println("next\t" + x + ":" + y + ":" + list.get(i).y);
			}
		}
		while(map[++x][y] > 0);
		// find minimum
		int ret = list.get(0).y;
		for(int i=0;i<list.size()-1;i++) {
			ret = Math.min(ret, list.get(i+1).y);
		}
		return ret;
	}
	
	static ArrayList<Point> goDown(int x, int y, int pr, int dist) {
		//System.out.println("goDown\t" + x + ":" + y + ":" + dist);
		ArrayList<Point> list = new ArrayList<Point>();
		if(price[x][y] == 0) {
			return list;
		}
		int gas = dist + pr * map[x][y];
		Point p = new Point(pr, gas);
		list.add(p);
		// get each of below
		int min = pr > price[x][y] ? price[x][y] : pr;
		if(min != 1) {
			list.addAll(goDown(x, y+1, min, gas));
		}
		// go up
		for(int i=0;i<list.size();i++) {
			int min2 = list.get(i).x > price[x][y] ? price[x][y] : list.get(i).x;
			list.get(i).x = min2;
			list.get(i).y = list.get(i).y + min2 * map[x][y];
			//System.out.println("list.get(i).y = min2 * map[x][y];" + list.get(i).y + " " + min2 + " " + map[x][y]);
		}
		return list;
	}
	
}
