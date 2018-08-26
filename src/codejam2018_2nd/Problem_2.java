package codejam2018_2nd;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem_2{
	// 역순 계산으로 변경
	// r 연산의 경우
	/* r 연산의 경우
	 * 1. 범위안에 들어가는 경우
	 * 2. 중심과의 거리 d를 구하고 현재 index에서 빼고 절대값으로  이동 전 index 를 구함
	 */
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		int m = sc.nextInt();
		
		int i,j;
		//List<Integer> X = new ArrayList<Integer>();
		//List<Integer> X = Arrays.asList(new int[n] {0,});
		List<Integer> X = Stream.iterate(0, x -> 0).limit(n).collect(Collectors.toList());

		//Stream<Integer> istream = Stream.iterate(0, x -> x + 1).limit(n);
		sc.nextLine();
		
		for(int ii = 0; ii < q; ii++) {
			String line = sc.nextLine();
			char op = line.split(" ")[0].charAt(0);
			if(op == 'r') {
				i = Integer.parseInt(line.split(" ")[1]);
				j = Integer.parseInt(line.split(" ")[2]);
				Collections.reverse(X.subList(i, j+1));
			} else {
				int index = 0;
				for(int id=0;id<n;id++) {
					X.set(id, X.get(id) + id);
				}
				//stream = Stream.iterate(0, x -> x + 1).limit(n);
			}
			//X.stream().forEach(System.out::print);
			//System.out.println();
		}
	
		for(int jj = 0; jj < m; jj++) {
			int y = sc.nextInt();
			if(X.size() > y)
				System.out.println(X.get(y));
		}
		//Collections.reverse(Y.subList(i, j));
		
	}
}
