import java.io.PrintWriter;
import java.util.Scanner;

public class letters {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new letters().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int counter = 0;
		while(true) {
			String first = in.next();
			String second = in.next();
			if(first.equals("END") && second.equals("END")) break;
			
			int[] occ1 = new int[26];
			int[] occ2 = new int[26];
			
			for(int i=0; i<first.length(); i++) {
				occ1[first.charAt(i)-'a']++;
			}
			for(int i=0; i<second.length(); i++) {
				occ2[second.charAt(i)-'a']++;
			}
			boolean good = true;
			for(int i=0; i<26; i++) {
				if(occ1[i]!=occ2[i]) {
					good = false;
					break;
				}
			}
			if(good) {
				out.println("Case "+(++counter) + ": same");
			} else {
				out.println("Case "+(++counter) + ": different");
			}
		}
		
	}
}
