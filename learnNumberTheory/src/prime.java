import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class prime {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		
		ArrayList<Integer> primes = new ArrayList<>();
		boolean[] sieve = new boolean[(int) (1e7+1)]; Arrays.fill(sieve, true);
		sieve[2] = true;
		for(int i=2; i*i<=(int) (1e7); i++) {
			if(sieve[i]) {
				for(int j=i*i; j<=(int) (1e7); j+=i) {
					sieve[j] = false;
				}
			}
		}
		//for(int i = 2; i <= 8000; i++)  if(sieve[i]) primes.add(i);
		while(T-->0) {
			int N = in.nextInt();
			if(sieve[N]) System.out.println(1);
			else System.out.println(0);
		}
	}
}
