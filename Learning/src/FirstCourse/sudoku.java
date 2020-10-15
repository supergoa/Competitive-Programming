//package FirstCourse;
///package FirstCourse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
1
0 6 0 1 0 4 0 5 0
0 0 8 3 0 5 6 0 0
2 0 0 0 0 0 0 0 1
8 0 0 4 0 7 0 0 6
0 0 6 0 0 0 3 0 0
7 0 0 9 0 1 0 0 4
5 0 0 0 0 0 0 0 2
0 0 7 2 0 6 9 0 0
0 4 0 5 0 8 0 7 0

 */

public class sudoku {
	
	 static PrintWriter out;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		out = new PrintWriter(System.out);
		
		new sudoku().solve(scan);
		
		scan.close();
		out.close();
	}

	int[][] puzzle;
	boolean[][] colsValues;
	boolean[][] rowsValues;
	
	private void solve(Scanner scan) {
		
		int N = scan.nextInt();
		
		for(int n=1; n<=N; n++) {
			boolean possible = true;
			out.println("Test case " + n + ":");
			out.println();
			puzzle = new int[10][10];
			colsValues = new boolean[10][10];
			rowsValues = new boolean[10][10];
			for(int i=1; i<=9; i++) {
				for(int j=1; j<=9; j++) {
					int x = scan.nextInt();
					if(x!=0 && conflict(i,j,x)) {
						possible = false;
					}
					puzzle[i][j] = x;
					if(x != 0) {
						colsValues[j][x] = true;
						rowsValues[i][x] = true;
					}
				}
			}
			
			if(!possible || !backTrack(1, 1)) {
				out.println("No solution possible.");
				out.println();
			}
			out.flush();
			
		}
	}


	int counter = 0;
	private boolean backTrack(int col, int row) {
//		System.out.println(row + " " + col);
		
		// I've filled my entire puzzle successfully, starting from 0,0 down-right to 8,8
		if(col == 10) {
			printSoln();
			return true;
		}
		
		if(puzzle[row][col] != 0) {
			if(row < 9) return backTrack(col, row + 1);
			else return backTrack(col + 1, 1);
		}
		
		for(int value = 1; value <= 9; ++value) {
				// If this row isn't filled, and placing the current value would not
				// create a row, column, or 3x3 conflict...
				if(!conflict(row, col, value)) {

					// Place the value (1-9) in row i, column location
					puzzle[row][col] = value;
					rowsValues[row][value] = true;
					colsValues[col][value] = true;
					//valuesUsed[rowsPlaced] = true;
					
					// If not all values have been placed for this column, remain on
					// the current column. Otherwise, go to the next column.
					
					if(row == 9) {
						if (backTrack(col + 1, 1)) {
							return true;
						}
					} else {
						if(backTrack(col, row + 1)) {
							return true;
						}
					}
					//reset values
					puzzle[row][col] = 0;
					rowsValues[row][value] = false;
					colsValues[col][value] = false;
			}
		}
		return false;
	}
	
	private void printSoln() {
		for(int i=1; i<=9; i++) {
			String line = "";
			for(int j=1; j<=9; j++) {
				line +=(puzzle[i][j]) + " ";
			}
			out.println(line.trim());
		}
		out.println();
	}
	private boolean conflict(int row, int col, int val) {
		
		
		// Check the whole row for any duplicate values
		
		if(rowsValues[row][val]) {
			return true;
		}
		
		if(colsValues[col][val]) {
			return true;
		}
//		System.out.println(row + " " + col + " " + val);
		
		row--;
		col--;
		
		//
		// Now check the 3x3
		//
		// Find starting row of the current 3x3
		while(row%3!=0) {
			row--;
		}
		
		// Find starting column of the current 3x3
		while(col%3!=0) {
			col--;
		}
		
		row++;
		col++;
		
		// Iterate over the 3x3, checking for value conflicts
		for(int i=row; i<row+3; i++) {
			for(int j=col; j<col+3; j++) {
				if(puzzle[i][j] == val) {
					return true;
				}
			}
		}
//		System.out.println("placed " + val + " at " + row + ", " + col);
		return false;
	}
}