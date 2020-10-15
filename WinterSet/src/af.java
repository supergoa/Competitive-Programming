import java.io.PrintWriter;
import java.util.Scanner;

public class af {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new af().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		
		for(int i=0; i<(1<<10); i++) {
			//System.out.println(i);
			if(valid(i)) {
				System.out.println(Integer.toBinaryString(i));
				//return;
			}
		}
		
	}

	private boolean valid(int i) {
		if(Integer.bitCount(i)!=5) return false;
		int cur = 0;
		for(int j=10; j>=0; j++) {
			if(((1<<j)&i) ==0) cur++;
			else cur--;
			if(cur<0) return false;
		}
		return true;
	}
}
