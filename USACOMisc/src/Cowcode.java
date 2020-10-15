import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Cowcode {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		PrintWriter out;
		try {
			in = new Scanner(new File("cowcode.in"));
			out = new PrintWriter(new File("cowcode.out"));
		} catch(Exception e) {
			in = new Scanner(System.in);
			out = new PrintWriter(System.out);
		}
		new Cowcode().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		String str = in.next();
		int len = str.length();
		long N = in.nextLong();
		while(N>len) {
			long size = len;
			while(size<N) {
				size*=2;
			}
			long end = size;
			long start = size/2+1;
			N--;
			if(N<start) N = end;
			N-=size/2;
		}
		out.println(str.charAt((int) (N-1)));
	}
} /*

ALTERNATE (better according to Athu) SOLN WITH RECURSION


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Cowcode {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		PrintWriter out;
		try {
			in = new Scanner(new File("cowcode.in"));
			out = new PrintWriter(new File("cowcode.out"));
		} catch(Exception e) {
			in = new Scanner(System.in);
			out = new PrintWriter(System.out);
		}
		new Cowcode().solve(in,out);
		in.close();
		out.close();
	}

	int len;
	private void solve(Scanner in, PrintWriter out) {
		String str = in.next();
		len = str.length();
		long N = in.nextLong();
	
		int ind = (int) recurse(N);
		out.println(str.charAt((int) (ind-1)));
		
	}

	private long recurse(long N) {
		long size=len;
		while(size<N) {
			size*=2;
		}
		if(N<=len) {
			return (int) N;
		}
		long start = size/2+1;
		if(N==start) N = size/2;
		else {
			N--;
			N-=size/2;
		}
		return recurse(N);
	}
}
*/
