package graph2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijstra {
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
