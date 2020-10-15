
public class ObjectBasedSegTree {
	final long oo = Long.MAX_VALUE;
	class Node {
		int l, r;
		Node left, right;
		long value, lazy;
		
		public Node(int ll, int rr, long[] a) {
			l=ll;
			r=rr;
			if(l==r) {
				value = a[l];
				return;
			}
			int m = (l+r)/2;
			left = new Node(l,m,a);
			right = new Node(m+1, r, a);
			value = Math.min(left.value, right.value);
		}
		
		long get() {
			return value+lazy;
		}
		
		void prop() {
			if(lazy==0)return;
			value+=lazy;
			left.lazy+=lazy;
			right.lazy+=lazy;
			lazy=0;
		}
		long query(int ll, int rr) {
			if(rr<l || ll>r) return oo;
			if(ll<=l && r<= rr) return get();
			prop();
			return Math.min(left.query(ll, rr), right.query(ll, rr));
		}
		void add(int ll, int rr, long d) {
			if(rr<l || ll>r) return;
			if(ll<=l && r<= rr) {
				lazy+=d;
				return;
			}
			prop();
			left.add(ll, rr, d);
			right.add(ll, rr, d);
			value = Math.min(left.get(), right.get());
		}
	}
}
