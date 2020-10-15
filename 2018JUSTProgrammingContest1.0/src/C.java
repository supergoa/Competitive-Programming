import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

import H.InputReader;

public class C {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in,out);
		out.close();
	}
	static int gcd(int A, int B) {
		//System.out.println("A: " + A + "   B: " + B);
	    if(B==0)
	        return A;
	    else
	        return gcd(B, A % B);
	}
	private void solve(InputReader in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int m = in.nextInt();
			long ans = 0;
			for(int a=1; a<=m; a++) {
				if(gcd(a,m)!=1) continue;
				ans+=m-1;
			}
			System.out.println(ans);
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
