import java.util.Scanner;

public class Fence {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		int[] arr = new int[N+1];
		
		arr[0] = 0;
		for(int n=1; n<=N; n++) {
			arr[n] = in.nextInt() + arr[n-1];
		}
		int idx = -1;
		int best = Integer.MAX_VALUE;
		for(int n=K; n<=N; n++) {
			int sum = arr[n]-arr[n-K];
			//System.out.println(n+ " " +sum + " ");
			if(sum<best) {
				best = sum;
				idx = n-K;
			}
		}
		System.out.println(idx+1);
		in.close();
	}
}
