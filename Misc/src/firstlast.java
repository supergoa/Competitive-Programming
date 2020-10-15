import java.io.PrintWriter;
import java.util.Scanner;

public class firstlast {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new firstlast().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int[] arr = new int[N];
		int[] dp = new int[N];
		int max = 0;
		for(int i=0; i<N; i++) {
			arr[i] = in.nextInt()-1;
			if(arr[i]!=0) dp[arr[i]] = dp[arr[i]-1] +1;
			else dp[arr[i]] = 1;
			max = Math.max(max, dp[arr[i]]);
		}
		System.out.println(N-max);
	}
}
