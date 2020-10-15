import java.io.PrintWriter;
import java.util.Scanner;

//7:45
public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in, out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		while(T-->0) {
			int N = in.nextInt();
			int M = in.nextInt();
			
			long[] low = new long[N];
			long[] high = new long[N];
			long[] time = new long[N];
			for(int n=0; n<N; n++) {
				time[n] = in.nextInt();
				low[n] = in.nextInt();
				high[n] = in.nextInt();
			}
			
			long currentTime = 0;
			long lowTemp = M;
			long highTemp = M;
			boolean possible = true;
			for(int n=0; n<N; n++) {
				long nextTime = time[n];
				long difference = nextTime - currentTime;
				lowTemp -= difference;
				highTemp += difference;
				
				if(low[n] > highTemp || high[n] < lowTemp) {
					possible = false;
					break;
				}
				if(low[n] > lowTemp) {
					lowTemp = low[n];
				}
				if(high[n] < highTemp) {
					highTemp = high[n];
				}
				currentTime = nextTime;
			}
			out.println(possible?"YES":"NO");
		}
		
	}
}
