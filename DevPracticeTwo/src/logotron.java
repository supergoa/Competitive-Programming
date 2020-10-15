import java.io.PrintWriter;
import java.util.Scanner;

public class logotron {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new logotron().solve(in,out);
		in.close();
		out.close();
	}

	class Statement{
		int A, S, X;
		public Statement(int a, int b, int c) {
			A = a;
			S = b;
			X = c;
		}
	}
	/**
	 * 
3
3 2
1 2 T
2 3 C
4 2
1 2 T
2 3 C
2 0
	 */
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int N = in.nextInt();
			int mask = (1<<N)-1;
			int M = in.nextInt();
			Statement[] s = new Statement[M];
			for(int m=0; m<M; m++) {
				s[m] = new Statement(in.nextInt()-1, in.nextInt()-1, in.next().toCharArray()[0]);
			}
			int count = 0;
			for(int i=0; i<=mask; i++) {
				//System.out.println(Integer.toBinaryString(i));
				boolean good = true;
				for(int m=0; m<M; m++) {
					//System.out.println(Integer.toBinaryString(i));
					boolean tato1 = (((1<<s[m].A) & i) !=0);
					boolean tato2 = (((1<<s[m].S) & i) !=0);
					//System.out.println(s[m].A + " " + tato1);
					//System.out.println(s[m].S + " " + tato2);
					if(s[m].X=='T') {
						if(tato1 && !tato2) good = false;
						if(!tato1 && tato2) good = false;
					} else {
						if(tato1 && tato2) good = false;
						if(!tato1 && !tato2) good = false;
					}
					if(!good) break;
				}
				if(good) count++;
			}
			System.out.println("Case #"+(t+1)+": " + count);
		}
		
	}
}
