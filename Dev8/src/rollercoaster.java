import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class rollercoaster {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new rollercoaster().solve(in,out);
		in.close();
		out.close();
	}

	int[] fun;
	int[] dizzy;
	int[] dp;
	int N,K,L;
	final int oo = (int) 1e9;
	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			N = in.nextInt(); // number sections
			K = in.nextInt(); // decreased dizzyness per section with eyes closes
			L = in.nextInt(); // max dizzyness
			if(N==0 && K==0 && L==0) break;
			
			fun = new int[N];
			dizzy = new int[N];
			
			for(int n=0; n<N; n++) {
				fun[n] = in.nextInt();
				dizzy[n] = in.nextInt();
			}
			dp = new int[20001];
			Arrays.fill(dp, oo);
			
			
			dp[0] = 0;
			for(int i=0; i<N; i++) {
				for(int f=20000; f>=0; f--) {
					if(dp[f]+dizzy[i] <= L) // open eyes
						dp[f + fun[i]] = Math.min(dp[f + fun[i]], dp[f]+dizzy[i]);
					if(dp[f]!=oo) // close eyes
						dp[f] = Math.max(0, dp[f]-K);
				}
			}
			
			int maxi = 0;
			for(int i=0; i<20001; i++) {
				if(dp[i]<=L) maxi = i;
			}
			System.out.println(maxi);
		}
		
	}
	/*
	private int dp(int i, int d) {
		if(d>L || d<0) return -oo;
		if(i==N) return 0;
		if(memo[i][d]!=-1) return memo[i][d];
		int open = dp(i+1, d+dizzy[i]) + fun[i];
		int closed = dp(i+1, d-K);
		return memo[i][d]=Math.max(open, closed);
	}*/
}
