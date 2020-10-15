import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class morecombos {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new morecombos().solve(in,out);
		in.close();
		out.close();
	}

	HashSet[] bags;
	int B;
	int[][][] memo;
	HashMap<Integer, Integer> candyId;
	private void solve(Scanner in, PrintWriter out) {
		//System.out.println((1<<19)+1);
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			B = in.nextInt();
			int K = in.nextInt();
			//System.out.println(B + " " + K);
			bags = new HashSet[B];
			for(int b=0; b<B; b++) bags[b] = new HashSet<>();
			//System.out.println("here");
			candyId = new HashMap<>();
			
			/*memo = new int[(1<<19)+1][21][21];
			for(int i=0; i<(1<<19)+1; i++) {
				for(int j=0; j<21; j++) {
					Arrays.fill(memo[i][j], -1);
				}
				//System.out.println("imstuck");
			}*/
			//System.out.println("here2");
			for(int b=0; b<B; b++) {
				int M = in.nextInt();
				//bags[b] = new int[M];
				for(int m=0; m<M; m++) {
					int x = in.nextInt()-1;
					
					if(candyId.get(x)==null) candyId.put(x, candyId.size());
					bags[b].add(candyId.get(x));
				}
				//System.out.println(bags[b]);
			}
			int maxCandy = 0;
			HashSet<Integer> curCandies = new HashSet<>();
			for(int mask=0; mask<(1<<B); mask++) {
				if(Integer.bitCount(mask)!=K) continue;
				curCandies.clear();
				for(int i=0; i<B; i++) {
					if((mask & (1<<i)) == 0) continue;
					curCandies.addAll(bags[i]);
				}
				maxCandy = Math.max(maxCandy, curCandies.size());
			}
			out.println(maxCandy);
		}
		
	}
	/*
	 * 3
3 2
4 1 1 1 1
2 2 3
2 4 5
4 1
10 6 5 5 5 4 6 5 5 5 4
4 1 2 3 4
5 2 2 2 3 3
6 7 7 1 1 3 3
	 */
	/*private int dp(int mask, int ind, int left) {
		if(left==0 || ind >=B) return Integer.bitCount(mask);
		
		if(memo[mask][ind][left]!=-1) return memo[mask][ind][left];
		
		int choose = 987654321;
		int tempMask = mask;
		for(int m=0; m<bags[ind].length; m++) {
			tempMask |= (1<<bags[ind][m]);
		}
		choose = dp(tempMask, ind+1, left-1);
		int leave = 987654321;
		leave = dp(mask, ind+1, left);
		
		return memo[mask][ind][left] = Math.max(choose, leave);
	}*/
}
