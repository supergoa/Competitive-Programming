import java.util.Arrays;
import java.util.Scanner;

public class a1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new a1().solve(in);
		in.close();
	}

	private void solve(Scanner in) {
		int N = in.nextInt();
		int L = in.nextInt();
		int A = in.nextInt();
		int[] starts = new int[N];
		int[] ends = new int[N];
		for(int i=0; i<N; i++) {
			int t = in.nextInt();
			int l = in.nextInt();
			starts[i] = t;
			ends[i] = t+l;		
		}
		long ans =0;
		for(int i=1; i<N; i++) {
			int free = starts[i]-ends[i-1];	
			ans += free/A;
		}
		if(N>0) {
			ans+=(starts[0])/A;
			ans+=(L-ends[N-1])/A;
		}
		else ans+=(L)/A;
		System.out.println(ans);
	}
}
