import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class relprime {
	static int[] primes;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		primes = new int[78498];
		boolean[] sieve = new boolean[(int) (1e6+1)]; Arrays.fill(sieve, true);
		sieve[2] = true;
		for(int i=2; i*i<=(int) (1e6); i++) {
			if(sieve[i]) {
				for(int j=i*i; j<=(int) (1e6); j+=i) {
					sieve[j] = false;
				}
			}
		}
		int count = 0;
		for(int i = 2; i <= (int) (1e6); i++)  if(sieve[i]) primes[(count++)] = i;
		while(T-->0) {
			out.println(phi(in.nextLong()));
		}
		in.close();
		out.close();
	}
	static long phi(long n) {
	    long result = n;
	    for (int i : primes) { // precomp primes here with prime sieve if too slow
	        if(n % i == 0) {
	            while(n % i == 0)
	                n /= i;
	            result -= result / i;
	        }
	    }
	    if(n > 1)
	        result -= result / n;
	    return result;
	}
}
