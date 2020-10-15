import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class welcome {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new welcome().solve(in,out);
		in.close();
		out.close();
	}
	
	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			HashMap<Character, Integer> idsf = new HashMap<>();
			HashMap<Character, Integer> idsl = new HashMap<>();
			String[] names = new String[N];
			in.nextLine();
			for(int n=0; n<N; n++) {
				names[n] = in.nextLine();
				if(!idsf.containsKey(names[n].split(" ")[0].charAt(0)))
					idsf.put(names[n].split(" ")[0].charAt(0), idsf.size());
				if(!idsl.containsKey(names[n].split(" ")[1].charAt(0)))
					idsl.put(names[n].split(" ")[1].charAt(0), idsl.size());
				//System.out.println(n+""+names[n]);
			}
			FF ff = new FF(2*N);
			for(int n=0; n<N; n++) {
				//ff.add(ff.s, n, 1);
				int id1 = idsf.get(names[n].split(" ")[0].charAt(0));
				int id2 = idsl.get(names[n].split(" ")[1].charAt(0))+N;
				ff.add(ff.s, id1, 1);
				ff.add(id1, id2, 1);
				ff.add(id2, ff.t, 1);
			}
			//ff.add(ff.s, ff.t, 1);
			int ans = ff.ff();
			System.out.println(ans);
		}
		
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
