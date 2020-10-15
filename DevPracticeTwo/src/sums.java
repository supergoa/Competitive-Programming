import java.io.PrintWriter;
import java.util.Scanner;

public class sums {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new sums().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N =in.nextInt();
		for(int n=0; n<N; n++) {
			int P = in.nextInt();
			long sum = 0;
			long ans = 0;
			for(int p=0; p<=P; p++) {
				sum += (p+1);
				ans += p*sum;
			}
			System.out.println((n+1) + " " + P + " " + ans);
		}
		
	}
}
