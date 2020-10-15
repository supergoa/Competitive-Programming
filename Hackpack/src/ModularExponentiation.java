import java.util.Scanner;

public class ModularExponentiation {
	public static void main(String[] args) {
		final long mod = (long)1e9+7;
		Scanner in = new Scanner(System.in);
		Long N = in.nextLong()%mod;
		Long M = in.nextLong();
		long ans = 1;
		
		while(M>0) {
			if(M%2==1) {
				ans = ans*N % mod;
				M--;
			}
			N = N*N % mod;
			M/=2;
		}
		System.out.println(ans%mod);
	}
}
