import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new test().solve(in,out);
		in.close();
		out.close();
	}

	int[][] memo;
	int N;
	String str;
	private void solve(Scanner in, PrintWriter out) {
		
		while(true) {
			str = in.next();
			if(str.charAt(0)=='0') break;
			N = str.length();
			int mask = (1<<N)-1;
			memo = new int[N+1][mask+10];
			
			int best = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				initmemo(mask);
				int dist = initCost(i);
				best = Math.min(best, dist + dp(i, mask - (1<<i)));
			}
			out.println(best);
		}
		
	}

	private void initmemo(int mask) {
		
		for(int i=0; i<N+1; i++) {
			Arrays.fill(memo[i], -1);
		}		
	}

	private int initCost(int i) {
		return str.charAt(i) - 'A';
	}

	private int dp(int cur, int mask) {
		if(mask==0) return N;
		if(memo[cur][mask]!=-1) return memo[cur][mask];
		
		int best = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			if(((1<<i) & mask)==0) continue;
			
			int dist = distFrom(cur, i, mask);
			best = Math.min(best, dist + dp(i, mask - (1<<i)));
			
		}
		return memo[cur][mask] = best;
	}

	private int distFrom(int cur, int i, int mask) {
		int cost = 0;
		
		int curLetter = str.charAt(cur)-'A';
		int toLetter = str.charAt(i)-'A';
		int diff = Math.abs(curLetter-toLetter);
		cost += Math.min(diff, 26-diff);
		
		if(cur > i) {
			int bigMask = mask & ((1<<(cur+1))-1);
			int littleMask = mask & ((1<<(i+1))-1);
			cost += (cur-i) - Integer.bitCount(bigMask - littleMask);
		} else {
			int bigMask = mask & ((1<<(i+1))-1);
			int littleMask = mask & ((1<<(cur+1))-1);
			cost += (i-cur) - Integer.bitCount(bigMask - littleMask);
		}
		return cost;
	}
}