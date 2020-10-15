import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class numways {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new numways().solve(in,out);
		in.close();
		out.close();
	}

	final int MOD = (int) (1e9+7);
	int[] denom;
	int[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int goal = in.nextInt();
			int X = in.nextInt();
			denom = new int[X];
			for(int x=0; x<X; x++) {
				denom[x] = in.nextInt();
			}
			memo = new int[X][goal+1];
			for(int i=0; i<X; i++) {
				Arrays.fill(memo[i], -1);
			}
			out.println(dp(0, goal));
			
		}
		
	}
	private int dp(int i, int goal) {
		if(goal<0) return 0;
		if(i==denom.length) {
			if(goal ==0) {
				return 1;
			}
			return 0;
		}
		if(memo[i][goal] != -1) return memo[i][goal];
		int choose = dp(i, goal-denom[i]);
		int skip = dp(i+1, goal);
		return memo[i][goal] = (choose + skip) % MOD;
	}
}
