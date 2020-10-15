
// i think this is wrong
public class SegmentTree1 {
	int[] a;
	int n;
	int[] minTree;
	int[] l;
	int[] r;
	final int oo = (int) 1e9;
	public SegmentTree1(int nn, int[] aa) {
		n=nn;
		a=aa;
		minTree=new int[4*n];
		l = new int[4*n];
		r = new int[4*n];
		buildMin(0, 0, n-1);
	}
	private void buildMin(int node, int ll, int rr) {
		l[node] = ll;
		r[node] = rr;
		if(ll==rr) {
			minTree[node]=a[ll];
			return;
		} 	
		int mid = (ll+rr)/2;
		buildMin(2*node+1, ll, mid);
		buildMin(2*node+2, mid+1, rr);
		minTree[node] = Math.min(minTree[2*node+1], minTree[2*node+2]);
	}
	//pass 0 to node
	public int min(int node, int ll, int rr) {
		if(l[node] > rr || r[node] < ll) {
			return oo;
		}
		if(ll <= l[node] && rr>= r[node]) {
			return minTree[node];
		}
		return Math.min(min(2*node+1, ll, rr), min(2*node+2, ll, rr));
	}
}
