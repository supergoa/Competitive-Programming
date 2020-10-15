import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class DropZone {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new DropZone().solve(in,out);
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		
		for(int t=0;t<T; t++) {
			int R = in.nextInt();
			int C = in.nextInt();
			char[][] map = new char[R][C];
			int[][] mapID = new int[R][C];
			int mapids = 0;
			for(int r=0; r<R; r++) {
				String line = in.next();
				for(int c=0; c<C; c++) {
					map[r][c] = line.charAt(c);
					mapID[r][c] =  mapids++;
				}
			}
			int ansToAdd = 0;
			FF ff = new FF(R*C);
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(map[r][c] == 'D') {
						if(r==0||r==R-1||c==0||c==C-1) {
							for(int i=-1; i<2; i++) {
								for(int j=-1; j<2; j++) {
									if(Math.abs(i+j)==1 && (r+i>=R || r+i<0 || j+c>=C || j+c<0)) {
										ansToAdd++;
									}
								}
							}
						}
					}
				}
			}
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(map[r][c] == 'X') continue;
					if(map[r][c] == '.') {
						if(r==0||r==R-1||c==0||c==C-1) {
							ff.add(ff.s, mapID[r][c], 1);
						}
						for(int i=-1; i<2; i++) {
							for(int j=-1; j<2; j++) {
								if(Math.abs(i+j)==1 && r+i<R && r+i>=0 && j+c<C && j+c>=0 && map[r+i][j+c]!='X') {
									ff.add(mapID[r][c], mapID[r+i][c+j], 1);
								}
							}
						}
					}
					if(map[r][c] == 'D') {
						ff.add(mapID[r][c], ff.t, 987654321);
					}
					
				}
			}
			out.println(ansToAdd+ff.ff());
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
