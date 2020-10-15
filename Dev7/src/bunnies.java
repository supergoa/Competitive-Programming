import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

public class bunnies {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new bunnies().solve(in,out);
		in.close();
		out.close();
	}

	class Point {
		int x;int y;
		public Point(int a, int b) {
			x=a;
			y=b;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int R = in.nextInt();
			int C = in.nextInt();
			int[][] grid = new int[R][C];
			Point start = new Point(-1,-1);
			for(int i=0; i<R; i++) {
				String line = in.next();
				for(int j=0; j<C; j++) {
					grid[i][j] = line.charAt(j);
					if(grid[i][j]=='P') {
						start.x=i;
						start.y=j;
					}
				}
			}
			ArrayDeque<Point> q= new ArrayDeque<>();
			boolean[][] visited = new boolean[R][C];
			visited[start.x][start.y] = true;
			q.add(start);
			boolean found = false;
			while(!q.isEmpty()) {
				Point p = q.poll();
				if(grid[p.x][p.y]=='C') {
					found = true;
					break;
				}
				for(int i=-1; i<2; i++) {
					for(int j=-1; j<2; j++) {
						if(Math.abs(i+j)==1 && p.x+i<R && p.x+i>=0 && p.y+j<C && p.y+j>=0 && grid[p.x+i][p.y+j]!='#' && !visited[p.x+i][p.y+j]) {
							visited[p.x+i][p.y+j] = true;
							q.add(new Point(p.x+i, p.y+j));
						}
					}
				}
			}
			if(found) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
		
	}
}
