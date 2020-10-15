import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class rounding {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new rounding().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		long N = in.nextLong();
		
		ArrayList<Long> ans = new ArrayList<>();
		for(int i=0; i<=63; i++) {
			for(int j=0; j<=26; j++) {
				//System.out.println(j + " " + power(5,j));
				BigInteger pow = new BigInteger(Long.toString(power(2, i))).multiply(new BigInteger(Long.toString(power(5, j))));
				long p = 0;
				try {
					p = Long.parseLong(pow.toString());
					
				} catch (Exception e) {
					continue;
				}
				if(N%p==0) {
					ans.add(p);
				}
			}
		}
		Collections.sort(ans);
		out.println(ans.size());
		for(Long i : ans) {
			out.println(i);
		}
		
	}

	private long power(long base, long p) {
		long s = 1;
		for(int i=0; i<p; i++) {
			s*=base;
		}
		return s;
	}
}