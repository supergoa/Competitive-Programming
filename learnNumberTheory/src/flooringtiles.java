import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class flooringtiles {
	static HashMap<Long, Long> hm = new HashMap<>();
	static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		backtrack(1, new int[8], 0);
		while(true) {
			long q = in.nextInt();
			if(q==0) break;
			if(q==1) {System.out.println(1); continue;} // backtrack doesn't handle 1
			out.println(hm.get(q));
		}
		in.close();
		out.close();
	}
	
	/*
	 * The number of divisors a number has is the product
	 * of all (exponent_i + 1) where exponent_i = the exponent of the number's ith prime in its prime factorization.
	 * e.g. 20 -> 2^2 * 5^1 -> (2+1)*(1+1) = 6 divisors for 20. (6 or 5 divisors = 3 rectangles)
	 * In the case of 4, it has 3 divisors, 1, 2, and 4 --> forms 2 rectangles.
	 * 
	 * This backtrack method brute forces each prime's exponent and saves the minimum
	 * number formed when each prime factorization is computed.
	 * 
	 * e.g. Input case 2: N=16, Answer=840
	 * 840 = 2^3 * 3^1 * 5^1 * 7^1 --> has 4*2*2*2 = 32 divisors --> forms N=16 rectangles
	 * backtrack() finds the minimum value such that that value's exponents multiply to 32
	 * Ergo, depth 1 chooses 4, depth 2 chooses 2, depth 3 chooses 2, and depth 4 chooses 2
	 * When possible, backtrack() calculates the current value formed by the current exponents chosen.
	 * In this case, that is 2^(4-1) * 3^(2-1) * 5^(2-1) * 7^(2-1)
	 * The method will continue to multiply exponents even after this to find solutions for other values.
	 * 
	 * You must realize that it is never better to use a higher exponent on a larger prime (as that would result in
	 * the same number of divisors but result in a worse answer) when compared to using the higher exponents on the smaller primes.
	 * e.g. 2^3 * 5^1 is better than 2^1 * 5^3, but both numbers have 6 divisors
	 * Similarly, it never makes sense to use a higher prime before the previous one has been used.
	 */
	private static void backtrack(long cur, int[] used, int ind) {
		if(cur > 150) return;
		else if(cur > 2) {
			long ans = 1;
			// compute the answer resulting from the chosen exponents
			for(int i=0; i<ind; i++) ans *= Math.pow(primes[i], used[i]-1);
			long best = Math.min(ans, hm.getOrDefault((cur+1)/2, Long.MAX_VALUE));
			hm.put((cur+1)/2, best);
		}
		// i represents my next prime's exponent
		for(int i=150; i>=2; i--) {
			used[ind] = i;
			backtrack(cur*i, used, ind+1);
		}
		return;
	}
}
