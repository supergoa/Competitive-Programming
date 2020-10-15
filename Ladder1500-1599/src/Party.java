import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Party {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Party().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		ArrayList<Integer>[] adj = new ArrayList[N];
		for(int i=0; i<N; i++) adj[i] = new ArrayList<>();
		for(int i=0; i<N; i++) {
			int x = in.nextInt()-1;
			if(x!=-2) {
				adj[x].add(i);
			}
		}
		int ans = 0;
		
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			stack.add(i);
			boolean[] visited = new boolean[N];
			int[] level = new int[N];
			level[i]=0;
			//System.out.println("TEST I: " + i);
			while(!stack.isEmpty()) {
				int node = stack.poll();
				if(visited[node]) continue;
				visited[node] = true;
				//System.out.println("node" + node);
				for(int a : adj[node]) {
					if(!visited[a]) {
						stack.addFirst(a);
						level[a] = level[node] +1;
						ans = Math.max(ans, level[a]);
					}
				}
			}
		}
		System.out.println(ans+1);
	}
}
