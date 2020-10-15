package round479Div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class F {
	public static void main(String[] args) {
		FastReader scan = new FastReader();
		PrintWriter out = new PrintWriter(System.out);
		
		new F().solve(scan,out);
		out.close();
	}

	static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
	
	private void solve(FastReader scan, PrintWriter out) {
		int N = scan.nextInt();
		HashMap<Integer, Integer> sums = new HashMap<>();
		HashMap<Integer, TreeSet<Integer>> inds = new HashMap<>();
		
		int x;
		int max = 1;
		int maxNum = -1;
		int cumSum = 1;
		for(int n=0; n<N; n++) {
			x = scan.nextInt();
			
			if(!inds.containsKey(x)) {
				inds.put(x, new TreeSet<>());
			}
			inds.get(x).add(n);
			
			cumSum = sums.getOrDefault(x-1,0) + 1;
			sums.put(x, cumSum);
			
			if(cumSum > max) {
				max = cumSum;
				maxNum = x;
			}
		}
		if(max==1) {
			out.println(1);
			out.println(1);
		} else {
			out.println(max);
			
			int[] output = new int[max];
			int ind = inds.get(maxNum).floor(N);
			output[max-1] = ind+1;
			maxNum--;
			
			for(int i=max-2; i>=0;i--) {
				ind = inds.get(maxNum).floor(ind);
				output[i] = ind+1;
				maxNum--;
			}
			for(int i:output) {
				out.print(i + " ");
			}
			
		}
	}
}