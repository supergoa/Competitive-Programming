import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class n {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new n().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int K = in.nextInt();
		HashMap<Long, Long> mins = new HashMap<>();
		String s = in.next();
		long best = 0;
		long cur = 0;
		mins.put(0L, -1L);
		for(long n=0; n<N; n++) {
			long add = s.charAt((int) n)=='O'?1:-K;
			cur += add;
			Long minInd = null;
			if(mins.containsKey(cur)) minInd = mins.get(cur);
			if(!mins.containsKey(cur)) mins.put(cur, n);
			if(minInd!=null) best = Math.max(n-minInd, best);
		}
		System.out.println(best);
	}
}
