import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class E {
	public static void main(String[] args) {
		FastReader in = new FastReader();
		PrintWriter out = new PrintWriter(System.out);
		new E().solve(in, out);
		//in.close();
		out.close();
	}

	class rgby {
		int rLocx;
		int rLocy;
		int length;

		public rgby(int rlocx, int rlocy, int l) {
			rLocx = rlocx;
			rLocy = rlocy;
			length = l;
		}

		@Override
		public String toString() {
			return rLocx + " " + rLocy + " " + length;
		}
	}

	int N, M, Q;

	private void solve(FastReader in, PrintWriter out) {
		N = in.nextInt();
		M = in.nextInt();
		Q = in.nextInt();
		char[][] grid = new char[N + 1][M + 1];
		int[][] csred = new int[N + 1][M + 1];
		int[][] csgreen = new int[N + 1][M + 1];
		int[][] csyellow = new int[N + 1][M + 1];
		int[][] csblue = new int[N + 1][M + 1];
		for (int n = 1; n <= N; n++) {
			String line = in.next();
			for (int m = 1; m <= M; m++) {
				grid[n][m] = line.charAt(m - 1);
			}
		}
		// System.out.println();
		// System.out.println(Arrays.toString(csred[0]));
		for (int n = 1; n <= N; n++) {
			int en = Math.max(0, n - 1);
			for (int m = 1; m <= M; m++) {
				// System.out.println(n+", "+m);
				int em = Math.max(0, m - 1);
				if (grid[n][m] == 'R') {
					csred[n][m] = csred[en][m] + csred[n][em] - csred[en][em] + 1;
				}
				if (grid[n][m] == 'Y') {
					csyellow[n][m] = csyellow[en][m] + csyellow[n][em] - csyellow[en][em] + 1;
				}
				if (grid[n][m] == 'G') {
					csgreen[n][m] = csgreen[en][m] + csgreen[n][em] - csgreen[en][em] + 1;
				}
				if (grid[n][m] == 'B') {
					csblue[n][m] = csblue[en][m] + csblue[n][em] - csblue[en][em] + 1;
				}
			}
			// System.out.println(Arrays.toString(csred[n]));
		}
		int size = 0;
		rgby[] found = new rgby[62501];
		for (int n = 0; n <= N - 1; n++) {
			for (int m = 0; m <= M - 1; m++) {
				if (grid[n][m] == 'R' && grid[n][m + 1] == 'G' && grid[n + 1][m] == 'Y' && grid[n + 1][m + 1] == 'B') {

					int r = 1;
					int frontx = n;
					int fronty = m;
					int backx = frontx;
					int backy = fronty;
					// System.out.println(frontx +""+fronty+""+backx+""+backy);
					while (inBounds(frontx, fronty) && inBounds(backx, backy)
							&& csred[frontx][fronty] - csred[frontx][backy - 1] - csred[backx - 1][fronty]
									+ csred[backx - 1][backy - 1] == r * r) {
						backx--;
						backy--;
						// System.out.println(csred[frontx][fronty] - csred[frontx-1][backy-1] -
						// csred[backx-1][fronty-1] + csred[backx-1][backy-1]);
						// System.out.println(frontx +""+fronty+""+backx+""+backy);
						r++;
					}
					r--;

					// System.out.println();
					int g = 1;
					frontx = n;
					fronty = m + 1;
					backx = frontx;
					backy = fronty;
					// System.out.println(frontx +""+fronty+""+backx+""+backy);
					while (inBounds(frontx, fronty) && inBounds(backx, backy)
							&& csgreen[frontx][fronty] - csgreen[frontx][backy - 1] - csgreen[backx - 1][fronty]
									+ csgreen[backx - 1][backy - 1] == g * g) {
						fronty++;
						backx--;
						// System.out.println(frontx +""+fronty+""+backx+""+backy);
						g++;
					}
					g--;

					// System.out.println();
					int y = 1;
					frontx = n + 1;
					fronty = m;
					backx = frontx;
					backy = fronty;
					// System.out.println(frontx +""+fronty+""+backx+""+backy);
					while (inBounds(frontx, fronty) && inBounds(backx, backy)
							&& csyellow[frontx][fronty] - csyellow[frontx][backy - 1] - csyellow[backx - 1][fronty]
									+ csyellow[backx - 1][backy - 1] == y * y) {
						frontx++;
						backy--;
						// System.out.println(frontx +""+fronty+""+backx+""+backy);
						y++;
					}
					y--;

					// System.out.println();
					int b = 1;
					frontx = n + 1;
					fronty = m + 1;
					backx = frontx;
					backy = fronty;
					// System.out.println(frontx +""+fronty+""+backx+""+backy);
					while (inBounds(frontx, fronty) && inBounds(backx, backy)
							&& csblue[frontx][fronty] - csblue[frontx][backy - 1] - csblue[backx - 1][fronty]
									+ csblue[backx - 1][backy - 1] == b * b) {
						frontx++;
						fronty++;
						// System.out.println(frontx +""+fronty+""+backx+""+backy);
						b++;
					}
					b--;

					int l = Math.min(r, y);
					l = Math.min(l, b);
					l = Math.min(l, g);

					found[size++] = new rgby(n, m, l);
				}
			}
		}
		// System.out.println(Arrays.toString(found));
		// for(int q=0; q<Q; q++) {
		int count = 0;
		for (int q = 0; q < Q; q++) {
			int x1 = in.nextInt();
			int y1 = in.nextInt();
			int x2 = in.nextInt();
			int y2 = in.nextInt();
			// ==System.out.println("case "+ q);
			int best = 0;
			for (int s = 0; s < size; s++) {
				int lx = found[s].rLocx;
				int ly = found[s].rLocy;
				if (lx > x2 || ly > y2 || lx < x1 || ly < y1) {
					continue;
				}
				int max = Math.min(x2 - lx, y2 - ly);
				max = Math.min(max, lx + 1 - x1);
				max = Math.min(max, ly + 1 - y1);
				max = Math.min(max, found[s].length);

				// System.out.println(lx + " lxly " + ly);
				// System.out.println(x2-lx);
				// System.out.println(y2-ly);
				// System.out.println(lx+1-x1);
				// System.out.println(ly+1-y1);
				// System.out.println("max loc: "+max);
				// Math.min(max, found[s].length);
				// max = Math.max(max, 0);

				best = Math.max(best, max);
				// System.out.println("current best:" + best);

			}
			// if(best * best * 4 == 16) System.out.println(a+" "+b+" "+c+" "+d);
			out.println(best * best * 4);
			// System.out.println();

		}
	}

	private boolean inBounds(int x, int y) {
		if (x < 1 || x > N)
			return false;
		if (y < 1 || y > M)
			return false;
		return true;
	}
	
	static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }

}
