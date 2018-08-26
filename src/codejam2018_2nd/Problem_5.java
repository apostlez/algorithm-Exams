package codejam2018_2nd;
import java.text.DecimalFormat;
import java.util.*;

public class Problem_5{
	static int alph = 26;
	static int spec = 3;
	static int vowel = 5;
	static int _3vowel = 5*5*5;
	static int alph_vowel = alph - vowel;

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
/*		
		fact34(a,b);
		System.out.println();		
		countAll(a,b);
		System.out.println();
		sum(a,b);
		
		int sub = subsum(4, 2);
		System.out.println("sub:" + sub);
*/		
		countReclStart(a,b);
/*
 * N[a-z] = 26
 * Nspec_char = N[.-*] = 3
 * Bvowel = N[aeiou] = 5
 * word = [a-z][.[a-z]] 
 */
		 
	}
	
/*	static int g_alp = 1; // 21;
	static int g_vow = 1; // 5;
	static int g_spe = 1; // 3;
*/	
	static int g_alp = 21;
	static int g_vow = 5;
	static int g_spe = 3;
	static int g_length = g_alp + g_vow;
	static int g_flength = g_alp + g_vow + g_spe;
	static int g_cont = 0;

	static void countReclStart(double a, double b) {
		int length = g_length;
		double count = 0;
		for(double i=a;i<=b;i++) {
			for(int j=0;j<length;j++) {
				//System.out.print(j);
				count += countRecl(i -1, j, -1);
				//System.out.println();
			}
		}
		System.out.println(new DecimalFormat("#").format(count));
		System.out.println(g_cont);
	}
	
	static double countRecl(double len, int parent, int grand) {
		if(len <= 0) {
			return 1;
		}
		int length = g_flength;
		if(len == 1) length = g_length;
		double ret = 0;
		for(int i=0;i<length;i++) {
			if(i == parent && i == grand) {
				g_cont++;
				continue;
			}
			// vowel
			else if(i >= g_alp && i < g_alp+g_vow
					&& parent >= g_alp && parent < g_alp+g_vow
					&& grand >= g_alp && grand < g_alp+g_vow ) {
				g_cont++;
				continue;
				}
			// spec
			else if(i >= g_alp+g_vow && i < g_alp+g_vow+g_spe
					&& parent >= g_alp+g_vow && parent < g_alp+g_vow+g_spe
					&& grand >= g_alp+g_vow && grand < g_alp+g_vow+g_spe ) {
				g_cont++;
				continue;
			}
			//System.out.print(i);
			ret += countRecl(len -1, i, parent);
		}
		return ret;
	}
	
	
	

	static void sum(int a, int b) {
		double count = 0;
		for(int i=a;i<=b;i++) {
			double mid = 0;
			if(i==1) mid = alph;
			if(i==2) {
				mid = Math.pow((alph + spec), i);
				// start / end with spec -> 3 xxx
				mid -= 3 * Math.pow((alph + spec), i-1) * 2;
				// duplicated
				mid += 3 * 3 * Math.pow((alph + spec), i-2);
			} else {
				mid = Math.pow((alph_vowel), i);
				// 1 vowel
				/*
				double v1 = vowel * Math.pow((alph_vowel), i-1) * i; // 1
				System.out.println(v1);
				mid += v1;
				*/
				double v1 = 0;
				for(int j=(i+1)/2;j>0;j--) {
					//System.out.println("i,j:" + i + "," + j);
					//System.out.println("subsum(j, i-(j-1)*2)=" + subsum(j, i-(j-1)*2));
					if(j == 1)
						v1 = Math.pow(vowel, j ) * Math.pow((alph_vowel), i-j) * i;
					else {
						v1 = Math.pow(vowel, j ) * Math.pow((alph_vowel), i-j) * subsum(j, i-(j-1)*2);
					}
					//System.out.println(v1);
					mid += v1;
				}
				// 2 vowel
				int l = (i+1)/3;
				for(int j=l;j>0;j--) {
					if(j == 1)
						v1 = Math.pow(vowel, j+1 ) * Math.pow((alph_vowel), i-j-1) * (i-1);
					else {
						v1 = Math.pow(vowel, j+1 ) * Math.pow((alph_vowel), i-j-1) * subsum(j, i-(j-1)*3-1);
					}
					//System.out.println(v1);
					mid += v1;
				}
				// 1 spec
				int spec_lenth = i-2;
				for(int j=(spec_lenth+1)/2;j>0;j--) {
					if(j == 1)
						v1 = Math.pow(spec, j ) * Math.pow((alph_vowel), i-j) * i;
					else {
						v1 = Math.pow(spec, j ) * Math.pow((alph_vowel), i-j) * subsum(j, spec_lenth-(j-1)*2);
					}
					//System.out.println(v1);
					mid += v1;
				}
				// 2 spec
				l = (spec_lenth+1)/3;
				for(int j=l;j>0;j--) {
					if(j == 1)
						v1 = Math.pow(vowel, j+1 ) * Math.pow((alph_vowel), i-j-1) * (spec_lenth-1);
					else {
						v1 = Math.pow(vowel, j+1 ) * Math.pow((alph_vowel), i-j-1) * subsum(j, spec_lenth-(j-1)*3-1);
					}
					//System.out.println(v1);
					
					mid += v1;
				}
			}
			//System.out.println(new DecimalFormat("#").format(mid));
			count += mid;
		}
		System.out.println(new DecimalFormat("#").format(count));
	}

	static void fact34(int a, int b) {
		double count = 0;
		for(int i=a;i<=b;i++) {
			double mid = 0;
			if(i==1) mid = alph;
			if(i==2) {
				mid = Math.pow((alph + spec), i);
				// start / end with spec -> 3 xxx
				mid -= 3 * Math.pow((alph + spec), i-1) * 2;
				// duplicated
				mid += 3 * 3 * Math.pow((alph + spec), i-2);
			} else {
				mid = alph;
				for(int j=2;j<i;j++) {
					if((j-1)%3 == 0) {
						mid *= (alph_vowel + spec);
					} else if((j-2)%3 == 0) {
						mid *= (alph_vowel);
					} else 
						mid *= (alph + spec);
				}
				mid *= alph;
			}
			System.out.println(new DecimalFormat("#").format(mid));
			count += mid;
		}
		System.out.println(new DecimalFormat("#").format(count));
	}
	
	static int subsum(int depth, int len) {
		int sum = 0;
		if(depth == 1) return 0;
		if(len == 0) return 1;
		for(int i=1;i<=len;i++) {
			sum += i;
		}
		sum += subsum(depth-1, len-1);
		return sum;
	}
	
	static int factorial(int n, int m)
    {
        if (n == 0)
        	return 1;
        else if (n == m)
        	return n;
         
        return n*factorial(n-1, m);
    }
	static void countAll(int a, int b) {
		double count = 0;
		for(int i=a;i<=b;i++) {
			double mid = 0;
			if(i==1) mid = alph;
			if(i>=2) {
				mid = Math.pow((alph + spec), i);
				// start / end with spec -> 3 xxx
				mid -= 3 * Math.pow((alph + spec), i-1) * 2;
				// duplicated
				mid += 3 * 3 * Math.pow((alph + spec), i-2);
			}
			if(i==3) {
				// - 3 spec ( including 3 same spec) -> 3*3*3 = 27
				// - 3 vowel ( including 3 same vowel) -> 5*5*5 = 125
				mid -= _3vowel;
				// - 3 same alph ( excluding 3 same vowel) -> 21
				mid -= alph_vowel;
			} else if(i==4) {
				// - 3 spec ( including 3 same spec) -> 3*3*3 = 27
				// - 3 vowel ( including 3 same vowel) -> 5*5*5 = 125
				mid -= _3vowel * alph * i-2;
				// - 3 same alph ( excluding 3 same vowel) -> 21
				mid -= alph_vowel * alph * i-2;
			} else if(i>=5) {
				// - 3 spec ( including 3 same spec) -> 3*3*3 = 27
				mid -= alph * alph * Math.pow((alph + spec), i-5) * 27;
				// - 3 vowel ( including 3 same vowel) -> 5*5*5 = 125
				mid -= _3vowel * Math.pow((alph + spec), i-4) * alph * 2;
				mid -= alph * Math.pow((alph + spec), i-5) * 125 * alph;
				// - 3 same alph ( excluding 3 same vowel) -> 21
				mid -= alph_vowel * Math.pow((alph + spec), i-4) * alph * 2;
				//System.out.println(alph_vowel * Math.pow((alph + spec), i-4) * alph * 2);
				mid -= alph * Math.pow((alph + spec), i-5) * alph_vowel * alph;
			}
			System.out.println(new DecimalFormat("#").format(mid));
			count += mid;
		}
		System.out.println(new DecimalFormat("#").format(count));
	}
}