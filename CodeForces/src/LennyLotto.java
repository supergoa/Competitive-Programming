import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class LennyLotto {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new LennyLotto().solve(in,out);
		in.close();
		out.close();
	}

	int N,M;
	final int oo = (int) 1e9;
	long [][][] memo1;
	long [][][] memo2;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		memo2 = new long[2001][12][2001];
		for(int i=0; i<2001; i++) {
			for(int j=0; j<12; j++) {
				//Arrays.fill(memo1[i][j], -1);
				Arrays.fill(memo2[i][j], -1);
			}
		}
		for (int t=0; t<T; t++) {
			N = in.nextInt();
			M = in.nextInt();
			
			long ans =0;
			boolean found = false;
			for(int i=1; i<=M/(Math.pow(2, N-1));i++) {
				found = true;
				ans+=dp(i, N-1);
			}
			if(!found) { 
				out.println("Data set "+(t+1)+": " + N +" " +M + " " + 0);
			}
			else {
				out.println("Data set "+(t+1)+": " + N +" " +M + " " + ans);
			}
		}
	}
	private long dp(int last, int numLeft) {
		if(numLeft==0 && last<=M) return 1;
		//if(tooHigh(last, numLeft) > M) return 0;
		long ans = 0;
		if(memo2[last][numLeft][M] != -1) return memo2[last][numLeft][M];
		for(int i=2*last; i<=M/(Math.pow(2, numLeft-1)); i++) {
			ans += dp(i, numLeft-1);
		}
		return memo2[last][numLeft][M] = ans;
	}
	/*private long tooHigh(int last, int numLeft) {
		if(memo1[last][numLeft][M] != -1) return memo1[last][numLeft][M];
		while(numLeft > 0) {
			last*=2;
			numLeft--;
		}
		return memo1[last][numLeft][M] = last;
	}*/
}
