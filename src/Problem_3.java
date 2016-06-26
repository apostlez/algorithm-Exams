import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Problem_3 {

	public static void main(String[] args) {
		try {
			//start(args[0]);
			start("problem_3_Set1.in");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static int a[], b[];
	static int evenNumber;
	static String str;
	static char[] charList;
	static int processedLength = 0;
	
	static void start(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			evenNumber = 0;
			str = sc.next();
			charList = str.toCharArray();
			processedLength = 0;
			findEvenNumber(0, str.length(), str.length());
			//findEvenNumber2(str);
			System.out.println(evenNumber);
		}
	}
	
	static int findEvenNumber(int start, int end, int length) {
		int ret = 0;
		for(int i=start; i< length-1; i++) {
			char first = charList[i];
			char second = charList[i+1];
			if(first == second) {
				evenNumber++;
				System.out.println(str.substring(i,i+2));
				ret = findBound(i, i+1, 1);
				if(ret > 0) {
					i += ret;
				}
			}
		}
		return 0;
	}
	
	static int findBound(int start, int end, int length) {
		int ret = 0;
		if(start > 0 && str.length() > end+1) {
			char prev = charList[start-1];
			char next = charList[end+1];
			if(prev == next) {
				System.out.println(str.substring(start-1,end+2));
				evenNumber++;
				ret = findBound(start-1, end+1, length+1);
			} else {
				return length + ret;
			}
		}
		return ret;
	}
	
	static int findEvenNumber2(String s) {
		Stack<Character> even = new Stack<Character>();
		Stack<Character> lookahead = new Stack<Character>();
		
		lookahead.push(s.charAt(0));
		for(int i=1;i<s.length();i++) {
			char current = s.charAt(i);
			if(current == lookahead.lastElement()) {
				even.push(current);
				lookahead.pop();
			} else {
				lookahead.push(current);
			}
		}
		return 0;
	}
	
	static int findEvenNumber3(int start, int end, int length) {
		int ret1, ret2;
		char first;
		if(length < 2) return 0;
		System.out.println(str.substring(start,  end));
		if(length == 2) {
			first = charList[start];
			char second = charList[start+1];
			if(second == first) {
				evenNumber++;
				System.out.println(str.substring(start,  end) + ":E");
				processedLength += 2;
				return 2;
			}
		}
		ret1 = findEvenNumber(start, end-1, length-1);
		//System.out.println("1:" + ret1);
		if(ret1 > 0) {
			System.out.println("1****:" + ret1 + str.substring(start,  end) + end);
			if(length % 2 != 0) return ret1;
			if(charList[start] == charList[end-1]) {
				evenNumber++;
				System.out.println(str.substring(start,  end) + ":(E)");
				processedLength += 2;
				return ret1 + 2;
			}
		}
		int loc = ret1 > 0 ? ret1 : 1;
		ret2 = findEvenNumber(start+ loc, end, length - loc);
		//System.out.println("2:" + ret2);
		if(ret2 > 0) {
			System.out.println("2****:" + ret2 + str.substring(start+loc, end));
			if(length % 2 != 0) return ret2;
			if(end < length && charList[start+loc] == charList[end]) {
				evenNumber++;
				System.out.println(str.substring(start + loc, end) + ":++3");
				ret2 =+ 2;
			}
			System.out.println(ret1 + " " + ret2);
			// (E) (E)
			if(ret1 > 0 && ret2 > 0) {
				evenNumber++;
				System.out.println(str.substring(start,  end) + ":EE");
			}
			return ret2;
		}
		return ret1 + ret2;
	}
}

