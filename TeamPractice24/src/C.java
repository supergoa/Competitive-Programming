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
		N = in.nextInt();
		D = in.nextInt();
		arr = new int[N+1];
		memo = new int[N+2][N+2][D+2];
		for(int n=0; n<N+2; n++)
			for(int i=0; i<N+2; i++)
				Arrays.fill(memo[n][i], -1);

		arr[0] = 0;
		for(int n=1; n<=N; n++) {
			arr[n] = in.nextInt() + arr[n-1];
		}
		out.println(dp(1,D,1));

	}
	final int oo = (int) 1e9;
	private int dp(int itemNum, int dividersLeft, int lastDivider) {
		if(itemNum==N+1) {
			int num = arr[N]-arr[lastDivider-1];
			if(num%10<=4) num-=num%10;
			else {
				while(num%10!=0) {
					num++;
				}
			}
			return num;
		}
		if(itemNum>N+1 || dividersLeft<0) {
			return oo;
		}
		
		//System.out.println(itemNum + " " +dividersLeft + " "+ lastDivider);
		if(memo[itemNum][lastDivider][dividersLeft]!=-1) return memo[itemNum][lastDivider][dividersLeft];
		
		//not place divider
		int noPlace = dp(itemNum + 1, dividersLeft, lastDivider);
		int toPlace = oo;
		//if(lastDivider>=1 && itemNum>=1) {
			//System.out.println(itemNum + " " + lastDivider);
			toPlace = arr[itemNum]-arr[lastDivider-1];
			if(toPlace%10<=4) toPlace-=toPlace%10;
			else {
				while(toPlace%10!=0) {
					toPlace++;
				}
			}
		//}
		
		int place = toPlace + dp(itemNum + 1, dividersLeft-1, itemNum+1);
		
		
		return memo[itemNum][lastDivider][dividersLeft] = Math.min(noPlace, place);
		//return oo;
	}
	
	/*if(choose%10<=4) choose-=choose%10;
	else {
		while(choose%10!=0) {
			choose++;
		}
	}
	//System.out.println(choose + " " + skip);
	/*if(skip%10<=4) skip-=skip%10;
	else {
		while(skip%10!=0) {
			skip++;
		}
	}*/
}
