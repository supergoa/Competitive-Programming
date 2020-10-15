import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new D().solve(in,out);
		in.close();
		out.close();
	}

	ArrayList<Integer>[] adj;
	int[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		int C = in.nextInt();
		int P = in.nextInt();
		
		memo = new int[C][C];
		for(int i=0; i<C; i++) Arrays.fill(memo[i], -1);
		
		adj = new ArrayList[C];
		for(int i=0; i<C; i++) adj[i] = new ArrayList<>();
		
		for(int p=0; p<P; p++) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			adj[b].add(a);
		}
		
		long ans = 0;
		for(int i=0; i<C; i++) {
			for(int j=i+1; j<C; j++) {
				if(i==j) continue;
				ans += dp(i, j);
			}
		}
		out.println(ans);
	}

	private long dp(int node1, int node2) {
		if(node1==node2) return 0;
		if(node1==0 || node2==0) return 1;
		
		if(memo[node1][node2]!=-1) return memo[node1][node2];
		
		int ans = 0;
		if(node1 < node2) {
			for(int a : adj[node2]) {
				ans += dp(node1, a);
			}
		} else {
			for(int a : adj[node1]) {
				ans += dp(a, node2);
			}
		}
		return memo[node1][node2] = ans>0?1:0;
	}
}
