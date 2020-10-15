import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class routes {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new routes().solve(scan, out);
		scan.close();
		out.close();
	}

	int V;
	private void solve(Scanner in, PrintWriter out) {
		int C = in.nextInt();
		for(int c=0; c<C; c++) {
			V = in.nextInt();
			int E = in.nextInt();
			int Q = in.nextInt();
			
			ArrayList<Edge>[] adj = new ArrayList[V+Q];
			for(int v=0; v<V; v++) {
				adj[v] = new ArrayList<>();
			}
			for(int e=0; e<E; e++) {
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				int d = in.nextInt();
				adj[a].add(new Edge(b, d));
				adj[b].add(new Edge(a, d));
			}
			long[] ans = dijkstras(0, adj);
			for(int q=0; q<Q; q++) {
				int house = in.nextInt()-1;
				System.out.println(ans[house]);
			}
		}
	}
	
	public static long[] dijkstras(int start, ArrayList<Edge>[] adj) {
	    int n = adj.length;
	    long[] dists = new long[n];
	    int[] prevs = new int[n];
	    Arrays.fill(dists, Long.MAX_VALUE);
	    Arrays.fill(prevs, -1);
	    dists[start] = 0;
	    PriorityQueue<State> pq = new PriorityQueue<State>();
	    pq.add(new State(start, 0));
	    while(!pq.isEmpty()) {
	        State s = pq.poll();
	        if(dists[s.v] < s.d) continue;
	        for(Edge e : adj[s.v]) {
	            if(e.w + s.d < dists[e.v]) {
	                dists[e.v] = e.w + s.d;
	                prevs[e.v] = s.v;
	                pq.add(new State(e.v, dists[e.v]));
	            }
	        }
	    }
	    return dists;
	}


	static class Edge {
	    int v;
	    long w;
	    public Edge(int v, long w) {
	        this.v = v;
	        this.w = w;
	    }
	}

	static class State implements Comparable<State> {
	    int v;
	    long d;
	    public State(int v, long d) {
	        this.v = v;
	        this.d = d;
	    }
	    public int compareTo(State s) {
	        return Long.compare(d, s.d);
	    }
	}
}
