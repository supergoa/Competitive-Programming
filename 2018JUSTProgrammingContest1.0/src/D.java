import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class D{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new D().solve(in,out);
		in.close();
		out.close();
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

	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		int w;
		 public Pair(int a, int b, int c) {
			 x=a;
			 y=b;
			 w=c;
		 }
		// Kruskal's sorts by lowest weight and greedily adds the lowest one if not in the same DSU
		@Override
		public int compareTo(Pair o) {
			return w - o.w;
		}
	}
	
	int N,M,K;
	ArrayList<Pair>[] pairs;
	boolean important[];
	boolean visited[];
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			N = in.nextInt();
			M = in.nextInt();
			K = in.nextInt();
			important = new boolean[N];
			pairs = new ArrayList[N];
			for(int n=0;n<N;n++) pairs[n] = new ArrayList<>();
			for(int m=0; m<M; m++) {
				int u = in.nextInt()-1;
				int v = in.nextInt()-1;
				int c = in.nextInt();
				pairs[u].add(new Pair(u,v,c));
				pairs[v].add(new Pair(v,u,c));
			}
			for(int k=0; k<K; k++) {
				int x = in.nextInt()-1;
				important[x] = true;
			}
			long min = Long.MAX_VALUE;
			for(int mask=0; mask < 1<<N; mask++) {
				PriorityQueue<Pair> pq = new PriorityQueue<>();
				boolean good = true;
				for(int a=0; a<N; a++) {
					if(important[a] && ((1<<a)&mask)==0) {
						good = false;
						break;
					}
				}
				if(!good) continue;
				for(int i=0; i<N; i++) {
					if(((1<<i)&mask)==0) continue;
					for(Pair p : pairs[i]) {
						if((1<<p.y&mask)==0) continue;
						pq.add(p);
					}
				}
				
				DSU dsu = new DSU(N);
				int minWeight = 0;
				while (!pq.isEmpty()) {
					Pair p = pq.poll();
					int x = p.x;
					int y = p.y;
					int w = p.w;
					if(dsu.root(x) == dsu.root(y)) {
						continue;
					}
					dsu.union(x, y);
					minWeight += w;
				}
				min = Math.min(min, minWeight);
				
			}
			out.println(min);
		}
		
	}
}
