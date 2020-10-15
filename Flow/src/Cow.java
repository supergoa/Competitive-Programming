import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Cow {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Cow().solve(in,out);
		in.close();
		out.close();
	}

	class Point {
		int x1,x2,y1,y2,id;
		public Point(int a, int b, int c, int d, int id) {
			x1 = a;
			x2 = b;
			y1 = c;
			y2 = d;
			this.id=id;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int N  = in.nextInt();
			FF ff = new FF(N);
			HashSet<Point> vert = new HashSet<>();
			HashSet<Point> horz = new HashSet<>();
			for(int n=0; n<N; n++) {
				int x1 = in.nextInt();
				int y1 = in.nextInt();
				int x2 = in.nextInt();
				int y2 = in.nextInt();
				
				if(x1==x2) {
					 vert.add(new Point(x1,x2,y1,y2,n));
					 ff.add(n, ff.t, 1);
				} else {
					 horz.add(new Point(x1,x2,y1,y2,n));
					 ff.add(ff.s, n, 1);
				}
			}
			for(Point v: vert) {
				for(Point h: horz) {
					if(Math.max(v.y1, v.y2) >= h.y1 && Math.min(v.y1, v.y2) <= h.y1 && Math.max(h.x1, h.x2) >= v.x1 && Math.min(h.x1, h.x2) <= v.x1) {
						ff.add(h.id, v.id, 1);
					}
				}
			}
			out.println(N-ff.ff());
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
