import java.io.PrintWriter;
import java.util.Scanner;

public class binary {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new binary().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int base = in.nextInt();
			String num = in.next();
			int count = 0;
			int base10 = 0;
			for(int i=num.length()-1; i>=0; i--) {
				if(num.charAt(i)=='1') {
					base10 += (1<<count);
					//System.out.println("ay");
				}
				count++;
				//System.out.println(base10);
			}
			System.out.println(Integer.toString(base10, base));
		}
		
	}
}
