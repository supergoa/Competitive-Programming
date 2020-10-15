import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Moocast {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("moocast.in"));
		PrintWriter out = new PrintWriter(new File("moocast.out"));
		new Moocast().solve(in,out);
		in.close();
		out.close();
	}

	class Pair implements Comparable<Pair>{
		public int x,y,p, id;
		public Pair(int x, int y, int p, int id) {
			this.x=x;
			this.y=y;
			this.p=p;
			this.id=id;
		}
		@Override
		public int compareTo(Pair o) {
			return this.p - o.p;
		}
		
	}
	public double distance(Pair p1, Pair p2) {
		return Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		Pair[] cows = new Pair[N];
		for(int n=0; n<N; n++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int p = in.nextInt();
			cows[n] = new Pair(x,y,p,n);
		}
		
		int maxCows = 0;
		for(int n=0; n<N; n++) {
			Pair start = cows[n];
			int numCows = 0;
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			boolean[] visited = new boolean[N];
			
			pq.add(start);
			visited[start.id] = true;
			while(!pq.isEmpty()) {
				Pair node = pq.poll();
				numCows++;
				for(int i=0; i<N; i++) {
					if(!visited[i] && node.id!=i && distance(node, cows[i]) <= node.p) {
						pq.add(cows[i]);
						visited[i]=true;
					}
				}
			}
			maxCows = Math.max(numCows, maxCows);
		}
		out.println(maxCows);
	}
}
