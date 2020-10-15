import java.io.PrintWriter;
import java.util.Scanner;

public class Kstring {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Kstring().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int K = in.nextInt();
		String S = in.next();
		int[] letters = new int[26];
		for(int s=0;s<S.length();s++) {
			letters[S.charAt(s)-'a']++;
		}
		
		if(S.length()%K!=0) {
			out.println(-1);
			return;
		}
		for(int s=0;s<26;s++) {
			if(letters[s]%K!=0) {
				out.println(-1);
				return;
			}
		}
		String ans = "";
		for(int s=0;s<26;s++) {
			//System.out.println(s);
			for(int i =0; i<letters[s]/K; i++) {
				ans+=Character.toString((char)(s+'a'));
			}
		}
		for(int i=0;i<K;i++) {
			System.out.print(ans);
		}
		
	}
}
