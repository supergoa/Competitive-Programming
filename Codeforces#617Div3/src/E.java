import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class E {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new E().solve(in,out);
		in.close();
		out.close();
	}
	
	class item {
		public item(char a, int b) {
			c = a;
			val = b;
		}
		char c;
		int val;
		
		@Override
		public String toString() {
			return ""+val;
		}
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		String str = in.next();
		
		char[] sort = (str.toCharArray());
		Arrays.sort(sort);
		String sorted = "";
		for(int i=0; i<N; i++) sorted += sort[i];
		
		ArrayList<item> list = new ArrayList<>();
		ArrayList<item> list2 = new ArrayList<>();
		for(int i=0; i<N; i++) {
			item it = new item(str.charAt(i), -1);
			list.add(it);
			list2.add(it);
		}
		boolean possible = true;
		outer:while(!sorted.equals(createString(list, N))) {
			//System.out.println(sorted);
			//System.out.println(createString(list, N));
			//System.out.println(list);
			char highest = 'a';
			for(int n=0; n<N; n++) {
				char cur = list.get(n).c;
				if(cur > highest) highest = cur;
				if(cur < highest) {
					//System.out.println("cur: " + cur + ", highest: "+highest);
					findInsertion(list, N, n);
					if(has0 && has1) {
						possible = false; break outer;
					} else {
						continue outer;
					}
				}
			}
		}
		if(possible) {
			String oo = "";
			for(int n=0; n<N; n++) {
				if(list2.get(n).val==0) {
					oo += "0";
				} else {
					oo += "1";
				}
			}
			out.println("YES");
			out.println(oo);
		} else {
			out.println("NO");
		}
	}
	
	boolean has0 = false;
	boolean has1 = false;
	private void findInsertion(ArrayList<item> list, int N, int n) {
		char c = list.get(n).c;
		int index = -1;
		for(int i=0; i<N; i++) {
			if(list.get(i).c > c) {
				index = i;
				break;
			}
		}
		has0 = false;
		has1 = false;
		for(int i=index; i<n; i++) {
			if(list.get(i).val == 0) has0 = true;
			if(list.get(i).val == 1) has1 = true;
		}
		if(has0 && has1) {
		} else {
			//System.out.println("has "+ has0 + ", " + has1 + ", " + index + ", " + n);
			if(has0) {
				for(int i=index; i<n; i++) {
					list.get(i).val = 0;
				}
				list.get(n).val = 1;
			} else {
				for(int i=index; i<n; i++) {
					list.get(i).val = 1;
				}
				list.get(n).val = 0;
			}
			item i = list.remove(n);
			list.add(index, i);
		}
	}

	String createString(ArrayList<item> list, int N) {
		String ret = "";
		for(int n=0; n<N; n++) {
			ret += ""+list.get(n).c;
		}
		return ret;
	}
}
