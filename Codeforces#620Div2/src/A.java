import java.io.PrintWriter;
import java.util.Scanner;

//7:45
public class A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new A().solve(in, out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		while(T-->0) {
			int x = in.nextInt();
			int y = in.nextInt();
			int a = in.nextInt();
			int b = in.nextInt();
			if((y-x)%(a+b)==0) {
				out.println(((y-x)/(a+b)));
			} else {
				out.println(-1);
			}
		}
		
	}
}
