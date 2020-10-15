import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Bumped {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Bumped().solve(in,out);
		in.close();
		out.close();
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		int F = in.nextInt();
		int S = in.nextInt();
		int T = in.nextInt();
		
		ArrayList<Edge>[] adj = new ArrayList[N*2];
		for(int i=0; i<N*2; i++) {
			adj[i] = new ArrayList<>();
		}
		for(int m=0; m<M; m++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			adj[a].add(new Edge(b, c));
			adj[b].add(new Edge(a, c));
			adj[a+N].add(new Edge(b+N, c));
			adj[b+N].add(new Edge(a+N, c));
		}
		for(int f=0; f<F; f++) {
			int a = in.nextInt();
			int b = in.nextInt();
			adj[a].add(new Edge(b+N, 0));
		}
		long[] dists = dijkstras(S, adj);
		System.out.println(Math.min(dists[T], dists[T+N]));
		
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
	        State currPathState = pq.poll();
	        if(dists[currPathState.v] < currPathState.d) continue;
	        for(Edge e : adj[currPathState.v]) {
	            if(e.w + currPathState.d < dists[e.v]) {
	                dists[e.v] = e.w + currPathState.d;
	                prevs[e.v] = currPathState.v;
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
