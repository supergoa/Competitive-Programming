import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class EscapeFromStones {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new EscapeFromStones().solve(in,out);
		in.close();
		out.close();
	}

	class Pair implements Comparable<Pair>{
		int id;
		int place;
		public Pair(int id, int place) {
			this.id=id;
			this.place=place;
		}
		@Override
		public int compareTo(Pair o) {
			return Integer.compare(this.place, o.place);
		}
		
	}
	private void solve(Scanner in, PrintWriter out) {
		String line = in.next();
		int front = 1;
		int back = line.length();
		Pair[] ans = new Pair[line.length()];
		for(int i=0; i<line.length(); i++) {
			char dir = line.charAt(i);
			if(dir=='l') {
				ans[i] = new Pair(i, back);
				back--;
			} else {
				ans[i] = new Pair(i, front);
				front++;
			}
		}
		Arrays.sort(ans);
		for(Pair i:ans) {
			out.println(i.id+1);
			//out.println(i.id+1);
		}
	}
}