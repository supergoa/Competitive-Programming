import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Chocolate {
	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Chocolate().solve(in,out);
		out.close();
	}

	int[][] permutations;
	private void solve(FastScanner in, PrintWriter out) {
		int N = in.nextInt();
		
		// 9! = 362880 -- > the [i][j]th cell stores the a number 1-9 for the ith permutation
		permutations = new int[362880][9];
		fillPerm();

		// If the [i][j]th cell of 'permutations' stores the a number 1, it represents "SV"... etc.
		HashMap<Integer, String> strs = new HashMap<>();
		strs.put(0,"__");
		strs.put(1,"SV");
		strs.put(2,"SS");
		strs.put(3,"SC");
		strs.put(4,"RV");
		strs.put(5,"RS");
		strs.put(6,"RC");
		strs.put(7,"TV");
		strs.put(8,"TS");
		strs.put(9,"TC");
		
		for(int n=0; n<N; n++) {
			int numClues = in.nextInt();
			ArrayList<String[][]> clues = new ArrayList<>();

			for(int i=0; i<numClues; i++) {
				int R = in.nextInt();
				int C = in.nextInt();
				clues.add(new String[R][C]);
				
				for(int r=0; r<R; r++) {
					for(int c=0; c<C; c++) {
						// Store the candy code in format ex. "TV"
						clues.get(i)[r][c] = (in.next());
					}
				}
			}
			
			int[][] curPerm = new int[3][3];
			for(int i=0; i<permutations.length; i++) {
				
				// store current permutation in a 3x3 for easy comparison to clue grid
				int ind = 0;
				for(int a=0; a<3; a++) {
					for(int b=0;b<3; b++) {
						curPerm[a][b] = permutations[i][ind++];
					}
				}
				
				// iterate over all clues
				int numGoodClues = 0;
				for(int j=0; j<numClues; j++) {
					boolean goodLocation = true;
					
					// try all grid spaces for the upper left corner of the clue
					for(int r=0; r<3; r++) {
						for(int c=0; c<3; c++) {
							
							// try next grid spot if this one doesn't align on the grid
							if(clues.get(j).length+r>3 || clues.get(j)[0].length+c>3) continue;
							
							// go over each cell in the current permutation and clue
							// and ensure values are the same
							goodLocation = true;
							for(int a=0; a<clues.get(j).length; a++) {
								for(int b=0; b<clues.get(j)[0].length; b++) {
									
									// Anything can match a clue's blank cell
									if(clues.get(j)[a][b].equals("__")) {continue;}
									
									boolean flag1 = false; boolean flag2 = false;
									
									// Check that the first characters equal each other
									if(clues.get(j)[a][b].charAt(0) == '_' 
										|| clues.get(j)[a][b].charAt(0) == strs.get(curPerm[r+a][c+b]).charAt(0))
											flag1 = true;
									
									// Check that the second characters equal each other
									if(clues.get(j)[a][b].charAt(1) == '_' 
										|| clues.get(j)[a][b].charAt(1) == strs.get(curPerm[r+a][c+b]).charAt(1))
											flag2 = true;
									
									// If one of these conditions isn't met, this grid spot is invalid
									// therefore, go to the next grid spot
									if(!flag1 || !flag2) {
										goodLocation = false;
										break;
									}
								}
								
								// Go to next grid spot
								if(!goodLocation) break;
							}
							// Stop checking grid spots if this clue is already valid
							if(goodLocation) break;
						}
						
						// Stop checking grid spots if this clue is already valid
						if(goodLocation) break;
					}
					
					// Count number of successful clues
					if(goodLocation) {numGoodClues++;}
				}
				
				// If successful clues = original number of clues --> found ans
				if(numGoodClues == numClues) {
					break;
				}
			}
			
			out.println("Puzzle #"+(n+1)+":");
			for(int a=0; a<3; a++) {
				for(int b=0;b<3; b++) {
					out.print(strs.get(curPerm[a][b]) + " ");
				}
				out.println();
			}
			out.println();
			
		}
	}
	
	// Generate all permutations (the hard way)
	private void fillPerm() {
		int ind = 0;
		for(int a=1; a<=9; a++) {
			for(int b=1; b<=9; b++) {
				if(a==b) continue;
				for(int c=1; c<=9; c++) {
					if(a==c || b ==c) continue;
					for(int d=1; d<=9; d++) {
						if(d==a || d==b || d==c) continue;
						for(int e=1; e<=9; e++) {
							if(e==a || e==b || e==c || e==d) continue;
							for(int f=1; f<=9; f++) {
								if(f==a || f==b || f==c || f==d || f==e) continue;
								for(int g=1; g<=9; g++) {
									if(g==a || g==b || g==c || g==d || g==e || g==f) continue;
									for(int h=1; h<=9; h++) {
										if(h==a || h==b || h==c || h==d || h==e || h==f || h==g) continue;	
										for(int i=1; i<=9; i++) {
											if(i==a || i==b || i==c || i==d || i==e || i==f || i==g || i==h) continue;	
											permutations[ind][0] = a;
											permutations[ind][1] = b;
											permutations[ind][2] = c;
											permutations[ind][3] = d;
											permutations[ind][4] = e;
											permutations[ind][5] = f;
											permutations[ind][6] = g;
											permutations[ind][7] = h;
											permutations[ind][8] = i;
											ind++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	// Unnecessary FastScanner
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