import java.io.PrintWriter;
import java.util.Scanner;

public class tadpole {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new tadpole().solve(in,out);
		in.close();
		out.close();
	}

	double[] xs;
	double[] ys;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int N = in.nextInt();
			double D = in.nextDouble();
			xs = new double[N];
			ys = new double[N];
			for(int n=0; n<N; n++) {
				xs[n] = in.nextDouble();
				ys[n] = in.nextDouble();
				
			}
			int max = 0;
			for(int n=0; n<N; n++) {
				
				for(int nn=0; nn<N; nn++) {
					
					if(n==nn) continue;
					double ang = Math.atan2(ys[n]-ys[nn], (xs[n]-xs[nn]));
					double thetaChange = Math.asin(1/2);
					double[] tryX = new double[3]; 
					double[] tryY = new double[3]; 
					tryX[0] = xs[n] + Math.cos(ang)*D/2;
					tryY[0] = ys[n] + Math.sin(ang)*D/2;
					tryX[1] = xs[n] + Math.cos(ang+thetaChange)*D/2;
					tryY[1] = ys[n] + Math.sin(ang+thetaChange)*D/2;
					tryX[2] = xs[n] + Math.cos(ang-thetaChange)*D/2;
					tryY[2] = ys[n] + Math.sin(ang-thetaChange)*D/2;
					//System.out.println(ang);
					for(int tt=0; tt<3; tt++) {
						int cur = 0;
						for(int f=0; f<N; f++) {
							if(Math.sqrt((xs[f]-tryX[tt])*(xs[f]-tryX[tt]) + (ys[f]-tryY[tt])*(ys[f]-tryY[tt]))+.000001 <=D/2) {
								//if(nn==f) System.out.println("woopie");
								cur++;
							}
						}
						max = Math.max(max, cur);
					}
					
				}
				
			}
			System.out.println(max+1);
		}
		
	}
}
