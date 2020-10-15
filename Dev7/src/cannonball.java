import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class cannonball {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new cannonball().solve(in,out);
		in.close();
		out.close();
	}

	class Cannon {
		double x,y;
		int id;
		public Cannon (double a, double b, int c) {
			x=a;
			y=b;
			id = c;
		}
	}
	final int INF = (int) 1e9;
	private void solve(Scanner in, PrintWriter out) {
		double x1 = in.nextDouble();
		double y1 = in.nextDouble();
		double x2 = in.nextDouble();
		double y2 = in.nextDouble();
		int N = in.nextInt();
		Cannon[] c = new Cannon[N];
		for(int n=0;n<N;n++) {
			c[n] = new Cannon(in.nextDouble(), in.nextDouble(), n);
		}
		double[][] dists = new double[N+2][N+2];
		for(int n=0; n<N+2; n++) {
			Arrays.fill(dists[n], INF);
		}
		// between starts/ends and cannons
		for(int n=0; n<N; n++) {
			double dist = Math.abs(Math.sqrt((c[n].x-x1)*(c[n].x-x1) + (c[n].y-y1)*(c[n].y-y1))-50)/5;
			dist = Math.min(dist, Math.sqrt((c[n].x-x1)*(c[n].x-x1) + (c[n].y-y1)*(c[n].y-y1))/5);
			dists[n][N] = INF;
			dists[N][n] = 2+Math.sqrt((c[n].x-x1)*(c[n].x-x1) + (c[n].y-y1)*(c[n].y-y1))/5;
			
			dist = Math.abs(Math.sqrt((c[n].x-x2)*(c[n].x-x2) + (c[n].y-y2)*(c[n].y-y2))-50)/5;
			dist = Math.min(dist, Math.sqrt((c[n].x-x2)*(c[n].x-x2) + (c[n].y-y2)*(c[n].y-y2))/5);
			dists[n][N+1] = (dist);
			dists[N+1][n] = INF;
		}
		
		dists[N][N+1] = (Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)))/5;
		dists[N+1][N] = (Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)))/5;
		
		for(int n=0; n<N; n++) {
			for(int nn=0; nn<N; nn++) {
				if(n==nn) continue;
				//double angle = Math.atan2(c[n].y-c[nn].y, c[n].x-c[nn].x);
				//double landX = c[n].x+50*Math.cos(angle);
				//double landY = c[n].y+50*Math.sin(angle);
				double dist = Math.abs(Math.sqrt((c[nn].x-c[n].x)*(c[nn].x-c[n].x) + (c[nn].y-c[n].y)*(c[nn].y-c[n].y))-50)/5;
				dist = Math.min(dist, Math.abs((c[nn].x-c[n].x)*(c[nn].x-c[n].x) + (c[nn].y-c[n].y)*(c[nn].y-c[n].y))/5);
				dists[n][nn] = 2+(dist);
				dists[nn][n] = 2+(dist);
			}
		}
		
		for (int k = 0; k < N+2; ++k) {
		    for (int i = 0; i < N+2; ++i) {
		        for (int j = 0; j < N+2; ++j) {
		        	if (dists[i][k] < INF && dists[k][j] < INF) //maybe remove this if statement
		            dists[i][j] = Math.min(dists[i][j], dists[i][k] + dists[k][j]); 
		        }
		    }
		}
		System.out.println(dists[N][N+1]);
		
	}
}
