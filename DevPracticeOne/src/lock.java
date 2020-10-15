import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class lock {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new lock().solve(in,out);
		in.close();
		out.close();
	}

	long[] memo;
	int B;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			B = in.nextInt();
			memo = new long[1<<B];
			Arrays.fill(memo, -1);
			long ans = dp((1<<B)-1);
			out.println((n+1)+ " " + B + " " + ans);
		}
		
	}

	private long dp(int mask) {
		if(Integer.bitCount(mask)==0) return 0;
		if(memo[mask]!=-1) return memo[mask];
		
		long count = 0;
		for(int next=mask; next>0; next = (next - 1) & mask) {
			count += 1 + dp(mask-next);
		}
		return memo[mask] = count;
	}
}
