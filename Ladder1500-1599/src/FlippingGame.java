import java.io.PrintWriter;
import java.util.Scanner;

public class FlippingGame {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new FlippingGame().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int[] line = new int[in.nextInt()];
		for(int i=0; i<line.length; i++) line[i] = in.nextInt();
		int numOnes=0;
		int maxCount = 0;
	
		for(int i=0; i<line.length; i++) {
			int count = 0;
			int maxLoopCount = 0;
			for(int j=i; j<line.length; j++) {
				if(line[j]==0) {
					count++;
				} else {
					count--;
				}
				maxLoopCount = Math.max(count, maxLoopCount);
			}
			maxCount = Math.max(maxCount, maxLoopCount);
			if(line[i]==1) numOnes++;
		}
		
		if(maxCount==0) {
			System.out.println(numOnes-1);
		} else {
			System.out.println(maxCount + numOnes);
		}
	}
}
