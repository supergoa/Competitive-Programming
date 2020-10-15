import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class Nile {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Nile().solve(in,out);
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		
		for(int t=0;t<T; t++) {
			int C = in.nextInt();
			int R = in.nextInt();
			int B = in.nextInt();
			char[][] map = new char[R][C];
			int[][] mapID = new int[R][C];
			int[][] mapID2 = new int[R][C];
			System.out.println(R + " " + C);
			int mapids = 0;
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					mapID[r][c] =  mapids++;
					mapID2[r][c] = mapids++;
				}
			}
			for(int b=0; b<B; b++) {
				int c0 = in.nextInt();
				int r0 = in.nextInt();
				int c1 = in.nextInt();
				int r1 = in.nextInt();
				//System.out.println(c0 + " " + r0 + " " + c1 + " " + r1);
				for(int i=r0; i<=r1; i++) {
					for(int j=c0; j<=c1; j++) {
						map[i][j] = 'b';
					}
				}
			}
			
			
			Edge[][] adj = new Edge[R*C*2+2][1];
			for(int i=0; i<R*C*2+2; i++) {
				adj[i][0] = new Edge(0,0);
			}
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					adj[mapID[r][c]][0] = new Edge(1, mapID2[r][c]);
				}
			}
			
			for(int c=0; c<C; c++) {
				if(map[0][c]!='b') {
					adj[R*C*2][0] = new Edge(1, mapID[0][c]);
				}
			}
			for(int c=0; c<C; c++) {
				if(map[R-1][c]!='b') {
					adj[mapID[R-1][c]][0] = new Edge(1, R*C+1);
				}
			}
			
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(map[r][c] == 'b') continue;
					for(int i=-1; i<2; i++) {
						for(int j=-1; j<2; j++) {
							if(Math.abs(i+j)==1 && r+i<R && r+i>=0 && j+c<C && j+c>=0 && map[r+i][j+c]!='b') {
								adj[mapID2[r][c]][0] =  new Edge(1, mapID[r+i][c+j]);
								//System.out.println(r + " " + c + " " + (r+i) + " " + (j+c));
							}
						}
					}
				}
			}
			netflowdinic dinic = new netflowdinic(adj, R*C*2, R*C*2+1);
			System.out.println("Case #" + (t+1)+": "+dinic.getMaxFlow());
		}
		
	}
	class Edge {

		public int dest;
		public int capacity;
		public int flow;

		public Edge(int cap, int d) {
			capacity = cap;
			flow = 0;
			dest = d;
		}

		public String toString() {
			return "("+dest+" "+capacity+", "+flow+") ";
		}

		public int maxPushForward() {
			return capacity - flow;
		}

		public int maxPushBackward() {
			return flow;
		}

		public boolean pushForward(int moreflow) {

			// We can't push through this much flow.
			if (flow+moreflow > capacity)
				return false;

			// Push through.
			flow += moreflow;
			return true;
		}

		public boolean pushBack(int lessflow) {

			// Not enough to push back on.
			if (flow < lessflow)
				return false;

			flow -= lessflow;
			return true;
		}
	}

	class direction {

		public int prev;
		public boolean forward;

		public direction(int node, boolean dir) {
			prev = node;
			forward = dir;
		}

		public String toString() {
			if (forward)
				return "" + prev + "->";
			else
				return "" + prev + "<-";
		}
	}

	class pair {

		public int vertex;
		public int distance;

		public pair(int v, int d) {
			vertex = v;
			distance = d;
		}
	}

	public class netflowdinic {

		private Edge[][] adjMat;
		private int source;
		private int dest;
		private HashMap[] lookup;
		private LinkedList[] backEdgeLookup;
		private int[] distance;

		public netflowdinic(Edge[][] matrix, int start, int end) {

			// Set up easy stuff.
			adjMat = matrix;
			source = start;
			dest = end;
			lookup = new HashMap[matrix.length];
			distance = new int[matrix.length];

			// Allocate empty LLs.
			backEdgeLookup = new LinkedList[matrix.length];
			for (int i=0; i<matrix.length; i++)
				backEdgeLookup[i] = new LinkedList<Integer>();

			// Fill these in.
			for (int i=0; i<adjMat.length; i++) {

				lookup[i] = new HashMap<Integer,Integer>();
				for (int j=0; j<adjMat[i].length; j++) {
					backEdgeLookup[adjMat[i][j].dest].offer(i);
					lookup[i].put(adjMat[i][j].dest,j);
					adjMat[i][j].flow = 0;
				}
			}

		}

		// Wrapper function for dfs that finds an augmenting path of a specific length.
		public ArrayList<direction> findAugmentingPath() {
			boolean[] used = new boolean[adjMat.length];
			ArrayList<direction> path = new ArrayList<direction>();
			path.add(new direction(source, true));
			path = findAugmentingPathRec(source, used, path);
			if (path == null) return null;
			return fix(path);
		}

		// Recursive function that builds the augmenting path from next to dest.
		public ArrayList<direction> findAugmentingPathRec(int next, boolean[] used, ArrayList<direction> path) {

			// We're done.
			if (next == dest) return path;

			// Mark this node.
			used[next] = true;
			int curDist = distance[next];

			// Find all neighbors and add into the queue. These are forward edges.
			for (int i=0; i<adjMat[next].length; i++) {

				int item = adjMat[next][i].dest;
				if (!used[item] && adjMat[next][i].maxPushForward() > 0 && distance[item] == curDist+1) {
					path.add(new direction(item, true));
					ArrayList<direction> temp = findAugmentingPathRec(item, used, path);
					if (temp != null)
						return temp;
					else
						path.remove(path.size()-1);
				}
			}

			ListIterator<Integer> itr = backEdgeLookup[next].listIterator(0);

			// Now look for back edges.
			while (itr.hasNext()) {
				Integer item = itr.next();
				if (!used[item] && lookup[item].containsKey(next) && adjMat[item][(Integer)(lookup[item].get(next))].maxPushBackward() > 0 && distance[item] == curDist+1) {
					path.add(new direction(item, false));
					ArrayList<direction> temp = findAugmentingPathRec(item, used, path);
					if (temp != null)
						return temp;
					else
						path.remove(path.size()-1);
				}
			}

			return null;
		}

		// This is to fix the faulty output of the recursive functions. The directions are "off by one".
		public ArrayList<direction> fix(ArrayList<direction> list) {
			for (int i=0; i<list.size()-1; i++)
				list.get(i).forward = list.get(i+1).forward;
			return list;
		}

		// This is the BFS that labels all of the distances from the source.
		public boolean labelDistances() {

			Arrays.fill(distance, -1);

			// Set up BFS.
			boolean[] inQueue = new boolean[adjMat.length];
			for (int i=0; i<inQueue.length; i++)
				inQueue[i] = false;

			LinkedList<pair> bfs_queue = new LinkedList<pair>();
			bfs_queue.offer(new pair(source, 0));
			inQueue[source] = true;

			// Our BFS will go until we clear out the queue.
			while (bfs_queue.size() > 0) {

				// Add all the new neighbors of the current node.
				pair nextItem = bfs_queue.poll();
				int next = nextItem.vertex;

				// Store distance from source.
				distance[next] = nextItem.distance;

				// Find all neighbors and add into the queue. These are forward edges.
				for (int i=0; i<adjMat[next].length; i++) {

					int item = adjMat[next][i].dest;
					if (!inQueue[item] && adjMat[next][i].maxPushForward() > 0) {
						bfs_queue.offer(new pair(item, nextItem.distance+1) );
						inQueue[item] = true;
					}
				}

				ListIterator<Integer> itr = backEdgeLookup[next].listIterator(0);

				// Now look for back edges.
				while (itr.hasNext()) {
					Integer item = itr.next();
					if (!inQueue[item] && lookup[item].containsKey(next) && adjMat[item][(Integer)(lookup[item].get(next))].maxPushBackward() > 0) {
						bfs_queue.offer(new pair(item, nextItem.distance+1));
						inQueue[item] = true;
					}
				}
			}

			// No augmenting path found.
			return inQueue[dest];
		}


		// Run the Max Flow Algorithm here.
		public int getMaxFlow() {

			int flow = 0;

			// Outer level - do BFS to label nodes.
			while (labelDistances()) {

				ArrayList<direction> nextpath = findAugmentingPath();

				// Run adjusted DFS here.
				while (nextpath != null) {



					// Check what the best flow through this path is.
					int this_flow = Integer.MAX_VALUE;
					for (int i=0; i<nextpath.size()-1; i++) {

						if (nextpath.get(i).forward) {
							this_flow = Math.min(this_flow, adjMat[nextpath.get(i).prev][(Integer)lookup[nextpath.get(i).prev].get(nextpath.get(i+1).prev)].maxPushForward());
						}
						else {
							this_flow = Math.min(this_flow, adjMat[nextpath.get(i+1).prev][(Integer)lookup[nextpath.get(i+1).prev].get(nextpath.get(i).prev)].maxPushBackward());
						}
					}

					// Now, put this flow through.
					for (int i=0; i<nextpath.size()-1; i++) {

						if (nextpath.get(i).forward) {
							adjMat[nextpath.get(i).prev][(Integer)lookup[nextpath.get(i).prev].get(nextpath.get(i+1).prev)].pushForward(this_flow);
						}
						else {
							adjMat[nextpath.get(i+1).prev][(Integer)lookup[nextpath.get(i+1).prev].get(nextpath.get(i).prev)].pushBack(this_flow);
						}
					}

					// Add this flow in and then get the next path.
					flow += this_flow;
					nextpath = findAugmentingPath();
				}
			}

			return flow;
		}
	}
}
