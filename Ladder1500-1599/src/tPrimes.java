import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class tPrimes {
	static PrintWriter out;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out = new PrintWriter(System.out);
		new tPrimes().solve(in,out);
		in.close();
		out.close();
	}

	HashMap<Long, Boolean> memo;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		HashSet<Long> hs = preComp();
		for(int n=0; n<N; n++) {
			long x = in.nextLong();
			if(hs.contains(x)) {
				out.println("YES");
			} else {
				out.println("NO");
			}
		}
		
	}

	private HashSet<Long> preComp() {
		HashSet<Long> hs = new HashSet<Long>();
		
		boolean[] notPrime = new boolean[1000003];
		notPrime[1]=true;
		for(long i=2; i<=1000001; i++) {
			if(!notPrime[(int) i]) {
				for(long j=2*i; j<=1000001; j=j+i) {
					notPrime[(int) j] = true;
				}
			}
		}
		for(long i=0; i<notPrime.length; i++) {
			if(!notPrime[(int) i]) {
				hs.add(i*i);
			}
		}
		return hs;
	}
}
