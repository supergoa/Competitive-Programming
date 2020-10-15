import java.util.PriorityQueue;
import java.util.Scanner;

public class levees1 {
	static double eps = 1e-4;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		P a = new P(in.nextDouble(), in.nextDouble());
		P b = new P(in.nextDouble(), in.nextDouble());
		P c = new P(in.nextDouble(), in.nextDouble());
		P d = new P(in.nextDouble(), in.nextDouble());
		
		while (!a.z() || !b.z() || !c.z() || !d.z())
		{
			P e = intersect(a, c, b, d);
			// System.out.println(e.x+" "+e.y);
			
			P[] po = {a, b, c, d, a};
			double[] A = {area(a, b, e), area(b, c, e), area(c, d, e), area(d, a, e)};
			double[] pe = new double[4];
			
			for (int i = 0; i < 4; i++)
				pe[i] = dist(po[i], e)+dist(e, po[i+1])+dist(po[i], po[i+1]);
			
			PriorityQueue<quad> q = new PriorityQueue<>();
			for (int i = 0; i < 4; i++)
				q.add(new quad(A[i], pe[i]));
			
			while (q.size() != 1)
			{
				quad cur = q.poll();
				System.out.printf("%.3f %.3f ", cur.area, cur.peri);
			}
			quad last = q.poll();
			System.out.printf("%.3f %.3f\n", last.area, last.peri);
			
			a = new P(in.nextDouble(), in.nextDouble());
			b = new P(in.nextDouble(), in.nextDouble());
			c = new P(in.nextDouble(), in.nextDouble());
			d = new P(in.nextDouble(), in.nextDouble());
		}
		
		in.close();
	}
	
	public static class quad implements Comparable<quad>
	{
		double area, peri;
		
		public quad(double a, double p)
		{
			area = a; peri = p;
		}
		
		public int compareTo(quad o)
		{
			if (Math.abs(o.area-this.area) < eps)
				return Math.abs(o.peri-this.peri) < eps ? 0 : o.peri > this.peri ? 1 : -1;// > eps ? 1 : -1;
			return Math.abs(o.area-this.area) < eps ? 0 : o.area > this.area ? 1 : -1;// > eps ? 1 : -1;
		}
	}
	
	public static P intersect(P b, P s, P o, P q)
    {
        double d = det(q.x-o.x, b.x-s.x, q.y-o.y, b.y-s.y);
        double lam = (double)det(q.x-o.x, b.x-o.x, q.y-o.y, b.y-o.y)/(double)d;
        
        return new P(b.x+lam*(s.x-b.x), b.y+lam*(s.y-b.y));
    }
	
	public static double det(double a, double b, double c, double d)
	{
		return a*d-b*c;
	}
	
	public static double area(P a, P b, P c)
	{
		return 0.5*Math.abs(a.x*(b.y-c.y)-a.y*(b.x-c.x)+(b.x*c.y-b.y*c.x));
	}
	
	public static double dist(P a, P b)
	{
		return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
	}
	
	public static class P
	{
		double x, y;
		
		public P(double xx, double yy)
		{
			x = xx; y = yy;
		}
		
		public boolean z()
		{
			return -1*eps < this.x && this.x < eps && -1*eps < this.y && this.y < eps;
		}
	}	
	
}
