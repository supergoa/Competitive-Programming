import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class FoxAndTwoDots {
	static PrintWriter out;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out = new PrintWriter(System.out);
		new FoxAndTwoDots().solve(in,out);
		in.close();
		out.close();
		
	}

	class Point {
		int x,y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	int N;
	int M;
	Point start = null;
	int color;
	int[][] grid;
	HashMap<Integer, HashSet<Integer>> used = new HashMap<>();
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		M = in.nextInt();
		HashSet<Integer> colors = new HashSet<>();
		grid = new int[N][M];
		for(int n=0; n<N; n++) {
			String line = in.next();
			for(int m=0; m<M; m++) {
				grid[n][m] = line.charAt(m);
				colors.add(grid[n][m]);
			}
		}
		
		for(int color : colors) {
			this.color = color;
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
					if(color == grid[n][m]) {
						if(used.get(n)==null) used.put(n, new HashSet<>());
						start = new Point(n,m);
						used.get(n).add(m);
						dp(n,m,0);
						used.get(n).remove(m);
					}
				}
			}
		}
		out.println("No");
	}
	private void dp(int n, int m, int count) {
		if(count>=3 && adjToStart(start, n, m)) {
			out.println("Yes");
			out.flush();
			System.exit(0);
		}
		for(int i=-1; i<2; i++) {
			for(int j=-1; j<2; j++) {
				if(Math.abs(i)+Math.abs(j)==1 && inBounds(n+i,m+j) && used.get(n+i)!=null && !used.get(n+i).contains(m+j) && grid[n+i][m+j] == color) {
					used.get(n+i).add(m+j);
					dp(n+i, m+j, count + 1);
					used.get(n+i).remove(m+j);
				}
			}
		}
		
	}
	private boolean adjToStart(Point start, int x, int y) {
		if(Math.abs(start.x-x) + Math.abs(start.y-y) == 1) return true;
		return false;
	}
	private boolean inBounds(int x, int y) {
		if(x>=N || x<0 || y>=M || y<0) return false;
		return true;
	}
}