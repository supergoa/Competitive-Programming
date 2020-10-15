import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;



public class gold {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new gold().solve(in, out);
		in.close();
		out.close();
	}

	//int maxMoney = Integer.MIN_VALUE;
	int dp(int l, int r) {
		if(memo[l][r]!=-1) return memo[l][r];
		if(l==r) {
			return bags[l];
		}
		//System.out.println("l: "+l + " , " + bags[l] + " r: " + r+ ", " + bags[r]);
		int left = bags[l] - dp(l+1,r);
		int right = bags[r] - dp(l,r-1);
		//System.out.println("Left: " + left + " right: " + right);
		//System.out.println("Max: " + Math.max(left, right));
		return memo[l][r] = Math.max(left, right);//-Math.min(left, right);

	}
	int[] bags;
	int memo[][];
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int N = in.nextInt();
			bags = new int[N];
			memo = new int[N+1][N+1];
			for(int i=0; i<N+1; i++) {
				Arrays.fill(memo[i], -1);
			}
			for(int n=0; n<N; n++) {
				bags[n]=in.nextInt();
			}
			//System.out.println(Arrays.toString(bags));
			out.println(dp(0,N-1));
		}
	}
}
