import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class DivisibilityByEight {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new DivisibilityByEight().solve(in,out);
		in.close();out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		String n = in.next();
		HashSet<Integer> hs = new HashSet<>();
		for(int i=0; i<124; i++) {
			hs.add(8*i);
		}
		for(int i:hs) {
			String num = i+"";
			int ind = 0;
			int max = num.length();
			boolean ans = false;
			for(int j=0; j<n.length(); j++) {
				if(n.charAt(j)==num.charAt(ind)) {
					ind++;
				}
				if(ind==max) {
					ans=true;
					break;
				}
			}
			if(ans) {
				out.println("YES");
				out.print(num);
				return;
			}
		}
		out.println("NO");
	}
	
}
