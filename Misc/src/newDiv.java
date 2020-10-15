import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class newDiv {
	/*
	Problem: Count the Dividing Pairs (UCF Local 2016)
	Author: Shahidul Islam (Sumon)
	*/

	final static int ARRAY_SZ = 1000001;
	final static int FREQ_SZ = 10000001;

	static int[] a = new int[ARRAY_SZ];
	static int[] freq = new int[FREQ_SZ];

	static long bigMultiply(int a, int b)
	{
	    long res = a;
	    res *= b;
	    return res;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File("C:\\Users\\Nick\\Downloads\\local2016Data (1)\\divide\\divide.in"));
		long start = System.currentTimeMillis();
	    //freopen("divide.in", "r", stdin);
	    //freopen("divide.ans", "w", stdout);
	    int i, j, k, n, p;
	    int limit, max;
	    long count;
	    n = in.nextInt();
	    for(i = 1; i <= n; i++)
	    {
	        System.out.printf("Test case #%d: ", i);
	        p = in.nextInt();
	        //Initialize memories
	        count = max = 0;

	        //Take input, set frequencies and find max
	        for(j = 0; j < p; j++)
	        {
	        	a[j] = in.nextInt();
	            freq[a[j]]++;
	            if(max < a[j])
	                max = a[j];
	        }

	        //Count for (0, Aj)
	        count = bigMultiply(freq[0], p - freq[0]);
	        //Count for (1, Aj)
	        count += bigMultiply(freq[1], p - freq[0] - freq[1]);

	        //Count (Di, Aj) for Di > 1
	        //Consider all numbers, and find the available multiples
	        for(j = 2; j <= max/2; j++)
	        {
	            if(freq[j]>0)
	            {
	                for(k = j + j; k <= max; k += j)
	                {
	                    if(freq[k]>0)
	                        count += bigMultiply(freq[j], freq[k]);
	                }
	            }
	        }
	        System.out.printf("%d\n", count);
	    }
	    System.out.println(System.currentTimeMillis()-start);
	}

}
