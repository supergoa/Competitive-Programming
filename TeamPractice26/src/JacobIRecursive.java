import java.util.*;
import java.io.*;

public class JacobIRecursive {

	int oo = 987654321;
	int W, N, time[], weight[];
	int M, totalWeight[], maxTime[];
	int memo[];

	// the complexity of a DP is equal to [memo space] * [complexity of dp method]
	// here, our memo space is 2^N, as is our dp method
	// this gives a total complexity of 4^N

	// returns the minimum total time to handle this group
	int dp(int mask) {
		// if our mask is 0, there are no bits on. if there are no bits on, there's
		// nobody left to send over.
		if (mask == 0)
			return 0;
		// DP step
		if (memo[mask] != -1)
			return memo[mask];

		int best = oo;
		// we start the submask at 1 because 0 is a subset of everything, meaning
		// we can call dp(mask) -> dp(mask) -> dp(mask) -> ... infinitely
		for (int submask = 1; submask < M; submask++) {
			// all of the ON bits in submask are also ON in the mask,
			// their bitwise & will have all of the submask's original
			// bits ON, making them equal.
			if ((mask & submask) != submask)
				continue;
			// if the total weight of this group is too much for the bridge,
			// don't try sending it
			if (totalWeight[submask] > W)
				continue;
			// mask - submask === (everybody) - (group sent)
			// this works because submask is a submask of mask
			int cost = maxTime[submask] + dp(mask - submask);
			// min our running best with this possible cost
			best = Math.min(best, cost);
		}
		// save to memo, and we're done!
		return memo[mask] = best;
	}

	void precomp() {
		M = 1 << N;
		totalWeight = new int[M];
		maxTime = new int[M];
		for (int mask = 0; mask < M; mask++) {
			for (int p = 0; p < N; p++) {
				if ((mask & (1 << p)) > 0) {
					// the pth bit is on in the mask
					// ==> person p is in the group
					totalWeight[mask] += weight[p];
					maxTime[mask] = Math.max(maxTime[mask], time[p]);
				}
			}
		}
	}

	void go() {
		Scanner s = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		W = s.nextInt();
		N = s.nextInt();
		time = new int[N];
		weight = new int[N];
		for (int i = 0; i < N; i++) {
			time[i] = s.nextInt();
			weight[i] = s.nextInt();
		}

		long tt = System.currentTimeMillis();
		precomp();

		memo = new int[M];
		Arrays.fill(memo, -1);
		out.println(dp(M - 1));

		System.out.println(System.currentTimeMillis() - tt);

		out.close();
		s.close();

	}

	public static void main(String[] args) {
		new JacobIRecursive().go();
	}
}