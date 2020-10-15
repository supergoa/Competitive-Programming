//package FirstCourse;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class TacoBell {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new TacoBell().solve(scan, out);
		
		scan.close();
		out.close();
	}

	ArrayList<String> combos;
	private void solve(Scanner scan, PrintWriter out) {
		int T = scan.nextInt();
		for(int t=0; t<T; t++) {
			int N = scan.nextInt();
			int K = scan.nextInt();
			combos = new ArrayList<String>();
			
			String[] items = new String[N];
			for(int n=0; n<N; n++) {
				items[n] = scan.next();
			}
			Arrays.sort(items);
			
			combine(0, 0, K, items, new String[K]);
			Collections.sort(combos);
			for(int i=0; i<combos.size(); i++) {
				System.out.println(combos.get(i));
			}
		}
		
	}
	
	private void combine(int size, int pos, int k, String[] items, String[] combo) {
		if(items.length-pos < k-size) return;
		if(size == k) {
			
			String str = "";
			for(String s: combo) {
				str+=s+" ";
			}
			str.trim();
			combos.add(str);
			return;
		}
		
		combo[size] = items[pos];
		combine(size+1, pos+1, k, items, combo);
		combine(size, pos+1, k, items, combo);
		
	}
	
}
