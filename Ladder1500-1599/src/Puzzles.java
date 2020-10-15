import java.util.Arrays;
import java.util.Scanner;

public class Puzzles {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		int[] pieces = new int[M];
		for(int m=0; m<M;m++) {
			pieces[m] = in.nextInt();
		}
		Arrays.sort(pieces);
		int min = Integer.MAX_VALUE;
		for(int i=0; i<M-N+1; i++) {
			min = Math.min(min, pieces[N-1+i]-pieces[i]);
		}
		System.out.println(min);
		in.close();
	}
}
