import java.io.PrintWriter;
import java.util.HashSet;
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
		int K = in.nextInt();
		HashSet<Integer> ranks = new HashSet<>();
		HashSet<Integer> indecies = new HashSet<>();
		int[] studetns = new int[N];
		int count = 0;
		for(int n=0; n<N; n++) {
			int x = in.nextInt();
			studetns[n]=x;
			if(!ranks.contains(x) && count < K) {
				ranks.add(x);
				indecies.add(n+1);
				count++;
			}
			if(count>=K) {
				break;
			}
		}
		if(count>=K) {
			out.println("YES");
			for(int key: indecies) {
				out.print(key + " ");
			}
			return;
		}
		out.println("NO");
	}
}
