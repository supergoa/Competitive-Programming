import java.util.Scanner;

public class collide {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count = 1;
		outer: while(true) {
			int N = in.nextInt();
			if(N==0) return;
			long[] nums = new long[N];
			for(int n=0; n<N; n++) nums[n] = in.nextInt();
			long lcm = nums[0];
			for(int n=1; n<N; n++) {
				//System.out.println(lcm + " " + nums[n]);
				lcm = LCM(lcm, nums[n]);
				if(lcm>Integer.MAX_VALUE) {System.out.println((count++)  + ": NOT TO WORRY");continue outer;}
			}
			System.out.println((count++)  + ": THE WORLD ENDS IN " + lcm + " DAYS");
		}
	}

	private static long LCM(long a, long b) {
		
		return (a*b)/GCD(a,b);
	}

	private static long GCD(long A, long B) {
		if(B==0)
	        return A;
	    else
	        return GCD(B, A % B);
	}
}
