import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FoxandNames {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new FoxandNames().solve(in,out);
		in.close();
		out.close();
		
	}
	class Letter {
		Character upper, lower;
		Character c;
		public Letter(Character a, Character b, Character c) {
			this.c = a;
			lower = b;
			upper = c;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		ArrayList<ArrayList<Letter>> orders = new ArrayList<>();
		for(int i=0; i<100; i++) orders.add(new ArrayList<>());
		
		String[] arr = new String[N];
		for(int n=0; n<N; n++) {
			arr[n] = in.next();
		}
		for(int n=1; n<N; n++) {
			String str1 = arr[n-1];
			String str2 = arr[n];
			Character c1 = null;
			Character c2 = null;
			int len = Math.min(str1.length(), str2.length());
			for(int i=0; i<len; i++) {
				if(i==0 && str1.charAt(i)!=str2.charAt(i)) {
					orders.get(0).add(new Letter(str2.charAt(i), str1.charAt(i), null));
						for(int l=0; l<orders.get(0).size(); l++) {
							if(orders.get(0).get(l).c.charValue() == str1.charAt(i)) {
								if(orders.get(0).get(l).upper!=null) {
									
								} else {
									orders.get(0).get(l).upper = str2.charAt(i);
								}
							}
						}
				}
				else if(str1.charAt(i)!=str2.charAt(i)) {
					boolean found = false;
					for(ArrayList<Letter> list: orders) {
						for(Letter l : list) {
							if(l.c.charValue() == str1.charAt(i)) {
								found = true;
								break;
							}
							else if(l.c.charValue() == str2.charAt(i)) {
								found = true;
								break;
							}
						}
						if(found) break;
					}
					break;
				}
			}
		}
	}
}
