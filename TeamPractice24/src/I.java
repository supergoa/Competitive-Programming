import java.io.PrintWriter;
import java.util.Scanner;

public class I {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new I().solve(in,out);
		in.close();
		out.close();
	}

	int N;
	long len;
	int[][] adj;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		len = in.nextLong();

		adj = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N;j++) {
				adj[i][j] = in.nextInt();
			}
		}
		boolean ans = false;
		for(int i=0; i<N;i++) {
			start = i;
			boolean[] visited = new boolean[N];
			visited[start] = true;
			ans = ans || recurse(i, visited, 0);
			if(ans) break;
		}
		if(ans) out.println("possible");
		else out.println("impossible");
	}

	int start;
	private boolean recurse(int curr, boolean[] visited, int length) {
		//System.out.println(curr);
		if(length + adj[curr][start]==len) {
			System.out.println("found");
			boolean flag = true;
			for(boolean a:visited) {
				if(!a) flag = false;
			}
			if(flag) return true;
		}
		for(int i=0; i<N; i++) {
			if(!visited[i] && i!=curr) {
				//System.out.println(curr + " to " + i);
				visited[i]=true;
				length += adj[curr][i];
				if(recurse(i, visited, length)) return true;
				visited[i]=false;
				length -= adj[curr][i];
			}
		}
		return false;
	}
}
