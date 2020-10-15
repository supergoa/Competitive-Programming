import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class movingpoints {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new movingpoints().solve(in,out);
		in.close();
		out.close();
	}

	double[][] memo;
	int N,C;
	double[] x, y, d, s;
	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			N = in.nextInt();
			C = in.nextInt();
			if(N==0 && C==0) break;
			x = new double[N+1];
			y = new double[N+1];
			d = new double[N+1];
			s = new double[N+1];
			for(int i=0; i<N; i++) {
				x[i] = in.nextInt();
				y[i] = in.nextInt();
				d[i] =  Math.toRadians(in.nextInt());
				s[i] = in.nextInt(); //convert to radians
			}
			
			memo = new double[N][(1<<N)+1];
			for(int i=0; i<N; i++) {
				Arrays.fill(memo[i], -1);
			}
			
			double min = 987654321;
			for(int i=0; i<N; i++) {
				//System.out.println(i + " " + Integer.toBinaryString(((1<<N)-1-(1<<i))));
				min = Math.min(min, dp(i, (1<<N)-1-(1<<i)));
			}
			System.out.printf("%.2f\n", min);
		}
		
	}
	private double dp(int prev, int mask) {
		if(memo[prev][mask]!=-1) return memo[prev][mask];
		if(mask==0) return memo[prev][mask] = one(N, prev, 0);
		for(int i=0; i <N; i++) {
			if(((1<<i)&mask)==0) continue;
			dp(i, mask-(1<<i));
		}
		
		double best = Long.MAX_VALUE;
		for(int toRemove=0; toRemove<N; toRemove++) {
			if(((1<<toRemove)&mask)==0) continue;
			int curMask = mask - (1<<toRemove);
			double curTime = memo[toRemove][curMask];
			best = Math.min(best, one(toRemove, prev, curTime));
		}
		return memo[prev][mask] = best;
	}
	
	//int count = 0;
	/*private double binSearch(int a, int b, double t) {
		double timePassed = t;
		//if(test.contains((a+.3166)*(b+77.98)*t)) count++;
		//test.add((a+.3166)*(b+77.98)*t);
		double curx = x[a] + t * s[a] * Math.cos(d[a]);
		double cury = y[a] + t * s[a] * Math.sin(d[a]);
		double tox = x[b] + timePassed * s[b] * Math.cos(d[b]);
		double toy = y[b] + timePassed * s[b] * Math.sin(d[b]);
		
		double low = 0;
		double high = 1000000001;
		double mid =0;
		double newx=0, newy = 0;
		while(high-low > .0000001) {
			mid = (low+high)/2.0;
			double myDist = mid * C;
			newx = tox + mid * s[b] * Math.cos(d[b]);
			newy = toy + mid * s[b] * Math.sin(d[b]);
			
			double distBetween = dist(curx, cury, newx, newy);
			if(myDist >= distBetween) {
				high = mid;
			} else {
				low = mid;
			}
		}
		timePassed += mid;
		//System.out.println("mid: " + mid);
		//System.out.println(timePassed);
		//totbin += System.currentTimeMillis()-time;
		return timePassed;
	}*/
	// i hate this
	private double one(int a, int b, double t) {
		double curx = x[a] + t * s[a] * Math.cos(d[a]);
		double cury = y[a] + t * s[a] * Math.sin(d[a]);
        double tox = x[b] + t * s[b] * Math.cos(d[b]);
        double toy = y[b] + t * s[b] * Math.sin(d[b]);
       
        double angle = Math.atan2(cury-toy,curx-tox) - d[b];
        double dd = Math.hypot(tox-curx, toy-cury);

        double aa = C*C - s[b]*s[b];
        double bb = 2.0 * dd * s[b] * Math.cos( angle );
        double cc = -dd*dd;
        
        double dt = (-bb-Math.sqrt(bb*bb-4.0*aa*cc))/(2.0*aa);
        if(dt<0.0) dt = (-bb + Math.sqrt(bb*bb-4.0*aa*cc))/(2.0*aa);
        return t+dt;        
	}
}
