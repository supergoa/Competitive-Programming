import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in, out);
		in.close();
		out.close();
	}

	class Pair {
		int k, indToRemove;
		public Pair(int a, int b) {
			this.k = a;
			this.indToRemove = b;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int K = in.nextInt();
		int[] sums = new int[K];
		HashMap<Integer, HashMap<Integer, Integer>> lastInds = new HashMap<>();
		HashMap<Integer, Pair> answers = new HashMap<>();
		for(int k=0; k<K; k++) {
			int size = in.nextInt();
			int sum=0;
			for(int i=0; i<size; i++) {
				int x = in.nextInt();
				sum+=x;
				if(lastInds.get(k)==null) {
					lastInds.put(k, new HashMap<Integer, Integer>());
				}
				HashMap<Integer, Integer> tempHM = lastInds.get(k);
				tempHM.put(x,i);
				lastInds.put(k, tempHM);
			}
			sums[k]=sum;
			HashMap<Integer, Integer> tempHM = lastInds.get(k);
			for(int a: tempHM.keySet()) {
				int ans = sums[k]-a;
				if(answers.containsKey(ans)) {
					//found ans
					out.println("YES");
					out.println((k+1) + " " + (tempHM.get(a)+1));
					Pair ansPair = answers.get(ans);
					out.println((ansPair.k+1) + " " + (ansPair.indToRemove+1));
					return;
				} else {
					answers.put(ans, new Pair(k, tempHM.get(a)));
				}
			}
		}
		out.print("NO");
	}
}
