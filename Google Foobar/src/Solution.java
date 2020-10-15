import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		System.out.println(Arrays.toString(new Solution().answer(outt, N)));
		in.close();
		out.close();
	}

	public static int[] answer(int[] data, int n) 
    {
        HashMap<Integer, Integer> map = new HashMap<>(); 
        ArrayList<Integer> data2 = new ArrayList<Integer>();

        int count=0;

        for(int x:data)
        {
            if(!map.containsKey(x))
            {           
                map.put(x,1);
            }
            else if(map.containsKey(x))
            {
                count=map.get(x);
                count+=1;
                map.put(x,count);
            }
        }

        for(int y:map.keySet())
        {
            if(map.get(y)<=n) {
                data2.add(y);
            }
        }
        int[] data3 = new int[data2.size()];


        for(int i=0;i<data2.size();i++){
            data3[i] = data2.get(i);
        }


        return data3; 
    }
	static int[] outt = {1, 2, 3};
	static int N = 0;
    public static int[] solution(int[] arr, int n) {
        // Count occurrences
		HashMap<Integer, Integer> occurences = new HashMap<>();
		for(int i=0; i<arr.length; i++) {
			occurences.put(arr[i], occurences.getOrDefault(arr[i], 0)+1);
		}
		
		// Calculate new array size
		int totRemove = 0;
		for(int i : occurences.keySet()) {
			if(occurences.get(i) > n) totRemove += occurences.get(i);
		}
		int[] ret = new int[arr.length - totRemove];
		
		// Return the answer
		int index = 0;
		for(int i=0; i<arr.length; i++) {
			if(occurences.get(arr[i]) <= n) {
				ret[index++] = arr[i];
			}
		}
		
		return ret;
    }
}
