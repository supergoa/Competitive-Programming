import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TravelingSalesmanDP {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new TravelingSalesmanDP().solve(in,out);
		in.close();
		out.close();
	}

	double[][] adj;
	double[][] memo;
	int N;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			N = in.nextInt();
			adj = new double[N][N];
			memo = new double[1<<N][N];
			for(int i=0; i<1<<N; i++) {
				Arrays.fill(memo[i], -1);
			}
			for(int n=0; n<N; n++) {
				for(int nn=0; nn<N; nn++) {
					adj[n][nn] = in.nextDouble(); // reads in cost to travel from n to nn.
				}
			}
			
			// (1 << N) - 2 turns off the 1st bit  --> 10, 110, 1110, etc...
			// I did this because for this problem you always start at node 0
			// and have no reason to go to it in the dp
			// make sure - 1<<N - is IN PARENTHESIS below!
			out.printf("%.2f\n", dp((1 << N) - 2, 0));
		}
	}
	final int oo = (int) 1e9;
	private double dp(int mask, int curr) {
		if(mask==0) return adj[curr][0];
		
		if(memo[mask][curr]!=-1) return memo[mask][curr];
		
		double best = oo;
		for(int next=0; next<N; next++) {
			if((mask & (1 << next)) == 0 || curr == next) continue; // ensures that the next bit is ON is mask (havent visited it yet)
			best = Math.min(best, adj[curr][next] + dp(mask-(1<<next), next)); //turns the next bit OFF because now we will be there
		}
		return memo[mask][curr] = best;
	}
}
