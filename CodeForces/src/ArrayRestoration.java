import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class ArrayRestoration {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new ArrayRestoration().solve(in,out);
		in.close();out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int Q = in.nextInt();
		
		SegmentTree st = new SegmentTree(N);
		int[] minInds = new int[Q+2];
		int[] maxInds = new int[Q+2];
		Arrays.fill(minInds, -1);
		Arrays.fill(maxInds, -1);
		int[] orig = new int[N];
		TreeSet<Integer> nonZeroInds = new TreeSet<>();
		TreeMap<Integer,Integer> valToInd = new TreeMap<>();
		int numZeros = 0;
		for(int n=0; n<N; n++) {
			int x = in.nextInt();
			if(x==0) { x = Q+1;numZeros++;}
			else {
				nonZeroInds.add(n);
				valToInd.put(x,n);
			}
			if(minInds[x]==-1) minInds[x] = n;
			maxInds[x] = n;
			//st.update(n, n, x);
			orig[n] = x;
		}
		st.update(0, N-1, 1);
		if(numZeros==N) { 
			out.println("YES");
			for(int i=0; i<N; i++) {
				out.print(Q + " ");
			}
			return;
		}
		
		boolean possible = true;
		for(int q=1; q<=Q; q++) {
			int minInd = minInds[q];
			int maxInd = maxInds[q];
			if(minInd==-1) { continue;}
			st.update(minInd, maxInd, q);
		}
		
		boolean haveQ = st.query(0, N-1)[1]==Q;
		if(!haveQ && numZeros>0) {
			for(int i=0; i<N; i++) {
				if(orig[i]==Q+1) {
					orig[i] = Q;
					st.update(i, i, Q);
					haveQ = true;
					break;
				}
			}
		}
		
		StringBuilder ans = new StringBuilder("");
		for(int i=0; i<N; i++) {
			if(orig[i]==Q+1) {
				Integer lower = nonZeroInds.lower(i);
				Integer higher = nonZeroInds.higher(i);
				if(lower!=null) ans.append(orig[lower] + " ");
				else if(higher!=null) ans.append(orig[higher] + " ");
				else ans.append(Q + " ");
			} else if(orig[i]!=st.query(i,i)[0]){
				possible = false;
			} else ans.append(orig[i] + " ");
			
			if(!possible) break;
		}
		
		if(possible && haveQ) {
			out.println("YES");
			out.println(ans);
		} else {
			out.println("NO");
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
			delta[i] = -1;
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
			if (lr == left && rr == right) {
				
				set(i, value);
				
			}
			else {
				if(delta[i]!=-1) prop(i);
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
			if (lr == left && rr == right) {
				return new long[] {min[i], max[i]};
			}
			else {
				if(delta[i]!=-1) prop(i);
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
