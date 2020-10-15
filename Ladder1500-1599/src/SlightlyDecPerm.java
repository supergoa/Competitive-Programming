import java.util.Scanner;

public class SlightlyDecPerm {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		for(int i=1; i<N-K; i++) {
			System.out.print(i + " ");
		}
		for(int i=N; i>=N-K; i--) {
			System.out.print(i + " ");
		}
	}
}
