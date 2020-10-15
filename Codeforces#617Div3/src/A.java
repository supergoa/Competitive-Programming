import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new A().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		while(T-->0) {
			int N = in.nextInt();
			int[] arr = new int[N];
			int odds = 0, evens = 0;
			for(int i=0; i<N; i++) {
				arr[i] = in.nextInt();
				if(arr[i] % 2 == 0) {
					evens++;
				} else {
					odds++;
				}
			}
			if(odds == 0) {
				out.println("NO");
			} else if(odds % 2 == 1) {
				out.println("YES");
			} else if(odds >= 1 && evens >= 1) {
				out.println("YES");
			} else {
				out.println("NO");
			}
		}
		
	}
}
