import java.io.PrintWriter;
import java.util.Scanner;

public class Circle {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Circle().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			double r = in.nextDouble();
			double a = r*Math.sqrt(2);
			double x = r*Math.sqrt(3);
			double s = a;
			double w = Math.sqrt(Math.pow(a, 2)-Math.pow(r, 2));
			double e = s+x+w;
			double ans = Math.pow(2*e*Math.sqrt(1.0/2.0),2);
			out.printf("%.5f\n",ans);
		}
		
	}
}
