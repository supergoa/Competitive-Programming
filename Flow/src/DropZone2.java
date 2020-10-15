import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class DropZone2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new DropZone2().solve(in,out);
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
			FF ff = new FF(2);
			ff.add(0, 1, 1);
			ff.add(1, 0, 1);
			ff.add(ff.s, 0, 1);
			ff.add(1, ff.t, 1);
			out.print(ff.ff());
		
		
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
