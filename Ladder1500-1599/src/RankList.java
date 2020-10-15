import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class RankList {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new RankList().solve(in,out);
		in.close();
		out.close();
	}

	class Pair implements Comparable<Pair>{
		int p,t;
		public Pair(int p, int t) {
			this.p=p;
			this.t=t;
		}
		@Override
		public int compareTo(Pair arg0) {
			if(this.p==arg0.p) {
				return -Integer.compare(t, arg0.t);
			}
			return Integer.compare(p, arg0.p);
		}
		@Override
		public String toString() {
			return p + " " + t;
		}
		
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int K = in.nextInt();
		Pair[] arr = new Pair[N];
		for(int n=0; n<N;n++) {
			arr[n] = new Pair(in.nextInt(), in.nextInt());
		}
		Arrays.sort(arr);
		//System.out.println(Arrays.toString(arr));
		//System.out.println(arr.length);
		int finalP = arr[N-K].p;
		int finalT = arr[N-K].t;
		//System.out.println(finalP + " " +finalT);
		int ans = 0;
		for(Pair p:arr) {
			if(p.p==finalP && p.t==finalT)
				ans++;
		}
		out.println(ans);
	}

}
