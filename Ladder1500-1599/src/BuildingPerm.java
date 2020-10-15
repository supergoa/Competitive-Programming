import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BuildingPerm {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new BuildingPerm().solve(in,out);
		out.close();
	}

	private void solve(InputReader in, PrintWriter out) {
		int N = in.nextInt();
		TreeMap<Integer, Integer> tm = new TreeMap<>();
		for(int n=0; n<N;n++) {
			int x = in.nextInt();
			tm.put(x, tm.getOrDefault(x, 0)+1);
		}
		long ans = 0;
		int ind = 1;
		for(int a:tm.keySet()) {
			int numLeft = tm.get(a);
			while(numLeft > 0) {
				ans += Math.abs(ind-a);
				ind++;
				numLeft--;
			}
		}
		System.out.println(ans);
	}
	static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
