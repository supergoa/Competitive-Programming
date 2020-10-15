import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class cow {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new cow().solve(in,out);
		in.close();
		out.close();
	}

	int L;
	String sub = "COW";
	String str;
	long[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		memo = new long[(int) 1e5][3];
		for(int n=0;n<N;n++) {
			L = in.nextInt();
			str = in.next();
			
			int[] cs = new int[L];
			int[] os = new int[L];
			int[] ws = new int[L];
			for(int i=0; i<L; i++) {
				if(str.charAt(i)=='C') {
					if(i==0) cs[i] = 1;
					else cs[i] = 1 + cs[i-1]; 
				} else if(i!=0) cs[i] = cs[i-1];
				if(str.charAt(i)=='O') {
					if(i==0) os[i] = 1;
					else os[i] = 1 + os[i-1]; 
				} else if(i!=0) os[i] = os[i-1];
				if(str.charAt(i)=='W') {
					if(i==0) ws[i] = 1;
					else ws[i] = 1 + ws[i-1]; 				
				} else if(i!=0) ws[i] = ws[i-1];
			}
			long ans = 0;
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i)=='O') {
					ans += (cs[i])*(ws[L-1]-ws[i]);
				}
			}
			//for(int m=0;m<L;m++) Arrays.fill(memo[m], -1);
			//long ans = dp(0,0);
			System.out.println(ans);
		}
		
	}
	/*private long dp(int i, int j) {
		//System.out.println(i + " " + j);
		if(j==3) return 1;
		if(i==L) return 0;
		
		if(memo[i][j]!=-1) return memo[i][j];
	//	System.out.println("he");
		long ans = 0;
		for(int a=i; a<L; a++) {
			//System.out.println(sub.charAt(j) + " " + str.charAt(a));
			if(sub.charAt(j)==str.charAt(a)) {
				ans+=dp(a+1,j+1);
			}
		}
		return memo[i][j] = ans;
	}*/
}
