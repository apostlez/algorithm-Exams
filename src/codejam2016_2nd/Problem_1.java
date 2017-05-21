
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;

public class Problem_1 {

    static BufferedWriter out;
	public static void main(String[] args) {
		try {
            out = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].lastIndexOf('.')) + ".out"));
			start(args[0]);
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	static void start(String filename) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
		int tc = sc.nextInt();
		while(tc-- > 0) {
			double rx = sc.nextInt();
			double ry = sc.nextInt();
			double en = sc.nextInt();
			int result = calculate(rx, ry, en);
			System.out.println(result);
			out.write(String.valueOf(result));
			out.newLine();
		}
	}
	
	static int calculate(double rx, double ry, double en) {
		double dgreeA = Math.toDegrees(Math.atan(ry/rx) * 2);
		double dgreeB = Math.toDegrees(Math.atan(ry/(en-rx)) * 2);
		return (int) ((int) (Math.ceil(2 * Math.PI * ry * (dgreeA+dgreeB) / 360)) + en);
	}
}
