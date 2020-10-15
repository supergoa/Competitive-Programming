package BFSDFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Grid1 {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();
		int[][] grid = new int[N][M];
		
		for(int n=0; n<N; n++) {
			String line = scan.next();
			for(int m=0; m<M; m++) {
				grid[n][m] = (line.charAt(m)-'0');
			}
		}
		
		//System.out.println("hello");
		
		ArrayDeque<Pair> Q = new ArrayDeque<Pair>();
		int[][] distance = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		
		Pair start = new Pair(0,0);
		Pair end = new Pair(N-1,M-1);
		
		distance[start.x][start.y] = 0;
		Q.add(start);
		visited[start.x][start.y] = true;
		
		int x, y;
		int k;
		int currDist;
		while (!Q.isEmpty()) {
			x = Q.peek().x;
			y = Q.pop().y;
			k = grid[x][y];
			currDist = distance[x][y];
		
			// NICK READ
			// Call Atharva and ask about how I was setting visited in the wrong place
			// made copy with old code
			
			
			
			//up
			if(x-k >= 0 && !visited[x-k][y]) {
				Q.add(new Pair(x-k, y));
				distance[x-k][y] = currDist+1;
				visited[x-k][y] = true;
			}
			//down
			if(x+k < N && !visited[x+k][y]) {
				Q.add(new Pair(x+k, y));
				distance[x+k][y] = currDist+1;
				visited[x+k][y] = true;
			}
			//up
			if(y-k >= 0 && !visited[x][y-k]) {
				Q.add(new Pair(x, y-k));
				distance[x][y-k] = currDist+1;
				visited[x][y-k] = true;
			}
			//down
			if(y+k < M && !visited[x][y+k]) {
				Q.add(new Pair(x, y+k));
				distance[x][y+k] = currDist+1;
				visited[x][y+k] = true;
			} else {
				//visited = new boolean[N][M];
			}
				
			
		}
		if(!visited[end.x][end.y]) {
			System.out.println(-1);
		} else {
			System.out.println(distance[end.x][end.y]);
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
