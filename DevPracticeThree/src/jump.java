import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class jump {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new jump().solve(in,out);
		in.close();
		out.close();
	}



	ArrayList<int[]> perms;
	int[] mainPerm;
	private void solve(Scanner in, PrintWriter out) {
		
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int B = in.nextInt();
			perms = new ArrayList<>();
			
			mainPerm = new int[B];
			for(int b=0; b<B; b++) 
				mainPerm[b] = in.nextInt();
			
			fillPerms(new boolean[B], new int[B], 0);
			int count = 0;
			
			int U = in.nextInt();
			int D = in.nextInt();
			for(int[] p:perms) {
				boolean good = true;
				for(int i=1; i<B; i++) {
					if(p[i]>p[i-1]) {
						if(p[i]-p[i-1] > U) {
							good = false;
							break;
						}
					} else {
						if(p[i-1]-p[i] > D) {
							good = false;
							break;
						}
					}
				}
				if(good) count++;
			
			}
			out.println(count);
		}
	}

	private void fillPerms(boolean[] used, int[] perm, int ind) {
		if(ind == perm.length) {
			perms.add(perm.clone());
			return;
		}
		for(int i=0; i<perm.length; i++) {
			if(!used[i]) {
				int prev = perm[ind];
				perm[ind] = mainPerm[i];
				used[i] = true;
				fillPerms(used, perm, ind+1);
				used[i] = false;
				perm[ind] = prev;
			}
		}
		
	}
}
