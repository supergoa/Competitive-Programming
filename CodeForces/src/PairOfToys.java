import java.io.PrintWriter;
import java.util.Scanner;

public class PairOfToys {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new PairOfToys().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		long N = in.nextLong();
		long M = in.nextLong();
		long low,high;
		if(M%2==0) {
			low = M/2-1;
			high = M/2+1;
		} else {
			low = M/2;
			high = M/2+1;
		}
		long ans = 0;
		if(low<=N && high<=N && low>0) ans++;
		long minDist = Math.min(low-1, N-high);
		if(minDist<0) minDist = 0;
		ans+=minDist;
		out.println(ans);
	}
}
