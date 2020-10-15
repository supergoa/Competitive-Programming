import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ant {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new ant().solve(in,out);
		in.close();
		out.close();
	}

	class Edge implements Comparable<Edge>{
		int v1,v2,w;
		public Edge(int a, int b, int c) {
			v1 = a;
			v2 = b;
			w = c;
		}
		@Override
		public int compareTo(Edge o) {
			return w-o.w;
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
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			DSU dsu = new DSU(in.nextInt());
			int T = in.nextInt();
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for(int t=0; t<T; t++) {
				int x = in.nextInt()-1;
				int y = in.nextInt()-1;
				int w = in.nextInt();
				pq.add(new Edge(x,y,w));
			}
			
			int min = 0;
			while(!pq.isEmpty()) {
				Edge e = pq.poll();
				if(dsu.root(e.v1)==dsu.root(e.v2)) continue;
				dsu.union(e.v1, e.v2);
				min += e.w;
			}
			//System.out.println(dsu.size);
			if(dsu.size>2) {
				System.out.println("Campus #"+(n+1)+": I'm a programmer, not a miracle worker!");
			} else {
				System.out.println("Campus #"+(n+1)+": "+min);
			}
			
		}
		
	}
}
