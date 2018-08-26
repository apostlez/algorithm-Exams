package codejam2018_2nd;
import java.util.*;


/*
 * failed
 */
public class Main {
	
	static int n = 0;
	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		for(int i=0;i<=52;i++) {
			n = i;
			System.out.println(n);
			int min = 0, max = 0;
			if(n < 26) {
				int left = 52 - n/3 * 4;
				// 1 x
				//min = (26 - n) / 2  + n%2;
				// 2 x
				//min = Math.round((26f - n) / 2f);
				// 3
				//int triple_num = ((26 - n) / 2)  + n%2;
				int triple_num = (left / 4)  + (left%4)/3;
				int double_num = (left / 4) - triple_num;
				if(triple_num > double_num) {
					min = (triple_num * 2 + double_num) / 3;
				} else {
					min = triple_num;
				}
				//System.out.println(triple_num + "," + double_num);
			}
			if(n == 52)
				max = 0;
			else
				max = (52 - n) / 5;
			//System.out.println((min > 8 ? 8 : min) + " " + (max > 8 ? 8 : max));
			System.out.println((min) + " " + (max > 8 ? 8 : max));
		}
	}
}


/*

1111
2222
3333
4444
5555
6666
7777
8888
9999
0000
jjjj
qqqq
kkkk

::::5 (7,8)
11 1
22 2
33 3
44 4
55 5
66 66
777 7
888 8
999 9
000 0
jjj j
qqq q
kkk k

::::6 (6,8)
11 1
22 2
33 3
44 4
55 5
66 6
777 7
888 8
999 9
000 0
jjj j
qqq q
kkk k

::::7 (6,8)
11 1
22 2
33 3
44 4
55 5
66 6
777 
888 8
999 9
000 0
jjj j
qqq q
kkk k

::::11 (7,8) = 52 - 11 = 41 - 26 = 15
11
22
33
44
55
666
77 77
888 8
999 9
000 0
jjj j
qqq q
kkk k

:::::11 (7,8) ?
111
222
333
444
555
666
777
88 8
99 9
00 0
jj j
qq qk
kk kk

::::11 (7,8) ?
11
22
33
44
55
666
77 77
888 8
999 9
000 0
jjj j
qqq q
kkk k

:::::12 (7,8) ?
11 11
22 2
33 3
44 4
55 5

666

777
888
999
000
jjj
qqq
kkk

::::12 (6,7) ?
11
22
33
44
55
66
777 7
888 8
999 9
000 0
jjj j
qqq q
kkk k

:::::13 (6,8) ?
11 1
22 2
33 3
44 4
55 5
66 6

777

888
999
000
jjj
qqq
kkk

::::13 (6,7) ?
11
22
33
44
55
66
777
8888
9999
0000
jjjj
qqqq
kkkk

::::14 (6,7) ?
11
22
33
44
55
66
77
8888
9999
0000
jjjj
qqqq
kkkk

::::15 (6,7) ?
11
22
33
44
55
66
77
888
9999
0000
jjjj
qqqq
kkkk

::::16 (5,7) ?
11
22
33
44
55
66
77
88
9999
0000
jjjj
qqqq
kkkk


::::19 (4,6) ?
11
22
33
44
55
66
77
88
99
000
jjjj
qqqq
kkkk

::::20 (3,6) ?
11
22
33
44
55
66
77
88
99
00
jjjj
qqqq
kkkk
::::21 (3,6) ?
11
22
33
44
55
66
77
88
99
00
jjj
qqqq
kkkk














=============================================
:::::12 (7,8) ?
11 11
22 22
33 33
44 
55 

666
777
888
999

000
jjj
qqq
kkk


:::::11 (7,8) ?
11 11
22 22
33 33
44 44

555 

666
777
888
999

000
jjj
qqq
kkk


*/