import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class NewDeal {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new NewDeal().solve(in,out);
		in.close();
		out.close();
	}

	int[] arr;
	int target = 0;
	int K;
	boolean doable = false;
	boolean[][] memo;
	final int shift = 1000001;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			K = in.nextInt();
			arr = new int[K];
			for(int k=0; k<K; k++) {
				arr[k] = in.nextInt();
			}
			target = in.nextInt();
			doable = false;
			memo = new boolean[K][2000002];
			dp(0, 0);
			if(doable) out.println("Test case #"+(t+1)+": You can hit the target "+target+" and win $10M!");
			else out.println("Test case #"+(t+1)+": You can not hit the target "+target+", sorry!");
		}
	}
	
	private void dp(int i, int sum) {
		if(i==K) {
			if(sum==target) {
				doable = true;
			}
			return;
		}
		
		if(memo[i][sum+shift] == true) return;
		memo[i][sum+shift] = true;
		dp(i+1, sum + arr[i]);
		dp(i+1, sum);
		
	}
}
