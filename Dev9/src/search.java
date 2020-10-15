import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class search {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new search().solve(in,out);
		in.close();
		out.close();
	}

	final int oo= (int) 1e9;
	int[][] dist;
	int[][] officerMemo;
	int[][] tsMemo;
	int S,N,P;
	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			S = in.nextInt();
			if(S==0) break;
			N = in.nextInt();
			P = in.nextInt();
			
			// Floyd's
			dist = new int[S][S];
			for(int s=0;s<S;s++) Arrays.fill(dist[s], oo);
			for(int s=0;s<S;s++) dist[s][s] = 0;
			for(int p=0;p<P;p++) {
				String path = in.next();
				int site1 = path.charAt(0)-'A';
				int site2 = path.charAt(1)-'A';
				dist[site1][site2] = 1;
				dist[site2][site1] = 1;
			}
			for(int i=0; i<S; i++) {
				for(int j=0; j<S; j++) {
					for(int k=0; k<S; k++) {
						dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
					}
				}
			}
			
			// set up double dp
			officerMemo = new int[N][(1<<S)+1];
			tsMemo = new int[S+1][(1<<S)+1];
			for(int i=0; i<N; i++) Arrays.fill(officerMemo[i], -1);
			for(int i=0; i<S+1; i++) Arrays.fill(tsMemo[i], -1);
			
			// 0th officer, All nodes unvisited except A (therefore -2, not -1)
			int answer = officerDp(0, (1<<S)-2);
			System.out.println(answer);
		}
		
	}
	private int officerDp(int officer, int visited) {
		if(officerMemo[officer][visited] != -1) return officerMemo[officer][visited];
		if(officer==N && visited!=0) return oo; // out of officers but did not visited everything [don't need but it lead me to understanding line 61's necessity better]
		if(officer==N) return 0; // visited everything using all officers
		if(officer==N-1) return tsDp(0, visited); // need or line 67's recursive call below will execute first base case (line 59) and cause wrong ANS
		
		int bestOfficerDist = Integer.MAX_VALUE;
		int worstTSP = Integer.MIN_VALUE;
		for(int subMask = visited; subMask > 0; subMask = (subMask-1) & visited) {
			int tsp = tsDp(0,subMask);
			int restDp = officerDp(officer+1, visited-subMask);
			
			// which officer took the longest?
			worstTSP = Math.max(tsp, restDp);
			
			// is this better than another scenario I've checked?
			bestOfficerDist = Math.min(bestOfficerDist, worstTSP);
		}
		
		return officerMemo[officer][visited]=bestOfficerDist;
	}
	
	// best way to visit all unvisited nodes starting at node using 1 person [standard tsp]
	private int tsDp(int node, int visited) {
		if(tsMemo[node][visited] != -1) return tsMemo[node][visited];
		if(visited==0) return 0;
		
		int best = Integer.MAX_VALUE;
		for(int nextNode = 0; nextNode < S; nextNode++) {
			if(((1<<nextNode)&visited) == 0) continue;
			best = Math.min(best, dist[node][nextNode] + tsDp(nextNode, visited-(1<<nextNode)));
		}
		return tsMemo[node][visited]=best;
	}
/*
 
 4 4 5 AB BC CD AD AC
 4 2 5 AB BC CD AD AC
 6 3 5 AB BC BD AE AF
 6 2 5 AB BC BD AE AF
 0
 */
}
