import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Checklist2 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);//new File("moocast.in"));
		PrintWriter out = new PrintWriter(System.out);//new File("moocast.out"));
		new Checklist2().solve(in,out);
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
		public Edge(Pair v, double dist) {
			this.v=v;
			this.w=dist;
		}
		@Override
		public int compareTo(Edge o) {
			return (int) ((int) w-o.w);
		}
	}
	
	public double distance(Pair p1, Pair p2) {
		return Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
	}
	
	int H,G;
	int N;
	Pair[] cows;
	private void solve(Scanner in, PrintWriter out) {
		H = in.nextInt();
		G = in.nextInt();
		N = H+G;
		cows = new Pair[N];
		for(int i=0; i<N; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			cows[i] = new Pair(x,y,i);
		}
		
		double distance = prims();
		
		double energy = Math.pow(distance, 2);
		if(energy - (int)energy <= .1) {
			out.println((int)energy);
		} else {
			out.println((int)(energy+1));
		}
	}
	private double prims() {
		int hCount = 0;
		int gCount = H;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		ArrayList<Edge>[] edges = new ArrayList[N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i!=j) {
					if(edges[i]==null) {
						edges[i] = new ArrayList<>();
					}
					edges[cows[i].id].add(new Edge(cows[j], distance(cows[i], cows[j])));
				}
			}
		}
		
		boolean[] visited = new boolean[N];
		
		for(Edge i : edges[hCount]) {
			if(i.v.id == hCount+1 || i.v.id ==gCount+1) {
				pq.add(i);
			}
		}
		visited[hCount] = true;
		int totW = 0;
		
		while(!pq.isEmpty()) {	
			Edge e = pq.poll();
			if(visited[e.v.id]) continue;
		
			visited[e.v.id] = true;
				
			for(Edge adj : edges[e.v.id]) {
				if(!visited[adj.v.id]) {
					int id = adj.v.id;
					if(id >= H) {
						if(id == gCount + 1) {
							pq.add(adj);
							gCount++;
						}
					} else {
						if(id == hCount + 1) {
							pq.add(adj);
							hCount++;
						}
					}
				}
			}
			totW += e.w;
		}
		return totW;
	}
}
