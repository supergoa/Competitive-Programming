import java.io.PrintWriter;
import java.util.Scanner;

public class I {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new I().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			int d = in.nextInt();
			
			int total1 = a+c;
			int total2 = b+d;
			if(total1>total2) {
				out.println(1);
				continue;
			} else if(total2>total1) {
				out.println(2);
				continue;
			} else {
				if(b>c) {
					out.println(2);
					continue;
				} else if(c>b) {
					out.println(1);
					continue;
				} else {
					out.println(-1);
					continue;
				}
			}
		}
		
	}
}
