import java.util.*;
import java.io.*;

public class JacobI {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int w = s.nextInt(), n = s.nextInt();

		int[][] p = new int[n][2];
		for (int i = 0; i < n; i++) {
			p[i][0] = s.nextInt();
			p[i][1] = s.nextInt();
		}
		int m = 1 << n;
		int[] tw = new int[m], mw = new int[m], dp = new int[m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if ((j & (1 << i)) > 0) {
					tw[j] += p[i][1];
					mw[j] = Math.max(mw[j], p[i][0]);
				}
		Arrays.fill(dp, 123456789);
		dp[0] = 0;
		for (int k = 1; k < m; k++) {
			int j = k;
			while (j > 0) {
				if (tw[j] <= w)
					dp[k] = Math.min(dp[k], mw[j] + dp[k - j]);
				j = (j - 1) & k;
			}
		}
		out.println(dp[m - 1]);
		out.close();
	}
}