import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class longpath {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new longpath().solve(scan, out);
		scan.close();
		out.close();
	}

	int V;
	private void solve(Scanner in, PrintWriter out) {
		int C = in.nextInt();
		for(int c=0; c<C; c++) {
			V = in.nextInt();
			int E = in.nextInt();
			
			ArrayList<Edge> adj = new ArrayList<>();
			ArrayList<Edge> adj2 = new ArrayList<>();
			for(int e=0; e<E; e++) {
				int a = in.nextInt();
				int b = in.nextInt();
				int d = in.nextInt();
				adj.add(new Edge(a, b, d));
				adj2.add(new Edge(a, b, -d));
			}
			System.out.println(bellmanFord(new boolean[V], V, adj, 0) + " " + (-bellmanFord(new boolean[V], V, adj2, 0)));
		}
	}
	
	public static long oo = 123456789876543L;
	public static long bellmanFord(boolean[] negCycles, int n, ArrayList<Edge> edges, int source) {
		int[] pred = new int[n];
		long[] dist = new long[n];
		Arrays.fill(pred, -1);
		Arrays.fill(dist, oo - 1L);

		dist[source] = 0;
		boolean changed = true;
		for(int x = 0; x < n - 1 && changed; x++) {
			changed = false;
			for(Edge e : edges)
				if(dist[e.u] + e.w < dist[e.v]) {
					dist[e.v] = dist[e.u] + e.w;
					pred[e.v] = e.u;
					changed = true;
				}
		}
		for(Edge e : edges)
			if(dist[e.u] + e.w < dist[e.v])
				negCycles[e.u] = negCycles[e.v] = true; // Usually return failure here
		return dist[n-1];
	}


	class Edge {
	    int u;
	    int v;
	    long w;
	    public Edge(int v, int u, long w) {
	        this.u = v;
	        this.v = u;
	        this.w = w;
	    }
	}
}
