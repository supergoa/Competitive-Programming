import java.io.PrintWriter;
import java.util.Scanner;

public class CielAndFlowers {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new CielAndFlowers().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		long R = in.nextInt();
		long G = in.nextInt();
		long B = in.nextInt();
		
		long ans2 = 0;
		
		ans2+=Math.max(Math.min(R, Math.min(G,B))-1,0);
		long R1=R - ans2;
		long G1=G - ans2;
		long B1=B - ans2;
		ans2+= Math.max(R1/3 + G1/3 + B1/3, Math.min(R1%3, Math.min(G1%3 , B1%3)));
		
		long ans1 = 0;
		long ans3 = 0;
		
		ans1+=R/3+G/3+B/3;
		//System.out.println(ans1);
		long R2=R-3*(R/3);
		long G2=G-3*(G/3);
		long B2=B-3*(B/3);
		ans1+=Math.min(R2, Math.min(G2,B2));
		
		ans3+=Math.min(R, Math.min(G,B));
		R-=ans3;
		G-=ans3;
		B-=ans3;
		ans3+= R/3 + G/3 + B/3;
		out.println(Math.max(ans1, Math.max(ans2, ans3)));
	}
}
