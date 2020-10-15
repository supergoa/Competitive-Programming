import java.util.Arrays;
import java.util.Scanner;

public class Twins {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] coins = new int[N];
		int total = 0;
		for(int n=0;n<N; n++) {
			coins[n] = in.nextInt();
			total+=coins[n];
		}
		Arrays.sort(coins);
		int coinsTaken = 0;
		for(int n=N-1;n>=0; n--) {
			coinsTaken += coins[n];
			total -= coins[n];
			if(coinsTaken > total) {
				System.out.println(N-n);
				in.close();
				return;
			}
		}
		in.close();
	}
}
