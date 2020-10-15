import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in, out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		String str = in.next();
		if(str.length()==1) {
			out.println(0);
			//System.out.println("hello");
			return;
		}
		HashMap<Character, Integer> chars = new HashMap<>();
		for(int i=0; i<str.length(); i++) {
			chars.put(str.charAt(i), chars.getOrDefault(str.charAt(i),0)+1);
		}
		int total = 0;
		for(Character c:chars.keySet()) {
			//System.out.println(c + " " + chars.get(c));
			if(chars.get(c)%2==1) {
				total++;
			}
		}
		total = Math.max(0, total-1);
		System.out.println(total);
	}
}
