import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class levees {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new levees().solve(in,out);
		in.close();
		out.close();
	}

   class Triangle implements Comparable<Triangle> {
	   double area;
	   double perim;
	   //DecimalFormat df = new DecimalFormat("###########.###");
	   public Triangle(double a, double p) {
		   area = a;
		   perim = p;
		   area = Double.parseDouble(String.format("%.3f", area));
		   perim = Double.parseDouble(String.format("%.3f", perim));
		   area = -area;
		   perim = -perim;
		   
	   }
	@Override
	public int compareTo(Triangle o) {
		if(Math.abs(area-o.area)>=.001) {
			//System.out.println(area + " " + o.area);
			return Double.compare(area, o.area);
		} else {
			//System.out.println("asfdasdfasdf");
			return Double.compare(perim, o.perim);
		}
	}
   }
	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			double Ax = in.nextDouble();
			double Ay = in.nextDouble();
			double Bx = in.nextDouble();
			double By = in.nextDouble();
			double Cx = in.nextDouble();
			double Cy = in.nextDouble();
			double Dx = in.nextDouble();
			double Dy = in.nextDouble();
			if(Ax==0 && Ay==0 && Bx==0 && By==0 && Cx==0 && Cy==0 && Dx==0 && Dy==0) break;
			
			double m1 = (Cy-Ay)/(Cx-Ax);
			double b1 = -Cx*m1+Cy;
			double m2 = (Dy-By)/(Dx-Bx);
			double b2 = -Dx*m2+Dy;
			double x,y;
			if(Double.isInfinite(m1)) {
				x = Ax;
				y = m2*x+b2;
			} else if(Double.isInfinite(m2)) {
				x = Bx;
				y = m1*x+b1;
			} else {
				x = (b1-b2)/(-m1+m2);
				y = m1*x+b1;
			}
			
			Triangle[] tris = new Triangle[4];
			
			double triArea1 = Math.abs(((Ax-x)*(By-y))-((Ay-y)*(Bx-x)))/2.0;
			double triArea2 = Math.abs(((Bx-x)*(Cy-y))-((By-y)*(Cx-x)))/2.0;
			double triArea3 = Math.abs(((Cx-x)*(Dy-y))-((Cy-y)*(Dx-x)))/2.0;
			double triArea4 = Math.abs(((Dx-x)*(Ay-y))-((Dy-y)*(Ax-x)))/2.0;
			
			double triPerim1 = Math.abs(mag(Ax,Ay,Bx,By) + mag(x,y,Ax,Ay) + mag(x,y,Bx,By));
			double triPerim2 = Math.abs(mag(Cx,Cy,Bx,By) + mag(x,y,Cx,Cy) + mag(x,y,Bx,By));
			double triPerim3 = Math.abs(mag(Cx,Cy,Dx,Dy) + mag(x,y,Cx,Cy) + mag(x,y,Dx,Dy));
			double triPerim4 = Math.abs(mag(Ax,Ay,Dx,Dy) + mag(x,y,Ax,Ay) + mag(x,y,Dx,Dy));
			
			tris[0] = new Triangle(triArea1,triPerim1);
			tris[1] = new Triangle(triArea2,triPerim2);
			tris[2] = new Triangle(triArea3,triPerim3);
			tris[3] = new Triangle(triArea4,triPerim4);
			
			Arrays.sort(tris);
			for(int i=0; i<4; i++) {
				out.printf("%.3f %.3f ",-tris[i].area, -tris[i].perim);
			}
			out.println();
		}
		
	}
	private double mag(double ax, double ay, double bx, double by) {
		return Math.sqrt((bx-ax)*(bx-ax) + (by-ay)*(by-ay));
	}
}
