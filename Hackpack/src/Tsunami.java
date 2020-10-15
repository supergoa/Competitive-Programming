import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
;

public class Tsunami {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new Tsunami().solve(scan,out);
		scan.close();
		out.close();
	}

	class Edge implements Comparable<Edge>{
		Pair v2;
		double w;
		public Edge(Pair v2, double dist) {
			this.v2=v2;
			this.w=dist;
		}
		@Override
		public int compareTo(Edge o) {
			if(v2.y==o.v2.y) {
				return (int) Math.signum(this.w - o.w); 
			} else {
				return (int) Math.signum(this.v2.y - o.v2.y);
			}
		}
	}
	class Pair {
		int x;
		int y;
		int id;
		public Pair(int x, int y, int id) {
			this.x=x;
			this.y=y;
			this.id=id;
		}
	}
	int N;
	private void solve(Scanner scan, PrintWriter out) {
		while(true) {
			N=scan.nextInt();
			if(N==0) break;
			Pair[] cities = new Pair[N];
			
			int start = 0;
			int minY = Integer.MAX_VALUE;
			for(int n=0; n<N; n++) {
				int x = scan.nextInt();
				int y = scan.nextInt();
				cities[n] = new Pair(x,y,n);
				
				if(y<minY) {
					minY = y;
					start = n;
				}
			}

			PriorityQueue<Edge> pq = new PriorityQueue<>();
			ArrayList<Edge>[] edges = new ArrayList[N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					double dist = Math.sqrt(Math.pow(cities[i].x-cities[j].x,2) + Math.pow(cities[i].y-cities[j].y,2));
					if(edges[i]==null) {
						edges[i] = new ArrayList<>();
					}
					edges[i].add(new Edge(cities[j], dist));
				}
			}
			boolean[] visited = new boolean[N];
			
			pq.addAll(edges[start]);
			visited[start] = true;
			
			double totalW = 0;
			//int numEdges = 0;
			
			while(!pq.isEmpty()) {	
				Edge e = pq.poll();
				if(visited[e.v2.id]) continue;
			
				visited[e.v2.id] = true;
					
				for(Edge adj : edges[e.v2.id]) {
					if(adj.v2.y >= e.v2.y && !visited[adj.v2.id]) {
						pq.add(adj);
					}
				}
				totalW += e.w;
			}
			out.println(totalW);
			out.flush();
		}
		
	}
}
