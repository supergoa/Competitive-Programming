import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class KuriyamaMirai {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new KuriyamaMirai().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		Long[] arr = new Long[N+1];
		Long[] arr2 = new Long[N+1];
		arr[0]=(long) 0;arr2[0]=(long) 0;
		for(int n=1; n<=N; n++) {
			Long x = in.nextLong();
			arr[n] = x;
			arr2[n] = x;
		}
		Arrays.sort(arr);
		for(int n=1; n<=N; n++) {
			arr[n] += arr[n-1];
			arr2[n] += arr2[n-1];
		}
		int M = in.nextInt();
		for (int m=0; m<M; m++) {
			int type = in.nextInt();
			int l = in.nextInt();
			int r = in.nextInt();
			if(type==1) {
				out.println(arr2[r]-arr2[l-1]);
			} else {
				out.println(arr[r]-arr[l-1]);
			}
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
			delta[i] += value;
			max[i] += value;
			min[i] += value;
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
