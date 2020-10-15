import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MaxPathSum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new MaxPathSum().solve(in,out);
		in.close();
		out.close();
	}
	
	int[][] arr;
	int[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		arr = new int[100][100];
		for(int n=1; n<=100; n++) {
			for(int k=0; k<n; k++) {
				arr[n-1][k] = in.nextInt();
			}
			//System.out.println(Arrays.toString(arr[n-1]));
		}
		memo = new int[100][100];
		for(int x=0; x<100; x++) {
			Arrays.fill(memo[x], -1);
		}
		out.println(dp(0,0));
	}

	private int dp(int i, int j) {
		if(i==100) {
			return 0;
		}
		if(memo[i][j] != -1) return memo[i][j];
		int left = arr[i][j] + dp(i+1, j);
		int right =  arr[i][j] + dp(i+1, j+1);
		memo[i][j] = Math.max(left, right);
		return Math.max(left, right);
	}
}
