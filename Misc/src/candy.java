// Arup Guha
// 9/23/2014
// Example iterating through subsets using bitwise operators.

import java.util.*;

public class candy {
	
	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		System.out.println("How many candy bars do you have?\n");
		int n = stdin.nextInt();

		// Item names, sorted.
		System.out.println("Enter each one, one per line, no spaces.");
		String[] items = new String[n];
		for (int i=0; i<n; i++)
			items[i] = stdin.next();
			
		// Loop through all masks, only looking at those with k bits on.
		for (int mask=0; mask<(1 << n); mask++) {
					
			// Go through each bit and print items for the ones that are on.
			for (int i=0; i<n; i++)
				if (((mask >> i) & 1) == 1) 
					System.out.print(items[i]+" ");
			System.out.println();
		}
	}
} 