import java.io.PrintWriter;
import java.util.Scanner;

public class euclid {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new euclid().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) throws Exception {
		while(true) {
			double Ax = in.nextDouble();
			double Ay = in.nextDouble();
			double Bx = in.nextDouble();
			double By = in.nextDouble();
			double Cx = in.nextDouble();
			double Cy = in.nextDouble();
			double Dx = in.nextDouble();
			double Dy = in.nextDouble();
			double Ex = in.nextDouble();
			double Ey = in.nextDouble();
			double Fx = in.nextDouble();
			double Fy = in.nextDouble();
			if(Ax==0 && Ay==0 && Bx==0 && By==0 && Cx==0 && Cy==0
			&& Dx==0 && Dy==0 && Ex==0 && Ey==0 && Fx==0 && Fy==0) break;
			
			double triArea = Math.abs(((Ex-Dx)*(Fy-Dy))-((Ey-Dy)*(Fx-Dx)))/2.0;
			double rectArea = Math.abs(Cx*By-Ax*By-Cx*Ay+Ax*Ay-Cy*Bx+Cy*Ax+Ay*Bx-Ax*Ay);
			double theta = Math.atan2(Cy-Ay, Cx-Ax);
			double r = triArea/rectArea;
			//double Hx,Hy;
			double magAC = Math.sqrt((Cx-Ax)*(Cx-Ax) + (Cy-Ay)*(Cy-Ay));
			double magAH = magAC * r;
			//System.out.println(triArea + " " + rectArea + " " + r + " " + magAH);
			double Gx = Bx+magAH*Math.cos(theta);
			double Gy = By+magAH*Math.sin(theta);
			double Hx = Ax+magAH*Math.cos(theta);
			double Hy = Ay+magAH*Math.sin(theta);
			//double m = (Cy-Ay)/(Cx-Ax);
			//double b = -Cx*m+Cy;
			//double Hx,Hy;
			//System.out.println(m);
			/*if(Double.isInfinite(m)) {
				double rectArea = (Bx-Ax)*(Cy-Ay);
				double r = triArea/rectArea;
				Hx = Ax;
				Hy = Cy*r;
				double magAH = Math.sqrt((Hx-Ax)*(Hx-Ax) + (Hy-Ay)*(Hy-Ay));
				double Gx = Bx+magAH*Math.cos(theta);
				double Gy = By+magAH*Math.sin(theta);
				System.out.printf("%.3f %.3f %.3f %.3f\n", Gx,Gy,Hx,Hy);
				continue;
			} else {
				Hx = (triArea+Ax*(By-Ay)+Ay*(-Bx+Ax)-b*Ax+b*Bx)/(By-Ay+m*(Ax-Bx));
				Hy = Hx*m+b;
			} 
			double magAH = Math.sqrt((Hx-Ax)*(Hx-Ax) + (Hy-Ay)*(Hy-Ay));
			//System.out.println(Hx + " " + Hy + " " + magAH);
			
			double factor = -10000000;
			
			double firstHx = Hx;
			double firstHy = Hy;
			while(Math.max(Ax, Cx)<Hx || Math.min(Ax, Cx)>Hx || Math.max(Ay, Cy)<Hy || Math.min(Ay, Cy)>Hy) {
				Hx = firstHx + factor*magAH*Math.cos(theta);
				Hy = firstHy + factor*magAH*Math.sin(theta);
				factor++;
				//System.out.println(Math.abs(Hx*By-Ax*By-Hx*Ay+Ax*Ay-Hy*Bx+Hy*Ax+Ay*Bx-Ax*Ay)+ " " + test);
				if(factor==10000000) throw new Exception();
			}
			//System.out.println(factor);
			//System.out.println(test + " " + triArea + " " + (triArea+Ax*(By-Ay)+Ay*(-Bx+Ax)-b*Ax+b*Bx) + " " + (By-Ay+m*(Ax-Bx)));
			//System.out.println(theta + " " + Math.cos(theta) + " " + magAH + " " + Bx + " " + By);
			double Gx = Bx+magAH*Math.cos(theta);
			double Gy = By+magAH*Math.sin(theta);*/
			System.out.printf("%.3f %.3f %.3f %.3f\n", Gx,Gy,Hx,Hy);
			//System.out.println();
		}
		
	}
}
