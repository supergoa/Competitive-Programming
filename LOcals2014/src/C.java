import java.io.PrintWriter;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in,out);
		in.close();out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int C = in.nextInt();
			int D = in.nextInt();
			int[] cells = new int[C];
			int start = C-1;
			String line = in.next();
			for(int c=0; c<C; c++) {
				if(line.charAt(c)=='.') {
					cells[c] = 1;
				}
			}
			int numHops = 0;
			boolean possible = false;
			for(int c=C-1; c>=0;) {
				if(c==0) break;
				possible = false;
				for(int to = Math.max(c-D-1,0); to<c; to++) {
					if(cells[to]==1) {
						possible = true;
						c=to;
						numHops++;
						break;
					}
				}
				if(!possible) break;
			}
			out.println("Day #"+(n+1));
			out.println(C + " " + D);
			out.println(line);
			if(possible) {
				out.println(numHops);
			} else {
				out.println(0);
			}
			out.println();
		}
		
	}
}
