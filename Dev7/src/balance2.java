import java.util.*;

public class balance2 {

	static double eps = 1e-6;
	static p a, b, c, C;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();		
		
		for (int i = 1; i <= n; i++)
		{
			a = new p(in.nextDouble(), in.nextDouble());
			b = new p(in.nextDouble(), in.nextDouble());
			c = new p(in.nextDouble(), in.nextDouble());
			double ab = d(a, b);
			double bc = d(c, b);
			double ac = d(a, c);
			
			int max = 0;
			if (ab-bc > eps && ab-ac > eps) max = 0;
			if (bc-ab > eps && bc-ac > eps) max = 1;
			if (ac-bc > eps && ac-ab > eps) max = 2;

			p A = ans(max);
			System.out.printf("Triangle #"+i+" Balance Point: (%.2f,%.2f)\n", A.x, A.y);
		}
		
		in.close();
	}
	
	public static p ans(int max)
	{
		p x = new p(0, 0), y = new p(0, 0), z = new p(0, 0);
		if (max == 0) { x = a; y = b; z = c; }
		if (max == 1) { x = b; y = c; z = a; }
		if (max == 2) { x = a; y = c; z = b; }
		
		if (d(x, z)-d(y, z) > eps)
		{
			double cos = loc(x, y, z);
			double sin = Math.sqrt(1-cos*cos);
			double r = cos*Math.sqrt(a(x, y, z)/(cos*sin))/d(x, y);
			
			double X = r*(y.x-x.x)+x.x;
			double Y = r*(y.y-x.y)+x.y;
			return new p(X, Y);
		}
		
		else
		{
			double cos = loc(y, x, z);
			double sin = Math.sqrt(1-cos*cos);
			double r = cos*Math.sqrt(a(x, y, z)/(cos*sin))/d(x, y);
			
			double X = r*(x.x-y.x)+y.x;
			double Y = r*(x.y-y.y)+y.y;
			return new p(X, Y);
		}
	}
	
	public static double loc(p a, p b, p c)
	{
		double A = d(a, b);
		double B = d(c, b);
		double C = d(a, c);
		
		return (A*A+C*C-B*B)/(2*A*C);
	}
	
	public static double a(p a, p b, p c)
	{
		return 0.5*Math.abs(a.x*b.y+b.x*c.y+c.x*a.y-a.y*b.x-b.y*c.x-c.y*a.x);
	}
	
	public static double d(p a, p b)
	{
		return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
	}
	
	public static class p
	{
		double x, y;
		
		p(double xx, double yy)
		{
			x =xx; y= yy;
		}
	}
	
}
