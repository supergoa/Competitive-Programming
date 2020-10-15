//package FirstCourse;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Mines {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new Mines().solve(scan, out);
		
		scan.close();
		out.close();
	}

	int[][][] grid;
	int size;
	int volumeCleared;
	private void solve(Scanner scan, PrintWriter out) {
		size = scan.nextInt();
		int[][][] arr = new int[size][size][size];
		grid = new int[size][size][size];
		for(int i=0; i<size; i++) {
			
			for(int j=0; j<size; j++) {
			
				for(int k=0; k<size; k++) {
					arr[i][j][k] = scan.nextInt();
				}
			}
		}
		//System.out.println(arr[11][15][18]);
		//System.out.println(arr[12][15][18]);
		//System.out.println(arr[10][15][18]);
		//System.out.println(arr[11][16][18]);
		//System.out.println(arr[11][14][19]);
		//System.out.println(arr[11][15][17]);
		
		int T = scan.nextInt();

		// 11 15 18
		
		for(int t=0; t<T; t++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			int z = scan.nextInt();
			
			volumeCleared = 0;
			if(arr[x][y][z]==0) {
				out.println("Simulation #"+(t+1)+", volume cleared is "+volumeCleared+" cubic feet.");
			} else {
				grid = arr.clone();
				floodfill(x,y,z);
				out.println("Simulation #"+(t+1)+", volume cleared is "+volumeCleared+" cubic feet.");
			}
			out.println();
		}
	}
	
	private void floodfill(int x, int y, int z) {

		volumeCleared += 1;
		grid[x][y][z] = -1;
		
		for(int i=-1; i<2; i++) {
			for(int j=-1; j<2; j++) {
				for(int k=-1; k<2; k++) {
					if(manhattenDistance(i,j,k)==1 && inBounds(x+i,y+j,z+k) && grid[x+i][y+j][z+k]==1) {
						floodfill(x+i,y+j,z+k);
					}
				}
			}
		}
	}
	private boolean inBounds(int x, int y, int z) {
		if(x<size && x>=0 && y<size && y>=0 && z<size && z>=0) {
			return true;
		}
		return false;
	}

	private int manhattenDistance(int x, int y, int z) {
		return Math.abs(x) + Math.abs(y) + Math.abs(z);
	}
}
