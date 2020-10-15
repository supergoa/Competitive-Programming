import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in,out);
		in.close();
		out.close();
	}

	int N;
	int D;
	int[] arr;
	int[][][] memo;
	private void solve(Scanner in, PrintWriter out) {
		//System.out.println(Math.(109));
		N = in.nextInt();
		D = in.nextInt();
		arr = new int[N];
		memo = new int[N+2][D+2][2];
		for(int n=0; n<N+2; n++) 
			for(int d=0; d<D+2; d++)
				Arrays.fill(memo[n][d], -1);
		for(int n=0; n<N; n++) {
			arr[n] = in.nextInt();
		}
		out.println(dp(0,0, true));
	}
	final int oo = (int) 1e9;
	private int dp(int itemNum, int pos, boolean round) {
		
		if(pos==D-1 && itemNum==N) {
			return 0;
		}
		if(itemNum>=N || pos>=D) {
			return oo;
		}
		if(memo[itemNum][pos][round?1:0]!=-1) return memo[itemNum][pos][round?1:0];
		int choose = arr[itemNum] + dp(itemNum+1, pos, false);
		if(round) {
			if(choose%10<=4) choose-=choose%10;
		
			else {
				while(choose%10!=0) {
					choose++;
				}
			}
		}
		int skip = dp(itemNum, pos+1, true);
		if(round) {
			if(skip%10<=4) skip-=skip%10;
		
			else {
				while(skip%10!=0) {
					skip++;
				}
			}
		}
		
		return memo[itemNum][pos][round?1:0] = Math.min(choose, skip);
	}
}
