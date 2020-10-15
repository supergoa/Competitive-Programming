import java.util.Scanner;

public class NeedForSpeed {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int T = scan.nextInt();
		
		double[] vels = new double[N];
		
		for(int n=0; n<N; n++) {
			int d = scan.nextInt();
			int s = scan.nextInt();
			vels[n] = s/(double)d;
					
			s.nextInt();
		}
		
	}
}
