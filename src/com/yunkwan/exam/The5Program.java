package com.yunkwan.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

public class The5Program {
	// https://blog.svpino.com/2015/05/07/five-programming-problems-every-software-engineer-should-be-able-to-solve-in-less-than-1-hour
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		The5Program p = new The5Program();
		System.out.println(p.getFibonacci(10));
	}
	
	/*
	 * Problem 1
	 * Write three functions that compute the sum of the numbers in a given list using a for-loop, a while-loop, and recursion.
	 */
	int sumUsingForLoop(int[] list) {
		int sum = 0;
		for(int i=0;i<list.length;i++) {
			sum += list[i];
		}
		return sum;
	}
	
	int sumUsingWhileLoop(int[] list) {
		int sum = 0;
		int index = 0;
		while(index++ < list.length) {
			sum += list[index];
		}
		return sum;
	}
	
	int sumUsingRecursion(ArrayList<Integer> list) {
		int sum = 0;
		if (list.size() > 1) {
			sum += sumUsingRecursion((ArrayList<Integer>)list.subList(0, list.size()-2));
		} else {
			return list.get(0);
		}
		return sum;
	}

	int getFibonacci(int number) {
		int first = 0;
		int next = 0;
		int third = 1;
		for(int i=0;i<number;i++) {
			first = next;
			next = third;
			third = first + next;
			System.out.println(third);
		}
		return next;
	}
	
	private static Map<Integer,Long> memo = new HashMap<>();
	static {
	   memo.put(0,0L); //fibonacci(0)
	   memo.put(1,1L); //fibonacci(1)
	}
	
	public static long fibonacci(int x) {
		   return memo.computeIfAbsent(x, n -> Math.addExact(fibonacci(n-1),
		                                                     fibonacci(n-2)));
	}

	public static long fibonacci2(int x) {
		   return memo.computeIfAbsent(x, n -> fibonacci(n-1) + fibonacci(n-2));
	}

/*	public interface A<T> {
	    T apply();
	}
*/
/*	public interface long {
		void setLong();
	}
*/
/*	public static long fibonacci3(int x) {
		A<Long> f = n -> fibonacci(n-1) + fibonacci(n-2);
		   return f;
	}
*/
}
