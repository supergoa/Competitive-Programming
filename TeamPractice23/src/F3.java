import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class F3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new F3().solve(in, out);
		in.close();
		out.close();
	}

	class Pair implements Comparable<Pair>{
		int cash,timeLeaves;
		public Pair(int cash, int timeLeavs) {
			this.cash = cash;
			this.timeLeaves = timeLeavs;
		}
		@Override
		public int compareTo(Pair o) {
			if(timeLeaves == o.timeLeaves) {
				return Integer.compare(o.cash, cash);
			}
			return Integer.compare(timeLeaves,o.timeLeaves);
		}
	}
	Pair[] people;
	int N,T;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		T = in.nextInt();
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for(int n=0;n<N; n++) {
			pq.add(new Pair(in.nextInt(), in.nextInt()));
		}
		int time = 0;
		int ans = 0;
		while(!pq.isEmpty() && time <= T) {
			Pair person = pq.poll();
			System.out.println(person.cash + " " + person.timeLeaves);
			if(person.timeLeaves >= time) {
				ans+=person.cash;
				time++;
			}
		}
		out.println(ans);
	}
}
