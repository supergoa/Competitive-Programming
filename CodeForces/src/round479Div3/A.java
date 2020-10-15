package round479Div3;

import java.io.PrintWriter;
import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new A().solve(scan, out);
		
		scan.close();
		out.close();
	}

	private void solve(Scanner scan, PrintWriter out) {
		int n,k;
		n = scan.nextInt();
		k = scan.nextInt();
		while(k-->0) {
			if(n%10==0) {
				n/=10;
			}
			else {
				n--;
			}
		}
		out.println(n);
	}
}
