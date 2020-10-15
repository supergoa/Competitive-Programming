package graph2;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RainOrShine {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new RainOrShine().solve(in,out);
		in.close();
		out.close();
	}

	static class Edge {
	    int from;
	    int to;
	    long w;
	    public Edge(int from, int to,  long w) {
	        this.from = from;
	        this.to = to;
	        this.w = w;
	    }
	}

	private void solve(Scanner in, PrintWriter out) {
		
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int v,e;
			v = in.nextInt();
			e = in.nextInt();
			
			//shortest
			Edge[] adj = new Edge[e];
			long[] dist = new long[v];
			Arrays.fill(dist, (long) 1e11);
			dist[0] = 0;
			
			//longest
			Edge[] adj2 = new Edge[e];
			long[] dist2 = new long[v];
			Arrays.fill(dist2, (long) 1e11);
			dist2[0] = 0;
			//for(int i=0; i<e; i++) adj[i] = new ArrayList<>();
			
			for(int ee=0; ee<e; ee++) {
				int s = in.nextInt();
				int d = in.nextInt();
				int time = in.nextInt();
				adj[ee] = (new Edge(s, d,time));
				adj2[ee] = (new Edge(s, d,-time)); 
			}
			for(int i=0; i<v-1; i++) {
				for(int j=0; j<e; j++) {
					if(dist[adj[j].from] + adj[j].w < dist[adj[j].to]) {
						dist[adj[j].to] = dist[adj[j].from] + adj[j].w;
					}
				}
			}
			for(int i=0; i<v-1; i++) {
				for(int j=0; j<e; j++) {
					if(dist2[adj2[j].from] + adj2[j].w < dist2[adj[j].to]) {
						dist2[adj2[j].to] = dist2[adj[j].from] + adj2[j].w;
					}
				}
			}
			System.out.println(dist[v-1] + " " + -dist2[v-1]);
			//System.out.println(Arrays.toString(dist2));
		}
		
		
		
		
	}
}
