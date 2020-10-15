import java.io.PrintWriter;
import java.util.Scanner;

public class strike3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new strike3().solve(in,out);
		in.close();
		out.close();
	}

	final double PI = 3.141;
	private void solve(Scanner in, PrintWriter out) {
		int counter = 1;
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			double x1 = in.nextDouble();
			double y1 = in.nextDouble();
			double x2 = in.nextDouble();
			double y2 = in.nextDouble();
			double T = in.nextDouble();
			double[] xs = new double[N];
			double[] ys = new double[N];
			for(int n=0; n<N; n++) {
				xs[n] = in.nextDouble();
				ys[n] = in.nextDouble();
			}
			//double area = 0;
			//double area2 = 0;
			int best = 987654321;
			for(double e=0; e<=T; e+=.001) {
				double r = Math.sqrt(e/PI);
				//area2 = T-area;
				//System.out.println(area + " " + area2 + " " +(area+area2));
				double r2 = Math.sqrt((T-e)/PI);
				int count = 0;
				for(int n=0; n<N; n++) {
					
					if(Math.sqrt(Math.abs(xs[n]-x1)*Math.abs(xs[n]-x1) + Math.abs(ys[n]-y1)*Math.abs(ys[n]-y1)) -.0000001<= r
					|| Math.sqrt(Math.abs(xs[n]-x2)*Math.abs(xs[n]-x2) + Math.abs(ys[n]-y2)*Math.abs(ys[n]-y2)) -.0000001<= r2) {
					} else count++;
				}
				best = Math.min(best, count);
			}
			System.out.println((counter++) + ". "+best);
		}
		
	}
}
