import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		while(T-->0) {
			int S = in.nextInt();
			int total = S;
			while(S >= 10) {
				int tens = S/10;
				S -= tens * 10;
				S += tens;
				total += tens;
			}
			out.println(total);
		}
		
	}
}
