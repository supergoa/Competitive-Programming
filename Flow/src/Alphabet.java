import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Alphabet {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Alphabet().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			String red = in.next();
			String green = in.next();
			FF ff = new FF(red.length() + green.length());
			for(int r=0; r<red.length(); r++) {
				ff.add(ff.s, r, 1);
			}
			for(int r=0; r<green.length(); r++) {
				ff.add(r+red.length(), ff.t, 1);
			}
			for(int r=0; r<red.length(); r++) {
				for(int g=0; g<green.length(); g++) {
					if(Math.abs(green.charAt(g)-red.charAt(r)) >= 3) {
						ff.add(r, g+red.length(), 1);
					}
				}
			}
			out.println(ff.ff());
			
		}
	}
	
	class FF {
		int[][] cap; boolean[] vis; int n,s,t,oo = (int)(1E9);
		public FF(int size) {n=size+2; s=n-2; t=n-1;
		cap = new int[n][n]; }
		void add(int v1, int v2, int c) { cap[v1][v2] = c; }
		int ff() {
			vis = new boolean[n]; int f=0;
			while(true) {
				Arrays.fill(vis, false); int res = dfs(s,oo);
				if(res==0) {break;} f+=res;
			}
			return f;
		}
		int dfs(int pos, int min) {
			if(pos==t) return min;
			if(vis[pos]) return 0;
			vis[pos] = true; int f = 0;
			for(int i=0; i< n; i++) {
				if(cap[pos][i] > 0 ) f = dfs(i, Math.min(cap[pos][i], min));
				if(f>0) {cap[pos][i] -= f; cap[i][pos] += f; return f;}	
			}
			return 0;
		}
	}
}
