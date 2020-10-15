package Misc;
import java.io.PrintWriter;
import java.util.Scanner;

public class BargainingTable {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new BargainingTable().solve(in,out);
		in.close();
		out.close();
		
	}

	int N,M;
	int[][] room;
	private void solve(Scanner in, PrintWriter out) {
		
		N = in.nextInt();
		M = in.nextInt();
		room = new int[N][M];
		for(int n=0; n<N; n++) {
			String string = in.next();
			for(int m=0; m<M; m++) {
				room[n][m] = string.charAt(m)-'0';
			}
		}
		int[][] cumsum = new int[N][M];
		for(int n=0; n<N; n++) {
			for(int m=0; m<M; m++) {
				cumsum[n][m] = room[n][m];
				if (n-1 >= 0) {
					cumsum[n][m] += cumsum[n-1][m];
				}
				if (m-1 >= 0) {
					cumsum[n][m] += cumsum[n][m-1];
				}
				if(n-1 >= 0 && m-1 >= 0) {
					cumsum[n][m] -= cumsum[n-1][m-1];
				}
			}
		}
		int ans = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int i2=i; i2<N; i2++) {
					for(int j2=j; j2<M; j2++) {
						int num = cumsum[i2][j2];
						if (i-1 >= 0) {
							num -= cumsum[i-1][j2];
						}
						if (j-1 >= 0) {
							num -= cumsum[i2][j-1];
						}
						if(i-1 >= 0 && j-1>=0) {
							num += cumsum[i-1][j-1];
						}
						if(num < 1) {
							ans = Math.max((j2-j+1)*2 + (i2-i+1)*2, ans);
						}
					}
				}
			}
		}
		out.println(ans);
	}
}
