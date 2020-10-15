import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Mashmokh {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Mashmokh().solve(in,out);
		in.close();
		out.close();
	}
	final int MOD = 1000000007;
	int N,K;
	int[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		K = in.nextInt();
		
		//arr = new int[K];
		
		memo = new int[N+1][K+1];
		for(int n=0; n<N+1; n++) {
			Arrays.fill(memo[n], -1);
		}
		
		int finalAns = 0;
		for(int i=1; i<=N; i++) {
			
			finalAns = (finalAns + dp(K-1,i))%MOD;
			//System.out.println("HERE");
		}
		out.println(finalAns);
		
	}
	//int[] arr;
	private int dp(int numLeft, int curNum) {
		//arr[numLeft] = curNum;
		if(curNum > N || numLeft < 0) return 0;
		if(numLeft == 0) {
			if(curNum<=N) return 1;
			return 0;
		}
		if(memo[curNum][numLeft] != -1) return memo[curNum][numLeft];
		int ans = 0;
		for(int i=1;i<=N/curNum+1; i++) {
			//System.out.println(numLeft-1 + " " + curNum*i);
			ans = (ans + dp(numLeft-1, curNum*i)) % MOD;
		}
		return memo[curNum][numLeft]=ans;
	}
}
