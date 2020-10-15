package BitOperators;

import java.io.PrintWriter;
import java.util.Scanner;

public class Logotron {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Logotron().solve(in,out);
		in.close();
		out.close();
	}

	class Statement {
		int a,s,x;
		public Statement(int b, int c, int d) {
			a = b;
			s = c;
			x = d;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int N = in.nextInt();
			int M = in.nextInt();
			Statement[] statements = new Statement[M];
			for(int m=0; m<M; m++) {
				statements[m] = new Statement(in.nextInt()-1, in.nextInt()-1, in.next().toCharArray()[0]);
			}
			int ans = 0;
			for(int mask=0; mask< 1<<N; mask++) {
				boolean good = true;
				for(Statement s : statements) {
					boolean truthS = ((1<<s.s) & mask) != 0;
					boolean truthA = ((1<<s.a) & mask) != 0;
					if(truthA) {
						if(s.x=='T' && !truthS) {
							good = false;
						}
						if(s.x=='C' && truthS) {
							good = false;
						}
					} else {
						if(s.x=='T' && truthS) {
							good = false;
						}
						if(s.x=='C' && !truthS) {
							good = false;
						}
					}
					if(!good) break;
				}
				if(good) ans++;
			}
			out.println("Case #"+(t+1)+": "+ans);
		}
	}
}
