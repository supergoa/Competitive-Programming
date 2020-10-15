import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Jedii {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Jedii().solve(in,out);
		in.close();out.close();
	}

	int[] shots;
	int jedi1;
	int jedi2;
	int B;
	long memo[][][];
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			B = in.nextInt();
			shots = new int[B];
			for(int b=0; b<B; b++) {
				shots[b] = in.nextInt();
			}
			Arrays.sort(shots);
			
			int J = in.nextInt();
			jedi1 = in.nextInt();
			jedi2 = 1002;
			long ans;
			memo = new long[B+1][jedi1+1][1003];
			for(int j=0; j<B+1; j++) {
				for(int k=0; k<jedi1+1; k++) {
					Arrays.fill(memo[j][k], -1);
				}
			}
			if(J==2) {
				jedi2 = in.nextInt();
				ans = dp(0,0,0);
			} else {
				ans = dp(0, 0, 1002);
			}
			out.println("Mission #"+(n+1)+": "+ans);
		}
		
	}
	private long dp(int i, int j1, int j2) {
		if(i==B) return 0;
		
		int curShot;
		if(i==0) curShot = shots[i];
		else curShot = shots[i] - shots[i-1];
		
		j1 = Math.max(j1-curShot, 0);
		j2 = Math.max(j2-curShot, 0);
		
		//System.out.println(i + " " + j1 + " " + j2);
		if(memo[i][j1][j2]!=-1) return memo[i][j1][j2];
		
		long ans1 = Integer.MAX_VALUE;
		long ans2 = Integer.MAX_VALUE;
		long ans3 = Integer.MAX_VALUE;
		if(j1==0) ans1 = dp(i+1, jedi1, j2);
		if(j2==0) ans2 = dp(i+1, j1, jedi2);	
		
		ans3 = 1+dp(i+1, j1, j2);
		return memo[i][j1][j2] = Math.min(ans3, Math.min(ans2, ans1));	
	}
}
