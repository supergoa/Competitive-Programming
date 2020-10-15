import java.io.PrintWriter;
import java.util.Scanner;

public class E {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new E().solve(in, out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		String num = in.next();
		
		int inx = 0;
		for(char c:num.toCharArray()) {
			if(c=='0') inx++;
			else break;
		}
		
		num = num.substring(inx, num.length());
		int N = num.length();
		//System.out.println(num);
		
		int zero1 = num.lastIndexOf('0',N);
		int zero2 = num.lastIndexOf('0',zero1-1);
		int five = num.lastIndexOf('5',N);
		int two = num.lastIndexOf('2',N);
		int seven = num.lastIndexOf('7',N);
		
		
		boolean cannotFive = false;
		boolean cannotTwo = false;
		boolean cannotSeven = false;
		if(five==0 &&num.charAt(1)=='0') cannotFive = true;
		if(seven==0 &&num.charAt(1)=='0') cannotTwo=true;
		if(two==0 &&num.charAt(1)=='0') cannotSeven=true;
		
		//System.out.println(zero1 + " " + zero2);
		int ans = Integer.MAX_VALUE;
		if(zero1!=-1 && zero2!=-1) {
			ans = Math.min(ans, (N - zero1) +(N-1 - zero2) -2);
			//System.out.println(ans);
		}
		if(zero1!=-1 && five!=-1) {
			if(five==0 &&num.charAt(1)=='0') {
				ans = Math.min(ans,2*N - 1 - five -3);
			} else {
				ans = Math.min(ans,2*N - zero1 - five -2);
			}
			//System.out.println(ans);
		}
		if(two!=-1 && five!=-1 && !cannotFive && !cannotTwo) {
			ans = Math.min(ans,2*N - two - five -2);
			//System.out.println(ans);
		}
		if(seven!=-1 && five!=-1 && !cannotFive && !cannotSeven) {
			ans = Math.min(ans,2*N - seven - five -2);
			//System.out.println(ans);
		}
		//Math.min(ans, 
		out.print(ans==Integer.MAX_VALUE?-1:ans);
	}
}
