import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class g {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new g().solve(in,out);
		in.close();
		out.close();
	}

	class Q {
		char com;
		int a,b;
		public Q(char z,int c,int d) {
			com = z;
			a=c;b=d;
		}
	}
	int[] newIds;
	int[] depths;
	int[] starts;
	int[] ends;
	int maxDepth = 0;
	HashMap<Integer, Integer> ids;
	TreeMap<Integer, Integer>[] depthIDs;
	ArrayList<Integer>[] tree;
	int globalID;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		
		Q[] queries = new Q[N];
		int zCount = 1;
		ids = new HashMap<>();
		ids.put(0,0);
		for(int n=0; n<N; n++) {
			char c = in.next().toCharArray()[0];
			if(c=='Z') {
				zCount++;
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				
				if(!ids.containsKey(a)) ids.put(a, ids.size());
				queries[n] = new Q(c,ids.get(a), ids.get(b));
			} else {
				int a = in.nextInt()-1;
				queries[n] = new Q(c,ids.get(a), in.nextInt());
			}
		}
		
		tree = new ArrayList[zCount];
		for(int i=0; i<zCount; i++) tree[i] = new ArrayList<>();
		
		for(int n=0; n<N; n++) {
			if(queries[n].com=='Z') {
				tree[queries[n].b].add(queries[n].a);
			}
		}
		
		newIds = new int[zCount];
		starts = new int[zCount];
		ends = new int[zCount];
		depths = new int[zCount];
		globalID = 0;
		labelNodes(0, 0);
		
		//System.out.println(zCount + " " + globalID);
		
		depthIDs = new TreeMap[maxDepth+1];
		for(int i=0; i<=maxDepth; i++) depthIDs[i] = new TreeMap<>();
		for(int i=0; i<zCount; i++) {
			depthIDs[depths[i]].put(i, depthIDs[depths[i]].size());
			//System.out.println(i + " " + depths[i] + " " + depthIDs[depths[i]].size());
		}
		
		Node[] segtrees = new Node[maxDepth+1];
		for(int i=0; i<=maxDepth; i++) {
			segtrees[i] = new Node(0, depthIDs[i].size()-1, new long[depthIDs[i].size()]);
		}
		//System.out.println("~~~~~~~~~~~~~");
		segtrees[0].add(0, 0, 1);
		for(int n=0; n<N; n++) {
			if(queries[n].com=='Z') {
				//System.out.println("Z");
				int id = newIds[queries[n].a];
				int depth = depths[id];
				//System.out.println(queries[n].a + " " + id + " " + depth + " " + depthIDs[depth].get(id));
				
				segtrees[depth].add(depthIDs[depth].get(id), depthIDs[depth].get(id), 1);
				//System.out.println(segtrees[depth].querey(0, 1));
				//System.out.println();
			} else {
				int id = newIds[queries[n].a];
				int depth = depths[id];
				int depthToQuerey = depth + queries[n].b+1;
				if(depthToQuerey > maxDepth) {System.out.println(0); continue;}
				
				Integer startQ = null, endQ = null;
				Entry startE = depthIDs[depthToQuerey].ceilingEntry(starts[id]);
				Entry endE = depthIDs[depthToQuerey].floorEntry(ends[id]);
				if(startE != null) startQ = (Integer) startE.getValue();
				if(endE != null) endQ = (Integer) endE.getValue();
				
				if(startQ==null || endQ==null || endQ < startQ) {System.out.println(0); continue;}
				
				System.out.println(segtrees[depthToQuerey].querey(startQ, endQ));
			}
		}
	}
	
	private void labelNodes(int node,int depth) {
		newIds[node] = globalID++;
		depths[newIds[node]] = depth;
		starts[newIds[node]] = (globalID-1);
		maxDepth = Math.max(maxDepth, depth);
		for(int a : tree[node]) {
			labelNodes(a, depth+1);
		}
		ends[newIds[node]] = (globalID-1);
		//System.out.println((node+1) + " " + ids[node] + " " + starts[ids[node]] + " " + ends[ids[node]]);
	}

	class Node {
		Node left, right;
		int l, r;
		long val, lazy;
		public Node(int ll, int rr, long[] a) {
			l=ll;
			r=rr;
			if(l==r) {
				val = a[l];
				return;
			}
			int mid = (ll+rr)/2;
			left = new Node(ll, mid, a);
			right = new Node(mid+1, rr, a);
			val = left.val + right.val;
		}
		long get() {
			return val + lazy;
		}
		void prop() {
			val+=lazy;
			left.lazy+=lazy;
			right.lazy+=lazy;
			lazy=0;
		}
		void add(int ll, int rr, int d) {
			if(rr<l || ll>r) return;
			if(l>=ll && r<=rr) {
				lazy+=d;
				return;
			}
			prop();
			left.add(ll, rr, d);
			right.add(ll, rr, d);
			val = left.get() + right.get();
		}
		long querey(int ll, int rr) {
			if(rr<l || ll>r) return 0;
			if(l>=ll && r<=rr) {
				return get();
			}
			prop();
			return left.querey(ll, rr) + right.querey(ll, rr);
		}
	}
}
