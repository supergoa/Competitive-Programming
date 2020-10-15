import java.io.PrintWriter;
import java.util.Scanner;

public class Xenia {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Xenia().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		int[] arr = new int[M];
		for(int m=0;m<M; m++) {
			arr[m] = in.nextInt();
		}
		long time = 0;
		int currHouse = 1;
		for(int m=0;m<M; m++) {
			if(arr[m] < currHouse) {
				time += N-currHouse + arr[m];
			} else {
				time += arr[m]-currHouse;
			}
			currHouse = arr[m];
		}
		out.print(time);
	}
}
