import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Catcher {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Catcher().solve(in,out);
		in.close();
		out.close();	
	}

	Integer[] heights;
	private void solve(Scanner in, PrintWriter out) {
		int ind = 0;
		while(true) {
			ind++;
			ArrayList<Integer> height = new ArrayList<>();
			height.add(in.nextInt());
			if(height.get(0)==-1) break;
			while(true) {
				int x = in.nextInt();
				if(x==-1) break;
				height.add(x);
			}
			heights = height.toArray(new Integer[height.size()-1]);
			memo = new int[heights.length][40000]; 
			for(int i=0; i<heights.length; i++) Arrays.fill(memo[i], -1);
			int ans = dp(0, heights[0]);
			out.println("Test #"+ind+":");
			out.println("maximum possible interceptions: " +ans);
			out.println();
		}
		
	}
	final int oo = 987654321;
	int[][] memo;
	private int dp(int i, int curHeight) {
		if(i==heights.length) {
			return 0;
		}
		if(memo[i][curHeight]!=-1) return memo[i][curHeight];
		int choose = -oo;
		if(heights[i]<=curHeight) {
			choose = 1+ dp(i+1, heights[i]);
		}
		int leave = dp(i+1, curHeight);
		return memo[i][curHeight]=Math.max(choose, leave);
	}
}
