import java.io.PrintWriter;
import java.util.Scanner;

public class j {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new j().solve(in,out);
		in.close();out.close();
			
	}

	private void solve(Scanner in, PrintWriter out) {
		int n1 = in.nextInt();
		int n2 = in.nextInt();
		
	}

	static int GCD(int A, int B) {
	    if(B==0)
	        return A;
	    else
	        return GCD(B, A % B);
	}
}
