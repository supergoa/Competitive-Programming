import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new D().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int K = in.nextInt();
		long S = in.nextInt();
		
		int curHouse = 1;
		ArrayList<Integer> moves = new ArrayList<>();
		while(S>0) {
			if(S-K<=N) {
				S-=K;
				curHouse = K;
			} else {
				
			}
		}
	}
}
