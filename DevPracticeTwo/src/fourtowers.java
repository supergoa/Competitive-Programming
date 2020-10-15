import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class fourtowers {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new fourtowers().solve(in,out);
		in.close();
		out.close();
	}

	long[] memo = new long[1001];
	private void solve(Scanner in, PrintWriter out) {
		Arrays.fill(memo, -1);
		for(int i=0; i<1001; i++) {
			memo[i] = rec(i);
		}
		int counter = 1;
		int N = 0;
		while(in.hasNext()) {
			N = in.nextInt();
			System.out.println("Case "+counter+": "+memo[N]);
			counter++;
		}
		
		
	}
	private long rec(int i) {
		if(i==0) return 0;
		if(i==1) return 1;
		if(i==2) return 3;
		if(memo[i]!=-1) return memo[i];
		long best = Long.MAX_VALUE;
		for(int j=1; j<=i-1 && j<=62; j++) {
			best = Math.min(best, rec(i-1-j) + rec2(j));
		}
		return memo[i] = 2*best+1;
	}
	private long rec2(int i) {
	        return (long) (Math.pow(2,i)-1);
	}
}
