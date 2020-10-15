import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class B {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in,out);
		out.close();
	}
	private void solve(InputReader in, PrintWriter out) {
		
		//for(int i=0; i<26; i++) inds[i] = new TreeSet<>();
		
		int N = in.nextInt();
		int M = in.nextInt();
		int Q = in.nextInt();
		
		String S = in.next();
		String T = in.next();
		
		int[] cumsum = new int[N+1];
		for(int s=1; s<=N; s++) {
			if(S.charAt(s-1)==T.charAt(0) && (s+M-1)<=N && S.substring(s-1, s+M-1).equals(T))
				cumsum[s] = 1;
			cumsum[s] += cumsum[s-1];
			//System.out.println(cumsum[s]);
		}
		
		
		for(int q=0; q<Q; q++) {
			int l = in.nextInt();
			int r = in.nextInt();
			if(r-l+1<M) {out.println(0);continue;}
			if(r-M+1 <0 || l-1<0) {out.println(0);continue;}
			int ans = cumsum[r-M+1]-cumsum[l-1];
			out.println(ans);
		}
	}
	public static class InputReader {
	    public BufferedReader reader;
	    public StringTokenizer tokenizer;

	    public InputReader(InputStream stream) {
	        reader = new BufferedReader(new InputStreamReader(stream), 32768);
	        tokenizer = null;
	    }

	    public String next() {
	        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
	            try {
	                tokenizer = new StringTokenizer(reader.readLine());
	            } catch (IOException e) {
	                throw new RuntimeException(e);
	            }
	        }
	        return tokenizer.nextToken();
	    }

	    public BigInteger nextBigInt() {
	        return new BigInteger(next());
	    }

	    public int nextInt() {
	        return Integer.parseInt(next());
	    }

	    public double nextDouble() {
	        return Double.parseDouble(next());
	    }

	    public long nextLong() {
	        return Long.parseLong(next());
	    }

	}
}
