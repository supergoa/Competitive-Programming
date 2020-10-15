import java.io.PrintWriter;
import java.util.Scanner;

public class AmusJoke {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new AmusJoke().solve(in,out);
		in.close();
		out.close();
	}
	
	private void solve(Scanner in, PrintWriter out) {
		String guest = in.next();
		String host = in.next();
		String letters = in.next();
		char[] arr1 = new char[26];
		char[] arr2 = new char[26];
		for(int a=0; a<guest.length(); a++) {
			char x =guest.charAt(a);
			arr1[x-'A']++;
		}
		for(int a=0; a<host.length(); a++) {
			char x =host.charAt(a);
			arr1[x-'A']++;
		}
		for(int a=0; a<letters.length(); a++) {
			char x =letters.charAt(a);
			arr2[x-'A']++;
		}
		
		for(int a=0; a<26; a++) {
			if(arr1[a]!=arr2[a]) {
				out.println("NO");
				return;
			}
		}
		out.println("YES");
	}
}
