import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Joysticks {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Joysticks().solve(in,out);
		in.close();
		out.close();
	}

	
	int[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		int A = in.nextInt();
		int B = in.nextInt();
		
		memo = new int[300][300];
		for(int i=0; i<300; i++) {
			Arrays.fill(memo[i], -1);
		}
		out.print(dp(A,B));
	}
	private int dp(int a, int b) {
		if(a<0 || b<0) return -1;
		if(a==0 || b==0) return 0;
		if(memo[a][b]!=-1) return memo[a][b];
		
		int ansA = 1 + dp(a+1, b-2);
		int ansB = 1 + dp(a-2, b+1);
		return memo[a][b]= Math.max(ansA, ansB);
	}
}
