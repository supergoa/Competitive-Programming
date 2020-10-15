import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Citystate {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("citystate.in"));
		PrintWriter out = new PrintWriter(new File("citystate.out"));
		new Citystate().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		HashMap<String, Integer> counts = new HashMap<>();
		for(int n=0; n<N; n++) {
			String city = in.next().substring(0, 2);
			String state = in.next();
			if(!city.equals(state)) {
				String key = city + state;
				counts.put(key, counts.getOrDefault(key, 0)+1); 
			}
		}
		int sum = 0;
		HashSet<String> used = new HashSet<>();
		for(String key:counts.keySet()) {
			
			String adjKey = key.substring(2,4) + key.substring(0,2);
			if(!used.contains(key) && !used.contains(adjKey)) {
				int min = Math.min(counts.getOrDefault(key,0), counts.getOrDefault(adjKey,0));
				int max = Math.max(counts.getOrDefault(key,0), counts.getOrDefault(adjKey,0));
				sum += min*max;
				used.add(key);
				used.add(adjKey);
			}
		}
		out.println(sum);
		
	}
}
