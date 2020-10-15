import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class upwards2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new upwards2().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int k = in.nextInt();
			int l = in.nextInt();
			int r = in.nextInt();
			
			int[] ans = new int[l];
			ans[0] = 0;
			for(int ll=1; ll<l; ll++) {
				for(int i=ans[ll-1]+1; i<26; i++) {
					if(i-ans[ll-1] > k) {
						ans[ll] = i;
						break;
					}
				}
			}
			int moves = 1;
			for(int j=l-1; j>=0; j--) {
				while(ans[j]!=25 && (j==l-1 || ans[j+1]-ans[j] > k+1) && moves<r) {
					ans[j]++;
					moves++;
					
					if(moves==r) break;
				}
				if(moves==r) break;
			}
			
			for(int ll=0; ll<l; ll++) {
				out.print((char)(ans[ll] + 'a'));
			}
			out.println();
		}
		
	}
}
