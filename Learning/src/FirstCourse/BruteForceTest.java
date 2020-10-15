package FirstCourse;

import java.util.Arrays;

public class BruteForceTest {
	static int[] L = {1,2,3,4};
	static int[] values = {1,2,3,4};
	static int[] perm = new int[4];
	public static void main(String[] args) {
		int[] in = new int[4];
		
		 //subsets1(0,0, in);
		//permute2(0,0,4);
		permuteNoSub(values, new boolean[values.length], 0, perm);
	}
	public static void subsets(int p, boolean[] in, int N) {
		if (p == N) {
			// The array ’in’ represents our subset.
			// Do something problem-specific here, maybe print it.
			System.out.println(Arrays.toString(in));
			return;
		}
		
		// L[p] is not in the subset.
		in[p] = false;
		subsets(p + 1, in,N);
		// L[p] is in the subset.
		in[p] = true;
		subsets(p + 1, in, N);
	}
	
	public static void subsets1(int pos, int len, int[] sub) {
		if(pos == 4) {
			
			System.out.println(Arrays.toString(sub));
		// Do something problem-specific with the subset here.
		return;
		}
		// Try the current element in the subset.
		sub[len] = L[pos];
		subsets1(pos+1, len+1, sub);
		
		// Skip the current element.
		sub[len] = -1;
		subsets1(pos+1, len, sub);
		}
	
	public static void permute2(int ind, int used, int N)
    {
        if(ind == N)
        {
            // Do something problem-specific with the permutation. Here I'll just
            // print it.
            for(int k = 0; k < N; k++)
                System.out.printf("%d ", perm[k]);
            System.out.println();
            return;
        }

        // Try all unused elements at this position in the permutation.
        for(int i = 0; i < N; i++)
            if(!on(used, i))
            {
                perm[ind] = values[i];
                permute2(ind+1, set(used, i), N);
            }
    }
	
	// arr = orignal set objs ex. {1,2,3,4}
	public static void permuteNoSub(int arr[], boolean[] used, int size, int[] perm) {
		
		//base case
		if(size == perm.length) {
			System.out.println(Arrays.toString(perm));
			return;
		}	
		
		for(int i=0; i<used.length; i++) {
			
			if(!used[i]) {
				
				// try
				perm[size] = arr[i];
				used[i] = true;
				permuteNoSub(arr, used, size+1, perm);
				used[i] = false;
			}
		}
	}
	/*public static boolean on(int mask, int pos) {
		return ((mask & (1 << pos))==1)?true:false;
	}
	public static int set(int mask, int pos) {
		return (mask | (1 << pos));
	}*/
	
    static boolean on(int mask, int pos)
    {
        return (mask & (1 << pos)) > 0;
    }

    // Set the bit at index pos in mask.
    static int set(int mask, int pos)
    {
        return mask | (1 << pos);
    }

}
