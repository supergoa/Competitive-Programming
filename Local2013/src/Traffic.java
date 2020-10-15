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
		int from, to, flow;
		String name;
		public Street(int a, int b, int c, String d) {
			from = a;
			to = b;
			flow = c;
			this.name = d;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int M = in.nextInt();
			int E = in.nextInt();
			in.nextLine();
			Street[] streets = new Street[E];
			HashMap<String, ArrayList<Integer>> nameToStreet = new HashMap<>();
			FF ff = new FF(M);
			for(int e=0; e<E; e++) {
				String[] line = in.nextLine().split(",");
				streets[e] = new Street(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]),line[3]);
				if(!nameToStreet.containsKey(streets[e].name)) nameToStreet.put(streets[e].name, new ArrayList<>());
				nameToStreet.get(streets[e].name).add(e);
				ff.add(streets[e].from, streets[e].to, streets[e].flow);
			}
			ff.add(ff.s, 0, 987654321);
			ff.add(M-1, ff.t, 987654321);
			int[][] capCopy = copy(ff.cap);
			
			long initFlow = ff.ff();
			String maxFId = "";
			long maxFlow = initFlow;
			for(String s:nameToStreet.keySet()) {
				ff.cap = copy(capCopy);
				
				ArrayList<Integer> inds = nameToStreet.get(s);
				for(int i:inds) {
					ff.add(streets[i].from, streets[i].to, 987654321);
				}
				long curFlow = ff.ff();
				if(curFlow > maxFlow) {
					maxFId = s;
					maxFlow = curFlow;
				}
			}
			out.println(maxFId + " " + (maxFlow-initFlow));
		}
		
	}
	
	private int[][] copy(int[][] cap) {
		int[][] ret = new int[cap.length][cap.length];
		for(int i=0; i<ret.length; i++) {
			for(int j=0; j<ret.length; j++) {
				ret[i][j] = cap[i][j];
			}
		}
		return ret;
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
