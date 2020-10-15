import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MultiModalTrans {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new MultiModalTrans().solve(in,out);
		in.close();
		out.close();
	}

	class City {
		int id;
		int[] transportIDs;
		private int aid, bid, rid, tid, cost;
		String z;
		public City (String z, int a,int b,int c,int d,int e,int f) {
			this.z=z;
			id = a;
			aid = b;
			bid = c;
			rid = d;
			tid = e;
			cost = f;
			transportIDs = new int[4];
			transportIDs[0] = aid;
			transportIDs[1] = bid;
			transportIDs[2] = rid;
			transportIDs[3] = tid;
		}
		public String toString() {
			return id + " " + Arrays.toString(transportIDs)+ " " + cost;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int C = in.nextInt();
			City[] cities = new City[C];
			
			int cityID = 0;
			int totalID = 0;
			
			ArrayList<Edge>[] adj = new ArrayList[C*4+2]; for(int i=0; i<C*4+2; i++) adj[i] = new ArrayList<>();
			HashMap<String, Integer> ids = new HashMap<>();
			
			for(int c=0; c<C; c++) {
				String city = in.next();
				ids.put(city, cityID);
				cities[c] = new City(city, cityID++, totalID++, totalID++,totalID++,totalID++, in.nextInt());
				
				for(int i=0; i<4; i++) {
					for(int j=0; j<4; j++) {
						if(i==j) continue;
						adj[cities[c].transportIDs[i]].add(new Edge(cities[c].transportIDs[j], cities[c].cost));
					}
				}
			}
			
			int R = in.nextInt();
			for(int r=0; r<R; r++) {
				int city1 = ids.get(in.next());
				int city2 = ids.get(in.next());
				
				String m = in.next();
				int mVal = -1;
				if(m.equals("AIR")) mVal = 0;
				if(m.equals("SEA")) mVal = 1;
				if(m.equals("RAIL")) mVal = 2;
				if(m.equals("TRUCK")) mVal = 3;
				int cost = in.nextInt();

				adj[cities[city1].transportIDs[mVal]].add(new Edge(cities[city2].transportIDs[mVal], cost));
				adj[cities[city2].transportIDs[mVal]].add(new Edge(cities[city1].transportIDs[mVal], cost));
			}

			City start = cities[ids.get(in.next())];
			City end = cities[ids.get(in.next())];
			for(int i=0; i<4; i++) {
				adj[C*4].add(new Edge(start.transportIDs[i],0));
				adj[start.transportIDs[i]].add(new Edge(C*4,0));
				
				adj[C*4+1].add(new Edge(end.transportIDs[i],0));
				adj[end.transportIDs[i]].add(new Edge(C*4+1,0));
			}
			long[] ans = dijkstras(C*4, adj);
			out.println(ans[C*4+1]);
		}
	}
	
	// Dijkstra's shortest path O (e + v * log(v))
	// Can easily be modified to return the path using the prev[] array or by making states keep track of parent
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
