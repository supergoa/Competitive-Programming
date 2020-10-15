import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class B {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int[] one = new int[26];
		int[] two = new int[26];
		
		String s = in.next();
		String t = in.next();
		if(s.equals(t)) {out.print(0); return;}
		
		for(int i=0; i<N; i++) {
			one[s.charAt(i)-'a']++;
			two[t.charAt(i)-'a']++;
		}
		for(int i=0; i<26; i++) {
			if(one[i]!=two[i]) {
				out.print(-1);
				return;
			}
		}
		ArrayList<Integer> moves = new ArrayList<>();
		boolean possbile = false;
		for(int i=0; i<10000; i++) {
			for(int j=0; j<N; j++) {
				if(s.charAt(j)!=t.charAt(j)) {
					//out.println(s.charAt(j) + " " + t.charAt(j));
					int ind = -1;
					for(int k=0; k<N; k++) {
						if(s.charAt(k)==t.charAt(j) && s.charAt(k)!=t.charAt(k)) {
							ind = k;
						}
					}
					if(ind==-1) {out.print(-3);return;}
					if(ind<j) {
						for(int a=ind; a<j; a++) {
							moves.add(a+1);
						}
						s = s.substring(0, ind) + s.substring(ind+1, j+1) + s.substring(ind, ind+1) + s.substring(j+1, N);
						//out.println("1"+s);
					} else {
						for(int a=ind-1; a>=j; a--) {
							moves.add(a+1);
						}
						//out.println(j + " " + ind);
						s = s.substring(0, j) + s.substring(ind, ind+1) + s.substring(j, ind) + s.substring(ind+1, N);
						//out.println("2"+s);
					}
				}
			}
			if(s.equals(t)) possbile = true;// out.println(s + " " + t);}
			if(possbile) break;
		}
		if(possbile) {
			out.println(moves.size());
			for(int i:moves) {
				out.print(i + " ");
			}
		} else {
			out.print(-1);
		}
	}
}
