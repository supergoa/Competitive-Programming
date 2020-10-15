import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class B {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		String sub = in.next();
		String str = in.next();
		int[][] cumsumsU = new int[26][str.length()];
		int[][] cumsumsL = new int[26][str.length()];
		for(int c = 0; c<str.length(); c++) {
			char ch = str.charAt(c);
			if(Character.isLowerCase(ch)) {
				cumsumsL[ch-'a'][c] = 1;
				if(c>0) cumsumsL[ch-'a'][c] += cumsumsL[ch-'a'][c-1];
			} else {
				cumsumsU[ch-'a'][c] = 1;
				if(c>0) cumsumsU[ch-'a'][c] += cumsumsU[ch-'a'][c-1];
			}
		}
		for(int n=0; n<N; n++) {
			int l = in.nextInt()-1;
			int r = in.nextInt()-1;
			for(int ch = 0; ch<sub.length(); ch++) {
				
			}
		}
	}
}
