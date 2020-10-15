import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MuseumGuards {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new MuseumGuards().solve(in,out);
		in.close();
		out.close();
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

	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			
			FF ff = new FF(N+48);
			for(int i=N; i<N+48; i++) ff.add(i, ff.t, 1);
			for(int n=0; n<N; n++) {
				int K = in.nextInt();
				int M = in.nextInt();
				//System.out.println(K + " " + M);
			    //System.out.println();
				ff.add(ff.s, n, M/30);
				for(int k=0; k<K; k++) {
					String s = in.next();
					String e = in.next();
					int shour = Integer.parseInt(s.split(":")[0]);
					int smin = Integer.parseInt(s.split(":")[1]);
					int ehour = Integer.parseInt(e.split(":")[0]);
					int emin = Integer.parseInt(e.split(":")[1]);
					if(shour == ehour && smin == emin) {
						ehour = 23;
						emin = 30;
						shour = 0;
						smin = 0;
					}
					while(smin%30!=0) smin++;
					int timeLeft = Math.abs(ehour*60+emin - (shour*60+smin));
					int startTime = shour*60+smin;
					//System.out.println(timeLeft + " " + startTime);
					while(timeLeft >= 30) {
						startTime%=24*60;
						//System.out.println(n + " " + (N+startTime/30));
						ff.add(n, N+startTime/30, 1);
						timeLeft-=30;
						startTime+=30;
					}
				}
			}
			int[][] copy = copyFF(ff.cap);
			int ans = ff.ff();
			int newAns = ans;
			int maxGuards = 987654321;
			if(ans < 48) {
				out.println(0);
			} else {
				for(int i=N; i<N+48; i++) {
					ff.cap = copyFF(copy);
					ff.add(i, ff.t, 987654321);
					newAns = ff.ff();
					//System.out.println(newAns-ans);
					maxGuards = Math.min(maxGuards, newAns-ans+1);
				}
				if(maxGuards == 987654321) maxGuards =1;
				out.println(maxGuards);
			}
			
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
