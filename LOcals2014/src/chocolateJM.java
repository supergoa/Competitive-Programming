import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;

public class chocolateJM {
	static class Solver {

		HashMap<Character, Integer> hm1 = new HashMap<>(), hm2 = new HashMap<>();

		char[] a = "_SRT".toCharArray();
		char[] b = "_VSC".toCharArray();

		int id1(char c) {
			return hm1.get(c);
		}

		int id2(char c) {
			return hm2.get(c);
		}

		void precomp() {
			for (int i = 0; i < 4; i++) {
				hm1.put(a[i], i);
				hm2.put(b[i], i);
			}
		}

		int N, shape[][], type[][], clues[][][][];

		void solve(int testNumber, FastScanner s, PrintWriter out) {
			N = s.nextInt();
			clues = new int[N][][][];
			for (int i = 0; i < N; i++) {
				int x = s.nextInt();
				int y = s.nextInt();
				clues[i] = new int[x][y][2];
				for (int xx = 0; xx < x; xx++)
					for (int yy = 0; yy < y; yy++) {
						char[] c = s.next().toCharArray();
						clues[i][xx][yy][0] = id1(c[0]);
						clues[i][xx][yy][1] = id2(c[1]);
					}
			}

			type = new int[3][3];
			shape = new int[3][3];
			ArrayList<int[]> p = perms(9);
			String[] types = "SV SS SC RV RS RC TV TS TC".split(" ");
			int[][] nums = new int[9][2];
			for (int i = 0; i < 9; i++) {
				nums[i][0] = id1(types[i].charAt(0));
				nums[i][1] = id2(types[i].charAt(1));
			}
			pp: for (int[] a : p) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						shape[i][j] = nums[a[3 * i + j]][0];
						type[i][j] = nums[a[3 * i + j]][1];
					}
				}
				cloop: for (int[][][] clue : clues) {
					int R = clue.length, C = clue[0].length;
					for (int _r = 0; _r + R <= 3; _r++)
						spot: for (int _c = 0; _c + C <= 3; _c++) {
							for (int i = 0, r = _r + i; i < R; i++, r++)
								for (int j = 0, c = _c + j; j < C; j++, c++) {
									if (clue[i][j][0] != 0 && clue[i][j][0] != shape[r][c])
										continue spot;
									if (clue[i][j][1] != 0 && clue[i][j][1] != type[r][c])
										continue spot;
								}
							continue cloop;
						}
					continue pp;

				}
				break;
			}
			out.printf("Puzzle #%d:%n", testNumber);
			out.println(board());

		}

		ArrayList<int[]> perms(int N) {
			ArrayList<int[]> ret = new ArrayList<>();
			int[] a = new int[N];
			boolean[] o = new boolean[N];
			permutations(ret, a, o, 0, N);
			return ret;
		}

		void permutations(ArrayList<int[]> a, int[] pm, boolean[] used, int ind, int N) {
			if (ind == N) {
				a.add(pm.clone());
				return;
			}
			for (int next = 0; next < N; next++) {
				if (!used[next]) {
					pm[ind] = next;
					used[next] = true;
					permutations(a, pm, used, ind + 1, N);
					used[next] = false;
				}
			}
		}

		String board() {
			StringBuilder ret = new StringBuilder();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++)
					ret.append(String.format("%s ", "" + a[shape[i][j]] + b[type[i][j]]));
				ret.append('\n');
			}
			return ret.toString();
		}

	}

	public static void main(String[] args) {

		FastScanner s = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		Solver solver = new Solver();
		solver.precomp();
		int T = s.nextInt();
		for (int t = 1; t <= T; t++)
			solver.solve(t, s, out);

		out.close();

	}

	static int swap(int a, int b) {
		return a;
	}

	static Object swap(Object a, Object b) {
		return a;
	}

	static String ts(Object... o) {
		return Arrays.deepToString(o);
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
