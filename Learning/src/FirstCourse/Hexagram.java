package FirstCourse;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Hexagram {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out) ;
		
		new Hexagram().solve(scan, out);
		
		scan.close();
		out.close();
	}
	final int SIZE = 12;
	final int LINES = 6;
	final int LENGTH = 4;
	int solutionsFound = 0;
	int numbers[] = new int[SIZE];
	int[][] grid = new int[LINES][LENGTH];
	int[][][] adj = new int[6][4][2];
	
	private void solve(Scanner scan, PrintWriter out) {
		// set up identical hexagram indecies
		adj[0][0][0]=2;
		adj[0][0][1]=3;
		adj[0][1][0]=3;
		adj[0][1][1]=2;
		adj[0][2][0]=4;
		adj[0][2][1]=1;
		adj[0][3][0]=1;
		adj[0][3][1]=0;
		adj[1][0][0]=0;
		adj[1][0][1]=3;
		adj[1][1][0]=4;
		adj[1][1][1]=2;
		adj[1][2][0]=5;
		adj[1][2][1]=1;
		adj[1][3][0]=2;
		adj[1][3][1]=0;
		adj[2][0][0]=1;
		adj[2][0][1]=3;
		adj[2][1][0]=5;
		adj[2][1][1]=2;
		adj[2][2][0]=3;
		adj[2][2][1]=1;
		adj[2][3][0]=0;
		adj[2][3][1]=0;
		adj[3][0][0]=5;
		adj[3][0][1]=3;
		adj[3][1][0]=2;
		adj[3][1][1]=2;
		adj[3][2][0]=0;
		adj[3][2][1]=1;
		adj[3][3][0]=4;
		adj[3][3][1]=0;
		adj[4][0][0]=3;
		adj[4][0][1]=3;
		adj[4][1][0]=0;
		adj[4][1][1]=2;
		adj[4][2][0]=1;
		adj[4][2][1]=1;
		adj[4][3][0]=5;
		adj[4][3][1]=0;
		adj[5][0][0]=4;
		adj[5][0][1]=3;
		adj[5][1][0]=1;
		adj[5][1][1]=2;
		adj[5][2][0]=2;
		adj[5][2][1]=1;
		adj[5][3][0]=3;
		adj[5][3][1]=0;
		
		boolean done = false;
		while(true) {
			done = true;
			solutionsFound = 0;
			
			int sum = 0;
			for(int i=0; i<SIZE; i++) {
				int x = scan.nextInt();
				numbers[i] = x;
				sum+=x;
				if(x!=0) {
					done = false;
				}
			}
			if(done)break;
			
			int goal = 2*sum/LINES;
			if(Math.abs(goal-((double)2.0*sum/(double)LINES)) > .000001) {
				out.println(0);
			} else {
				backtrack(0, 0, new int[SIZE], goal, 0);
				out.println(solutionsFound/12);
			}
			out.flush();
		}
		
	}
	private void backtrack(int line, int length, int[] used, int goal, int totalPlaced) {
		if(line==6 || totalPlaced >= 24) {
			solutionsFound++;
			return;
		}
		
		if(grid[line][length]!=0) {
			if((length+1)!=LENGTH) {
				backtrack(line, length+1, used, goal, totalPlaced);
				
			} else {
				backtrack(line+1, 0, used, goal, totalPlaced);
			}
			return;
		}
		// 3 17 15 18 11 22 12 23 21 7 9 13
		for(int currNumInd = 0; currNumInd<SIZE; currNumInd++) {
			
			if(used[currNumInd]<2 && !confict(line, length, numbers[currNumInd], goal)) {
				placeIndecies(line, length, numbers[currNumInd]);
				used[currNumInd] += 2;
				totalPlaced+=2;
				
				if((length+1)!=LENGTH) {
					backtrack(line, length+1, used, goal, totalPlaced);
				} else {
					backtrack(line+1, 0, used, goal, totalPlaced);
				}
				
				placeIndecies(line, length, 0);
				used[currNumInd] -= 2;
				totalPlaced-=2;
			}
		}
		return;
	}
	
	private void placeIndecies(int line, int length, int val) {
		grid[line][length] = val;
		int x = adj[line][length][0];
		int y = adj[line][length][1];
		grid[x][y] = val; //never check conflict -- lucky?
	}
	private boolean confict(int line, int length, int val, int goal) {
		
		int lineSum = 0;
		int filled = 0;
		for(int l = 0; l<LENGTH; l++) {
			lineSum += grid[line][l];
			if(grid[line][l]!=0) {
				filled++;
			}
		}
		
		if(filled==3 && val+lineSum != goal) {
			return true;
		}
		return false;
	}
}
