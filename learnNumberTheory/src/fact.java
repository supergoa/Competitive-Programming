import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class fact {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		
		ArrayList<Integer> primes = new ArrayList<>();
		boolean[] sieve = new boolean[8001]; Arrays.fill(sieve, true);
		sieve[2] = true;
		for(int i=2; i*i<=8000; i++) {
			if(sieve[i]) {
				for(int j=i*i; j<=8000; j+=i) {
					sieve[j] = false;
				}
			}
		}
		for(int i = 2; i <= 8000; i++)  if(sieve[i]) primes.add(i);
		
		// this works, uncommented below is more efficient though
		/*while(T-->0) {
			int N = in.nextInt();
			int[] exps = new int[8001];
			for(int i=N; i>=2; i--) {
				int cur = i;
				for(int j : primes) {
					while(cur%j==0) {
						exps[j]++;
						cur/=j;
					}
				}
			}
			for(int i:exps) {
				if(i!=0) System.out.print(i+ " ");
			}
			System.out.println();
		}*/
		while(T-->0) {
			int N = in.nextInt();
			int[] exps = new int[8001];
			for(int j : primes) {
				for(int k=j; k<=N; k*=j)
					exps[j]+=N/k;
			}
			for(long i:exps) {
				if(i!=0) System.out.print(i+ " ");
			}
			System.out.println();
		}
	}
	
	
}
