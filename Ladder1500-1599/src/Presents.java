import java.io.PrintWriter;
import java.util.Scanner;

public class Presents {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Presents().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int[] arr = new int[N];
		for(int n=0; n<N; n++) {
			arr[n] = in.nextInt()-1;
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[j]==i) {
					out.print((j+1) + " ");
				}
			}
		}
	}
}
