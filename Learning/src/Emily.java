
public class Emily {
	public static void main(String[] args) {
		new Emily().start();
	}
	public void start() {
		BIT bit = new BIT(100);
		bit.add(1, 1);
		bit.add(2, 4);
		System.out.println(bit.sum(1));
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
