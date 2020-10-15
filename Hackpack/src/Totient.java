public class Totient {
	int phi(int n) {
	    int result = n;
	    for (int i = 2; i * i <= n; i++) { // precomp primes here with prime sieve if too slow
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
	
	// a^phi(n) = 1 mod m;
}
