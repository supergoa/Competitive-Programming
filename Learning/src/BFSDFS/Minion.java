//package BFSDFS;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Minion {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		new Minion().solve(scan, pw);
		scan.close();
		pw.close();
	}
	private void solve(Scanner scan, PrintWriter out) {		
		int M = scan.nextInt();
		for(int m=0; m<M; m++) {
			int T = scan.nextInt();
			HashSet<String> trials = new HashSet<>();
			for(int t=0; t<T; t++) {
				trials.add(scan.next());
			}
			int N = scan.nextInt();
			int E = scan.nextInt();
			ArrayList<Integer>[] adj = new ArrayList[N];
			for(int i =0; i<N; i++) {
				adj[i] = new ArrayList<>();
			}
			for(int e =0; e<E; e++) {
				int to = scan.nextInt();
				int from = scan.nextInt();
				if(!trials.contains(scan.next())) {
					adj[to].add(from);
					adj[from].add(to);
				}
			}
			//BFS
			ArrayDeque<Integer> Q = new ArrayDeque<>();
			boolean[] visited = new boolean[N];
			visited[0] = true;
			Q.add(0);
			while(!Q.isEmpty()) {
				for(Integer child : adj[Q.pop()]) {
					if(!visited[child]) {
						Q.add(child);
						visited[child] = true;
					}
				}
			}
			out.println((visited[N-1])?1:0);
		}
	}
}
