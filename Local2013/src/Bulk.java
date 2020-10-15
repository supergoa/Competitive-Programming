import java.io.PrintWriter;
import java.util.Scanner;

public class Bulk {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Bulk().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int c = in.nextInt();
			int p = in.nextInt();
			out.println(c + " " + p);
			out.println(p + (p-2)*(c-1));
		}
		
	}
}
