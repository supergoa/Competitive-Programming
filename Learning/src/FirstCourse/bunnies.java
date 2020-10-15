//package FirstCourse;

import java.io.PrintWriter;
import java.util.Scanner;

public class bunnies {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new bunnies().solve(scan,out);
		
		scan.close();
		out.close();
	}

	char[][] grid;
	boolean foundC = false;
	int row;
	int col; 
	private void solve(Scanner scan, PrintWriter out) {
		int T = scan.nextInt();
		
		for(int t=0; t<T; t++) {
			row = scan.nextInt();
			col = scan.nextInt();
			
			grid = new char[row][];
			int startx = -1;
			int starty = -1;
			for(int i=0; i<row; i++) {
				grid[i] = scan.next().toCharArray();
				for(int j=0; j<col; j++) {
					if(grid[i][j]=='P') {
						startx = i;
						starty = j;
					}
				}
			}
			if(startx==-1 || starty==-1) {
				out.println("ERROR");
			}
			foundC = false;
			floodfill(startx, starty);
			if(foundC) {
				out.println("yes");
			} else {
				out.println("no");
			}
		}
	}
	void floodfill(int x, int y) {
		//System.out.println(x + " " + y);
		if(grid[x][y] == 'C') {
			foundC = true;
		}
		
		grid[x][y] = 'x';
		
		for(int i=-1; i<2; i++) {
			for(int j=-1; j<2; j++) {
				if((Math.abs(i)+Math.abs(j)<2) && inBounds(x+i, y+j) && grid[x+i][y+j]!='#' && grid[x+i][y+j]!='x') {
					
					floodfill(x+i, y+j);
				}
			}
		}
	}
	private boolean inBounds(int x, int y) {
		if(x<row && y<col && x>=0 && y>=0) {
			return true;
		}
		return false;
	}
}
