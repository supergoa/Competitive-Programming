import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Shopping {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Shopping().solve(in,out);
		in.close();out.close();
	}

	int[] items;
	int S;
	long[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			S = in.nextInt();
			items = new int[S];
			memo = new long[S+1][S/2+5];
			for(int s=0; s<=S; s++) Arrays.fill(memo[s], -1);
			for(int s=0; s<S; s++) {
				items[s] = in.nextInt();
			}
			int canTake = Math.max(1, S/2);
			long ans = dp(S-1, canTake);
			out.println("Spree #"+(n+1)+": "+ans);
		}
		
	}
	private long dp(int ind, int canTake) {
		if(ind==0) canTake = Math.min(canTake, ind+1);
		else canTake = Math.min(canTake, (ind+1)/2);
		
		if(ind==-1) return 0;
		if(memo[ind][canTake] != -1) return memo[ind][canTake];
		
		long choose = -987654321;
		long leave = -987654321;
		if(canTake > 0) {
			choose = items[ind] + dp(ind-1, Math.max(0, canTake-1));
		}
		leave = dp(ind-1, Math.max(0, canTake));
		
		return memo[ind][canTake] = Math.max(choose, leave);
	}
}
