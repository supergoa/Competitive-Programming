import java.io.PrintWriter;

import java.util.Scanner;

public class SingleWildcard {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new SingleWildcard().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		String s = in.next();
		String t = in.next();
		//if(s.substring(N-1,N).equals("*")) {
		//	s = s.substring(0,N);
		//	N--;
		//}
		if(s.equals(t)) {
			out.println("YES");
			return;
		}
		if(N>M+1) {
			out.println("NO");
			return;
		}
		
		if(N>M) {
			if(s.contains("*")) {
				s = s.substring(0, s.indexOf("*")) + s.substring(s.indexOf("*")+1, N);
				if(s.equals(t)) {
					out.println("YES");
				} else {
					out.println("NO");
				}
			} else {
				out.println("NO");
			}
			return;
		} else {
		
			boolean good = true;
			if(s.contains("*")) {
				String s1 = s.substring(0, s.indexOf("*"));
				String t1 = t.substring(0, s.indexOf("*"));
				if(s1.equals(t1)) {
					String theRest = s.substring(s.indexOf("*")+1, N);
					if(t.substring(M-theRest.length(),M).equals(theRest));
					else good = false;
					
				} else {
					good = false;
				}
			}
			else {
				good = false;
			}
			if(good) {
				out.println("YES");
			} else {
				out.println("NO");
			}
		}
	}
}
