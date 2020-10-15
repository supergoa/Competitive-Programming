import java.io.PrintWriter;
import java.util.Scanner;

public class I {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new I().solve(in, out);
		in.close();
		out.close();
		
	}

	private void solve(Scanner in, PrintWriter out) {
		in.nextLine();
		int N = in.nextInt();
		String[] input = new String[N];
		int length = 0;
		for(int n=0; n<N; n++) {
			while(input[n]==null || input[n].equals("")) {
				input[n] = in.nextLine();
			}
			String[] line = input[n].split(", ");
			length = Math.max(length, line.length);
		}
		int minDifferences = Integer.MAX_VALUE;
		int[] diffInput = new int[N];
		for(int i=0; i<N; i++) {
			String[] line1 = input[i].split(", ");
			int maxDiff = 0;
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				
				String[] line2 = input[j].split(", ");
				int differences = 0;
				
				for(int k=0; k<length; k++) {
					if(!line1[k].equals(line2[k])) {
						differences++;
					}
				}
				maxDiff = Math.max(maxDiff, differences);
				
			}
			diffInput[i] = maxDiff;
			minDifferences = Math.min(minDifferences, maxDiff);
		}
		for(int i=0; i<N; i++) {
			if(diffInput[i]==minDifferences) {
				out.println(input[i]);
			}
		}
	}
}
