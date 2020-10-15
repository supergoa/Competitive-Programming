import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class cables {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new cables().solve(in,out);
		in.close();
		out.close();
	}

	class Point {
		int x;int y;
		public Point(int a, int b) {
			x=a;
			y=b;
		}
	}
	class Dist implements Comparable<Dist>{
		int p1id, p2id;
		double dist;
		public Dist (double a, int b, int c) {
			dist = a;
			p1id = b;
			p2id = c;
		}
		@Override
		public int compareTo(Dist o) {
			return Double.compare(dist, o.dist);
		}
	}
	

	public class DSU {
		int[] id;
		int size;
		DSU(int x) { //x is size
			size = x + 1;
			id = new int[size]; // disj relation
			for (int i = 0; i < size; ++i) {
				id[i] = i;
			}
		}
		int root(int a) { //get root of a. "id[a] = get(id[a])" makes retrieval O(1) on successive calls
			return id[a] == a ? a : (id[a] = root(id[a]));
		}
		
		void union(int a, int b) {
			if(root(a) == root(b)) return;
			id[root(a)] = id[root(b)];
			--size;
		}
	}

	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			Point[] pts = new Point[N];
			for(int n=0; n<N; n++) {
				pts[n] = new Point(in.nextInt(), in.nextInt());
			}
			ArrayList<Dist> dists = new ArrayList<>();
			for(int n=0; n<N; n++) {
				for(int nn=0; nn<N; nn++) {
					if(n==nn) continue;
					dists.add(new Dist(Math.sqrt(Math.abs(pts[n].x-pts[nn].x)*Math.abs(pts[n].x-pts[nn].x) +
										Math.abs(pts[n].y-pts[nn].y)*Math.abs(pts[n].y-pts[nn].y)), n, nn));
				}
			}
			Collections.sort(dists);
			double ans = 0;
			DSU dsu = new DSU(N);
			//int count = 0;
			for(int n=0; n<dists.size(); n++) {
				if(dsu.size == 2) break;
				if(dsu.root(dists.get(n).p1id) == dsu.root(dists.get(n).p2id)) {
					continue;
				}
				ans += dists.get(n).dist;
				dsu.union(dists.get(n).p1id, dists.get(n).p2id);
			}
			System.out.printf("%.2f\n",ans);
		}
		
	}
}
