import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class B3 {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B3().solve(in,out);
		out.close();
	}
	//s
	private void solve(InputReader in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0;t<T; t++) {
			int N = in.nextInt();
			int A = in.nextInt();
			long ans = 0;
			
			if(A>=N) ans = N;
			if((A-1)*2<=N) ans = Math.max(ans, A-1)*2;
			if(((A-1)*2)+1<=N) ans = Math.max(ans,(A-1)*2+1);
			
			int low = 1;
			int high = N*(N+1)/2;
			int mid = 0;
			int target = N*A;
			if(N-((A-1)*2+1)>0) {
				//System.out.println("hai");
				while(high-low>=5) {
					System.out.println(low + " " + mid + " " + high);
					mid = (low+high)/2;
					int sum = mid*(mid+1)/2 + target-mid;
					if(sum>target) {
						high = Math.max(low, mid-1);
					}
					if(sum<target) {
						low = Math.min(mid+1, high);
					}
				}
			}
			System.out.println(low + " " + mid + " " + high + " " + ans);
			System.out.println("------------------------");
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
