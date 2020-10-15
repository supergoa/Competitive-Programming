import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AboveTheMedian {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new AboveTheMedian().solve(in,out);
		in.close();
		out.close();
	}

	final int shift = 100005;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int X = in.nextInt();
		
		int[] arr = new int[N];
		BIT bit = new BIT(2*shift);	
		
		int ans = 0;
		for(int i=0; i<N; i++) {
			arr[i] = (in.nextInt()>=X)?1:-1;
		}
		bit.add(shift, 1);
		int sum = 0;
		for(int i=0; i<N; i++) {
			sum += arr[i];
			ans += bit.sum(sum+shift);
			bit.add(sum+shift, 1);	
		}
		out.print(ans);
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
