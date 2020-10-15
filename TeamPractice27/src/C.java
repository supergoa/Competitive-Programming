import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in,out);
		in.close();
		out.close();
	}
	
	int len;
	long[][][] memo;
	private void solve(Scanner in, PrintWriter out) {
		long A = in.nextLong();
		long B = in.nextLong();
		// B
		String num = Long.toBinaryString(B);
		len = num.length();
		memo = new long[len][2][100];
		for(int i=0; i<len; i++) {
			for(int j=0; j<2; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		int[] arr = new int[len];
		for(int i=0; i<num.length(); i++) {
			arr[i] = num.charAt(i)-'0';
		}
		long ansB = dp(arr, 0, true, 0); 
		
		// A
		num = Long.toBinaryString(A-1);
		len = num.length();
		memo = new long[len][2][100];
		for(int i=0; i<len; i++) {
			for(int j=0; j<2; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		arr = new int[len];
		for(int i=0; i<num.length(); i++) {
			arr[i] = num.charAt(i)-'0';
		}
		long ansA = dp(arr, 0, true, 0);
		
		out.print(ansB - ansA);
	}

	private long dp(int[] num, int ind, boolean bounded, int sum) {
		if(ind==len) {
			return sum;
		}
		if(memo[ind][bounded?1:0][sum]!=-1) return memo[ind][bounded?1:0][sum];
		
		int max = bounded?num[ind]:1;
		long ans = 0;
		for(int i=0; i<=max; i++) {
			ans+=dp(num, ind+1, (bounded && i==max)?true:false, sum + i);
		}
		
		return memo[ind][bounded?1:0][sum] = ans;
	}
	
}
