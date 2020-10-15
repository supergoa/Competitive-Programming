import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in, out);
		in.close();
		out.close();
	}

	char[] low, high, str;
	long[][][][] memo;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		memo = new long[10][19][2][19];
		while(T-->0) {
			low = new StringBuilder(in.nextLong()-1 +"").toString().toCharArray();
			high = new StringBuilder(in.nextLong() +"").toString().toCharArray();
			
			
			for(int i=0; i<10; i++) {
				for(int j=0; j<19; j++) {
					for(int k=0; k<2; k++) {
						Arrays.fill(memo[i][j][k], -1);
					}
				}
			}
			
			str = high;
			long ans1 = 0;
			for(int len=1; len<=high.length; len++) {
				boolean bounded = (len==high.length);
				int e = (bounded) ? high[0]-'0' : 9;
				for(int start=1; start<=e; start++) {
					boolean toBound = bounded && start==e;
					ans1 += dp(start, 1, toBound, len);
				}
				System.out.println("ans1 " + ans1);
				ans1=0;
			}
			
			str = low;
			for(int i=0; i<10; i++) {
				for(int j=0; j<19; j++) {
					for(int k=0; k<2; k++) {
						Arrays.fill(memo[i][j][k], -1);
					}
				}
			}
			
			long ans2 = 0;
			for(int len=1; len<=low.length; len++) {
				boolean bounded = (len==low.length);
				int e = (bounded) ? low[0]-'0' : 9;
				for(int start=1; start<=e; start++) {
					boolean toBound = bounded && start==e;
					ans2 += dp(start, 1, toBound, len);
				}
			}
			out.println(ans1-ans2);
		}
		
	}
	private long dp(int start, int ind, boolean b, int len) { // add length to go to
		long ret = 0;
		if(memo[start][ind][b?1:0][len]!=-1) return memo[start][ind][b?1:0][len];
		if(ind + 1 >= len) {
			if(b) {
				if(str[len-1]-'0'>=start) return 1;
				return 0;
			} else {
				return 1;
			}
		}
		
		int s = 0;
		int e = (b) ? str[ind]-'0' : 9;
		for(int i=s; i<=e; i++) {
			boolean fu = (i==e);
			if(!b) fu = false;
			ret += dp(start, ind+1, fu, len);
		}
		return memo[start][ind][b?1:0][len] = ret;
	}
}
