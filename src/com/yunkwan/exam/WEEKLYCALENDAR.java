package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// https://algospot.com/judge/problem/read/WEEKLYCALENDAR

public class WEEKLYCALENDAR {
	
	static ArrayList<String> dayOfWeek = new ArrayList<String>();
	static int[] dayOfMonth = {31,28,31,30,31,30, 31,31,30,31,30,31};

    public static void main(String args[]) throws FileNotFoundException {
    	String[] d = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    	dayOfWeek.addAll(Arrays.asList(d));
    	
	    Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("test.txt")));
	    //Scanner sc = new Scanner(System.in);
	    int tc = sc.nextInt();
	    while (tc-- > 0) {
	        int month = sc.nextInt();
	        int date = sc.nextInt();
	        String day = sc.next();
	        calculate(month, date, day);
	    }
    }
    
    public static void calculate(int month, int date, String day) {
    	int d = dayOfWeek.indexOf(day);
    	for(int i=0;i<7;i++) {
    		int c = date - d + i > dayOfMonth[month-1] ? date - d + i - dayOfMonth[month-1] : date - d + i;
    		c = c <= 0 ? c + dayOfMonth[month-2 < 0 ? 11 : month-2] : c;
    		System.out.print(c + " ");
    	}
    	System.out.println();
    }
}
