import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Problem_5 {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
            //out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".out"));
			out = new BufferedWriter(new FileWriter("input.out"));
			System.out.println(new Timestamp((new Date()).getTime()));
            start("Set2.in");
            System.out.println(new Timestamp((new Date()).getTime()));
			//start("t.in");
			//start(args[0]);
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	static void start(String filename) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			double[][] list = new double[N][]; 
			for(int i=0;i<N;i++) {
				double x = sc.nextInt();
				double y = sc.nextInt();
				double dx = sc.nextInt();
				double dy = sc.nextInt();
 
				double a = dx == 0 ? 0 : dy / dx;
				double b = dx == 0 ? x : y - a * x;
				list[i] = new double[] {x,y,dx,dy,a,b};
			}
			//if(tc == 89-82)
			{
				//System.out.println(calc(list, M));
				//System.out.println(BF(list, M));
				System.out.println(calc_old(list, M));
			}
		}
	}
	
	static boolean checkDist(double xi, double xj, double yi, double yj, int i, int j, int k, int l) {
		if(Math.max(Math.abs(xi-xj), Math.abs(yi-yj)) <= 2) {
			//System.out.println("ijkl " + i + " " + j + " " + k + " " + l);
			//System.out.println("xyxy " + xi + " " + yi + " " + xj + " " + yj);
			return true;
		}
		return false;
	}

	
	static int calc_old (double[][] list, int M) {
		int probe = 0;
		for(int i=0;i<list.length-1;i++) {
			for(int j=i+1;j<list.length;j++) {
				if(isParallel(list[i][4], list[j][4])) {
					// check distance
					double dist;
					if(list[i][2] == 0) {
						dist = Math.abs(list[i][0] - list[j][0]);
					} else if(list[i][3] == 0) {
						dist = Math.abs(list[i][1] - list[j][1]);
					} else {
						dist = Math.abs(list[i][4] * list[j][0] - list[j][1] + list[i][5]) / Math.sqrt(list[i][4] * list[i][4] + 1);
					}
					if(dist <= 3)
					{
						for(int k=0;k<=M;k++) {
							double xi = list[i][0] + (k)*list[i][2];
							double yi = list[i][1] + (k)*list[i][3];
							for(int l=0;l<=M;l++) {
								double xj = list[j][0] + (l)*list[j][2];
								double yj = list[j][1] + (l)*list[j][3];
								// if distance is less than 2, then +2 probe
								if(Math.max(Math.abs(xi-xj), Math.abs(yi-yj)) <= 2) {
									probe += 2;
								}
							}
						}
/*					} else {
						System.out.println(Arrays.toString(list[i]));
						System.out.println(Arrays.toString(list[j]));
						System.out.println("dist:" + dist);
*/					}
					continue;
				}

				double contact_x_min = 0;
				double contact_x_max = 0;
				double contact_y_min = 0;
				double contact_y_max = 0;
				// y = c , y = ax + b -> x = (c-b)/a - dy
				/*if(list[i][3] == 0) {
					contact_x_min = (list[i][1] - list[j][5]) / list[j][4] - list[i][2];
					contact_x_max = (list[i][1] - list[j][5]) / list[j][4] + list[i][2];
				} else if(list[j][3] == 0) {
					contact_x_min = (list[j][1] - list[i][5]) / list[i][4] - (list[i][2] + list[j][2]);
					contact_x_max = (list[j][1] - list[i][5]) / list[i][4] + (list[i][2] + list[j][2]);
				} else */if(list[i][2] == 0) {
					// x = c, y = ax + b -> y = ac + b - dy
					contact_y_min = (list[j][4] * list[i][0]) + list[j][5] - list[i][3];
					contact_y_max = (list[j][4] * list[i][0]) + list[j][5] + list[i][3];
				} else if(list[j][2] == 0) {
					contact_y_min = (list[i][4] * list[j][0]) + list[i][5] - list[j][3];
					contact_y_max = (list[i][4] * list[j][0]) + list[i][5] + list[j][3];
				} else {
					double range = Math.max(3 / Math.abs(list[i][4] - list[j][4]), 3 * Math.abs(list[i][4] + list[j][4]));
					// calc contact x = (b' - b) / (a - a')
					contact_x_min = (list[j][5] - list[i][5] - range) / (list[i][4] - list[j][4]);
					contact_x_max = (list[j][5] - list[i][5] + range) / (list[i][4] - list[j][4]);
				}
				
				// x = b
				double cixmin = 0;
				double cixmax = 0;
				double cjxmin = 0;
				double cjxmax = 0;
				if(list[i][2] == 0 || list[j][2] == 0) {
					cixmin = (contact_y_min - list[i][1]) / list[i][3];
					cixmax = (contact_y_max - list[i][1]) / list[i][3];
					cjxmin = (contact_y_min - list[j][1]) / list[j][3];
					cjxmax = (contact_y_max - list[j][1]) / list[j][3];
				} else {
					cixmin = (contact_x_min - list[i][0]) / list[i][2];
					cixmax = (contact_x_max - list[i][0]) / list[i][2];
					cjxmin = (contact_x_min - list[j][0]) / list[j][2];
					cjxmax = (contact_x_max - list[j][0]) / list[j][2];
				}

				if(cixmax < cixmin) {
					double tmp = cixmax;
					cixmax = cixmin;
					cixmin = tmp;
				}				
				
				int Mi_min = (int)Math.floor(cixmin) - 1;
				int Mi_max = (int)Math.ceil(cixmax) + 1;
				
				if(Mi_min < 0) Mi_min = 0;  
				if(Mi_max > M) Mi_max = M;
				if(Mi_max < 0) Mi_max = 0;

				if(cjxmax < cjxmin) {
					double tmp = cjxmax;
					cjxmax = cjxmin;
					cjxmin = tmp;
				}				

				int Mj_min = (int)Math.floor(cjxmin) - 1;
				int Mj_max = (int)Math.ceil(cjxmax) + 1;
				
				if(Mj_min < 0) Mj_min = 0;  
				if(Mj_max > M) Mj_max = M;
				if(Mj_max < 0) Mj_max = 0;

/*				int hear = 0;
				if(i == 43 && j == 88) {
					System.out.println("ij:" + i + " " + j);
					System.out.println("MiMj:" + Mi_min + " " + Mi_max + " " + Mj_min + " " + Mj_max);
					System.out.println(Arrays.toString(list[i]));
					System.out.println(Arrays.toString(list[j]));
				}
*/
/*				if(Math.abs(list[i][4] - list[j][4]) < 0.2) {
					System.out.println("ij:" + i + " " + j);
					System.out.println("MiMj:" + Mi_min + " " + Mi_max + " " + Mj_min + " " + Mj_max);
					System.out.println(Arrays.toString(list[i]));
					System.out.println(Arrays.toString(list[j]));
				}
*/
/*				for(int a=0;a<1;a++) {
					if(list[i][a] > list[j][a] && list[i][2+a] > 0 && list[j][2+a] > 0) {
						// jump j
						int jump_x = (int)Math.floor((list[i][a] - list[j][a]) / list[j][2+a]);
						Mj_min = Math.max(Mj_min, jump_x);
						System.out.println("Mj_min try to changed " + Mj_min + " " + jump_x );
					} else if (list[i][a] < list[j][a] && list[i][2+a] > 0 && list[j][2+a] > 0) {
						// jump i
						int jump_x = (int)Math.floor((list[j][a] - list[i][a]) / list[i][2+a]);
						Mi_min = Math.max(Mi_min, jump_x);
						System.out.println("Mi_min try to changed " + Mi_min + " " + jump_x );
					}
				}
*/				
				if((Mi_min == 0 && Mi_max == M) || (Mj_min == 0 && Mj_max == M)) {
					System.out.println("p:" + probe);
					System.out.println(Arrays.toString(list[i]));
					System.out.println(Arrays.toString(list[j]));
				}
				//Mi_min = 0; Mi_max = 500; Mj_min = 0; Mj_max = 500;
				for(int k=Mi_min;k<=Mi_max;k++) {
					double xi = list[i][0] + (k)*list[i][2];
					double yi = list[i][1] + (k)*list[i][3];
					//Mj_min = Math.min(contact_x_max - list[j][0]) / list[j][2];
					for(int l=Mj_min;l<=Mj_max;l++) {
						double xj = list[j][0] + (l)*list[j][2];
						double yj = list[j][1] + (l)*list[j][3];
						if(list[j][3] > 0 && yi + 2 < yj) break;
						if(list[j][2] > 0 && xi + 2 < xj) break;
						if(list[j][3] < 0 && yj + 2 < yi) break;
						if(list[j][2] < 0 && xj + 2 < xi) break;
						// if distance is less than 2, then +2 probe
						if(checkDist(xi, xj, yi, yj, i, j, k, l)) {
							probe += 2;
						}
					}
				}
				if((Mi_min == 0 && Mi_max == M) || (Mj_min == 0 && Mj_max == M)) {
					System.out.println("p:" + probe);
					System.out.println(Arrays.toString(list[i]));
					System.out.println(Arrays.toString(list[j]));
				}
			}
			//System.out.println(probe);
		}
		return probe;
	}

	static int BF(double[][] list, int M) {
		int probe = 0;
		for(int i=0;i<list.length-1;i++) {
			for(int j=i+1;j<list.length;j++) {
				if(isParallel(list[i][4], list[j][4])) {
					// check distance
					double dist = Math.abs(list[i][4] * list[j][0] - list[j][1] + list[i][5]) / Math.sqrt(list[i][4] * list[i][4] + 1);
					if(dist <= 3) {
						for(int k=0;k<=M;k++) {
							double xi = list[i][0] + (k)*list[i][2];
							double yi = list[i][1] + (k)*list[i][3];
							for(int l=0;l<=M;l++) {
								double xj = list[j][0] + (l)*list[j][2];
								double yj = list[j][1] + (l)*list[j][3];
								// if distance is less than 2, then +2 probe
								if(Math.max(Math.abs(xi-xj), Math.abs(yi-yj)) <= 2) {
									probe += 2;
								}
							}
						}
					}
					continue;
				}
				int Mi_min = 0;
				int Mi_max = 500;
				int Mj_min = 0;
				int Mj_max = 500;
				for(int k=Mi_min;k<=Mi_max;k++) {
					double xi = list[i][0] + (k)*list[i][2];
					double yi = list[i][1] + (k)*list[i][3];
					for(int l=Mj_min;l<=Mj_max;l++) {
						double xj = list[j][0] + (l)*list[j][2];
						double yj = list[j][1] + (l)*list[j][3];
						// if distance is less than 2, then +2 probe
						if(checkDist(xi, xj, yi, yj, i, j, k, l)) {
							probe += 2;
						}
					}
				}
			}
			//System.out.println(probe);
		}
		return probe;
	}
	
	static boolean isAvailable(double[] n, double x, double y) {
		if(n[2] > 0 && n[3] > 0) { // dx > 0 && dy > 0
			if(n[0] < x && n[1] < y) return true;
		} else if(n[2] > 0 && n[3] < 0) { // dx > 0 && dy < 0
			if(n[0] < x && n[1] > y) return true;
		} else if(n[2] < 0 && n[3] > 0) { // dx < 0 && dy > 0
			if(n[0] > x && n[1] < y) return true;
		} else if(n[2] < 0 && n[3] < 0) { // dx < 0 && dy < 0
			if(n[0] > x && n[1] > y) return true;
		}
		//System.out.println(x + " " + y);
		return false;
	}
		
	static boolean isParallel(double a1, double a2) {
		if(a1 == a2)
			return true;
		return false;
	}
			
	static int calc(double[][] list, int M) {
		int probe = 0;
		for(int i=0;i<list.length;i++) {
			for(int j=i+1;j<list.length;j++) {
				if(isParallel(list[i][4], list[j][4])) {
					// check distance
					double dist = Math.abs(list[i][4] * list[j][0] - list[j][1] + list[i][5]) / Math.sqrt(list[i][4] * list[i][4] + 1);
					if(dist <= 3) {
						for(int k=0;k<=M;k++) {
							double xi = list[i][0] + (k)*list[i][2];
							double yi = list[i][1] + (k)*list[i][3];
							for(int l=0;l<=M;l++) {
								double xj = list[j][0] + (l)*list[j][2];
								double yj = list[j][1] + (l)*list[j][3];
								// if distance is less than 2, then +2 probe
								if(Math.max(Math.abs(xi-xj), Math.abs(yi-yj)) <= 2) {
									probe += 2;
								}
							}
						}
					}
					//System.out.println(Arrays.toString(list[i]));
					//System.out.println(Arrays.toString(list[j]));
					//System.out.println("dist = " + dist);

					continue;
				}
/*				int hear = 0;
				if(i == 43 && j == 88) {
					System.out.println(Arrays.toString(list[i]));
					System.out.println(Arrays.toString(list[j]));
				}
*/
				if(Math.abs(list[i][4] - list[j][4]) < 0.05) {
					for(int k=0;k<=M;k++) {
						double xi = list[i][0] + (k)*list[i][2];
						double yi = list[i][1] + (k)*list[i][3];
						for(int l=0;l<=M;l++) {
							double xj = list[j][0] + (l)*list[j][2];
							double yj = list[j][1] + (l)*list[j][3];
							// if distance is less than 2, then +2 probe
							if(checkDist(xi, xj, yi, yj, i, j, k, l)) {
								probe += 2;
							}
						}
					}
					continue;
				}

				double contact_x = 0;
				double contact_y = 0;
				// y = c , y = ax + b -> x = (c-b)/a - dy
				/*if(list[i][3] == 0) {
					contact_x_min = (list[i][1] - list[j][5]) / list[j][4] - list[i][2];
					contact_x_max = (list[i][1] - list[j][5]) / list[j][4] + list[i][2];
				} else if(list[j][3] == 0) {
					contact_x_min = (list[j][1] - list[i][5]) / list[i][4] - (list[i][2] + list[j][2]);
					contact_x_max = (list[j][1] - list[i][5]) / list[i][4] + (list[i][2] + list[j][2]);
				} else */if(list[i][2] == 0) {
					// x = c, y = ax + b -> y = ac + b - dy
					contact_y = (list[j][4] * list[i][0]) + list[j][5] - list[i][3];
				} else if(list[j][2] == 0) {
					contact_y = (list[i][4] * list[j][0]) + list[i][5] - list[j][3];
				} else {
					// calc contact x = (b' - b) / (a - a')
					contact_x = (list[j][5] - list[i][5]) / (list[i][4] - list[j][4]);
				}
				
				// x = b
				double cix = 0;
				double cjx = 0;
				if(list[i][2] == 0 || list[j][2] == 0) {
					cix = (contact_y - list[i][1]) / list[i][3];
					cjx = (contact_y - list[j][1]) / list[j][3];
				} else {
					cix = (contact_x - list[i][0]) / list[i][2];
					cjx = (contact_x - list[j][0]) / list[j][2];
				}

				int Mi = (int)Math.floor(cix);
				int Mj = (int)Math.floor(cjx);
				
				if(Mi < 0) Mi = 0;
				if(Mj < 0) Mj = 0;
				
				//Mi_min = 0; Mi_max = 500; Mj_min = 0; Mj_max = 500;
				for(int k=Mi+1;k<=M;k++) {
					boolean notFound = true;
					double xi = list[i][0] + (k)*list[i][2];
					double yi = list[i][1] + (k)*list[i][3];
					for(int l=Mj+1;l<=M;l++) {
						double xj = list[j][0] + (l)*list[j][2];
						double yj = list[j][1] + (l)*list[j][3];
						// if distance is less than 2, then +2 probe
						if(checkDist(xi, xj, yi, yj, i, j, k, l)) {
							probe += 2;
							notFound = false;
						} else {
							notFound = true;
							break;
						}
						if(notFound) break;
					}
					for(int l=Mj;l>=0;l--) {
						double xj = list[j][0] + (l)*list[j][2];
						double yj = list[j][1] + (l)*list[j][3];
						// if distance is less than 2, then +2 probe
						if(checkDist(xi, xj, yi, yj, i, j, k, l)) {
							probe += 2;
							notFound = false;
						} else {
							notFound = true;
							break;
						}
					}
					//if(notFound) break;
				}
				for(int k=Mi;k>=0;k--) {
					boolean notFound = true;
					double xi = list[i][0] + (k)*list[i][2];
					double yi = list[i][1] + (k)*list[i][3];
					for(int l=Mj+1;l<=M;l++) {
						double xj = list[j][0] + (l)*list[j][2];
						double yj = list[j][1] + (l)*list[j][3];
						// if distance is less than 2, then +2 probe
						if(checkDist(xi, xj, yi, yj, i, j, k, l)) {
							probe += 2;
							notFound = false;
						} else {
							notFound = true;
							break;
						}
					}
					for(int l=Mj;l>=0;l--) {
						double xj = list[j][0] + (l)*list[j][2];
						double yj = list[j][1] + (l)*list[j][3];
						// if distance is less than 2, then +2 probe
						if(checkDist(xi, xj, yi, yj, i, j, k, l)) {
							probe += 2;
							notFound = false;
						} else {
							notFound = true;
							break;
						}
					}
					//if(notFound) break;
				}
			}
			System.out.println(probe);
		}
		return probe;
	}
	
}
