import java.io.PrintWriter;
import java.util.Scanner;

public class PetyaAndStr {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new PetyaAndStr().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		String one = in.next().toLowerCase();
		String two = in.next().toLowerCase();
		boolean found = false;
		for(int n=0; n<one.length(); n++) {
			if(one.charAt(n)!=two.charAt(n)) {
				if(one.charAt(n) > two.charAt(n)) {
					System.out.println(1);
				} else {
					System.out.println(-1);
				}
				found = true;
				break;
			}
		}
		if(!found) {
			System.out.println(0);
		}
	}
}
