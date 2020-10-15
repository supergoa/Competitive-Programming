import java.io.PrintWriter;
import java.util.Scanner;

public class composition {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new composition().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		long T = in.nextInt();
		for(int t=0; t<T; t++) {
			long N = in.nextInt();
			long[] nums = new long[100001];
			for(int n=0; n<N; n++) {
				nums[in.nextInt()] = in.nextInt();
			}
			
			long[] mNeeded = new long[100001];
			for(int n=0; n<100001; n++) {
				long num = nums[(int) nums[n]];
				long count = 1;
				while(num != nums[n]) {
					num = nums[(int) num];
					count++;
				}
				mNeeded[n] = count;
			}
			long lcm = mNeeded[0];
			for(int n=1; n<100001; n++) {
				lcm = (lcm*mNeeded[n])/GCD(lcm, mNeeded[n]);
				
			}
			System.out.println(lcm);
		}
		
	}
	
	static long GCD(long A, long B) {
	    if(B==0)
	        return A;
	    else
	        return GCD(B, A % B);
	}
}
