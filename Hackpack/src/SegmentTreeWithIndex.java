class SegmentTreeWithIndex {
	class Pair implements Comparable<Pair> {
		int index, value;
		Pair(int i, int v) {
			index = i; value = v;
		}
		public int compareTo(Pair o) {
			return value - o.value;
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
				max[node] = (left.value > right.value) ? left : right;
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
			return (left.value > right.value) ? left : right;
		}
	}
}