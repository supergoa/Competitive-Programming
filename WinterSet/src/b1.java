import java.util.Arrays;
import java.util.Scanner;

public class b1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new b1().solve(in);
		in.close();
	}

	char[][] stamp = {{'#', '#', '#'},{'#', '.', '#'},{'#', '#', '#'}};
	private void solve(Scanner in) {
		int N = in.nextInt();
		int M = in.nextInt();
		char[][] grid = new char[N][M];
		for(int i=0; i<N; i++) {
			String line = in.next();
			for(int j=0; j<M; j++) {
				grid[i][j] = line.charAt(j);
			}
		}
		char[][] newGrid = new char[N][M];
		for(int i=0; i<N; i++) Arrays.fill(newGrid[i], '.');
		for(int i=0; i<N-2; i++) {
			for(int j=0; j<M-2; j++) {
				boolean good = true;
				for(int a=0; a<3; a++) {
					for(int b=0; b<3; b++) {
						if(grid[i+a][j+b]=='.' && stamp[a][b]=='#') {
							good = false;
						}
					}
				}
				if(good) {
					for(int a=0; a<3; a++) {
						for(int b=0; b<3; b++) {
							if(newGrid[i+a][j+b]!='#')
								newGrid[i+a][j+b]=stamp[a][b];
						}
					}
				}
				
			}
		}
		
		boolean ans = true;
		outer: for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(grid[i][j]!=newGrid[i][j]) {
					ans = false;
					break outer;
				}
			}
		}
		System.out.println(ans?"YES":"NO");
		
	}
}
