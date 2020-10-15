import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MaximumAbsurdity {
	static PrintWriter out;
	public static void main(String[] args) {	
		Scanner in = new Scanner(System.in);
		out = new PrintWriter(System.out);
		new MaximumAbsurdity().solve(in,out);
		out.close();
	}
	
	/*class Absurd implements Comparable<Absurd>{
		int left;
		long value;
		
		public Absurd(int a, long b) {
			left = a;
			value = b;
		}

		@Override
		public int compareTo(Absurd o) {
			if(o==null) return 1;
			if(value == o.value) {
				return o.left - left;	
			}
			return Long.compare(value,o.value);	
		}
	}*/
	private void solve(Scanner in, PrintWriter out2) {
		int N = in.nextInt();
		int K = in.nextInt();
		
		int[] abusurdity = new int[N];
		ArrayList<Pair> combinedAbusurdity = new ArrayList<>();
		long[] cumsum = new long[N];
		for(int n=0; n<N; n++) {
			abusurdity[n] = in.nextInt();
			cumsum[n] = abusurdity[n];
			if(n>0) cumsum[n] += cumsum[n-1];
		}
		
		for(int n=0; n<N; n++) {
			if(n+(K-1)>=N) break;
			int right = n+(K-1);
			int left = n-1;
			long sum = 0;
			
			if(left < 0) {
				sum = cumsum[right];
			} else {
				sum = cumsum[right] - cumsum[left];
			}
			combinedAbusurdity.add(new Pair(n, sum));
		}
		
		int size = combinedAbusurdity.size();
		SegmentTree st = new SegmentTree(combinedAbusurdity.toArray(new Pair[size]));
		
		long bestAns = 0;
		long a = 0;
		long b = 0;
		for(int i =0; i < combinedAbusurdity.size(); i++) {
			int ind = combinedAbusurdity.get(i).index;
			int upper = ind+K;
			int lower = ind-K;
			if(lower>=0) {
				Pair lowerSum = st.queryMax(0, 0, size-1, 0, lower);
				if(lowerSum.value + combinedAbusurdity.get(i).value > bestAns) {
					bestAns = lowerSum.value + combinedAbusurdity.get(i).value;
					a = lowerSum.index;
					b = ind;
				}
			}
			if(upper<size) {
				Pair higherSum = st.queryMax(0, 0, size-1, upper, size-1);
				if(higherSum.value + combinedAbusurdity.get(i).value > bestAns) {
					bestAns = higherSum.value + combinedAbusurdity.get(i).value;
					b = higherSum.index;
					a = ind;
				}
			}
		}
		out.print(++a + " " + ++b);
	}
	
	class Pair implements Comparable<Pair> {
		int index;
		long value;
		Pair(int i, long v) {
			index = i; value = v;
		}
		public int compareTo(Pair o) {
			if(value == o.value) {
				return o.index - index;	
			}
			return Long.compare(value, o.value);
		}
		@Override
		public
		String toString() {
			return index + " " + value;
		}
	}
	
	final int oo = (int) 1e9 + 10;
	final Pair inf = new Pair(-1, oo);
	final Pair ninf = new Pair(-1, -oo);
	
	class SegmentTree {
		Pair[] min, max;
		Pair[] a;
		int n;
		SegmentTree(Pair[] a) {
			this.a = a;
			n = a.length;
			min = new Pair[4 * n + 1];
			max = new Pair[4 * n + 1];
			init(0, 0, n - 1);
		}
		void init(int node, int l, int r) {
			if(l == r) {
				min[node] = max[node] = a[l];
				return;
			}
			int mid = (l + r) / 2;
			init(node * 2 + 1, l, mid);
			init(node * 2 + 2, mid + 1, r);
			{
				Pair left = min[node * 2 + 1], right = min[node * 2 + 2];
				min[node] = (left.value < right.value) ? left : right;
			}
			{
				Pair left = max[node * 2 + 1], right = max[node * 2 + 2];
				max[node] = (left.compareTo(right) > 0) ? left : right;
			}
		}
		//l, r => node range
		//ll, rr => query range
		Pair queryMin(int node, int l, int r, int ll, int rr) {
			if(l >= ll && r <= rr) return min[node]; //node is completely in query range
			if(r < ll || l > rr) return inf;
			int mid = (l + r) / 2;
			Pair left = queryMin(node * 2 + 1, l, mid, ll, rr);
			Pair right = queryMin(node * 2 + 2, mid + 1, r, ll, rr);
			return (left.value < right.value) ? left : right;
		}
		Pair queryMax(int node, int l, int r, int ll, int rr) {
			if(l >= ll && r <= rr) return max[node]; //node is completely in query range
			if(r < ll || l > rr) return ninf;
			int mid = (l + r) / 2;
			Pair left = queryMax(node * 2 + 1, l, mid, ll, rr);
			Pair right = queryMax(node * 2 + 2, mid + 1, r, ll, rr);
			return (left.compareTo(right) > 0) ? left : right;
		}
	}
}
