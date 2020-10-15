import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Checklist {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("checklist.in"));
		PrintWriter out = new PrintWriter(new File("checklist.out"));
		new Checklist().solve(in,out);
		in.close();
		out.close();
	}

	int H,G;
	Pair[] gcows;
	Pair[] hcows;
	int[][] dists;
	long memo[][][];
	private void solve(Scanner in, PrintWriter out) {
		H = in.nextInt();
		G = in.nextInt();
		hcows = new Pair[H];
		gcows = new Pair[G];
		memo = new long[2][H+1][G+1];
		for(int k=0; k<2; k++) {
			for(int i=0; i<H+1; i++) {
				Arrays.fill(memo[k][i], -1);
			}
		}
	
		for(int i=0; i<H; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			hcows[i] = new Pair(x,y,i);
		}
		for(int i=0; i<G; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			gcows[i] = new Pair(x,y,i);
		}
		
		//for(int i=0; i<H; i++) {
		//	for(int j=0; j<G; j++) {
		//		dists[i][j] = distance(hcows[i],gcows[j]);
		//	}
		//	System.out.println(Arrays.toString(dists[i]));
		//}
		out.print(dp(true, 1, 0));
	}

	final int oo = (int)1e9;
	private long dp(boolean curType, int nextH, int nextG) {
		if(nextH == H && nextG == G) return 0;
		if(nextH == H && nextG < G) return oo;
		
		if(nextH < H && nextG == G) {
			if(memo[(curType)?1:0][nextH][nextG] != -1) return memo[(curType)?1:0][nextH][nextG];
			
			long distanceToH = 0;
			if(curType) {
				distanceToH = distance(hcows[nextH-1], hcows[nextH]);
			} else {
				distanceToH = distance(gcows[nextG-1], hcows[nextH]);
			}
			
			return memo[(curType)?1:0][nextH][nextG] = distanceToH + dp(true, nextH+1, nextG);
		}
		
		if(memo[(curType)?1:0][nextH][nextG] != -1) return memo[(curType)?1:0][nextH][nextG];
		long distanceToH = 0;
		long distanceToG = 0;

		if(curType) {
			distanceToH = distance(hcows[nextH-1], hcows[nextH]);
			distanceToG = distance(hcows[nextH-1], gcows[nextG]);
		} else {
			distanceToH = distance(gcows[nextG-1], hcows[nextH]);
			distanceToG = distance(gcows[nextG-1], gcows[nextG]);
		}
		
		
		long chooseH = distanceToH + dp(true, nextH+1, nextG);
		long chooseG = distanceToG + dp(false, nextH, nextG+1);
		
		return memo[(curType)?1:0][nextH][nextG] = Math.min(chooseG, chooseH);
	}

	class Pair {
		public int x,y,id;
		public Pair(int x, int y, int id) {
			this.x=x;
			this.y=y;
			this.id=id;
		}
		
	}
	
	public int distance(Pair p1, Pair p2) {
		return (int) (Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
	}
}
