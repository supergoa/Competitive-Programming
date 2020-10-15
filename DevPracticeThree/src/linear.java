import java.io.PrintWriter;
import java.util.Scanner;

public class linear {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new linear().solve(in,out);
		in.close();
		out.close();
	}
	/*
	 2
3 4 -10 5
17 6 0 50
	 */
	double eps = 1e-4;
	private void solve(Scanner in, PrintWriter out) throws Exception {
		int N = in.nextInt();
		for(int n=0;n<N;n++) {
			long A = in.nextLong();
			long B = in.nextLong();
			long lowX = in.nextLong();
			long highX = in.nextLong();
			//double dist = Math.sqrt(A*A+B*B);
			
			// dist * X 
			//double m = -A/B;
			//double theta = Math.atan2(m,1);
		
			//System.out.println(dist + " " + m + " " + theta);
			//double b = 1/B;
			//double lowY = lowX*m+b;
			//double highY = highX*m+b;
			//double firstX = -dist*1e9*Math.cos(theta);
			//double firstY = b-dist*1e9*Math.sin(theta);
			//double eps = Math.abs(firstX*A+firstY*B-1);
			//double distFromStart = Math.abs((lowX-firstX)/B);
			long count = 0;
			//double firstInBoundX = firstX+distFromStart*Math.abs(B);
			//double firstInBoundY = firstY+distFromStart*-Math.abs(A);
			//System.out.println(firstX + " " + firstY);
			//System.out.println(distFromStart + " " + firstInBoundX + " " + firstInBoundY);
			//if((lowX-firstX)/dist-eps<=lowX) count++;
			
			//double y1 = lowX*m+b;
			//double x1 = lowX;
			//double y2 = 
			
			//System.out.println("here2");
			//double firstXBound = lowX+Math.abs(B);
			//double test = lowX+Math.abs(B);
			//System.out.println(firstXBound + " " + test);
			//double firstYBound = lowY+dist*Math.cos(theta);
			final long oo = (int) 2e9;
			long firstX=-oo, firstY=-oo;
			long lastX = -oo; long lastY = -oo;
			//boolean atleastOnce = false;
			//int start = (int) ((lowX-(int)lowX <= eps)?lowX:lowX+1);
			for(long x=lowX; x<=highX; x++) {
				//double y = x*m+b;
				//System.out.println("x: "+x+" y:"+y);
				if(Math.abs(1-A*x)%B==0) {
					//atleastOnce = true;
					firstX = x;
					firstY = (1-A*x)/B;
					break;
				}
			}
			if(firstX==-oo) {
				System.out.println(0);
				continue;
			}
			/*for(long x=highX; x>=lowX; x--) {
				//double y = x*m+b;
				//System.out.println("x: "+x+" y:"+y);
				//System.out.println(x + " " + y);
				if(Math.abs(1-A*x)%B==0) {
					//atleastOnce = true;
					lastX = x;
					lastY = (1-A*x)/B;
					break;
				}
			}*/
			//System.out.println(firstX +" "+ lastX);
			//System.out.println(firstY +" "+ lastY);
			//count = GCD(Math.abs(lastX-firstX), Math.abs(firstY-lastY));
			//System.out.println(firstX + " " + firstY);
			//count++;
			long hiloDist = highX-firstX;
			count += hiloDist/B;
			//if(!atleastOnce) throw new Exception();
			System.out.println(count+1);
		}
		
		
	}
	long GCD(long A, long B) {
	    if(B==0)
	        return A;
	    else
	        return GCD(B, A % B);
	}
}
