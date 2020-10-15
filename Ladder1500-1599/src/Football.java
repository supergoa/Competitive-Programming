import java.io.PrintWriter;
import java.util.Scanner;

public class Football {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Football().solve(in,out);
		in.close();
		out.close();
	}
	private void solve(Scanner in, PrintWriter out) {
		String situation = in.nextLine();
		int count = 1;
		for(int i=1; i<situation.length(); i++) {
			if(situation.charAt(i)==situation.charAt(i-1)) {
				count++;
			} else {
				count = 1;
			}
			if(count==7) {
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");
	}
}
