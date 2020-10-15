package Misc;
//package Misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TheLeastRoundWay {
	static PrintWriter out;
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		out = new PrintWriter(System.out);
		
		new TheLeastRoundWay().solve(in,out);
		out.close();
	}
	
	int[][] memoT;
	int[][] memoF;
	int N;
	int[][] twos;
	int[][] fives;
	final int oo = (int) 1e9;
	private void solve(InputReader in, PrintWriter out) {
		N = in.nextInt();
		memoT = new int[N][N];
		memoF = new int[N][N];
		twos = new int[N][N];
		fives = new int[N][N];
	
		int iz=-1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int x = in.nextInt();
				if(x==0) iz=i;
				while(x%2==0 && x!=0) {
					twos[i][j]++;
					x/=2;
				}
				while(x%5==0 && x!=0) {
					fives[i][j]++;
					x/=5;
				}
			}
			Arrays.fill(memoT[i], -1);
			Arrays.fill(memoF[i], -1);
		}

		int zerosT = dpT(0, 0);
		int zerosF = dpF(0, 0);
		int minZeros = Math.min(zerosF, zerosT);

		if(iz>=0 && minZeros>1) {
			out.println(1);
			for(int i=0; i<iz; i++) {
				out.print("D");
			}
			for(int j=0; j<N-1; j++) {
				out.print("R");
			}
			for(int i=iz; i<N-1; i++) {
				out.print("D");
			}
		} else {
			out.println(minZeros);
			if(zerosT < zerosF) {
				dpT2(0, 0);
			} else {
				dpF2(0, 0);
			}
		}
	}
	
	//BACKTRACING not really dp (no, not backtracking)
	private void dpT2(int i, int j) {
		if(i==N-1 && j==N-1) return;
		
		int down = dpT(i+1, j);
		int right = dpT(i, j+1);
		
		if(down<right) {
			out.print("D");
			dpT2(i+1,j);
		} else {
			out.print("R");
			dpT2(i,j+1);
		}		
	}
	 //BACKTRACING not really dp
	private void dpF2(int i, int j) {
		if(i==N-1 && j==N-1) return;
		
		int down = dpF(i+1, j);
		int right = dpF(i, j+1);
		
		if(down<right) {
			out.print("D");
			dpF2(i+1,j);
		} else {
			out.print("R");
			dpF2(i,j+1);
		}		
	}

	private int dpT(int i, int j) {
		if(i>=N || j>=N) return oo;
		if(i==N-1 && j==N-1) return twos[i][j];
		if(memoT[i][j] != -1) return memoT[i][j];
		return memoT[i][j] = twos[i][j] + Math.min(dpT(i+1,j), dpT(i, j+1));
	}
	
	private int dpF(int i, int j) {
		if(i>=N || j>=N) return oo;
		if(i==N-1 && j==N-1) return fives[i][j];
		if(memoF[i][j] != -1) return memoF[i][j];
		return memoF[i][j] = fives[i][j] + Math.min(dpF(i+1,j), dpF(i, j+1));
	}
	
	static class InputReader {
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

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
