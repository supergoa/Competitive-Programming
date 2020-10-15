import java.io.PrintWriter;
import java.util.Scanner;

public class shuffle {
	public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			PrintWriter out = new PrintWriter(System.out);
			new shuffle().solve(in,out);
			in.close();
			out.close();
	}
/*
 * 2
4
AHAH
HAHA
HHAAAAHH
3
CDE
CDE
EEDDCC
 */
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int C = in.nextInt();
			char[] s1 = in.next().toCharArray();
			char[] s2 = in.next().toCharArray();
			char[] s12 = in.next().toCharArray();
			
			char[] tempBig = new char[2*C];
			int ans = -1;
			for(int i=0; i<100000; i++) {
				
				for(int j=0; j<2*C; j++) {
					if(j%2==0)
						tempBig[j] = s2[j/2];
					else
						tempBig[j] = s1[j/2];
				}
				
				boolean good = true;
				for(int j=0; j<2*C; j++) {
					if(tempBig[j]!=s12[j]) {
						good = false;
					}
					//System.out.print(tempBig[j]);
				}
				//System.out.println();
				if(good) {
					ans = i+1;
					break;
				}
				
				for(int j=0; j<C; j++) {
					s1[j] = tempBig[j];
				}
				for(int j=C; j<2*C; j++) {
					s2[j-C] = tempBig[j];
				}
			}
			out.println((n+1) + " " + ans);
		}
		
	}
}
