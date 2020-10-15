import java.io.PrintWriter;
import java.util.Scanner;

public class etch {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new etch().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			double f1 = in.nextDouble();
			double f2 = in.nextDouble();
			double a = in.nextDouble();
			double b = in.nextDouble();
			double c = in.nextDouble();
			
			double low = 0;
			double high = 1000001;
			double mid = 0;
			double target = (f2-f1)/(f2*f1);
			for(int i=0; i<200; i++) {
				mid = (low+high)/2.0;
				double cur = a*mid + b*(1-Math.pow(Math.E, -c*mid));
				
				if(cur > target) {
					high = mid;
				} else {
					low = mid;
				}
			}
			out.printf("Crystal #"+(t+1)+": %.2f\n\n", mid);
		}
	}

}