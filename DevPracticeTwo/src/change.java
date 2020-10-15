

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class change {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new change().solve(in,out);
		in.close();
		out.close();
	}
	
	int[] costs = {25,10,5,1};
	int C;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			C = in.nextInt();
			best = 987654321;
			ans = null;
			rec(0, 0, new int[4]);
			System.out.println((n+1) + " " + ans[0] + " QUARTER(S), " + ans[1] + " DIME(S), " + ans[2] + " NICKEL(S), " + ans[3] + " PENNY(S)");
		}
		
	}
	int best = 987654321;
	int[] ans;
	private void rec(int ind, int total, int[] quan) {
		if(total > C || ind>=4) return;
		if(total==C) {
			int count = 0;
			for(int i:quan) {
				count+=i;
			}
			if(count < best) {
				best = count;
				ans = quan.clone();
			}
			return;
		}
		//System.out.println(Arrays.toString(quan));
		quan[ind]++;
		total += costs[ind];
		rec(ind, total, quan);
		quan[ind]--;
		total -= costs[ind];
		
		rec(ind+1, total, quan);
		
	}
}
