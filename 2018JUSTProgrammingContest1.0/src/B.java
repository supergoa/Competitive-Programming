import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class B {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in,out);
		out.close();
	}
	//s
	private void solve(InputReader in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0;t<T; t++) {
			int N = in.nextInt();
			int A = in.nextInt();
			long ans = 0;
			if((A-1)*2<=N) {
				ans = (A-1)*2;
				if(((A-1)*2)+1<=N) {
					ans = (A-1)*2+1;
					
					int nLeft = N - ((A-1)*2+1);
					for(int i=3; i<1000000; i++) {
						if((A-1)*i<=nLeft) {
							ans += (A-1);
							nLeft -= (A-1)*i;
						} else {
							while(nLeft>=i) {
								ans += 1;
								nLeft-=i;
							}
							break;
						}
					}
				}
			}
			else {
				ans = N;
			}
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
