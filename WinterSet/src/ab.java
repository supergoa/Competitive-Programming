import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;



public class ab {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new ab().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		ArrayList<Edge>[] adj = new ArrayList[N];
		for(int i=0; i<N; i++) adj[i] = new ArrayList<>();
		
		for(int m=0;m<M;m++) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			int c = in.nextInt();
			adj[a].add(new Edge(b,c));
			adj[b].add(new Edge(a,c));
		}

		long[][] ans=dijkstras(0, adj);
		if(ans[N-1][1]==Long.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(ans[N-1][1]);
		}
	}
	
	public static long[][] dijkstras(int start, ArrayList<Edge>[] adj) {
		   
		int n = adj.length;
	    long[][] dists = new long[n][2];
	    int[] prevs = new int[n];
	    for(int i=0; i<dists.length; i++) Arrays.fill(dists[i], Long.MAX_VALUE);
	    Arrays.fill(prevs, -1);
	    dists[start][0] = (long) 0;
	    PriorityQueue<State> pq = new PriorityQueue<State>();
	    pq.add(new State(start, 0));
	    
	    while(!pq.isEmpty()) {
	        State currPathState = pq.poll();
	        int par = (int) (currPathState.d%2);
	        if(dists[currPathState.v][par] < currPathState.d) continue;
	        for(Edge e : adj[currPathState.v]) {
	        	par = (int) ((e.w + currPathState.d)%2);
	            if(e.w + currPathState.d < dists[e.v][par]) {
	                dists[e.v][par] = e.w + currPathState.d;
	                prevs[e.v] = currPathState.v;
	                pq.add(new State(e.v, dists[e.v][par]));
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
	    @Override
	    public String toString() {
	    	return this.v + " " +this.w;
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
