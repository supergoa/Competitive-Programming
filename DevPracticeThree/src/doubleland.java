import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class doubleland {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new doubleland().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int F = in.nextInt();
			int[] farms = new int[F];
			for(int f=0; f<F; f++) {
				farms[f] = in.nextInt();
			}
			int K = in.nextInt();
			HashSet<Integer> desired = new HashSet<>();;
			for(int k=0; k<K; k++) {
				desired.add(in.nextInt()-1);
			}
			int countBot = 0;
			int countTop = 0;
			for(int mask=0; mask<(1<<F);mask++) {
				int sum1 = 0;
				int sum2 = 0;
				int allDesired = K;
				for(int i=0; i<F; i++) {
					if((mask & (1<<i)) == 0) {
						sum1 += farms[i];
					} else {
						sum2 += farms[i];
						if(desired.contains(i)) {
							allDesired--;
						}
					}
				}
				if(sum1==sum2) {
					countBot++;
					if(allDesired==0) {
						countTop++;
					}
				}
				
			}
			int gcd=GCD(countBot, countTop);
			countBot /= gcd;
			countTop /= gcd;
			System.out.println(countTop+"/"+countBot);
		}
		
	}
	int GCD(int A, int B) {
	    if(B==0)
	        return A;
	    else
	        return GCD(B, A % B);
	}
}
