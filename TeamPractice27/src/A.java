import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new A().solve(in,out);
		in.close();
		out.close();
	}

	int board[][];
	int N;
	TreeSet<Integer>[] colRooks;
	TreeSet<Integer>[] rowRooks;
	TreeSet<Integer>[] colPawns;
	TreeSet<Integer>[] rowPawns;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		
		board = new int[N][N];
		colRooks = new TreeSet[N];
		rowRooks = new TreeSet[N];
		colPawns = new TreeSet[N];
		rowPawns = new TreeSet[N];
		
		for(int i=0; i<N; i++) {
			String line = in.next();
			for(int j=0; j<N; j++) {
				if(colRooks[j]==null) colRooks[j] = new TreeSet<>();
				if(rowRooks[i]==null) rowRooks[i] = new TreeSet<>();
				if(colPawns[j]==null) colPawns[j] = new TreeSet<>();
				if(rowPawns[i]==null) rowPawns[i] = new TreeSet<>();
				
				board[i][j] = (line.charAt(j)=='.')?0:2; //0 empty, 1 rook, 2 pawn
				
				if(board[i][j]==2) {
					colPawns[j].add(i);
					rowPawns[i].add(j);
				}
			}
		}
		backtrack(0, 0, 0);
		System.out.println(max);
	}
	int max = 0;
	private void backtrack(int row, int col, int numRooks) {
		if(col==N) {
			max = Math.max(numRooks, max);
			return;
		}
		for(int r=row; r<N; r++) {
			if(r==N-1 && (board[r][col] != 0 || conflict(r, col))) {
				backtrack(0, col+1, numRooks);
			}
			if(board[r][col] == 0 && !conflict(r, col)) {
				int prev = board[r][col];
				board[r][col] = 1;
				colPawns[col].add(r);
				rowPawns[r].add(col);
				
				if(r==N-1) backtrack(0, col+1, numRooks+1);
				else backtrack(row+1, col, numRooks+1);
				
				board[r][col] = prev;
				colPawns[col].remove(r);
				rowPawns[r].remove(col);
			}
		}
		//backtrack(0, col+1, numRooks);
	}
	private boolean conflict(int r, int col) {		
		//higher col
		int higherRook = -1;
		if(colRooks[col].higher(r)!=null) {
			higherRook = colRooks[col].higher(r);
		}
		int higherPawn = -1;
		if(colPawns[col].higher(r)!=null) {
			higherPawn = colPawns[col].higher(r);
		}
		if(higherRook == -1 || (higherPawn !=-1 && higherPawn < higherRook)) {
		} else return true;
		
		//lower col
		int lowerRook = -1;
		if(colRooks[col].lower(r)!=null) {
			lowerRook = colRooks[col].lower(r);
		}
		int lowerPawn = -1;
		if(colPawns[col].lower(r)!=null) {
			lowerPawn = colPawns[col].lower(r);
		}
		if(lowerRook == -1 || (lowerPawn !=-1 && lowerPawn > lowerRook)) {
		} else return true;
		
		//higher row
		higherRook = -1;
		if(rowRooks[r].higher(col)!=null) {
			higherRook = rowRooks[r].higher(col);
		}
		higherPawn = -1;
		if(rowPawns[r].higher(col)!=null) {
			higherPawn = rowPawns[r].higher(col);
		}
		if(higherRook == -1 || (higherPawn !=-1 && higherPawn < higherRook)) {
		} else return true;
		
		//lower row
		lowerRook = -1;
		if(rowRooks[r].lower(col)!=null) {
			lowerRook = rowRooks[r].lower(col);
		}
		lowerPawn = -1;
		if(rowPawns[r].lower(col)!=null) {
			lowerPawn = rowPawns[r].lower(col);
		}
		if(lowerRook == -1 || (lowerPawn !=-1 && lowerPawn > lowerRook)) {
		} else return true;
		
		return false;
	}
}
