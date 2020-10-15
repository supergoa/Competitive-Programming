import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class G3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new G3().solve(in,out);
		in.close();out.close();
			
	}
	class Target implements Comparable<Target> {
		int a,b,c,max,id;
		public Target(int a, int b, int c, int id) {
			this.a=a;
			this.b=b;
			this.c=c;
			this.id=id;
			max = Math.max(a, Math.max(b, c));
			if(id==0 || id==(N-1)) max = Math.max(a, b);
		}
		@Override
		public int compareTo(Target o) {
			return -Integer.compare(max, o.max);
		}
	}
	int N;
	PriorityQueue<Target> pq;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			pq.add(new Target(in.nextInt(), in.nextInt(), in.nextInt(), i));
		}
		boolean[] used = new boolean[N];
		int[] adjSelected = new int[N];
		int ans = 0;
		while(!pq.isEmpty()) {
			Target t = pq.poll();
			if(t.max==t.c) {
				
			}
			if(adjSelected[t.id]==1 && t.max==t.c) {
				ans += t.c;
				adjSelected[t.id]=2;
			}
		}
	}

}
