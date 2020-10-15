import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Traffic {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Traffic().solve(in,out);
		in.close();
		out.close();
	}

	class Street {
		int x,y,z;
		String name;
		public Street(int a, int b, int c, String name) {
			x = a;
			y = b;
			z = c;
			this.name=name;
		}
	}
	final int oo = (int) (1e9+123);
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int N  = in.nextInt();
			int M = in.nextInt();
			FF ff = new FF(N);
			Street[] arr = new Street[M];
			in.nextLine();
			HashMap<String, ArrayList<Integer>> ids = new HashMap<>();
			for(int m=0; m<M; m++) {
				
				String[] line = in.nextLine().split(",");
				arr[m] = new Street(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), line[3]);
				ff.add(arr[m].x, arr[m].y, arr[m].z);
				
				if(!ids.containsKey(arr[m].name)) ids.put(arr[m].name, new ArrayList<>());
				ids.get(arr[m].name).add(m);
			}
			
			ff.add(ff.s, 0, oo);
			ff.add(N-1, ff.t, oo);
			int[][] cloneCap = copyFF(ff.cap);
			int startFF = ff.ff();
			int maxFF = startFF;
			String maxFFid = "";
			for(String s: ids.keySet()) {
				ff.cap = copyFF(cloneCap);
				ArrayList<Integer> sids = ids.get(s);
				for(int i=0; i<sids.size();i++) {
					ff.add(arr[sids.get(i)].x, arr[sids.get(i)].y, oo);
				}
				int flow = ff.ff();
				if(flow>maxFF) {
					maxFF=flow;
					maxFFid = s;
				}
			}
			out.println(maxFFid + " " + (maxFF-startFF));
		}
		
	}

	private int[][] copyFF(int[][] cap) {
		int[][] copy = new int[cap.length][cap.length];
		for(int i=0; i<cap.length; i++) {
			for(int j=0; j<cap.length; j++) {
				copy[i][j] = cap[i][j];
			}
		}
		return copy;
	}

	class FF {
		int[][] cap; boolean[] vis; int n,s,t,oo = (int)(1E9);
		public FF(int size) {n=size+2; s=n-2; t=n-1;
		cap = new int[n][n]; }
		void add(int v1, int v2, int c) { cap[v1][v2] = c; }
		int ff() {
			vis = new boolean[n]; int f=0;
			while(true) {
				Arrays.fill(vis, false); int res = dfs(s,oo);
				if(res==0) {break;} f+=res;
			}
			return f;
		}
		int dfs(int pos, int min) {
			if(pos==t) return min;
			if(vis[pos]) return 0;
			vis[pos] = true; int f = 0;
			for(int i=0; i< n; i++) {
				if(cap[pos][i] > 0 ) f = dfs(i, Math.min(cap[pos][i], min));
				if(f>0) {cap[pos][i] -= f; cap[i][pos] += f; return f;}	
			}
			return 0;
		}
	}
}
