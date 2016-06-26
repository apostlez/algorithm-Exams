import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem_1 {

	public static void main(String[] args) {
		try {
			start(args[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static int father[];
	
	static void start(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			father = new int[50001];
			int relation = sc.nextInt();
			for(int i = 0; i < relation-1; i++) {
				int p = sc.nextInt();
				int s = sc.nextInt();
				father[s] = p;
			}
			System.out.println(calculate(relation));
		}
	}
	
	static int calculate(int relation) {
		int res = 1, old = res;
		for(int i=0;i<relation;i++) {
			old = getDepth(i+1,1);
			res = res > old ? res : old;			
		}
		return res;
	}
	
	static int getRoot(int son) {
		System.out.println(son);
		return father[son] == 0 ? son : getRoot(father[son]); 
	}
	
	static int getDepth(int son, int d) {
		return father[son] == 0 ? d : getDepth(father[son], d+1); 
	}

}
