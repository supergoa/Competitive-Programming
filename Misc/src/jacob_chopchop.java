import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.InputMismatchException;

public class jacob_chopchop implements Runnable {

	public static void main(String[] args) {
		new Thread(null, new jacob_chopchop(), "name", (1L << 30)).start();
	}

	static class Solver {

		int N, a[], l[], r[];

		boolean good(int L, int R) {
			if (L >= R)
				return true;
			for (int i = L, j = R; i <= j; i++, j--) {
				if (l[i] < L && r[i] > R)
					return good(L, i - 1) && good(i + 1, R);
				if (l[j] < L && r[j] > R)
					return good(L, j - 1) && good(j + 1, R);
			}
			return false;
		}

		void precomp() {
			HashMap<Integer, Integer> prev = new HashMap<>();
			l = new int[N];
			r = new int[N];
			for (int i = 0; i < N; i++) {
				l[i] = prev.getOrDefault(a[i], -1);
				prev.put(a[i], i);
			}
			prev.clear();
			for (int i = N - 1; i >= 0; i--) {
				r[i] = prev.getOrDefault(a[i], N);
				prev.put(a[i], i);
			}
		}

		void solve(int testNumber, FastScanner s, PrintWriter out) {
			N = s.nextInt();
			a = s.nextIntArray(N);
			compress(a);
			precomp();
			out.println(good(0, N - 1) ? 1 : 0);
		}

		void compress(int[] arr) {
			HashMap<Integer, Integer> map = new HashMap<>();
			for (int i : arr)
				map.put(i, map.getOrDefault(i, map.size()));
			for (int i = 0; i < arr.length; i++)
				arr[i] = map.get(arr[i]);
		}

	}

	public void run() {

		FastScanner s = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int T = s.nextInt();
		Solver solver = new Solver();
		for (int t = 1; t <= T; t++)
			solver.solve(t, s, out);

		out.close();

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
