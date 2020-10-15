import java.io.PrintWriter;
import java.util.Scanner;

public class FileName {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new FileName().solve(in,out);
		in.close();
		out.close();
	}
	
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int numX = 0;
		int ans = 0;
		String line = in.next();
		for(int n=0; n<N;n++) {
			char a = line.charAt(n);
			if(a=='x') {
				numX++;
			} else {
				numX=0;
			}
			if(numX>=3) {
				ans++;
			}
		}
		out.println(ans);
	}
}
