//package FirstCourse;

import java.io.PrintWriter;
import java.util.Scanner;

public class IsItPrime {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new IsItPrime().solve(scan, out);
		scan.close();
		out.close();
	}

	private void solve(Scanner scan, PrintWriter out) {
		int N = scan.nextInt();
		//precomp
		boolean[] isComposite = new boolean[10000001];
		isComposite[0] = true;
		isComposite[1] = true;
		for(int i=2; i<Math.sqrt(10000001)+1; i++) {
			for(int j=i*i; j<10000001; j+=i) {
				isComposite[j] = true;
			}
		}
		for(int n=0; n<N; n++) {
			out.println(isComposite[scan.nextInt()]?0:1);
		}
		out.flush();
	}
}
