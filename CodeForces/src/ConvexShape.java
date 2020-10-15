import java.io.PrintWriter;
import java.util.Scanner;

public class ConvexShape {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new ConvexShape().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int R = in.nextInt();
		int C = in.nextInt();
		for(int i=0; i<50; i++) {
		//	System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		}
		boolean[][] grid = new boolean[R][C];
		for(int i=0; i<R; i++) {
			String line = in.next();
			for(int j=0; j<C; j++) {
				grid[i][j] = line.charAt(j)=='B';
			}
		}
		boolean good = true;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				for(int i2=0; i2<R; i2++) {
					for(int j2=0; j2<C; j2++) {
						if(i==i2 && j==j2) continue;
						if(grid[i][j] && grid[i2][j2]) {
							
							//sideways then vertically
							int curI = i;
							int curJ = j;
							int endI = i2;
							int endJ = j2;
		
							boolean possible = true;
							while(curJ!=endJ && possible) {
								curJ += (endJ-curJ>0)?1:-1;
								if(!grid[curI][curJ]) possible = false;
							}
							//then try to go vert
							if(possible) {
								while(curI!=endI && possible) {
									curI += (endI-curI>0)?1:-1;
									if(!grid[curI][curJ]) possible = false;
								}
							}
							if(possible) continue;
							
							//vertically then sideways
							curI = i;
							curJ = j;
							endI = i2;
							endJ = j2;
							
							possible = true;
							while(curI!=endI && possible) {
								curI += (endI-curI>0)?1:-1;
								if(!grid[curI][curJ]) possible = false;
							}
							//then try to go sideways
							if(possible) {
								while(curJ!=endJ && possible) {
									curJ += (endJ-curJ>0)?1:-1;
									if(!grid[curI][curJ]) possible = false;
								}
							}
							if(!possible) {
								good = false;
								break;
							}
						}
					}
				}
			}
		}
		if(good) {
			out.println("YES");
		} else {
			out.println("NO");
		}
	}
}
