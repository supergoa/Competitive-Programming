import java.io.PrintWriter;
import java.util.Scanner;

public class J {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new J().solve(in,out);
		in.close();
		out.close();
	}
	static long GCD(long A, long B) {
	    if(B==0)
	        return A;
	    else
	        return GCD(B, A % B);
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			long l = in.nextLong();
			if(l<10) l=10;
			long r = in.nextLong();
			long ans = -1;
			for(long j=r; j>=l; j--) {
				long num1=0;
				long num2=0;
				String js = j+"";
				if(js.length()%2==0) {
					num1 = Long.parseLong(js.substring(0,js.length()/2));
					num2 = Long.parseLong(js.substring(js.length()/2,js.length()));
					//System.out.println(num1+ " " +num2);
				} else {
					num1 = Long.parseLong(js.substring(0,js.length()/2+1));
					num2 = Long.parseLong(js.substring(js.length()/2+1,js.length()));
					//System.out.println(num1+ " " +num2);
				}
				if(num1==0 || num2==0) continue;
				if(GCD(num1,num2)==1) {
					ans=j;
					break;
				}
			}
			out.println(ans);
		}
		
	}
}
