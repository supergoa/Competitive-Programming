import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class bustour {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new bustour().solve(in, out);
		in.close();
		out.close();
	}

	final int oo = (int) (1e7 + 7);
	int[][] memo1;
	int[][] memo2;
	int[][] adj;
	int N;

	private void solve(Scanner in, PrintWriter out) {
		int count = 1;
		while (in.hasNext()) {
			
			N = in.nextInt();
			int M = in.nextInt();

			boolean even = N % 2 == 0;
			adj = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(adj[i], oo);
				adj[i][i] = 0;
			}
			for (int m = 0; m < M; m++) {
				int u = in.nextInt();
				int v = in.nextInt();
				int t = in.nextInt();
				adj[u][v] = t;
				adj[v][u] = t;
			}
			
			for(int k=0; k<N; k++) {
				for(int j=0; j<N; j++) {
					for(int i=0; i<N; i++) {
						adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
					}
				}
			}

			memo1 = new int[(1<<N)][N];
			memo2 = new int[(1<<N)][N];
			for(int n=0; n<(1<<N); n++) {
				Arrays.fill(memo1[n], oo);
				Arrays.fill(memo2[n], oo);
			}
			
			for (int mask=0; mask < (1<<N); mask++) {
				if(even && Integer.bitCount(mask)!=N/2) continue;
				if(!even && (Integer.bitCount(mask)!=N/2 && Integer.bitCount(mask)!=N/2+1)) continue;
				if(!((mask&1)!=0 && (mask&(1<<(N-1)))==0) &&
				   !((mask&1)==0 && (mask&(1<<(N-1)))!=0)) continue;
				//System.out.println(Integer.toBinaryString(mask));
				for (int i = 0; i < N; i++) {
					if((mask&(1<<i))==0) continue;
					
					if((mask&1)==0) memo1[mask][i] = dp1(mask - (1<<i), i);
					else memo2[mask][i] = dp2(mask - (1<<i), i);
				}
			}
			
			int ans = oo;
			int needed = N/2;
			for (int mask=0; mask < (1<<N); mask++) {
				if(Integer.bitCount(mask)!=needed) continue;
				if(!((mask&1)!=0 && (mask&(1<<(N-1)))==0)) continue;
				
				int part1 = mask;
				int part2 = ((1<<N)-1) ^ mask;
				int bestThere = oo;
				for(int i=0; i<N; i++) {
					if((part1 & (1<<i))==0) continue;
					for(int j=0; j<N; j++) {
						if((part2 & (1<<j))==0) continue;
						bestThere = Math.min(bestThere, memo2[part1][i] + adj[i][j] + memo1[part2][j]);
					}
				}
				
				part1 = part1 - 1 + (1<<(N-1));
				part2 = part2 + 1 - (1<<(N-1));
				int bestBack = oo;
				for(int i=0; i<N; i++) {
					if((part1 & (1<<i))==0) continue;
					for(int j=0; j<N; j++) {
						if((part2 & (1<<j))==0) continue;
						bestBack = Math.min(bestBack, memo1[part1][i] + adj[i][j] + memo2[part2][j]);
					}
				}
				ans = Math.min(ans, bestThere + bestBack);
			}
			System.out.println("Case "+ (count++) + ": " + ans);
		}
		
	}

	private int dp1(int mask, int prev) {
		if(memo1[mask][prev] != oo) return memo1[mask][prev];
		if(mask==0) return memo1[mask][prev] = 0;
		if (Integer.bitCount(mask) == 1) {
			if((mask & (1<<(N-1))) != 0) return memo1[mask][prev] = adj[prev][N-1];
			else return memo1[mask][prev] = oo/2;
		}

		int best = oo;
		for (int i = 0; i < N; i++) {
			if (i == N-1 || ((1 << i) & mask) == 0) continue;
			best = Math.min(best, adj[prev][i] + dp1(mask - (1 << i), i));
		}
		
		return memo1[mask][prev] = best;
	}
	
	private int dp2(int mask, int prev) {
		if(memo2[mask][prev] != oo) return memo2[mask][prev];
		if(mask==0) return memo2[mask][prev] = 0;
		if (Integer.bitCount(mask) == 1) {
			if((mask &1) != 0) return memo2[mask][prev] = adj[prev][0];
			else return memo2[mask][prev] = oo/2;
		}
		
		int best = oo;
		for (int i = 0; i < N; i++) {
			if (i == 0 || ((1 << i) & mask) == 0) continue;
			best = Math.min(best, adj[prev][i] + dp2(mask - (1 << i), i));
		}
		return memo2[mask][prev] = best;
	}
	
}
