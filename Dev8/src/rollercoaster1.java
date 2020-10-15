import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class rollercoaster1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new rollercoaster1().solve(in,out);
		in.close();
		out.close();
	}

	int[] fun;
	int[] dizzy;
	int[][] memo;
	int N,K,L;
	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			N = in.nextInt();
			K = in.nextInt();
			L = in.nextInt();
			if(N==0 && K==0 && L==0) break;
			
			fun = new int[N];
			dizzy = new int[N];
			
			for(int n=0; n<N; n++) {
				fun[n] = in.nextInt();
				dizzy[n] = in.nextInt();
			}
			memo = new int[N+1][L+1];
			for(int n=0; n<N+1; n++) {
				Arrays.fill(memo[n], -1);
			}
			
			int ans = dp(0,0);
			if(ans<0) ans=0;
			System.out.println(ans);
		}
		
	}
	final int oo = (int) 1e9;
	private int dp(int i, int d) {
		if(d>L || d<0) return -oo;
		if(i==N) return 0;
		if(memo[i][d]!=-1) return memo[i][d];
		int open = dp(i+1, d+dizzy[i]) + fun[i];
		int closed = dp(i+1, d-K);
		return memo[i][d]=Math.max(open, closed);
	}
}
