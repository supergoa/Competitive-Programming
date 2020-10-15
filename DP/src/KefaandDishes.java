/*import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class KefaandDishes {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new KefaandDishes().solve(in,out);
		in.close();
		out.close();
	}

	int N, M, K;
	int[][] bonusPoints;
	long[][] memo;
	int[] satisfaction;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		satisfaction = new int[N];
		for(int n=0; n<N; n++) {
			satisfaction[n] = in.nextInt();
		}
		memo = new long[1<<N][N];
		for(int i=0; i< 1<<N; i++) {
			Arrays.fill(memo[i], -1);
		}
		bonusPoints = new int[N][N];
		for(int k=0; k<K; k++) {
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			bonusPoints[x][y] = in.nextInt();
		}
		//System.out.println(Integer.toBinaryString((1<<N)-1));
		// make sure 1<<N is IN PARENTHESIS below!
		out.println(dp((1<<N)-1, -1));
	}
	
	final int oo = (int) 1e9;
	private long dp(int mask, int curr) {
		if(Integer.bitCount((1<<N)-1) - Integer.bitCount(mask) == M) {
			//System.out.println("ans " + Integer.toBinaryString(mask) );
			return 0;
		}
		
		if(curr != -1 && memo[mask][curr] != -1) return memo[mask][curr];
		
		long best = -oo;
		for(int next =0; next<N; next++) {
			if(((1<<next) & mask) == 0) continue;
			
			int toAdd = 0;
			if(curr != -1) toAdd = bonusPoints[curr][next];
			//System.out.println(Integer.toBinaryString(mask) + " " + next + " " + toAdd);
			best = Math.max(best, toAdd + satisfaction[next] + dp(mask-(1<<next), next));
			
		}
		//System.out.println(best);
		if(curr!=-1) memo[mask][curr] = best;
		return best;
	}
}*/
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class KefaandDishes {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new KefaandDishes().solve(in,out);
		in.close();
		out.close();
	}

	int N, M, K;
	int[][] bonusPoints;
	long[][] memo;
	int[] satisfaction;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		satisfaction = new int[N];
		for(int n=0; n<N; n++) {
			satisfaction[n] = in.nextInt();
		}
		memo = new long[N][1<<N];
		for(int i=0; i<N; i++) {
			Arrays.fill(memo[i], -1);
		}
		bonusPoints = new int[N][N];
		for(int k=0; k<K; k++) {
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			bonusPoints[x][y] = in.nextInt();
		}
		//System.out.println(Integer.toBinaryString((1<<N)-1));
		// make sure 1<<N is IN PARENTHESIS below!
		out.println(dp((1<<N)-1, -1));
	}
	//
	final int oo = (int) 1e9;
	private long dp(int mask, int curr) {
		if(Integer.bitCount((1<<N)-1) - Integer.bitCount(mask) == M) {
			//System.out.println("ans " + Integer.toBinaryString(mask) );
			return 0;
		}
		
		if(curr != -1 && memo[curr][mask] != -1) return memo[curr][mask];
		
		long best = -oo;
		for(int next =0; next<N; next++) {
			if(((1<<next) & mask) == 0) continue;
			
			int toAdd = 0;
			if(curr != -1) toAdd = bonusPoints[curr][next];
			//System.out.println(Integer.toBinaryString(mask) + " " + next + " " + toAdd);
			best = Math.max(best, toAdd + satisfaction[next] + dp(mask-(1<<next), next));
			
		}
		//System.out.println(best);
		if(curr!=-1) memo[curr][mask] = best;
		return best;
	}
}
