import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class upwards {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new upwards().solve(in,out);
		in.close();
		out.close();
	}

	int k,l,r;
	char[] ans;
	int answers;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			k = in.nextInt()+1;
			l = in.nextInt();
			r = in.nextInt();
			ans = null;
			answers = 0;
			rec(0-k, l, new char[l]);
			for(int i=0; i<l; i++)
				out.print(ans[i]);
			out.println();
		}
		
	}
	private void rec(int prevLetter, int lLeft, char[] cur) {
		if(answers>=r) return;
		if(lLeft == 0) {
			answers++;
			if(answers==r) {
				ans = cur.clone();
			}
			return;
		}
		for(int i=prevLetter+k; i<26; i++) {
			cur[l-lLeft] = (char) (i+'a');
			rec(i, lLeft-1, cur);
		}
		return;
	}
}
