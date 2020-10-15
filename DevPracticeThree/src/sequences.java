import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class sequences {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new sequences().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int size = in.nextInt();
			int[] newArr = new int[size];
			int[] oldArr = new int[size];
			int operations = in.nextInt();
			for(int s=0; s<size; s++) {
				oldArr[s] = in.nextInt();
			}
			//System.out.println(Arrays.toString(oldArr));
			
			for(int i=0; i<operations; i++) {
				for(int s=0; s<size-1 ; s++) {
					newArr[s] = oldArr[s] + oldArr[s+1];
					//System.out.println(newArr[s] + " " + oldArr[s] + " " + oldArr[s+1]);
				}
				size--;
				oldArr = newArr.clone();
			}
			for(int i=0; i<size; i++) {
				out.print(oldArr[i] + " ");
			}
			out.println();
		}
		
	}
}
