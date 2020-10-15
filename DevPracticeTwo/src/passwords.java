import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class passwords {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new passwords().solve(in,out);
		in.close();
		out.close();
	}
/*
 * 2
3
abc
xy
dmnr
10
2
abcdefghijklmnopqrstuvwxyz
abcdefghijklmnopqrstuvwxyz
676
 */
	int numSoln = 0;
	String[] letters;
	int M;
	int R;
	int canSkip;
	long len;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			M = in.nextInt();
			letters = new String[M];
			for(int m=0; m<M; m++) {
				letters[m] = in.next();
			}
			R = in.nextInt();
			numSoln = 0;
			/*int[] inds = new int[M];
			for(int m=0; m<M; m++) {
				long val = 1;
				for(int j=m+1; j<M; j++) {
					val*=letters[j].length();
				}
				int tempR = R;
				int ind = 0;
				//System.out.println(m + " " + val);
				while(tempR >= val+1) {
					
						tempR-=val;
						ind++;
						if(ind == letters[m].length()) {
							ind = 0;
						}
						
				}
				inds[m] = ind;
			
			}*/
			numSoln=0;
			done=false;
			rec(0,new char[M]);
			for(int i=0; i<M; i++) {
				//out.print(letters[i].charAt(inds[i]));
				out.print(ans[i]);
			}
			out.println();
		}
		
	}
	char[] ans;
	boolean done = false;
	private void rec(int ind, char[] s) {
		if(done) return;
		if(ind==M) {
			numSoln++;
			if(numSoln==R) {
				ans = s.clone();
				done = true;
			}
			return;
		}
		//start = (int) ((ind==0)?canSkip/len:0);
		for(int i= 0; i<letters[ind].length(); i++) {
			s[ind] = letters[ind].charAt(i);
			rec(ind+1, s);
		}
		
	}
}
