package graph2;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class TopSort {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new TopSort().solve(scan, out);
		
		scan.close();
		out.close();
	}

	int N,M;
	ArrayList<Integer>[] adj;
	ArrayList<Integer> order;
	private void solve(Scanner scan, PrintWriter out) {
		
		while(true) {
			N = scan.nextInt();
			M = scan.nextInt();
			if(N==0 && M==0) break;
			int[] inDeg = new int[N];
			adj = new ArrayList[N];
			order = new ArrayList<>(N);
			for(int n=0; n<N; n++) {
				adj[n] = new ArrayList<>();
			}
			for(int m=0; m<M; m++) {
				int x=scan.nextInt()-1;
				int y=scan.nextInt()-1;
				adj[x].add(y);
				inDeg[y]++;
			}
			System.out.println(topSort(inDeg));
			//System.out.println(order);
		}
		
	}
	private int topSort(int[] inDeg) {
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		
		for(int d=0; d<inDeg.length; d++) {
			if(inDeg[d]==0) {
				q.add(d);
			}
		}
		int numVisited=0;
		boolean mult = false;
		while(!q.isEmpty()) {
			if(q.size()>1) {
				mult=true;
			}
			int node = q.poll();
			numVisited++;
			order.add(node);
			for(int v: adj[node]) {
				inDeg[v]--;
				if(inDeg[v]==0) {
					q.add(v);
				}
			}
		}
		if(N==numVisited) {
			if(mult) {
				return 2;
			}
			return 1;
		} else {
			return 0;
		}
	}
}
