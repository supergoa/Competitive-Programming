import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class FoxAndBox {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new FoxAndBox().solve(in,out);
		in.close();
		out.close();
	}

	int[] boxes;
	int N;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		
		boxes = new int[N];
		
		for(int n=0; n<N; n++) {
			boxes[n] = in.nextInt();
		}
		Arrays.sort(boxes);
		//Collections.reverse(boxes);
		//System.out.println(boxes);
		int ans = 0;
		
		boolean[] used = new boolean[N];
		int numUsed = 0;
		while(numUsed<N) {
			int height = 0;
			for(int i=0; i<boxes.length; i++) {
				if(boxes[i]>=height && !used[i]) {
					//System.out.println("+1");
					height++;
					numUsed++;
					used[i] = true;
				}
			}
			if(height>0) {
				//System.out.println("done");
				ans++;
			}
		}
	
		System.out.println(ans);
	}

}
