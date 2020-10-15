import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class candy {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new candy().solve(in,out);
		in.close();
		out.close();
	}

	int[] cal;
	int N;
	int[] price;
	//HashMap<Double, HashMap<Integer, Integer>> memo;
	long[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			N = in.nextInt();
			
			int M = (int) (in.nextDouble()*100);
			//System.out.println(M);
			if(N==0 && M==0) break;
			//memo = new HashMap<>();
		//	mID = new HashMap<>();
			cal = new int[N];
			price = new int[N];
			memo = new long[M+1][N];
			for(int i=0;i<M+1; i++) {
				Arrays.fill(memo[i], -1);
			}
			for(int n=0; n<N; n++) {
				cal[n] = in.nextInt();
				price[n] = (int) (in.nextDouble()*100);
			}
			
			long ans = dp(M,0);
			System.out.println(ans);
		}
		
	}
	private long dp(int m, int i) {
		if(m<0) return -987654321;
		if(i==N) return 0;
		
		//if(memo.get(m)==null) memo.put(m, new HashMap<>());
		//if(!memo.get(m).containsKey(i)) memo.get(m).put(i, -1);
		//else return  memo.get(m).get(i);
		if(memo[m][i]!=-1) return memo[m][i];
		//int mid = mID.get(m);
		//if(memo[mid][i] != -1) return memo[mid][i];
		long choose = cal[i] + dp(m-price[i],i);
		long leave = dp(m, i+1);
		//memo.get(m).put(i,  (int) Math.max(choose, leave));
		return memo[m][i] = Math.max(choose, leave);
	}
}
