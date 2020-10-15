import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class blastamon {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new blastamon().solve(in,out);
		in.close();
		out.close();
	}

	class minion implements Comparable<minion>{
		String name; int hp, id;
		public minion(String a, int b) {
			this.name = a;
			hp = b;
		}
		@Override
		public int compareTo(minion o) {
			return (this.hp-o.hp);
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			minion[] names = new minion[N];
			for(int n=0;n<N; n++) {
				names[n] = new minion(in.next(), in.nextInt());
			}
			Arrays.sort(names);
			for(minion m:names) {
				System.out.println(m.name);
			}
			System.out.println();
		}
		
	}
}
