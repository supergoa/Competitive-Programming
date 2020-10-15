import java.io.PrintWriter;
import java.util.Scanner;

public class ZoningHouses {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new ZoningHouses().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int Q = in.nextInt();
		
		SegmentTree xs = new SegmentTree(N);
		SegmentTree ys = new SegmentTree(N);
		int[] xx = new int[N + 1];
		int[] yy = new int[N + 1];
		for(int i = 1; i <= N; ++i) {
			int x = in.nextInt();
			int y = in.nextInt();
			xx[i] = x;
			yy[i] = y;
		}
		xs.a = xx;
		ys.a = yy;
		xs.build(1, 1, N);
		ys.build(1, 1, N);
		
		for(int i=0; i<Q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			//if(b-a==1) {out.println(0); continue;}
			
			Pair maxX = xs.queryMax(1, a, b);
			Pair minX = xs.queryMin(1, a, b);
			Pair maxY = ys.queryMax(1, a, b);
			Pair minY = ys.queryMin(1, a, b);
			
			Pair[] arr = new Pair[] {maxX, minX, maxY, minY};
			int ans = 2*oo;
			for(int j=0; j<4; j++) {
				int ind = arr[j].index;
				int maX = Math.max(xs.queryMax(1, a, ind-1).value, xs.queryMax(1, ind+1, b).value);
				int miX = Math.min(xs.queryMin(1, a, ind-1).value, xs.queryMin(1, ind+1, b).value);
				int maY = Math.max(ys.queryMax(1, a, ind-1).value, ys.queryMax(1, ind+1, b).value);
				int miY = Math.min(ys.queryMin(1, a, ind-1).value, ys.queryMin(1, ind+1, b).value);
				ans = Math.min(ans, Math.max(maX-miX, maY-miY));
			}
			out.println(ans);
		}
		
	}
	int oo = (int) (1e9 + 10);
	Pair inf = new Pair(oo, oo);
	Pair ninf = new Pair(-oo, -oo);
	
	class Pair {
		int value;
		int index;
		Pair(int v, int i) {
			value = v;
			index = i;
		}
	}
	
	class SegmentTree {
		int[] a;
		Pair[] minTree, maxTree;
		int[] l, r;
		SegmentTree(int n) {
			minTree = new Pair[4 * n + 1];
			maxTree = new Pair[4 * n + 1];
			l = new int[4 * n + 1];
			r = new int[4 * n + 1];
		}
		void build(int node, int left, int right) {
			l[node] = left; r[node] = right;
			if(left == right) {
				minTree[node] = maxTree[node] = new Pair(a[left], left);
				return;
			}
			int mid = left + right >> 1;
			build(node * 2, left, mid);
			build(node * 2 + 1, mid + 1, right);
			Pair minLeft = minTree[node * 2], minRight = minTree[node * 2 + 1];
			if(minLeft.value < minRight.value) {
				minTree[node] = minLeft;
			} else {
				minTree[node] = minRight;
			}
			Pair maxLeft = maxTree[node * 2], maxRight = maxTree[node * 2 + 1];
			if(maxLeft.value > maxRight.value) {
				maxTree[node] = maxLeft;
			} else {
				maxTree[node] = maxRight;
			}
		}
		Pair queryMin(int node, int left, int right) {
			if(left > right || right < l[node] || left > r[node]) return inf;
			if(left <= l[node] && r[node] <= right) return minTree[node];
			Pair ll = queryMin(node * 2, left, right);
			Pair rr = queryMin(node * 2 + 1, left, right);
			if(ll.value < rr.value) return ll;
			return rr;
		}
		Pair queryMax(int node, int left, int right) {
			if(left > right || right < l[node] || left > r[node]) return ninf;
			if(left <= l[node] && r[node] <= right) return maxTree[node];
			Pair ll = queryMax(node * 2, left, right);
			Pair rr = queryMax(node * 2 + 1, left, right);
			if(ll.value > rr.value) return ll;
			return rr;
		}
	}
}
