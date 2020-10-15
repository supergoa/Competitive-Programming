import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class F {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new F().solve(in, out);
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
	int[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		T = in.nextInt();
		memo = new int[T+1][N+1];
		for(int i=0; i<T+1; i++) {
			Arrays.fill(memo[i], -1);
		}
		people = new Pair[N];
		for(int n=0;n<N; n++) {
			people[n] = new Pair(in.nextInt(), in.nextInt());
		}
		Arrays.sort(people);
		out.println(dp(0,0));
	}
	private int dp(int timeLeft, int curr) {
		if(timeLeft>=T || curr >= N) {
			return 0;
		}
		if(memo[timeLeft][curr] != -1) return memo[timeLeft][curr];
		boolean tooLate = false;
		if(people[curr].timeLeaves < timeLeft) {
			tooLate = true;
		}
		int cash = 0;
		if(!tooLate) {
			cash = people[curr].cash;
		}
		int choose = cash + dp(timeLeft+1, curr+1);
		int leave = dp(timeLeft, curr+1);
		
		return memo[timeLeft][curr] = Math.max(choose, leave);
	}
}
