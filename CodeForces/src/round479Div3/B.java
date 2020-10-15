package round479Div3;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new B().solve(scan, out);
		
		scan.close();
		out.close();
	}

	private void solve(Scanner scan, PrintWriter out) {
		int n;
		String s = "";
		n = scan.nextInt();
		s = scan.next();
		//System.out.println("abc".substring(0, 1));
		HashMap<String, Integer> hm = new HashMap<>();
		for(int i=0; i<n-1; i++) {
			String x = s.substring(i, i+2);
			hm.put(x, hm.getOrDefault(x,0)+1);
		}
		int max = 0;
		for(String a: hm.keySet()) {
			int x = hm.get(a);
			if(x > max) {
				s=a;
				max=x;
			}
		}
		out.println(s);
	}
}
