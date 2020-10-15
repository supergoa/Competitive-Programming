import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class guards {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new guards().solve(in,out);
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
	
	private boolean inTime(int starti, int endi, int startTime, int endTime) {
		if(startTime == endTime) return true;
		boolean good = true;
		for(int i=starti; i!=endi; i+=30) {
			if(starti>=startTime && endi<=endTime);
			else good = false;
			starti %= 24*60;
		}
		if(good) return true;
		else return false;
	}

	class Time {
		int start,end;
		public Time(int a, int b) {
			start = a;
			end = b;
		}
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
				ff.add(ff.s, n, M/30);
				
				boolean[] times = new boolean[24*60];
				for(int k=0; k<K; k++) {
					String s = in.next();
					String e = in.next();
					int stime = Integer.parseInt(s.split(":")[0])*60 + Integer.parseInt(s.split(":")[1]);
					int etime = Integer.parseInt(e.split(":")[0])*60 + Integer.parseInt(e.split(":")[1]);
					if(stime == etime) Arrays.fill(times, true);
					for(int i=stime; i!=etime; i++) {
						if(i==24*60) i = 0;
						if(i==etime) break;
						times[i] = true;
					}

				}
				for(int i=N, start = 0, end = 30; i<48+N; i++, end+=30, start+=30) {
					boolean good = true;
					for(int j = start; j<end; j++) {
						if(!times[j]) {
							good = false;
							break;
						}
					}
					if(good) ff.add(n, i, 1);
				}
			}
			int[][] copy = copyFF(ff.cap);
			int ans = ff.ff();
			int curFlow = ans;
			if(ans < 48) {
				out.println(0);
			} else {
				int iteration = 1;
				while(iteration * 48 == curFlow) {
					ff.cap = copyFF(copy);
					iteration++;
					for(int i=0; i<48; i++) {
						ff.add(N+i, ff.t, iteration);
					}
					curFlow = ff.ff();
				}
				out.println(iteration-1);
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
