package com.yunkwan.exam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * https://algospot.com/judge/problem/read/KAKURO1
 */
public class KAKURO1 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream("KAKURO1.txt")));
		int T = sc.nextInt();
		// num of tc
		System.out.println(T);
		while (T-- > 0) {
			int N = sc.nextInt();
			int board[][] = new int[N][N];
			for(int i=0;i<N*N;i++) {
				board[i/N][i%N] = sc.nextInt();
			}
			
			ArrayList<int[]> h_ans = new ArrayList<int[]>();
			ArrayList<int[]> v_ans = new ArrayList<int[]>();
			
			for(int i=0; i<N ;i++) {
				for(int j=0; j<N ;j++) {
					int sum = 0;
					if(board[i][j] == 0) { // hint or black
						// check right
						// right end
						if(j != N-1 && board[i][j+1] != 0) {
							// this is hint
							for(int k=j+1; k<N ;k++) {
								sum += board[i][k];
								if(board[i][k] == 0)
									break;
							}
							int[] ans = {i+1, j+1, 0, sum};
							h_ans.add(ans);
						}					
						// check below
						// bottom
						if(i != N-1 && board[i+1][j] != 0) {
							// this is hint
							sum = 0;
							for(int k=i+1; k<N ;k++) {
								sum += board[k][j];
								if(board[k][j] == 0)
									break;
							}
							int[] ans = {i+1, j+1, 1, sum};
							v_ans.add(ans);
						}
					}
				}
			}
			// output
			// size of map
			System.out.println(N);
			// N*N map
			for(int i=0; i<N ;i++) {
				for(int j=0; j<N ;j++) {
					System.out.print((board[i][j] == 0 ? 0 : 1) + " ");
				}
				System.out.println();
			}
			// num of hint
			System.out.println(h_ans.size() + v_ans.size());
			// y x (0 -> h) sum
			for(int i=0;i<h_ans.size();i++) {
				int[] ans = h_ans.get(i);
				System.out.println(ans[0] + " " + ans[1] + " " + ans[2] + " " + ans[3]);
			}
			// y x (1 -> v) sum
			for(int i=0;i<v_ans.size();i++) {
				int[] ans = v_ans.get(i);
				System.out.println(ans[0] + " " + ans[1] + " " + ans[2] + " " + ans[3]);
			}
		}
	}

}
