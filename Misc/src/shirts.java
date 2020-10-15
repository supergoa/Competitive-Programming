import java.io.PrintWriter;
import java.util.Scanner;

public class shirts {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new shirts().solve(in,out);
		in.close();
		out.close();
	}

	int[] shirts;
	char[] T;
	int N;
	Boolean memo[][];
	private void solve(Scanner in, PrintWriter out) {
		T = (in.nextInt()+"").toCharArray();
		N = in.nextInt();
		shirts = new int[N];
		for(int n=0; n<N; n++) {
			shirts[n] = in.nextInt();
		}
		memo = new Boolean[3][(1<<N)];
		System.out.println(dp(0, (1<<N)-1, 0) ? "1" : "0");
	}
	private boolean dp(int i, int mask, int used) {
		if(i>=T.length) return true;
		if(memo[used][mask] != null) return memo[used][mask];

		boolean ans = false;
		if(used==0 && T[i]!='0') {
			// need different transitions for using my one move one ahead vs two ahead
			// because they are two different states. (i.e. if we always passed 1, states would not be unique)
			ans |= dp(i+1, mask, 1); if(ans) return memo[1][mask] = true;
			if(!ans) ans |= dp(i+2, mask, 2); if(ans) return memo[2][mask] = true;
			if(ans) return memo[1][mask] = true;
		}
		outer: for(int a=0; a<N; a++) {
			if(ans) break;
			if((mask & (1<<a)) == 0) continue;
			
			// if backtracking instead of dp, need below
			//for(int j=0; j<a; j++) {
			//	if((mask & (1<<j)) == 0) continue;
			//	if(shirts[a]==shirts[j]) continue outer;
			//}
			if(shirts[a]>=10) {
				if(i+2 > T.length) continue;
				int dig2 = shirts[a]%10;
				int dig1 = shirts[a]/10;
				
				if(dig1 == T[i]-'0' && dig2== T[i+1]-'0') ans |= dp(i+2, mask-(1<<a), used);
			} else if(shirts[a] == T[i]-'0') 
				ans |= dp(i+1, mask-(1<<a), used);
		}
		return memo[used][mask] = ans;
	}
}
