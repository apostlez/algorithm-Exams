package codejam2018_2nd;
import java.util.*;

class Matrix {
	int R;
	int C;
	public Matrix(int r, int c) {
		R = r;
		C = c;		
	}
	public Matrix mult(Matrix m) {
		return this;
	}	
	public boolean isAblefollow(Matrix m) {
		if(C == m.R)
			return true;
		return false;
	}
	public String toString() {
		return R + " " + C + " ";
	}

}

public class Problem_3 {
	
	static int n = 0;
	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		List<Matrix> matrix = new ArrayList<Matrix>();
		for(int i=0;i<n;i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			Matrix last = new Matrix(r, c);
			matrix.add(last);

			int ret = findFirst(matrix);
			System.out.println(ret);
		}
	}
	public static int findFirst(List<Matrix> matrix) {
		List<Integer> ret = new ArrayList<Integer>();
		for(int i=0;i<matrix.size();i++) {
			Collections.swap(matrix, 0, i);
			ret.add(findNext(matrix, matrix.get(0), 1));
		}
		return ret.stream().max(Comparator.comparing(Integer::valueOf)).get();
	}
	
	public static int findNext(List<Matrix> matrix, Matrix first, int index) {
		if(matrix.size() == index)
			return matrix.get(0).R * matrix.get(index-1).C;

		List<Matrix> tmp = new ArrayList<Matrix>(matrix);
		List<Integer> ret = new ArrayList<Integer>();
		for(int i=index;i<tmp.size();i++) {
			if(tmp.get(i).R == first.C) {
				Collections.swap(tmp, index, i);
				ret.add(findNext(tmp, tmp.get(index), index+1));
			}
		}
		if(ret.isEmpty()) return 0;
		//tmp.stream().forEach(System.out::print);
		//System.out.println();
		return ret.stream().max(Comparator.comparing(Integer::valueOf)).get();
	}
}
/*
4
1 2
2 3
3 2
2 1
*/

