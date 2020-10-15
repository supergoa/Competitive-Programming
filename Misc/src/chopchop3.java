import java.io.*;
import java.util.*;

public class chopchop implements Runnable
{
	public static void main(String[] args)
	{
		new Thread(null, new chopchop(), "", 1<<28).start();
	}
	
	static int data[], prev[], next[];
	public void run()
	{
		FastScanner in = new FastScanner();
		int numCases = in.nextInt();
		
		for(int l = 1; l <= numCases; l++)
		{
			int n = in.nextInt();
			data = new int[n];
			prev = new int[n];
			next = new int[n];
			Arrays.fill(prev, -1);
			Arrays.fill(next, n);
			HashMap<Integer, Integer> map = new HashMap<>();
			
			for (int i = 0; i < n; i++)
			{
				data[i] = in.nextInt();
				Integer tmp = map.get(data[i]);
				
				if (tmp != null)
				{
					prev[i] = tmp;
					next[tmp] = i;
				}
				
				map.put(data[i], i);
			}
			
			System.out.println(go(0, n - 1) ? 1 : 0);
		}
	}
	
	static boolean go(int l, int r)
	{
		if (l >= r)
			return true;
		
		for (int i = l, j = r; i <= j; i++, j--)
		{
			if (prev[i] < l && next[i] > r)
				return go(l, i - 1) && go(i + 1, r);
			else if (prev[j] < l && next[j] > r)
				return go(l, j - 1) && go(j + 1, r);
		}
		
		return false;
	}

	public static class FastScanner
	{
		BufferedReader br;
		StringTokenizer st;

		public FastScanner(String s)
		{
			try {
				br = new BufferedReader(new FileReader(s));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public FastScanner()
		{
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String nextToken()
		{
			while (st == null || !st.hasMoreElements())
			{
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt()
		{
			return Integer.parseInt(nextToken());
		}

		long nextLong()
		{
			return Long.parseLong(nextToken());
		}

		double nextDouble()
		{
			return Double.parseDouble(nextToken());
		}
		
		String next()
		{
			return nextToken();
		}
	}
}
