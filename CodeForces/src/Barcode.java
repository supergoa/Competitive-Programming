import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Barcode {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Barcode().solve(in,out);
		in.close();
		out.close();
	}

	int N,M,X,Y;
	boolean[][] barcode;
	int[] numTrue;
	int[] numFalse;
	final int oo = (int) 1e9;
	int[][][] memo;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		M = in.nextInt();
		X = in.nextInt();
		Y = in.nextInt();
		
		numFalse = new int[M];
		numTrue = new int[M];
		memo = new int[M+1][2][M+1];
		for(int i=0; i<M+1; i++) {
			Arrays.fill(memo[i][0],-1);
			Arrays.fill(memo[i][1],-1);
		}
		for(int n=0; n<N; n++) {
			String line = in.next();
			for(int m=0; m<M; m++) {
				if(line.charAt(m)=='#') {
					numTrue[m]++;
				} else {
					numFalse[m]++;
				}
			}
		}
		//System.out.println(Arrays.toString(numTrue));
		//System.out.println(Arrays.toString(numFalse));
		out.print(Math.min(dp(0,true,0), dp(0,false,0)));
	}
	private int dp(int ind, boolean currColor, int currSize) {
		if(currSize>Y) return oo;
		if(ind==M) { 
			if(currSize>=X && currSize<=Y) {
				return 0;
			} else {
				return oo;
			}
		}
		if(memo[ind][currColor?0:1][currSize] != -1) return memo[ind][currColor?0:1][currSize];
		int a=0,b=0,c=0,d=0;
		int currAddColor = (currColor) ? numTrue[ind] : numFalse[ind];
		int otherAddColor = (currColor) ? numFalse[ind] : numTrue[ind];
		if(currSize<X) {
			a= otherAddColor + dp(ind+1,currColor, currSize+1);
			b=oo;
			c=oo;
			d=oo;
		}
		if(currSize==Y) {
			d= currAddColor + dp(ind+1, !currColor, 1);
			a=oo;
			b=oo;
			c=oo;
		}
		if(currSize>=X && currSize<Y) {
			b= otherAddColor + dp(ind+1, currColor, currSize+1);
			c= currAddColor + dp(ind+1, !currColor, 1);
			a=oo;
			d=oo;
		}
		 
		//System.out.println("Index: " + ind + "Color: " + currColor + " Size:" + currSize + " Ans:" + x);
		return memo[ind][currColor?0:1][currSize] = Math.min(Math.min(a, b), Math.min(c, d));
	}
}
