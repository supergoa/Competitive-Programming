//package FirstCourse;

import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class cpu {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new cpu().solve(scan, out);
		
		scan.close();
		out.close();
	}

	class DSU {
		int size;
		int[] set;
		public DSU(int size) {
			this.size = size+1;
			set = new int[size];
			for(int i=0;i<size;i++) {
				set[i]=i;
			}
		}
		public int root(int a) {
			if(set[a]!=a) {
				return (set[a] = root(set[a]));
			} else {
				return a;
			}
		}
		public void union(int x, int y) {
			if(root(x) == root(y)) return;
			int p = root(x);
			int q = root(y);
			set[p] = set[q];
			size--;
		}
		
	}
	class Pair implements Comparable<Pair>{
		int x;
		int y;
		int w;
		public Pair(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
		@Override
		public int compareTo(Pair o) {
			return w-o.w;
		}
		
	}
	private void solve(Scanner scan, PrintWriter out) {
		int N = scan.nextInt();
			for(int n=0; n<N; n++) {
			int S = scan.nextInt();
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			DSU dsu = new DSU(S);
			for(int i=0; i<S; i++) {
				for(int j=0; j<S; j++) {
					if(i<j) {
						int x = i;
						int y = j;
						int w = scan.nextInt();
						//System.out.println("x: "+x+"y: "+y+"w: "+w);
						pq.offer(new Pair(x,y,w));
					} else {
						scan.nextInt();
					}
				}
			}
			int totalW=0;
			while(!pq.isEmpty()) {
				Pair p = pq.poll();
				int x=p.x;
				int y=p.y;
				int w=p.w;
				if(dsu.root(x) == dsu.root(y)) {
					continue;
				}
				dsu.union(x, y);
				totalW+=w;
			}
			out.println("Design "+(n+1)+": "+totalW+" micrometers");
		}
	}
}
