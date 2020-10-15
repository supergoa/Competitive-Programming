import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

// was only a /n on printout
public class balance {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new balance().solve(in,out);
		in.close();
		out.close();
	}

	class Segment implements Comparable<Segment>{
		double x1,y1,x2,y2,length;
		public Segment(double a, double b, double c, double d) {
			x1 = a;
			y1 = b;
			x2 = c;
			y2 = d;
			length = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
		}
		@Override
		public int compareTo(Segment o) {
			return Double.compare(length, o.length);
		}
		@Override
		public String toString() {
			return x1 + " " + y1  + " " + x2 + " " + y2 + " " + length;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			Segment[] s = new Segment[3];
			double[] xs = new double[3];
			double[] ys = new double[3];
			for(int i=0; i<3; i++) {
				xs[i] = in.nextDouble();
				ys[i] = in.nextDouble();
			}
			s[0] = new Segment(xs[0], ys[0], xs[1], ys[1]);
			s[1] = new Segment(xs[2], ys[2], xs[1], ys[1]);
			s[2] = new Segment(xs[0], ys[0], xs[2], ys[2]);
			Arrays.sort(s);
			//System.out.println(Arrays.toString(s));
			double v1x = s[2].x1-s[2].x2;
			double v1y = s[2].y1-s[2].y2;
			
			double v2x = s[1].x1-s[1].x2;
			double v2y = s[1].y1-s[1].y2;
			double area = Math.abs(v1x*v2y-v2x*v1y)/2;
			//System.out.println(v1x);
			//System.out.println(v2x);
			//System.out.println(v1y);
			//System.out.println(v2y);
			//System.out.println(area);
			
			double theta = Math.asin(area*2/(s[1].length*s[2].length));
			double h = s[1].length*Math.sin(theta);
			double newTri = s[1].length*Math.cos(theta)*h/2;
			//System.out.println(theta + " " + h + " " + newTri);
			
			double oldLength = s[1].length*Math.cos(theta);
			
			double x1 = 0; double x2=0;
			double y1 = 0; double y2=0;
			if(s[2].x1==s[0].x1 && s[2].y1==s[0].y1) {
				x1 = s[2].x1;
				y1 = s[2].y1;
				
				x2 = s[2].x2;
				y2 = s[2].y2;
				
			} else if(s[2].x2==s[0].x1 && s[2].y2==s[0].y1){
				//System.out.println("here1");
				x1 = s[2].x2;
				y1 = s[2].y2;
				
				x2 = s[2].x1;
				y2 = s[2].y1;
				
			} else if(s[2].x1==s[0].x2 && s[2].y1==s[0].y2){
				//System.out.println("here2");
				x1 = s[2].x1;
				y1 = s[2].y1;
				
				x2 = s[2].x2;
				y2 = s[2].y2;
			} else if(s[2].x2==s[0].x2 && s[2].y2==s[0].y2){
				//System.out.println("here3");
				x1 = s[2].x2;
				y1 = s[2].y2;
				
				x2 = s[2].x1;
				y2 = s[2].y1;
			}
			double thetaBig = Math.atan2(y1-y2, x1-x2);
			double ratio=0;// = (area/2)/newTri;
			double newLength=0;// = ratio * s[2].length;
			double low = 0;
			double high = oldLength;
			double mid = 0;
			for(int i=0; i<700; i++) {
				mid = (low+high)/2;
				h = Math.tan(theta)*mid;
				double testArea = mid*h/2;
				if(testArea<area/2) {
					low=mid;
				} else {
					high = mid;
				}
			}
			ratio = mid/oldLength;
			newLength = ratio * oldLength;
			//System.out.println(ratio + " " + newLength + " " + thetaBig); 
			double addx = 0, addy = 0;
			if(s[2].x1==s[1].x1 && s[2].y1==s[1].y1) {
				addx = s[2].x1;
				addy = s[2].y1;
			} else if(s[2].x2==s[1].x1 && s[2].y2==s[1].y1){
				//System.out.println("here1");
				addx = s[2].x2;
				addy = s[2].y2;
			} else if(s[2].x1==s[1].x2 && s[2].y1==s[1].y2){
				//System.out.println("here2");
				addx = s[2].x1;
				addy = s[2].y1;
			} else if(s[2].x2==s[1].x2 && s[2].y2==s[1].y2){
				//System.out.println("here3");
				addx = s[2].x2;
				addy = s[2].y2;
			}
			double ansx = addx+newLength * Math.cos(thetaBig);
			double ansy = addy+newLength * Math.sin(thetaBig);
			//System.out.println(thetaBig + " " + addx + " " + addy);
			System.out.printf("Triangle #%d Balance Point: (%.2f,%.2f)\n",(n+1),ansx,ansy );
			
		}
	}
}
/*
 * 3
0 0 5 0 1.8 2.4
0 0 0 5 -2.4 3.2
0 0 1 0 .5 .86602540378 

*1
 8.23 43.23 45.55 1.11 3.21 7.77
Triangle #1 Balance Point: (25.32,23.94)*/
