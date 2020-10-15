package BFSDFS;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Grid {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int N = scan.nextShort();
		int M = scan.nextShort();
		int[][] grid = new int[N][M];
		
		for(int n=0; n<N; n++) {
			String line = scan.next();
			for(int m=0; m<M; m++) {
				grid[n][m] = (line.charAt(m)-'0');
			}
		}
		
		System.out.println("hello");
		
		ArrayDeque<Pair> Q = new ArrayDeque<Pair>();
		int[][] distance = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		boolean possible = false;
		
		Pair start = new Pair(0,0);
		Pair end = new Pair(N-1,M-1);
		
		distance[start.x][start.y] = 0;
		Q.add(start);
		
		int x, y;
		int k;
		int currDist;
		while (!Q.isEmpty()) {
			x = Q.peek().x;
			y = Q.pop().y;
			k = grid[x][y];
			currDist = distance[x][y];
			
			if(x == end.x && y == end.y) {
				System.out.println(currDist);
				possible = true;
				break;
			}
			if(!visited[x][y]) {
				visited[x][y] = true;
				
				//this is the version i am confused as to why it does not work
				//talk to atharva. see other file "Grid1.java"
				
				//up
				if(x-k >= 0 && !visited[x-k][y]) {
					Q.add(new Pair(x-k, y));
					distance[x-k][y] = currDist+1;
				}
				//down
				if(x+k < N && !visited[x+k][y]) {
					Q.add(new Pair(x+k, y));
					distance[x+k][y] = currDist+1;
				}
				//up
				if(y-k >= 0 && !visited[x][y-k]) {
					Q.add(new Pair(x, y-k));
					distance[x][y-k] = currDist+1;
				}
				//down
				if(y+k < M && !visited[x][y+k]) {
					Q.add(new Pair(x, y+k));
					distance[x][y+k] = currDist+1;
				}				
			}
		}
		if(!possible) {
			System.out.println(-1);
		}
		
		scan.close();
	}
	static class Pair{
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		int x;
		int y;
	}
}
