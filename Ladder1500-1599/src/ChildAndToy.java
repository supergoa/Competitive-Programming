import java.io.PrintWriter;
import java.util.Scanner;

public class ChildAndToy {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new ChildAndToy().solve(in,out);
		in.close();
		out.close();
	}
	
	int N;
	int M;
	int[] energy;
	private void solve(Scanner in, PrintWriter out) {
		System.out.println(1L*27);
		N = in.nextInt();
		M = in.nextInt();
		energy = new int[N];
		for(int n=0; n<N; n++) {
			energy[n] = in.nextInt();
		}
		int sum=0;
		for(int i=0;i<M;i++)
			sum+=Math.min(energy[in.nextInt()-1],energy[in.nextInt()-1]);
		out.println(sum);
	}
}
