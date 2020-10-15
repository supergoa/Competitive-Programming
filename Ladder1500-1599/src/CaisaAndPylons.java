import java.util.Scanner;

public class CaisaAndPylons {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cumsum=0;
		int ans = 0;
		int N = in.nextInt();
		int[] arr = new int[N+1];
		arr[0] = 0;
		for(int n=1;n<=N; n++) {
			arr[n] = in.nextInt();
			cumsum += -arr[n]+arr[n-1];
			if(cumsum<0) {
				ans+=-cumsum;
				cumsum=0;
			}
		}
		System.out.println(ans);
		in.close();
	}
}
