import java.io.PrintWriter;
import java.util.Scanner;

public class removeDup {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new removeDup().solve(in,out);
		in.close();
		out.close();
	}
	
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int[] arr = new int[N];
		for(int n=0; n<N;n++) {
			arr[n] = in.nextInt();
			
		}
		boolean[] usedNum = new boolean[1001]; //could use hashset
		boolean[] usedInd = new boolean[N];
		int takenOut = 0;
		for(int n=N-1; n>=0;n--) {
			if(!usedNum[arr[n]]) {
				usedNum[arr[n]] = true;
				usedInd[n] = true;
			} else {
				takenOut++;
			}
		}
		out.println(N-takenOut);
		for(int n=0; n<N;n++) {
			if(usedInd[n]) {
				out.print(arr[n] + " ");
			}
		}
	}
}
