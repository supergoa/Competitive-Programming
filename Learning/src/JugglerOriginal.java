import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class JugglerOriginal {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("C:\\Users\\Nick\\Downloads\\minion\\juggler\\juggler.in"));
		//Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new JugglerOriginal().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			BIT bit = new BIT(N);
			int[] ballToInd = new int[N+1];
			TreeMap<Integer, Integer> tm = new TreeMap<>();
			for(int i=1; i<=N; i++) {
				int x = in.nextInt();
				ballToInd[x] = i;
				tm.put(i, x);
			}
			
			long ans = 0;
			int curInd = 1;
			for(int i=1; i<=N; i++) {
				int lower = Math.min(ballToInd[i], curInd);
				int higher = Math.max(ballToInd[i], curInd);
				
				long way1 = higher-lower - bit.sum(lower, higher);
				long way2 = N - higher + lower - (bit.sum(higher, N) + bit.sum(1, lower));
				long chose = Math.min(way1, way2);
				ans += chose+1;
				
				bit.add(ballToInd[i], 1);
				tm.remove(ballToInd[i]);
				
				Integer next = tm.higherKey(ballToInd[i]);
				if(next==null) next = tm.higherKey(0);
				if(next==null) break;
				curInd = next;
			}	
			System.out.println(ans);
		}
	}
	
	public class BIT {
	
	    long[] tree;
	    int n;
	
	    public BIT(int n) {
	        this.n = n;
	        tree = new long[n + 1];
	    }
	
	    public void add(int i, long val)
	    {
	        while(i <= n)
	        {
	            tree[i] += val;
	            i += i & -i;
	        }
	    }
	
	    public long sum(int to)
	    {
	        long res = 0;
	        for(int i = to; i >= 1; i -= (i & -i))
	        {
	            res += tree[i];
	        }
	        return res;
	    }
	
	    public long sum(int from, int to) {
	        return sum(to) - sum(from - 1);
	    }
	}

}
