import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class J {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new J().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		HashMap<String, Integer> dom = new HashMap<>();
		HashMap<String, Integer> kat = new HashMap<>();
		for(int n=0; n<N; n++) {
			String line = in.next();
			dom.put(line, dom.getOrDefault(line, 0)+1);
		}
		for(int n=0; n<N; n++) {
			String line = in.next();
			kat.put(line, kat.getOrDefault(line, 0)+1);
		}
		int ans = 0;
		for(String a:dom.keySet()) {
			if(kat.containsKey(a)) {
				ans += Math.min(kat.get(a), dom.get(a));
			}
		}
		out.println(ans);
	}
}
