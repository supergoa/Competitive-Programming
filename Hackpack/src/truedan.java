import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class truedan {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new truedan().solve(in,out);
		in.close();
		out.close();
	}
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int n,m,d,s;
			n = in.nextInt();
			m = in.nextInt();
			d = in.nextInt()-1;
			s = in.nextInt()-1;
			ArrayList<Edge>[] adj =  new ArrayList[n];
			//ArrayList<Integer>[] adjS =  new ArrayList[n];
			for(int i=0; i<n; i++) {
				adj[i] = new ArrayList<>();
				//adjS[i] = new ArrayList<>();
			}
			for(int mm=0; mm<m;mm++) {
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				int w = in.nextInt();
				adj[a].add(new Edge(b,w));
				adj[b].add(new Edge(a,w));
			}
			long sAns = dijkstras(s, adj);
			long dAns = dijkstras(d, adj);
			//System.out.println(sAns + " " + dAns);
			if(sAns%2==0 && sAns/2==dAns) {
				out.println("Sawyer's style is undeniable!");
			} else if (sAns/2.0<dAns) {
				out.println("Sawyer's style is undeniable!");
			} else {
				out.println("I can't believe Danny won!");
			}
		}
		
	}
	private long dijkstras(int start, ArrayList<Edge>[] adj) {
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
	    return dists[0];
	}


	public class Edge {
	    int v;
	    long w;
	    public Edge(int v, long w) {
	        this.v = v;
	        this.w = w;
	    }
	}

	public class State implements Comparable<State> {
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
