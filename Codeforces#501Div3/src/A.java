import java.io.PrintWriter;
import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new A().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		boolean[] visited = new boolean[M];
		for(int i=0; i<N; i++) {
			int l = in.nextInt()-1;
			int r = in.nextInt()-1;
			for(int j=l; j<=r; j++) {
				visited[j] = true;
			}
		}
		int count = 0;
		for(int j=0; j<M; j++) {
			if(!visited[j]) {
				count++;
			}
		}
		out.println(count);
		for(int j=0; j<M; j++) {
			if(!visited[j]) {
				out.print((j+1) + " ");
			}
		}
		
	}
}
