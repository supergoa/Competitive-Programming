import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new D().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int[] arr = new int[N];
		for(int n=0; n<N; n++) {
			arr[n] = in.nextInt();
		}
		Arrays.sort(arr);
		int max = -1;
		int cry = 0;
		for(int i:arr) {
			//System.out.println(i + " " + max);
			if(i<=max) cry++;
			max = Math.max(i, max);
		}
		out.print(cry);
	}
}
