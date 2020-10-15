import java.util.Scanner;

public class DontBreakTheIce {
	static int global = 0;
	static int N;
	static boolean[][] game;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();
		for(int t=0; t<T; t++) {
			N = s.nextInt();
			int M = s.nextInt();
			
			// false symbolized pieces left --> true = fallen
			game = new boolean[N][N];
			int count = 0;
			for(int m=0; m<M; m++) {
				int row = s.nextInt();
				int col = s.nextInt();
				row-=1;
				col-=1;
				if(game[row][col] == true) {
					count++;
				}
				else {
					global++;
					knockPieces(row,col);
				}
			}
		System.out.println("Strategy #"+(t+1)+": "+count);
		
		}
		System.out.println("Global" + global);
		s.close();
	}

	static void knockPieces(int row, int col) {
		//System.out.println(row+", "+col);
		game[row][col] = true;
		//rows
		for(int c=0; c<N; c++) {
			global++;
			if(game[row][c]==false && c!= col && inPlace(row,c) == false) {
				global++;
				knockPieces(row,c);
			}
		}
		//cols
		for(int r=0; r<N; r++) {
			global++;
			if(game[r][col]==false && r!=row && inPlace(r,col) == false) {
				global++;
				knockPieces(r,col);
			}
		}
	}

	static boolean inPlace(int row, int col) {
		boolean rowIntact = true;
		boolean colIntact = true;
		//rows
		for(int c=0; c<N; c++) {
			if(game[row][c] == true) {
				rowIntact = false;
				break;
			}
		}
		//cols
		for(int r=0; r<N; r++) {
			if(game[r][col] == true) {
				colIntact = false;
				break;
			}
		}
		return (rowIntact || colIntact);
	}
}
