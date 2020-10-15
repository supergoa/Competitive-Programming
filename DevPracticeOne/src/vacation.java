import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class vacation {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new vacation().solve(in,out);
		in.close();
		out.close();
	}

	class Point {
		int x,y,id;
		public Point(int id, int a, int b) {
			this.id = id;
			x = a;
			y = b;
		}
	}
	
	class Edge implements Comparable<Edge>{
		int v1,v2;
		double w;
		public Edge(int a, int b, double c) {
			v1 = a;
			v2 = b;
			w = c;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(w,o.w);
		}
	}
	
	public class DSU {
		int[] id;
		int size;
		DSU(int x) {
			size = x+1;
			id = new int[size];
			for(int i=0; i<size; i++) {
				id[i]=i;
			}
		}
		int root(int a) {
			return id[a]==a?a:(id[a]=root(id[a]));
		}
		void union(int a, int b) {
			if(root(a)==root(b)) return;
			id[root(a)] = id[root(b)];
			size--;
		}
	}
	/*
	 *
3
4 0
0 2
2 2
2 4
4 4
5 4
10 10
12 35
64 60
3 7
100 857
1 2
1 3
1 4
1 5
5 2
 0 5
0 10
0 20
0 50
0 25
3 4
1 2
	 */
	ArrayList<int[]> perms;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int R = in.nextInt();
			int B = in.nextInt();
			
			//DSU dsu = new DSU(R);
			//PriorityQueue<Edge> pq = new PriorityQueue<>();;
			Point[] points = new Point[R];
			for(int r=0; r<R; r++) {
				points[r] = new Point(r, in.nextInt(), in.nextInt());
			}
			boolean[][] blocked = new boolean[R][R];
			for(int b=0; b<B; b++) {
				int x = in.nextInt()-1;
				int y = in.nextInt()-1;
				blocked[x][y] = true;
				blocked[y][x] = true;
			}
			
			perms = new ArrayList<>();
			fillPerms(new boolean[R], new int[R], 0);
			
			double best = 987654321;
			for(int[] perm: perms) {
				double minDist = 0;
				for(int i=0; i<perm.length; i++) {
					if(i==0) minDist += dist(new Point(0,0,0), points[perm[i]])+120;
					else {
						minDist += dist(points[perm[i-1]], points[perm[i]]) + 120;
						
						boolean good = true;
						if(blocked[perm[i-1]][perm[i]]) good = false;
						if(!good) {
							minDist = 987654321;
							break;
						}
					}
				}
				best = Math.min(best, minDist);
			}
			
			out.println("Vacation #"+(n+1)+":");
			if(best != 987654321) {
				out.printf("Jimmy can finish all of the rides in %.3f seconds.\n", best);
			} else {
				out.println("Jimmy should plan this vacation a different day.");
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
