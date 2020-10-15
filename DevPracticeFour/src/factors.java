import java.io.PrintWriter;
import java.util.Scanner;

public class factors {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new factors().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			long D = in.nextInt();
			long smallest = Long.MAX_VALUE;
			for(int i=0; i<64; i++) {
				for(int j=0; j<64; j++) {
					long num = Long.MAX_VALUE;
					if((i+1)*(j+1)==D) num = (long) (Math.pow(2, i)*Math.pow(3, j));
					if(num<smallest) smallest = num;
				}
			}
			System.out.println(smallest);
		}
		
	}
}
