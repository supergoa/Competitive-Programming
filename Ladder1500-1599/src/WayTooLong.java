import java.io.PrintWriter;
import java.util.Scanner;

public class WayTooLong {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new WayTooLong().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			String str = in.next();
			if(str.length()>10) {
				out.println(str.charAt(0) + "" + (str.length()-2) + "" + str.charAt(str.length()-1));
			} else {
				out.println(str);
			}
		}
	}
}
