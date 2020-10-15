import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class AlmostArth {
	public static void main(String[] args) {
		FastReader in = new FastReader();
		PrintWriter out = new PrintWriter(System.out);
		new AlmostArth().solve(in,out);
		out.close();
	}

	/*public int[] copy(int[] arr) {
		int[] copyNum = new int[arr.length];
		for(int a=0; a<arr.length; a++) {
			copyNum[a] = arr[a];
		}
		return copyNum;
	}*/
	private void solve(FastReader in, PrintWriter out) {
		int N = in.nextInt();
		
		int[] nums = new int[N];
		HashMap<Integer,Integer> differences = new HashMap<>();
		nums[0] = in.nextInt();
		
		for(int i=1; i<N; i++) {
			nums[i] = in.nextInt();
			int diff = nums[i]-nums[i-1];
			differences.put(diff, differences.getOrDefault(diff,0));
		}
		
		//copy nums
		
		
		if(differences.keySet().size()>5) {
			//System.out.println("hello0");
			out.println(-1);
		} else {
			
			int minDiff = Integer.MAX_VALUE;
			int maxDiff = Integer.MIN_VALUE;
			for(int diff:differences.keySet()) {
				minDiff = Math.min(minDiff, diff);
				maxDiff =  Math.max(maxDiff, diff);
			}
			int minmaxdiff = maxDiff-minDiff;
			if(Math.abs(minmaxdiff) > 15) out.println(-1);
			
			else {
				for(int i=0; i<5-minmaxdiff; i++) {
					maxDiff++;
					minDiff--;
				}
				
				int minChanges = Integer.MAX_VALUE;
				for(int diff=minDiff; diff<=maxDiff; diff++){
					for(int i=0; i<3; i++) {
						
						int[] copyNum = nums.clone();
						int changes = 0;
					
						if(i==1) {
							copyNum[0]++;
							changes++;
						}
						if(i==2) { 
							copyNum[0]--;
							changes++;					
						}
						
						for(int j=1; j<N; j++ ) {
							int curVal = copyNum[j];
							boolean found = false;
							
							// Don't change
							if(curVal-copyNum[j-1] == diff) {
								found = true;
							}
							// Add 1
							if(curVal+1-copyNum[j-1] == diff) {
								found = true;
								copyNum[j]++;
								changes++;
							}
							// Subtract 1
							if(curVal-1-copyNum[j-1] == diff) {
								found = true;
								copyNum[j]--;
								changes++;
							}
									
							if(!found) {
								break;
							}
							
							if(j==N-1) {
								minChanges = Math.min(minChanges, changes);
							}
						}
					}
				}
				if(N==1) {
					out.println(0);
				}
				else if(minChanges==Integer.MAX_VALUE) {
					out.println(-1);
				} else {
					out.println(minChanges);
				}
			}
		}
		
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
}
