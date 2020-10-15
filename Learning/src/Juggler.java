import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class Juggler {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("C:\\Users\\Nick\\Downloads\\minion\\juggler\\juggler.in"));
		//Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Juggler().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			BIT bit = new BIT(N);
			int[] ballToInd = new int[N+1];
			TreeSet<Integer> ts = new TreeSet<>();
			for(int i=1; i<=N; i++) {
				int x = in.nextInt();
				ballToInd[x] = i;
				ts.add(i);
				bit.add(i, 1);
			}
			Integer curPos = 1;
			long ans = 0;
			for(int i=1; i<=N; i++) {
				Integer toGet = ballToInd[i];
				int lower = Math.min(curPos, toGet);
				int higher = Math.max(curPos, toGet);
				//System.out.println(curPos + " " + toGet);
				int way1 = (int) bit.sum(lower, higher);
				int way2 = (int) (bit.sum(higher,N) + bit.sum(0,lower));
				//System.out.println(way1 + " " + way2);
				//System.out.println();
				bit.add(toGet, -1);
				ans += Math.min(way1, way2);
				ts.remove(toGet);
				curPos = ts.higher(toGet);
				if(curPos == null) curPos = ts.higher(-1);
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
