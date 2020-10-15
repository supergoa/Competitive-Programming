import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class PocketBook {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new PocketBook().solve(in,out);
		in.close();
		out.close();
		
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		int[][] arr = new int[N][M];
		for(int n=0;n<N;n++) {
			String line = in.next();
			for(int m=0;m<M;m++) {
				arr[n][m] = line.charAt(m);
			}
		}
		HashSet<Integer> count = new HashSet<>();
		long ans = 1;
		final int MOD = (int) (1e9+7);
		for(int m=0;m<M;m++) {
			for(int n=0;n<N;n++) {
				count.add(arr[n][m]);
			}
			ans = (ans*count.size()) % MOD;
			count.clear();
		}
		out.print(ans);
	}
}
