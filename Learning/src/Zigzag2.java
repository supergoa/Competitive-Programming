import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Zigzag2 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Zigzag2().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		BIT bit = new BIT(2000000001);
		bit.add(1999999999, 1);
		System.out.println(bit.sum(0, 2000000001));
		for(int t=0; t<T; t++) {
			int N = in.nextInt();
			BIT incr = new BIT(N);
			BIT decr = new BIT(N);
			
			int last = in.nextInt();
			incr.add(1, 1);
			decr.add(1, 1);
			for(int n=2; n<=N; n++) {
				int x = in.nextInt();
				if(x>last) {
					incr.add(n, 1);
				}
				if(x<last) {
					decr.add(n, 1);
				}
				last = x;
			}
			
			
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
