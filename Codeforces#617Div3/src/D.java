import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new D().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		int K = in.nextInt();
		
		int[] hp = new int[N];
		long[] kneeded = new long[N];
		for(int i=0; i<N; i++) hp[i] = in.nextInt();
		for(int n=0; n<N; n++) {
			long low = 0;
			long high = 987654321;
			while(high-low>5) {
				long mid = (low+high)/2;
				if(mid * a + mid * b >= hp[n]) {
					high = mid;
				} else {
					low = mid;
				}
			}
			long turn = -1;
			for(long i=Math.max(low-5, 0); i<=low+10; i++) {
				if(i*a+i*b >= hp[n]) break;
				turn = i;
			}
			
			long hpLeft = -(turn * a + turn * b) + hp[n];
			int flag = hpLeft % a == 0 ? 0 : 1;
			long turnsNeeded = hpLeft/a + flag;
			//System.out.println("Turn: "+turn);
			//System.out.println(hpLeft + ", " + turnsNeeded);
			kneeded[n] = turnsNeeded-1;
		}
		Arrays.sort(kneeded);
		long cur = K;
		int ind = 0;
		while(cur > 0) {
			if(ind >=N) break;
			if(cur >= kneeded[ind]) {
				cur -= kneeded[ind];
				ind++;
			} else {
				break;
			}
		} 
		out.println(ind);
	}
}
