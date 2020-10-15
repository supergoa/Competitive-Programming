package lpc2015;

import java.io.PrintWriter;
import java.util.Scanner;

public class brownie {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new brownie().solve(scan, out);
		
		scan.close();
		out.close();
	}

	private void solve(Scanner scan, PrintWriter out) {
		int N = scan.nextInt();
		for(int n=0; n<N; n++) {
			int students = scan.nextInt();
			int brownies = scan.nextInt();
			int M = scan.nextInt();
			for(int m=0; M<m; m++) {
				int size = scan.nextInt();
				while(size>=brownies) {
					brownies*=2;
				}
				brownies-=size;
			}
		}
		
	}
}
