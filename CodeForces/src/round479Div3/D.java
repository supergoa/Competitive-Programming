package round479Div3;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;


public class D {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new D().solve(scan, out);
		
		out.close();
	}

	private void solve(Scanner scan, PrintWriter out) throws Exception {
		int N =scan.nextInt();
		HashSet<Long> hs = new HashSet<>();
		for(int n=0; n<N; n++) {
			long x = scan.nextLong();
			hs.add(x);
		}
		long start = -1;
		for(long a:hs) {
			if(a%2==0) {
				if(!hs.contains(a*3) && !hs.contains(a/2)) {
					start = a;
					break;
				}
			} else {
				if(!hs.contains(a*3)) {
					start = a;
					break;
				}
			}
		}
		String output = start +  " ";
		N--;
		while(N-->0) {
			if(start%3==0) {
				if(hs.contains(start/3)) {
					output += start/3 + " ";
					start/=3;
					//N--;
					continue;
				}
			}
			if(hs.contains(start*2)) {
				output += start*2 + " ";
				start*=2;
				//N--;
				continue;
			} else {
				throw new Exception();
			}
		}
		output = output.trim();
		out.println(output);
		
	}
}
