import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Moocastt{
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("moocast.in"));
		PrintWriter out = new PrintWriter(new File("moocast.out"));
		new Moocastt().solve(in,out);
		in.close();
		out.close();
	}

	class Pair {
		public int x,y,id;
		public Pair(int x, int y, int id) {
			this.x=x;
			this.y=y;
			this.id=id;
		}
		
	}
	class Edge implements Comparable<Edge>{
		Pair v;
		double w;
		public Edge(Pair v2, double dist) {
			this.v=v2;
			this.w=dist;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(w, o.w);
		}
	}
	
	public double money(Pair p1, Pair p2) {
		return Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2);
	}
	
	int N;
	Pair[] cows;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		cows = new Pair[N];
		for(int n=0; n<N; n++) {
			int x = in.nextInt();
			int y = in.nextInt();
			cows[n] = new Pair(x,y,n);
		}
		
		double distance = prims();
		
		out.print((int)distance);
		/*if(N==1) out.println(0);
		else if(dollars - (int)dollars <= .0001) {
			out.println((int)dollars);
		} else {
			out.println((int)(dollars+1));
		}*/
	}
	private double prims() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		ArrayList<Edge>[] edges = new ArrayList[N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i!=j) {
					if(edges[i]==null) {
						edges[i] = new ArrayList<>();
					}
					edges[cows[i].id].add(new Edge(cows[j], money(cows[i], cows[j])));
				}
			}
		}
		
		boolean[] visited = new boolean[N];
		
		int start = 0;
		pq.addAll(edges[start]);
		visited[start] = true;
		
		double maxW = 0;
		//int numEdges = 0;
		
		while(!pq.isEmpty()) {	
			Edge e = pq.poll();
			if(visited[e.v.id]) continue;
		
			visited[e.v.id] = true;
				
			for(Edge adj : edges[e.v.id]) {
				if(!visited[adj.v.id]) {
					pq.add(adj);
				}
			}
			maxW = Math.max(e.w, maxW);
		}
		return maxW;
	}
}
