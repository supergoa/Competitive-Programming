import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Ktree {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Ktree().solve(in,out);
		in.close();
		out.close();
	}

	final int MOD = (int) 1e9 + 7;
	int N,K,D;
	int[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		K = in.nextInt();
		D = in.nextInt();
		memo = new int[N+1][2];
		for(int n=0; n<N; n++) {
			Arrays.fill(memo[n], -1);
		}
		if(D>K) {
			//System.out.println("wtf");
			out.print(0);
		} else {
			out.println(dp(0, false));
		}
	}
	private int dp(int weight, boolean atLeastD) {
		if(weight > N) {
			return 0;
		}
		if(weight == N && atLeastD) {
			return 1;
		}
		int ans =0;
		if(memo[weight][atLeastD?0:1] != -1) return memo[weight][atLeastD?0:1];
		
		for(int child=0; child<K; child++) {
			ans += dp(weight + child + 1, atLeastD || (child+1) >= D) % MOD;
			ans %= MOD;
		}
		return memo[weight][atLeastD?0:1] = ans % MOD;
	}
}
