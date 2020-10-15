import java.io.PrintWriter;
import java.util.Scanner;

public class BubbleSortGraph {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new BubbleSortGraph().solve(in, out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int[] arr = new int[N];
		for (int n = 0; n < N; n++) {
			arr[n] = in.nextInt()-1;
		}
		SegmentTree st = new SegmentTree(N, new int[N]);
		for(int n=0; n<N; n++) {
			int num = arr[n];
			int place = st.max(0, 0, num)+1;
			st.update(0, num, place);
		}
		out.println(st.max(0, 0, N-1));

	}

	public class SegmentTree {
		int[] a;
		int n;
		int[] maxTree;
		int[] l;
		int[] r;
		final int oo = (int) 1e9;

		public SegmentTree(int nn, int[] aa) {
			n = nn;
			a = aa;
			maxTree = new int[4 * n];
			l = new int[4 * n];
			r = new int[4 * n];
			buildMax(0, 0, n - 1);
		}

		private void buildMax(int node, int ll, int rr) {
			l[node] = ll;
			r[node] = rr;
			if (ll == rr) {
				maxTree[node] = a[ll];
				return;
			}
			int mid = (ll + rr) / 2;
			buildMax(2 * node + 1, ll, mid);
			buildMax(2 * node + 2, mid + 1, rr);
			maxTree[node] = Math.max(maxTree[2 * node + 1], maxTree[2 * node + 2]);
		}

		// pass 0 to node
		public int max(int node, int ll, int rr) {
			if (l[node] > rr || r[node] < ll) {
				return -oo;
			}
			if (ll <= l[node] && rr >= r[node]) {
				return maxTree[node];
			}
			return Math.max(max(2 * node + 1, ll, rr), max(2 * node + 2, ll, rr));
		}

		// at index ind, add the value val
		void update(int node, int ind, int val) {
			if(l[node] > ind || r[node] < ind) {
				return;
			}
			if(l[node]==r[node]) {
				maxTree[node] += val;
				return;
			}
			update(2 * node + 1, ind, val);
			update(2 * node + 2, ind, val);
			maxTree[node] = Math.max(maxTree[2 * node + 1], maxTree[2 * node + 2]);
		}
	}
}
