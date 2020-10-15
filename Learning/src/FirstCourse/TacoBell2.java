//package FirstCourse;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class TacoBell2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new TacoBell2().solve(scan, out);
		
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
			
			
			for(int i=0; i< (1<<N); i++) {
				int x = Integer.bitCount(i);
				//System.out.println(Integer.toBinaryString(j));
				if(x==K) {
					int size = 0;
					String[] combo = new String[K];
					for(int j=0; j<N; j++) {
						if((i&(1<<j)) > 0) {
							
							combo[size] = items[j];
							size++;
						}
					}
					Arrays.sort(combo);
					String str = "";
					for(String s: combo) {
						str += s +" ";
					}
					str.trim();
					combos.add(str);
				}
			}
			Collections.sort(combos);
			for(int i=0; i<combos.size(); i++) {
				System.out.println(combos.get(i));
			}
		}
		
	}	
}
