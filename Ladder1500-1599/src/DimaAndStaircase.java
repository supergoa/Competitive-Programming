import java.io.PrintWriter;
import java.util.Scanner;

public class DimaAndStaircase {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new DimaAndStaircase().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		SegmentTree st = new SegmentTree(N);
		for(int n=0; n<N; n++) {
			st.update(n, n, in.nextInt());
			//System.out.println(st.query(n, n)[1]);
		}
		int M = in.nextInt();
		for(int m=0; m<M; m++) {
			int w = in.nextInt();
			int h = in.nextInt();
			int stairHeight = (int) st.query(0, w-1)[1];
			out.println(stairHeight);
			//for(int i=0; i<=w-1; i++) {
				st.update(0, w-1, h+stairHeight);
			//}
		}
	}

	class SegmentTree {
		long[] min, max, delta;
		int n;
		
		public SegmentTree(int n) {
			this.n = n;
			min = new long[n * 4 + 1];
			max = new long[n * 4 + 1];
			delta = new long[n * 4 + 1];
		}
		
		void prop(int i) {
			set(i * 2, delta[i]);
			set(i * 2 + 1, delta[i]);
			delta[i] = 0;
		}
		
		void set(int i, long value) {
			delta[i] = value;
			max[i] = value;
			min[i] = value;
		}
		
		void update(int left, int right, long value) {
			update(1, 0, n-1, left, right, value);
		}
		
		void update(int i, int lr, int rr, int left, int right, long value) {
			if (lr == left && rr == right)
				set(i, value);
			else {
				prop(i);
				int mid = (lr + rr) / 2;
				if (left <= mid)
					update(i * 2, lr, mid, left, Math.min(mid,  right), value);
				if (mid < right)
					update(i * 2 + 1, mid + 1, rr, Math.max(mid + 1, left), right, value);
				min[i] = Math.min(min[i * 2], min[i * 2 + 1]);
				max[i] = Math.max(max[i * 2], max[i * 2 + 1]);
			}
		}
		
		long[] query(int left, int right) {
			return query(1, 0, n-1, left, right);
		}
		
		long[] query(int i, int lr, int rr, int left, int right) {
			if (lr == left && rr == right)
				return new long[] {min[i], max[i]};
			else {
				prop(i);
				int mid = (lr + rr) / 2;
				long[] ret = new long[] {Long.MAX_VALUE, Long.MIN_VALUE};
				if (left <= mid) {
					long[] l = query(i * 2, lr, mid, left, Math.min(mid, right));
					ret[0] = Math.min(ret[0], l[0]);
					ret[1] = Math.max(ret[1], l[1]);
				}
				if (mid < right) {
					long[] r = query(i * 2 + 1, mid + 1, rr, Math.max(mid + 1, left), right);
					ret[0] = Math.min(ret[0], r[0]);
					ret[1] = Math.max(ret[1], r[1]);
				}
				return ret;
			}
		}
	}

}
