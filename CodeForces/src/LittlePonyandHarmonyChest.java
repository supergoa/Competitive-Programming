import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class LittlePonyandHarmonyChest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new LittlePonyandHarmonyChest().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		//int[] a = new int[N];
		for(int n=0; n<N; n++) {
		//	a[n] = in.nextInt();
		}
		//Arrays.sort(a);
		
		//precomp
		boolean[] memo = new boolean[1<<30];
		
		for(int mask=0; mask<1<<30; mask++) {
			
			int gcd = 1;
			for(int i=0; i<30; i++) {
				if((mask & (1<<i)) == 0) continue; //bit i is off
				for(int j=0; j<30; j++) {
					if((mask & (1<<j)) == 0 || i==j) continue; //bit j is off
					gcd = GCD(i+1, j+1);
					if(gcd!=1) break;
				}
				if(gcd!=1) break;
			}
			if(gcd==1) memo[mask] = true;
		}
		System.out.println("done");
	}
	int GCD(int A, int B) {
		//System.out.println("A: " + A + "   B: " + B);
	    if(B==0)
	        return A;
	    else
	        return GCD(B, A % B);
	}
}
