import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class OddEvenSubarrays {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new OddEvenSubarrays().solve(in,out);
		in.close();
		out.close();
	}

	long shift = 200001;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = (in.nextInt()%2==0)?-1:1;
		}
		
		HashMap<Long, Long> counts = new HashMap<>();
		//counts.put(0,1);
		long sum = 0;
		long ans = 0;
		for(int i=0; i<N; i++) {
			sum += arr[i];
			if(sum==0) ans++;
			ans += counts.getOrDefault(sum+shift,0L);
			counts.put(sum+shift,counts.getOrDefault(sum+shift,0L)+1L);
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
