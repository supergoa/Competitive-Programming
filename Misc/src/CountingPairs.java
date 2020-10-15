import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CountingPairs {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("C:\\Users\\Nick\\Downloads\\local2016Data (1)\\divide\\divide.in"));
		PrintWriter out = new PrintWriter(System.out);
		new CountingPairs().solve(in,out);
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		//System.out.println(Long.MAX_VALUE - 250000000000L);
		int N = in.nextInt();
		long start = System.currentTimeMillis();
		for(int n=0; n<N; n++) {
			int P = in.nextInt();
			int[] arr = new int[P];
			long[] freq = new long[10000001];
			boolean[] dontNeed = new boolean[P];
			int numDontNeed = 0;
			
			for(int p=0;p<P;p++) {
				arr[p] = in.nextInt();
				freq[arr[p]]++;
				if(freq[arr[p]]>1) {
					dontNeed[p] = true;
					numDontNeed++;
				}
			}
			
			int[] newArr = new int[P-numDontNeed];
			int ind=0;
			for(int p=0;p<P;p++) {
				if(dontNeed[p]) continue;
				newArr[ind++] = arr[p];
			}
			Arrays.sort(newArr);
			
			int maxInd = 0;
			for(int i=0; i<ind; i++) {
				if(newArr[i]*2 > newArr[ind-1]) break;
				maxInd = i;
			}
			long ans = freq[0] * (P-freq[0]);
			long[] sieve = new long[10000002];
			for(int i=0; i<ind; i++) {
				if(newArr[i]==0) continue;
				//System.out.println(sieve[newArr[i]] + "  " + freq[newArr[i]]);
				ans += sieve[newArr[i]] * freq[newArr[i]];
				for(int j=newArr[i]+newArr[i]; j<=10000001; j+=newArr[i]) {
					sieve[j]+=freq[newArr[i]];
				}
			}
			out.println("Test case #"+(n+1)+": "+ans);
			
		}
		System.out.println(System.currentTimeMillis()-start);
		
	}
	static class FastScanner {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public FastScanner(InputStream stream) {
			this.stream = stream;
		}

		public FastScanner(File f) throws FileNotFoundException {
			this(new FileInputStream(f));
		}

		public FastScanner(String s) {
			this.stream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
		}

		int read() {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		boolean isEndline(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String next() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public String nextLine() {
			int c = read();
			while (isEndline(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isEndline(c));
			return res.toString();
		}

		// Jacob Garbage

		public int[] nextIntArray(int N) {
			int[] ret = new int[N];
			for (int i = 0; i < N; i++)
				ret[i] = this.nextInt();
			return ret;
		}

		public int[][] next2DIntArray(int N, int M) {
			int[][] ret = new int[N][];
			for (int i = 0; i < N; i++)
				ret[i] = this.nextIntArray(M);
			return ret;
		}

		public long[] nextLongArray(int N) {
			long[] ret = new long[N];
			for (int i = 0; i < N; i++)
				ret[i] = this.nextLong();
			return ret;
		}

		public long[][] next2DLongArray(int N, int M) {
			long[][] ret = new long[N][];
			for (int i = 0; i < N; i++)
				ret[i] = nextLongArray(M);
			return ret;
		}

		public double[] nextDoubleArray(int N) {
			double[] ret = new double[N];
			for (int i = 0; i < N; i++)
				ret[i] = this.nextDouble();
			return ret;
		}

		public double[][] next2DDoubleArray(int N, int M) {
			double[][] ret = new double[N][];
			for (int i = 0; i < N; i++)
				ret[i] = this.nextDoubleArray(M);
			return ret;
		}

	}
}
