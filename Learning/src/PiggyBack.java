import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class PiggyBack {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new PiggyBack().solve(in,out);
		in.close();
		out.close();
	}

	int B,E,P,N,M;
	private void solve(Scanner in, PrintWriter out) {
		B = in.nextInt();
		E = in.nextInt();
		P = in.nextInt();
		N = in.nextInt();
		M = in.nextInt();
		
		ArrayList<Edge>[] adjB = new ArrayList[M];
		for(int i=0; i<N; i++) adjB[i] = new ArrayList<>();
		ArrayList<Edge>[] adjE = new ArrayList[M];
		for(int i=0; i<N; i++) adjE[i] = new ArrayList<>();
		
		for(int m=0; m<M; m++) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			adjB[a].add(new Edge(b,B));
			adjB[b].add(new Edge(a,B));
			
			adjE[a].add(new Edge(b,E));
			adjE[b].add(new Edge(a,E));
		}
		long[] distsB = dijkstras(0, adjB);
		long bToE = distsB[1];
		long eToB = distsB[1]/B*E;
		long bToN = distsB[N-1];
		
		long[] distsE = dijkstras(1, adjE);
		long eToN = distsE[N-1];
		
		// Case 1, Cow goes to another
		long ans1 = 0;
		if(B<E) {
			ans1 = bToE + eToN/E*P;
		} else {
			ans1 = eToB + bToN/B*P;
		}
		
		// Case 2, both go home
		long ans2 = bToN + eToN;
		System.out.println(Math.min(ans1, ans2));
	}
	
	public long[] dijkstras(int start, ArrayList<Edge>[] adj) {
		   
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

	class Edge {
	    int v;
	    long w;
	    public Edge(int v, long w) {
	        this.v = v;
	        this.w = w;
	    }
	}

	class State implements Comparable<State> {
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
