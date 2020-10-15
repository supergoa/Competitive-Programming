import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class juice {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new juice().solve(in, out);
		in.close();
		out.close();
	}

	int[] energy;
	int[] capacity;
	ArrayList<Integer>[] adj;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		energy = new int[N+1];
		capacity = new int[N+1];
		
		adj = new ArrayList[N+1];
		for(int n=0; n<N+1; n++) adj[n] = new ArrayList<>();
		for(int n=1; n<=N; n++) {
			int p = in.nextInt();
			int r = in.nextInt();
			int c = in.nextInt();
			adj[p].add(n);
			energy[n] = r;
			capacity[n] = c;
		}
		
		int ans = 0;
		for(int a:adj[0]) {
			int[] ret = dp(a);
			int max = 0;
			for(int i=0;i<=capacity[a]; i++) {
				max = Math.max(max, ret[i]);
			}
			ans+=max;
		}
		System.out.println(ans);
	}
	private int[] dp(int cur) {
		int[] dp = new int[101];
		for(int i=energy[cur]; i<=capacity[cur]; i++) {
			dp[i] = 1;
		}
		//System.out.println("cur " + cur + ": " +Arrays.toString(dp));
		for(Integer child:adj[cur]) {
			int[] ret = dp(child);
			
			int[] newRes = new int[101];
			for(int i=0; i<=capacity[cur]; i++) {
				for(int j=0; j<=capacity[child]; j++) {
					if(j+i>capacity[cur]) break;
					//if((ret[j]+dp[i])==4) System.out.println(j+" "+i+ " " +" "+ret[j] + " " + dp[j]+" "+ (ret[j]+dp[i]));
					newRes[i+j] = Math.max(newRes[i+j], ret[j]+dp[i]);
				}
			}
			dp = newRes;
			
		}
		//System.out.println("after: cur " + cur + ": " +Arrays.toString(dp));
		return dp;
	}
}