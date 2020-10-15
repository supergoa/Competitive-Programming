import java.io.PrintWriter;
import java.util.Scanner;

public class birdman {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new birdman().solve(in,out);
		in.close();
		out.close();
	}

	public class Point { 
	    int x; 
	    int y; 
	    public Point(int a, int b) {
	    	x=a;
	    	y=b;
	    }
	}
	
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			Point B = new Point(in.nextInt(), in.nextInt());
			Point S = new Point(in.nextInt(), in.nextInt());
			Point P1 = new Point(in.nextInt(), in.nextInt());
			Point P2 = new Point(in.nextInt(), in.nextInt());
			
			if(intersect(B,S,P1,P2)) {
				System.out.println("Move to the left or right!");
			} else {
				System.out.println("Good picture, Birdman!");
			}
		}
		
	}
	
	boolean onLine(Point a, Point b, Point c) { 
	    if (b.x <= 	Math.max(a.x, c.x) && b.x >= Math.min(a.x, c.x) 
	     && b.y <= Math.max(a.y, c.y) && b.y >= Math.min(a.y, c.y)) 
	       return true; 
	  
	    return false; 
	} 

	int orientation(Point a, Point b, Point c) { 
	    // slope calculation
		// shorthand for slope1<slope2?1:2 (if equal then 0)
		int val = (b.y-a.y)*(c.x-b.x)-(b.x-a.x)*(c.y-b.y); 
	    if (val==0) return 0;  // same 
	    return (val > 0)? 1: 2; // clockwise/counterclock 
	} 

	boolean intersect(Point a, Point b, Point c, Point d) { 
	    int orientation1 = orientation(a, b, c); 
	    int orientation2 = orientation(a, b, d); 
	    int orientation3 = orientation(c, d, a); 
	    int orientation4 = orientation(c, d, b); 
	  
	    if (orientation1 != orientation2 && orientation3 != orientation4) return true; 
	    if (orientation1 == 0 && onLine(a, c, b)) return true; 
	    if (orientation2 == 0 && onLine(a, d, b)) return true; 
	    if (orientation3 == 0 && onLine(c, a, d)) return true; 
	    if (orientation4 == 0 && onLine(c, b, d)) return true; 
	  
	    return false;
	}
}
