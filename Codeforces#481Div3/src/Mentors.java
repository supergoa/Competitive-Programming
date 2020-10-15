import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Mentors {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Mentors().solve(in,out);
		in.close();
		out.close();
	}
	
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int K = in.nextInt();
		int[] programmers = new int[N];
		for(int n=0; n<N; n++) {
			programmers[n] = in.nextInt();
		}
		int[] sorted = programmers.clone();
		Arrays.sort(sorted);
		
		TreeMap<Integer, Integer> skills = new TreeMap<>();
		skills.put(0, 0);
		int nextInd = 1;
		for(int n=0; n<N; n=nextInd) {
			int value = sorted[n];
			int startInd = n;
			nextInd = n+1;
			for(int u=n+1; u<N; u++) {
				if(sorted[u]==value) {
					nextInd = u+1;
				} else {
					break;
				}
			}
			if(nextInd != -1) {
				skills.put(value, startInd);
			} else {
				skills.put(value, n);
			}
		}
		
		//for(int a:skills.keySet()) {
		//	System.out.println(a + " " + skills.get(a));
		//}
	///	System.out.println();
		
		HashMap<Integer, ArrayList<Integer>> quarrels = new HashMap<>();
		for(int k=0; k<K; k++) {
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			if(quarrels.get(x)==null) {
				quarrels.put(x, new ArrayList<>());
			}
			if(quarrels.get(y)==null) {
				quarrels.put(y, new ArrayList<>());
			}
			if(programmers[x] > programmers[y]) {
				quarrels.get(x).add(y);
			}
			if(programmers[y] > programmers[x]) {
				quarrels.get(y).add(x);				
			}
		}
		
		for(int n=0; n<N; n++) {
			int curr = programmers[n];
			int quarrs = 0;
			if(quarrels.get(n)!=null) {
				quarrs = quarrels.get(n).size();
			}
			//System.out.println(n + " " + curr + " " + " " + skills.get(curr) + " " + quarrs);
			int ans = skills.get(curr) - quarrs;
			out.print(ans<0?0:ans + " ");
		}
	}
}
