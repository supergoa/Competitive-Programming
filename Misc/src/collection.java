// Arup Guha
// 9/23/2014
// Simulation of collecting randomly generated individual items until a whole set is obtained.

import java.util.*;

public class collection {
	
	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		Random r = new Random();
		
		// Get total number of items to get.
		System.out.println("How many items in the set? (30 or fewer, please)");
		int n = stdin.nextInt();
		
		// Keep on going until all 1s are in the mask.
		int count = 0, mask = 0;
		while (mask != ((1 << n) - 1)) {
			
			// Get this item, use or to keep track of total collection.
			int item = r.nextInt(n);
			System.out.println("Got item "+item);
			mask = mask | (1 << item);
			count++;
		}
		System.out.println("Took "+count+" turns to collect all items in the set.");
	}	
}