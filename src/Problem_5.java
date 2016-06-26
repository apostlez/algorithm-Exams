import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class Problem_5 {

	public static void main(String[] args) {
		try {
			//start(args[0]);
			start("problem_5_Set1.in");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static int P[];
	static int sex[];
	static int N;
	
	static void start(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			P = new int[2000];
			sex = new int[2000];
		
			N = sc.nextInt();
			for(int i=0;i<N;i++) {
				P[i] = sc.nextInt();
			}
			for(int i=0;i<N;i++) {
				sex[i] = sc.nextInt();
			}
			int max = calc(0, N);

			System.out.println(max);
		}
	}
	
	static int getMaxMaleFromEnd(int end) {
		for(int i=end;i>0;i--) {
			if(sex[i] == 1) {
				return -1;
			}
		}
		return 0;
	}
	
	static int calc(int start, int end) {
		int max = 0, maxIndex = -1;
		
		int frontFemaleIndex = -1;
		// find max male
		for(int i=0;i<N;i++) {
			if(sex[i] == 1) {
				// female
				frontFemaleIndex = i;
			} else {
				// male
				if(P[i] > max) {
					max = P[i];
					maxIndex = i;
				}
			}
		}
		// div and con using female loc
		return 0;
	}
};

