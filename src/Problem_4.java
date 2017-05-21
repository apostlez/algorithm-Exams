

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Problem_4 {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
			//String filename = "Set3.in";
			String filename = "test.in";
			if(args.length > 0) {
				filename = args[0];
			}
            out = new BufferedWriter(new FileWriter(filename.substring(0, filename.lastIndexOf('.')) + ".out"));
			start(filename);
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	static void start(String filename) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			int [] n = new int[5];
			for(int i=0;i<5;i++) {
				n[i] = sc.nextInt();
			}
			String result = calculate(n);
			System.out.println(result);
			out.write(String.valueOf(result));
			out.newLine();
		}
	}
	
	static String calculate(int[] n) {
		long sum = 0;
		long unit = 0;
		long index = 0;
		int length = 1;
		for(int i=0;i<5;i++) {
			unit += n[i];
		}
		while(true) {
			for(int i=1;i < 10; i++) {
				// make number
				sum = 0;
				for(int j=length-1; j >= 0;j--) {
					long pow = (long) Math.pow(10, j);
					sum += i*pow;
				}
				if(sum < unit) continue;
				System.out.println(sum);
				int left = (int) (sum % unit);
				index = sum / unit * 5 -1;
				for(int k=0;k<5;k++) {
					if(left == 0) {
						index += k;
						return index % 1000000007 + " " + sum % 10 + "(" + length + ")";
					}					
					if(left > n[k])
						left -= n[k];
					else
						break;
				}
			}
			length++;
		}
	}
	
	static String calculate2(int[] n) {
		String ret = "";
		long sum = 0;
		long index = 0;
		
		long unit = 0, unit_e = 0;
		long unit_i = 5;
		for(int i=0;i<5;i++) {
			unit += n[i];
		}
		unit_e = unit;
		
		while(true) {
			for(int i=0;i<5;i++) {
				sum += n[i];
				long length = isIterate(index, sum);
				System.out.println(sum + " " + length);
				if(length > 0) {
					return index % 1000000007 + " " + sum % 10 + "(" + length + ")";
				}
				index++;
			}
			while(true) {
				//System.out.println(String.valueOf(sum + unit_e).charAt(0));
				//System.out.println(String.valueOf(sum).charAt(0));
				if(String.valueOf(sum + unit_e).charAt(0) == String.valueOf(sum).charAt(0)) {
					unit_e += unit;
					unit_i += 5;
				}
				sum += unit_e;
				index += unit_i;
				long iter = findFirstIterate(index, sum);
				if(iter > 0 ) {
					break;
				} else if(iter < 0) {
					unit_e += unit;
					unit_i += 5;
				}
			}
		}
	}

	static long isIterate(long index, long sum) {
		long length = 1;
		long number = sum % 10;
		char[] strSum = String.valueOf(sum).toCharArray();
	    if(number == 0) return 0;
    	for(int i = strSum.length-2; i >= 0; i--) {
        	//System.out.println(sum + " " + strSum[i] + " " + number);
    		if(strSum[i] != number + '0')
    			return 0;
    	}
	    return strSum.length;
	}
	
	static long findFirstIterate(long index, long sum) {
		long number = sum % 10;
		char[] strSum = String.valueOf(sum).toCharArray();
	    if(number == 0) return 0;
	    if(strSum[0] == strSum[1]) {
	    	return index;
	    } else if(strSum[0] < strSum[1]) {
	    	return -1;
	    }
	    return 0;
	}

	static long findLastIterate(long index, long sum) {
		long number = sum % 10;
		char[] strSum = String.valueOf(sum).toCharArray();
	    if(number == 0) return 0;
	    if(strSum[strSum.length-2] == strSum[strSum.length-1]) {
	    	return index;
	    }
	    return 0;
	}

}
