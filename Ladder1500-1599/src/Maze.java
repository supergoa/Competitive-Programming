import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Maze {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Maze().solve(in,out);
		in.close();
		out.close();
	}

	class Point implements Comparable<Point>{
		int x,y,level;
		public Point(int a, int b, int c) {
			x = a;
			y = b;
			level = c;
		}
		@Override
		public int compareTo(Point o) {
			return o.level-level;
		}
	}
	private void solve(Scanner in, PrintWriter out) throws Exception {
		int N = in.nextInt();
		int M = in.nextInt();
		int K = in.nextInt();
		
		char[][] grid = new char[N][M];
		boolean[][] visited = new boolean[N][M];
		int firstI = -1;
		int firstJ = -1;
		for(int i=0; i<N; i++) {
			String line = in.next();
			for(int j=0; j<M; j++) {
				grid[i][j] = line.charAt(j);
				if(grid[i][j]=='.' && firstI ==-1 && firstJ==-1) {
					firstI = i;
					firstJ = j;
				}
			}
		}
		
		ArrayDeque<Point> q = new ArrayDeque<>();
		PriorityQueue<Point> leavesFirst = new PriorityQueue<>();
		q.add(new Point(firstI, firstJ, 0));
		visited[firstI][firstJ] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			int x = p.x;
			int y = p.y;
			int level = p.level;
			
			
			
			//System.out.println(x + " " + y);
			for(int i=-1; i<2; i++) {
				for(int j=-1; j<2; j++) {
					if(Math.abs(i+j)==1 && (x+i)<N && (x+i)>=0 && (y+j)<M && (y+j)>=0 && grid[x+i][y+j]!='#' && !visited[x+i][y+j]) {
						Point toAdd = new Point(x+i, y+j, level + 1);
						q.add(toAdd);
						leavesFirst.add(toAdd);
						visited[x+i][y+j] = true;
					}
				}
			}
		}
		int temp = K;
		while(!leavesFirst.isEmpty() && K >0) {
			Point p = leavesFirst.poll();
			//System.out.println(p.x + " " + p.y);
			grid[p.x][p.y] = 'X';
			K--;
		}
		int numX = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				out.print(grid[i][j]);
				if(grid[i][j]=='X') numX++;
			}
			if(i==N-1 && numX!=temp) throw new Exception();
			out.println();
		}
	}
}
