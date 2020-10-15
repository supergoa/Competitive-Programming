import java.util.Arrays;
import java.util.Scanner;

public class airport {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new airport().solve(in);
		in.close();
	}
	
	private void solve(Scanner in) {
		int N = in.nextInt();
		int K = in.nextInt()-1;
		
		int[] arr = new int[N];
		for(int n=0; n<N; n++) {
			arr[n] = in.nextInt();
		}
		Arrays.sort(arr);
		
		int low = 0;
		int high = (int) 1e9;
		while(low<=high) {
			int mid = (low+high)/2;
			int parts = 0;
			int sum = 0;
			for(int n=1; n<N; n++) {
				int diff = arr[n]-arr[n-1];
				sum+=diff;
				if(sum > mid) {
					parts++;
					sum = 0;
				}
			}
			if(parts>K) {
				low = mid+1;
			} else {
				high = mid-1;
			}
		}
		System.out.println(low);
	}
}
