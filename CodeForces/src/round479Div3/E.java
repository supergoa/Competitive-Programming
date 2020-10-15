package round479Div3;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class E {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new E().solve(scan, out);
		
		out.close();
	}
	public class DSU {
		int[] id;
		int size;
		DSU(int x) { //x is size
			size = x + 1;
			id = new int[size]; // disj relation
			for (int i = 0; i < size; ++i) {
				id[i] = i;
			}
		}
		int root(int a) { //get root of a. "id[a] = get(id[a])" makes retrieval O(1) on successive calls
			return id[a] == a ? a : (id[a] = root(id[a]));
		}
		
		void union(int a, int b) {
			if(root(a) == root(b)) return;
			id[root(a)] = id[root(b)];
			--size;
		}
	}

	private void solve(Scanner scan, PrintWriter out) {
		int N,M;
		N = scan.nextInt();
		M = scan.nextInt();
		ArrayList<Integer>[] adj = new ArrayList[N];
		for(int n=0; n<N; n++) {
			adj[n] = new ArrayList<>();
		}
		DSU dsu = new DSU(N);
		for(int m=0; m<M; m++) {
			int x= scan.nextInt()-1;
			int y= scan.nextInt()-1;
			adj[x].add(y);
			adj[y].add(x);
			dsu.union(x, y);
			dsu.union(y, x);
		}

		HashMap<Integer, HashSet<Integer>> hm = new HashMap<>();
		for(int i=0; i<N; i++) {
			int num = dsu.root(i);
			HashSet<Integer> temp = hm.getOrDefault(num, new HashSet<>());
			temp.add(i);
			hm.put(num, temp);
		}
		ArrayDeque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		int numLoops = 0;
		for(int a : hm.keySet()) {
			HashSet<Integer> temp = hm.get(a);
			//System.out.println(temp);
			int nodes = temp.size();
			int edges=0;
			for(int node:temp) {
				edges+=adj[node].size();
			}
			if(edges%2==0) {
				edges/=2;
			}
			if(nodes!=edges) {
				//System.out.println("IM HERE");
				continue;
			}
			
			int start = temp.iterator().next();
			visited[start] = true;
			q.add(start);
			int lastNode =  start;
			int numVisited = 1;
			while(!q.isEmpty()) {
				int node = q.pop();
				//System.out.println("Node: " + node);
				for(int n:adj[node]) {
					if(!visited[n]) {
						lastNode=n;
						visited[n]=true;
						q.add(n);
						numVisited++;
						break;
					}
				}
			}
			
			
			
			if(numVisited == nodes) {
				for(int n: adj[lastNode]) {
					if(n==start) {
						//System.out.println("Start: " +start + " lastNoded: "+lastNode + "numVis: "+ numVisited);
						numLoops++;
						break;
					}
				}
			}
		}

		out.println(numLoops);
	}
}
