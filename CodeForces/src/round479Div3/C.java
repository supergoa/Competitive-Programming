package round479Div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C {
	public static void main(String[] args) {
		FastReader scan = new FastReader();
		PrintWriter out = new PrintWriter(System.out);
		
		new C().solve(scan, out);
		
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
		int n,k;
		String s = "";
		n = scan.nextInt();
		k = scan.nextInt();
		Integer[] arr = new Integer[n];
		for(int nn=0; nn<n; nn++) {
			arr[nn] = scan.nextInt();
		}
		Arrays.sort(arr);
		if(k==0) {
			if(arr[0]>1) {
				out.println(arr[0]-1);
			} else {
				out.println(-1);
			}
		}
		else if(k==n) {
			out.println(arr[k-1]);
		} else if(arr[k-1].intValue() != arr[k].intValue()) {
			out.println(arr[k-1]);
		} else {
			out.println(-1);
		}
	}
}
