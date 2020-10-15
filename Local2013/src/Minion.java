import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Minion {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Minion().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int M = in.nextInt();
		for(int m=0; m<M; m++) {
			int T = in.nextInt();
			HashSet<String> trials = new HashSet<>();
			for(int t=0; t<T; t++) {
				trials.add(in.next());
			}
			int N = in.nextInt();
			int E = in.nextInt();
			ArrayList<Edge>[] adj = new ArrayList[N];
			for(int n=0; n<N; n++) adj[n] = new ArrayList<>();
			for(int e=0; e<E; e++) {
				int from = in.nextInt();
				int to = in.nextInt();
				String trial = in.next();
				if(trials.contains(trial)) continue;
				adj[from].add(new Edge(to, 1));
				adj[to].add(new Edge(from, 1));
			}
			long[] dists = dijkstras(0, adj);
			if(dists[N-1]!=Long.MAX_VALUE) {
				out.println(1);
			} else {
				out.println(0);
			}
		}
		
	}
	
	
		// Dijkstra's shortest path O (e + v * log(v))
		// Can easily be modified to return the path using the prev[] array or by making states keep track of parent
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
