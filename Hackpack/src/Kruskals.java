import java.util.PriorityQueue;
import java.util.Scanner;

public class Kruskals {
	static class DSU {
		int[] id;
		int size; // how many vertices not fully connected
		DSU(int x) { //x is size
			size = x + 1;
			id = new int[size]; // disj relation
			for (int i = 0; i < size; ++i) {
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
	
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		int w;
		
		// Kruskal's sorts by lowest weight and greedily adds the lowest one if not in the same DSU
		@Override
		public int compareTo(Pair o) {
			return w - o.w;
		}

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N, M;
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
		System.out.println(minWeight);
		scan.close();
	}
}
