import java.io.PrintWriter;
import java.util.Scanner;

public class E {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new E().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int N = in.nextInt();
			int X = in.nextInt();
			int Y = in.nextInt();
			
			int bestDist = 987654321;
			int bestCoins = 987654321;
			int bestInd = -1;
			for(int n=0; n<N; n++) {
				int D = in.nextInt();
				int M = in.nextInt();
				if(D<=X && M>=Y) {
					if(D<bestDist) {
						bestDist = D;
						bestCoins = M;
						bestInd = n+1;
						continue;
					}
					if(D==bestDist && M>bestCoins) {
						bestDist = D;
						bestCoins = M;
						bestInd = n+1;
						continue;
					}
				}
			}
			out.println(bestInd);
		}
		
	}
}
