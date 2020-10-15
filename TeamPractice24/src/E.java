import java.io.PrintWriter;
import java.util.Scanner;

public class E {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new E().solve(in,out);
		in.close();
		out.close();
	}

	int N ;
	double P ;
	double S ;
	double V ;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		P = in.nextDouble();
		S = in.nextDouble();
		V = in.nextDouble();
		//System.out.println(log2fp0(10));
		//System.out.println(func(15.598261092309));
		ts(0,100);
	}
	
	public static double log2(double d) {
	      return Math.log(d)/Math.log(2.0);
	   }
	double func(double c) { 
		return (S*(1.0+1.0/c))/V + N*Math.pow(log2(N),c*Math.sqrt(2))/(P*1e9); 
	}


	void ts(double start, double end)
	{
	    double l = start, r = end;

	    for(int i=0; i<20000; i++) {
	      double l1 = (l*2+r)/3;
	      double l2 = (l+2*r)/3;
	     
	      if(func(l1) < func(l2))
	    	  r = l2; 
	      else 
	    	  l = l1;
	    }

	    double x = r;
	    System.out.println(func(x) + " " + x);
	    ;
	}
}
