import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class C2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C2().solve(in, out);
		in.close();
		out.close();
	}

	
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		while(T-->0) {
			String l = (in.nextLong()-1)+"";
			String h = in.nextLong()+"";
			long ans = helper(h) - helper(l);
			out.println(ans);
		}
		
	}

	String helpStr = "9999999999999999999999999999";
	private long helper(String h) {
		int fc = h.charAt(0)-'0';
		int lc = h.charAt(h.length()-1)-'0';
		if(h.length()==1) return fc;
		if(h.length()==2) return 9 + Math.min(fc, lc);
		
		long ans = 18;
		long mult = 10;
		for(int i=3; i<h.length(); i++) {
			ans += 9*mult;
			mult *=10;
		}
		System.out.println(ans);
		
		int between = h.length()-2;
		int min = Math.min(fc, lc);
		if(between > 0) ans += min * between * 10;
		else if(between == 0) ans += min;
		System.out.println("in " + ans);
		//System.out.println();
		return ans;
	}
	
}
