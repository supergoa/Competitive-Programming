import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class sortme {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new sortme().solve(in,out);
		in.close();
		out.close();
	}

	class myString implements Comparable<myString>{
		String s;
		public myString(String a) {
			s = a;
		}
		@Override
		public int compareTo(myString o) {
			//boolean diff = false;
			//int ind = -1;
			for(int i=0; i<Math.min(s.length(), o.s.length()); i++) {
				if(s.charAt(i)!=o.s.charAt(i)) {
					return Integer.compare(id.get(s.charAt(i)), id.get(o.s.charAt(i)));
				}
			}
			if(o.s.length() == s.length()) return 0;
			if(s.length() < o.s.length()) return -1;
			else return 1;
		}
	}
	HashMap<Character, Integer> id;
	private void solve(Scanner in, PrintWriter out) {
		int counter = 1;
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			
			String order = in.next();
			id = new HashMap<>();
			for(int i=0; i<order.length(); i++) {
				id.put(order.charAt(i), id.size());
			}
			myString[] strs = new myString[N];
			for(int n=0; n<N; n++) {
				strs[n] =  new myString(in.next());
			}
			Arrays.sort(strs);
			System.out.println("year "+(counter++));
			for(myString s : strs) {
				System.out.println(s.s);
			}
		}
		
	}
}
