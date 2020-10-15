import java.io.PrintWriter;
import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new A().solve(in, out);
		in.close();
		out.close();
		
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		
		String[] dict = new String[N];
		for(int n=0; n<N; n++) {
			dict[n] = in.next();
		}
		
		for(int m=0;m<M; m++) {
			String letters = in.next().toLowerCase();
			boolean noWord = true;
			//System.out.println(letters);
			for(int n=0; n<N; n++) {
				int lastInd = 0;
				int count = 0;
				for(int i=0; i<3; i++) {
					int curLetter = letters.charAt(i);
					int ind = 0;
					//System.out.println(curLetter + "  " + lastInd);
					//System.out.println(dict[n].indexOf(curLetter, lastInd));
					if((ind = dict[n].indexOf(curLetter, lastInd)) != -1) {
						lastInd = ind+1;
						count++;
					} else {
						break;
					}
				}
				if(count==3) {
					out.println(dict[n]);
					noWord = false;
					break;
				}
			}
			if(noWord) {
				out.println("No valid word");
			}
		}
		
	}
}
