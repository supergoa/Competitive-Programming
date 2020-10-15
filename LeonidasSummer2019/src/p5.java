import java.util.Scanner;

public class p5 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new p5().solve(in);
		in.close();
	}

	final double eps = 1e-6;
	private void solve(Scanner in) {
		int N = in.nextInt();
		double[] xs = new double[N];
		double[] ys = new double[N];
		double[] ds = new double[N];
		
		for(int n=0; n<N; n++) {
			xs[n] = in.nextInt();
			ys[n] = in.nextInt();
			ds[n] = in.nextInt();
		}
		double max = 0;
		ii: for(int i=-1000; i<=1000; i++) {
			for(int n=0; n<N; n++) {
				if(Math.abs(i - xs[n]) > ds[n] - eps) {
					continue ii;
				}
			}
			jj: for(int j=-1000; j<=1000; j++) {
				if(Math.sqrt(i*i + j*j) < max) continue;
					for(int n=0; n<N; n++) {
						if(Math.sqrt((i - xs[n])*(i - xs[n]) + (j - ys[n])*(j - ys[n])) > ds[n] - eps) {
							continue jj;
						}
					}
					max = Math.max(max, Math.sqrt(i*i + j*j));
			}
		}
		System.out.println(max);
	}


}
