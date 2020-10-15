//package FirstCourse;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class sqfree {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new sqfree().solve(scan, out);
		scan.close();
		out.close();
		
	}

	public void solve(Scanner scan, PrintWriter out) {
		int N = scan.nextInt();
		
		
		//precomp
		// <square, cumSum of # of squares>
		TreeMap<Integer, Integer> tm = new TreeMap<>();
		for(int i=1; i<Math.sqrt(1000000)+1; i++) {
			tm.put(i*i, i);
		}
		
		for(int n=0; n<N; n++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			if(a==b) {
				if(tm.containsKey(a)) {
					out.println(0);
				} else {
					out.println(1);
				}
			} else {
				int top = tm.get(tm.floorKey(b));
				int bot = tm.get(tm.ceilingKey(a));
				//System.out.println(top + " " + bot);
				out.println((b-a)-(top-bot));
			}
		}
	}
}
