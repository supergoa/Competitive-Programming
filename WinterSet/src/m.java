import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class m {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new m().solve(in,out);
		in.close();
		out.close();
	}

	final int oo = (int)1e9;
	int[][] memo;
	String word1, word2;
	private void solve(Scanner in, PrintWriter out) {

		int N = in.nextInt();
		String word = in.next();
		
		int best = N;
		for(int n=1; n<N; n++) {
			word1 = word.substring(0, n);
			word2 = word.substring(n, N);
			memo = new int[word1.length()][word2.length()];
			for(int i=0; i<word1.length(); i++) {
				Arrays.fill(memo[i], -1);
			}
			
			int ans = dp(0,0);
			best = Math.min(best, ans);
		}
		System.out.println(best);
	}
	private int dp(int i, int j) {
		if(i==word1.length()&&j==word2.length()) return 0;
		if(i==word1.length()) return Math.abs(word2.length()-j);
		if(j==word2.length()) return Math.abs(word1.length()-i);
		if(memo[i][j]!=-1) return memo[i][j];
		
		int ans1 = oo;
		int ans2 = oo;
		if(word1.charAt(i) == word2.charAt(j)) ans1 = dp(i+1,j+1);
		else {
			ans1 = 1+dp(i+1, j);
			ans2 = 1+dp(i, j+1);
		}
		return memo[i][j] = Math.min(ans1, ans2);
	}
}
