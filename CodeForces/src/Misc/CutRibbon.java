package Misc;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CutRibbon {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new CutRibbon().solve(in,out);
		in.close();
		out.close();
	}
	int N,A,B,C;
	int[] ribs;
	final int oo = (int) 1e9;
	int[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		A = in.nextInt();
		B = in.nextInt();
		C = in.nextInt();
		ribs = new int[3];
		ribs[0]=A;ribs[1]=B;ribs[2]=C;
		
		memo = new int[3][N+1];
		for(int i=0; i<3; i++) {
			Arrays.fill(memo[i], -1);
		}
		
		out.println(dp(0, N));
	}
	private int dp(int ind, int ribLeft) {
		if(ribLeft<0) {
			return -oo;
		}
		if(ind==3) {
			if(ribLeft==0) {
				return 0;
			} else {
				return -oo;
			}
		}
		if(memo[ind][ribLeft]!=-1) return memo[ind][ribLeft];
		int choose = 1+dp(ind, ribLeft-ribs[ind]);
		int leave = dp(ind+1, ribLeft);
		return memo[ind][ribLeft] = Math.max(choose, leave);
		
	}
}
