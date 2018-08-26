package codejam2018_2nd;
import java.text.DecimalFormat;
import java.util.*;

public class Problem_4 {

	
	/*
	 * R C D W
	 * YR1C1 YR1C2 YR1CM
	 * YR2C1 YR2C2 YR2CM
	 * YRNC1 YRNC2 YRNCM
	 * R: 행, C는 열, D 는 공원 가격 W는 건물 가격
	 */
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int w = sc.nextInt();
		
		int[][] y = new int[r][c];
		List<Integer> yOrderedList = new ArrayList<Integer>(); 
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				y[i][j] = sc.nextInt();
				yOrderedList.add(y[i][j]);
			}
		}
		double min = Double.MAX_VALUE;
		int z = 0;
		yOrderedList.sort(Comparator.naturalOrder());
		int size = r*c;
		// check 0 height
		if(checkPark(size, 0, y, yOrderedList, r, c, 0)) {
			double current = d * (size) / 2 + z * w;
			//System.out.println(current);
			min = Math.min(current, min);
		}
		
		int last = 0;
		List<Double> bg = new ArrayList<Double>();
		for(int i=0;i<size;i++) {
			// check park
			z = yOrderedList.get(i);
			if(checkPark(size, i+1, y, yOrderedList, r, c, z)) {
				double current = d * (size-(i+1)) / 2 + z * w;
				//System.out.println(current);
				min = Math.min(current, min);
				bg.add(min);
				if(bg.size() == 2 && bg.get(0) > bg.get(1)) {
					last = i;
					break;
				}
			}
		}
		if(last != 0) {
			for(int i = size - 1; i > last; i--) {
				// check park
				z = yOrderedList.get(i);
				if(checkPark(size, i+1, y, yOrderedList, r, c, z)) {
					double current = d * (size-(i+1)) / 2 + z * w;
					min = Math.min(current, min);
					break;
				}
			}
		}
		System.out.println(new DecimalFormat("#").format(min));
	}

	public static boolean checkPark(int size, int index, int[][] y, List<Integer> yOrderedList, int r, int c, int z) {
		if((size-index)%2 != 0) return false;
		int[][] buf = new int[r][c];
		int empty = size-index;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(y[i][j] <= z) {
					buf[i][j] = 2;
				}
			}
		}
		boolean ret = checkParkRecl(buf, r, c, empty, 0, 0);
/*		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				System.out.print(buf[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
*/
		return ret;
	}

	public static boolean checkParkRecl(int[][] buf, int r, int c, int left, int ri, int ci) {
		if(left == 0) return true;
		boolean ret = false;
		for(int i=ri;i<r;i++) {
			for(int j=ci;j<c;j++) {
				// check current
				if(buf[i][j] == 0) {
					buf[i][j] = 1;
					if(j+1 < c && buf[i][j+1] == 0) {
						// check right
						buf[i][j+1] = 1;
						ret = checkParkRecl(buf, r, c, left - 2, i, 0);
						if(ret)
							return true;
						buf[i][j+1] = 0;
					}
					if(i+1 < r && buf[i+1][j] == 0) {
						// check down
						buf[i+1][j] = 1;
						ret = checkParkRecl(buf, r, c, left - 2, i, 0);
						if(ret)
							return true;
						buf[i+1][j] = 0;
					}
					buf[i][j] = 0;
					return false;
					
				}
			}
		}
		return false;
	}
}