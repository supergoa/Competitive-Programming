import java.io.PrintWriter;
import java.util.Scanner;

public class jacuzzi {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new jacuzzi().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int N = in.nextInt();
			int max = 0;
			int sum = 0;
			for(int n=0; n<N; n++) {
				int x = in.nextInt();
				sum += x;
				max = Math.max(max, x);
			}
			if(sum-max > max) {
				System.out.println("Jacuzzi #"+(t+1)+": YES");
			} else {
				System.out.println("Jacuzzi #"+(t+1)+": NO");
			}
		}
		 
	}

}
