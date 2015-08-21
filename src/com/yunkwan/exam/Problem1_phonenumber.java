package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// 114
// 1596

/* dist
 * x 0 1 2 3 4 5 6 7 8 9
 * 0 0 3 3 3 2 2 2 1 1 1 
 * 1 3 0 1 2 1 1 2 2 2 2
 * 2 3 1 0 1 1 1 1 2 2 2
 * 3 3 2 1 0 2 1 1 2 2 2
 * 4 2 1 1 2 0 1 2 1 1 2
 * 5 2 1 1 1 1 0 1 1 1 1
 * 6 2 2 1 1 2 1 0 2 1 1
 * 7 1 2 2 2 1 1 2 0 1 2
 * 8 1 2 2 2 1 1 1 1 0 1
 * 9 1 2 2 2 2 1 1 2 1 0 
 */

/* n0 n1 ... n-2 n-1 n
 * b1. n-1 = n -> 0
 * b2. dist(n-2, n-1) < 1 & dist(n-1,n)<1 & dir(n-2, n-1) = dir(n-1, n) -> 1
 * b3. d(n-1, n) < 1 -> 2
 * b4. -> 3
 * 
 * a1. n0 = n1 -> 0
 * a2. dist(n0, n1)<1 -> 1
 * a3 -> 2
 */


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
        Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("Problem1.txt")));
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
        
        for(int i=1;i<str.length;i++) {
            if(str[i-1] != str[i]) {
                int dist = getDistance(str[i-1]-'0', str[i]-'0');
                if(dist <= 2) { // b2 or b3 or a2
                    if(prev_distance <= 2) {
                        if((DIRECTION[0]-prev_dir[0] + DIRECTION[1]-prev_dir[1]) == 0) {
                            ret += 1; // b2
                        } else {
                            ret += 2; // b3
                        }
                    } else {
                        if(prev_distance == 100) ret--; // a2
                        ret += 2; // b3
                    }
                } else { // b4 or a3
                    if(prev_distance == 100) ret--; // a3
                    ret += 3; // b4
                }
                prev_distance = dist;
                prev_dir = DIRECTION;
            } else { // b1, a1
                // prev == next
                prev_distance = 0;
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
            //System.out.println(str[i] + "\t:\t" + ret);
        }
        return min_number;
    }
}