import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.StringTokenizer;

public class H {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new H().solve(in,out);
		out.close();
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
	private void solve(InputReader in, PrintWriter out) {
		int T = in.nextInt();
		HashSet<Integer> diffInds = new HashSet<>();
		int N,M;
		char[] s;
		for(int t=0; t<T; t++) {
			diffInds.clear();
			N = in.nextInt();
			M = in.nextInt();
			s = in.next().toCharArray();
			for(int c =0; c<N/2; c++) {
				if(s[c]!=s[N-1-c]) {
					diffInds.add(c);
				}
			}
			long ans = 0;
			for(int m=0; m<M; m++) {
				int p = in.nextInt()-1;
				char c = in.next().toCharArray()[0];
				s[p] = c;
				if(p >= N/2) {
					if(s[p]==s[N-p-1]) {
						if(diffInds.contains(N-p-1)) {
							diffInds.remove(N-p-1);
						}
					} else {
						diffInds.add(N-p-1);
					}
					if(diffInds.size()==0) {
						ans++;
					}
				} else {
					if(s[p]==s[N-1-p]) {
						if(diffInds.contains(p)) {
							diffInds.remove(p);
						}
					} else {
						diffInds.add(p);
					}
					if(diffInds.size()==0) {
						ans++;
					}
				}
			}
			out.println(ans);
		}
	}
	
}
