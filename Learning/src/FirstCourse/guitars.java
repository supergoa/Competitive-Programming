package FirstCourse;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class guitars {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new guitars().solve(scan, out);
		scan.close();
		out.close();
	}
	class DSU {
		int[] id;
		int size; // how many vertices not fully connected
		DSU(int x) { //x is size
			size = x;
			id = new int[size+1]; // disj relation
			for (int i = 0; i < size+1; ++i) {
				id[i] = i;
			}
		}
		int root(int a) { //get root of a. "id[a] = get(id[a])" makes retrieval O(1) on successive calls
			return id[a] == a 
					? a 
					: (id[a] = root(id[a]));
		}
		
		void union(int a, int b) {
			if(root(a) == root(b)) return;
			id[root(a)] = id[root(b)];
			--size;
		}
	}
	
	class Pair implements Comparable<Pair>{
		int x;
		int y;
		int w;
		
		// Kruskal's sorts by lowest weight and greedily adds the lowest one if not in the same DSU
		@Override
		public int compareTo(Pair o) {
			return w - o.w;
		}

	}

	private void solve(Scanner scan, PrintWriter out) {
		int T, N, M;
		T = scan.nextInt();
		for(int t=0; t<T; t++) {
			N = scan.nextInt();
			M = scan.nextInt();
			
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			for(int m=0; m<M; m++) {
				Pair p = new Pair();
				p.x = scan.nextInt(); // vertex a
				p.y = scan.nextInt(); // vertex b
				p.w = scan.nextInt(); // weight of the edge connecting the vertices
				pq.add(p);
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
			if(dsu.size>1) {
				out.println(-1);
			} else {
				out.println(minWeight);
			}
		}
		
	}
}
