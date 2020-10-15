import java.io.PrintWriter;
import java.util.Scanner;

public class C2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C2().solve(in, out);
		in.close();
		out.close();
	}

	boolean[] used;
	int[] lengths;
	int N, l1, l2;
	private void solve(Scanner in, PrintWriter out) {
		l1 = in.nextInt();
		l2 = in.nextInt();
		N = in.nextInt();
		used = new boolean[N];
		lengths = new int[N];
		for(int n=0; n<N; n++) {
			lengths[n] = in.nextInt();
		}
		out.println(dp(false,false,0,0));
	}

	final int oo = (int) 1e9;
	private int dp(boolean chose1, boolean chose2, int curr1, int curr2) {
		if(curr1 >= N || curr2 >= N) return -oo;
		if(chose1 && chose2) return 0;
		
		int sum = lengths[curr1] + lengths[curr2];
		int choose1 = 0;
		int choose3 = 0;
		if(curr1!=curr2) {
			if(sum<=l1) {
				choose1 = lengths[curr1] + lengths[curr2] + dp(true, chose2, curr1+1, curr2+1);
			}
			if(sum<=l2) {
				choose3 = lengths[curr1] + lengths[curr2] + dp(chose1, true, curr1+1, curr2+1);
			}
		}
		int leave1 = dp(chose1, chose2, curr1+1, curr2);
		int leave2 = dp(chose1, chose2, curr1, curr2+1);
		return Math.max(leave1, Math.max(leave2,  Math.max(choose3, choose1)));
	}
}
