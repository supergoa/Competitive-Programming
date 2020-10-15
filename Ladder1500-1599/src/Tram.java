import java.io.PrintWriter;
import java.util.Scanner;

public class Tram {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Tram().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		String num1 = in.next();
		String num2 = in.next();
		String ans = "";
		for(int a=0; a<num1.length(); a++) {
			if(num1.charAt(a)!=num2.charAt(a)) {
				ans+="1";
			} else {
				ans+="0";
			}
		}
		out.print(ans);
	}
}
