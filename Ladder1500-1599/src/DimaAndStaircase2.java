import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class DimaAndStaircase2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new DimaAndStaircase2().solve(in,out);
		in.close();
		out.close();
	}
//
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int[] arr = new int[N];
		for(int n=0; n<N; n++) {
			arr[n] = in.nextInt();
		}
		long frontHeight = arr[0];
		int M = in.nextInt();
		for(int m=0; m<M;m++) {
			int w = in.nextInt();
			int h = in.nextInt();
			int stairheight = arr[w-1];
			if(stairheight>frontHeight) {
				out.println(stairheight);
				frontHeight = stairheight+h;
			} else {
				out.println(frontHeight);
				frontHeight += h;
			}
			
		}
	}
}