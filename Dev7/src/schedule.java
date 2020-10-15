import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class schedule {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new schedule().solve(in,out);
		in.close();
		out.close();
	}

	class event implements Comparable<event>{
		int o,c;
		public event(int a, int b) {
			o = a;
			c = b;
		}
		@Override
		public int compareTo(event o) {
			return Integer.compare(this.o, o.o); //terrible programming
		}
		@Override
		public String toString() {
			return o + " " + c;
		}
	}
	int K;
	event[] events;
	final int oo = (int)1e9;
	int[] memo;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			K = in.nextInt();
			events = new event[K];
			memo = new int[1020-480+1];
			Arrays.fill(memo, -1);
			
			for(int k=0; k<K; k++) {
				events[k] = new event(in.nextInt()-480, in.nextInt()-480);
				if(events[k].o<0 || events[k].c>1020-480) {
					events[k].o = oo;
					events[k].c = oo;
				}
			}
			Arrays.sort(events);
			int ans = dp(0);
			System.out.println(ans);
		}
		
	}
	private int dp(int t) {
		if(t>1020-480) return 0;
		if(memo[t]!=-1) return memo[t];
		
		int best = 0;
		for(int i=0; i<K; i++) {
			if(events[i].o < t || events[i].c==oo) continue;
			best = Math.max(best, events[i].c-events[i].o+dp(events[i].c));
		}
		return memo[t]=best;
	}
	/**
	 * 2
5
500 520
520 601
600 700
650 800
800 1000
3
1232321 1240000
200 300
500 520
	 */
}
