import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class p44 {
	
	final long oo = (long) 1e15;
	
	class Edge {
		int v;
		int flights;
		long w;
		Edge(int a, long b, int d) {
			v = a; w = b; flights = d;
		}
	}
	
	class State implements Comparable<State> {
		int v;
		int flights;
		long d;
		State(int a, long b, int f) {
			v = a;
			d = b;
			flights = f;
		}
		public int compareTo(State s) {
			if(Long.compare(d, s.d) == 0) return flights - s.flights;
			return Long.compare(d, s.d);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void solve(Scanner in, PrintWriter out) {
		int n = 1001;
		
		int A = in.nextInt() - 1;
		int B = in.nextInt() - 1;
		
		if(A == B) {
			out.println("0 0");
			return;
		}
		
		int routes = in.nextInt();
		
		ArrayList<Edge>[] adj = new ArrayList[n];
		for(int i = 0; i < n; ++i) adj[i] = new ArrayList<>();
		
		for(int r = 0; r < routes; ++r) {
			int cost = in.nextInt();
			int ns = in.nextInt();
			int[] a = new int[ns];
			for(int i = 0; i < ns; ++i) a[i] = in.nextInt() - 1;
			for(int i = 0; i < ns; ++i) {
				for(int j = i + 1; j < ns; ++j) {
					int u = a[i], v = a[j];
					adj[u].add(new Edge(v, cost, j - i));
				}
			}
		}
		
		long[] dists = new long[n];
		int[] steps = new int[n];
		Arrays.fill(dists, oo);
		Arrays.fill(steps, (int) 1e9);
		
		dists[A] = 0;
		PriorityQueue<State> q = new PriorityQueue<>();
		q.add(new State(A, 0, 0));
		
		while(!q.isEmpty()) {
			State s = q.poll();
			if(dists[s.v] < s.d) continue;
			for(Edge e : adj[s.v]) {
				if(e.w + s.d > dists[e.v]) continue;
				if(e.w + s.d == dists[e.v]) {
					if(s.flights + e.flights < steps[e.v]) {
						steps[e.v] = Math.min(steps[e.v], s.flights + e.flights);
						q.add(new State(e.v, dists[e.v], s.flights + e.flights));
					}
				} else {
					dists[e.v] = e.w + s.d;
					q.add(new State(e.v, dists[e.v], s.flights + e.flights));
					steps[e.v] = s.flights + e.flights;
				}
			}
		}
		
		if(dists[B] == oo) {
			out.println("-1 -1");
			return;
		}
		
		out.println(dists[B] + " " + steps[B]);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("cowroute.in"));
		PrintWriter out = new PrintWriter(new File("cowroute.out"));
		new p44().solve(in, out);
		in.close();
		out.close();
	}
}
