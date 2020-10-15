


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class CowRouting {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("cowroute.in"));
		PrintWriter out = new PrintWriter(new File("cowroute.out"));
		new CowRouting().solve(in,out);
		in.close();
		out.close();
	}
	private void solve(Scanner in, PrintWriter out) {
		int n,a,b;
		a = in.nextInt()-1;
		b = in.nextInt()-1;
		n = in.nextInt();
		ArrayList<Edge>[] adj =  new ArrayList[1001];
		//ArrayList<Integer>[] adjS =  new ArrayList[n];
		for(int i=0; i<1001; i++) {
			adj[i] = new ArrayList<>();
			//adjS[i] = new ArrayList<>();
		}
		for(int nn=0; nn<n;nn++) {
			int c = in.nextInt();
			int k = in.nextInt();
			
			int[] cities = new int[k];
			for(int i=0; i<k; i++) cities[i] = in.nextInt()-1;
			
			for(int i=0; i<k; i++) {
				for(int j=i+1; j<k; j++) {
					adj[cities[i]].add(new Edge(cities[j],c, Math.abs(j-i)));
				}
			}
		}
		dijkstras(a, adj);
		if(dists[b]==Long.MAX_VALUE) {
			out.println(-1 + " " + -1);
		} else {
			out.println(dists[b] + " " + flights[b]);
		}
	}
		
	
	long[] dists;
    int[] flights;
	public void dijkstras(int start, ArrayList<Edge>[] adj) {
	    int n = adj.length;
	    
	    dists = new long[n];
	    flights = new int[n];
	    
	    Arrays.fill(dists, Long.MAX_VALUE);
	    Arrays.fill(flights, (int)1e9);
	    
	    dists[start] = 0;
	    PriorityQueue<State> pq = new PriorityQueue<State>();
	    pq.add(new State(start, 0, 0));
	    
	    while(!pq.isEmpty()) {
	        State s = pq.poll();
	        if(dists[s.v] < s.d) continue;
	        for(Edge e : adj[s.v]) {
	            if(e.w + s.d == dists[e.v]) {
	            	if(s.numFlights + e.numFlights < flights[e.v]) {
	            		flights[e.v] = s.numFlights + e.numFlights;
	            		pq.add(new State(e.v, dists[e.v], flights[e.v]));
	            	}
	            }
	            else if(e.w + s.d < dists[e.v]) {
	                dists[e.v] = e.w + s.d;
	                flights[e.v] = s.numFlights + e.numFlights;
	                pq.add(new State(e.v, dists[e.v], flights[e.v]));
	            }
	        }
	    }
	}


	class Edge {
	    int v;
	    long w;
	    int numFlights;
	    public Edge(int v, long w, int numFlights) {
	        this.v = v;
	        this.w = w;
	        this.numFlights = numFlights;
	    }
	}

	class State implements Comparable<State> {
	    int v;
	    long d;
	    int numFlights;
	    public State(int v, long d, int numFlights) {
	        this.v = v;
	        this.d = d;
	        this.numFlights = numFlights;
	    }
	    public int compareTo(State s) {
	    	if(s.d == d) {
	    		return Long.compare(numFlights, s.numFlights);
	    	} else {
	    		return Long.compare(d, s.d);
	    	}
	    }
	}
}

