import java.io.PrintWriter;
import java.util.Scanner;

public class LittleElephantandBits {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new LittleElephantandBits().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		String line = in.next();
		int place = 0;
		for(int i=0; i<line.length(); i++) {
			if(line.charAt(i)=='0') {
				place = i;
				break;
			}
		}
		for(int i=0; i<line.length(); i++) {
			if(i!=place) {
				out.print(line.charAt(i));
			}
		}
	}
}
