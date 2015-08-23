package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Problem1_phonenumber {
    public static String min_number = "";
    public static int min_eval = 1024000;
    public static int[][] pad = {
        {3,1},
        {0,0}, {0,1}, {0,2},
        {1,0}, {1,1}, {1,2},
        {2,0}, {2,1}, {2,2}
        };
    public static int[] DIRECTION = {0,0};
    
    public static void main(String args[]) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("problem1.in")));
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int length = sc.nextInt();
            String str[] = new String[length];
            for (int i = 0; i < length; i++) {
                str[i]= sc.next();
            }
            System.out.println(calculate(str));
            min_eval = 1024000;
        }
    }
    
    public static int getDistance(int prev, int next) {
        int x = pad[prev][0] - pad[next][0];
        int y = pad[prev][1] - pad[next][1];
        DIRECTION[0] = x;
        DIRECTION[1] = y;
        return x*x+y*y;
    }

    public static int eval(char[] str) {
        int ret = 0;
        int prev_distance = 100;
        int[] prev_dir = {0,0};
        int dist = 0;
        
        if(str[0] != str[1]) { // a1
        	dist = getDistance(str[0]-'0', str[1]-'0');
        	if(dist <= 2) { // a2
        		ret += 1;
        	} else {
        		ret += 2; // a3
        	}
    		prev_distance = dist;
            prev_dir[0] = DIRECTION[0]; 
            prev_dir[1] = DIRECTION[1];
        }
        for(int i=2;i<str.length;i++) {
            if(str[i-1] != str[i]) {
                dist = getDistance(str[i-1]-'0', str[i]-'0');
                if(dist <= 2) { // b2 or b3
                    if(prev_distance <= 2) {
                        if((DIRECTION[0] == prev_dir[0] && DIRECTION[1] == prev_dir[1])) {
                            ret += 1; // b2
                        } else {
                            ret += 2; // b3
                        }
                    } else {
                        ret += 2; // b3
                    }
                } else { // b4
                    ret += 3;
                }
                prev_distance = dist;
                prev_dir[0] = DIRECTION[0]; 
                prev_dir[1] = DIRECTION[1];
            } else { // b1, a1
                prev_distance = 0;
                prev_dir[0] = 0; 
                prev_dir[1] = 0;
            }
        }
        return ret;
    }

    public static String calculate(String[] str) {
        for(int i=0;i<str.length;i++) {
            int ret = eval(str[i].toCharArray());
            if(min_eval > ret) {
                min_eval = ret;
                min_number = str[i];
            }
        }
        return min_number;
    }
}
