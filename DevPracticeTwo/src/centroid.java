import java.io.PrintWriter;
import java.util.Scanner;

public class centroid {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new centroid().solve(in,out);
		in.close();
		out.close();
	}
	/**
	 * 
	 * 
	 3
1 1 10
22 1 10
1 31 10
3
10 10 100
20 20 50
10 40 30
	 * @param in
	 * @param out
	 */
	private void solve(Scanner in, PrintWriter out) {
		int count = 1;
		while(true) {
			int N = in.nextInt();
			if(N<0) break;
			int[] xs = new int[N];
			int[] ys = new int[N];
			int[] ms = new int[N];
			for(int n=0; n<N; n++) {
				xs[n] = in.nextInt();
				ys[n] = in.nextInt();
				ms[n] = in.nextInt();
			}
			double low = 0;
			double high = 5000;
			double a,b, mid = 0;
			for(int i=0; i<300; i++) {
				mid = (low+high)/2.0;
				double sum = 0;
				for(int n=0; n<N; n++) {
					sum += ms[n] * ((mid) - ys[n]);
				}
				if(sum > 0) {
					high = mid;
				} else if(sum < 0){
					low = mid;
				}
			}
			a = mid;
			
			low = 0;
			high = 5000;
			for(int i=0; i<300; i++) {
				mid = (low+high)/2;
				double sum = 0.0;
				for(int n=0; n<N; n++) {
					sum += ms[n] * ((mid) - xs[n]);
				}
				if(sum > 0) {
					high = mid;
				} else if(sum < 0){
					low = mid;
				}
			}
			b = mid;
			
			System.out.printf("Case "+(count)+": " + "%.2f %.2f\n", b, a);
			count++;
		}
	}
}
