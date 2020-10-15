import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class D2{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new D2().solve(in,out);
		in.close();
		out.close();
	}
	static class Edge {
	    int v;
	    long w;
	    public Edge(int v, long w) {
	        this.v = v;
	        this.w = w;
	    }
	}
	
	int N,M,K;
	ArrayList<Edge>[] adj;
	boolean important[];
	boolean visited[];
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			N = in.nextInt();
			M = in.nextInt();
			K = in.nextInt();
			important = new boolean[N];
			adj = new ArrayList[N];
			visited = new boolean[N];
			for(int n=0;n<N;n++) adj[n] = new ArrayList<>();
			for(int m=0; m<M; m++) {
				int u = in.nextInt()-1;
				int v = in.nextInt()-1;
				int c = in.nextInt();
				adj[u].add(new Edge(v,c));
				adj[v].add(new Edge(u,c));
			}
			for(int k=0; k<K; k++) {
				int x = in.nextInt();
				important[x] = true;
			}
			//long ans = dp(0);
		}
		
	}
	final long oo = (long) (1e14+7);
	private long dp(int numVisited, int numImpVisited, int ind) {
		if(numImpVisited==K) return 0;
		if(numVisited==N) return oo;
		if(visited[ind]) return oo;
		
		long ans = 0;
		for(Edge v:adj[ind]) {
			long choose = 0;
			long leave = 0;
			
			//visited[]
			//choose = adj[v.w] + dp(numVisited+1, numImpVisited+1, v.v);
			//leave =  dp(numVisited+1, numImpVisited+1, v.v)
			
			
			ans += Math.min(choose, leave);
		}
		visited[ind] = true;
		return ans;
	}
}
