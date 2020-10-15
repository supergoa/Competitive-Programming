import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class G {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new G().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		char a = in.next().charAt(0);
		int n = in.nextInt();
		if(n==1) out.print(0);
		if(a=='r' && n>=2 && n<=5) {
			out.print(1);
		}
		if(a=='c' && n>=2 && n<=7) {
			out.print(1);
		}
		if(a=='i' && n>=2 && n<=4) {
			out.print(1);
		}
		
		if(a=='r' && n>=6 && n<=10) {
			out.print(2);
		}
		if(a=='c' && n>=8 && n<=14) {
			out.print(2);
		}
		if(a=='i' && n>=5 && n<=8) {
			out.print(2);
		}
		
		if(a=='r' && n>=11 && n<=15) {
			out.print(3);
		}
		if(a=='c' && n>=15 && n<=20) {
			out.print(3);
		}
		if(a=='i' && n>=9 && n<=12) {
			out.print(3);
		}
		
		if(a=='r' && n>=16 && n<=20) {
			out.print(4);
		}
		if(a=='i' && n>=13 && n<=16) {
			out.print(4);
		}
		if(a=='i' && n>=17 && n<=20) {
			out.print(5);
		}
	}
}
