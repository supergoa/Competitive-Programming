package Misc;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class CountsNumCycles {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new CountsNumCycles().solve(scan, out);
		
		out.close();
	}

	private void solve(Scanner scan, PrintWriter out) {
		int N,M;
		N = scan.nextInt();
		M = scan.nextInt();
		ArrayList<Integer>[] adj = new ArrayList[N];
		for(int n=0; n<N; n++) {
			adj[n] = new ArrayList<>();
		}
		for(int m=0; m<M; m++) {
			int x= scan.nextInt()-1;
			int y= scan.nextInt()-1;
			adj[x].add(y);
			adj[y].add(x);
		}
		int numVisited = 0;
		boolean[] visited = new boolean[N];
		int[] parent = new int[N];
		for(int a=0; a<parent.length; a++) {
			parent[a] = -1;
		}
		Stack<Integer> q = new Stack<>();
		
		int numLoops = 0;
		int start = 0;
		q.add(start);
		visited[start] = true;
		//parent[start] = -1;
		numVisited++;
		
		while (numVisited < N) {
			while(!q.isEmpty()) {
				int node = q.pop();
				
				System.out.println("Node: " + node+ " parent: " + parent[node]);
				for(int a : adj[node]) {
					System.out.println("Adj: " + a );
					if(a==start && parent[node]!=start) {
						System.out.println("Loop: " + a);
						numLoops++;
					}
					if(!visited[a]) {
						q.push(a);
						visited[a] = true;
						numVisited++;
					}
					parent[a] = node;
				}
			}
			for(int i=0; i<visited.length; i++) {
				if(!visited[i]) {
					start = i;
					q.push(i);
					numVisited++;
					visited[i] = true;
					//parent[start] = -1;
					break;
				}
			}
		}
		out.println("Ans: " + numLoops);
	}
}
