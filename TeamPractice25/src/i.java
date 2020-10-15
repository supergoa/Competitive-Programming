import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class i {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new i().solve(in,out);
		in.close();out.close();
			
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int K = in.nextInt();
		
		ArrayList<Integer>[] adj = new ArrayList[N];
		for(int n=0; n<N;n++) adj[n] = new ArrayList<>();
		
		int[] bad = new int[K];
		for(int k=0; k<K; k++) {
			bad[k] = in.nextInt()-1;
		}
		in.nextLine();
		for(int n=0; n<N;n++) {
			String[] line = in.nextLine().split(" ");
			for(int i=1; i<line.length; i++) {
				adj[n].add(Integer.parseInt(line[i])-1);
			}
		}
		
		int ans = 0;
		int maxInd = 0;
		for(int k=0; k<K;k++) {
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean[] visited = new boolean[N];
			q.add(bad[k]);
			int kans = 0;
			while(!q.isEmpty()) {
				int node = q.poll();
				if(visited[node]) continue;
				kans++;
				visited[node] = true;
				for(int i : adj[node]) {
					if(!visited[i]) {
						q.add(i);
					}
				}
			}
			if(kans > ans) {
				maxInd = k;
				ans = kans;
			}
		}
		System.out.println(maxInd+1);
	}
}
