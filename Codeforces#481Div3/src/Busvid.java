import java.io.PrintWriter;
import java.util.Scanner;

public class Busvid {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Busvid().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int W = in.nextInt();
		
		
		int cumsum = 0;
		int maxNum = 0;
		int minNum = 0;
		for(int n=0;n<N; n++) {
			cumsum += in.nextInt();
			maxNum = Math.max(maxNum, cumsum);
			minNum = Math.min(minNum, cumsum);
		}
		//System.out.println(maxNum + " " + minNum);
		
		int ans = 0;
		int range = maxNum - minNum;
		if(range > W) {
			ans = 0;
		} else {
			ans = W-range+1;
		}
		
		out.println(ans);
		
	}
}
