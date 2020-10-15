import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class swan {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new swan().solve(in,out);
		in.close();
		out.close();
	}

	class Point {
		double x,y;
		int id;
		public Point(int id, double a, double b) {
			this.id = id;
			x = a;
			y = b;
		}
	}

	/**
	 2
1 0 100
1000 180
5 90 10
80 30
1000 180
280 40
678 235
995 209
	 */
	ArrayList<int[]> perms;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int K = in.nextInt();
			double A = in.nextInt();
			double S =  in.nextDouble();
			Point start = new Point(K, 1000.0*Math.cos(A/360.0*Math.PI*2), 1000.0*Math.sin(A/360.0*Math.PI*2));
			
			//DSU dsu = new DSU(R);
			//PriorityQueue<Edge> pq = new PriorityQueue<>();;
			Point[] points = new Point[K];
			for(int r=0; r<K; r++) {
				double d = in.nextInt();
				double a = in.nextInt();
				double x = d*Math.cos(a/360.0*Math.PI*2);
				double y = d*Math.sin(a/360.0*Math.PI*2);
				points[r] = new Point(r, x, y);
			}
			
			
			perms = new ArrayList<>();
			fillPerms(new boolean[K], new int[K], 0);
			
			double best = 987654321;
			for(int[] perm: perms) {
				double minDist = 0;
				for(int i=0; i<perm.length; i++) {
					if(i==0) minDist += dist(start, points[perm[i]]);
					else {
						minDist += dist(points[perm[i-1]], points[perm[i]]);
					}
					if(i==perm.length-1) {
						minDist += dist(points[perm[i]], start);
					}
				}
				best = Math.min(best, minDist);
			}
			
			if(best != 987654321) {
				out.printf("%.2f", best/S);
			}
			out.println();
		}
		
	}
	private void fillPerms(boolean[] used, int[] perm, int ind) {
		if(ind == perm.length) {
			perms.add(perm.clone());
			return;
		}
		for(int i=0; i<perm.length; i++) {
			if(!used[i]) {
				int prev = perm[ind];
				perm[ind] = i;
				used[i] = true;
				fillPerms(used, perm, ind+1);
				used[i] = false;
				perm[ind] = prev;
			}
		}
		
	}
	private double dist(Point a, Point b) {
		return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}
}
