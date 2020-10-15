import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class help {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new help().solve(in, out);
		in.close();
		out.close();
	}
	
	int D ;
	int N ;
	int M ;
	int[] values;
	int[] times;
	long[][] memo;
	final long oo = (long) 1e9;
	private void solve(Scanner scan, PrintWriter out) {
		D = scan.nextInt();
		for(int d=0; d<D; d++) {
			N = scan.nextInt();
			M = scan.nextInt();
			
			values = new int[N];
			times = new int[N];
			for(int n=0; n<N; n++) {
				times[n] = scan.nextInt();
				values[n] = scan.nextInt();
			}
			memo = new long[N][M+1];
			for(int n=0; n<N; n++) {
				Arrays.fill(memo[n],-1);
			}
			out.println(dp(0, M));
		}
	}
	private long dp(int ind, int timeLeft) {
		if(timeLeft<0) {
			return -oo;
		}
		if(ind==N) {
			return 0;
		}
		if(memo[ind][timeLeft] != -1) return memo[ind][timeLeft];
		long choose = values[ind] + dp(ind+1, timeLeft-times[ind]);
		long skip =  dp(ind+1, timeLeft);
		return memo[ind][timeLeft] = Math.max(choose, skip);
	}
}
