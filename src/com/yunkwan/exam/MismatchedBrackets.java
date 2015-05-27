package com.yunkwan.exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MismatchedBrackets {
	
	public static void main(String[] args) {
		MismatchedBrackets mb = new MismatchedBrackets();
		mb.start("test.txt");
	}
	
	public void start(String filename) {
		try {
	      BufferedReader in = new BufferedReader(new FileReader(filename));
	      String str = in.readLine();
	      int nTestCases = Integer.parseInt(str);
	      for(int i = 0; i < nTestCases; i++) {
	    	  str = in.readLine();
	    	  System.out.println(analyze(str)>=0?"YES":"NO");
	      }
	      in.close();
	    } catch (IOException e) {
	        System.err.println(e);
	    }
	}

	private int analyze(String buf) {
		int start = 1;
		int ret = 0;
		int length = buf.length();
		do {
			ret = startBrackets(buf.substring(start), buf.charAt(start-1));
			start += ret;
		}
		while (start < length && ret > 0);
		return ret;
	}

	private int startBrackets(String buf, char bracket) {
		int ret = 0;
		char firstChar = buf.charAt(0);
		switch(firstChar) {
		case ')':
			if (bracket != '(') {
				return -1;
			}
			break;
		case '}':
			if (bracket != '{') {
				return -1;
			}
			break;
		case ']':
			if (bracket != '[') {
				return -1;
			}
			break;
		default:
			ret = startBrackets(buf.substring(1), firstChar);
			if (ret <= 0) {
				return -1;
			}
			int start = ret;
			ret = startBrackets(buf.substring(start), bracket);
			if (ret <= 0) {
				return -1;
			}
			return start + ret;
		}
		return ret + 2;
	}
}
