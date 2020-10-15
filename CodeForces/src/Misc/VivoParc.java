package Misc;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class VivoParc {
	static PrintWriter out;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out = new PrintWriter(System.out);
		
		new VivoParc().solve(in, out);
		in.close();
		out.close();
	}

	int N;
	HashMap<Integer, HashSet<Integer>> adj;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		adj = new HashMap<>();

		while(in.hasNext()) {
			String[] line = in.next().split("-");
			int x = Integer.parseInt(line[0])-1;
			int y = Integer.parseInt(line[1])-1;
			if(x==-1 && y==-1) break;
			
			if(!adj.containsKey(x)) adj.put(x, new HashSet<>());
			if(!adj.containsKey(y)) adj.put(y, new HashSet<>());
			adj.get(Math.max(x, y)).add(Math.min(x, y));
			//adj.get(y).add(x);
			
		}
		
		int[] perm = new int[N];
		Arrays.fill(perm, -1);
		backtrack(0, perm);
	}

	private boolean backtrack(int node, int perm[]) {
		if(node==N) {
			for(int i=0; i<perm.length; i++) {
				out.println((i+1)+" "+(perm[i]+1));
			}
			return true;
		}
		for(int i=0; i<4; i++) {
			if(!conflict(i, node, perm)) {				
				perm[node]= i;
				if(backtrack(node+1, perm)) {
					return true;
				}
				perm[node]= -1;
			}
		}
		return false;
	}


	private boolean conflict(int i, int node, int[] perm) {
		for(int a : adj.get(node)) {
			if(i==perm[a]) {
				return true;
			}
		}
		return false;
	}
}

