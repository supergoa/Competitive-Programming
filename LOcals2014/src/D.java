import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new D().solve(in,out);
		in.close();out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int P = in.nextInt();
			int S = in.nextInt()-1;
			
			String[] idtop = new String[P];
			HashMap<String, Integer> ptoid = new HashMap<>();
			for(int s=0; s<P; s++) {
				idtop[s] = in.next();
				ptoid.put(idtop[s], s);
			}
			
			ArrayList<Integer>[] adj = new ArrayList[P];
			for(int p=0; p<P; p++) {
				adj[p] = new ArrayList<>();
				int M = in.nextInt();
				for(int m=0; m<M; m++) {
					adj[p].add(in.nextInt()-1);
				}
			}
			
			ArrayDeque<Integer> q = new ArrayDeque<>();
			q.add(S);
			int[] visited = new int[P];
			visited[S] = 1;
			boolean infinite = false;
			while(!q.isEmpty()) {
				for(int c: adj[q.poll()]) {
					if(visited[c]<20) {
						q.add(c);
						visited[c]++;
						if(visited[c]==20) infinite = true;
					}
				}
			}
			if(infinite) {
				for(int p=0; p<P; p++) {
					if(visited[p]==20) {
						out.print(idtop[p] + " ");
					}
				}
				out.println();
			} else {
				out.println("Safe chain email!");
			}
			out.println();
		}
		
	}
}
