import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in, out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		while(T-->0) {
			int N = in.nextInt();
			int[] arr = new int[N];
			for(int n=0; n<N; n++) {
				arr[n] = in.nextInt();
			}
			HashSet<Integer> hs = new HashSet<>();
			long min = 9876543210l;
			long max = -9876543210l;
			for(int n=0; n<N; n++) {
				int low = Math.max(n-1, 0);
				int high = Math.min(n+1, N-1);
				if(arr[n] == -1) {
					if(arr[low] != -1) {
						min = Math.min(min, arr[low]);
						max = Math.max(max, arr[low]);
					}
					if(arr[high] != -1) {
						min = Math.min(min, arr[high]);
						max = Math.max(max, arr[high]);
					}
				}
			}
			long diff = 0;
			if(min != 9876543210l && max != -9876543210l) {
				long ans = (max - min)/2 + min;
				for(int n=1; n<N; n++) {
					long cur = arr[n];
					long last = arr[n-1];
					if(cur == -1) cur = ans;
					if(last == -1) last = ans;
					diff = Math.max(diff, Math.abs(cur - last));
				}
				out.println(diff + " "+ ans);
			}
			if(min == 9876543210l && max == -9876543210l) {
				long ans = Math.max(arr[0], 0);
				for(int n=1; n<N; n++) {
					long cur = arr[n];
					long last = arr[n-1];
					if(cur == -1) cur = ans;
					if(last == -1) last = ans;
					diff = Math.max(diff, Math.abs(cur - last));
				}
				out.println(diff + " "+ ans);
			}

		}
		
	}
}
