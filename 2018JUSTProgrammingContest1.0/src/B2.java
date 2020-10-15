import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class B2 {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B2().solve(in,out);
		out.close();
	}
	//s
	private void solve(InputReader in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0;t<T; t++) {
			long N = in.nextInt();
			long A = in.nextInt();
				
			long low = 1;
			long high = N;
			long mid = 0;
			double average = -1;
			while(low<=high) {
				//System.out.println(low + " " + mid + " " + high);
				mid = (low+high)/2;
				average = ((mid*(mid+1)/2.0)+(N-mid))/N;
				if(average<=A) { // we dont care if this fails so put it first
					low = mid+1;
				}
				else { //always put correct ans second
					high = mid-1; // proof by contradiction: 
								  // assume this line makes the *final* ans wrong --> impossible bc of while condition
				}
			}
			out.println(high);
			
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
