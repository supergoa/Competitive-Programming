import java.io.PrintWriter;
import java.util.Scanner;

public class IlyaQueries {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new IlyaQueries().solve(in,out);
		in.close();
		out.close();
	}
	private void solve(Scanner in, PrintWriter out) {
		String s = in.next();
		
		BIT bit = new BIT(s.length());
		for(int i=2; i<=s.length(); i++) {
			//System.out.println("wtf2");
			if(s.charAt(i-2)==s.charAt(i-1)) {
				bit.add(i-1, 1);
			}
		}
		int M = in.nextInt();
		for(int m=0; m<M; m++) {
			int l = in.nextInt();
			int r = in.nextInt();
			out.println(bit.sum(l, r-1));
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


