import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class square {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("square.in"));//System.in);
		PrintWriter out = new PrintWriter(new File("square.out"));
		new square().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int x1 = in.nextInt();
		int y1 = in.nextInt();
		int x2 = in.nextInt();
		int y2 = in.nextInt();
		
		int x3 = in.nextInt();
		int y3 = in.nextInt();
		int x4 = in.nextInt();
		int y4 = in.nextInt();
		
		int x = Math.max(Math.max(Math.abs(x4-x1), Math.abs(x4-x2)), Math.max(Math.abs(x2-x1), Math.max(Math.abs(x4-x3), Math.max(Math.abs(x3-x1), Math.abs(x3-x2)))));
		int y = Math.max(Math.max(Math.abs(y4-y1), Math.abs(y4-y2)), Math.max(Math.abs(y2-y1), Math.max(Math.abs(y4-y3), Math.max(Math.abs(y3-y1), Math.abs(y3-y2)))));
		out.println((int)Math.pow(Math.max(x, y),2));
		//System.out.println(Math.max(arg0, arg1));
	}
}
