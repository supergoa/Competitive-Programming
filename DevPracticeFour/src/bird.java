import java.io.PrintWriter;
import java.util.Scanner;

public class bird {
	// I was 2 parentheses off :(
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new bird().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0;t<T; t++) {
			int R = in.nextInt();
			int N = in.nextInt();
			Point[] p = new Point[N];
			for(int n=0; n<N; n++) {
				p[n] = new Point(in.nextInt(), in.nextInt());
			}
			boolean good = true;
			for(int n=0; n<N; n++) {
				int ne = (n+1)%N;
				Point se = new Point(p[ne].x-p[n].x, p[ne].y-p[n].y);
				Point sp = new Point(p[n].x, p[n].y);
				double angle = dotCos(-sp.x, -sp.y, se.x, se.y);
				double d = mag(p[n].x, p[n].y) * Math.sin(angle);
				if(R>=d) {
					good = false;
				}
			}
			if(good) {
				System.out.println("Yard #"+(t+1)+ ": Fly away!");
			} else {
				System.out.println("Yard #"+(t+1)+ ": Better not risk it.");
			}
			
		}
			
	}
	double mag(int x1, int y1) {
		return Math.sqrt(x1*x1+y1*y1);
	}
	double dotCos(int x1, int y1, int x2, int y2) {
		return Math.acos((x1*x2+y1*y2)/(mag(x1,y1)*mag(x2,y2)));
	}
	class Point {
		int x; int y;
		public Point(int a, int b) {
			x = a;
			y = b;
		}
	}
}
