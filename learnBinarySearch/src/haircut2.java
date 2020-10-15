import java.io.PrintWriter;
import java.util.Scanner;

public class haircut2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new haircut2().solve(in,out);
		in.close();
		out.close();
	}

	int B;
	int[] times;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			B = in.nextInt();
			int N = in.nextInt()-1;
			
			times = new int[B];
			for(int b=0; b<B; b++) {
				times[b] = in.nextInt();
			}
			long low = 0;
			long mid = 0;
			long high = (long) 1e15;
			while(low<=high) {
				mid = (low+high)/2;
				long numPpl = numPeopleHelped(mid);
				if(numPpl >= N) {
					high = mid-1;
				} else {
					low = mid+1;
				}
			}
			//low-=1;
			//System.out.println("~~");
			//System.out.println(low);
			int ans = -1;
			long cur = numPeopleHelped(low);
			//System.out.println(cur);
			for(int j=0; j<B; j++) {
				ans = j;
				//System.out.println("in " + j);
				//System.out.println(times[j]);
				if(low%times[j]==0) {
				//	System.out.println("??");
					cur++;
				}
				//System.out.println("cur " + cur + " " + N);
				if(cur == N+1) break;
			}
		
			out.println("Case #"+(t+1)+": "+(ans+1));
			
		}
	}

	private long numPeopleHelped(long mid) {
		long ret = 0;
		for(int i=0; i<B; i++) {
			ret += mid/times[i];
		}
		return ret;
	}
}