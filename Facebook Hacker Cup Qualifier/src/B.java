import java.io.PrintWriter;
import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in, out);
		in.close();
		out.close();
	}

	int[] arr;
	short[] memo;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();

		for(int t=0; t<T; t++) {
			int N = in.nextInt();
			arr = new int[N];
			String line = in.next();
			int a=0,b=0;
			for(int i = 0; i<N; i++) {
				arr[i] = line.charAt(i) == 'A' ? a++ : b++;
			}
			if(Math.abs(a-b)>2) {
				out.println("Case #"+(t+1)+": "+ "N");
			} else {
				out.println("Case #"+(t+1)+": "+ "Y");
			}
			
		/*int best = 2;
		for(int i=2; i<N; i+=3) {
			best = Math.min(best, dp(i, true));
			best = Math.min(best, dp(i, false));
		}*/
		}
		
		
	}
	/*private int dp(int i, boolean left) {
		if()
		return 0;
	}*/
}
