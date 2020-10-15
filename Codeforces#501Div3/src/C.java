import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class C {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		
		long totalBytes = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			totalBytes += a;
			pq.add(b-a);
		}

		int count = 0;
		while(!pq.isEmpty()) {
			if(totalBytes<=M) break;
			totalBytes+=pq.poll();
			count++;
		}
		if(totalBytes<=M) {
			out.print(count);
		} else {
			out.print(-1);
		}
	}
}
